<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">


<title>Welcome Page</title>
</head>
<body>
<h2 align="center">
		<div id="header">
			&nbsp;
			<div align="center">Festival Event Registration System</div>
		</div></h2>
	<table align="right" cellpadding="2">
		<tr>
			<td width="90">
				<div id="menu" align="center">
					<a href="<c:url value="event/showEvent.html"/>"> Events </a>
				</div>
			</td>

			<td width="90">
				<div id="menu" align="center">
					<a href="<c:url value="event/about.html"/>"> About</a><br />
				</div>
			<td width="160">
				<div id="menu" align="center">
					<a style="text-decoration: none; color: black;"
						href="visitor/logIn.html"> LogIn </a>
				</div>
			</td>

		</tr>
	</table>
	
	</h2>

</body>
</html>