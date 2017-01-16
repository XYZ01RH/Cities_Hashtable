
public class Cities {
	private String city;
	private Double latitude;
	private Double longitude;
	private final double radEarth = 6373;
	
	public Cities() {}
	
	public Cities(String city, Double lat, Double longi) {
		this.city = city;
		this.latitude = lat;
		this.longitude = longi;
	}

	public String getCity() {
		return this.city;
	}
	
	public Double getLatitude() {
		return this.latitude;
	}
	
	public Double getLongitude() {
		return this.longitude;
	}
	
	public void setCity(String c) {
		this.city = c;
	}
	
	public void setLatitude(Double lat) {
		this.latitude = lat;
	}
	
	public void setLongitude(Double lon) {
		this.longitude = lon;
	}
	
	private double toRadian(Double value) {
		return value * Math.PI / 180;
	}
	
	public double getDistance(Cities to) {
		if(this == null || city == null) {
			return -1;
		}
		
		Double latBetween = toRadian(this.latitude - to.latitude) / 2;
		Double lonBetween = toRadian(this.longitude - to.longitude) / 2;
		
		Double latSinSq = Math.pow(Math.sin(latBetween), 2);
		Double lonSinSq = Math.pow(Math.sin(lonBetween) ,2);
		
		Double latsCos = Math.cos(toRadian(this.latitude)) * Math.cos(toRadian(to.latitude));
		
		Double cosPlusLong = latsCos * lonSinSq;
		Double innerAddition = latSinSq + cosPlusLong;
		
		Double arcSin = Math.asin(Math.sqrt(innerAddition));
		
		Double distance = radEarth * 2 * arcSin;
		
		return distance;
	}
	
	public String toString() {
		return this.getLatitude() + " " + this.getLongitude();
	}
}
