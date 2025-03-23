package edu.ilkiv;

/*
  @author Il'kiv Bodya
  @project lab2
  @class Main
  version 1.0.0
  @since 22.03.2025 - 22:35
*/


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Sphere sphere = new Sphere(5, 0 ,0,0);
        Sphere sphere1 = new Sphere(3, 1 ,0,0);

        // Збільшую радіус кулі в 2 рази
        sphere.setRadius(sphere.scale(2));
        System.out.println("Sphere radius: " + sphere.getRadius());

        System.out.println("Sphere volume: " + sphere.getVolume());
        System.out.println("Sphere area: " + sphere.getArea());
        System.out.println("Section area, cut in 5.0 from center: " + sphere.getSectionArea(5.0));
        System.out.println("Circle length, cut in 5.0 from center: " + sphere.getIntersectionCircleLength(5.0));
        System.out.println("Outer surface area of sphere, cut in 5.0 from center: " + sphere.getAreaOfOuterSurfaceOfSegmentOfSphere(5.0));
        System.out.println("Segment volume, cut in 5.0 from center: " + sphere.getSegmentVolume(5.0));
        System.out.println("Surface area after 2 parallel cuts, bigger cut is 5.0, and smaller is 3.0: " + sphere.getAreaOfOuterSurfaceOfSpheresCut(5.0, 3.0));
        System.out.println("Volume of sphere after 2 cuts: " + sphere.getSphereCutVolume(6.0, 8.0, 5.0));
        System.out.println("Point (5,5,5): " + sphere.checkPointPosition(5.0, 5.0, 5.0));
        System.out.println("Sphere contains sphere1: " + sphere.containsSphere(sphere1));
        System.out.println("Sphere intersects with sphere1: " + sphere.intersectsWithSphere(sphere1));
        System.out.println("Distance between sphere and sphere1: " + sphere.countDistanceBetweenCenters(sphere1));

        double[] mirrorPoint = sphere.createMirrorPoint(1.0,1.0,1.0);
        System.out.println("Mirror point to point(1,1,1) in sphere: " + mirrorPoint[0] + ", " + mirrorPoint[1] + ", " + mirrorPoint[2]);
        System.out.println("Plane is tangent to sphere: " + sphere.isPlaneTangent(1,0,0,-10));

        double[] closestPoint = sphere.findClosestPointOnSurface(20, 0, 0);
        System.out.println("Closest point on sphere surface to point(: " + closestPoint[0] + ", " + closestPoint[1] + ", " + closestPoint[2]);

        double[] projectionPoint = sphere.projectPointOntoSphere(20, 0, 0);
        System.out.println("Project point on sphere surface to point(: " + projectionPoint[0] + ", " + projectionPoint[1] + ", " + projectionPoint[2]);
        List<double[]> pointss = new ArrayList<>();
        List<double[]> points = sphere.getIntersectionPoints(-20, 0, 0, 20, 0, 0);
        if (points.isEmpty()) {
            System.out.println("Відрізок не перетинає кулю.");
        } else {
            System.out.println("Точки перетину:");
            for (double[] p : points) {
                System.out.printf("(%.2f, %.2f, %.2f)\n", p[0], p[1], p[2]);
            }
        }




    }
}