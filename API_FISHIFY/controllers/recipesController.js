const recipesData = require('../data/recipes');

// Fungsi untuk mendapatkan resep berdasarkan ID
const getRecipeById = (request, h) => {
  try {
    const recipeId = parseInt(request.params.id);
    const recipe = recipesData.find((r) => r.id === recipeId);

    if (!recipe) {
      return h.response({ message: 'Recipe not found' }).code(404);
    }

    return h.response(recipe).code(200);
  } catch (error) {
    console.error(error);
    return h.response({ message: 'Internal Server Error' }).code(500);
  }
};

// Fungsi untuk mendapatkan resep berdasarkan nama
const getRecipeByName = (request, h) => {
  try {
    const recipeName = request.params.name.toLowerCase();
    const recipe = recipesData.find((r) => r.name.toLowerCase() === recipeName);

    if (!recipe) {
      return h.response({ message: 'Recipe not found' }).code(404);
    }

    return h.response(recipe).code(200);
  } catch (error) {
    console.error(error);
    return h.response({ message: 'Internal Server Error' }).code(500);
  }
};

module.exports = { getRecipeById, getRecipeByName };
