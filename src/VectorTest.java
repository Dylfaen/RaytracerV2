import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {
    private Vector vx;
    private Vector vy;
    private Vector vz;

    @Before
    public void setUp() throws Exception {
        this.vx = new Vector(1.0, 0.0, 0.0);
        this.vy = new Vector(0.0, 1.0, 0.0);
        this.vz = new Vector(0.0, 0.0, 1.0);
    }

    @Test
    public void testSetRotation() throws Exception {
        Vector result1 = Vector.setRotation(vx, Math.toRadians(45), Axis.X);
        System.out.println("result1" + result1.toString());
        assertTrue(result1.equals(vx));

        Vector result2 = Vector.setRotation(vx, Math.toRadians(45), Axis.Y);
        System.out.println("result2" + result2.toString());
    }

    @Test
    public void testAdd() throws Exception {

    }
}