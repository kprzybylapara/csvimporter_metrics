<%@ page import="java.lang.*, java.lang.String, java.util.Enumeration, java.util.Hashtable" %>

<%!

    public Hashtable price_table;
    public Hashtable qty_table;
    
    /**
     * @return boolean
     */
	public static void setTable(Hashtable price_table, Hashtable qty_table, HttpServletRequest request) {
	
		Enumeration en = request.getParameterNames();
		for (; en.hasMoreElements();) {
			String pname = (String)en.nextElement();
			if (pname.endsWith("_price")) {
				String name = pname.substring(0, pname.length()-"_price".length());
				String price = request.getParameter(pname);
				price_table.put(name, new Double(price));
			} else if (pname.endsWith("_qty")) {
				String name = pname.substring(0, pname.length()-"_qty".length());
				String qty = request.getParameter(pname);
				int total = Integer.parseInt(qty);
				Integer count = (Integer)qty_table.get(name);
				if (count != null) {
					total = total + count.intValue();
				}
				qty_table.put(name, new Integer(total));
			}
		}
	
	}
%>
<%
	session = request.getSession(false);
	String login = (String)session.getAttribute("login");
	price_table = (Hashtable)session.getAttribute("price_table");
	if (price_table == null)
		price_table = new Hashtable();
	qty_table = (Hashtable)session.getAttribute("qty_table");
	if (qty_table == null)
		qty_table = new Hashtable();
	this.setTable(price_table, qty_table, request);
	
	session.setAttribute("price_table", price_table);
	session.setAttribute("qty_table", qty_table);
%>
<html>
	<head>
		<title><%= login %> cart</title>
	</head>
	
	<body>
		<H1>My Sushi Cart</H1>
		<a href="sushi_list.jsp"><< back to shopping</a>
		<form action="confirm.jsp" method="post">
			<table border width=40%>
				<tr>
					<td align="center"><B>Sushi Name</B></td>
					<td align="center"><B>Qty</B></td>
					<td align="center"><B>Amount (US)</B></td>
				</tr>
				<% 
					double total_amount = 0;
					int total_qty = 0;
					int num_items = 0;
					for (Enumeration e = price_table.keys(); e.hasMoreElements(); ) {
						String sushi_name = (String)e.nextElement();
						Double sushi_price = (Double)price_table.get(sushi_name);
						Integer sushi_qty = (Integer)qty_table.get(sushi_name);
						if (sushi_qty.intValue() == 0) {
							continue;
						}
						double price = sushi_price.doubleValue();
						int qty = sushi_qty.intValue();
						double sushi_amount = (price * qty);
						total_amount = total_amount + sushi_amount;
						total_qty = total_qty + sushi_qty.intValue();
				%>
				<tr>
					<td><%= sushi_name  %>
					<td><%= sushi_qty.intValue() %>
					<td><%= sushi_amount %>
				</tr>
				<%
					}
				%>
				<tr>
					<td><B>Total</B></td>
					<td><B><%= total_qty %></B></td>
					<td><B><%= total_amount %></B></td>
				</tr>
				<tr>
					<td align="right" colspan="3">
						<input type="hidden" name="amount" value="<%= total_amount %>">
						<input type="submit" value="check out">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>