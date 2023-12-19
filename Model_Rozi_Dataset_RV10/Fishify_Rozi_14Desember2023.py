import tensorflow as tf
from tensorflow.keras import layers, models
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.applications import MobileNetV2

# Define the paths for training and validation data
train_data_dir = 'train'
valid_data_dir = 'valid'

# Define image dimensions and batch size
img_height, img_width = 224, 224
batch_size = 32

# Create data generators with data augmentation for training
train_datagen = ImageDataGenerator(
    rescale=1./255,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True
)

train_generator = train_datagen.flow_from_directory(
    train_data_dir,
    target_size=(img_height, img_width),
    batch_size=batch_size,
    class_mode='categorical'
)

# Create data generators without data augmentation for validation
valid_datagen = ImageDataGenerator(rescale=1./255)

valid_generator = valid_datagen.flow_from_directory(
    valid_data_dir,
    target_size=(img_height, img_width),
    batch_size=batch_size,
    class_mode='categorical'
)
# Load MobileNetV2 pre-trained on ImageNet data
base_model = MobileNetV2(input_shape=(img_height, img_width, 3),
                        include_top=False,
                        weights='imagenet')

# Freeze the layers of MobileNetV2
for layer in base_model.layers:
    layer.trainable = False

# Build the transfer learning model
model = models.Sequential()
model.add(base_model)
model.add(layers.GlobalAveragePooling2D())
model.add(layers.Dense(128, activation='relu'))
model.add(layers.Dropout(0.5))
model.add(layers.Dense(len(train_generator.class_indices), activation='softmax'))


# Compile the model
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

# Train the model
epochs = 50
target_accuracy = 0.95
target_val_accuracy = 0.95

for epoch in range(epochs):
    history = model.fit(
        train_generator,
        epochs=1,
        validation_data=valid_generator
    )

    accuracy = history.history['accuracy'][0]
    val_accuracy = history.history['val_accuracy'][0]

    print(f"Epoch {epoch + 1}/{epochs}")

    # Check if the conditions are met
    if accuracy >= target_accuracy and val_accuracy >= target_val_accuracy:
        print(
            f"Training stopped. Target accuracy ({target_accuracy}) and target validation accuracy ({target_val_accuracy}) reached.")
        break
# Save the trained model
model.save('image_classification_model.h5')
