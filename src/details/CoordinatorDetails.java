package details;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Coordi;
import database.JAF;
import database.Student;

/**
 * Servlet implementation class CoordinatorDetails
 */
@WebServlet("/CoordinatorDetails")
public class CoordinatorDetails extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CoordinatorDetails()
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
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Insert title here</title></head>");
		out.print("<body><center><h3>");
		out.print(request.getParameter("option"));
		out.println("</h3><p>");
		HttpSession ss = ((HttpServletRequest) request).getSession(true);
		String id = ss.getAttribute("id").toString();
		String pass = ss.getAttribute("pass").toString();
		String option = request.getParameter("option");
		if (option.equals("Verify Students"))
		{

			List<String> StuList = Coordi.getStudents(id);
			int i = 0;

			if (StuList.size() > 0)
			{
				out.print("<table>");
				out.print("<tr>");
				out.print("<th>Roll No.</th>");
				out.print("<th>Name</th>");
				out.print("<th>Department</th>");
				out.print("<th>Status</th>");
				out.print("<th>Profile</th>");
				out.print("</tr>");
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
				out.println("<form action='CoordinatorDetails' method='post'>"
						+ "<input type='text' name='option' value='StudDetails' hidden/>"
						+ "<input type='text' name='rollno' value='" + StuList.get(i * 4) + "' hidden/>"
						+ "<input type='submit' value='View Details' />" + "</form>");
				out.print("</td></tr>");
				i++;
			}

			if (StuList.size() > 0)
				out.print("</table>");
		}
		else if (option.equals("StudDetails"))
		{
			// TODO Put Verify Button
			String sid = request.getParameter("rollno");
			List<String> studentDetails = Student.getStudentDetails(sid);
			String details = "<table>";
			for (int i = 0; i < studentDetails.size() / 2; i++)
				details += "<tr><td>" + studentDetails.get(i * 2) + "</td><td>" + studentDetails.get(i * 2 + 1)
						+ "</td></tr>";
			details += "</table>";
			details += "<form action='CoordinatorDetails' method='post'>"
					+ "<input type='text' name='option' value='VerifyStud' hidden/>"
					+ "<input type='text' name='rollno' value='" + sid + "' hidden/>"
					+ "<input type='submit' value='Verify' />" + "</form>";
			out.print(details);
		}
		else if (option.equals("VerifyStud"))
		{
			String rollno = request.getParameter("rollno").toString();
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
			details += "</table>";
			details += "<form action='CoordinatorDetails' method='post'>"
					+ "<input type='text' name='option' value='VerifyJAF' hidden/>"
					+ "<input type='text' name='CompID' value='" + companyID + "' hidden/>"
					+ "<input type='text' name='jafNum' value='" + JAFNumber + "' hidden/>"
					+ "<input type='submit' value='Verify' />" + "</form>";
			out.print(details);
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
				out.print("<th>Profile</th>");
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
						+ "<input type='submit' value='View Details' />" + "</form>");
				out.print("</td></tr>");
				i++;
			}

			if (StuList.size() > 0)
				out.print("</table>");

		}
		out.println("</p></center></body></html>");
	}

}
