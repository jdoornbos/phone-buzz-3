<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Phone Buzz   </title>
		<script>
		function validateForm()
		{
			var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;  
			var delaytime = /[0-9]?[0-9]:[0-9][0-9]/;
			
		    if(!document.frm.number.value.match(phoneno) || !document.frm.number.value.match(/\d/g).length===10)
		    {
		      alert("Not a valid phone number.");
		      document.frm.number.focus();
		      return false;
		    }
		    else if(!document.frm.delay.value.match(delaytime))
		    {
		      alert("Not a valid delay time. Time must be in the format mm:ss");
		      document.frm.delay.focus();
		      return false;
		    }
		}
		</script>
	</head>	
	<body>		
		<form name="frm" action="callout" onSubmit="return validateForm()">			
			 Please enter a 10-digit phone number: 
			 <input type="text" name="number"size="20px"> <br>
			 Please enter amount of time to delay: 
			 <input type="text" placeholder="mm:ss" name="delay" size="20px"> <br>
			<input type="submit" value="submit">						
		</form>		
	</body>	
</html>