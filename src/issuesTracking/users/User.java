package issuesTracking.users;

public class User {
	protected int id;
	protected String username;
	protected String lastName;
	protected String firstName;
	protected String password;
	
	
	public User() {
		super();
	}


	public User(String username, String lastName, String firstName, String password) {
		super();
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
	}


	public User(int id, String username, String lastName, String firstName, String password) {
		super();
		this.id = id;
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
}
