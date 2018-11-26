/* global cordova, module */

module.exports = {
  doPrint: function (successCallback, errorCallback) {
    alert('tofarrCordovaPrint.js');
    cordova.exec(successCallback, errorCallback, "TofarrCordovaPrint", "print", []);
  }
};
