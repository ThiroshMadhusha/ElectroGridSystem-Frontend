// CLIENT-MODEL================================================================
function validateItemForm()
{
// Bill Account Number
if ($("#billAccNo").val().trim() == "")
{
return "Insert Bill Account Number.";
}
// NAME
if ($("#cusName").val().trim() == "")
{
return "Insert Bill Customer Name.";
}
// Unit-------------------------------
if ($("#billUnit").val().trim() == "")
{
return "Insert Monthly Bill Unit.";
}
// Amount------------------------
if ($("#billAmount").val().trim() == "")
{
return "Insert Monthly Bill Amount.";
}
return true;
}


$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});





// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
$("#formBill").submit();
});







// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
$("#billAccNo").val($(this).closest("tr").find('td:eq(0)').text());
$("#cusName").val($(this).closest("tr").find('td:eq(1)').text());
$("#billUnit").val($(this).closest("tr").find('td:eq(2)').text());
$("#billAmount").val($(this).closest("tr").find('td:eq(3)').text());
});
// CLIENT-MODEL================================================================
function validateItemForm()
{
// Account Number
if ($("#billAccNo").val().trim() == "")
{
return "Insert Bill Account Number.";
}
// NAME
if ($("#cusName").val().trim() == "")
{
return "Insert Bill Customer Name.";
}

// Unit-------------------------------
if ($("#billUnit").val().trim() == "")
{
return "Insert Monthly Bill Unit.";
}
// Amount------------------------
if ($("#billAmount").val().trim() == "")
{
return "Insert Monthly Bill Amount.";
}
return true;
}







$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "BillsAPI", 
 type : type, 
 data : $("#formBill").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});


function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 

 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

// Delete
$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "BillsAPI", 
 type : "DELETE", 
 data : "billID=" + $(this).data("billid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});


function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}