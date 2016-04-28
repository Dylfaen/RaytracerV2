import com.sun.javafx.geom.Vec3f;
import javafx.geometry.Point3D;

import java.util.Objects;

public class Vector extends Vec3f{

    public Vector(Float x, Float y, Float z) {
        super(x, y, z);
    }

    public Vector(Double x, Double y, Double z) {
        super(new Float(x), new Float(y), new Float(z));

    }

    public Vector(Point3D a, Point3D b) {
        this(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ());
    }

    public Vector(Point3D p) {
        this(p.getX(), p.getY(), p.getZ());
    }

    public static Vector setRotation(Vector v, Double angle, Enum<Axis> axis) throws Exception {
        Vector v2;
        if(axis == Axis.X) {
            v2 = new Vector(
                    1. * v.getX() + 0. * v.getY() + 0. * v.getZ(),
                    0. * v.getX() +  Math.cos(angle) * v.getY() + -Math.sin(angle) * v.getZ(),
                    0. * v.getX() +  Math.sin(angle) * v.getY() +  Math.cos(angle) * v.getZ());
        } else if(axis == Axis.Y) {
            v2 = new Vector(
                     Math.cos(angle) * v.getX() + 0. * v.getY() +  Math.sin(angle) * v.getZ(),
                    0. * v.getX() +  1. * v.getY() + 0. * v.getZ(),
                    -Math.sin(angle) * v.getX() +  0. * v.getY() +  Math.cos(angle) * v.getZ());
        } else if(axis == Axis.Z) {

            v2 = new Vector(
                    Math.cos(angle) * v.getX() + -Math.sin(angle) * v.getY() + 0. * v.getZ(),
                    Math.sin(angle) * v.getX() +  Math.cos(angle) * v.getY() + 0. * v.getZ(),
                    0. * v.getX() + 0. * v.getY() + 1. * v.getZ());
        } else {
            throw new Exception("Axis is supported");
        }
        return v2;
    }

    public Float getX() {
        return this.x;
    }

    public Float getY() {
        return this.y;
    }

    public Float getZ() {
        return this.z;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        Boolean result = false;
        if(obj.getClass() == Vector.class) {
            Vector v2 = (Vector) obj;
            if(Objects.equals(this.getX(), v2.getX()) && Objects.equals(this.getY(), v2.getY()) && Objects.equals(this.getZ(), v2.getZ())) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
    }
}
