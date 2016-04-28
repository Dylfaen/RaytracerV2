import javafx.geometry.Point3D;

import java.awt.*;

public class Ray {
    private Point3D startingPoint;
    private Vector direction;

    public Ray(Point3D staringPoint, Vector direction) {
        this.startingPoint = staringPoint;
        this.direction = direction;
        //this.direction.normalize();
    }

    public Point3D getStartingPoint() {
        return startingPoint;
    }

    public Vector getDirection() {
        return direction;
    }
}
