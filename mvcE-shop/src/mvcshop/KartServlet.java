package mvcshop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class KartServlet
 */
@WebServlet("/kart-Servlet")
public class KartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] acquisti=request.getParameterValues("aggiungi");
		HttpSession session=request.getSession();
		
		if(session.getAttribute("carrello")==null){
			Carrello c= new Carrello();
			
			for(String s: acquisti){
				
				c.aggiunggiProdotto(s);
			}
			
			session.setAttribute("carrello", c);
			
			}else{
				
				
				Carrello c= (Carrello)session.getAttribute("carrello");
				
				for (String s: acquisti){
					
					
					c.aggiunggiProdotto(s);
				}
				
				
				
				
			}
		

		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/eshop.jsp");
		rd.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
