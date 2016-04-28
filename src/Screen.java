public class Screen {
    private final Integer sizeX;
    private final Integer sizeY;
    private Ray rays[][];

    public Screen(Integer sizeX, Integer sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.rays = new Ray[sizeX][sizeY];
    }

    public Ray getRay(int i, int j) {
        return this.rays[i][j];
    }

    public void setRay(int i, int j, Ray ray) {
        this.rays[i][j] = ray;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public Integer getSizeX() {
        return sizeX;
    }
}
