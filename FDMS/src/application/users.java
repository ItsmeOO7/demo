package application;


public class users {
	
	 private String email;
	 private String fullname;
	 private String pass;
	 private String age;
	 private String number;
	
	 
	 public users(String email,String fullname,String pass,String age,String number) {
		 this.email=email;
		 this.fullname=fullname;
		 this.pass=pass;
		 this.age=age;
		 this.number=number;
			
		} 
	 
	public users() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
    
   
    
}