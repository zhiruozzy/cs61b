public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

    private static final double G = 6.67e-11;	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
		
	}
	public  double calcDistance(Planet b2){
		double distance = Math.sqrt((b2.xxPos-this.xxPos)*(b2.xxPos-this.xxPos)+(b2.yyPos-this.yyPos)*(b2.yyPos-this.yyPos));
		return distance;
	}
	public double calcForceExertedBy(Planet b2){
		double r = this.calcDistance(b2);
		double Force = (G*this.mass*b2.mass)/(r*r);
		return Force;
	}
	public double calcForceExertedByX(Planet b2){
		double Force = this.calcForceExertedBy(b2);
		double distance = this.calcDistance(b2);
		double distance_x = b2.xxPos - this.xxPos;
		double Force_x = Force * (distance_x/distance);
		return Force_x;
	}
	public double calcForceExertedByY(Planet b2){
		double Force = this.calcForceExertedBy(b2);
		double distance = this.calcDistance(b2);
		double distance_y = b2.yyPos - this.yyPos;
		double Force_y = Force * (distance_y/distance);
		return Force_y;
	}
	public double calcNetForceExertedByX(Planet[] arr){
		double Force_x = 0;
        for(int i=0;i < arr.length; i++){
        	if(!arr[i].equals(this)){
        		double Force = this.calcForceExertedBy(arr[i]);
				double distance = this.calcDistance(arr[i]);
				double distance_x = arr[i].xxPos - this.xxPos;
			    Force_x += Force * (distance_x/distance);
        	}
	        
        }
        return Force_x;
	}
	public double calcNetForceExertedByY(Planet[] arr){
		double Force_y = 0;
        for(int i=0;i < arr.length; i++){
        	if(!arr[i].equals(this)){
        		double Force = this.calcForceExertedBy(arr[i]);
				double distance = this.calcDistance(arr[i]);
				double distance_y = arr[i].yyPos - this.yyPos;
			    Force_y += Force * (distance_y/distance);
        	}
	        
        }
        return Force_y;
	}
	public void update(double dt, double fX, double fY){
		double a_x = fX / this.mass;
		double a_y = fY / this.mass;
		this.xxVel += dt * a_x;
		this.yyVel += dt * a_y;
		this.xxPos += dt * xxVel;
		this.yyPos += dt * yyVel;
	}
	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}
}
