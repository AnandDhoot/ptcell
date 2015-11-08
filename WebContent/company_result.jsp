<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*" %>
<%@ page import="database.*" %>
<%@ page import="ui.*" %>
<!DOCTYPE html>
<html>
<%=HTMLHeaderUtils.getGenericHeader("Company Result")%>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/js/materialize.min.js"></script>
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
				%>
			<form class='col s12' action='company_result.jsp?option=Submit+New+JAF' method='post'>
			
		      <div class="row">
		        <div class="input-field col s6">
		          <input id="endDate" name='endDate' type="text" class="datepicker">
		          <label for="endDate">JAF End Date</label>
		        </div>
		        <div class="input-field col s6">
		          <input id="location" name='location' type="text" placeholder="Mumbai">
		          <label for="location">Location of Work</label>
		        </div>
		      </div>
		      
		      <div class="row">
		        <div class="input-field col s6">
		          <input id="cpiCutoff" name='cpiCutoff' type="number" step='0.01' placeholder='7.00'>
		          <label for="cpiCutoff">CPI Cutoff</label>
		        </div>
		        <div class="input-field col s6">
		          <input id="salary" name='salary' type="number" step='1000' placeholder='10000'>
		          <label for="salary">CPI Cutoff</label>
		        </div>
		      </div>
		      
		      <div class="row">
		        <div class="input-field col s6">
		          <input id="description" name='description' type="text" placeholder='Brief Description'>
		          <label for="description">Description</label>
		        </div>
		        <div class="input-field col s6">
		          <input id="profile" name='profile' type="text" placeholder='Tech'>
		          <label for="profile">Profile</label>
		        </div>
		      </div>
			  Eligible Departments
		      <div class="row">
				<div class="input-field col s1">
			      <input type="checkbox" id="test1" name='deptEligible' value='AE' />
			      <label for="test1">AE</label>
			    </div>
				<div class="input-field col s1">
			      <input type="checkbox" id="test2" name='deptEligible' value='CS' />
			      <label for="test2">CS</label>
			    </div>
				<div class="input-field col s1">
			      <input type="checkbox" id="test3" name='deptEligible' value='EE' />
			      <label for="test3">EE</label>
			    </div>
				<div class="input-field col s1">
			      <input type="checkbox" id="test4" name='deptEligible' value='ME' />
			      <label for="test4">ME</label>
			    </div>
				<div class="input-field col s1">
			      <input type="checkbox" id="test5" name='deptEligible' value='PH' />
			      <label for="test5">PH</label>
			    </div>
				<div class="input-field col s3 offset-s1">
				 <button class="btn waves-effect waves-light" type="submit" name="action">Create
				 	<i class="material-icons right">send</i>
				  </button>
				 </div>
		    	</div>
			</form>
	<script>

	  $('.datepicker').pickadate({
	    selectMonths: true, // Creates a dropdown to control month
	    selectYears: 15 // Creates a dropdown of 15 years to control year
	  });
	  
	</script>
		<%
	}
	else if(option.equals("JAF Details"))
	{
		String companyID = id;
		int JAFNumber = Integer.parseInt(request.getParameter("jafNum").toString());
	
		String details = "<table class='centered responsive-table sortable'><thead><tr><th>Attribute</th><th>Value</th></tr></thead><tdata>";
		List<String> JAFDetails = JAF.getJAFDetails(companyID, JAFNumber);
		for (int i = 0; i < JAFDetails.size() / 2; i++)
			details += "<tr><td>" + JAFDetails.get(i * 2) + "</td><td>" + JAFDetails.get(i * 2 + 1) + "</td></tr>";
		details += "</tdata></table>";
		details += "<form action='company_result.jsp?option=Select+Students' method='post' style='text-align:center'>"
				+ "<input type='text' name='jafNum' value='" + JAFNumber + "' hidden/>"
				+ "<button class='btn waves-effect waves-light' type='submit' name='action'>Select Students</button>" + "</form>";
		out.print(details);
	}
	else if(option.equals("Select Students"))
	{	
		List<String> StuList = Coordi.getcStudents(id,request.getParameter("jafNum"));
		int i = 0;
		out.print("<form action = 'company_result.jsp?option=StudSelected' method = 'post'>");
		if (StuList.size() > 0)
		{
			out.print("<table class='centered responsive-table sortable'><thead>");
			out.print("<tr>");
			out.print("<th>Roll No.</th>");
			out.print("<th>Name</th>");
			out.print("<th>Department</th>");
			out.print("<th>CPI</th>");
			out.print("<th>Profile</th>");
			out.print("<th>See Resume</th>");
			out.print("<th>Select</th>");
			out.print("</tr></thead>");
		}

			while (i < StuList.size() / 4)
			{
				int stage = applies.getLevel(request.getParameter("jafNum"), id, StuList.get(i*4));
				//System.out.println(stage);
				if(stage != -2){
					out.print("<tr><td>");
					out.print(StuList.get(i * 4));
					out.print("</td><td>");
					out.print(StuList.get(i * 4 + 1));
					out.print("</td><td>");
					out.print(StuList.get(i * 4 + 2));
					out.print("</td><td>");
					out.print(StuList.get(i * 4 + 3));
					out.print("</td><td>");
					%>
					
						<form action='coordinator_result.jsp?option=StudDetails' method='post'>
								<input type='text' name='rollno' value='<%=StuList.get(i * 4) %>' hidden/>
								<button class='btn waves-effect waves-light' type='submit' name='action'>View Details</button></form>
					</td>
					<td>
						<form action='StudentResume' method='post'>
								<input type='text' name='rollno' value='<%=StuList.get(i * 4) %>' hidden/>
								<button class='btn waves-effect waves-light' type='submit' name='action'>View Resume</button></form>
					</td>
					<td>
						<div class="input-field row s1">
					      <input type="checkbox" name='deptEligible' id='<%=StuList.get(i*4) %>' value='<%=StuList.get(i*4) %>' />
					      <label for='<%=StuList.get(i*4) %>'></label>
					    </div>
				    </td></tr>
					<%
					
				}
				i++;
				
			}

			if (StuList.size() > 0)
				out.print("</table>");
			%>
			<br>
					<input type='text' name='jafNum' value='<%=request.getParameter("jafNum").toString()%>' hidden/>
			
			<div class="input-field col s12">
				<div class="input-field col s6 offset-s2">
				      <input type="number" name='stage' id="test5" value='2' />
				      <label for="test5">JAF Stage</label>
				</div>
				<div class="input-field col s4">
					<button class='btn waves-effect waves-light' type='submit' name='action'>Select</button>
				</div>
			</div>
			</form>
			<%
			
	}
	else if(option.equals("StudSelected")){
		String deptEligible[] = request.getParameterValues("deptEligible");	
		
		int status = Integer.parseInt(request.getParameter("stage"));

		String JAFNumber = (request.getParameter("jafNum").toString());
		
		int jafno = Integer.parseInt(JAFNumber);

		for(int i=0; i<deptEligible.length; i++){
			Student.chgAStatus(status, deptEligible[i] , id , JAFNumber);
			JAF.chgJAFStage(status, jafno, id);
		}
		
		response.sendRedirect("company.jsp");
		
	}
	else if(option.equals("View JAFs")){
		List<String> StuList = Coordi.getcJAFs(id);
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
				<th>View Details & Select Students</th>
				</tr>
				</thead><tbody>
			<%
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
			out.println("<form action='company_result.jsp?option=JAF+Details' method='post'>"
					+ "<input type='text' name='jafNum' value='" + StuList.get(i * 5 + 1) + "' hidden/>"
					+ "<button class='btn waves-effect waves-light' type='submit' name='action'>View Details & Select Students</button>" + "</form>");
			out.print("</td></tr>");
			i++;
		}

		if (StuList.size() > 0)
			out.print("</tbody></table>");
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
		
		System.out.println("-----" + LastDate + "-----");
		
		if(LastDate == null || cpiCutoff == null || deptEligible == null || location == null || salary == null || profile == null || description == null)
			out.println("<br>Invalid input");
		else
		{
			java.util.Date date = null;
			try
			{
				date = new SimpleDateFormat("dd MMMM, yyyy").parse(LastDate);
				System.out.println(date);
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
	out.println("</p>");
	%>

</body>
</html>