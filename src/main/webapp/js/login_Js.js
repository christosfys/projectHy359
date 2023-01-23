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


            const jsonArray = JSON.parse(xhr.responseText);
           var table = document.createElement("table");
table.setAttribute("id", "json-table");
var thead = document.createElement("thead");
var tr = document.createElement("tr");

for(var key in jsonArray[0]) {
    var th = document.createElement("th");
    th.innerHTML = key;
    tr.appendChild(th);
}

thead.appendChild(tr);
table.appendChild(thead);

// Create table body
var tbody = document.createElement("tbody");

for(var i = 0; i < jsonArray.length; i++) {
    var tr = document.createElement("tr");

    for(var key in jsonArray[i]) {
        var td = document.createElement("td");
        td.innerHTML = jsonArray[i][key];
        tr.appendChild(td);
    }

    tbody.appendChild(tr);
}

table.appendChild(tbody);

// Append table to the body
document.body.appendChild(table);


//var jsonArray = [{"isbn":"9781606801482","title":"She: A History of Adventure","authors":"H. Rider Haggard","genre":"Adventure","url":"https://www.abebooks.com/products/isbn/9781606801482?cm_sp=bdp-_-ISBN13-_-PLP","photo":"https://pictures.abebooks.com/isbn/9781606801482-us.jpg","pages":334,"publicationyear":1887},{"isbn":"9780064471046","title":"The Lion, the Witch and the Wardrobe","authors":"C. S. Lewis","genre":"Fantasy","url":"https://www.abebooks.com/9780064471046/Lion-Witch-Wardrobe-Lewis-0064471047





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
                    //alert("LOgin succesfull");
            //const responseData = JSON.parse(xhr.responseText);
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
            alert(count);
           
             document.getElementById("ajaxContent").innerHTML = "";
             for (var i = 0; i < count; i++) {
                
               var radioButton = document.createElement("input");
                radioButton.type = "radio";
                radioButton.id="choice";
                radioButton.name="choice";
                radioButton.value=responseData[i].title;
                document.body.appendChild(radioButton);
               }
               var button=document.createElement("button");
               button.innerHTML = "Click me";
               button.innerHTML = "Click me";
               button.onclick=function() {
                getvalue();
        };
                document.body.appendChild(button);

        } else if (xhr.status !== 200) {

        }
    };
    xhr.open('GET', 'SeeBorrowings');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();


}
function getvalue(){
    
    alert("mpika");
  var text= document.querySelector('input[name="choice"]:checked').value;
   
   alert(text);
   }