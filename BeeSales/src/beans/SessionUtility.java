package beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtility {

	public static HttpSession getSession() {

		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public HttpServletResponse getResponse() {

		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

}
