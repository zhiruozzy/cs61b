public class NBody{
    public static double readRadius(String filename){
    	In in = new In(filename);
    	int first = in.readInt();
    	double second = in.readDouble();
    	return second;
    }
    public static Planet[] readPlanets(String filename){
    	In in = new In(filename);
    	int num = in.readInt();
    	Planet[] arr = new Planet[num];
    	in.readDouble();
    	for(int i=0;i<num;i++){
    		//can't arr[i] = in.readDouble,must new object fuzhi to it
    		double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            arr[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
    	}
    	return arr;
    }
    public static void main(String[] args){
    	double T = Double.parseDouble(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String filename = args[2];
    	Planet[] Planets = readPlanets(filename);
    	double radius = readRadius(filename);
        
		StdDraw.enableDoubleBuffering();

    	StdDraw.setScale(-radius,radius);
    	StdDraw.picture(0,0,"images/starfield.jpg");
    	int num = Planets.length;
    	for(int i=0;i < num;i++){
    		Planets[i].draw();
    	}
        double time = 0;
        while(time < T){
        	double[] xForces = new double[num];
        	double[] yForces = new double[num];
        	for(int i=0;i<num;i++){
        		xForces[i]=Planets[i].calcNetForceExertedByX(Planets);
        		yForces[i]=Planets[i].calcNetForceExertedByY(Planets);
        	}
        	for(int i=0;i<num;i++){
        		Planets[i].update(dt,xForces[i],yForces[i]);
        	}
        	StdDraw.picture(0,0,"images/starfield.jpg");
        	for(int i=0;i < num;i++){
    		Planets[i].draw();
    	}
        StdDraw.show();
        StdDraw.pause(10);
        time+=dt;
        }
        StdOut.printf("%d\n", Planets.length);
	    StdOut.printf("%.2e\n", radius);
	    for (int i = 0; i < Planets.length; i++) {
	    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
	                  Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
	                  Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
	    }
    }
} 