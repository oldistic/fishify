const { uploadToCloudStorage } = require('../controllers/mlController');
const Joi = require('@hapi/joi');

const mlRoutes = [
  {
    method: 'POST',
    path: '/upload',
    options: {
      payload: {
        parse: true,
        output: 'data', // Menggunakan 'data' untuk mendapatkan buffer langsung
        maxBytes: 209715200, // 200MB
        multipart: { output: 'file' },
      },
    },
    handler: async (request, h) => {
      try {
        console.log('Entering /upload-image handler function');
        console.log('Request payload:', request.payload);
        console.log('Actual Boundary in Content-Type:', request.headers['content-type'].split('boundary=')[1]);
    
        const { image } = request.payload;
    
        // Tambahkan log untuk menampilkan struktur image
        console.log('Image:', image);
    
        // Upload gambar ke Cloud Storage
        await uploadToCloudStorage(image._data, image.filename);
    
        return h.response({ message: 'Gambar berhasil diunggah' }).code(200);
      } catch (error) {
        console.error('Error uploading image:', error);
        return h.response({ message: 'Terjadi kesalahan server' }).code(500);
      }
    },
  },
];

module.exports = mlRoutes;
