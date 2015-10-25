package details;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String id = request.getParameter("id");
		String option = request.getParameter("option");
		if (option.equals("Personal Details"))
		{
			String details = "<table>";
			List<String> studentDetails = Student.getStudentDetails(id);
			for (int i = 0; i < studentDetails.size()/2; i++)
				details += "<tr><td>" + studentDetails.get(i * 2) + "</td><td>"
						+ studentDetails.get(i * 2 + 1) + "</td></tr>";
			details += "</table>";
			request.setAttribute("StudentDetails", details);
		}
		request.getRequestDispatcher("student_result.jsp").forward(request,
				response);
	}

}
