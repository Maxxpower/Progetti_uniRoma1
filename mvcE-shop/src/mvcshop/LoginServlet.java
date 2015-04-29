package mvcshop;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * Default constructor. 
     */
	private HashMap<String, String> userDB= new HashMap<String, String>();
	
	
	
    public LoginServlet() {
    
    	userDB.put("francesco","zada#res");
    	
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user=request.getParameter("username");
		String pwd=request.getParameter("password");
		String indirizzo;
		if(pwd.equals(userDB.get(user))){
			indirizzo= "/WEB-INF/eshop.jsp";
			
			
			}else{
				
				indirizzo="/WEB-inf/log-inerror.jsp";
				
			}
		
		
		RequestDispatcher rd=request.getRequestDispatcher(indirizzo);
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
