//package task_01_sin;
//
///**
// * Created by Eugene on 16-Mar-17.
// */
//public class Exapmle {
//    #include <iostream>
//    #include <math.h>
//
//    using namespace std;
//    double _2pi = 6.2831;
//    double _pi = 3.1415;
//
//    int main()
//    {
//        cout «"Enter radians" « endl;
//        double rad;
//        cin » rad;
//
//        while (rad >= _2pi) {
//            rad -= _2pi;
//        }
//
//        double result = rad;
//        double tmp=rad;
//        int val = 1;
//        for (int i = 0;i<8;i++) {
//            tmp*=rad*rad/((val+1)*(val+2));
//            if (i % 2 == 0) {
//                result -= tmp;
//            }
//            else {
//                result += tmp;
//            }
//            val += 2;
//            cout«i+1«")"«result«endl;
//        }
//        cout«"Prog sin =" « result « endl;
//        cout«"Math.sin ="«sin(rad)«endl;
//        return 0;
//    }
//}
