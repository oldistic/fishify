const { uploadToCloudStorage } = require('../controllers/mlController');
const Joi = require('@hapi/joi');

const mlRoutes = [
  {
    method: 'POST',
    path: '/upload-image',
    options: {
      payload: {
        output: 'stream',
        parse: true,
        allow: 'multipart/form-data',
        maxBytes: 10 * 1024 * 1024, // Set batas ukuran file (10MB)
      },
      validate: {
        payload: Joi.object({
          image: Joi.any().required(),
        }),
      },
    },
    handler: async (request, h) => {
      try {
        console.log('Entering /upload-image handler function'); // Tambahkan log ini
        console.log('Request payload:', request.payload);

        const { image } = request.payload;
        const fileBuffer = image._data;
        const fileName = image.hapi.filename;

        // Upload gambar ke Cloud Storage
        await uploadToCloudStorage(fileBuffer, fileName);

        return h.response({ message: 'Gambar berhasil diunggah' }).code(200);
      } catch (error) {
        console.error('Error uploading image:', error);
        return h.response({ message: 'Terjadi kesalahan server' }).code(500);
      }
    },
  },
];

module.exports = mlRoutes;
