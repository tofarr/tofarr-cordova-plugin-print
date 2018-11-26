/* global cordova, module */

module.exports = {
  doPrint: function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "TofarrCordovaPrint", "print", []);
  }
};
