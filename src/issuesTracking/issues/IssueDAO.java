package issuesTracking.issues;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import issuesTracking.issues.Issue;
import issuesTracking.users.User;

public class IssueDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/issue_tracking_app?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_ISSUES_SQL_ALL = "INSERT INTO issues"
			+ "  (title, description, type, created_at, created_by, assigned_to) VALUES " + " (?, ?, ?, ?, ?, ?);";

	private static final String INSERT_ISSUES_SQL = "INSERT INTO issues"
			+ "  (title, description, type, created_at, created_by) VALUES " + " (?, ?, ?, ?, ?);";
	private static final String SELECT_ISSUES_BY_ID = "select * from issues where id =?";
	private static final String SELECT_ISSUES_BY_USERID = "select * from issues where assigned_to =?";
	private static final String SELECT_ALL_ISSUES = "select * from issues";
	private static final String SELECT_ISSUES = "select * from issues where type=?";
	private static final String SELECT_USER_ISSUES = "select * from issues where (assigned_to is null) or (assigned_to = ?)";
	private static final String DELETE_ISSUES_SQL = "delete from issues where id = ?;";
	private static final String UPDATE_ISSUES_SQL = "update issues set title = ?, description= ?, type=?, assigned_to =?, edited_at =?, edited_by = ?, status=?  where id = ?;";

	public IssueDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}


	public Issue selectIssue(int id) {
		Issue issue = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ISSUES_BY_ID);) {
			preparedStatement.setInt(1, id);
//			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
//				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String type = rs.getString("type");
				int assignedTo = rs.getInt("assigned_to");
				Date createdAt = rs.getDate("created_at");
				Date editedAt = rs.getDate("edited_at");
				int editedBy = rs.getInt("edited_by");
				int createdBy = rs.getInt("created_by");
				String status = rs.getString("status");
				issue = new Issue(id, title, description, type, assignedTo, createdAt, editedAt, editedBy, createdBy,
						status);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return issue;
	}

	public List<Issue> selectUserIssues(int user_id) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Issue> issues = new ArrayList<>();
		
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				Statement statement = connection.createStatement()) {
			String sql = "SELECT * FROM `issues` WHERE (`assigned_to` is null) or (assigned_to = "+user_id+");";
//			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String type = rs.getString("type");
				int assignedTo = rs.getInt("assigned_to");
				Date createdAt = rs.getDate("created_at");
				Date editedAt = rs.getDate("edited_at");
				int editedBy = rs.getInt("edited_by");
				int createdBy = rs.getInt("created_by");
				String status = rs.getString("status");
				issues.add(new Issue(id, title, description, type, assignedTo, createdAt, editedAt, editedBy, createdBy,
						status));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}

		return issues;
	}
	public List<Issue> selectUserTypeIssues(int user_id, String type) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Issue> issues = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				Statement statement = connection.createStatement()) {
			String sql = "SELECT * FROM `issues` WHERE ((`assigned_to` is null) or (assigned_to = "+user_id+")) AND (type = '"+type+"') ;";
//			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
				

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
//				String type = rs.getString("type");
				int assignedTo = rs.getInt("assigned_to");
				Date createdAt = rs.getDate("created_at");
				Date editedAt = rs.getDate("edited_at");
				int editedBy = rs.getInt("edited_by");
				int createdBy = rs.getInt("created_by");
				String status = rs.getString("status");
				issues.add(new Issue(id, title, description, type, assignedTo, createdAt, editedAt, editedBy, createdBy,
						status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return issues;
	}


	public void insertIssue(Issue issue) throws SQLException {

			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ISSUES_SQL_ALL)) {
				preparedStatement.setString(1, issue.getTitle());
				preparedStatement.setString(2, issue.getDescription());
				preparedStatement.setString(3, issue.getType());
				preparedStatement.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
				preparedStatement.setInt(5, issue.getCreatedBy());
				if (issue.getAssignedTo() > 0)
					preparedStatement.setInt(6, issue.getAssignedTo());
				else
					preparedStatement.setNull(6, Types.INTEGER);
				//preparedStatement.setInt(6, issue.getAssignedTo());
//				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}

	}

	

	public boolean updateIssue(Issue issue, int user_id) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ISSUES_SQL);) {
			statement.setString(1, issue.getTitle());
			statement.setString(2, issue.getDescription());
			statement.setString(3, issue.getType());
			if (issue.getAssignedTo() > 0)
				statement.setInt(4, issue.getAssignedTo());
			else
				statement.setNull(4, Types.INTEGER);
			statement.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
			statement.setInt(6, user_id);
			statement.setString(7, issue.getStatus());
			statement.setInt(8, issue.getId());
//			System.out.println(statement);

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean deleteIssue(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ISSUES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
