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
        maxBytes: 10 * 1024 * 1024, // Set file size limit to 10MB
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

        // Upload the image to Cloud Storage
        await uploadToCloudStorage(fileBuffer, fileName);

        return h.response({ message: 'Image uploaded successfully' }).code(200);
      } catch (error) {
        console.error('Error uploading image:', error);
        return h.response({ message: 'Server error' }).code(500);
      }
    },
  },
];

module.exports = mlRoutes;
