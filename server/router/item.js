const express = require("express");
const router = express.Router();
const Shop = require("../model/shop");

// Create Item
router.post("/shops/:id/items", async (req, res) => {
  try {
    const shop = await Shop.findById(req.params.id);
    if (!shop) {
      return res.status(404).send();
    }
    if (req.body.length >= 1) {
      let check = false;
      req.body.forEach(item => {
        shop.items.forEach(i => {
          if (i.name != item.name) {
            check = true;
          } else {
            return res.status(400).send({ error: "Name is taken!!" });
          }
        });
        shop.items.push(item);
      });
    } else {
      shop.items.push(req.body);
    }
    await shop.save();
    res.send(shop);
  } catch (error) {
    console.log(error);
    res.status(500).send({ error });
  }
});
// Get all items
router.get("/shops/:id/items", async (req, res) => {
  try {
    const shop = await Shop.findById(req.params.id);
    if (!shop) {
      return res.status(404).send();
    }
    res.send({ items: shop.items });
  } catch (error) {
    res.status(500).send({ error });
  }
});

// Get Items by id
router.get("/shops/:id/items/:item_id", async (req, res) => {
  try {
    const shop = await Shop.findById(req.params.id);
    const item = shop.items.id(req.params.item_id);
    if (!item) {
      return res.status(404).send();
    }
    res.send(item);
  } catch (error) {
    res.status(500).send({ error });
  }
});

// Update Items by id
router.patch("/shops/:id/items/:item_id", async (req, res) => {
  const updates = Object.keys(req.body);
  const allowedUpdates = ["name", "price"];
  const isValidOperation = updates.every(update =>
    allowedUpdates.includes(update)
  );
  if (!isValidOperation) {
    return res.status(400).send();
  }
  try {
    const shop = await Shop.findById(req.params.id);
    const item = shop.items.id(req.params.item_id);
    if (!item) {
      return res.status(404).send();
    }
    updates.forEach(update => {
      item[update] = req.body[update];
    });
    await shop.save();
    res.send(item);
  } catch (error) {
    res.status(500).send({ error });
  }
});

// Delete item
router.delete("/shops/:id/items/:item_id", async (req, res) => {
  try {
    const shop = await Shop.findById(req.params.id);
    if (!shop) {
      return res.status(404).send();
    }
    const item = shop.items.id(req.params.item_id).remove();
    await shop.save();
    res.send(item);
  } catch (error) {
    res.status(500).send({ error });
  }
});

module.exports = router;
