$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

//SAVE
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
 		$("#alertSuccess").text(""); 
 		$("#alertSuccess").hide(); 
 		$("#alertError").text(""); 
 		$("#alertError").hide(); 

// Form validation-------------------
		var status = validateUserForm(); 
		if (status != true) 
 		{ 
 			$("#alertError").text(status); 
 			$("#alertError").show(); 
 			return; 
 		} 
		// If valid------------------------
		var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT"; 
 		$.ajax( 
 		{ 
 			url : "UsersAPI", 
 			type : type, 
 			data : $("#formUser").serialize(), 
 			dataType : "text", 
 			complete : function(response, status){ 
 			onUserSaveComplete(response.responseText, status); 
 			} 
 		}); 
});


function onUserSaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
			 var resultSet = JSON.parse(response); 
 			 if (resultSet.status.trim() == "success"){ 
 			 	$("#alertSuccess").text("Successfully saved."); 
 			 	$("#alertSuccess").show(); 
             	$("#divUserGrid").html(resultSet.data); 
             
 		     }else if (resultSet.status.trim() == "error"){ 
 				$("#alertError").text(resultSet.data); 
 				$("#alertError").show(); 
 			} 
 } else if (status == "error"){ 
 		    $("#alertError").text("Error while saving."); 
 			$("#alertError").show(); 
 } else{ 
 			$("#alertError").text("Unknown error while saving.."); 
 			$("#alertError").show(); 
 }
			$("#hidUserIDSave").val(""); 
			$("#formUser")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		 $("#hidUserIDSave").val($(this).data("userid")); 
		 $("#name").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#address").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#nic").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#email").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("#phone").val($(this).closest("tr").find('td:eq(5)').text());  
		});
		
$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "UsersAPI", 
		 type : "DELETE", 
		 data : "userId=" + $(this).data("userid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onUserDeleteComplete(response.responseText, status); 
		 } 
		 
		 });
		  
		});		


function onUserDeleteComplete(response, status){ 
	if (status == "success"){ 
 			var resultSet = JSON.parse(response); 
 			if (resultSet.status.trim() == "success"){ 
 					$("#alertSuccess").text("Successfully deleted."); 
 					$("#alertSuccess").show(); 
 					$("#divItemsGrid").html(resultSet.data); 
 			}else if(resultSet.status.trim() == "error"){ 
 					$("#alertError").text(resultSet.data); 
 					$("#alertError").show(); 
 			} 
 	}else if(status == "error"){ 
 		  $("#alertError").text("Error while deleting."); 
 		  $("#alertError").show(); 
 	}else{ 
 		  $("#alertError").text("Unknown error while deleting.."); 
          $("#alertError").show(); 
 } 
};


// CLIENT-MODEL================================================================
function validateUserForm(){
	// UserAccount--------------------------
	if ($("#accountNo").val().trim() == ""){
		return "Insert Account Number.";
	}
	
	// UserName--------------------------
	if ($("#name").val().trim() == ""){
		return "Insert Name.";
	}
	
	// UserAddress-------------------------------
	if ($("#address").val().trim() == ""){
		return "Insert User Address.";
	}
	
	// UserNIC-------------------------------
	if ($("#nic").val().trim() == ""){
		return "Insert NIC.";
	}
	
	// UserEmail-------------------------------
	if ($("#email").val().trim() == ""){
		return "Insert User Email.";
	}
	
	// UserPhone-------------------------------
	if ($("#phone").val().trim() == ""){
		return "Insert Phone Number.";
	}
	
	// UserPassword-------------------------------
	if ($("#password").val().trim() == ""){
		return "Insert Password.";
	}
	
	// UserRole-------------------------------
	if ($("#userrole").val().trim() == ""){
		return "Select User Role";
	}
	
	
	var email = $("#email").val().trim();
    var filter = /^[A-Za-z0-9+_.-]+@(.+)$/i;
	 if(filter.test(email) === false)
    {
        return "You Have Entered An Invalid E-mail Address";
    }
    
    
    var phone = $("#phone").val().trim();
    var phoneFilter = /^(?:7|0|(?:\\+94))[0-9]{9,10}$/;
   	if (phoneFilter.test(phone) === false) {
        return "You Have Entered An Invalid Contact Number";
    }
    
    var firstName =  $("#name").val().trim();
    var filterFirst = /^[a-zA-Z]+$/i;
    if (filterFirst.test(firstName) === false) {
        return "You Have Entered An Invalid First Name";
    }

	return true;
}
