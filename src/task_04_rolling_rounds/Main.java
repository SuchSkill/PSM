package task_04_rolling_rounds;

public class Main {
    private static double g = 9.80665;
    private static double dt = 0.2;
    private static double ddt = 0.2;
    private static double alfa = -45.0;
    private static double x0 = 411;
    private static double xx0 = 711;
    private static double aKul;
    private static double aWal;
    private static double sigma;
    private static float r = 16;


    static double theta = 360;  // angle that will be increased each loop
    static double step = 0.1;
    static double theta1 = 360;
    static double step1 = 0.13;

    public static void main(String[] args) {
        alfa = Math.toRadians(alfa);
        aKul = (g * Math.sin(alfa)) / 1.4;
        aWal = (g * Math.sin(alfa)) / 1.5;

        double x = aKul * Math.sin(alfa) * dt;
        double y = aKul * Math.cos(alfa) * dt;
        if (x + x0 < 510) {
            dt += 0.2;
        } else {
            dt = 0.2;
        }

        double xx = aWal * Math.sin(alfa) * ddt;
        double yy = aWal * Math.cos(alfa) * ddt;

        if (xx + xx0 < 810) {
            ddt += 0.2;
        } else {
            ddt = 0.2;
        }

        float z = (float) (x+x0 + r*Math.cos(theta));
        float z1 = (float) (y+x0 + r*Math.sin(theta));//(float)(x+x0),(float)(y+x0),r
        theta-=step;
        if(theta==0){
            theta=360;
        }

        float zz = (float) (xx+xx0 + r*Math.cos(theta1));
        float zz1 = (float) (yy+x0 + r*Math.sin(theta1));//(float)(x+x0),(float)(y+x0),r

        theta1-=step1;
        if(theta1==0){
            theta1=360;
        }

    }
}
