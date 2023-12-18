// src/controllers/recipes.js

const recipes = [
    {
      id: 1,
      name: 'Ikan Patin Bumbu Kecap',
      ingredients: [
        '1/2 kg ikan patin',
        '1 bawang bombay',
        '4 biji bawang merah',
        '4 siung bawang putih',
        'cabai rawit secukupnya',
        '2 buah tomat, potong-potong',
        '1 ruas jahe, memarkan',
      ],
      seasoning: [
        '2 sdm kecap manis',
        '2 sdm saus tiram',
        'Kaldu bubuk, lada bubuk, gula, dan garam secukupnya',
      ],
      instructions: [
        'Bersihkan ikan, lalu lumuri dengan garam dan sedikit lada bubuk, diamkan sepuluh menit',
        'Panaskan teflon, tambahkan sedikit minyak, masukkan 1 ruas jahe yang sudah digeprek (fungsi jahe ini untuk menghilangkan bau amis ikan). Goreng ikan sampai kedua belah sisi kering berwarna kecokelatan. Sisihkan',
        'Iris-iris semua bahan lainnya, tumis bawang bombay sampai layu, lalu masukkan bawang merah bawang putih dan cabai, tumis sampai harum.',
        'Masukkan ikan dan tomat, tambahkan air secukupnya. Tambahkan semua bumbu. Masak sampai air agak menyusut dan bumbu meresap.',
        'Koreksi Rasa, lalu angkat.',
      ],
    },
    {
      id: 2,
      name: 'Ikan Patin Kuah Belimbing',
      ingredients: [
        '4 potong ikan patin',
        '5 siung bawang merah',
        '2 siung bawang putih',
        '1 ruas kunyit bakar',
        '1 batang serai',
        '1 ruas jahe',
        '3 lembar daun jeruk',
        '4 cabai merah besar',
        '500 ml air',
        '1 sdt garam',
        '4 buah belimbing wuluh ukuran kecil',
        '3 sdm minyak sayur',
      ],
      instructions: [
        'Cuci bersih ikan patin, beri 1/2 sdt garam, kemudian aduk sampai rata, sisihkan.',
        'Haluskan bawang merah, bawang putih, dan kunyit bakar.',
        'Geprek serai, jahe, iris cabai, dan tomat, belimbing wuluh sisihkan.',
        'Panaskan 500 ml air sampai mendidih, sisihkan',
        'Panaskan minyak sayur, tumis bumbu halus, lalu masukkan serai dan daun jeruk.',
        'Lalu masukkan jahe geprek dan garam, tumis sampai bumbu matang.',
        'Tambahkan air mendidih, lalu aduk sampai rata.',
        'Tambahkan ikan, masak sampai ikan matang, lalu masukkan belimbing wuluh dan irisan tomat, masak sampai matang, sekitar 10 menit.',
        'Setelah matang, angkat dari wajan pindahkan ke piring saji, ikan patin kuah belimbing siap untuk disajikan.',
      ],
    },
    {
      id: 3,
      name: 'Mangut Lele',
      ingredients: [
        '1/2 kg ikan lele digoreng',
        '1 paket kecil santan kelapa',
        'Secukupnya cabai rawit utuh',
        'Secukupnya kemangi',
        '1 serai geprek',
        '2 daun salam',
        '2 daun jeruk purut',
        'Lengkuas geprek',
        'Garam, penyedap rasa, gula secukupnya',
        'Air secukupnya',
      ],
      spicePaste: [
        '1 sdt ketumbar',
        '3 bawang putih',
        '4 bawang merah',
        '2 ruas kunyit',
        '1 kencur',
        '2 kemiri',
      ],
      instructions: [
        'Tumis bumbu halus bersama lengkuas, daun salam, dan daun jeruk purut sampai harum.',
        'Masukkan santan, tambahkan air secukupnya, masukkan garam, penyedap rasa.',
        'Masukkan ikan lele yang sudah digoreng, lalu tambahkan cabe rawit utuh, aduk rata.',
        'Koreksi rasa, sesaat sebelum diangkat masukkan kemangi, aduk sebentar lalu matikan api.',
        'Mangut lele siap disajikan.',
      ],
    },
  ];
  
  module.exports = recipes;
  