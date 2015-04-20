package musesproject.eu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;


public class AddMarkersView implements Serializable {
    
   private MapModel emptyModel;
   
   private MapModel polygonModel;
   
   private MapModel circleModel;
     
   private String title;
   
   private int rayon;

   private int rayonSlider;
   
   private String colorPicker;
     
   private double lat;
     
   private double lng;
   
   private String threat = "";
   
   private ArrayList<Point> listpoints  = new ArrayList<Point>();
   
   private ArrayList<Circle> listCircle = new ArrayList<Circle>();
 
   @PostConstruct
   public void init() {
	   emptyModel = new DefaultMapModel();
	   polygonModel = new DefaultMapModel();
       lat = 36.890257;
       lng = 30.707417;
       /*LatLng coord1 = new LatLng(lat, lng);
       Circle circle1 = new Circle(coord1, 500);
       circle1.setStrokeColor("#d93c3c");
       circle1.setFillColor("#d93c3c");
       circle1.setFillOpacity(0.5);
       emptyModel.addOverlay(circle1);*/
   }
   
   /*polygonModel = new DefaultMapModel();
   
   //Shared coordinates
   LatLng coord1 = new LatLng(36.879466, 30.667648);
   LatLng coord2 = new LatLng(36.883707, 30.689216);
   LatLng coord3 = new LatLng(36.879703, 30.706707);*/
     
   public void addMarker() {
	   emptyModel = new DefaultMapModel();



	   Point p = new Point(lat,lng);

	   listpoints.add(p);
	   System.out.println("ok: "+listpoints.size());
	   circleModel = new DefaultMapModel();	   

	   LatLng coord1 = new LatLng(lat, lng);

	   //Point p = new Point(lat,lng);
	   Circle circle1 = new Circle(coord1, rayonSlider);
	   circle1.setStrokeColor("#d93c3c");
	   circle1.setFillColor("#"+threat);
	   circle1.setFillOpacity(0.5);
	   listCircle.add(circle1);

	   System.out.println(listCircle.size());
	   for (int i = 0; i < listCircle.size(); i++) { 
		   circleModel.addOverlay(listCircle.get(i));
	   }



       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Circle Added", "Lat:" + lat + ", Lng:" + lng + " Radius: "+rayonSlider));
   }
   
   public void addCercle() {
	   circleModel = new DefaultMapModel();	   

	   LatLng coord1 = new LatLng(lat, lng);
	   
       //Point p = new Point(lat,lng);
       
       Circle circle1 = new Circle(coord1, rayon);
       circle1.setStrokeColor("#d93c3c");
       circle1.setFillColor("#d93c3c");
       circle1.setFillOpacity(0.5);
       
       circleModel.addOverlay(circle1);
       
       //listpoints.add(p);
	   //System.out.println("ok: "+listpoints.size());

   
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
   }

   
   public void createZone(){
	   polygonModel = new DefaultMapModel();

	   Polygon polygon = new Polygon();
	   System.out.println("ok: "+listpoints.size());
	   for (int i = 0; i < listpoints.size(); i++) {
	        polygon.getPaths().add(new LatLng(listpoints.get(i).getLat(),listpoints.get(i).getLng()));
	        System.out.println("ok");
	     
		
	}
	   polygon.setStrokeColor("#FF9900");
       polygon.setFillColor("#FF9900");
       polygon.setStrokeOpacity(0.7);
       polygon.setFillOpacity(0.7);
         
       polygonModel.addOverlay(polygon);
	   
       listpoints.clear();
	   
	   
   }
   
   
   
   
	/**
	 * @return the polygonModel
	 */
	public MapModel getPolygonModel() {
		return polygonModel;
	}
	
	/**
	 * @param polygonModel the polygonModel to set
	 */
	public void setPolygonModel(MapModel polygonModel) {
		this.polygonModel = polygonModel;
	}

	/**
	 * @return the listpoints
	 */
	public ArrayList<Point> getListpoints() {
		return listpoints;
	}

	/**
	 * @param listpoints the listpoints to set
	 */
	public void setListpoints(ArrayList<Point> listpoints) {
		this.listpoints = listpoints;
	}

	/**
	 * @return the rayon
	 */
	public int getRayon() {
		return rayon;
	}

	/**
	 * @param rayon the rayon to set
	 */
	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	/**
	 * @return the circleModel
	 */
	public MapModel getCircleModel() {
		return circleModel;
	}

	/**
	 * @param circleModel the circleModel to set
	 */
	public void setCircleModel(MapModel circleModel) {
		this.circleModel = circleModel;
	}
	
	  
	   public MapModel getEmptyModel() {
	       return emptyModel;
	   }
	     
	   public String getTitle() {
	       return title;
	   }
	 
	   public void setTitle(String title) {
	       this.title = title;
	   }
	 
	   public double getLat() {
	       return lat;
	   }
	 
	   public void setLat(double lat) {
	       this.lat = lat;
	   }
	 
	   public double getLng() {
	       return lng;
	   }
	 
	   public void setLng(double lng) {
	       this.lng = lng;
	   }
	   public String getColorPicker() {
	        return colorPicker;
	    }
	 
	    public void setColorPicker(String colorPicker) {
	        this.colorPicker = colorPicker;
	    }

		public int getRayonSlider() {
			return rayonSlider;
		}

		public void setRayonSlider(int rayonSlider) {
			this.rayonSlider = rayonSlider;
		}

		public String getThreat() {
			return threat;
		}

		public void setThreat(String threat) {
			this.threat = threat;
		}

		public ArrayList<Circle> getListCircle() {
			return listCircle;
		}

		public void setListCircle(ArrayList<Circle> listCircle) {
			this.listCircle = listCircle;
		}
	    
	    
	    
	    }
	    
	    