import javafx.geometry.Point3D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Camera {
    private static final Vector defaultRotationX = new Vector(1.0, 0.0, 0.0);
    private static final Vector defaultRotationY = new Vector(0.0, 1.0, 0.0);
    private static final Vector defaultDirectionZ = new Vector(0.0, 0.0, 1.0);

    private double distance;
    private Point3D position;
    private Double rotationX;
    private Double rotationY;
    private Double rotationZ;
    private Screen screen;
    private Scene scene;
    private BufferedImage picture;

    public Camera(Point3D position, Double rotationX, Double rotationY, Double rotationZ, Integer sizeX, Integer sizeY, Scene scene, double FOV) {
        this.distance = sizeX/2 / Math.sin(Math.toRadians(FOV));
        this.position = position;
        this.rotationX = rotationX;
        this.rotationY = rotationY;
        this.rotationZ = rotationZ;
        this.scene = scene;
        this.screen = new Screen(sizeX, sizeY);

        for(int i = 0; i < this.screen.getSizeX(); i++) {
            for(int j = 0; j < this.screen.getSizeY(); j++) {
                Vector vectorX = Camera.defaultRotationX;
                Vector vectorY = Camera.defaultRotationY;
                Vector vectorZ = Camera.defaultDirectionZ;

                try {
                    vectorX = Vector.setRotation(Camera.defaultRotationX, this.rotationY, Axis.Y);
                    vectorX = Vector.setRotation(vectorX, this.rotationZ, Axis.Z);

                    vectorY = Vector.setRotation(Camera.defaultRotationY, this.rotationX, Axis.X);
                    vectorY = Vector.setRotation(vectorY, this.rotationZ, Axis.Z);

                    vectorZ = Vector.setRotation(Camera.defaultDirectionZ, this.rotationX, Axis.X);
                    vectorZ = Vector.setRotation(vectorZ, this.rotationY, Axis.Y);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                vectorX.mul(-this.screen.getSizeX()/2+i);
                vectorY.mul(this.screen.getSizeY()/2-j);
                vectorZ.mul((float) this.distance);

                vectorX.add(vectorY);
                vectorX.add(vectorZ);
                Vector rayVector = vectorX;

                this.screen.setRay(i, j, new Ray(position, rayVector));

            }
        }
    }

    public void shoot() {

        this.picture = new BufferedImage(this.screen.getSizeX(), this.screen.getSizeY(), BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < this.screen.getSizeX(); i++) {
            for(int j = 0; j < this.screen.getSizeY(); j++) {
                this.picture.setRGB(i, j, this.traceRay(this.screen.getRay(i, j)).getRGB());
            }
        }
    }

    public void print(String filePath) throws IOException {
        Integer count = 0;
        if(this.picture != null) {
            File file = new File(filePath + "image_counter");
            if(file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                count = Integer.parseInt(br.readLine());
                br.close();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(Integer.toString(count + 1));
            bw.close();

            File outputFile = new File(filePath + "image" + (count+1) + ".png");
            ImageIO.write(this.picture, "png", outputFile);
        }

    }

    public Color traceRay(Ray ray) {
        Point3D nearestIntersection = null;
        Color intersectionColor = Color.lightGray;
        for(Displayable displayable : this.scene.getDisplayables()) {
            Point3D intersectionPoint = displayable.getIntersectionPoint(ray);
            if(intersectionPoint != null) {
                if(nearestIntersection != null) {
                    if(new Vector(ray.getStartingPoint(), nearestIntersection).length() > new Vector(ray.getStartingPoint(), intersectionPoint).length()) {
                        nearestIntersection = intersectionPoint;
                        intersectionColor = displayable.color;
                    }
                } else {
                    nearestIntersection = intersectionPoint;
                    intersectionColor = displayable.color;
                }
            }
//            System.out.println(displayable.color);
        }
        return intersectionColor;
    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public Double getRotationX() {
        return rotationX;
    }

    public void setRotationX(Double rotationX) {
        this.rotationX = rotationX;
    }

    public Double getRotationY() {
        return rotationY;
    }

    public void setRotationY(Double rotationY) {
        this.rotationY = rotationY;
    }

    public Double getRotationZ() {
        return rotationZ;
    }

    public void setRotationZ(Double rotationZ) {
        this.rotationZ = rotationZ;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
