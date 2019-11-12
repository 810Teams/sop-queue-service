const express = require("express");
const app = express();
const port = process.env.PORT | 3000;
require("./database/db");
const productRouter = require("./router/product");
const cors = require("cors");

// const Eureka = require("eureka-js-client").Eureka;

// const eureka = new Eureka({
//   instance: {
//     app: "product-service",
//     instanceId: "product-service",
//     hostName: "localhost",
//     ipAddr: "127.0.0.1",
//     port: {
//       $: 8080,
//       "@enabled": "true"
//     },
//     vipAddress: "product-service",
//     statusPageUrl: "https://vocal-pathway-257309.appspot.com",
//     dataCenterInfo: {
//       "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
//       name: "MyOwn"
//     },
//     registerWithEureka: true,
//     fetchRegistry: true
//   },
//   eureka: {
//     host: "eureka-server-258207.appspot.com",
//     port: 80,
//     servicePath: "/eureka/apps/"
//   }
// });
// eureka.logger.level("debug");
// eureka.start(function(error) {
//   console.log(error || "complete");
// });

// Router
app.use(express.json());
app.use(cors());
app.use(productRouter);

// Create server
app.listen(port, () => {
  console.log("Start on port " + port);
});
