package details;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Coordi;
import database.JAF;

/**
 * Servlet implementation class CoordinatorDetails
 */
@WebServlet("/CoordinatorDetails")
public class CoordinatorDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoordinatorDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Insert title here</title></head>");
		out.print("<body><center><h3>");
		out.print(request.getParameter("option"));
		out.println("</h3><p>");
		String id = request.getParameter("id");
		String option = request.getParameter("option");
		if(option.equals("Verify Students")){
			
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
				out.print("Put Link");
				out.print("</td></tr>");
				i++;
			}

			if (StuList.size() > 0)
				out.print("</table>");
		}
		else if(option.equals("Verify JAFs")){
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
				out.print("Put Link");
				out.print("</td></tr>");
				i++;
			}

			if (StuList.size() > 0)
				out.print("</table>");
			
		}
		out.println("</p></center></body></html>");
	}

}
