package ch.unige;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;



public class AuthenticationBean implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthenticationManager authenticationManager = null;

	
	private String username;
	
	private String password;


  /**
 * @return the authenticationManager
 */
public AuthenticationManager getAuthenticationManager() {
	return authenticationManager;
}

/**
 * @param authenticationManager the authenticationManager to set
 */
public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
}

public String doLogin() throws IOException, ServletException {
	    
	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		try{    
            
            Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            
            Authentication result = authenticationManager.authenticate(request);
            
            
            
            SecurityContextHolder.getContext().setAuthentication(result);
            
            if(result.isAuthenticated()){
	            //context.redirect(encodedURL);
            	context.redirect("../pages/main.jsf");
	            }else{ 
	            //	context.redirect("../public/loginfailed.jsf");
		            //context.redirect("../public/loginfailed.xhtml");//055712968

	            }
            
	    }  catch (AuthenticationException e) {
        	System.out.println("message: "+e.getMessage());
        	context.redirect("../public/loginfailed.jsf");

	    	return null;
	    }
            
	return "pm:success"; 
  }

  public String doLogout() throws IOException, ServletException {
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.invalidateSession();
    ec.redirect("../public/logout.jsf");
    return null;

  }

 

  public String getCurrentLoggedInUserEmail() {
    // org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)
    // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // String nameSpring = user.getUsername();
    FacesContext facesContext = FacesContext.getCurrentInstance();
    String nameJsf = facesContext.getExternalContext().getUserPrincipal().getName();
    String userEmailAddress = nameJsf;
    return userEmailAddress;
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
