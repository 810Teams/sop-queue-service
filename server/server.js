const express = require('express')
const app = express()
const port = process.env.PORT | 3000
require('./database/db')
const shopRouter = require('./router/shop')
const itemRouter = require('./router/item')

// Router
app.use(express.json());
app.use(shopRouter)
app.use(itemRouter)

// Create server
app.listen(port, ()=>{
    console.log("Start on port " + port)
})