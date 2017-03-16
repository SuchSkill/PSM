package task_01_sin;

import java.util.Scanner;

/**
 * Created by Eugene on 16-Mar-17.
 */
public class Main {
    public static void main(String[] args) {
        int iterations = Integer.parseInt(args[1]);
        String angleRadString = args[0];

        double angleDeg = Double.parseDouble(angleRadString);

        double angleRad = (Math.PI/180) * angleDeg;
        System.out.println(angleDeg + " dergee in rad = " +angleRad);

        while (angleRad > 2*Math.PI){
            angleRad -= 2*Math.PI;
        }

        double result = angleRad;
        double buff= angleRad;
        int val = 1;

        for (int i = 0; i < iterations ;i++) {
            buff *= angleRad * angleRad / ((val+1)*(val+2));
            if (i % 2 == 0) {
                result -= buff;
            }
            else {
                result += buff;
            }
            val += 2;
            System.out.println("Result after " + (i+1) +" iteration is "+result );
        }
        System.out.println(result);
        System.out.println(Math.sin(angleRad));
    }

}
