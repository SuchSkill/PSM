package task_03_pendulum;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class Main {
    private static double gravity = 10;
    private static double length = 5;
    private static double dTime = 0.1;
    private static double mass = 0.1;
    private static double startAngle = 45; // in degrees

    public static void main(String[] args) throws InterruptedException, IOException {
        double angleSpeed = 0;
        double alpha = Math.toRadians(startAngle);
        double height = 0;
        double kineticEnergy = 0;
        double potentialEnergy = 0;


        double angleSpeedMid = 0;
        double alphaMid = Math.toRadians(startAngle);
        double heightMid = 0;
        double kineticEnergyMid = 0;
        double potentialEnergyMid = 0;
        double alphaDergee;
        double alphaDergeeMid;


        //Get the file reference
        Path path = Paths.get("euler.txt");
        Path path2 = Paths.get("midpoint.txt");

//Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path);
             BufferedWriter writer2 = Files.newBufferedWriter(path2)
        ) {
            int i=0;
            while (i++<50) {

                alpha = alpha + (angleSpeed * dTime);
//            alphaDergee = Math.toDegrees(alpha);
//            System.out.println("alphaDergee = " + alphaDergee);
                angleSpeed = angleSpeed +
                        ((gravity * -1 / length) * Math.sin(alpha) * dTime);
                kineticEnergy = mass * Math.pow(angleSpeed, 2) * Math.pow(length, 2) / 2.0;
                height = 10 - (length * Math.cos(alpha));
                potentialEnergy = mass * gravity * height;

//            System.out.println("kineticEnergy = " + kineticEnergy);
//            System.out.println("potentialEnergy = " + potentialEnergy);
                System.out.println("------------------");
                System.out.println(potentialEnergy + kineticEnergy);
//
//            System.out.println();


                double out2 = (gravity * -1 / length) * Math.sin(alphaMid);
                double t1 = alphaMid + angleSpeedMid * dTime / 2.0;
                double t2 = angleSpeedMid + out2 * dTime / 2.0;
                double k2 = (gravity * -1 / length) * Math.sin(t1);
                angleSpeedMid = angleSpeedMid + k2 * dTime;
                alphaMid = alphaMid + t2 * dTime;
                alphaDergeeMid = Math.toDegrees(alphaMid);
                System.out.println("alphaDergeeMid = " + alphaDergeeMid);
                kineticEnergyMid = mass * Math.pow(angleSpeedMid, 2) * Math.pow(length, 2) / 2.0;
                heightMid = 10 - (length * Math.cos(alphaMid));
//            System.out.println("heightMid = " + heightMid);
                potentialEnergyMid = mass * gravity * heightMid;

//            System.out.println("kineticEnergy = " + kineticEnergyMid);
//            System.out.println("potentialEnergy = " + potentialEnergyMid);
                System.out.println(potentialEnergyMid + kineticEnergyMid);

                writer.append(""+kineticEnergy + '\t' + potentialEnergy + '\t' + (kineticEnergy + potentialEnergy) + '\n');
                writer2.append(""+kineticEnergyMid + '\t' + potentialEnergyMid + '\t' + (kineticEnergyMid + potentialEnergyMid)+ '\n');
//                writer.
//                Thread.sleep(50);
            }
        }



    }

}
