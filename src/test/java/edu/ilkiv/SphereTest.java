package edu.ilkiv;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/*
  @author Bodya
  @project lab2
  @class SphereTest
  version 1.0.0
  @since 23.03.2025 - 00:59 
*/


public class SphereTest {

    Sphere sphere = new Sphere(10, 0, 0, 0);
    Sphere sphere1 = new Sphere(3, 1 ,0,0);

    @Test
    public void scale() {
        assertEquals(20.0, sphere.scale(2), 0.01);
    }

    @Test
    public void getVolume() {
        assertEquals(4188.79, sphere.getVolume(), 0.01);
    }

    @Test
    public void getArea() {
        assertEquals(1256.63, sphere.getArea(), 0.01);
    }

    @Test
    public void getSectionArea() {
        assertEquals(8.66, sphere.getSectionArea(5.0), 0.01);
    }

    @Test
    public void getIntersectionCircleLength() {
        assertEquals(54.41, sphere.getIntersectionCircleLength(5.0), 0.01);
    }

    @Test
    public void getAreaOfOuterSurfaceOfSegmentOfSphere() {
        assertEquals(314.15, sphere.getAreaOfOuterSurfaceOfSegmentOfSphere(5.0), 0.01);
    }

    @Test
    public void getSegmentVolume() {
        assertEquals(654.49, sphere.getSegmentVolume(5.0), 0.01);
    }

    @Test
    public void getAreaOfOuterSurfaceOfSpheresCut() {
        assertEquals(94.24, sphere.getAreaOfOuterSurfaceOfSpheresCut(5.0, 3.0), 0.01);
    }

    @Test
    public void getSphereCutVolume() {
        assertEquals(951.9, sphere.getSphereCutVolume(6.0, 8.0, 5.0), 0.01);
    }

    @Test
    public void checkPointPosition() {
        assertEquals("Точка знаходиться всередині кулі", sphere.checkPointPosition(5.0, 5.0, 5.0));
    }

    @Test
    public void testGetIntersectionPoints_TwoIntersections() {
        List<double[]> points = sphere.getIntersectionPoints(-20, 0, 0, 20, 0, 0);

        assertEquals(2, points.size());
        assertArrayEquals(new double[]{-10.0, 0.0, 0.0}, points.get(0), 0.01);
        assertArrayEquals(new double[]{10.0, 0.0, 0.0}, points.get(1), 0.01);
    }

    @Test
    public void testGetIntersectionPoints_OneIntersection() {
        List<double[]> points = sphere.getIntersectionPoints(10, -10, 0, 10, 10, 0);

        assertEquals(1, points.size());
        assertArrayEquals(new double[]{10.0, 0.0, 0.0}, points.get(0), 0.01);
    }

    @Test
    public void testGetIntersectionPoints_NoIntersection() {
        List<double[]> points = sphere.getIntersectionPoints(20, 20, 20, 30, 30, 30);

        assertEquals(0, points.size());
    }

    @Test
    public void containsSphere() {
        assertEquals(true, sphere.containsSphere(sphere1));
    }

    @Test
    public void intersectsWithSphere() {
        assertEquals(true, sphere.intersectsWithSphere(sphere1));
    }

    @Test
    public void countDistanceBetweenCenters() {
        assertEquals(1.0, sphere.countDistanceBetweenCenters(sphere1), 0.01);
    }

    @Test
    public void createMirrorPoint() {
        assertArrayEquals(new double[]{-1.0, -1.0, -1.0}, sphere.createMirrorPoint(1,1,1), 0.01);
    }

    @Test
    public void isPlaneTangent() {
        assertEquals(true , sphere.isPlaneTangent(1,0,0,-10));
    }

    @Test
    public void findClosestPointOnSurface() {
        assertArrayEquals(new double[]{10.0, 0.0, 0.0}, sphere.findClosestPointOnSurface(20, 0, 0), 0.01);
    }

    @Test
    public void projectPointOntoSphere() {
        assertArrayEquals(new double[]{10.0, 0.0, 0.0}, sphere.projectPointOntoSphere(20, 0, 0), 0.01);
    }
}