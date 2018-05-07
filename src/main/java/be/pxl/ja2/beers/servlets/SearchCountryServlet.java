package be.pxl.ja2.beers.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import be.pxl.ja2.beers.data.Country;

@WebServlet(name = "SearchCountry", value = "/SearchCountry")
public class SearchCountryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Client client = ClientBuilder.newClient();
		Country country = client.target("https://restcountries.eu/rest/v2/capital/" + req.getParameter("capital"))
				.request(MediaType.APPLICATION_JSON).get(Country[].class)[0];
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		try (PrintWriter out = resp.getWriter()) {
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Hello Servlet Get</h1>");
			out.println("<table>");

			out.println("<tr><td>" + country.getName() + "</td></tr>");
			out.println("<tr><td>" + country.getAlpha2Code() + "</td></tr>");
			out.println("<tr><td>" + country.getNativeName() + "</td></tr>");

			out.println("</table");
			out.println("</body>");
			out.println("</html>");
		}

	}
}
