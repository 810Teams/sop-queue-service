const mongoose = require("mongoose");
mongoose
  .connect("mongodb+srv://patrick:rzx3396c@productcluster-gubrn.gcp.mongodb.net/product-service?retryWrites=true&w=majority", {
    useNewUrlParser: true,
    useCreateIndex: true,
    useFindAndModify: false,
    useUnifiedTopology: true
  })
  .catch(e => {
    console.log("Fail to connect database");
  });
