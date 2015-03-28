package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EshopServlet
 */
@WebServlet("/EshopServlet")
public class EshopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> prodotti = new ArrayList<String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EshopServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {

		prodotti.add("Scarpe");
		prodotti.add("Jeans");
		prodotti.add("Prodottoe");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		creaCarrello(request);
		String user = (String) session.getAttribute("username");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<p>Bentornato " + user
				+ "  Seleziona i Nostri Prodotti</p>");
		out.println("<form method=\"post\" action=\"" + request.getRequestURI()
				+ "\">\n");
		for (String p : prodotti) {

			out.println("<input name=\"compra\"type=\"checkbox\"value=\"" + p
					+ "\"/><label>" + p + "</label>\n");

		}

		out.println("<input type=\"submit\" value=\"Metti nel carrello\"/>\n</form>");
		stampaCarrello(request,out);
		out.close();

	}

	private void creaCarrello(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<String> carrello =(ArrayList<String>)session.getAttribute("kart");
		
		if(carrello==null){
			carrello = new ArrayList<String>();
		}
		String[] acquisti = request.getParameterValues("compra");

		if(acquisti != null){
			for (String a : acquisti) {

				if (!carrello.contains(a)) {

					carrello.add(a);

				}

			}
		
		}	
		if (carrello.size() > 0) {

			session.setAttribute("kart", carrello);

		}

	}
	
	private void stampaCarrello(HttpServletRequest request,PrintWriter out){
		HttpSession session=request.getSession();
		ArrayList<String>carrello=(ArrayList<String>)session.getAttribute("kart");
		
		out.println("Carrello Attuale\n");
		if(carrello!=null)
		for(String s: carrello){
			
			out.println(s+"\n");
			
			
			
		}
		
		
		
		
	}

}
