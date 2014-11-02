$(document) .ready( function() {
	alert("Javascript is running");
	$("#btnLogin") .click(function() {
		alert ("Button clicked");
		var UserName=document.getElementById("t_username").value;
		var UserPassword=document.getElementById("t_password").value;
		//create cookie
		//convert UTC time to EST
		var offset = -5.0;
		var utc_date = new Date();
		var utc = utc_date.getTime() + (utc_date.getTimezoneOffset() * 60000);

		var cur_date = new Date(utc + (3600000 * offset)); 

		//Add hour for daylight savings time
		cur_date.setTime(cur_date.getTime() + (60*60*1000));
			
		//set expiration time	
		cur_date.setTime(cur_date.getTime() + (60*30*1000));

		var expires = "expires="+cur_date.toLocaleString();
		var cookie = "username=" + UserName + "; " + expires + "";
		//alert(cookie);	

		var sessionID = UserName + cur_date.toLocaleString();
		//alert(sessionID);
		
		var theData= "theUsername="+UserName+"&"+"thePassword="+UserPassword+"&"+"theSessionID="+sessionID;
		//alert("Sending: "+theData);
		
		var theUrl = "http://localhost:8080/formwebapp/resources/online/authSession";
		$.ajax( {
			url: theUrl,
			type: "POST",
			dataType: "text",
			contentType: "application/x-www-form-urlencoded",
			data: theData,
			success: function(responseData) { 
				/* responseData is what the server returns in the response object, in our case the user table row Id*/
				//alert("user Id: "+ responseData);		
				sessionStorage[ 'userRecordId' ] = responseData;		
				window.location.href="http://localhost:8080/formwebapp/user-info.html";
				document.cookie = cookie;
			},
			error:function(status) { 
				alert("Login unsuccessful. Username or password is incorrect."); 
			},
		});
	});
	$("#btnRegister") .click(function() {
		alert ("clicked");
		//create cookie
		//convert UTC time to EST
		var offset = -5.0;
		var utc_date = new Date();
		var utc = utc_date.getTime() + (utc_date.getTimezoneOffset() * 60000);

		var cur_date = new Date(utc + (3600000 * offset)); 

		//Add hour for daylight savings time
		cur_date.setTime(cur_date.getTime() + (60*60*1000));
			
		//set expiration time	
		cur_date.setTime(cur_date.getTime() + (0*03*1000));

		var expires = "expires="+cur_date.toLocaleString();
		var cookie = "username=register" + expires;
		alert(cookie);	

		var sessionID = "register" + cur_date.toLocaleString();
		alert(sessionID); 
		
		if(cookie){
		
			alert("success"); 
			window.location.href="http://localhost:8080/formwebapp/register.html";
			document.cookie = cookie;
		}
		else{
		
			alert("Your session has timed out.");
		}
	});
});