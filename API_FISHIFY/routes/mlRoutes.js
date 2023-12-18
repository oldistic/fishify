const { processImageAndDetectFish } = require('../controllers/mlController');
const Joi = require('@hapi/joi');

const mlRoutes = [
  {
    method: 'POST',
    path: '/upload-and-detect-fish',
    options: {
      payload: {
        output: 'stream',
        parse: true,
        allow: 'multipart/form-data',
        maxBytes: 10 * 1024 * 1024, // Set batas ukuran file (2MB)
      },
      validate: {
        payload: Joi.object({
          image: Joi.any().required(),
        }),
      },
    },
    handler: async (request, h) => {
      try {
        const { image } = request.payload;
        const fileBuffer = image._data;
        const fileName = image.hapi.filename;

        // Proses gambar dan deteksi ikan
        const result = await processImageAndDetectFish(fileBuffer, fileName);

        return h.response(result).code(200);
      } catch (error) {
        console.error('Error processing image:', error);
        return h.response({ message: 'Terjadi kesalahan server' }).code(500);
      }
    },
  },
];

module.exports = mlRoutes;
