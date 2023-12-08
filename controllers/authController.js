const Joi = require('@hapi/joi');
const jwt = require('jsonwebtoken');
const { createUser, getUserByEmail, checkCredentials } = require('../models/userModel');
const { testConnection } = require('../config/database');

const generateJWTToken = (userId) => {
  const token = jwt.sign({ userId }, 'g(3VH*dsb23$%f9@!n2PQ^j1L8', { expiresIn: '1h' });
  return token;
};

const register = async (request, h) => {
  try {
    const schema = Joi.object({
      name: Joi.string().required(),
      email: Joi.string().email().required(),
      username: Joi.string().alphanum().min(3).max(30).required(),
      password: Joi.string().pattern(new RegExp('^[a-zA-Z0-9]{3,30}$')).required(),
    });

    const { error } = schema.validate(request.payload);
    if (error) {
      return h.response({ message: 'Invalid request payload', details: error.details }).code(400);
    }

    await testConnection();

    const { name, email, username, password } = request.payload;

    const existingUser = await getUserByEmail(email);
    if (existingUser) {
      return h.response({ message: 'Email sudah terdaftar' }).code(400);
    }

    // Simpan data pengguna ke database (tanpa hashing password)
    const userId = await createUser({ name, email, username, password });
    return h.response({ message: 'Registrasi berhasil', userId }).code(201);

  } catch (error) {
    console.error('Error in register:', error);
    return h.response({ message: 'Terjadi kesalahan server' }).code(500);
  }
};

const login = async (request, h) => {
  try {
    const schema = Joi.object({
      email: Joi.string().email().required(),
      password: Joi.string().required(),
    });

    const { error } = schema.validate(request.payload);
    if (error) {
      return h.response({ message: 'Invalid request payload', details: error.details }).code(400);
    }

    await testConnection();

    const { email, password } = request.payload;

    // Check credentials using the checkCredentials function
    const user = await checkCredentials(email, password);

    if (user) {
      // Jika kredensial valid, buat token JWT
      const token = generateJWTToken(user.id);

      return h.response({ message: 'Login successful', userId: user.id, token }).code(200);
    } else {
      return h.response({ message: 'Invalid email or password' }).code(401);
    }
  } catch (error) {
    console.error('Error in login:', error);
    return h.response({ message: 'Terjadi kesalahan server' }).code(500);
  }
};

module.exports = { register, login };
