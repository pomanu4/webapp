package usrer.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usrer.jdbc.Connector;

@WebServlet(urlPatterns = "/Servlet22")
public class Servlet22 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/pages/page2.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String personName = request.getParameter("personName");
		String id = request.getParameter("id");

		Connector connector = new Connector();
		connector.saveToDb(personName, id);
		request.getRequestDispatcher("/WEB-INF/pages/page3.jsp").forward(request, response);
	}

}
