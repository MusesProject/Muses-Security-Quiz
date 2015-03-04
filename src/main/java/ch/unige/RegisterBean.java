package ch.unige;
 
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

 

public class RegisterBean {
 
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String job;
    private int experience;
    private int age;
    private String sex;
    private String confirmpassword;
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	
	public boolean checkavailableEmail(String email){
		
    	List<Employee> list = Employee.findAllEmployees();
        for (int i = 0; i < list.size(); i++) {
            if (email.equalsIgnoreCase(list.get(i).getEmail())) return false;
        }
   	return true;
   	
   }
	
    public void createEmployeeAccount(){
    	
    	if (firstname == "") {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must specify your firstname", null);
            facesContext.addMessage(null, msg);
            return;
        }
   	 
   	 	if (lastname == "") {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must specify your lastname", null);
            facesContext.addMessage(null, msg);
            return;
        }
   	 
   	 	if (email == "") {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must specify an email address", null);
            facesContext.addMessage(null, msg);
            return;
        }
   	 	
   	 	if(!checkavailableEmail(email)){
   	 		FacesContext facesContext = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "This email address is already taken,please provide another email address", null);
           facesContext.addMessage(null, msg);
           return;
   		
   	 	}
   	 	if (job == "") {
           FacesContext facesContext = FacesContext.getCurrentInstance();
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must specify your job", null);
           facesContext.addMessage(null, msg);
           return;
       }
   	  	 
   	 	if (password == "") {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must specify your password", null);
            facesContext.addMessage(null, msg);
            return;
        }
   	 
   	 	if (age == 0) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must specify a valid age", null);
            facesContext.addMessage(null, msg);
            return;
        }
   	 	
   	 if (sex == null) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must specify your sex", null);
         facesContext.addMessage(null, msg);
         return;
     }
   	 
   	 if (confirmpassword == "") {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must confirm your password", null);
            facesContext.addMessage(null, msg);
            return;
        }
   	 
   	 if (confirmpassword == password) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password Must be identique", null);
            facesContext.addMessage(null, msg);
            return;
        }
    	
    	Employee employee = new Employee();
    	
    	
    	employee.setAge(age);
    	employee.setEmail(email);
    	employee.setExperience(experience);
    	employee.setFirstname(firstname);
    	employee.setLastname(lastname);
    	employee.setJob(job);
    	employee.setSex(sex);

    	employee.setPassword(password);
    	employee.persist();
    	
    	System.out.println("3");
    	System.out.println("size: "+Employee.findAllEmployees().size());


    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

    	try {
			context.redirect("../public/loginActivity.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * @return the confirmpassword
	 */
	public String getConfirmpassword() {
		return confirmpassword;
	}
	/**
	 * @param confirmpassword the confirmpassword to set
	 */
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
 
    
}