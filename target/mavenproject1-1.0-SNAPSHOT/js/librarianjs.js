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
        alert(xhr.responseText);
        
          

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
          alert("patietai to gamidi");
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        alert(xhr.responseText);
        
          

        } else if (xhr.status !== 200) {

     
        }
    };

    xhr.open('GET', 'PareTarequest');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send();
      
      
  
  
  
  }

