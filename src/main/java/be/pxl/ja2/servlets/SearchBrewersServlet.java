package be.pxl.ja2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.pxl.ja2.dao.BrewerDao;
import be.pxl.ja2.dao.EntityManagerUtil;
import be.pxl.ja2.data.Brewer;


@WebServlet(name = "SearchBrewers", value = "/SearchBrewers")
public class SearchBrewersServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = EntityManagerUtil.createEntityManager();
		BrewerDao brewerDao = new BrewerDao(em);
		System.out.println("BrewerDao: " + brewerDao);
		String city = req.getParameter("city");
		List<Brewer> brewers = brewerDao.findByCity(city);
		em.close();
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		try (PrintWriter out = resp.getWriter()) {
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Hello Servlet Get</h1>");
			out.println("<table>");
			for (Brewer brewer : brewers) {
				out.println("<tr><td>" + brewer.getName() + "</td></tr>");
			}

			out.println("</table");
			out.println("</body>");
			out.println("</html>");
		} 
	}
}
