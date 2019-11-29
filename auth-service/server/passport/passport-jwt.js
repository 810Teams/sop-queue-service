const JwtStrategy = require("passport-jwt").Strategy;
const ExtractJwt = require("passport-jwt").ExtractJwt;
const passport = require("passport");
const User = require("../models/user");
const { secret } = require("../config/config");

module.exports = passport => {

  const jwtOptions = {
    jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
    secretOrKey: secret
  };


  passport.use(
    new JwtStrategy(jwtOptions, async (payload, done) => {
      const user = await User.findOne({
        username: payload.username,
      });
      if (!user) {
        return done(null, false);
      }
      return done(null, user);
    })
  );
};
