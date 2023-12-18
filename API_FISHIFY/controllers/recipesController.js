const recipesData = require('../data/recipes');

const getRecipeById = (request, h) => {
  try {
    const recipeId = request.params.id;

    // Cari resep berdasarkan ID dalam data statis
    const recipe = recipesData.find((recipe) => recipe.id === recipeId);

    if (!recipe) {
      return h.response({ message: 'Resep tidak ditemukan' }).code(404);
    }

    // Mengembalikan data resep
    return h.response(recipe).code(200);
  } catch (error) {
    console.error(error);
    return h.response({ message: 'Terjadi kesalahan server' }).code(500);
  }
};

module.exports = { getRecipeById };
