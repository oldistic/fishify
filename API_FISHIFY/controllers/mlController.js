const { Storage } = require('@google-cloud/storage');

const projectId = 'loyal-world-406507'; 
const keyFilename = {
  "type": "service_account",
  "project_id": "loyal-world-406507",
  "private_key_id": "4dd5b8ccde9ebb43b3cae5666733d6b662ee4d8e",
  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC+wZ47VyKVg4uM\now4UTC97oJfwIhLl/ekBlWXP4hMjALtIReuy7K2SKAcsn2o6DCPI9kq0IvMHw/gD\n2Vo8UqE+jQRGhLtGX8nZK9LC7rR5dEVhCNzlbBCC7FuUsASTZKxCXvxiidKg3WgX\ngPpmTe1a4uE/0o2h1Yv4vwY1wKwYQTPgMWJiZImSvGvLk2D1lAm6XMk2HPXb5/iU\nTf6qt03DLBvkAPUc3CsXuAjcggXLRjKBTSFr8ZmQk5uWl3gGjaE7iez7tV1HHY9H\nk9a+AQp2K3Hk+Oerzgmaj6QuK4OAwWNo9J35SqkgVgDYRIyjab/d8SDu1/GRiHrh\nMYxNWJ9XAgMBAAECggEAWe3su3wGTWdxNDGw0wfhwM5Cow5lS3FLVphIodsqe29+\nW4G/LyCeaY+eZj6XdVtAyZ63NDpfydLtC19+R38LDnKrx9UOdwx0nAIpgAXKJpIW\nnTrw7OXn2Nk2CaB738Lv5PBj2s6v8m+/AYmoQu/NDPJ2D8EjTV7f+LqR4mh9mYbe\n3bP8PGQ2QX4R+HBb4dlW2JtfBD61t57LV9IfFP0ApIfY9EBHuSCkkwf9c4H2I54O\nco4SR0Mir1+cjLngH5o2QuoL9zQYnNyd9SbYEh+cD2OIR3fXjV+KIZqOYFgQlA0V\nz01ma1DvW3XDEfIEoxOffmSsKe17qp6BKJ5TSnU0AQKBgQDtF05Z8OT4tSiYi1e5\nTToUg2kitGNtix3cQgXPIMh97Qg6HXLiScE1zqFHkVawaB6iH/MsN2o6KklZ/7rL\n+0KjyQXeYM1qI+rJ3Sr5KoYxPX+S8xdRFkxqSUn/ZcLbId7uuAY1MyJOZ9lCy9Wk\njQO7NIZlKxJ8hDgqwpHDRAAcAQKBgQDN+Et8zhROUBiPCNC6pIR1OKglTFJDfMlf\nnf0tAbCfS1Vlx2PP+NTSad/+rKBxW/o8gt8D/QHA2ANsElL5B/jCazN95I2g6QfW\nBJ3xKv/exudGJdG8yafsTqkVw4MvYrgzI7hTlN32s0qH1mSaRGPzSVagrpd+2mm/\ncyWiOlsbVwKBgQCb7qsiblQ52vQDOAoPHmKnoTIhmCCYqFmQRoAJlVcnsyCpEDtB\n5x5C5E6ggk9QoHodSQQnPZ8I+Ezb+1yA0hUnIYxRkLspJsxBzct4quYtNnRUUrsw\nIolJkkAv0F7Svrkfqjqy/JmAlb6HtwYhPnmIw2fs2+JBsLg8L1Gs6mlkAQKBgHR0\nFEomQjjc9GW6uDku121ncTpVYpWhskGLlB+kOvZ1o3z+NSLbL4kgL3NQqw4s0Njx\nraIVpK4h9B1gZuVCapWSAvUFCkaWOsX4qkw24aBVmWGB9InnAjiLaPJTLpLWIAta\nANfQ7YaUbGlNAGhwjop5Rq9mx2UDnJKQShDlKanVAoGBAKumw+deyDlw2NW2jD/M\n9FHkxcP1vJPG38nGolR18joC/icDtBIlPOczYRzHhsfyECjGM2ArC92wJ+QlUhtD\n6OGV8h0Hq1muXQBiQrC/4JEOM3kKEyDYO6ipMGC3NwH1uF/09WmbhJdTnURnY50/\nvfr7Z6Gaw0pZ3XX0qhO5K0YJ\n-----END PRIVATE KEY-----\n",
  "client_email": "upload-image@loyal-world-406507.iam.gserviceaccount.com",
  "client_id": "105920176787916462119",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/upload-image%40loyal-world-406507.iam.gserviceaccount.com",
  "universe_domain": "googleapis.com"
}
;

const storage = new Storage({
  projectId: projectId,
  keyFilename: keyFilename,
});

const bucketName = 'fishify';
const bucket = storage.bucket(bucketName);
const cloudStoragePath = 'upload_picture';
const uploadToCloudStorage = async (fileBuffer, fileName) => {
  try {
    console.log('Before save - fileBuffer:', fileBuffer);

    if (!fileBuffer) {
      throw new Error('Before save - fileBuffer is undefined or null');
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
