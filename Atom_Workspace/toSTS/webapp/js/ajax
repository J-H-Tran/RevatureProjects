/*jshint esversion: 6 */
//time interval

// console.log(4);
// setTimeout (() => console.log(5), 500);
//
// setTimeout (function(){
//      console.log(6);
// });

//simple ajax call /request
var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(){
    console.log("response text: "+ xhr.responseText+
            "http status: "+ xhr.status +
            "http status text: "+ xhr.statusText +
            "readyState: "+ xhr.readyState
    );
};
xhr.open("GET", "https://api.myjson.com/bins/eq9je");
xhr.send();

/*
http status codes
100 - information
200 - success
300 - redirection
400 - client error
500 - server error
*/

window.onload = () => {
    let btn = document.getElementById("display");
    btn.addEventListener("click", () => {
        //for every readyState, onReadyStateChange will be executed
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            let rt = document.getElementById("responsetext");
            let hs = document.getElementById("httpstatus");
            let hst = document.getElementById("httpstatustext");
            let rs = document.getElementById("readystate");
            let rh = document.getElementById("responseheaders");
            rt.innerHTML = xhr.responseText;
            hs.innerHTML = xhr.status;
            hst.innerHTML = xhr.statusText;
            rs.innerHTML = xhr.readyState;
            rh.innerHTML = xhr.getAllResponseHeaders();
        };
        xhr.open("get", "https://api.myjson.com/bins/eq9je");
        xhr.send();
    });

    let giftedBtn = document.getElementById("gifted");
    giftedBtn.addEventListener("click", () => {

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if ((xhr.readyState == 4) && (xhr.status == 200)){
                let original = document.getElementById("original");
                original.innerHTML = xhr.responseText;
            }
            xhr.open("get", "https://api.myjson.com/bins/eq9je");
            xhr.send();
        };
    });
};
