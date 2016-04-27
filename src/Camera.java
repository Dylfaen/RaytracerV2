/**
 * Created by cesar on 4/26/16.
 */
public class Camera {
    private static final Vector defaultRotationX = new Vector(1.0, 0.0, 0.0);
    private static final Vector defaultRotationY = new Vector(0.0, 1.0, 0.0);
    private static final Vector defaultDirectionZ = new Vector(0.0, 0.0, 1.0);

    private Point position;
    private Double rotationX;
    private Double rotationY;
    private Double rotationZ;
    private Ray[][] screen;

    public Camera(Point position, Double rotationX, Double rotationY, Double rotationZ, Integer sizeX, Integer sizeY) {
        this.position = position;
        this.rotationX = rotationX;
        this.rotationY = rotationY;
        this.rotationZ = rotationZ;
        this.screen = new Ray[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++) {
            for(int j = 0; j < sizeY; j++) {
                Vector vectorX = Camera.defaultRotationX;
                Vector vectorY = Camera.defaultRotationY;
                Vector vectorZ = Camera.defaultDirectionZ;
                try {
                    vectorX = Vector.setRotation(Camera.defaultRotationX, this.rotationY, Axis.Y);
                    vectorX = Vector.setRotation(vectorX, this.rotationZ, Axis.Z);

                    System.out.println("vectorX" + vectorX.toString());

                    vectorY = Vector.setRotation(Camera.defaultRotationY, this.rotationX, Axis.X);
                    vectorY = Vector.setRotation(vectorY, this.rotationZ, Axis.Z);

                    System.out.println("vectorY" + vectorY.toString());

                    vectorZ = Vector.setRotation(Camera.defaultDirectionZ, this.rotationX, Axis.X);
                    vectorZ = Vector.setRotation(vectorZ, this.rotationY, Axis.Y);

                    System.out.println("vectorZ" + vectorZ.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                vectorX = Vector.multiply(vectorX, -sizeX/2);
                vectorY = Vector.multiply(vectorY, sizeY/2);
                Vector rayVector = Vector.add(Vector.add(vectorX, vectorY), vectorZ);
                System.out.println("rayVector" + rayVector.toString() + "\n");
                this.screen[i][j] = new Ray(position, rayVector);
            }
        }
    }

    public void shoot() {

    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
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

    public Ray[][] getScreen() {
        return screen;
    }

    public void setScreen(Ray[][] screen) {
        this.screen = screen;
    }
}
