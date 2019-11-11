const jwt = require('jsonwebtoken')
const User = require('../models/user')

const auth = async (req,res,next)=>{
    try {
        const token = req.header('Authorization').replace('Bearer ','')
        const user = req.user
        const _token = user.tokens.filter(userToken=>userToken.token===token)[0].token
        if(!token){
            throw new Error({ error: "Please authenticate" })
        }
        req.user = user
        req.token = token
        next()
    } catch (e) {
        res.status(401).send({ error: "Please authenticate" })
    }
}

module.exports = auth