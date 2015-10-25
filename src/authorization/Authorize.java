package authorization;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.PasswordCheck;

/**
 * Servlet implementation class AuthorizeStudent
 */
@WebServlet("/AuthorizeStudent")
public class Authorize extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authorize()
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
		String userid = request.getParameter("id");
		String password = request.getParameter("pass");
		String type = "Invalid";
		String dept = "Invalid";
		String cpi = "0.0";
		
		if (userid == null || userid.equalsIgnoreCase("") || password == null
				|| password.equalsIgnoreCase(""))
		{
			System.out.println("Invalid input");
		}
		else
		{
			List<String> str = new ArrayList<String>();
			String pass = "";
			if (userid.length() == 9)
			{
				str = PasswordCheck.getStudentPassword(userid);
				pass = str.get(0);
				dept = str.get(1);
				cpi = str.get(2);
				if (pass.equals(password))
				{
					type = "Student";
					System.out.println("Student Authorized");
				}
				else if (!pass.equals(password))
					System.out.println("Invalid Password");
				else
					System.out.println("Invalid User");
			}
			else if (userid.length() == 6)
			{
				pass = PasswordCheck.getCoordinatorPassword(userid);
				if (pass.equals(password))
				{
					type = "Coordinator";
					System.out.println("Coordinator Authorized");
				}
				else if (!pass.equals(password))
					System.out.println("Invalid Password");
				else
					System.out.println("Invalid User");
			}
			else if(userid.length() == 5)
			{
				pass = PasswordCheck.getCompanyPassword(userid);
				if (pass.equals(password))
				{
					type = "Company";
					System.out.println("Company Authorized");
				}
				else if (!pass.equals(password))
					System.out.println("Invalid Password");
				else
					System.out.println("Invalid User");
			}
			else
				System.out.println("Invalid User");
		}
		
		if(!type.equals("Invalid User"))
			request.setAttribute("id", userid);
		request.setAttribute("type", type);
		request.setAttribute("dept", dept);
		request.setAttribute("cpi", cpi);
		if(type.equals("Invalid"))
			request.getRequestDispatcher("index.html").forward(request, response);
		else if(type.equals("Student"))
			request.getRequestDispatcher("student.jsp").forward(request, response);
		else
			request.getRequestDispatcher("student.jsp").forward(request, response);
			
	}

}
