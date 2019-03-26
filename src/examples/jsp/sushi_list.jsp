<%!
	private static String[] SUSHI_NAMES = {
												"abalone"
												, "uni"
												, "tuna"
												, "shrimp"
												, "salmon"
												, "scallop"
												, "yellow tail"
												, "octopus"
												, "eel"
												, "clam"
												, "whitefish"
												, "egg omelet"
	};
	
	private static double[] SUSHI_PRICE = {
												6
												, 6
												, 4
												, 3
												, 4
												, 4
												, 4
												, 3.5
												, 5
												, 4
												, 3.5
												, 2.5
												
	};

%>

<%
	Boolean validate = (Boolean)session.getAttribute("validate_login");
	if (validate != null) {
       boolean b = validate.booleanValue();
       if (b == true) {
 %>
<html>
	<head>
		<title>Sushi list</title>
	</head>
	
	<body>
		<H1>Sushi Table</H1>
		<form action="mycart.jsp" method="post">
			<table border width=40%>
				<tr>
					<td align="center"><B>Name</B></td>
					<td align="center"><B>Price (US)</B></td>
					<td align="center"><B>Qty</B></td>
				</tr>
				<%
					for (int i = 0; i < SUSHI_NAMES.length; i++) {
				%>
				<tr>
					<td padding="2"><%= SUSHI_NAMES[i] %></td>
					<td><input type="hidden" name="<%= SUSHI_NAMES[i]+"_price" %>" value="<%= SUSHI_PRICE[i] %>">$<%= SUSHI_PRICE[i] %></td>
					<td><input type="text" size="5" name="<%= SUSHI_NAMES[i]+"_qty" %>" value="0"></td>
				</tr>
				<% 
					}
				%>
				<tr>
					<td align="right" colspan="3"><input type="submit" value="Add into cart"></td>
				</tr>
			</table>
		</form>
	
	</body>
</html>

<% 
       }
   } else {
%>
    <jsp:forward page="login.jsp"/>
<% } %>
