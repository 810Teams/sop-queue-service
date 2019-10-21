const mongoose = require("mongoose");

const itemSchema = mongoose.Schema({
    name: {
      type: String,
      required: true,
      trim: true,
      unique: true
    },
    price: {
      type: Number,
      required: true,
      validate(value){
        if(value < 0){
          throw new Error("Price can't be negative")
        }
      }
    }
});

module.exports = itemSchema;
