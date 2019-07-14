//
// Class for 3d vector coordinates
//
public class Vector3
{
	// cartesian coordinates
	public double x;
	public double y;
	public double z;

	// default constructor
	public Vector3()
	{
		x = y = z = 0;
	}

	public Vector3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3(double[] pos)
	{
		this.x=pos[0];
		this.y=pos[1];
		this.z=pos[2];
	}

	// norm/length of the vector
	public double norm()
	{
		return Math.sqrt(x*x+y*y+z*z);
	}

	// common vector operations
	// add 2 vectors together
	public static Vector3 add(Vector3 a, Vector3 b)
	{
		return new Vector3(a.x+b.x, a.y+b.y, a.z+b.z);
	}

	// take the difference between two vectors
	public static Vector3 diff(Vector3 a, Vector3 b)
	{
		return new Vector3(a.x-b.x, a.y-b.y, a.z-b.z);
	}

	// multiply/scale a vector
	public static Vector3 mult(Vector3 a, double n)
	{
		return new Vector3(a.x*n, a.y*n, a.z*n);
	}

	// calculate a unit vector
	public static Vector3 unit(Vector3 a)
	{
		return mult(a, 1.0/a.norm());
	}

	public static void main(String[] args)
	{
		// some simple tests...
		Vector3 a = new Vector3(1.0, 2.0, 3.0);
		Vector3 b = new Vector3(3.0, 4.0, 5.0);

		Vector3 c = diff(b, a);
		Vector3 d = add(c, a);
		Vector3 e = unit(c);

		System.out.printf("vec a: [%f %f %f]\n", a.x, a.y, a.z);
		System.out.printf("vec b: [%f %f %f]\n", b.x, b.y, b.z);
		System.out.printf("vec c: [%f %f %f]\n", c.x, c.y, c.z);
		System.out.printf("vec d: [%f %f %f]\n", d.x, d.y, d.z);
		System.out.printf("vec e: [%f %f %f]\n", e.x, e.y, e.z);
	}
}
