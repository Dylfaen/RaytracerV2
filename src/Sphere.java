import javafx.geometry.Point3D;

import java.awt.*;

public class Sphere extends Displayable {
    private Point3D center;
    private Float radius;

    public Sphere(Point3D center, Float radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Point3D getIntersectionPoint(Ray ray) {
        Point3D result = null;


            Point3D O = ray.getStartingPoint();
            Vector D = ray.getDirection();

            Vector L = new Vector(O.subtract(this.center));

            Float a = D.dot(D);

        Float b = 2 * D.dot(L);

        Float c = L.dot(L) - (float) Math.pow(this.radius, 2);

            Double delta = Math.pow(b, 2) - 4 * a * c;

            if(delta == 0) {
                Float t = -b / (2 * a);
                Vector D1 = D;
                D1.mul(t);
                D1.add(new Vector(O));
                result = new Point3D(D1.getX(), D1.getY(), D1.getZ());

            } else if(delta > 0) {
                double t1 = (-b + Math.sqrt(delta)) / (2 * a);

                Vector D1 = D;
                Point3D p1 = null;

                if (t1 >= 0) {
                    D1.mul((float) t1);
                    D1.add(new Vector(O));
                    p1 = new Point3D(D1.getX(), D1.getY(), D1.getZ());

                }

                double t2 = (-b - Math.sqrt(delta)) / (2 * a);

                Vector D2 = D;
                Point3D p2 = null;

                if (t2 >= 0) {
                    D2.mul((float) t2);
                    D2.add(new Vector(O));
                    p2 = new Point3D(D2.getX(), D2.getY(), D2.getZ());

                }
                if (p1 != null && p2 != null) {
                    if (new Vector(p1).length() <= new Vector(p2).length()) {
                        result = p1;

                    } else if (new Vector(p1).length() > new Vector(p2).length()) {
                        result = p2;

                    }
                }
            } else {
                return null;
            }

        return result;
    }
}
