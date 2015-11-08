<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ui.*" %>
<!DOCTYPE html>
<html>
<%
	out.print(HTMLHeaderUtils.getGenericHeader("Student Landing Page"));
%>
<body>
<%
	out.print(HTMLHeaderUtils.getTopNavBar(request.getSession(false).getAttribute("entity").toString()));
%>
<div class='row' style='height:100%'>
	<%=HTMLHeaderUtils.getGenericSidebar(request.getSession(false).getAttribute("entity").toString(), request.getSession(false).getAttribute("name").toString())%>
	<div class='col s8 offset-s0' style="text-align:center">
		<br><br>
					
			<% 
				String approved = request.getAttribute("approved").toString();
				if (approved.equals("0"))
			  	{
			%>
					<div class="col s4 offset-s2 card blue darken-3">
						<div class="card-content white-text">
							<span class="card-title"><b>Edit Personal Details</b></span>
							<p>
								Edit Personal Details 
							</p>
						</div>
						<div class="card-action">
							<a href="/ptcell/StudentDetails?option=Edit+Personal+Details" style="margin-right:0px">
								<b>Edit Personal Details</b>
							</a>
						</div>	
					</div>
					
					<div class="col s4 offset-s2 card blue darken-3">
						<div class="card-content white-text">
							<span class="card-title"><b>Resume</b></span>
							<p>
								Upload Resume
							</p>
						</div>
						<div class="card-action">
							<a href="/ptcell/resume_upload.jsp" style="margin-right:0px">
								<b>Upload Resume</b>
							</a>
						</div>	
					</div>
			<% 
				}
			  	else
			  	{
			%>			
					<div class="col s4 offset-s2 card blue darken-3">
						<div class="card-content white-text">
							<span class="card-title"><b>Personal Details</b></span>
							<p>
								View (Frozen) Personal Details 
							</p>
						</div>
						<div class="card-action">
							<a href="/ptcell/StudentDetails?option=Personal+Details" style="margin-right:0px">
								<b>Personal Details</b>
							</a>
						</div>	
					</div>
					
					<div class="col s4 offset-s2 card blue darken-3">
						<div class="card-content white-text">
							<span class="card-title"><b>Signed JAFs</b></span>
							<p>
								View Signed JAFs
							</p>
						</div>
						<div class="card-action">
							<a href="/ptcell/StudentDetails?option=Signed+JAFs" style="margin-right:0px">
								<b>See signed JAFs</b>
							</a>
						</div>	
					</div>
					
					<div class="col s4 offset-s2 card blue darken-3">
						<div class="card-content white-text">
							<span class="card-title"><b>Open JAFs</b></span>
							<p>
								View Open JAFs
							</p>
						</div>
						<div class="card-action">
							<a href="/ptcell/StudentDetails?option=Open+JAFs" style="margin-right:0px">
								<b>See open JAFs</b>
							</a>
						</div>	
					</div>
			<%
				}
			%>
	
			
			<div class="col s4 offset-s2 card blue darken-3">
				<div class="card-content white-text">
					<span class="card-title"><b>Resume</b></span>
					<p>
						Download Resume
					</p>
				</div>
				<div class="card-action">
					<a href="/ptcell/StudentResume" style="margin-right:0px">
						<b>Download Resume</b>
					</a>
				</div>	
			</div>	
			
			<br>
	</div>
</div>
</body>
</html>
