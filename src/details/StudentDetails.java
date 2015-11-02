package details;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Student;

/**
 * Servlet implementation class StudentDetails
 */
@WebServlet("/StudentDetails")
public class StudentDetails extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentDetails()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		HttpSession ss = ((HttpServletRequest) request).getSession(true);
		String id = ss.getAttribute("id").toString();
		String pass = ss.getAttribute("pass").toString();
		String type = request.getParameter("type");
		String option = request.getParameter("option");
		if (option.equals("Personal Details"))
		{
			String details = "<table>";
			List<String> studentDetails = Student.getStudentDetails(id);
			for (int i = 0; i < studentDetails.size() / 2; i++)
				details += "<tr><td>" + studentDetails.get(i * 2) + "</td><td>" + studentDetails.get(i * 2 + 1)
						+ "</td></tr>";
			details += "</table>";
			request.setAttribute("StudentDetails", details);
		}
		else if (option.equals("Edit Personal Details"))
		{
			String details = "<form action='StudentDetails' method='post'>";
			details += "<input type='radio' name='option' value='Update Student Details' checked='checked' hidden/>";
			details += "<input type='text' name='id' value='" + id + "' readonly hidden />";
			details += "<input type='text' name='pass' value='" + pass + "' readonly hidden />";
			details += "<input type='text' name='type' value='" + type + "' readonly hidden />";
			details += "<table>";
			List<String> studentDetails = Student.getStudentDetails(id);
			for (int i = 0; i < studentDetails.size() / 2; i++)
			{
				if (studentDetails.get(i * 2).equalsIgnoreCase("EmailID"))
				{
					details += "<tr><td>Email ID</td><td>";
					details += "<input type='text' name='emailid' value='" + studentDetails.get(i*2+1) + "'/>";
					details += "</td></tr>";
				}
				else if(studentDetails.get(i * 2).equalsIgnoreCase("MobileNumber"))
				{
					details += "<tr><td>Mobile Number</td><td>";
					details += "<input type='text' name='mobile' value='" + studentDetails.get(i*2+1) + "'/>";
					details += "</td></tr>";
				}
				else if(studentDetails.get(i * 2).equalsIgnoreCase("Address"))
				{
					details += "<tr><td>Address</td><td>";
					details += "<input type='text' name='address' value='" + studentDetails.get(i*2+1) + "'/>";
					details += "</td></tr>";
				}
			}
			details += "</table>";
			details += "<input type='submit' value='Update to database' /></form>";
			request.setAttribute("EditStudentDetails", details);
		}
		else if (option.equals("Update Student Details"))
		{
			String email = request.getParameter("emailid");
			String mobile = request.getParameter("mobile");
			String address = request.getParameter("address");
			Student.updateStudentDetails(id, email, mobile, address);
			System.out.println("Updated details");
			request.getRequestDispatcher("student.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("StudentResult").forward(request, response);
	}

}
