<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="CoordinatorDetails" method="post">
<input type="text" name="isFirst" value="0" readonly hidden />
<input type="text" name="id" value='<%=request.getParameter("id") %>' readonly hidden />
<input type="text" name="pass" value='<%=request.getParameter("pass") %>' readonly hidden />
<input type="text" name="type" value='<%=request.getAttribute("type") %>' readonly hidden />
<input type="radio" name="option" value="Verify Students" />Verify Students<br>
<input type="radio" name="option" value="Verify JAFs" />Verify JAFs<br>
<input type="submit" value="Submit" />
</form>
</body>
</html>