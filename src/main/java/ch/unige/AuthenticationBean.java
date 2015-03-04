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
	  /*ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	    RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
	    FacesContext facesContext = FacesContext.getCurrentInstance();

	    dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
	    FacesContext.getCurrentInstance().responseComplete();
	   // HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		//session.setAttribute("islogged", true);
	 
		return "login";
	  
	  System.out.println("Please enter your username:");
      System.out.println("Please enter your password:");
     
      
      Authentication authentication = SecurityContextHolder.
              getContext().getAuthentication();
      
        Authentication request = new UsernamePasswordAuthenticationToken("xavier", "titi");
        Authentication result = am.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
        ;
      
    System.out.println("Successfully authenticated. Security context contains: " +
              SecurityContextHolder.getContext().getAuthentication());
 
	  ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();  
      HttpServletRequest request = ((HttpServletRequest)context.getRequest());
            
      ServletResponse resposnse = ((ServletResponse)context.getResponse());
      RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
      dispatcher.forward(request, resposnse);
      FacesContext.getCurrentInstance().responseComplete();
      System.out.println("test");
      
      return "login";
	  
	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
    FacesContext facesContext = FacesContext.getCurrentInstance();

    dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
    FacesContext.getCurrentInstance().responseComplete();*/
	  

	   /*ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	   RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
	   System.out.println(context.getRequest().toString());
	   System.out.println(context.getResponse().toString());

	   
	   dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
	   FacesContext.getCurrentInstance().responseComplete();*/
	    
	        //HttpServletRequest req = (HttpServletRequest) context.getRequest();

	        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
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
            
	       	        
	        //dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
	        
	       // try {
	            //HttpSession session = req.getSession(true);
	           // System.out.println("ok: "+result.getName());
	            //System.out.println("ok: "+result.isAuthenticated());
	            //System.out.println("ko: "+request.isAuthenticated());


	           /* String cp = context.getRequestContextPath();
	            String redirectUrl = cp;

	            //performing some filtering depending on Roles and target-urls
	            
	            
	            String encodedURL = context.encodeResourceURL(redirectUrl);
	            System.out.println("URL:"+redirectUrl);

	            System.out.println("URL:"+encodedURL);
	            if(result.isAuthenticated()){
	            //context.redirect(encodedURL);
	            	return "pm:success"; 
	            }else{
	            	return "pm:success"; 

		            //context.redirect("../public/loginfailed.xhtml");//055712968

	            }
	           // FacesContext.getCurrentInstance().responseComplete();
	            } catch (AuthenticationException ae) {
	           // UtilBean.addErrorMessage("bad_credential");
	        }*/ 
	        
	return "pm:success"; //CONSEILLER JURIDIQUE, députés locaux,
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
