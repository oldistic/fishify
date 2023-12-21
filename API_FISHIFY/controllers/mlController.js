const { Storage } = require('@google-cloud/storage');

const projectId = 'loyal-world-406507'; // Replace with your Google Cloud project ID
const bucketName = 'fishify'; // Replace with your Cloud Storage bucket name

const storage = new Storage({ projectId });
const bucket = storage.bucket(bucketName);
const cloudStoragePath = 'upload_picture'; // Replace with the desired path in the bucket

const uploadToCloudStorage = async (fileBuffer, fileName) => {
  try {
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
