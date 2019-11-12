const mongoose = require("mongoose");
const bcrypt = require("bcryptjs");
const axios = require("axios");
const url = "http://localhost:8080/logoutAll";
const userSchema = new mongoose.Schema(
  {
    username: {
      type: String,
      unique: true,
      required: true,
      trim: true,
      lowercase: true
    },
    password: {
      type: String,
      required: true,
      trim: true,
      minlength: 7,
      validate(value) {
        if (value.toLowerCase().includes("password")) {
          throw new Error("Password cannot contain password");
        }
      }
    },
    firstname: {
      type: String,
      trim: true,
      required: true
    },
    lastname: {
      type: String,
      trim: true,
      required: true
    },
    role: {
      type: String,
      enum: ["customer", "shop"],
      required: true
    },
    tokens: [
      {
        token: {
          type: String,
          required: true
        }
      }
    ]
  },
  {
    timestamps: true,
    strict: true
  }
);

// Hide data (Override user.toJSON()) private data
userSchema.methods.toJSON = function() {
  const user = this;
  const userObject = user.toObject();
  delete userObject.password;
  delete userObject.tokens;

  return userObject;
};

userSchema.methods.logout = async function(token) {
  console.log(token);
  const response = await axios.get(url, { headers: { Authorization: token } });
  const { data } = response;
};

// Hash the plain text password before save
userSchema.pre("save", async function(next) {
  const user = this;
  if (user.isModified("password")) {
    user.password = await bcrypt.hash(user.password, 8);
  }
  next();
});

const User = mongoose.model("User", userSchema);

module.exports = User;
