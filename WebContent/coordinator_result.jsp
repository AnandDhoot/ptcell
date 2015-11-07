<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="database.*" %>
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
		<div class='col s9 offset-s0'>
			<div class='col s11 offset-s1'>
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
						<table class='centered responsive-table sortable'>
							<thead>
								<tr>
								<th>Roll No.</th>
								<th>Name</th>
								<th>Department</th>
								<th>Status</th>
								<th>Profile</th>
								<th>See Resume</th>
								</tr>
							</thead>
							<%
						}
			
						while (i < StuList.size() / 4)
						{
							%>
								<tr>
									<td> <%=StuList.get(i * 4)%> </td>
									<td> <%=StuList.get(i * 4 + 1)%> </td>
									<td> <%=StuList.get(i * 4 + 2)%> </td>
									<td> <%=StuList.get(i * 4 + 3)%> </td>
									<td>
										<form action='coordinator_result.jsp' method='post'>
									 		<input type='text' name='option' value='StudDetails' hidden/>
											<input type='text' name='rollno' value='<%=StuList.get(i * 4)%>' hidden/>
											<button class='btn waves-effect waves-light' type='submit' name='action'>View Details</button>
										</form>
									</td>
									<td>
										<form action='StudentResume' method='post'>
											<input type='text' name='rollno' value='<%=StuList.get(i * 4)%>' hidden/>
											<button class='btn waves-effect waves-light' type='submit' name='action'>View Resume</button>
										</form>
									</td>
								</tr>
							<%
							i++;
						}
			
						if (StuList.size() > 0)
							out.print("</table>");
					}
					else if (option.equals("StudDetails"))
					{
						String sid = request.getParameter("rollno");
						List<String> studentDetails = Student.getStudentDetails(sid);
						%>
						<table class='centered striped responsive-table sortable'>
							<thead>
								<tr>
									<th> Attribute </th>
									<th> Value </th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < studentDetails.size() / 2; i++)
									{
								%>
								<tr>
									<td><%=studentDetails.get(i * 2)%></td>
									<td><%=studentDetails.get(i * 2 + 1)%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<form action='coordinator_result.jsp' method='post'>
							<input type='text' name='option' value='Verify Student' hidden/>
							<input type='text' name='rollno' value='<%=sid%>' hidden/>
							<button class='btn waves-effect waves-light' type='submit' name='action'>Verify</button>
						</form>
						<%
					}
					else if (option.equals("Verify Student"))
					{
						String rollno = request.getParameter("rollno").toString();
						Student.chgStatus(1, rollno);
						out.print("Verified");
					}
					else if (option.equals("Verify JAF"))
					{
						String companyID = request.getParameter("CompID").toString();
						int JAFNumber = Integer.parseInt(request.getParameter("jafNum").toString());
						JAF.chgJAFStage(1, JAFNumber, companyID);
						out.print("Verified");
					}
					else if (option.equals("JAF Details"))
					{
						String companyID = request.getParameter("CompID").toString();
						int JAFNumber = Integer.parseInt(request.getParameter("jafNum").toString());
						List<String> JAFDetails = JAF.getJAFDetails(companyID, JAFNumber);
					%>
						<table class='centered responsive-table sortable'>
						<thead>
							<tr>
								<th> Attribute </th>
								<th> Value </th>
							</tr>
						</thead>
						<tbody>
					<%
						for (int i = 0; i < JAFDetails.size() / 2; i++)
						{
					%>
							<tr>
								<td> <%=JAFDetails.get(i * 2)%> </td>
								<td> <%=JAFDetails.get(i * 2 + 1)%> </td>
							</tr>
					<%
						}
					%>
						</tbody>
						</table>
							<form action='coordinator_result.jsp' method='post'>
								<input type='text' name='option' value='Verify JAF' hidden/>
								<input type='text' name='CompID' value='<%=companyID%>' hidden/>
								<input type='text' name='jafNum' value='<%=JAFNumber%>' hidden/>
								<button class='btn waves-effect waves-light' type='submit' name='action'>Verify</button>
							</form>
					<%
					}
					else if (option.equals("Verify JAFs"))
					{
						List<String> StuList = Coordi.getJAFs(id);
						int i = 0;
			
						if (StuList.size() > 0)
						{
						%>
							<table class='centered responsive-table sortable'>
							<thead>
								<tr>
								<th>Name</th>
								<th>JAF No.</th>
								<th>Stage</th>
								<th>Company Name</th>
								<th>View Details & Verify</th>
								</tr>
							</thead>
							<tbody>
						<%
						}
			
						while (i < StuList.size() / 5)
						{
						%>
							<tr>
							<td> <%=StuList.get(i * 5)%> </td>
							<td> <%=StuList.get(i * 5 + 1)%> </td>
							<td> <%=StuList.get(i * 5 + 2)%> </td>
							<td> <%=StuList.get(i * 5 + 3)%> </td>
							<td> 
								<form action='coordinator_result.jsp' method='post'>
									<input type='text' name='option' value='JAF Details' hidden/>
									<input type='text' name='CompID' value='<%=StuList.get(i * 5 + 4)%>' hidden/>
									<input type='text' name='jafNum' value='<%=StuList.get(i * 5 + 1)%>' hidden/>
									<button class='btn waves-effect waves-light' type='submit' name='action'>
										<span class="black-text text-darken-2">View Details & Verify</span>
									</button>
								</form>
							</td>
							</tr>
						<%
							i++;
						}
			%>
			<%
						if (StuList.size() > 0)
							out.print("</tbody></table>");
			
					}
			%>
				</p>
			</center>
			</div>
		</div>
		</div>
	</div>
</body>
</html>