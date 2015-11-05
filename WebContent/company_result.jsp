<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*" %>
<%@ page import="database.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	out.print("<center><h3>");
	out.print(request.getParameter("option"));
	out.println("</h3><p>");
	HttpSession ss = request.getSession(false);
	String id = ss.getAttribute("id").toString();
	String pass = ss.getAttribute("pass").toString();
	if(!ss.getAttribute("entity").equals("Company"))
	{
		request.getRequestDispatcher("/Logout").forward(request,
				response);
		return;}
	String option = request.getParameter("option");
	if (option.equals("Create JAF"))
	{
		String details = "";
		details += "<form action='company_result.jsp' method='post'>"
				+ "<input type='text' name='option' value='Submit New JAF' hidden/>"
				+ "<input type='text' name='id' value='" + id + "' hidden/>"
				+ "<input type='text' name='pass' value='" + pass + "' hidden/>"
				+ "<input type='datetime-local' name='endDate' value='2015-12-31T23:59' /> JAF End Date <br>"
				+ "<input type='number' step='0.01' name='cpiCutoff' value='7.00' /> CPI Cutoff <br> "
				+ "Select Eligible Departments <br>"
				+ "<input type='checkbox' name='deptEligible' value='AE'> AE <br>"
				+ "<input type='checkbox' name='deptEligible' value='CS'> CS <br>"
				+ "<input type='checkbox' name='deptEligible' value='EE'> EE <br>"
				+ "<input type='checkbox' name='deptEligible' value='ME'> ME <br>"
				+ "<input type='checkbox' name='deptEligible' value='PH'> PH <br><br>"
				+ "<input type='text' name='location' value='Mumbai' /> Location <br>"
				+ "<input type='number' step='1000' name='salary' value='10000' /> Salary (INR/month) <br>"
				+ "<input type='text' name='description' value='JAF Description' /> Describe JAF <br>"
				+ "<input type='text' name='profile' value='Tech' /> Profile <br>"
				+ "<input type='submit' value='Verify' />" + "</form>";
		out.println(details);
	}
	else if(option.equals("JAFDetails")){
	String companyID = id;
	int JAFNumber = Integer.parseInt(request.getParameter("jafNum").toString());

	String details = "<table>";
	List<String> JAFDetails = JAF.getJAFDetails(companyID, JAFNumber);
	for (int i = 0; i < JAFDetails.size() / 2; i++)
		details += "<tr><td>" + JAFDetails.get(i * 2) + "</td><td>" + JAFDetails.get(i * 2 + 1) + "</td></tr>";
	details += "</table>";
	details += "<form action='company_result.jsp' method='post'>"
			+ "<input type='text' name='option' value='SelectStu' hidden/>"
			+ "<input type='text' name='jafNum' value='" + JAFNumber + "' hidden/>"
			+ "<input type='submit' value='Select Students' />" + "</form>";
	out.print(details);
	}
	else if(option.equals("SelectStu")){
		//TODO Print Students applies for this
		// Make links to view profile,resume of each
		//Make checkboxws to select them to update stage
		//Make a text box to enter stage # students are selected for
		//Make fxn that called on list of students updates their stage in applies table
		//TODO Advanced Ensure only unplaced students are selectable
	}
	else if(option.equals("View JAFs")){
		List<String> StuList = Coordi.getcJAFs(id);
		int i = 0;

		if (StuList.size() > 0)
		{
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>Name</th>");
			out.print("<th>JAF No.</th>");
			out.print("<th>Stage</th>");
			out.print("<th>Company Name</th>");
			out.print("<th>View Details & Select Students</th>");
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
			out.println("<form action='company_result.jsp' method='post'>"
					+ "<input type='text' name='option' value='JAFDetails' hidden/>"
					+ "<input type='text' name='jafNum' value='" + StuList.get(i * 5 + 1) + "' hidden/>"
					+ "<input type='submit' value='View Details & Select Students' />" + "</form>");
			out.print("</td></tr>");
			i++;
		}

		if (StuList.size() > 0)
			out.print("</table>");
	}
	else if (option.equals("Submit New JAF"))
	{
		String LastDate = request.getParameter("endDate");
		String cpiCutoff = request.getParameter("cpiCutoff");
		String deptEligible[] = request.getParameterValues("deptEligible");
		String location = request.getParameter("location");
		String salary = request.getParameter("salary");
		String profile = request.getParameter("profile");
		String description = request.getParameter("description");
		
		if(LastDate == null || cpiCutoff == null || deptEligible == null || location == null || salary == null || profile == null)
			out.println("<br>Invalid input");
		else
		{
			java.util.Date date = null;
			try
			{
				date = new SimpleDateFormat("yyyy-mm-dd").parse(LastDate);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
				System.err.println(e.getMessage());
			}

			JAF.addNewJAF(id, date, cpiCutoff, deptEligible, location, salary, description, profile);
			
			out.println("<br>JAF Submitted");
		}
	}
	else
	{
		out.print("Invalid Option");
	}
	out.println("</p></center>");
	%>

</body>
</html>