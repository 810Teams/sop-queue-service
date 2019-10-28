const express = require('express')
const app = express()
const port = process.env.PORT | 8080
require('./database/db')
const shopRouter = require('./router/shop')

// Router
app.use(express.json());
app.use(shopRouter)

// Create server
app.listen(port, ()=>{
    console.log("Start on port " + port)
})