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
		str += "<link rel='stylesheet' href='custom.css'>\n";
		str += "</head>";
		return str;
	}
	public static String getTopNavBar(String entity)
	{
		entity = entity.toLowerCase();
		
		String str = "";
		str += "<div class='navbar-fixed'><nav class='top-nav indigo'><div class='nav-wrapper'>\n"
				+ "<a href='#' class='brand-logo'><img class='responsive-img' src='http://www.ese.iitb.ac.in/icaer2015/images/IITB-logo.png'></a>"
				+ "<ul id='nav-mobile' class='right hide-on-med-and-down'>" 
						+ "<li><a href='/ptcell/" + entity + ".jsp'><i class='material-icons'>home</i></a></li>"
						+ "<li><a href='/ptcell/Logout'><i class='material-icons'>power_settings_new</i></a></li>"
				+ "</ul></div></nav></div>\n";
//		str += "<ul id='slide-out' class='side-nav fixed'>"
//				+ "<li><a href='#!'>First Sidebar Link</a></li>" 
//				+ "<li><a href='#!'>Second Sidebar Link</a></li>"
//				+ "</ul><a href='#' data-activates='slide-out' class='button-collapse'><i class='mdi-navigation-menu'></i></a>\n";
		
		
		return str;
	}
}
