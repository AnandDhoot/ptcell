<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="database.*" %>
<%@ page import="ui.*" %>
<!DOCTYPE html>
<html>
<%=HTMLHeaderUtils.getGenericHeader("Student Result")%>
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
	String id = request.getSession(false).getAttribute("id").toString();
	String option = request.getParameter("option");
	String app=request.getAttribute("approved").toString();
	if (option == null || option.equalsIgnoreCase(""))
	{
		out.print("Invalid input");
	}
	else if (option.equals("Edit Personal Details"))
	{
		out.print(request.getAttribute("EditStudentDetails").toString());
	}
	else if (option.equals("Personal Details"))
	{
		out.print(request.getAttribute("StudentDetails").toString());
	}
	else if (option.equals("Signed JAFs"))
	{	
		if(app.equals("0")){
			request.getRequestDispatcher("student.jsp").forward(request,
					response);
			return;}
		List<String> JAFList = JAF.getSignedJAFs(id);
		int i = 0;
	
		if (JAFList.size() > 0)
		{
			%>
			<table class='centered striped responsive-table sortable'>
				<thead>
				<tr>
				<th>Company Name</th>
				<th>JAF Number</th>
				<th>Status</th>
				<th>Details</th>
				</tr>
				</thead><tbody>
			<%
		}
	
		while (i < JAFList.size() / 4)
		{
			%>
			<tr>
				<td><%=JAFList.get(i * 4)%></td>
				<td><%=JAFList.get(i * 4 + 1)%></td>
				<td><%=JAFList.get(i * 4 + 2)%></td>
				<td>
					<form action='JAFDetails' method='post'>
						<input type='text' name='option' value='JAF Details' hidden/>
						<input type='text' name='CompID' value='<%=JAFList.get(i * 4 + 3)%>' hidden />
						<input type='text' name='jafNum' value='<%=JAFList.get(i * 4 + 1)%>' hidden />
						<button class='btn waves-effect waves-light' type='submit' name='action'>View Details</button>
					</form>
				</td>
			</tr>

			<%
			i++;
		}
	
		if (JAFList.size() > 0)
			out.print("</tbody></table>");
	}
	else if (option.equals("Open JAFs"))
	{	
		if(app.equals("0")){
			request.getRequestDispatcher("student.jsp").forward(request,
					response);return;}
		List<String> JAFList = JAF.getOpenEligibleJAFs(id);
		int i = 0;
	
		if (JAFList.size() > 0)
		{
			%>			
				<table class='centered striped responsive-table sortable'>
					<thead>
					<tr>
					<th>Company Name</th>
					<th>Category</th>
					<th>JAF Number</th>
					<th>End Time</th>
					<th>Profile</th>
					<th>Details</th>
					</tr>
					</thead><tbody>
			<%
		}
	
		while (i < JAFList.size() / 6)
		{
			%>
					<tr>
						<td><%=JAFList.get(i * 6)%></td>
						<td><%=JAFList.get(i * 6 + 1)%></td>
						<td><%=JAFList.get(i * 6 + 2)%></td>
						<td><%=JAFList.get(i * 6 + 3)%></td>
						<td><%=JAFList.get(i * 6 + 4)%></td>
						<td>
							<form action='JAFDetails' method='post'>
								<input type='text' name='option' value='JAF Details' hidden/>
								<input type='text' name='CompID' value='<%=JAFList.get(i * 6 + 5)%>' hidden/>
								<input type='text' name='jafNum' value='<%=JAFList.get(i * 6 + 2)%>' hidden/>
								<button class='btn waves-effect waves-light' type='submit' name='action'>View Details</button>
							</form>
							<form action='StudentResult' method='post'>
								<input type='text' name='option' value='SignJAF' hidden/>
								<input type='text' name='CompID' value='<%=JAFList.get(i * 6 + 5)%>' hidden/>
								<input type='text' name='jafNum' value='<%=JAFList.get(i * 6 + 2)%>' hidden/>
								<button class='btn waves-effect waves-light' type='submit' name='action'>Sign JAF</button>
							</form>
						</td>
					</tr>
					<%
					i++;
				}
			
				if (JAFList.size() > 0)
					out.print("</tbody></table>");
			}
			else if(option.equals("SignJAF")){
				
				String cid=request.getParameter("CompID");
				String jnum= request.getParameter("jafNum");
				Student.applyJAF(id, cid, jnum);
				request.getRequestDispatcher("student.jsp").forward(request, response);
			}
			else
			{
				out.print("Option not supported");
			}
		%>
			</p>
		</div>
	</div>
</div>


</body>
</html>