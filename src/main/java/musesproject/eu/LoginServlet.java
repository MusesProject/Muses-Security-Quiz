package musesproject.eu;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet implements Serializable {
	  
	private String username;
	
	private String password;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AuthenticationManager authenticationManager = null;

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}
	
	
	 public Employee findInstructorbylogin(String login) {
	        List<Employee> list = musesproject.eu.Employee.findAllEmployees();
	        for (int i = 0; i < list.size(); i++) {
	            if (login.equalsIgnoreCase(list.get(i).getEmail())) return list.get(i);
	        }
	        return null;
	  }

	/**
	 * @param authenticationManager the authenticationManager to set
	 */
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
			
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		System.out.println("response : " + response);
		
        //Authentication result = authenticationManager.authenticate(request1);
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
	    FacesContext facesContext = FacesContext.getCurrentInstance();

	    //System.out.println("name: "+request.getUserPrincipal().toString());
	     dispatcher.forward(request, response);
	    
	    //FacesContext.getCurrentInstance().responseComplete();
	    
	    //ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	    	
	    

	    
	   
	    	
	    //FacesContext.getCurrentInstance().responseComplete();

	    //Instructor instructor = findInstructorbylogin(facesContext.getExternalContext().getUserPrincipal().getName());	
	    
		//auth.doLogin();
		
		
		
		/*ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		//ServletContext context = getServletContext();
		
		try{ 
		 Authentication request1 = new UsernamePasswordAuthenticationToken(email, password);
         
         Authentication result = authenticationManager.authenticate(request1);
         
         
         
         SecurityContextHolder.getContext().setAuthentication(result);
         
         if(result.isAuthenticated()){
	            //context.redirect(encodedURL);
        	 System.out.println("tag test 1");
         	//context.redirect("../pages/main.jsf");
	            }else{ 
	            //	context.redirect("../public/loginfailed.jsf");
		            //context.redirect("../public/loginfailed.xhtml");//055712968
	           	 System.out.println("tag test 2");

	            }
         
	    }  catch (AuthenticationException e) {
     	System.out.println("message: "+e.getMessage());
     //	context.redirect("../public/loginfailed.jsf");

	    	return;
	    }
		*/
	    return; 
}
		

/**
 * @return the username
 */
public String getUsername() {
	return username;
}

/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
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

}
