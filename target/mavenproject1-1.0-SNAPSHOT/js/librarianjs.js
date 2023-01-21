/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function showform(){
    
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