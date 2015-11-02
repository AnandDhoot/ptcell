<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="StudentDetails" method="post">
<input type="text" name="type" value='<%=request.getAttribute("type") %>' readonly hidden />
<% 
	String approved = request.getAttribute("approved").toString();
	if (approved.equals("0"))
	  	{
%>
			<input type="radio" name="option" value="Edit Personal Details" />Edit Personal Details<br>
<% 		}
  	else
  	{
%>			

			<input type="radio" name="option" value="Personal Details" />View Personal Details<br>
			<input type="radio" name="option" value="Signed JAFs" />See signed JAFs<br>
			<input type="radio" name="option" value="Open JAFs" />See Open JAFs<br>
<%
	}
%>
<br>
<input type="submit" value="Submit" />
</form>
</body>
</html>