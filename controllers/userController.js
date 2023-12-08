const { getUserById } = require('../models/userModel');

const getUser = async (request, h) => {
  try {
    const userId = request.params.id;
    
    // Mengambil data pengguna dari database berdasarkan ID
    const user = await getUserById(userId);

    if (!user) {
      return h.response({ message: 'Pengguna tidak ditemukan' }).code(404);
    }

    // Mengembalikan data pengguna
    return h.response(user).code(200);
  } catch (error) {
    console.error(error);
    return h.response({ message: 'Terjadi kesalahan server' }).code(500);
  }
};

module.exports = { getUser };
