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
Student Roll Number: 
<input type="text" name="id" value='<%=request.getParameter("id") %>' readonly /><br><br>
<input type="text" name="pass" value='<%=request.getParameter("pass") %>' readonly /><br><br>
<input type="text" name="type" value='<%=request.getAttribute("type") %>' readonly /><br><br>
<input type="text" name="dept" value='<%=request.getAttribute("dept") %>' readonly /><br><br>
<input type="text" name="cpi" value='<%=request.getAttribute("cpi") %>' readonly /><br><br>
<input type="radio" name="option" value="Personal Details" />View Personal Details<br>
<input type="radio" name="option" value="Signed JAFs" />See signed JAFs<br>
<input type="radio" name="option" value="Open JAFs" />See Open JAFs<br>
<br>
<input type="submit" value="Submit" />
</form>
</body>
</html>