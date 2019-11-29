const express = require("express");
const router = express.Router();
const User = require('../models/user')
const passport = require('passport')
require('../passport/passport-jwt')(passport)
const auth = require('../middleware/auth')
const requireJWTAuth = passport.authenticate("jwt", { session: false });

// Document
router.get("/", (req, res) => {
  res.send({ document: "https://github.com/810Teams/sop-reservation-service" });
});

// Login
router.post('/login', async (req,res)=>{
  try {
    const user = await User.findByCredentials(
      req.body.username,
      req.body.password
    );
    const token = await user.generateAuthToken();
    res.send({ user, token });
  } catch (e) {
    res.status(400).send({error:"username or password incorrect"});
  }
})

// Verify
router.get('/verify', requireJWTAuth, auth,(req,res)=>{
  try{
    const user = req.user;
    if(!user){
      res.status(400).send()
    }
    res.send(user)
  }catch(e){
    res.status(500).send(e)
  }
})

// Logout
router.get('/logout', requireJWTAuth, auth, async (req,res)=>{
  try {
    req.user.tokens = req.user.tokens.filter(token => {
      return token.token !== req.token;
    });
    await req.user.save();
    res.send({status:true});
  } catch (e) {
    res.status(500).send();
  }
})

// LogoutAll
router.get("/logoutAll", requireJWTAuth, auth, async (req, res) => {
  try {
    req.user.tokens = [];
    await req.user.save();
    res.send({status:true});
  } catch (e) {
    res.status(500).send();
  }
});

module.exports = router