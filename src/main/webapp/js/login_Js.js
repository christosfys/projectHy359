/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function loginPOST() {


    let myForm = document.getElementById('loginForm');
    let formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData = JSON.stringify(data);
    //  alert(jsonData);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // alert("LOgin succesfull");
            //   const responseData = JSON.parse(xhr.responseText);
            document.getElementById("error").innerHTML = "Succeeded Log in";
            window.location.replace('welcome.html');

        } else if (xhr.status !== 200) {

            document.getElementById("error").innerHTML = "Wrong Credetential";
        }
    };

    xhr.open('POST', 'Login');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
}

function logout() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.replace('login.html');

        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'Logout');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}


function getData() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // alert("MPIKe o" +xhr.responseText);
            username = xhr.responseText;
            requestData();
        } else if (xhr.status === 409) {
            window.location.replace('login.html');

            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('GET', 'Login');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();


}



function requestData() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            //const responseData = JSON.parse(xhr.responseText);  
            const responseData = JSON.parse(xhr.responseText);


            //   let result = responseData.includes(\"firstName\":\"John\");
            if (responseData.position === "student") {
                delete responseData.position;

                document.getElementById("ajaxContent").innerHTML = createTableFromJSON(responseData);
            } else {
                window.location.replace('librarian.html');
                delete responseData.position;


            }
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.open('Post', 'GetData');
    xhr.setRequestHeader('Content-type', "application/json");

    xhr.send();


}



function createTableFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        if (value.endsWith('jpg') || value.endsWith("png")) {
            value = "<img height=300 src='" + value + "'/>";
        }
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
    return html;

}

function showUpdate() {
    let element = document.getElementById("update");
    let hidden = element.getAttribute("hidden");

    if (hidden) {
        element.removeAttribute("hidden");
    } else {
        element.setAttribute("hidden", "hidden");
    }
}

function updateData() {
    // alert("mpika");
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    formData.forEach((value, key) => (!data[key] && data[key] !== undefined) && delete data[key]);

    var jsonData = JSON.stringify(data);

    //   alert(jsonData);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        } else if (xhr.status !== 200) {

            document.getElementById("ajaxContent").innerHTML = "Request failed. Returned status of " + xhr.status + " " + xhr.responseText;
        }
    };
    xhr.open('Post', 'UpdateData');
    xhr.setRequestHeader('Content-type', "application/json");
    xhr.send(jsonData);

}

function getBooks() {
    var xhr = new XMLHttpRequest();
    //alert("TI ua ginei");
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {


            const responseData = JSON.parse(xhr.responseText);
            var count = Object.keys(responseData).length;
            console.log(xhr.responseText);
            alert(count);
            document.getElementById("ajaxContent").innerHTML = "";
            
            for (var i = 0; i < count; i++) {
                document.getElementById("ajaxContent").innerHTML += " " + responseData[i].title + "|| " + responseData[i].isbn + " ||" + responseData[i].authors + "||" + responseData[i].title + "<img src='" + responseData[i].photo + "'/>";
                document.getElementById("ajaxContent").innerHTML += "<br>";


            }

        } else if (xhr.status !== 200) {
        }
    };
    xhr.open('POST', 'GetBooks');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();


}

function writereview() {
    var xhr = new XMLHttpRequest();
    //alert("TI ua ginei");
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {


            const responseData = JSON.parse(xhr.responseText);
            var count = Object.keys(responseData).length;
            alert(count);
            document.getElementById("ajaxContent").innerHTML = "";
            for (var i = 0; i < count; i++) {
                document.getElementById("ajaxContent").innerHTML += " " + responseData[i].title + "|| " + responseData[i].isbn + " ||" + responseData[i].authors + "||" + responseData[i].title + "<img src='" + responseData[i].photo + "'/>";
                document.getElementById("ajaxContent").innerHTML += "<br>";


            }

        } else if (xhr.status !== 200) {
        }
    };
    xhr.open('GET', 'Book_success');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();




}



function showrequest() {
    let element = document.getElementById("request");
    let hidden = element.getAttribute("hidden");

    if (hidden) {
        element.removeAttribute("hidden");
    } else {
        element.setAttribute("hidden", "hidden");
    }


}
function requestbook() {

    let myForm = document.getElementById('book');
    let formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData = JSON.stringify(data);
    alert(jsonData);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // alert("LOgin succesfull");
            //   const responseData = JSON.parse(xhr.responseText);
            document.getElementById("error").innerHTML = "Succeeded Log in";
            window.location.replace('welcome.html');

        } else if (xhr.status !== 200) {

            document.getElementById("error").innerHTML = "Wrong Credetential";
        }
    };

    xhr.open('POST', 'Request_Data');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
}

function seeActiveBorrowings(){
    var xhr = new XMLHttpRequest();
    //alert("TI ua ginei");
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

        alert(xhr.responseText);

            const responseData = JSON.parse(xhr.responseText);
            var count = Object.keys(responseData).length;
             document.getElementById("ajaxContent").innerHTML = "";
             
               document.getElementById("ajaxContent").innerHTML = "<input type='radio' id='text' name='s' value="+responseData[0].borrowing_id+">";

        } else if (xhr.status !== 200) {

        }
    };
    xhr.open('GET', 'SeeBorrowings');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();


}