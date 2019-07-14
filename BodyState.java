//
// represents the state of a body in space
// could be a celestial body (Earth, Mars, etc.) or a spacecraft.
//
public class BodyState
{
	public Vector3 pos; // [distance] (m, km, AU, etc.)
	public Vector3 vel; // [distance/time]
	public double mass; // [mass] (kg or ??)

	public BodyState()
	{
		this.pos = new Vector3(0,0,0);
		this.vel = new Vector3(0,0,0);
		this.mass = 1.0;
	}

	public BodyState(Vector3 pos, Vector3 vel, double mass)
	{
		this.pos = pos;
		this.vel = vel;
		this.mass = mass;
	}
}
