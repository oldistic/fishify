const { getUser } = require('../controllers/userController');

const routes = [
  {
    method: 'GET',
    path: '/users/{id}',
    handler: getUser,
  },
];

module.exports = routes;
