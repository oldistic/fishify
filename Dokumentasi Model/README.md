- CloudShell_Filename : merupakaan tampilan file source code/pendukung main.py pada CloudShell untuk di Deploy pada CloudRun
- CloudRun_Filename : merupakan tampilan service yang sudah di upload di CloudRun
- Postman_Filename : merupakan tampilan postman ketika service dijalankan setelah di deploy di CLoudRun.

Cara Deploy di CloudRun :
- Siapkan file" pada root directory. (main.py, credential.json, requirements.txt, Data.csv, model.h5)
- Buka terminal pada cloudshell, lalu jalankan "gcloud config set project [PROJECT-ID]" dan pindah ke direktori project(root folder)
- Jalankan "gcloud run deploy" maka terminal akan memastikan beberapa hal seperti :
    - lokasi project yang akan di deploy,
    - nama service yang akan dibuat,
    - region yang akan digunakan untuk deploy,
    - terminal akan memberikan pesan "Allow unauthenticated invocations to [test] (y/N)?" klik y dan lanjutkan,
    - tunggu hingga proses deploy berhasil
    - service sudah bisa dilihat pada cloudrun. copy link url service dan cobakan pada postman
 
Postman : 
- Pada postman set environment dan masukan link url service pada kolom Initial Value
- Buka tab collections dan salin link url service pada kolom yang ada printah masukan URL
- Ubah method menjadi post, lalu pada tab Body pilih bagian RAW. Pada bagian RAW ketik "{"filename" : "nama_file_dalam_bucket.jpg"}". Pastikan file dengan nama tersebut sudah ada di dalam bucket.
- klik POST dan response akan mengembalikan data ikan yang dideteksi
