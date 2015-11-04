package authorization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.PasswordCheck;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/AuthFilter")
public class AuthFilter implements Filter
{

	/**
	 * Default constructor.
	 */
	public AuthFilter()
	{

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		String path = ((HttpServletRequest) request).getRequestURI();
		if (path.equals("/ptcell/JAFDetails"))
		{
			chain.doFilter(request, response);
		}
		else
		{
			String userid;
			String password;
			System.out.println(path);
			if (path.equals("/ptcell/AuthRedirect"))
			{
				userid = request.getParameter("id");
				password = request.getParameter("pass");
			}
			else
			{
				HttpSession ss = ((HttpServletRequest) request).getSession(false);
				if(ss.getAttribute("id") == null || ss.getAttribute("pass") == null)
					request.getRequestDispatcher("index.html").forward(request,
							response);
				userid = ss.getAttribute("id").toString();
				password = ss.getAttribute("pass").toString();
			}
			String type = "Invalid";
			if (userid == null || userid.equalsIgnoreCase("") || password == null || password.equalsIgnoreCase(""))
			{
				System.out.println("Invalid input");
			}
			else
			{
				List<String> str = new ArrayList<String>();
				String pass = "";
				String approved = "";
				if (userid.length() == 9)
				{
					str = PasswordCheck.getStudentPassword(userid);
					pass = str.get(0);
					approved = str.get(1);
					if (pass.equals(password))
					{
						type = "Student";
						request.setAttribute("approved", approved);
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
				else if (userid.length() == 5)
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
			request.setAttribute("type", type);
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{

	}

}
