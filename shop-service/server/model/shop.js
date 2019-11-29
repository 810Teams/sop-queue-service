const mongoose = require("mongoose");

const shopSchema = mongoose.Schema(
  {
    shopname: {
      type: String,
      required: true,
      trim: true
    },
    description: {
      type: String,
      required: true
    },
    tel: {
      type: String,
      required: true,
      validate(value) {
        if (value.length > 10) {
          throw new Error("Tel don't use more than 10 numbers");
        }
      }
    },
    owner:{
      type:String,
      trim:true,
      unique:true,
      required:true
    },
    address: {
      type: String,
      required: true
    },
    rating: {
      type: String,
      default: "No rating yet!",
      validate(value) {
        if (parseFloat(value) < 0 || parseFloat(value) > 5) {
          throw new Error("Score must between 0 and 5 !!");
        }
      }
    }
  },
  {
    timestamps: true,
    strict: true
  }
);

const Shop = mongoose.model("Shop", shopSchema);

module.exports = Shop;
