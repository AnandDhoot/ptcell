<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ui.*" %>
<!DOCTYPE html>
<html>
<%
	out.print(HTMLHeaderUtils.getGenericHeader("Coordinator Landing Page"));
%>
<body>
<%
	out.print(HTMLHeaderUtils.getTopNavBar(request.getSession(false).getAttribute("entity").toString()));
%>
<div class='row' style='height:100%'>
	<%=HTMLHeaderUtils.getGenericSidebar(request.getSession(false).getAttribute("entity").toString(), request.getSession(false).getAttribute("name").toString())%>
	<div class='col s8 offset-s0' style="text-align:center">
		<br><br>
		<div class="col s4 offset-s2 card blue darken-3">
			<div class="card-content white-text">
				<span class="card-title"><b>Students</b></span>
				<p>
					Verify allotted students. 
				</p>
			</div>
			<div class="card-action">
				<a href="/ptcell/coordinator_result.jsp?option=Verify+Students" style="margin-right:0px">
					<b>Verify Students</b>
				</a>
			</div>	
		</div>
		
		<div class="col s4 offset-s2 card blue darken-3">
			<div class="card-content white-text">
				<span class="card-title"><b>JAFs</b></span>
				<p>
					Verify allotted JAFs. 
				</p>
			</div>
			<div class="card-action">
				<a href="/ptcell/coordinator_result.jsp?option=Verify+JAFs" style="margin-right:0px">
					<b>Verify JAFs</b>
				</a>
			</div>	
	</div>
</div>
</body>
</html>