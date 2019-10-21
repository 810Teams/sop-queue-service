const mongoose = require("mongoose");
const itemSchema = require('../schema/item')

const shopSchema = mongoose.Schema({
  shopname: {
    type: String,
    required: true,
    trim: true
  },
  items: [itemSchema]
});

const Shop = mongoose.model("Shop", shopSchema);

module.exports = Shop;
