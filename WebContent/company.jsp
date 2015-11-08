<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ui.*" %>
<!DOCTYPE html>
<html>
<%
	out.print(HTMLHeaderUtils.getGenericHeader("Company Landing Page"));
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
					<span class="card-title"><b>Add new JAF</b></span>
					<p>
						Create a JAF 
					</p>
				</div>
				<div class="card-action">
					<a href="/ptcell/company_result.jsp?option=Create+JAF" style="margin-right:0px">
						<b>Create JAF</b>
					</a>
				</div>	
			</div>
			
			<div class="col s4 offset-s2 card blue darken-3">
				<div class="card-content white-text">
					<span class="card-title"><b>View JAFs</b></span>
					<p>
						See previously opened JAFs
					</p>
				</div>
				<div class="card-action">
					<a href="/ptcell/company_result.jsp?option=View+JAFs" style="margin-right:0px">
						<b>View Opened JAFs</b>
					</a>
				</div>	
			</div>
	</div>
</div>
</body>
</html>