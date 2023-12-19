import os
import random
import numpy as np
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import image
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from sklearn.metrics import confusion_matrix, classification_report
import matplotlib.pyplot as plt
import seaborn as sns

# Load the trained model
model = load_model('image_classification_model.h5')

# Define the path to the validation folder
valid_folder_path = 'trainn'

# Create a data generator for the validation set
valid_datagen = ImageDataGenerator(rescale=1./255)
valid_generator = valid_datagen.flow_from_directory(
    valid_folder_path,
    target_size=(224, 224),
    batch_size=1,
    class_mode='categorical',
    shuffle=False
)

# Get the true labels and predicted labels
true_labels = valid_generator.classes
num_samples = len(true_labels)

predicted_labels = []
class_labels = list(valid_generator.class_indices.keys())

# Loop through each image and make predictions
for i in range(num_samples):
    img, true_label = valid_generator[i]
    predictions = model.predict(img)
    predicted_label = np.argmax(predictions)
    predicted_labels.append(predicted_label)

# Create a confusion matrix
conf_matrix = confusion_matrix(true_labels, predicted_labels)

# Display the confusion matrix
plt.figure(figsize=(10, 8))
sns.heatmap(conf_matrix, annot=True, fmt='d', cmap='Blues', xticklabels=class_labels, yticklabels=class_labels)
plt.title('Confusion Matrix')
plt.xlabel('Predicted')
plt.ylabel('True')
plt.show()

# Print classification report
print("Classification Report:\n", classification_report(true_labels, predicted_labels, target_names=class_labels))
