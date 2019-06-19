//package com.baidu.ocr.demo;
//
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//
//public class DetectCircles {
//
//    public static double radMax=0;
//    public static double radMin=0;
//    public static double rad=0;
//    public static void findCircles(Mat dstImage, Mat circles){
//        Imgproc.HoughCircles(dstImage,circles,Imgproc.CV_HOUGH_GRADIENT,1.5,0);
//    }
//
//    public static void drawCircles(Mat image,Mat circles){
//        radMax = 0;
//        radMin = 0;
//        rad = 0;
//        for (int i = 0; i < circles.cols(); i++)
//        {
//            double vCircle[] = circles.get(0,i);
//            double x = vCircle[0];
//            double y = vCircle[1];
//            double radius = vCircle[2];
//            Core.circle(image, new Point(x,y), (int) radius, new Scalar(0,255,0),3);
//            radMax = (radius>radMax)? radius:radMax;
//            if (i == 0)
//                radMin = radius;
//            radMin = (radius < radMin) ? radius : radMin;
//        }
//    }
//
//    public static double getRad()
//    {
//        rad = 0.5 * (radMax + radMin);
//        return rad;
//    }
//}
