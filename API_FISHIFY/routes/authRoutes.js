// routes/authRoutes.js
const { register } = require('../controllers/authController');
const { login } = require('../controllers/authController');

const authRoutes = [
  {
    method: 'POST',
    path: '/register',
    handler: register,
  },
  {
    method: 'POST',
    path: '/login',
    handler: login,
  },
];

module.exports = authRoutes;
