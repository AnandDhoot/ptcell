<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="database.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>
		<%
		out.print(request.getParameter("option"));
		%>
</h3>
<p>
		<%
		String id = request.getSession(false).getAttribute("id").toString();
		if(!request.getSession(false).getAttribute("entity").equals("Coordinator"))
		{
			request.getRequestDispatcher("/Logout").forward(request,
					response);
			return;
		}
		
		String option = request.getParameter("option");
		if (option.equals("Verify Students"))
		{
			List<String> StuList = Coordi.getStudents(id);
			int i = 0;

			if (StuList.size() > 0)
			{
				%>
<table>
<tr>
<th>Roll No.</th>
<th>Name</th>
<th>Department</th>
<th>Status</th>
<th>Profile</th>
<th>See Resume</th>
</tr>
				<%
			}

			while (i < StuList.size() / 4)
			{
				out.print("<tr><td>");
				out.print(StuList.get(i * 4));
				out.print("</td><td>");
				out.print(StuList.get(i * 4 + 1));
				out.print("</td><td>");
				out.print(StuList.get(i * 4 + 2));
				out.print("</td><td>");
				out.print(StuList.get(i * 4 + 3));
				out.print("</td><td>");
				out.println("<form action='coordinator_result.jsp' method='post'>"
						+ "<input type='text' name='option' value='StudDetails' hidden/>"
						+ "<input type='text' name='rollno' value='" + StuList.get(i * 4) + "' hidden/>"
						+ "<input type='submit' value='View Details' />" + "</form>");
				out.print("</td><td>");
				out.println("<form action='StudentResume' method='post'>"
						+ "<input type='text' name='rollno' value='" + StuList.get(i * 4) + "' hidden/>"
						+ "<input type='submit' value='View Resume' />" + "</form>");
				out.print("</td></tr>");
				i++;
			}

			if (StuList.size() > 0)
				out.print("</table>");
		}
		else if (option.equals("StudDetails"))
		{
			String sid = request.getParameter("rollno");
			List<String> studentDetails = Student.getStudentDetails(sid);
			String details = "<table>";
			for (int i = 0; i < studentDetails.size() / 2; i++)
				details += "<tr><td>" + studentDetails.get(i * 2) + "</td><td>" + studentDetails.get(i * 2 + 1)
						+ "</td></tr>";
			out.print(details);
		%>
			</table>
			<form action='coordinator_result.jsp' method='post'>
			<input type='text' name='option' value='VerifyStud' hidden/>
			<input type='text' name='rollno' value='<%=sid%>' hidden/>
			<input type='submit' value='Verify' /></form>
		<%
		}
		else if (option.equals("VerifyStud"))
		{
			String rollno = request.getParameter("rollno").toString();
			System.err.println(rollno);
			Student.chgStatus(1, rollno);
		}
		else if (option.equals("VerifyJAF"))
		{
			String companyID = request.getParameter("CompID").toString();
			int JAFNumber = Integer.parseInt(request.getParameter("jafNum").toString());
			JAF.chgJAFStage(1, JAFNumber, companyID);
		}
		else if (option.equals("JAFDetails"))
		{ // TODO ADD Verify Button
			String companyID = request.getParameter("CompID").toString();
			int JAFNumber = Integer.parseInt(request.getParameter("jafNum").toString());

			String details = "<table>";
			List<String> JAFDetails = JAF.getJAFDetails(companyID, JAFNumber);
			for (int i = 0; i < JAFDetails.size() / 2; i++)
				details += "<tr><td>" + JAFDetails.get(i * 2) + "</td><td>" + JAFDetails.get(i * 2 + 1) + "</td></tr>";
			out.print(details);
		%>
					</table>
					<form action='coordinator_result.jsp' method='post'>
					<input type='text' name='option' value='VerifyJAF' hidden/>
					<input type='text' name='CompID' value='" + companyID + "' hidden/>
					<input type='text' name='jafNum' value='" + JAFNumber + "' hidden/>
					<input type='submit' value='Verify' />
					</form>
		<%
		}
		else if (option.equals("Verify JAFs"))
		{
			List<String> StuList = Coordi.getJAFs(id);
			int i = 0;

			if (StuList.size() > 0)
			{
				out.print("<table>");
				out.print("<tr>");
				out.print("<th>Name</th>");
				out.print("<th>JAF No.</th>");
				out.print("<th>Stage</th>");
				out.print("<th>Company Name</th>");
				out.print("<th>View Details & Verify</th>");
				out.print("</tr>");
			}

			while (i < StuList.size() / 5)
			{
				out.print("<tr><td>");
				out.print(StuList.get(i * 5));
				out.print("</td><td>");
				out.print(StuList.get(i * 5 + 1));
				out.print("</td><td>");
				out.print(StuList.get(i * 5 + 2));
				out.print("</td><td>");
				out.print(StuList.get(i * 5 + 3));
				out.print("</td><td>");
				out.println("<form action='CoordinatorDetails' method='post'>"
						+ "<input type='text' name='option' value='JAFDetails' hidden/>"
						+ "<input type='text' name='CompID' value='" + StuList.get(i * 5 + 4) + "' hidden/>"
						+ "<input type='text' name='jafNum' value='" + StuList.get(i * 5 + 1) + "' hidden/>"
						+ "<input type='submit' value='View Details & Verify' />" + "</form>");
				out.print("</td></tr>");
				i++;
			}
%>
<%
			if (StuList.size() > 0)
				out.print("</table>");

		}
%>
		</p></center>




</body>
</html>