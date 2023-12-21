const { Storage } = require('@google-cloud/storage');

const projectId = 'loyal-world-406507'; // Replace with your Google Cloud project ID
const bucketName = 'fishify'; // Replace with your Cloud Storage bucket name

const storage = new Storage({ projectId });
const bucket = storage.bucket(bucketName);
const cloudStoragePath = 'upload_picture'; 

const uploadToCloudStorage = async (fileBuffer, fileName) => {
  try {
    if (!fileBuffer) {
      throw new Error('fileBuffer is undefined or null');
    }

    const file = bucket.file(`${cloudStoragePath}/${fileName}`);
    await file.save(Buffer.from(fileBuffer));

    console.log(`File ${fileName} uploaded to Cloud Storage`);
    return file;
  } catch (error) {
    console.error('Error uploading file to Cloud Storage:', error);
    throw error;
  }
};


module.exports = { uploadToCloudStorage };
