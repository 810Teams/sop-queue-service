const mongoose = require("mongoose");
url_prod = "mongodb+srv://patrick:rzx3396c@productcluster-gubrn.gcp.mongodb.net/shop-service?retryWrites=true&w=majority"
url_dev = "mongodb://127.0.0.1:27017/shop-service"
mongoose
  .connect(url_prod, {
    useNewUrlParser: true,
    useCreateIndex: true,
    useFindAndModify: false,
    useUnifiedTopology: true
  })
  .catch(e => {
    console.log("Fail to connect database");
  });
