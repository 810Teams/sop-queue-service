const express = require("express");
const app = express();
const port = process.env.PORT | 8080;
require("./database/db");
const authRouter = require("./router/auth");
const cors = require('cors')


// ---Eureka config---
const Eureka = require("eureka-js-client").Eureka;

const eureka = new Eureka({
  instance: {
      app: 'auth-service',
      instanceId: 'auth-service',
      hostName: 'localhost',
      ipAddr: '127.0.0.1',
      port:  {
          '$': 8080,
          '@enabled': 'true',
      },
      vipAddress: 'auth-service',
      statusPageUrl: 'http://localhost:8080',
      dataCenterInfo:  {
          '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
          name: 'MyOwn',
      },
      registerWithEureka: true,
      fetchRegistry: true
  },
  eureka: {
      host: 'eureka-server-258207.appspot.com',
      port: 80,
      servicePath: '/eureka/apps/'
  }
});
eureka.logger.level('debug');
eureka.start(function(error){
  console.log(error || 'complete');
});

// Router
app.use(express.json());
app.use(cors())
app.use(authRouter);
authRouter.use(function(req, res, next) {
  res.header('Content-Type', 'application/json');
})

// Create server
app.listen(port, () => {
  console.log("Start on port " + port);
});