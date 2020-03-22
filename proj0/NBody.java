/*
*1.why it needs two readLine() in readPlanets() ? maybe there is something wrong in .txt
*2.why not to use foreach to replace planets[i] ?
*
*/
public class NBody{
    public static void main(String[] args) {
        double T = Double.valueOf(args[0]),
              dt = Double.valueOf(args[1]);

        String filename = args[2];
        double universe_radius = readRadius(filename);
        Planet[] planets;
        planets = readPlanets(filename);
        //System.out.println(T+" "+dt);
        
        StdDraw.setScale(-universe_radius, universe_radius);

        /* Clears the drawing window. */
        StdDraw.clear();

        StdDraw.picture(0, 0, "images/starfield.jpg");
        //-universe_radius, universe_radius


        for (Planet p:planets) {
            p.draw();
        }

        StdDraw.enableDoubleBuffering();

        double t = 0;
        while(t<T+1){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0;i<planets.length;i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);                
            }
            //separated just for autograder
            for(int i=0;i<planets.length;i++){
                planets[i].update(dt, xForces[i], yForces[i]);    
            }
            
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p:planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universe_radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}

    }
    public static double readRadius(String path){
        In in = new In(path);

        in.readLine();
        double radius = in.readDouble(); 
        return radius;
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int length = in.readInt();
        
        in.readLine();
        in.readLine();
        // System.out.println((in.readLine()).equals(null));
        // System.out.println(in.readLine());
        Planet[] ps = new Planet[length];
        
        
        // for(Planet p:planets){
        //     p.xxPos = in.readDouble();
        //     p.yyPos = in.readDouble();
        //     p.xxVel = in.readDouble();
        //     p.yyVel = in.readDouble();
        //     p.mass = in.readDouble();
        //     p.imgFileName = in.readString();
        // }
        //     p=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
        //             in.readDouble(),in.readDouble(),in.readString());
        // }
        // planets[0] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
        //             in.readDouble(),in.readDouble(),in.readString());
        // System.out.println(planets[0].imgFileName);

        // planets[1] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
        //             in.readDouble(),in.readDouble(),in.readString());

        // planets[2] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
        //             in.readDouble(),in.readDouble(),in.readString());

        // planets[3] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
        //             in.readDouble(),in.readDouble(),in.readString());

        // planets[4] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
        //             in.readDouble(),in.readDouble(),in.readString());
        for(int i=0;i<length;i++){
            // double xP = in.readDouble();
            // double yP = in.readDouble();
            // double xV = in.readDouble();
            // double yV = in.readDouble();
            // double mass = in.readDouble();
            // String img = in.readString();
        //     System.out.println(xP);
            ps[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
                    in.readDouble(),in.readDouble(),in.readString());
        
        }

        // for(Planet p:planets){
        //     // p = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
        //     //      in.readDouble(),in.readDouble(),in.readString());
        //     double xP = in.readDouble();
        //     double yP = in.readDouble();
        //     double xV = in.readDouble();
        //     double yV = in.readDouble();
        //     double mass = in.readDouble();
        //     String img = in.readString();
        //     p = new Planet(xP,yP,xV,
        //             yV,mass,img);
        
        return ps;
        }
}