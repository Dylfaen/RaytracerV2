import javafx.geometry.Point3D;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        Scene scene = new Scene();
        scene.addDisplayable(new Sphere(new Point3D(-5000.0, 500.0, 1000.0), 500f, Color.green));
        scene.addDisplayable(new Sphere(new Point3D(500.0, 0.0, 4000.0), 700f, Color.red));
        scene.addLightSource(new LightSource(0.0, 50.0, 100.0, Color.white));
        Camera camera = new Camera(new Point3D(.0, .0, -1000), Math.toRadians(0), Math.toRadians(0), Math.toRadians(0), 1000, 1000, scene, 90);
        camera.shoot();
        try {
            camera.print("/home/cesar/Pictures/raytracerV2_rendus/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
