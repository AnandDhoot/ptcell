package details;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.*;
import database.Student;

/**
 * Servlet implementation class StudentDetails
 */
@WebServlet("/StudentDetails")
public class StudentDetails extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentDetails()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		HttpSession ss = ((HttpServletRequest) request).getSession(false);
		if(!ss.getAttribute("entity").equals("Student"))
		{
			request.getRequestDispatcher("/Logout").forward(request,
					response);
			return;}
		String id = ss.getAttribute("id").toString();
		String type = request.getParameter("type");
		String app=request.getAttribute("approved").toString();
		String option = request.getParameter("option");
		System.out.println(option);
		if (option.equals("Personal Details"))
		{
			String details = "<table>";
			List<String> studentDetails = Student.getStudentDetails(id);
			for (int i = 0; i < studentDetails.size() / 2; i++)
				details += "<tr><td>" + studentDetails.get(i * 2) + "</td><td>" + studentDetails.get(i * 2 + 1)
						+ "</td></tr>";
			details += "</table>";
			request.setAttribute("StudentDetails", details);
		}
		else if (option.equals("Resume Upload")){
			System.out.println("Enter");
			if(app.equals("0")){
				request.getRequestDispatcher("student.jsp").forward(request,
						response);
				return;}
			PrintWriter out = response.getWriter();
			out.print("<html><head><title>Insert title here</title></head>");
			out.print( "<center><form action='StudentDetails?option=Store+PDF' method='post' enctype='multipart/form-data'>"+
			"<b>Upload Resume</b>"+
		       "<input type='file' name='file'/></center>"+
		      "<center> <input type='submit' /></center></form></html>");
			return;
		}
		else if (option.equals("Store PDF")){
			System.out.println("Dragon");
			if(app.equals("0")){
				request.getRequestDispatcher("student.jsp").forward(request,
						response);
				return;}
		    try
		    {
		      List<FileItem> localList = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		      for (FileItem localFileItem : localList)
		      {
		        String str1 = localFileItem.getFieldName();
		        String str2 = FilenameUtils.getName(localFileItem.getName());
		        System.out.println("fieldname:" + str1);
		        System.out.println("filename:" + str2);
		        ByteArrayOutputStream output = new ByteArrayOutputStream();
		        IOUtils.copy(localFileItem.getInputStream(), output);
		        byte[] filecontent = output.toByteArray();
		        Student.insertPDF(id,filecontent);
		      }

		    }
		    catch (FileUploadException localFileUploadException)
		    {
		      throw new ServletException("Cannot parse multipart request.", localFileUploadException);
		    }
			request.getRequestDispatcher("student.jsp").forward(request,
					response);
			
		}
		else if (option.equals("Edit Personal Details"))
		{	
			if(app.equals("1")){
				request.getRequestDispatcher("student.jsp").forward(request,
						response);
				return;}
			String details = "<form action='StudentDetails' method='post'>";
			details += "<input type='radio' name='option' value='Update Student Details' checked='checked' hidden/>";
			details += "<input type='text' name='type' value='" + type + "' readonly hidden />";
			details += "<table>";
			List<String> studentDetails = Student.getStudentDetails(id);
			for (int i = 0; i < studentDetails.size() / 2; i++)
			{
				if (studentDetails.get(i * 2).equalsIgnoreCase("EmailID"))
				{
					details += "<tr><td>Email ID</td><td>";
					details += "<input type='text' name='emailid' value='" + studentDetails.get(i*2+1) + "'/>";
					details += "</td></tr>";
				}
				else if(studentDetails.get(i * 2).equalsIgnoreCase("MobileNumber"))
				{
					details += "<tr><td>Mobile Number</td><td>";
					details += "<input type='text' name='mobile' value='" + studentDetails.get(i*2+1) + "'/>";
					details += "</td></tr>";
				}
				else if(studentDetails.get(i * 2).equalsIgnoreCase("Address"))
				{
					details += "<tr><td>Address</td><td>";
					details += "<input type='text' name='address' value='" + studentDetails.get(i*2+1) + "'/>";
					details += "</td></tr>";
				}
			}
			details += "</table>";
			details += "<input type='submit' value='Update to database' /></form>";
			request.setAttribute("EditStudentDetails", details);
		}
		else if (option.equals("Update Student Details"))
		{
			String email = request.getParameter("emailid");
			String mobile = request.getParameter("mobile");
			String address = request.getParameter("address");
			Student.updateStudentDetails(id, email, mobile, address);
			System.out.println("Updated details");
			request.getRequestDispatcher("student.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("StudentResult").forward(request, response);
	}

}
