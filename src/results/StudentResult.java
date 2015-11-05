package results;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.JAF;

/**
 * Servlet implementation class StudentResult
 */
@WebServlet("/StudentResult")
public class StudentResult extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentResult()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Insert title here</title></head>");
		out.print("<body><center><h3>");
		out.print(request.getParameter("option"));
		out.println("</h3><p>");
		HttpSession ss = ((HttpServletRequest) request).getSession(false);
		String id = ss.getAttribute("id").toString();
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
				out.print("<table>");
				out.print("<tr>");
				out.print("<th>Company Name</th>");
				out.print("<th>JAF Number</th>");
				out.print("<th>Status</th>");
				// TODO - Authorization to view JAF pages
				out.print("<th>Details</th>");
				out.print("</tr>");
			}

			while (i < JAFList.size() / 4)
			{
				out.print("<tr><td>");
				out.print(JAFList.get(i * 4));
				out.print("</td><td>");
				out.print(JAFList.get(i * 4 + 1));
				out.print("</td><td>");
				out.print(JAFList.get(i * 4 + 2));
				out.print("</td><td>");
				out.print("<form action='JAFDetails' method='post'>"
						+ "<input type='text' name='option' value='JAFDetails' hidden/>"
						+ "<input type='text' name='CompID' value='"
						+ JAFList.get(i * 4 + 3) + "' hidden />"
						+ "<input type='text' name='type' value='"
						+ JAFList.get(i * 4 + 1) + "' hidden />"
						+ "<input type='submit' value='View Details' />"
						+ "</form>");
				out.print("</td></tr>");
				i++;
			}

			if (JAFList.size() > 0)
				out.print("</table>");
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
				out.print("<table border='1'>");
				out.print("<tr>");
				out.print("<th>Company Name</th>");
				out.print("<th>Category</th>");
				out.print("<th>JAF Number</th>");
				out.print("<th>End Time</th>");
				out.print("<th>Profile</th>");
				out.print("<th>Details</th>");
				out.print("</tr>");
			}

			while (i < JAFList.size() / 6)
			{
				out.print("<tr><td>");
				out.print(JAFList.get(i * 6));
				out.print("</td><td>");
				out.print(JAFList.get(i * 6 + 1));
				out.print("</td><td>");
				out.print(JAFList.get(i * 6 + 2));
				out.print("</td><td>");
				out.print(JAFList.get(i * 6 + 3));
				out.print("</td><td>");
				out.print(JAFList.get(i * 6 + 4));
				out.println("</td><td>");
				out.println("<form action='JAFDetails' method='post'>"
						+ "<input type='text' name='option' value='JAFDetails' hidden/>"
						+ "<input type='text' name='CompID' value='"
						+ JAFList.get(i * 6 + 5) + "' hidden/>"
						+ "<input type='text' name='jafNum' value='"
						+ JAFList.get(i * 6 + 2) + "' hidden/>"
						+ "<input type='submit' value='View Details' />"
						+ "</form>");
				i++;
			}

			if (JAFList.size() > 0)
				out.print("</table>");
		}
		else
		{
			out.print("Option not supported");
		}
		out.println("</p></center></body></html>");
	}
}
