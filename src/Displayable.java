import javafx.geometry.Point3D;

import java.awt.*;

public abstract class Displayable {
    public Color color;
    public abstract Point3D getIntersectionPoint(Ray ray);
}
