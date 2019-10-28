const express = require("express");
const router = express.Router();
const Product = require("../model/product");

// Product
// Create Product
router.post("/products", async (req, res) => {
  try {
    const product = new Product(req.body);
    await product.save();
    res.send(product);
  } catch (error) {
    console.log(error);
    res.status(400).send({ error });
  }
});

// Get all product in the shop
router.get("/products/", async (req, res) => {
    try {
      const product = await Product.find({});
      if (!product) {
        return res.status(404).send();
      }
      res.send(product);
    } catch (error) {
      res.status(500).send({ error });
    }
  });

// Get product by id
router.get("/products/:shop_id", async (req, res) => {
  try {
    const product = await Product.findOne({ owner: req.params.shop_id });
    if (!product) {
      return res.status(404).send();
    }
    res.send(product);
  } catch (error) {
    res.status(500).send({ error });
  }
});



// delete product in the shop
router.delete("/products/:shop_id", async (req, res) => {
  try {
    const product = await Product.deleteOne({ owner: req.params.shop_id });
    if (product.deletedCount === 0) {
      return res.status(404).send();
    }
    res.send({status:true});
  } catch (error) {
    res.status(500).send({ error });
  }
});

// Items
// Get Items
router.get("/products/:shop_id/items/", async (req, res) => {
    try {
      const product = await Product.findOne({ owner: req.params.shop_id });
      if (!product) {
        return res.status(404).send();
      }
      const items = product.items
      res.send({items});
    } catch (error) {
      console.log(error);
      res.status(500).send({ error });
    }
  });

// Get Items by id
router.get("/products/:shop_id/items/:item_id", async (req, res) => {
  try {
    const product = await Product.findOne({ owner: req.params.shop_id });
    const item = product.items.id(req.params.item_id);
    if (!item) {
      return res.status(404).send();
    }
    res.send(item);
  } catch (error) {
    console.log(error);
    res.status(500).send({ error });
  }
});
// Create Item
router.post("/products/:shop_id/items", async (req, res) => {
    try {
      const product = await Product.findOne({ owner: req.params.shop_id });
      if (!product) {
        return res.status(404).send();
      }
      if (req.body.length >= 1) {
        let check = false;
        req.body.forEach(item => {
          product.items.forEach(i => {
            if (i.name != item.name) {
              check = true;
            } else {
              return res.status(400).send({ error: "Name is taken!!" });
            }
          });
          product.items.push(item);
        });
      } else {
        product.items.push(req.body);
      }
      await product.save();
      res.send(product);
    } catch (error) {
      console.log(error);
      res.status(500).send({ error });
    }
  });
// Update Items by id
router.patch("/products/:shop_id/items/:item_id", async (req, res) => {
  const updates = Object.keys(req.body);
  const allowedUpdates = ["name", "price"];
  const isValidOperation = updates.every(update =>
    allowedUpdates.includes(update)
  );
  if (!isValidOperation) {
    return res.status(400).send();
  }
  try {
    const product = await Product.findOne({ owner: req.params.shop_id });
    const item = product.items.id(req.params.item_id);
    if (!item) {
      return res.status(404).send();
    }
    updates.forEach(update => {
      item[update] = req.body[update];
    });
    await product.save();
    res.send(item);
  } catch (error) {
    res.status(500).send({ error });
  }
});

// Delete item
router.delete("/products/:shop_id/items/:item_id", async (req, res) => {
  try {
    const product = await Product.findOne({ owner: req.params.shop_id });
    if (!product) {
      return res.status(404).send();
    }
    const item = product.items.id(req.params.item_id).remove();
    await product.save();
    res.send(item);
  } catch (error) {
    res.status(500).send({ error });
  }
});

module.exports = router;
