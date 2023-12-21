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
        maxBytes: 10 * 1024 * 1024, // Set batas ukuran file (10MB)
      },
      validate: {
        payload: Joi.object({
          image: Joi.object({
            _data: Joi.binary().required(),
            hapi: Joi.object({
              filename: Joi.string().regex(/\.(jpg|jpeg|png)$/).required(),
            }).required(),
          }).required(),
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
