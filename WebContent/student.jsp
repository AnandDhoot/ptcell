<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	String approved = request.getAttribute("approved").toString();
	if (approved.equals("0"))
	  	{
%>
			<a href="/ptcell/StudentDetails?option=Edit+Personal+Details">Edit Personal Details</a>
<% 		}
  	else
  	{
%>			
			
<a href="/ptcell/StudentDetails?option=Personal+Details">Personal Details</a><br>
<a href="/ptcell/StudentDetails?option=Signed+JAFs">See signed JAFs</a><br>
<a href="/ptcell/StudentDetails?option=Open+JAFs">See Open JAFs</a><br>
<a href="/ptcell/StudentDetails?option=Resume+Upload">Resume Upload</a><br>		
<a href="/ptcell/StudentResume">Download Resume</a><br>		
<%
	}
%>
<br>
</body>
</html>
