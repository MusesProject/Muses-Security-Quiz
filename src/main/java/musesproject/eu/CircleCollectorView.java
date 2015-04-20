package musesproject.eu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import musesproject.eu.AddMarkersView;

@ManagedBean
@ViewScoped
public class CircleCollectorView implements Serializable{

	private AddMarkersView marker;
	private List<AddMarkersView> markers;
	
	@PostConstruct
    public void init() {
		marker = new AddMarkersView();
		markers = new ArrayList<AddMarkersView>();
         
    }
	
	public void createNew() {
        if(markers.contains(marker)) {
            FacesMessage msg = new FacesMessage("Dublicated");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
        else {
        	markers.add(marker);
        	marker = new AddMarkersView();
        }
    }

	public AddMarkersView getMarker() {
		return marker;
	}

	public List<AddMarkersView> getMarkers() {
		return markers;
	}

	public String reinit() {
		marker = new AddMarkersView();
         
        return null;
    }


	}
