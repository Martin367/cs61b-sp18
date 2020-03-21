/**
 *  Tests the Planet.
 */
public class TestPlanet{
    public static void main(String[] args) {
        checkPlanet();
    //    checkPlanetPairForce();
    }

    private static void checkEquals(double expected, double actual, String lable){
        if(expected == actual){
            System.out.println("PASS:"+lable+" Expected:"+expected+" and yours:"+actual);    
        }
        else
            System.out.println("FAIL:"+lable+" Expected:"+expected+" and yours:"+actual);
    }

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and yours " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and yours " + actual);
        }
    }

    private static void checkStringEquals(String expected, String actual, String lable){
        if(expected.equals(actual)){
            System.out.println("PASS:"+lable+" Expected:"+expected+" and yours:"+actual);    
        }
        else
            System.out.println("FAIL:"+lable+" Expected:"+expected+" and yours:"+actual);
    }

    private static void checkPlanet(){
        System.out.println("Checking first Planet constructor...");

        double xxPos = 1.0,
               yyPos = 2.0,
               xxVel = 3.0,
               yyVel = 4.0,
               mass = 5.0;

        String imgFileName = "m78.gif";

        Planet p1 = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        checkEquals(xxPos, p1.xxPos, "xxPos");
        checkEquals(yyPos, p1.yyPos, "yyPos");
        checkEquals(xxVel ,p1.xxVel, "xxVel");
        checkEquals(yyVel, p1.yyVel, "yyVel");
        checkEquals(mass, p1.mass, "mass");
        checkStringEquals(imgFileName, p1.imgFileName, "path to image");

        System.out.println("Checking second Planet constructor...");
        Planet p2 = new Planet(-xxPos,-yyPos,xxVel,yyVel,mass,"-"+imgFileName);
        checkEquals(-xxPos, p2.xxPos, "xxPos");
        checkEquals(-yyPos, p2.yyPos, "yyPos");
        checkEquals(xxVel ,p2.xxVel, "xxVel");
        checkEquals(yyVel, p2.yyVel, "yyVel");
        checkEquals(mass, p2.mass, "mass");
        checkStringEquals("-"+imgFileName, p2.imgFileName, "path to image");

        System.out.println("Checking pairwise force between them...");
        // p1.calcForceExertedBy(p2);
        // DecimalFormat df = new DecimalFormat(".0000");
        // System.out.println(df.format(p1.calcForceExertedBy(p2)));
        checkEquals(6.67*1.0e-11*5.0*5.0/20,p1.calcForceExertedBy(p2),"pairwise force between them",0.0001);
    }

    // private static void checkPlanetPairForce(){
        
    // }
}
