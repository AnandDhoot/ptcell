package ui;

public class HTMLHeaderUtils
{
	public static String getGenericHeader(String title)
	{
		String str = "";
		str += "<head><title>" + title + "</title>";
//		str += "<link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>\n";
		str += "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css'>\n";
		str += "<link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet'>\n";
		str += "<link rel='stylesheet' href='css/custom.css'>\n";
		str += "<script src='js/sorttable.js'></script>";
		str += "</head>";
		return str;
	}
	public static String getTopNavBar(String entity)
	{
		entity = entity.toLowerCase();
		
		String str = "";
		str += "<div class='navbar-fixed'>"
				+ "<nav class='top-nav indigo darken-3'>"
					+ "<div class='nav-wrapper'>\n"
						+ "<a href='#' class='brand-logo'>"
							+ "<img class='responsive-img' src='http://www1.iitb.ac.in/images/header/iitb_logo.gif'>"
						+ "</a>"
						+ "<ul id='nav-mobile' class='right hide-on-med-and-down'>" 
							+ "<li><a href='/ptcell/" + entity + ".jsp'><i class='material-icons'>home</i></a></li>"
							+ "<li><a href='/ptcell/Logout'><i class='material-icons'>power_settings_new</i></a></li>"
						+ "</ul>"
					+ "</div>"
				+ "</nav>"
			+ "</div>\n";
		return str;
	}
	public static String getGenericSidebar(String entity, String name)
	{
		String str = "";
		str += "<div class='col s2 indigo lighten-4' style='min-height:100%'>" 
				+ "<center>"
				+ "<br><br>"
					+ "<i class='large material-icons'>perm_identity</i>"
					+ "<br>" + name + "<br>" + entity 
					+ "<br><br><br>"
					+ "<h5>Quick links</h5>"					
					+ "<ul>"
						+ "<li><a href='http://iitb.ac.in/'><span class='indigo-text'>IITB Homepage</span></a></li>"
						+ "<li><a href='http://placements.iitb.ac.in/trainingblog/'><span class='indigo-text'>Placement Blog</span></a></li>"
					+ "</ul>"
				+ "</center>"
				+ "</div>";
		return str;	
	}
}
