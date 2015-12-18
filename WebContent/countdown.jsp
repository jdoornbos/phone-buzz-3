<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">    
var mins = <%=request.getAttribute("mins")%>;
var secs = mins * 60 + <%=request.getAttribute("secs")%>;
var currentSeconds = 0;
var currentMinutes = 0;

setTimeout(Decrement,1000); 

function Decrement() {
    currentMinutes = Math.floor(secs / 60);
    currentSeconds = secs % 60;
    if(currentSeconds <= 9) currentSeconds = "0" + currentSeconds;
    secs--;
    if(secs <= -1) {
    	currentSeconds = "00";
    	currentMinutes = "0";
    }
    document.getElementById("timerText").innerHTML = currentMinutes + ":" + currentSeconds; //Set the element id you need the time put into.
    if(secs > -8) setTimeout('Decrement()',1000);
    if(secs <= -8) window.location = "default.jsp";
}
</script>
</head>
<body>
<h1>Calling in approximately <span id="timerText"></span></h1>
</body>
</html>