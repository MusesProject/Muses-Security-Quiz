package ch.unige;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CacheControlPhaseListener implements PhaseListener
{
    public PhaseId getPhaseId()
    {
        return PhaseId.RENDER_RESPONSE;
    }

    public void afterPhase(PhaseEvent event)        
    {
    }

    public void check (){
    	
            FacesContext facesContext = FacesContext.getCurrentInstance();
            NavigationHandler navigationHandler =
                facesContext.getApplication().getNavigationHandler();
         
            FacesContext ctx = FacesContext.getCurrentInstance();
            String path = ctx.getExternalContext().getRequestContextPath();
            //System.out.println("xav: "+path +" "+ctx.getExternalContext().getRequestServletPath());
            String path1 = ctx.getExternalContext().getRequestServletPath();
            //System.out.println("xav: "+path +" "+ctx.getExternalContext().getRequestServletPath());
            HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
            //returns something like "/myapplication/home.faces"
            String fullURI = servletRequest.getRequestURI();
           // System.out.println("xav1: "+ fullURI);
           if( FacesContext.getCurrentInstance().isPostback()){
            //navigationHandler.handleNavigation(facesContext, null, "outcome");
           }
    	
    }
    public void beforePhase(PhaseEvent event)
    {
       FacesContext facesContext = event.getFacesContext();
       HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();
       response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       response.setHeader("Pragma", "no-cache");
       NavigationHandler navigationHandler =
           facesContext.getApplication().getNavigationHandler();
       FacesContext ctx = FacesContext.getCurrentInstance();
       String path = ctx.getExternalContext().getRequestContextPath();
       //System.out.println("xav: "+path +" "+ctx.getExternalContext().getRequestServletPath());
       HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
    // returns something like "/myapplication/home.faces"
       String fullURI = servletRequest.getRequestURI();
       //System.out.println("xav1: "+ fullURI);

       String path1 = ctx.getExternalContext().getRequestServletPath();

       // Stronger according to blog comment below that references HTTP spec
      // response.addHeader("Cache-Control", "no-store");
       //response.addHeader("Cache-Control", "must-revalidate");
       //System.out.println("zewuiewuzeuewureuzweruziuwzeuz");
       // some date in the past
       response.setHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
    }
} 