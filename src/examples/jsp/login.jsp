 <%
     // validation check for login
     String login = request.getParameter("login");
     if (login != null) {
	    session.invalidate();
     	session = request.getSession();
     	session.setAttribute("validate_login", new Boolean("true"));
     	session.setAttribute("login", login);
 %>
 	<jsp:forward page="sushi_list.jsp"/>
 <% } %>
 <html>
    <head>
        <title>Login</title>
    </head>
    <body>
    	<H1>Weclome to <I>Sushi</I> World</H1>
    	<BR>
    	<form action="login.jsp" method="post">
			Name <input type="text" name="login">
			<input type="submit" value="Login">
    	</form>
    </body>
</html>