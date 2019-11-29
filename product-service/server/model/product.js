const mongoose = require("mongoose");
const itemSchema = require('../schema/item')

const productSchema = mongoose.Schema({
  owner:{
    type:String,
    required: true,
    trim: true,
    unique:true
  },
  items: [itemSchema]
},{
  timestamps: true,
  strict: true
});

const Product = mongoose.model("Product", productSchema);

module.exports = Product;
