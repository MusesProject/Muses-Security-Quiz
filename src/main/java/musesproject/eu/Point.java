package musesproject.eu;

public class Point {
	
	private String title;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}

	private double lat;
    
	private double lng;

	public Point() {
		
		 
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param lat
	 * @param lng
	 */
	public Point(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

}
