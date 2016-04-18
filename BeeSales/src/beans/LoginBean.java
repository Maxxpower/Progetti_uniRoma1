package beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2842836001846092599L;
	private String userName;
	private String passwd;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String loginUser() {

		UserManager um = new UserManager();

		if (um.validateLogin(userName, passwd)) {

			HttpSession session = SessionUtility.getSession();
			session.setAttribute("user", userName);
			return ("/sales.xhtml?faces-redirect=true");

		} else {

			FacesMessage errorMessage = new FacesMessage("I dati inseriti non sono validi");
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);

			return null;

		}

	}

	public String logoutUser() {

		HttpSession session = SessionUtility.getSession();
		if(session!=null){
			
			session.invalidate();
		}

		return "/login.xhtml?faces-redirect=true";

	}

}
