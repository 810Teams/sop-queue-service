const express = require("express");
const router = express.Router();
const Shop = require("../model/shop");
const mongoose = require("mongoose");
const axios = require("axios");

const url = "http://127.0.0.1:8081";
// Document
router.get("/", (req, res) => {
  res.send({ document: "https://github.com/810Teams/sop-reservation-service" });
});

// Create shop
router.post("/shops", async (req, res) => {
  try {
    const shop = new Shop({
      shopname: req.body.shopname,
      description: req.body.description,
      tel: req.body.tel,
      address: req.body.address
    });
    const response = await axios.post(url + "/products", {
      owner: shop._id,
      items: req.body.items
    });
    const { data } = response;

    if (!shop) {
      return res.status(400).send();
    }

    await shop.save();
    res.send({ shop, items: data.items });
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
});

// Get All shops
router.get("/shops", async (req, res) => {
  try {
    const shops = await Shop.find({});
    if (shops.length == 0) {
      return res.status(404).send();
    }
    res.send(shops);
  } catch (error) {
    console.log(error);
    res.status(500).send();
  }
});

// Get shop
router.get("/shops/:id", async (req, res) => {
  try {
    const shop = await Shop.findById(req.params.id);
    
    if (!shop) {
      return res.status(404).send();
    }
    
    const response = await axios.get(url + "/products/" + shop._id);
    const { data } = response;

    res.send({ shop, items: data.items });
  } catch (error) {
    console.log(error)
    res.status(500).send({ error });
  }
});

router.patch("/shops/:id", async (req, res) => {
  const updates = Object.keys(req.body);
  const allowedUpdates = [
    "shopname",
    "description",
    "tel",
    "address",
    "rating"
  ];
  const isValidOperation = updates.every(update =>
    allowedUpdates.includes(update)
  );
  if (!isValidOperation) {
    return res.status(400).send({ error: "Invalid updates" });
  }
  try {
    const shop = await Shop.findById(req.params.id);
    if (!shop) {
      return res.status(404).send();
    }
    updates.forEach(update => {
      if (update === "rating") {
        if (shop["rating"] === "No rating yet!") {
          shop["rating"] = req.body["rating"];
        } else {
          shop["rating"] =
            (parseFloat(shop["rating"]) + parseFloat(req.body["rating"])) / 2;
        }
      } else {
        shop[update] = req.body[update];
      }
    });
    await shop.save();
    res.send(shop);
  } catch (error) {
    res.status(500).send({ error });
  }
});

// Delete shop
router.delete("/shops/:id", async (req, res) => {
  try {
    const shop = await Shop.findByIdAndDelete(req.params.id);
    if (!shop) {
      return res.status(404).send();
    }
    res.send(shop);
  } catch (error) {
    res.status(500).send({ error });
  }
});

module.exports = router;
