# Fishify
## Fishify: Consumable Fish Detection Application Repository

Fishify API adalah bagian dari aplikasi Fishify, yang bertujuan untuk mendeteksi ikan. Tim pengembang terbagi menjadi tiga: tim pengembang mobile develpopment(MD)yang bertugas membangun tampilan aplikasi, tim pegembang machine learning (ML) yang betugas membuat model dan tim cloud computing (CC) yang bertugas membuat API. API ini menggunakan teknologi RESTful dengan MySQL di Google Cloud Platform dan framework Hapi.

## Instalasi

### Clone repositori:

Copy code: git clone https://github.com/oldistic/fishify.git

### Pindah ke direktori proyek:

Copy code: cd fishify-api

### Instal dependensi:

Copy code: npm install

## Penggunaan

### Mulai server:

Copy code: npm start

Tim pengembang mobile dapat menggunakan API yang di-host di http://localhost:4000.

## Endpoint API

### Autentikasi

POST /register: Mendaftarkan pengguna baru.

POST /login: Melakukan login dan mengembalikan token otentikasi.

### Resep

GET /recipes/{id}: Mendapatkan informasi resep berdasarkan ID.

GET /recipes/name/{name}: Mendapatkan informasi resep berdasarkan nama.

### Gambar

POST /upload: Mengunggah gambar ke penyimpanan awan.
