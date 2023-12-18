const { getRecipes, getRecipeById } = require('../controllers/recipesController');

const recipeRoutes = [
  {
    method: 'GET',
    path: '/recipes',
    handler: getRecipes,
  },
  {
    method: 'GET',
    path: '/recipes/{id}',
    handler: getRecipeById,
  },
  // Tambah routes lainnya sesuai kebutuhan
];

module.exports = recipeRoutes;
