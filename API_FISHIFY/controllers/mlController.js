const { Storage } = require('@google-cloud/storage');

const projectId = 'loyal-world-406507'; 
const bucketName = 'fishify'; 

const storage = new Storage({ projectId });
const bucket = storage.bucket(bucketName);
const cloudStoragePath = 'upload_picture';

const uploadToCloudStorage = async (fileBuffer, fileName) => {
  try {
    console.log('fileBuffer:', fileBuffer);
    if (!fileBuffer) {
      throw new Error('fileBuffer is undefined or null');
    }

    const file = bucket.file(`${cloudStoragePath}/${fileName}`);
    await file.save(fileBuffer);

    console.log(`File ${fileName} uploaded to Cloud Storage`);
    return file;
  } catch (error) {
    console.error('Error uploading file to Cloud Storage:', error);
    throw error;
  }
};

module.exports = { uploadToCloudStorage };