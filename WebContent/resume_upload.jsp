<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ui.*" %>
<!DOCTYPE html>
<html>
<%
	out.print(HTMLHeaderUtils.getGenericHeader("Student Result"));
%>
<body>
<%
	out.print(HTMLHeaderUtils.getTopNavBar(request.getSession(false).getAttribute("entity").toString()));
%>
<div class='row' style='height:100%'>
	<%=HTMLHeaderUtils.getGenericSidebar(request.getSession(false).getAttribute("entity").toString(), request.getSession(false).getAttribute("name").toString())%>
	<div class='col s8 offset-s0' style="text-align:center">
		<br><br>
			<%
			if (request.getAttribute("approved").toString().equals("0"))
			{
				request.getRequestDispatcher("student.jsp").forward(request, response);
				return;
			}
			else
			{
			%>  				
				<br><br><br>
				<form action='StudentDetails?option=Store+PDF' method='post' enctype='multipart/form-data'>
					<b>Upload Resume</b>
					<input type='file' name='file'/>
					<button class='btn waves-effect waves-light' type='submit' name='action'>Submit</button>
				</form>
			<%
			}
			%>
	</div>
</div>
</body>
</html>