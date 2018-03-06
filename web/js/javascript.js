//Zoom images
if (top.location != self.location) {
  top.location = self.location
}
var _gaq = _gaq || [];
_gaq.push(["_setAccount", "UA-18506635-11"]);
_gaq.push(["_setDomainName", ".nl-mus.blogspot.com"]);
_gaq.push(["_trackPageview"]);
(function() {
  var a = document.createElement("script");
  a.type = "text/javascript";
  a.async = true;
  a.src = ("https:" == document.location.protocol ? "https://ssl" : "http://www") + ".google-analytics.com/ga.js";
  var b = document.getElementsByTagName("script")[0];
  b.parentNode.insertBefore(a, b)
})();

// Sự kiện login
var modal = document.getElementById('id01');
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

// Sự kiện sign 
var modal = document.getElementById('id02');
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

// Bắt lỗi form Liên hệ
function kiemtra() {
  var ten = document.forms["myform"]["ten"].value;
  var email = document.forms["myform"]["email"].value;
  var noidung = document.forms["myform"]["subject"].value;
  var dem = 0;
  if (ten == "") {
    document.getElementById("erro1").innerHTML = "Tên không được để trống";
    return false;
  }
  if (email == "") {
    document.getElementById("erro2").innerHTML = "Email không được để trống";
    return false;
  } else {
    for (var i = 0; i < email.length; i++) {
      if (email.charAt(i) == '@') {
        dem++;
      }
    }
    if (dem == 0 || dem > 1) {
      document.getElementById("erro2").innerHTML = "Email không hợp lệ";
      return false;
    }
  }
  if (noidung == "") {
    document.getElementById("erro3").innerHTML = "Nội dung không được để trống";
    return false;
  }
}