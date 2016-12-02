<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.model.*" %>

<html>
<head>

	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

	<title>
		Displaying XML data using XML data islands
	</title>
</head>

<body>

<h1 class="text-center">Displaying XML data using XML data islands!</h1>
<h2 class="text-center">Customers</h2>

<% List list = (List)request.getAttribute("customers"); %>
<% Iterator iterator; %>
<% Iterator iterator2; %>

<div class="container">
	<table class="table table-bordered table-hover">
		<tr>
			<th class="text-center col-md-1">Id</th>
			<th class="text-center col-md-2">Name</th>
		</tr>
		<% for (iterator = list.iterator(); iterator.hasNext();) {
			Customer customer = (Customer)iterator.next(); %>
			<tr>
				<td class="text-center"><%=customer.getId()%></td>
				<td class="text-center"><%=customer.getName()%></td>
			</tr>
		<% } %>
	</table>
</div>

<h2 class="text-center">Orders</h2>

<c:if test="${!empty customers}">
	<div class="container">
		<table class="table table-bordered table-hover">
			<tr>
				<th class="col-md-1 text-center">id</th>
				<th class="col-md-1 text-center">customer_id</th>
				<th class="col-md-1 text-center">position_id</th>
				<th class="col-md-1 text-center">price</th>
				<th class="col-md-1 text-center">count</th>
			</tr>
			<% for (iterator = list.iterator(); iterator.hasNext();) {
				Customer customer = (Customer)iterator.next();
				Orders orders = customer.getOrders();
				List listoforders = orders.getOrder();
				for (iterator2 = listoforders.iterator(); iterator2.hasNext();) {
					Order order = (Order)iterator2.next();
					Position position = order.getPosition(); %>
					<tr>
						<td><%=order.getId()%></td>
						<td><%=customer.getId()%></td>
						<td><%=position.getId()%></td>
						<td><%=position.getPrice()%></td>
						<td><%=position.getCount()%></td>
					</tr>
				<% } %>
			<% } %>
		</table>
	</div>
</c:if>

<h2 class="text-center">Querys results:</h2>

<div class="container">
	<table class="table table-bordered">
		<tr>
			<th><h3>Sum of all orders:</h3></th>
			<th><h3>Client with max sum of orders:</h3></th>
			<th><h3>Max order price:</h3></th>
			<th><h3>Min order price:</h3></th>
			<th><h3>Count of orders:</h3></th>
			<th><h3>Average order price:</h3></th>
		</tr>
		<tr>
			<td>${sumAll}</td>
			<td>${maxOrdersSum}</td>
			<td>${maxOrderPrice}</td>
			<td>${minOrderPrice}</td>
			<td>${ordersCount}</td>
			<td>${avgPrice}</td>
		</tr>
	</table>
</div>

<br/>

<form:form cssClass="text-center" method="post" commandName="position">
	<form:label for="enterPrice" path="price"><h4>Enter the price:</h4></form:label>
	<form:input id="enterPrice" placeholder="Enter price" path="price"/>
	<button type="submit" class="btn btn-success">Submit</button>
	<form:errors cssStyle="color: #ff0000;" path="price"></form:errors>
</form:form>


</body>
</html>