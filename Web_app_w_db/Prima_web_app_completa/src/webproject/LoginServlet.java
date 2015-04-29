package webproject;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DbAccessServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//leggiamo username e password dalla richiesta per poi confrontarli con quelli presenti sul db
		
		String user=request.getParameter("username");
		String pwd=request.getParameter("password");
		HttpSession session=request.getSession();
		
		//nuovo oggetto gestoreDB
		GestoreDb g= new GestoreDb();
		ArrayList<String> founded=g.findByName(user);
		
		if(user.equals(founded.get(0)) && pwd.equals(founded.get(1))){
			
			session.setAttribute("username", user);
			RequestDispatcher rs=request.getRequestDispatcher("/WEB-INF/logged.jsp");
			rs.forward(request, response);
			
			
		}else{
			
			RequestDispatcher rs=request.getRequestDispatcher("index.jsp");
			rs.forward(request, response);
			
			
			
		}
		
		
		
		
		
		
		
	}

}
