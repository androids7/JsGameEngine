
      //   bridge.registerHandler用来注册一个供java层调用的函数
    
    //这里是应用一启动调用的函数
   

    
    /*
   
        function connectWebViewJavascriptBridge(callback) {
            if (window.WebViewJavascriptBridge) {
                callback(WebViewJavascriptBridge)
            } else {
                document.addEventListener(
                    'WebViewJavascriptBridgeReady'
                    , function() {
                        callback(WebViewJavascriptBridge)
                    },
                    false
                );
            }
        }

        connectWebViewJavascriptBridge(function(bridge) {
            bridge.init(function(message, responseCallback) {
                console.log('JS got a message', message);
                var data = {
                    'Javascript Responds': '测试中文!'
                };

                if (responseCallback) {
                alert(data);
                //    console.log('JS responding with', data);
                    responseCallback(data);
                }
            });
/*
            bridge.registerHandler("functionInJs", function(data, responseCallback) {
                document.getElementById("show").innerHTML = ("data from Java: = " + data);
                if (responseCallback) {
                    var responseData = "Javascript Says Right back aka!";
                    responseCallback(responseData);
                }
            });
        })
    */
    
    
    
    
   //注册init函数
    bridge.registerHandler("init", function(data,responseCallback)
    {
    if(!responseCallback){
    document.write("初始化成功");
    responseCallback(data);
    
    
            }
    }
    );
   
    
       document.write("hello");
        