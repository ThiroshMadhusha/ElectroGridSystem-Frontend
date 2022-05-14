<%@page import = "com.Bill" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/bills.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Bills Management</h1>
	<form id="formBill" name="formBill" method="post" action="bills.jsp">
	 Bill Account No: 
	 <input id="billAccNo" name="billAccNo" type="text" 
	 class="form-control form-control-sm">
	 <br> 
	 Customer Name: 
	 <input id="cusName" name="cusName" type="text" 
	 class="form-control form-control-sm">
	 <br> 
	 Monthly Unit: 
	 <input id="billUnit" name="billUnit" type="text" 
	 class="form-control form-control-sm">
	 <br> 
	 Monthly Amount: 
	 <input id="billAmount" name="billAmount" type="text" 
	 class="form-control form-control-sm">
	 <br>
	 <input id="btnSave" name="btnSave" type="button" value="Save" 
	 class="btn btn-primary">
	 <input type="hidden" id="hidItemIDSave" 
	 name="hidItemIDSave" value="">
	</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Bill billObj = new Bill(); 
 out.print(billObj.readBills()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
