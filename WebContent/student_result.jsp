<%@page import="database.JAF"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<center>
		<h3><%=request.getParameter("option")%></h3>
		<p>
		<%
			String id = request.getParameter("id");
			String dept = request.getParameter("dept");
			Float cpi = Float.parseFloat(request.getParameter("cpi"));
			String option = request.getParameter("option");
		
			if(option==null || option.equalsIgnoreCase(""))
			{
				out.print("Invalid input");
			} 
			else if(option.equals("Personal Details"))
			{
				out.print(request.getAttribute("StudentDetails").toString());
			}
			else if(option.equals("Signed JAFs"))
			{
				List<String> JAFList = JAF.getSignedJAFs(id);
				int i = 0;
				
				if(JAFList.size() > 0)
				{
					out.print("<table>");
					out.print("<tr>");
					out.print("<th>Company Name</th>");
					out.print("<th>JAF Number</th>");
					out.print("<th>Status</th>");
					out.print("<th>Details</th>");
					out.print("</tr>");
				}

				while(i < JAFList.size()/3)
				{
					out.print("<tr><td>");
					out.print(JAFList.get(i*3));
					out.print("</td><td>");
					out.print(JAFList.get(i*3)+1);
					out.print("</td><td>");
					out.print(JAFList.get(i*3)+2);
					out.print("</td><td>");
					out.print("View Details");
					out.print("</td></tr>");
					i++;
				}
				
				if(JAFList.size() > 0)
					out.print("</table>");
			} 
			else if(option.equals("Open JAFs"))
			{
				List<String> JAFList = JAF.getOpenEligibleJAFs(id);
				int i = 0;
				
				if(JAFList.size() > 0)
				{
					out.print("<table border='1'>");
					out.print("<tr>");
					out.print("<th>Company Name</th>");
					out.print("<th>Category</th>");
					out.print("<th>JAF Number</th>");
					out.print("<th>End Time</th>");
					out.print("<th>Profile</th>");
					out.print("<th>Details</th>");
					out.print("</tr>");
				}

				while(i < JAFList.size()/5)
				{
					out.print("<tr><td>");
					out.print(JAFList.get(i*5));
					out.print("</td><td>");
					out.print(JAFList.get(i*5+1));
					out.print("</td><td>");
					out.print(JAFList.get(i*5+2));
					out.print("</td><td>");
					out.print(JAFList.get(i*5+3));
					out.print("</td><td>");
					out.print(JAFList.get(i*5+4));
					out.print("</td><td>");
					out.print("View More Details");
					out.print("</td></tr>");
					i++;
				}
				
				if(JAFList.size() > 0)
					out.print("</table>");
			} 
			else
			{
				out.print("Option not supported");
			}
		%>
		</p>
	</center>
</body>
</html>