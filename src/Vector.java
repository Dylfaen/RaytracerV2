import com.sun.deploy.config.VerboseDefaultConfig;
import com.sun.javafx.geom.Matrix3f;

import java.util.Objects;

/**
 * Created by cesar on 4/26/16.
 */
public class Vector {
    private Double x;
    private Double y;
    private Double z;

    public Vector(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector setRotation(Vector v, Double angle, Enum<Axis> axis) throws Exception {
        Vector v2;
        if(axis == Axis.X) {
            v2 = new Vector(
                    1 * v.getX() + 0 * v.getY() + 0 * v.getZ(),
                    0 * v.getX() +  Math.cos(angle) * v.getY() + -Math.sin(angle) * v.getZ(),
                    0 * v.getX() +  Math.sin(angle) * v.getY() +  Math.cos(angle) * v.getZ());
        } else if(axis == Axis.Y) {
            v2 = new Vector(
                     Math.cos(angle) * v.getX() + 0 * v.getY() +  Math.sin(angle) * v.getZ(),
                    0 * v.getX() +  1 * v.getY() + 0 * v.getZ(),
                    -Math.sin(angle) * v.getX() +  0 * v.getY() +  Math.cos(angle) * v.getZ());
        } else if(axis == Axis.Z) {

            v2 = new Vector(
                    Math.cos(angle) * v.getX() + -Math.sin(angle) * v.getY() + 0 * v.getZ(),
                    Math.sin(angle) * v.getX() +  Math.cos(angle) * v.getY() + 0 * v.getZ(),
                    0 * v.getX() + 0 * v.getY() + 1 * v.getZ());
        } else {
            throw new Exception("Axis is supported");
        }
        return v2;
    }

    public static Vector add (Vector v1, Vector v2) {
        Vector v3 = new Vector(v1.getX()+v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ());
        return v3;
    }

    public static Vector multiply(Vector v1, Integer scalar){
        return new Vector(v1.getX() * scalar, v1.getY() * scalar, v1.getZ()*scalar);
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
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
