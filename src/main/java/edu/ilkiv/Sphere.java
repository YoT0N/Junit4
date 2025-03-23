package edu.ilkiv;

/*
  @author Bodya
  @project lab2
  @class Sphere
  version 1.0.0
  @since 22.03.2025 - 22:28
*/


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sphere {

    private double radius;
    private int x;
    private int y;
    private int z;

    public Sphere(double radius, int x, int y, int z) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return Double.compare(radius, sphere.radius) == 0 && x == sphere.x && y == sphere.y && z == sphere.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, x, y, z);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "radius=" + radius +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public double scale(double factor) {
        return radius * factor;
    }

    // Отримати об'єм кулі
    public double getVolume() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
    }

    // Отримати площу кулі
    public double getArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    // Отримати площу перерізу
    // Distance - відстань від центру кулі до січної площини
    public double getSectionArea(double distance) {
        return Math.sqrt(Math.pow(radius, 2) - Math.pow(distance, 2));
    }

    // Довжина кола, яке утворюється перерізом кулі
    public double getIntersectionCircleLength(double planeDistance) {
        if (planeDistance > radius) {
            return 0; // Площина не перетинає сферу
        }

        double circleRadius = Math.sqrt(radius * radius - planeDistance * planeDistance);
        return 2 * Math.PI * circleRadius;
    }

    // Height - довжина перпендикуляра від січення до краю сфери
    // Обчислюється не вся площа сегменту, а тільки зовнішня, тобто без площі розрізу(відрізана частинка кулі)
    public double getAreaOfOuterSurfaceOfSegmentOfSphere(double height) {
        return 2 * Math.PI * radius * height;
    }

    // Отримати об'єм сегмента
    public double getSegmentVolume(double height) {
        return Math.pow(height, 2) * Math.PI / 3 * (3 * radius - height);
    }

    // Отримати площу бічної поверхні фігури, яка утворилась після двох паралельних перерізів сфери
    public double getAreaOfOuterSurfaceOfSpheresCut(double biggerCutRadius, double smallerCutRadius) {
        return 2 * Math.PI * biggerCutRadius * smallerCutRadius;
    }

    public double getSphereCutVolume(double cutHeight, double biggerCutRadius, double smallerCutRadius) {
        return (Math.PI * cutHeight / 6) * (3 * Math.pow(biggerCutRadius, 2) + 3 * Math.pow(smallerCutRadius, 2) + Math.pow(cutHeight, 2));
    }

    // Перевірка положення точки відносно кулі
    public String checkPointPosition(double px, double py, double pz) {
        double distanceSquared = Math.pow(px - x, 2) + Math.pow(py - y, 2) + Math.pow(pz - z, 2);
        double radiusSquared = Math.pow(radius, 2);

        if (distanceSquared < radiusSquared) {
            return "Точка знаходиться всередині кулі";
        } else if (distanceSquared == radiusSquared) {
            return "Точка знаходиться на поверхні кулі";
        } else {
            return "Точка знаходиться поза кулею";
        }
    }

    // Перевірка перетину відрізка з кулею
    public List<double[]> getIntersectionPoints(int x1, int y1, int z1, int x2, int y2, int z2) {
        List<double[]> intersectionPoints = new ArrayList<>();

        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;

        double A = dx * dx + dy * dy + dz * dz;
        double B = 2 * ((x1 - x) * dx + (y1 - y) * dy + (z1 - z) * dz);
        double C = (x1 - x) * (x1 - x) + (y1 - y) * (y1 - y) + (z1 - z) * (z1 - z) - radius * radius;

        double D = B * B - 4 * A * C; // Дискримінант

        if (D < 0) {
            return intersectionPoints; // Немає точок перетину
        }

        if (D == 0) { // Один дотик
            double t = -B / (2 * A);
            if (t >= 0 && t <= 1) {
                double[] point = {x1 + t * dx, y1 + t * dy, z1 + t * dz};
                intersectionPoints.add(point);
            }
            return intersectionPoints;
        }

        // Якщо два перетини
        double t1 = (-B - Math.sqrt(D)) / (2 * A);
        double t2 = (-B + Math.sqrt(D)) / (2 * A);

        if (t1 >= 0 && t1 <= 1) {
            double[] point1 = {x1 + t1 * dx, y1 + t1 * dy, z1 + t1 * dz};
            intersectionPoints.add(point1);
        }

        if (t2 >= 0 && t2 <= 1) {
            double[] point2 = {x1 + t2 * dx, y1 + t2 * dy, z1 + t2 * dz};
            intersectionPoints.add(point2);
        }

        return intersectionPoints;
    }

    // Перевірка чи містить куля в собі повністю іншу кулю
    public boolean containsSphere(Sphere other) {
        double distance = Math.sqrt(Math.pow(this.x - other.x, 2) +
                Math.pow(this.y - other.y, 2) +
                Math.pow(this.z - other.z, 2));
        return distance + other.radius <= this.radius;
    }

    // Перевірка чи кулі перетинаються
    public boolean intersectsWithSphere(Sphere other) {
        double distance = Math.sqrt(Math.pow(this.x - other.x, 2) +
                Math.pow(this.y - other.y, 2) +
                Math.pow(this.z - other.z, 2));
        return distance < (this.radius + other.radius); // Якщо відстань між центрами менша за суму радіусів, то кулі перетинаються.
    }

    // Порахувати відстань між центрами куль
    public double countDistanceBetweenCenters(Sphere other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) +
                Math.pow(this.y - other.y, 2) +
                Math.pow(this.z - other.z, 2));
    }

    // Віддзеркалення точки відносно центра кулі
    public double[] createMirrorPoint(double px, double py, double pz) {
        return new double[]{
                2 * x - px,
                2 * y - py,
                2 * z - pz
        };
    }

    // Чи дотикається площина до сфери
    public boolean isPlaneTangent(double a, double b, double c, double d) {
        // Обчислюємо відстань від центру кулі до площини
        double distance = Math.abs(a * x + b * y + c * z + d) / Math.sqrt(a * a + b * b + c * c);

        // Враховуємо похибку обчислень
        double epsilon = 0.01; // Допустима похибка
        return Math.abs(distance - radius) < epsilon;
    }

    // Визначення точки найкоротшого наближення від довільної точки до поверхні сфери
    public double[] findClosestPointOnSurface(double px, double py, double pz) {
        double dx = px - x;
        double dy = py - y;
        double dz = pz - z;

        double length = Math.sqrt(dx * dx + dy * dy + dz * dz);

        return new double[]{
                x + (dx / length) * radius,
                y + (dy / length) * radius,
                z + (dz / length) * radius
        };
    }

    // Визначення проекції точки на сферу
    public double[] projectPointOntoSphere(double px, double py, double pz) {
        double dx = px - x;
        double dy = py - y;
        double dz = pz - z;

        // Переносить точку на поверхню сфери, якщо вона знаходиться поза або всередині неї.
        double scale = radius / Math.sqrt(dx * dx + dy * dy + dz * dz);

        return new double[]{
                x + dx * scale,
                y + dy * scale,
                z + dz * scale
        };
    }


}
