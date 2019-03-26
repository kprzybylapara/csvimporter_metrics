<%
	String login = null;
	String amount = null;
	try {
		session = request.getSession(false);
		login = (String)session.getAttribute("login");
		amount = (String)request.getParameter("amount");
		session.invalidate();
	} catch (Exception e) {
	}
%>
<html>
	<head>
		<title>Confirm</title>
	</head>
	
	<body>
		<H1>Thank you for shopping Sushi World</H1>
		<BR>
		<H1>Amount $<%= amount %> will be charged in your credit card!!</H1>
	<a href="login.jsp">Logout</a>
	</body>
</html>