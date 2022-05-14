$(document).on("click", "#btnLogin", function(event)
{
// Clear alerts---------------------
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateLoginForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 $.ajax(
 {
 url : "LoginAPI",
 type : "POST",
 data : $("#formLogin").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onLoginComplete(response.responseText, status);
 }
 });
});

function onLoginComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 // Redirect the valid user-----------------
 document.location = "items.jsp";
 }
 else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while login.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while login.");
 $("#alertError").show();
 }
 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}

function validateLoginForm()
{
// USERNAME
if ($("#txtUsername").val().trim() == "")
 {
 return "Insert Username.";
 }
// PASSWORD
if ($("#txtPassword").val().trim() == "")
 {
 return "Insert Password.";
 }
return true;
}