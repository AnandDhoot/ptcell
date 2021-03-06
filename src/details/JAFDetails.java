package details;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JAF;

/**
 * Servlet implementation class JAFDetails
 */
@WebServlet("/JAFDetails")
public class JAFDetails extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JAFDetails()
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
		String option = request.getParameter("option");

		if (option.equals("JAF Details"))
		{
			String companyID = request.getParameter("CompID").toString();
			int JAFNumber = Integer
					.parseInt(request.getParameter("jafNum").toString());
			String details = "<table class='centered striped responsive-table sortable'>";
			details += "<thead><tr><th>Attribute</th><th>Value</th></tr></thead><tbody>";
			List<String> JAFDetails = JAF.getJAFDetails(companyID, JAFNumber);
			for (int i = 0; i < JAFDetails.size() / 2; i++)
				details += "<tr><td>" + JAFDetails.get(i * 2) + "</td><td>"
						+ JAFDetails.get(i * 2 + 1) + "</td></tr>";
			details += "</tbody></table>";
			request.setAttribute("JAF Details", details);
		}
		request.getRequestDispatcher("jaf_result.jsp").forward(request, response);
	}

}
