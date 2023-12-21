const { uploadToCloudStorage } = require('../controllers/mlController');
const Joi = require('@hapi/joi');

const mlRoutes = [
  {
    method: 'POST',
    path: '/upload',
   options: {
    payload: {
      parse: true,
      output: 'data',
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
        const fileBuffer = image._data;
        const fileName = image.filename;

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
