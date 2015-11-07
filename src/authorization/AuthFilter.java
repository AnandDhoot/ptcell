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
		if (path.equals("/ptcell/")||path.equals("/ptcell/index.html"))
		{
			chain.doFilter(request, response);
		}
		else
		{
			String userid;
			String password;
			int flag=0;
			HttpSession ss = ((HttpServletRequest) request).getSession(false);
			if (path.equals("/ptcell/AuthRedirect"))
			{
				userid = request.getParameter("id");
				password = request.getParameter("pass");
			}
			else
			{

				if(ss.getAttribute("id") == null || ss.getAttribute("pass") == null)
					request.getRequestDispatcher("index.html").forward(request,
							response);
				flag=1;
				userid = ss.getAttribute("id").toString();
				password = ss.getAttribute("pass").toString();
			}
			String type = "Invalid";
			String name = "Invalid";
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
						name = str.get(2);
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
					str = PasswordCheck.getCoordinatorPassword(userid);
					pass = str.get(0);
					if (pass.equals(password))
					{
						type = "Coordinator";
						name = str.get(1);
						System.out.println("Coordinator Authorized");
					}
					else if (!pass.equals(password))
						System.out.println("Invalid Password");
					else
						System.out.println("Invalid User");
				}
				else if (userid.length() == 5)
				{
					str = PasswordCheck.getCompanyPassword(userid);
					pass = str.get(0);
					if (pass.equals(password))
					{
						type = "Company";
						name = str.get(1);
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
			if(flag==1)
			{
				ss.setAttribute("entity", type);
				ss.setAttribute("name", name);
			}
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
