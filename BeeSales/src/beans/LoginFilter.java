package beans;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginFilter", urlPatterns = { "*.xhtml" })
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		HttpSession session = req.getSession(false);
		String loginUrl = req.getContextPath() + "/login.xhtml";
		boolean loginRequest = req.getRequestURI().equals(loginUrl);
		boolean resourceRequest = req.getRequestURI()
				.startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");

		if (resourceRequest || loginRequest || (session != null && session.getAttribute("user") != null)) {

			if (!resourceRequest) {

				resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1																		// 1.1.
				resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				resp.setDateHeader("Expires", 0);
			}

			arg2.doFilter(arg0, arg1);

		} else {

			resp.sendRedirect(loginUrl);

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
