alert("in user-crud.js");


/**
 * getUserInfoForm()
 * retrieves user data by user Id and redirects to a page where info is displayed
 * Input: int: userId
 * Output: none
 * TODO: Output: Could return 1 or 0 to signal success or error
 */
function getUserInfoForm(userId) {
	// retrieve the user data by table row id
	var theUrl = "http://localhost:8080/formwebapp/resources/online/user/" + userId;

	$.ajax( {
		url: theUrl,
		type: "GET",
		dataType: "text",
		success: function(result) {
			// result contains an array with user data

			// UserArray contains deserialized data: 
			// theId, theUsername, theUserFirstName, theUserLastName, theUserEmail, theUserId, theUserPassword, theUserInactive
			var UserArray = JSON.parse(result);

			// fill this info wherever is needed on the view
			fillUserInfoFields(UserArray);
 
		},
		error:function(xhr) {
			alert("Error, status: " + xhr.status + ", status text: " + xhr.statusText);
		}
	});	
}


/**
 * fillUserInfoFields()
 * fills in the user data in the table cell input elements on user-info.html 
 * Input: array: UserArray
 * Output: none
 */
function fillUserInfoFields(UserArray) {
	$("#t_userUserId").val(UserArray[0].user_id);
	$("#t_userName").val(UserArray[0].userName);
	$("#t_userFirstName").val(UserArray[0].user_firstName);
	$("#t_userLastName").val(UserArray[0].user_lastName);
	$("#t_userEmail").val(UserArray[0].user_email);
}

/*
* Not used yet
function getUserFields() {
	var data = {
		'userName': $("#t_updateUserName").val(),
		'user_firstName': $("#t_updateFirstName").val(),
		'user_lastName': $("#t_updateLastName").val(),
		'user_email': $("#t_updateEmail").val(),
		'user_id': $("#t_updateUserId").val(),
		'user_password': $("#t_updatePassword").val(),
	};
	return data;
}
*/
