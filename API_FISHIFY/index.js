const Hapi = require('@hapi/hapi');
const HapiPino = require('hapi-pino');
const { testConnection } = require('./config/database');
const authRoutes = require('./routes/authRoutes');
const userRoutes = require('./routes/userRoutes');
const recipesRoutes = require('./routes/recipesRoutes');
const mlRoutes = require('./routes/mlRoutes');

const init = async () => {
  try {
    // Coba melakukan koneksi ke database
    await testConnection();

    // Jika berhasil, lanjutkan dengan inisialisasi server
    const server = await initServer();
    await server.start();
    console.log(`Server running on ${server.info.uri}`);
  } catch (error) {
    // Jika terjadi kesalahan pada koneksi database, cetak pesan kesalahan
    console.error('Failed to connect to the database:', error.message);
    process.exit(1); // Keluar dari aplikasi jika koneksi ke database gagal
  }
};

const initServer = async () => {
  const server = Hapi.server({
    port: process.env.PORT || 4000,
    host: process.env.NODE_ENV !== 'production' ? 'localhost' : '0.0.0.0',
  });

  // Registrasi plugin HapiPino untuk logging (opsional)
  await server.register({
    plugin: HapiPino,
    options: {
      format: 'pino-pretty', // Menggunakan pino-pretty untuk pretty print
      redact: ['req.headers.authorization'], // Opsional, untuk menyamarkan informasi sensitif
      // Atau tambahkan opsi lain yang Anda butuhkan
    },
  });

  // Registrasi plugin untuk menangani payload JSON
  await server.register({
    plugin: require('hapi-bodyparser'),
  });

  const routes = [...authRoutes, ...userRoutes, ...recipesRoutes, ...mlRoutes];
  server.route(routes);

  return server;
};

if (require.main === module) {
  // Panggil fungsi init untuk memulai aplikasi
  init();
}

// Ekspor fungsi init untuk digunakan dalam pengujian atau kasus lainnya
module.exports = { init };
