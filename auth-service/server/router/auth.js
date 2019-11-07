const express = require("express");
const router = express.Router();

// Document
router.get("/", (req, res) => {
  res.send({ document: "https://github.com/810Teams/sop-reservation-service" });
});


// Login
router.post('/login', (req,res)=>{

})

// Verify
router.get('/verify', (req,res)=>{

})

// Logout
router.get('/logout', (req,res)=>{

})