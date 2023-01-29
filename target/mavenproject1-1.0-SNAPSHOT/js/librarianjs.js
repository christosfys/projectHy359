/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function showform() {
    alert("info");
    let element = document.getElementById("info");
    let hidden = element.getAttribute("hidden");

    if (hidden) {
        element.removeAttribute("hidden");
    } else {
        element.setAttribute("hidden", "hidden");
    }

}
function changeavalbilty() {
    // alert("patietai to gamidi");

    let myForm = document.getElementById('avaliabilty');

    let formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData = JSON.stringify(data);
    alert(jsonData);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {



        } else if (xhr.status !== 200) {


        }
    };

    xhr.open('POST', 'ChangeAvaliability');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
}


function getActiveBorrowings() {
    alert("patietai to gamidi");

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {


            const jsonArray = JSON.parse(xhr.responseText);
            console.log(jsonArray);
            const pinakas = JSON.parse(xhr.responseText);
            var table = document.createElement("table");
            table.setAttribute("id", "json-table");
            var thead = document.createElement("thead");
            var tr = document.createElement("tr");

            for (var key in jsonArray[0]) {
                var th = document.createElement("th");
                th.innerHTML = key;
                tr.appendChild(th);
            }

            thead.appendChild(tr);
            table.appendChild(thead);

            var tbody = document.createElement("tbody");

            for (var i = 0; i < jsonArray.length; i++) {
                var tr = document.createElement("tr");

                for (var key in jsonArray[i]) {
                    var td = document.createElement("td");
                    td.innerHTML = jsonArray[i][key];
                    tr.appendChild(td);
                }

                tbody.appendChild(tr);
            }

            table.appendChild(tbody);

            document.body.appendChild(table);
            var jsPDF = require('jspdf');

// Create a new PDF document
            var doc = new jsPDF();

// Get the JSON array
//var jsonArray = [{'name': 'John', 'age': 30}, {'name': 'Jane', 'age': 25}];

// Create a table to hold the data from the JSON array
            var table = "<table><thead><tr><th></th><th></th></tr></thead><tbody>";

// Loop through the JSON array and add each row to the table
            jsonArray.forEach(function (item) {
                table += "<tr><td>" + item.name + "</td><td>" + item.lastname + "</td></tr>";
            });

            table += "</tbody></table>";

// Add the table to the PDF
            doc.fromHTML(table, 20, 20);

// Save the PDF
            doc.save('response.pdf');







        } else if (xhr.status !== 200) {


        }
    };

    xhr.open('GET', 'SeeActiveBorrowing');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
}
;
function showRegistrationForm() {


    let element = document.getElementById("ajaxContent");
    let hidden = element.getAttribute("hidden");

    if (hidden) {
        element.removeAttribute("hidden");
    } else {
        element.setAttribute("hidden", "hidden");
    }
}


function createbook() {

    let myForm = document.getElementById('myForm');

    let formData = new FormData(myForm);

    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData = JSON.stringify(data);
    alert(jsonData);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {




        } else if (xhr.status !== 200) {


        }
    };


    xhr.open('Post', 'CreateBook');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);

}


function pareTarequests() {
    //     alert("patietai to gamidi");

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert(xhr.responseText);

            const jsonArray = JSON.parse(xhr.responseText);
            var table = document.createElement("table");
            table.setAttribute("id", "json-table");
            var thead = document.createElement("thead");
            var tr = document.createElement("tr");

            for (var key in jsonArray[0]) {
                var th = document.createElement("th");
                th.innerHTML = key;
                tr.appendChild(th);
            }

            thead.appendChild(tr);
            table.appendChild(thead);

            var tbody = document.createElement("tbody");

            for (var i = 0; i < jsonArray.length; i++) {
                var tr = document.createElement("tr");

                for (var key in jsonArray[i]) {
                    var td = document.createElement("td");
                    td.innerHTML = jsonArray[i][key];

                    tr.appendChild(td);
                }

                var radioButton = document.createElement("input");
                radioButton.type = "radio";
                radioButton.id = "choice";
                radioButton.name = "borrowing_id";
                radioButton.value = jsonArray[i].borrowing_id;
                tr.appendChild(radioButton);
                tbody.appendChild(tr);

            }

            table.appendChild(tbody);
            document.body.appendChild(table);
            var button = document.createElement("button");
            button.innerHTML = "Accept";

            button.name = "borrowing_id";
            button.onclick = function () {
                accept_request();
            };

            var returnbutton = document.createElement("button");
            returnbutton.innerHTML = "Confirm the Borrowing";

            returnbutton.name = "borrowing_id";

            returnbutton.onclick = function () {
                var form2 = {};
                form2.borrowing_id = document.querySelector('input[name="borrowing_id"]:checked').value;
                var check = checkStatus(form2.borrowing_id, jsonArray, "returned");
                if (check) {
                    returntoLibrary(form2.borrowing_id);

                } else {
                    alert("The books has'nt returned");
                }

            };
            document.body.appendChild(returnbutton);

            document.body.appendChild(button);


        } else if (xhr.status !== 200) {


        }
    };

    xhr.open('GET', 'PareTarequest');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();





}

function checkStatus(isbn, jsonArray, idiotita) {
    for (let i = 0; i < jsonArray.length; i++) {
        if (jsonArray[i].borrowing_id === isbn) {
            if (jsonArray[i].status === idiotita) {
                return true;
            } else {
                return false;
            }
        }
    }
    return false;
}




function accept_request() {

    var form2 = {};
    form2.borrowing_id = document.querySelector('input[name="borrowing_id"]:checked').value;


    var jsonData = JSON.stringify(form2);

    alert(jsonData);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {




        } else if (xhr.status !== 200) {


        }
    };


    xhr.open('Post', 'Accept_borrowing');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);







}
function returntoLibrary(book) {
    var data = {};
    data.borrowing_id = book;


    var jsonData = JSON.stringify(data);

    alert(jsonData);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {




        } else if (xhr.status !== 200) {


        }
    };


    xhr.open('Post', 'ReturnTheBook');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);

}