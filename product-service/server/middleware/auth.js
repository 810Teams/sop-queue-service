const axios = require("axios");
const url = "http://localhost:8080/verify";

const auth = async (req, res, next) => {
  try {
    const response = await axios.get(url, {
      headers: { Authorization: req.header("Authorization") }
    });
    const { data } = response;
    if (!data) {
      throw new Error({ error: "Please authenticate" });
    }
    req.user = data
    req.token = req.header("Authorization");
    next();
  } catch (e) {
    res.status(401).send({ error: "Please authenticate" });
  }
};

module.exports = auth;