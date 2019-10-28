const express = require('express')
const app = express()
const port = process.env.PORT | 8081
require('./database/db')
const productRouter = require('./router/product')

// Router
app.use(express.json());
app.use(productRouter)

// Create server
app.listen(port, ()=>{
    console.log("Start on port " + port)
})