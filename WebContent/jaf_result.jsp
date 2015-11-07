<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="database.*" %>
<%@ page import="ui.*" %>
<!DOCTYPE html>
<html>
<%=HTMLHeaderUtils.getGenericHeader("JAF Result")%>
<body>
<%=HTMLHeaderUtils.getTopNavBar(request.getSession(false).getAttribute("entity").toString())%>
	<div class='row' style='height:100%'>
		<%=HTMLHeaderUtils.getGenericSidebar(request.getSession(false).getAttribute("entity").toString(), request.getSession(false).getAttribute("name").toString())%>
		<div class='col s9 offset-s0'>
			<div class='col s11 offset-s1'>
			<h3 style="text-align:center">
				<%
					out.print(request.getParameter("option"));
				%>
			</h3>
			<p>
				<%
				String option = request.getParameter("option");
		
				if (option == null || option.equalsIgnoreCase(""))
				{
					response.getWriter().append("Invalid input");
					request.getRequestDispatcher("student_result.jsp").forward(request,
							response);
				}
				else if (option.equals("JAF Details"))
				{
					out.print(request.getAttribute("JAF Details").toString());
				}
				else
					out.print("Option not available");
				%>
			</p>
			</div>
		</div>
	</div>
</body>
</html>