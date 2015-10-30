package results;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JAFResult
 */
@WebServlet("/JAFResult")
public class JAFResult extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JAFResult()
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
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Insert title here</title></head>");
		out.print("<body><center><h3>");
		out.print(request.getParameter("option"));
		out.println("</h3><p>");

		String option = request.getParameter("option");

		if (option == null || option.equalsIgnoreCase(""))
		{
			response.getWriter().append("Invalid input");
			request.getRequestDispatcher("StudentResult").forward(request,
					response);
		}
		else if (option.equals("JAFDetails"))
		{
			out.print(request.getAttribute("JAFDetails").toString());
		}
		else
			out.print("Option not available");
		out.println("</p></center></body></html>");
	}

}
