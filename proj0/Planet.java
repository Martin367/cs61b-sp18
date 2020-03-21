public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67*1.0E-11;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass  = m;
        imgFileName = img;    
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass  = p.mass;
        imgFileName = p.imgFileName;
    }     
    
    double calcDistance(Planet p){
        return Math.sqrt((p.xxPos-xxPos)*(p.xxPos-xxPos)
            +(p.yyPos-yyPos)*(p.yyPos-yyPos));
    }

    double calcForceExertedBy(Planet p){
        return (G*p.mass*mass/
            (calcDistance(p)*calcDistance(p)));
    }

    double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos - xxPos)/
                calcDistance(p);
    }

    double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos - yyPos)/
                calcDistance(p);
    }

    double calcNetForceExertedByX(Planet[] allPlanets){
        int i;
        double res = 0.0;
        for(Planet p : allPlanets ){
            if(this.equals(p)) continue;
            res += calcForceExertedByX(p);
        }
        return res;
    }

    double calcNetForceExertedByY(Planet[] allPlanets){
        int i;
        double res = 0.0;
        for(Planet p : allPlanets){
            if(this.equals(p)) continue;
            res += calcForceExertedByY(p);
        }
        return res;
    }

    void update(double dt, double Fx, double Fy){
        xxVel += dt*Fx/mass;
        yyVel += dt*Fy/mass;
        xxPos += dt*xxVel;
        yyPos += dt*yyVel;
    }

    void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}