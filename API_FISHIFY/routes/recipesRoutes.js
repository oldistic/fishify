const { getRecipes, getRecipeById } = require('../controllers/recipesController');

const recipesRoutes = [
    {
      method: 'GET',
      path: '/recipes/{id}',
      handler: getRecipeById,
    },
    {
      method: 'GET',
      path: '/recipes/name/{name}',
      handler: getRecipeByName,
    },
  ];

module.exports = recipeRoutes;
