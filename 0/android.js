function drawColor(r,g,b){
    
    
   var color= "["+r+","+g+","+b+"]";
         

            //call native method
            window.WebViewJavascriptBridge.callHandler(
                'setColor'
                ,color
                , function(returnData) {
               //    document.write(returnData);
                }
            );
    }
    
    
    //init function
       function   onCreate(){
        //,responseCallback) {
        
        var r=document.getElementById("red").value;
       var g=document.getElementById("green").value;
      var b=document.getElementById("blue").value;
       
         drawColor(r,g,b);
       
    
    
        }
   