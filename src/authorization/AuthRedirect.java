package authorization;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthRedirect
 */
@WebServlet("/AuthRedirect")
public class AuthRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthRedirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = (String) request.getAttribute("type");
		HttpSession ss=request.getSession(true);
		ss.setAttribute("id", request.getParameter("id"));
		ss.setAttribute("pass", request.getParameter("pass"));
		if (type.equals("Invalid"))
			request.getRequestDispatcher("index.html").forward(request,
					response);
		else if (type.equals("Student"))
			request.getRequestDispatcher("student.jsp").forward(request,
					response);
		else if (type.equals("Coordinator")){
			request.getRequestDispatcher("coordinator.jsp").forward(request,
					response);}
		else if (type.equals("Company"))
			request.getRequestDispatcher("company.jsp").forward(request,
					response);
	}

}
