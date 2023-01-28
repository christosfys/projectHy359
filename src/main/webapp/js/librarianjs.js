/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function showform(){
    alert("info");
        let element = document.getElementById("info");
    let hidden = element.getAttribute("hidden");

    if (hidden) {
        element.removeAttribute("hidden");
    } else {
        element.setAttribute("hidden", "hidden");
    }
    
}
function changeavalbilty(){
    alert("patietai to gamidi");
    
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


function getActiveBorrowings(){
    alert("patietai to gamidi");
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            
            
            
            
        console.log(xhr.responseText);
        
        
          

        } else if (xhr.status !== 200) {

     
        }
    };

    xhr.open('GET', 'SeeActiveBorrowing');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
};
function showRegistrationForm(){
   
   
       let element = document.getElementById("ajaxContent");
    let hidden = element.getAttribute("hidden");

    if (hidden) {
        element.removeAttribute("hidden");
    } else {
        element.setAttribute("hidden", "hidden");
    }
}
 
 
function createbook(){
    
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
  
  
  function pareTarequests(){
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
            
            button.name="borrowing_id";
              button.onclick = function () {
                accept_request();
            };
            document.body.appendChild(button);


        } else if (xhr.status !== 200) {

     
        }
    };

    xhr.open('GET', 'PareTarequest');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
      
      
  
  
  
  }




function accept_request(){
    
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