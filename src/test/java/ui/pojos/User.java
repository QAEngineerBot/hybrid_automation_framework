package ui.pojos;

public class User {

	private String emailAddress;
	private String password;
	
	public User(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "User [emailAddress=" + emailAddress + ", password=" + password + "]";
	}
	
	
}
