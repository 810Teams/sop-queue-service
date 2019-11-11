const axios = require("axios");
const url = "http://localhost:8080/verify";
const User = require("../models/user");

const auth = async (req, res, next) => {
  try {
    const response = await axios.get(url, {
      headers: { Authorization: req.header("Authorization") }
    });
    const { data } = response;
    const user = await User.findOne({ username: data.username });
    if (!user) {
      throw new Error({ error: "Please authenticate" });
    }
    req.user = user;
    req.token = req.header("Authorization");
    next();
  } catch (e) {
    res.status(401).send({ error: "Please authenticate" });
  }

  next();
};

module.exports = auth;
