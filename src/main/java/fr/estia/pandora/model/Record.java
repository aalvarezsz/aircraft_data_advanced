package fr.estia.pandora.model;


public class Record {
	private double timestamp;
	private double longitude;
	private double latitude;
	private double altitude;
	
	private float roll;
	private float pitch;
	private float yaw;
	private float heading;
	private float u;
	private float v;
	private double air_speed;
	
	private double engine_power;
	private double temperature_in;
	private double humidity_in;
	private double pressure_in;
	
	private double heart_rate;
	private double oxygen_mask;
	
	// Constructor
	public Record() {  }
	
	// Methods
	public double getTimestamp() { return timestamp; }
	public void setTimestamp(double timestamp) { this.timestamp = timestamp; }
	
	public double getLongitude() { return longitude; }
	public void setLongitude(double longitude) { this.longitude = longitude; }
	
	public double getLatitude() { return latitude; }
	public void setLatitude(double latitude) { this.latitude = latitude; }
	
	public double getAltitude() { return altitude; }
	public void setAltitude(double altitude) { this.altitude = altitude; }
	
	public float getRoll() { return roll; }
	public void setRoll(float roll) { this.roll = roll; }
	
	public float getPitch() { return pitch; }
	public void setPitch(float pitch) { this.pitch = pitch; }
	
	public float getYaw() { return yaw; }
	public void setYaw(float yaw) { this.yaw = yaw; }
	
	public float getHeading() { return heading; }
	public void setHeading(float heading) { this.heading = heading; }
	
	public float getU() { return u; }
	public void setU(float u) { this.u = u; }
	
	public float getV() { return v; }
	public void setV(float v) { this.v = v; }
	
	public double getAir_speed() { return air_speed; }
	public void setAir_speed(double air_speed) { this.air_speed = air_speed; }
	
	public double getEnginePower() { return engine_power; }
	public void setEnginePower(double engine) { this.engine_power = engine; }
	
	public double getTemperature_in() { return temperature_in; }
	public void setTemperature_in(double temperature_in) { this.temperature_in = temperature_in; }
	
	public double getHumidity_in() { return humidity_in; }
	public void setHumidity_in(double humidity_in) { this.humidity_in = humidity_in; }
	
	public double getPressure_in() { return pressure_in; }
	public void setPressure_in(double pressure_in) { this.pressure_in = pressure_in; }
	
	public double getHeart_rate() { return heart_rate; }
	public void setHeart_rate(double heart_rate) { this.heart_rate = heart_rate; }
	
	public double getOxygen_mask() { return oxygen_mask; }
	public void setOxygen_mask(double oxygen_mask) { this.oxygen_mask = oxygen_mask; }
	
}
