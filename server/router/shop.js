const express = require("express");
const router = express.Router();
const Shop = require("../model/shop");
const mongoose = require("mongoose")

// Document
router.get('/', (req,res)=>{
  res.send({document:"https://github.com/810Teams/sop-reservation-service"})
})


// Create shop
router.post("/shops", async (req, res) => {
  try {
    const shop = new Shop(req.body);

    if (!shop) {
      return res.status(400).send();
    }
    await shop.save();
    res.send(shop);
  } catch (e) {
    console.log(e);
    res.status(500).send();
  }
});

// Get All shops
router.get("/shops", async (req, res) => {
  const shops = await Shop.find({});
  if (shops.length == 0) {
    return res.status(404).send();
  }
  res.send(shops);
});

// Get shop
router.get("/shops/:id", async (req, res) => {
  try {
    const shops = await Shop.findById(req.params.id);
    if (!shops) {
      return res.status(404).send();
    }
    res.send(shops);
  } catch (error) {
    res.status(500).send({ error });
  }
});

// Update shop
router.patch("/shops/:id", async (req, res) => {
  const updates = Object.keys(req.body);
  const allowedUpdates = ["shopname"];
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
    console.log(shop);
    updates.forEach(update => {
      shop[update] = req.body[update];
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


module.exports = router
