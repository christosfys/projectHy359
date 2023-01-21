var lat = 0;
var lon = 0;
function myFunction() {
  var x = document.getElementById("password");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
function createTableFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
    return html;

}

function matchPass() {
  var x = document.getElementById("password");
  var y = document.getElementById("pass2");
  var text = document.getElementById("message");
  if (x.value != "" && y.value != "") {
    if (x.value == y.value) {
      document.getElementById("message").style.color = "green";
      document.getElementById("message").innerHTML = "matching";
      text.style.display = "block";
    } else {
      document.getElementById("message").style.color = "red";
      document.getElementById("message").style.background.color = "white";
      document.getElementById("message").innerHTML = "not matching";
      //document.getElementById("submit").disabled=true;
      text.style.display = "block";
    }
  }
}
function seeDig() {
  let numdigits = 0;
  let text = document.getElementById("password").value;

  for (var i = 0; i < text.length; i++) {
    if (!isNaN(text.charAt(i))) {
      numdigits += 1;
    }
  }
  if (text.length / 2 <= numdigits) {
    return false;
  } else {
    return true;
  }
}

function seeStr() {
 
  if (seeDig() === false || checkNames() === false) {
    document.getElementById("strength").innerHTML = "Weak code";
    document.getElementById("strength").style.color = "red";
   // document.getElementById("submit").disabled = true;

  } else if (checkDiff()) {
    document.getElementById("strength").innerHTML = "Strong code";
    document.getElementById("strength").style.color = "green";
   // document.getElementById("submit").disabled = false;
  } else {
    document.getElementById("strength").innerHTML = "Medium Code";
    document.getElementById("strength").style.color = "blue";
  //
  //    document.getElementById("submit").disabled = false;
  }

}

function checkDuplicate() {
  let text = document.getElementById("password").value;
  var counter = 0;

  for (var i = 0; i < text.length; i++) {
    var s = text.charAt(i);

    for (var j = 0; j < text.length; j++) {
      if (text.charAt(j) == s) {
        counter += 1;
      }
    }

    if (text.length / 2 <= counter) {
      return false;
    } else {
      counter = 0;
    }
  }
  return true;
}
function checkNames() {
  let text = document.getElementById("password").value;
  if (text.includes("uoc") || text.includes("helmepa") || text.includes("tuc")) {
              // alert("ofi");
    return false;
  };

}
function checkDiff() {
  let text = document.getElementById("password").value;
  var counter = 0;
  var lowercase = false;
  var uppercase = false;
  var special_characters = "!`@#$%^&*()+=-[]\\\';,./{}|\":<>?~_";


  for (var i = 0; i < text.length; i++) {
    let symbol = text.charAt(i);
    if (symbol = symbol.toUpperCase()) {
      uppercase = true;
    }
    if (symbol = symbol.toLowerCase()) {
      lowercase = true;
    }
    if (special_characters.indexOf(text.charAt(i)) != -1) {
      counter++;
    }


  }
  if (lowercase && uppercase && (counter >= 2)) {
    return true;
  }
  else {
    return false;
  }
}
function email_validation() {
  
  if(document.getElementById("admin").checked==false){
      // alert("MPika");
  if (document.getElementById("admin").checked == false) {
    var mail = document.getElementById("email").value;
    let type_student = document.getElementById("university").value;
    switch (type_student) {
      case "UOC":
        if (!mail.endsWith("uoc.gr")) {


          document.getElementById("mail_valid").innerHTML = "The email must end with uoc.gr";
          document.getElementById("mail_valid").style.color = "red";
         // document.getElementById("submit").disabled = true;


        } else {
         // document.getElementById("submit").disabled = false;

          document.getElementById("mail_valid").innerHTML = "";


        }
        break;
      case "HELMEPA":
        if (!mail.endsWith("helmepa.gr")) {
          document.getElementById("mail_valid").innerHTML = "The email must end with helmepa.gr";
          document.getElementById("mail_valid").style.color = "red";
         // document.getElementById("submit").disabled = true;

          break;
        } else {
       //   document.getElementById("submit").disabled = false;

          document.getElementById("mail_valid").innerHTML = "";
        }

      case "TUC":
        if (!mail.endsWith("tuc.gr")) {
          document.getElementById("mail_valid").innerHTML = "The email must end with tuc.gr";
          document.getElementById("mail_valid").style.color = "red";
       //   document.getElementById("submit").disabled = true;


          break;
        } else {
     //     document.getElementById("submit").disabled = false;

          document.getElementById("mail_valid").innerHTML = "";


        }
    }
  }
}

}














function checkCheck() {
  var isCheck = document.getElementById("check22").checked;
  //alert("patietai to gamidi ");

  if (isCheck === false) {
    document.getElementById("error").innerHTML = "You must agree with the Conditions";
  }

}



function checkDates() {
  var start = document.getElementById("student_id_from_date").value;
  //alert(start);
  var end = document.getElementById("student_id_to_date").value;
  var start_date = start.charAt(0) + start.charAt(1) + start.charAt(2) + start.charAt(3);
  var end_date = end.charAt(0) + end.charAt(1) + end.charAt(2) + end.charAt(3);
  var result = Number(start_date);
  var result2 = Number(end_date);

  var end_date = end.charAt(0) + end.charAt(1) + end.charAt(2) + end.charAt(3);

  if (result > result2) {

    document.getElementById("problem_date").innerHTML = "The start date is bigger than end date<br>";

  } else if (result == result2) {
    start_date = start.charAt(5) + start.charAt(6);
    end_date = end.charAt(5) + end.charAt(6);
    result = Number(start_date);
    result2 = Number(end_date);

    if (result > result2) {
      document.getElementById("problem_date").innerHTML = "The start date is bigger than end date<br>";


    } else if (result == result2) {
      start_date = start.charAt(7) + start.charAt(8);
      end_date = end.charAt(7) + end.charAt(8);
      result = Number(start_date);
      result2 = Number(end_date);
      if (result > result2) {
        document.getElementById("problem_date").innerHTML = "The start date is bigger than end date<br>";

      }




    } else {

      document.getElementById("problem_date").innerHTML = "";

    }



  } else {
    document.getElementById("problem_date").innerHTML = "";



  }


  var type = document.getElementById("student_type").value;
  switch (type) {
    case "BSc":
      start_date = start.charAt(0) + start.charAt(1) + start.charAt(2) + start.charAt(3);
      end_date = end.charAt(0) + end.charAt(1) + end.charAt(2) + end.charAt(3);
      result = Number(start_date);
      result2 = Number(end_date);
      if ((result2 - result) > 6) {
        document.getElementById("problem_date").innerHTML += "The time of Academic Id must smaller or equual with 6<br>";
       // document.getElementById("submit").disabled = true;





      } else {
       // document.getElementById("submit").disabled = false;

      }
      break;


    case "PhD": {
      start_date = start.charAt(0) + start.charAt(1) + start.charAt(2) + start.charAt(3);
      end_date = end.charAt(0) + end.charAt(1) + end.charAt(2) + end.charAt(3);
      result = Number(start_date);
      result2 = Number(end_date);
      if ((result2 - result) > 2) {
        document.getElementById("problem_date").innerHTML += "The time of Academic Id must smaller or equal with 2<br>";
      //  document.getElementById("submit").disabled = true;




      } else {
      //  document.getElementById("submit").disabled = false;

      }

    }


    case "PhD": {
      start_date = start.charAt(0) + start.charAt(1) + start.charAt(2) + start.charAt(3);
      end_date = end.charAt(0) + end.charAt(1) + end.charAt(2) + end.charAt(3);
      result = Number(start_date);
      result2 = Number(end_date);
      if ((result2 - result) > 5) {
        document.getElementById("problem_date").innerHTML += "The time of Academic Id must smaller or equal with 5<br>";
       // document.getElementById("submit").disabled = true;




      } else {
       // document.getElementById("submit").disabled = false;

      }

    }

  }


}
function allagi() {
  var admin = document.getElementById("admin").checked;


  if (admin === true) {
    document.getElementById("address").innerHTML = "Enter Library";
    document.getElementById("libraryinfo").style.display = "block";
    document.getElementById("libraryinfo").required = true;
    document.getElementById("libraryname").style.display="block";
    document.getElementById("libraryname").required=true;

    document.getElementById("student_type").style.display = "none";
    document.getElementById("student_type").removeAttribute('required');
    document.getElementById("start_label").style.display = "none";
    document.getElementById("start_label").removeAttribute('required');
    document.getElementById("university").style.display = "none";
    document.getElementById("university").removeAttribute('required');
    document.getElementById("student_id_to_date").style.display = "none";
    document.getElementById("student_id_to_date").removeAttribute('required');
    document.getElementById("end_label").style.display = "none";
    document.getElementById("end_label").removeAttribute('required');
    document.getElementById("student_id_from_date").style.display = "none";
    document.getElementById("student_id_from_date").removeAttribute('required');
    document.getElementById("student_id").style.display = "none";
    document.getElementById("student_id").removeAttribute('required');
    document.getElementById("university").style.display = "none";
    document.getElementById("university").removeAttribute('required');
    document.getElementById("uni").innerHTML="";
    alert("nothing");
    document.getElementById("dep").style.display = "none";
    document.getElementById("dep").removeAttribute('required');


  } else {
    document.getElementById("address").innerHTML = "Enter Address";

    document.getElementById("university").style.display = "block";
    document.getElementById("university").required = true;
    document.getElementById("libraryname").style.display="none";
    document.getElementById("libraryname").removeAttribute('required');


    document.getElementById("libraryinfo").style.display = "none";
    document.getElementById("libraryinfo").removeAttribute('required');

    document.getElementById("student_type").style.display = "block";
    document.getElementById("student_type").required = true;
    document.getElementById("start_label").style.display = "block";
    document.getElementById("start_label").required = true;


    document.getElementById("end_label").style.display = "block";
    document.getElementById("end_label").required = true;


    document.getElementById("student_id_to_date").style.display = "block";
    document.getElementById("student_id_to_date").required = true;

    document.getElementById("student_id_from_date").style.display = "block";
    document.getElementById("student_id_from_date").required = true;

    document.getElementById("student_id").style.display = "block";
    document.getElementById("student_id").required = true;
    document.getElementById("uni").innerHTML="Choose your university";


    document.getElementById("university").style.display = "block";
    document.getElementById("university").required = true;
    document.getElementById("dep").style.display = "block";
    document.getElementById("dep").required = true;





  }

}

function RegisterPOST() {
//alert("Ola kala");
let myForm = document.getElementById('myForm');
let formData = new FormData(myForm);
const data = {};
formData.forEach((value, key) => (data[key] = value));
var jsonData=JSON.stringify(data);
//alert(jsonData);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // alert("LOgin succesfull");
         //   const responseData = JSON.parse(xhr.responseText);
            $('#ajaxContent').html("Successful Registration. Now please log in!");
        
        } else if (xhr.status !== 200) {
            
           document.getElementById("ajaxContent").innerHTML ="Request failed. Returned status of " + xhr.status + " "+xhr.responseText;
        }
    };
   
    xhr.open('POST', 'MyServlet');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
    alert(jsonData);
}












function getLocation() {
  var msg = document.getElementById('mapdiv');
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition, showError);
  }
  function showError(error) {
    switch (error.code) {
      case error.PERMISSION_DENIED:
        x.innerHTML = "User denied the request for Geolocation."
        break;
      case error.POSITION_UNAVAILABLE:
        x.innerHTML = "Location information is unavailable."
        break;
      case error.TIMEOUT:
        x.innerHTML = "The request to get user location timed out."
        break;
      case error.UNKNOWN_ERROR:
        x.innerHTML = "An unknown error occurred."
        break;
    }
  }
}

function showPosition(position) {
  const data = null;
  lat = position.coords.latitude;
   lon = position.coords.longitude;
  
  var msg = "lat=" + lat + "&lon=" + lon;
  const xhr = new XMLHttpRequest();
  xhr.withCredentials = false;
  xhr.addEventListener("readystatechange", function () {
    if (this.readyState === this.DONE) {
      const obj = JSON.parse(this.responseText);
      var text = obj.address;
      const city = text.road;
      document.getElementById("address").value = city;
      var count = text.country;
      document.getElementById("country").value = count;
      var ct = text.city;
      document.getElementById("city").value = ct;



    }
  });
  xhr.open(
    "GET",
    "https://forward-reverse-geocoding.p.rapidapi.com/v1/reverse?" +
    msg +
    "&accept-language=en&polygon_threshold=0.0"
  );
  xhr.setRequestHeader("x-rapidapi-host", "forward-reverse-geocoding.p.rapidapi.com");
  xhr.setRequestHeader("x-rapidapi-key", "3ab58c39e9msh0e84dbb8288e121p1a9598jsn2662304cd7f4");
  xhr.send();

}

function openstreetmaps() {
  const data = null;

  const xhr = new XMLHttpRequest();
  xhr.withCredentials = false;
  var coun = document.getElementById("country").value;
  var city = document.getElementById("city").value;
  var adress = document.getElementById("address").value;
  var name = adress + " " + " " + city + " " + coun;

  xhr.addEventListener("readystatechange", function () {
    if (this.readyState === this.DONE) {
      if (this.responseText == "{}") {
        document.getElementById("Nofound").innerHTML = "No found place";
      } else {
        const obj = JSON.parse(xhr.responseText);
        var text = obj[0].display_name;

        if (text.includes("Region of Crete")) {
          document.getElementById("Nofound").innerHTML = "";

          console.log(text);
          const myJSON = JSON.stringify(obj);
          console.log(myJSON);
          lat = obj[0].lat;
          lon = obj[0].lon;
          document.getElementById("count").disabled = false;

        } else {
          document.getElementById("Nofound").innerHTML = "Only Crete";
        }
      }
    }
  });

  xhr.open(
    "GET",
    "https://forward-reverse-geocoding.p.rapidapi.com/v1/search?q=" +
    name +
    "&accept-language=en&polygon_threshold=0.0"
  );
  xhr.setRequestHeader(
    "x-rapidapi-host",
    "forward-reverse-geocoding.p.rapidapi.com"
  );
  xhr.setRequestHeader(
    "x-rapidapi-key",
    "3ab58c39e9msh0e84dbb8288e121p1a9598jsn2662304cd7f4"
  );
  xhr.send(data);
}

function map(lat, lon) {
  
  map = new OpenLayers.Map("Map");
  var mapnik = new OpenLayers.Layer.OSM();
  map.addLayer(mapnik);
  var lonLat = new OpenLayers.LonLat(lon, lat).transform(
    new OpenLayers.Projection("EPSG:4326"),
    new OpenLayers.Projection("EPSG:900913")
  );
  var zoom = 16;
  var markers = new OpenLayers.Layer.Markers("Markers");
  map.addLayer(markers);
  markers.addMarker(new OpenLayers.Marker(lonLat));
  map.setCenter(lonLat, zoom);
}
function getlatlon(){
   
 document.getElementById("lat").value =lat;
 document.getElementById("lon").value =lon;
}