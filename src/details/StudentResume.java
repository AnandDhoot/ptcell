package details;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Student;

/**
 * Servlet implementation class StudentResume
 */
@WebServlet("/StudentResume")
public class StudentResume extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentResume()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession ss = ((HttpServletRequest) request).getSession(false);
		String id = ss.getAttribute("id").toString();
		if (!ss.getAttribute("entity").equals("Student"))
			id = request.getParameter("rollno");

		byte resume[] = Student.getPDF(id);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(resume);
		int fileLength = inputStream.available();

		System.out.println("fileLength = " + fileLength);

		ServletContext context = getServletContext();

		String fileName = id + ".pdf";
		String mimeType = context.getMimeType(fileName);
		if (mimeType == null)
		{
			mimeType = "application/octet-stream";
		}

		response.setContentLength(fileLength);
		response.setContentType(mimeType);
		String headerValue = String.format("attachment; filename=\"%s\"", fileName);
		String headerKey = "Content-Disposition";
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();

		int bytesRead = -1;
		byte[] buffer = new byte[4096];

		while ((bytesRead = inputStream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, bytesRead);
		}

		outStream.close();
		inputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
