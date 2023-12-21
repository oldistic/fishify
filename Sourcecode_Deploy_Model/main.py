import os
import chardet
import numpy as np
import pandas as pd
import tensorflow as tf
from google.cloud import storage
from io import BytesIO
from flask import Flask, request, jsonify
from keras.models import load_model
from tensorflow.keras.applications.mobilenet import preprocess_input

app = Flask(__name__)
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = 'fishify-credentials.json'
storage_client = storage.Client()


def req(y_true, y_pred):
    req = tf.metrics.req(y_true, y_pred)[1]
    tf.keras.backend.get_session().run(tf.local_variables_initializer())
    return req


model = load_model('FishifyModel.h5', custom_objects={'req': req})

# Mapping of fish indices to fish names
fish_names = {
    0: 'IKAN BAWAL',
    1: 'BELUT',
    2: 'IKAN GURAME',
    3: 'IKAN KEMBUNG',
    4: 'IKAN LELE',
    5: 'IKAN MAS',
    6: 'IKAN NILA',
    7: 'IKAN PATIN',
    8: 'IKAN TENGGIRI',
    9: 'UDANG'
    # Add more fish names as needed
}

@app.route('/', methods=['GET', 'POST'])
def predict_fish():
    if request.method == 'POST':
        try:
            image_bucket = storage_client.get_bucket('fishify')  # Change the bucket name
            filename = request.json['filename']
            img_blob = image_bucket.blob('upload_picture/' + filename)
            img_path = BytesIO(img_blob.download_as_bytes())
        except Exception as e:
            respond = jsonify({'message': f'Error loading image file: {str(e)}'})
            respond.status_code = 400
            return respond

        img = tf.keras.utils.load_img(img_path, target_size=(224, 224))
        x = tf.keras.utils.img_to_array(img)
        x = np.expand_dims(x, axis=0)
        x = preprocess_input(x)
        images = np.vstack([x])

        # model predict
        pred_fish_index = model.predict(images).argmax()
        predicted_fish_name = fish_names.get(pred_fish_index, 'Unknown Fish')

        
        # Read the CSV file with the detected encoding
        df = pd.read_csv('Data_Ikan_UTF8.csv')

        # Mengambil nilai dari CSV berdasarkan nama ikan
        if predicted_fish_name in df['NAMA IKAN'].values:
            fish_data = df[df['NAMA IKAN'] == predicted_fish_name].iloc[0]
            description = fish_data['DESKRIPSI']
            nutrients = fish_data['KANDUNGAN']
            recipe_columns = ['RESEP 1', 'RESEP 2', 'RESEP 3', 'RESEP 4']
            recipes = {column: fish_data[column] for column in recipe_columns}
        else:
            description = "Unknown Description"
            nutrients = "Unknown Nutrients"
            recipes = {}
            
        result = {
            "fish_name": predicted_fish_name,
            "description": description,
            "nutrients": nutrients,
            "recipes": recipes,
        }

        respond = jsonify(result)
        respond.status_code = 200
        return respond

    return 'OK'

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
