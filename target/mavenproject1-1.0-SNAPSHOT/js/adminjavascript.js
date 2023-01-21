/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function adminlogin() {


    let myForm = document.getElementById('loginForm');
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
            //document.getElementById("error").innerHTML = "Succeeded Log in";

            window.location.replace('adminwelcomepage.html');

        } else if (xhr.status !== 200) {

            alert("lathos");
        }
    };

    xhr.open('POST', 'Login_Admin');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
}
function getUsers() {
    var xhr = new XMLHttpRequest();
    alert("TI ua ginei");
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            var count = Object.keys(data).length;
            //  alert(count);
            var table = document.createElement("table");

// Create table headers
var header = table.createTHead();
var row = header.insertRow(0);
var cell1 = row.insertCell(0);
var cell2 = row.insertCell(1);
var cell3 = row.insertCell(2);
cell1.innerHTML = "username";
cell2.innerHTML = "firstname";
cell3.innerHTML = "lastname";

// Add data to table
var body = table.createTBody();
for (var i = 0; i < data.length; i++) {
  var row = body.insertRow(i);
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);
  cell1.innerHTML = data[i].username;
  cell2.innerHTML = data[i].firstname;
  cell3.innerHTML = data[i].lastname;
}

// Add table to the HTML document
document.body.appendChild(table);

            let element = document.getElementById("deleteuser");
            let hidden = element.getAttribute("hidden");
            alert(hidden);
            if (hidden) {
                alert("ti einai ");
                element.removeAttribute("hidden");
            } else {
                alert("malakia");

                element.setAttribute("hidden", "hidden");
            }




        } else if (xhr.status !== 200) {
        }
    };
    xhr.open('GET', 'GetUsers');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send();

}
function deleteUser() {
    alert("MPes");
    let myForm = document.getElementById('deleteform');
    let formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData = JSON.stringify(data);
    alert(jsonData);

    var xhr = new XMLHttpRequest();



    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            alert("i diagrafi egine");

        } else if (xhr.status !== 200) {
            alert("eisai malakas");


        }
    };
    xhr.open('POST', 'DeleteUser');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(jsonData);
}