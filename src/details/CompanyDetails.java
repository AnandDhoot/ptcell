package details;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Coordi;
import database.JAF;
import database.applies;

/**
 * Servlet implementation class CompanyDetails
 */
@WebServlet("/CompanyDetails")
public class CompanyDetails extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyDetails()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Insert title here</title></head>");
		out.print("<body><center><h3>");
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
			details += "<form action='CompanyDetails' method='post'>"
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
		details += "<form action='CompanyDetails' method='post'>"
				+ "<input type='text' name='option' value='SelectStu' hidden/>"
				+ "<input type='text' name='jafNum' value='" + JAFNumber + "' hidden/>"
				+ "<input type='submit' value='Select Students' />" + "</form>";
		out.print(details);
		}
		else if(option.equals("SelectStu")){
			//TODO Print Students applies for this
			/*Applies => 
			1 == Applied to JAF
			2 == Shortlisted for test
			3 == Shortlisted for interview
			4 == Selected
			-1 == Rejected by company
			-2 == Rejected becouse of selection in another JAF
			<form action="">
			<input type="checkbox" name="vehicle" value="Bike">I have a bike<br>
			<input type="checkbox" name="vehicle" value="Car">I have a car 
			</form>
			*/
			System.out.println("Inside selectstu");
			
			List<String> appliedStudentList = applies.getStudents() ;
			String details = "<table>";
			for (int i = 0; i < appliedStudentList.size() /3 ; i++)
				details += "<tr><td>" + appliedStudentList.get(i * 3) + "</td><td>" + appliedStudentList.get(i * 3 + 1) + "</td></tr>" + appliedStudentList.get(i * 3 + 2) + "</td></tr>";
			out.print(details);
			details += "<" + "/" + "table>";
			
			// Make links to view profile,resume of each
			
			//Make checkboxes to select them to update stage
			String checkbox  = "<form action=" + "\"" + "\"" + ">"; 
			for(int i=0; i<appliedStudentList.size() / 3; i++ ){
				checkbox += "<input type=\"checkbox\" name=\"student\" value=\"stud\">" + appliedStudentList.get(i*3) + "<br>";
			}
			checkbox += "</form>";
			out.print(checkbox);
			//Make a text box to enter stage # students are selected for
			/*<form action="demo_form.asp">
			First name: <input type="text" name="FirstName" value="Mickey"><br>
			Last name: <input type="text" name="LastName" value="Mouse"><br>
			<input type="submit" value="Submit">
			</form>*/
			
			//Make fn that called on list of students updates their stage in applies table
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
				out.println("<form action='CompanyDetails' method='post'>"
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
		out.println("</p></center></body></html>");
	}

}
