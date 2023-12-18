const mysql = require('mysql2/promise');

// Konfigurasi koneksi ke database
const pool = mysql.createPool({
  host: process.env.DB_HOST || '34.101.209.54',
  user: process.env.DB_USER || 'login',
  password: process.env.DB_PASSWORD || 'login123',
  database: process.env.DB_NAME || 'data_pengguna',
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0,
});

// Fungsi untuk menguji koneksi ke database
const testConnection = async () => {
  try {
    const connection = await pool.getConnection();
    console.log('Connected to the database');
    connection.release();
  } catch (error) {
    console.error('Error connecting to the database:', error);
    throw error;
  }
};

module.exports = { pool, testConnection };