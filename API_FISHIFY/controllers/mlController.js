const { Storage } = require('@google-cloud/storage');
const axios = require('axios');

const projectId = 'loyal-world-406507'; // Ganti dengan ID proyek Google Cloud Anda
const bucketName = 'fishify'; // Ganti dengan nama bucket Cloud Storage Anda
const cloudRunEndpoint = 'https://fishify-ml-vdvzsu6mbq-uc.a.run.app'; // Ganti dengan URL Cloud Run Anda

const storage = new Storage({ projectId });
const bucket = storage.bucket(bucketName);

const uploadToCloudStorage = async (fileBuffer, fileName) => {
  try {
    const file = bucket.file(fileName);
    await file.save(fileBuffer);

    console.log(`File ${fileName} uploaded to Cloud Storage`);
    return file;
  } catch (error) {
    console.error('Error uploading file to Cloud Storage:', error);
    throw error;
  }
};

const callCloudRun = async (fileName) => {
  try {
    const response = await axios.post(cloudRunEndpoint, { fileName });

    if (response.status !== 200) {
      console.error('Error calling Cloud Run:', response.statusText);
      throw new Error('Failed to call Cloud Run');
    }

    const result = response.data;
    console.log('Cloud Run response:', result);
    return result;
  } catch (error) {
    console.error('Error calling Cloud Run:', error);
    throw error;
  }
};

const processImageAndDetectFish = async (fileBuffer, fileName) => {
  try {
    // Upload gambar ke Cloud Storage
    const cloudStorageFile = await uploadToCloudStorage(fileBuffer, fileName);

    // Panggil Cloud Run dengan parameter nama file
    const cloudRunResult = await callCloudRun(cloudStorageFile.name);
    return cloudRunResult;
  } catch (error) {
    console.error('Error processing image and detecting fish:', error);
    throw error;
  }
};

module.exports = { processImageAndDetectFish };
