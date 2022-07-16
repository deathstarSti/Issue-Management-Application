package issuesTracking.users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import issuesTracking.users.UserDAO;
import issuesTracking.users.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1;
	private UserDAO userDAO;
	private HttpSession session;

	public void init() {
		userDAO = new UserDAO();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new-user":
				showNewForm(request, response);
				break;
			case "/check-user":
				checkUser(request, response);
				break;
			case "/insert-user":
				insertUser(request, response);
				break;
			case "/delete-user":
				deleteUser(request, response);
				break;
			case "/edit-user":
				showEditForm(request, response);
				break;
			case "/update-user":
				updateUser(request, response);
				break;
			case "/users":
				listUser(request, response);
				break;
			case "/signout":
				signOut(request, response);
				break;
			default:
				showLogin(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (session != null && request.isRequestedSessionIdValid()) {
			session.invalidate();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		if (session != null && request.isRequestedSessionIdValid()) {
//		    //comes here when session is invalid.   
//			session.invalidate();     
//		}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void checkUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDAO.getUser(username, password);

		if (user != null) {
			session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(300); // in seconds
			response.sendRedirect(request.getContextPath() + "/issues/");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			request.setAttribute("message", "Username and Password not match");

			dispatcher.forward(request, response);

		}
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String password = request.getParameter("password");
		User newUser = new User(username, lastName, firstName, password);
		userDAO.insertUser(newUser);
		response.sendRedirect(request.getContextPath());
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String password = request.getParameter("password");

		User updatedUser = new User(id, username, lastName, firstName, password);
		userDAO.updateUser(updatedUser);
		response.sendRedirect(request.getContextPath()+"/issues/");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect(request.getContextPath());

	}

	private void signOut(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		if (session != null && request.isRequestedSessionIdValid()) {
			session.invalidate();
		}
		response.sendRedirect("");

	}
}