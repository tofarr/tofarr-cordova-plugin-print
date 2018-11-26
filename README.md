# Tofarr's Cordova Android Print Plugin

Simple plugin that allows printing webviews on android

Overrides window.print on android platform

## Using

Create a new Cordova Project

    $ cordova create hello com.example.helloapp Hello

Install the plugin

    $ cd hello
    $ cordova plugin add https://github.com/tofarr/tofarr-cordova-plugin-print.git


Edit `www/js/index.js` and add the following code inside `onDeviceReady`

```js
    var success = function(message) {
        alert(message);
    }

    var failure = function() {
        alert("Error calling Hello Plugin");
    }

    hello.greet("World", success, failure);
```

Install Android platform

    cordova platform add android

Run the code

    cordova run

## More Info

For more information on setting up Cordova see [the documentation](http://cordova.apache.org/docs/en/latest/guide/cli/index.html)

For more info on plugins see the [Plugin Development Guide](http://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)
