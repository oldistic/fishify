const { pool } = require('../config/database');

// Fungsi untuk mendapatkan data pengguna berdasarkan ID
const getUserById = async (userId) => {
  try {
    const [rows] = await pool.execute('SELECT * FROM users WHERE id = ?', [userId]);
    return rows[0];
  } catch (error) {
    console.error('Error in getUserById:', error);
    throw error;
  }
};

// Fungsi untuk membuat pengguna baru dalam database
const createUser = async ({ name, email, username, password }) => {
  try {
    const [result] = await pool.execute('INSERT INTO users (name, email, username, password) VALUES (?, ?, ?, ?);', [name, email, username, password]);
    return result.insertId;
  } catch (error) {
    console.error('Error in createUser:', error);
    throw error;
  }
};

// Fungsi untuk mendapatkan data pengguna berdasarkan email
const getUserByEmail = async (email) => {
  try {
    const [rows] = await pool.execute('SELECT * FROM users WHERE email = ?', [email]);
    return rows[0];
  } catch (error) {
    console.error('Error in getUserByEmail:', error);
    throw error;
  }
};

// Fungsi untuk memeriksa kredensial pengguna saat login
const checkCredentials = async (email, password) => {
  try {
    const user = await getUserByEmail(email);

    if (user && user.password === password) {
      return user;
    } else {
      console.error('Invalid email or password');
      return null;
    }
  } catch (error) {
    console.error('Error in checkCredentials:', error);
    throw error;
  }
};

module.exports = { getUserById, createUser, getUserByEmail, checkCredentials };
