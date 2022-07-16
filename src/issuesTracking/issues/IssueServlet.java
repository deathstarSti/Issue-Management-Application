package issuesTracking.issues;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import issuesTracking.issues.IssueDAO;
import issuesTracking.issues.Issue;
import issuesTracking.users.UserDAO;
import issuesTracking.users.User;

@WebServlet(urlPatterns = { "/issues/", "/issues/new", "/issues/insert", "/issues/bug", "/issues/feature",
		"/issues/edit", "/issues/update", "/issues/delete" })
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1;
	private IssueDAO issueDAO;
	private UserDAO userDAO;
	private HttpSession session;
	private User user;

	public void init() {
		issueDAO = new IssueDAO();
		userDAO = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession(false);
		System.out.println("Issue Servlet doGet >> ");
		if ((session == null) || (session.getAttribute("user") == null)) {
			System.out.println(" Session does not exist >> Redirecting to Home Page ");
			response.sendRedirect(request.getContextPath());
			return;
		} 
		
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/issues/":
				listIssues(request, response);
				break;
			case "/issues/bug":
				listTypeIssues(request, response, "Bug");
				break;
			case "/issues/feature":
				listTypeIssues(request, response, "Feature");
				break;
			case "/issues/new":
				showNewForm(request, response);
				break;

			case "/issues/insert":
				insertIssue(request, response);
				break;
			case "/issues/delete":
				deleteIssue(request, response);
				break;
			case "/issues/edit":
				showEditForm(request, response);
				break;

			case "/issues/update":
				updateIssue(request, response);
				break;

			default:
				listIssues(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void listIssues(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

			user = (User) session.getAttribute("user");
			List<Issue> listIssues = issueDAO.selectUserIssues(user.getId());
			request.setAttribute("listIssues", listIssues);
			RequestDispatcher dispatcher = request.getRequestDispatcher("issue-list.jsp");
			dispatcher.forward(request, response);


	}

	private void listTypeIssues(HttpServletRequest request, HttpServletResponse response, String type)
			throws SQLException, IOException, ServletException {

		
			user = (User) session.getAttribute("user");
			List<Issue> listIssues = issueDAO.selectUserTypeIssues(user.getId(), type);
			request.setAttribute("listIssues", listIssues);
			RequestDispatcher dispatcher = request.getRequestDispatcher("issue-list.jsp");
			dispatcher.forward(request, response);
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			List<User> listUsers = userDAO.selectAllUsers();
			request.setAttribute("listUsers", listUsers);
			RequestDispatcher dispatcher = request.getRequestDispatcher("issue-form.jsp");
			dispatcher.forward(request, response);
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

			int id = Integer.parseInt(request.getParameter("id"));
			Issue existingIssue = issueDAO.selectIssue(id);

			RequestDispatcher dispatcher = request.getRequestDispatcher("issue-form.jsp");
			List<User> listUsers = userDAO.selectAllUsers();
			request.setAttribute("listUsers", listUsers);
			request.setAttribute("issue", existingIssue);
			dispatcher.forward(request, response);

	}

	private void insertIssue(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

			int createdBy = 0;
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String type = request.getParameter("type");
			User user = (User) session.getAttribute("user");
			int assignedTo = Integer.parseInt(request.getParameter("assignedTo"));
			if (user != null)
				createdBy = user.getId();
			Date today = new Date();

			Issue newIssue = new Issue(title, description, type, today, createdBy, assignedTo);
			issueDAO.insertIssue(newIssue);
			response.sendRedirect(request.getContextPath() + "/issues/");
	}

	private void updateIssue(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {



			int id = Integer.parseInt(request.getParameter("id"));

			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String type = request.getParameter("type");
			int assignedTo = Integer.parseInt(request.getParameter("assignedTo"));
			User user = (User) session.getAttribute("user");
			int editedBy = 0;
			if (user != null)
				editedBy = user.getId();
			Date today = new Date();
			String status = request.getParameter("status");
			Issue updatedIssue = new Issue(id, title, description, type, assignedTo, today, editedBy, status);
			issueDAO.updateIssue(updatedIssue, user.getId());
			response.sendRedirect(request.getContextPath() + "/issues/");
	}

	private void deleteIssue(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

			int id = Integer.parseInt(request.getParameter("id"));
			issueDAO.deleteIssue(id);
			response.sendRedirect(request.getContextPath() + "/issues/");
	}
}