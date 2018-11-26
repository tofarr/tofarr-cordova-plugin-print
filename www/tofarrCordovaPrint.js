/* global cordova, module */

module.exports = {
  doPrint: function (successCallback, errorCallback) {
    var content = document.getElementsByTagName('html')[0].innerHTML;
    cordova.exec(successCallback, errorCallback, "TofarrCordovaPrint", "print", [content]);
  }
};
