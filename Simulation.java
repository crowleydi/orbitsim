//
// This class simulates a 100kg satellite in a circular
// 400km orbit around Earth.
//
public class Simulation implements IOdeFunc<BodyState>, IOdeObserver<BodyState>
{
	// some constants we will use in our simulation
	private static final double G = 6.67408e-11;		// [m^3/kg/s^2]
	private static final double MassEarth = 5.9722e24;	// [kg]
	private static final double RadiusEarth = 6378;		// [km]
	private static final double Mu = G*MassEarth;		// [m^3/s^2]

	// central body
	private BodyState Earth;

	// calculates the force due to gravity between two bodies
	private static Vector3 ForceGravity(BodyState a, BodyState b)
	{
		// calculate the vector between the two bodies
		Vector3 r = Vector3.diff(b.pos, a.pos);
		// calculate the distance
		double rn = r.norm();
		// calculate the resulting force vector
		Vector3 F = Vector3.mult(r,-G*a.mass*b.mass/rn/rn/rn);

		return F;
	}

	//
	// Returns bdot = func(t, b)
	// where bdot is the calculated change to the body
	// position/velocity at time t
	public BodyState func(double t, BodyState b)
	{
		// Calculate the force due to gravity
		Vector3 Fg = ForceGravity(Earth, b);

		// could add in additional Force vectors...
		// Force due to solar radiation pressure.
		// Force due to atmospheric drag.
		// Force due to other graviational bodies. (Moon, Jupiter, etc.)

		// calculate acceleration vector by dividing total force
		// by the mass of the orbiting object
		Vector3 accel = Vector3.mult(Fg, 1./b.mass);

		// return the time differential change in the body
		// change in position -> velocity
		// change in velocity -> acceleration (due to gravity)
		// change in mass -> zero
		BodyState bdot = new BodyState(b.vel, accel, 0.0);

		return bdot;
	}

	// add function is needed so the ODE function
	// can add two arbitrary objects together. In this case
	// we add changes to the bodys position and veloicty vectors
	public BodyState add(BodyState b, BodyState bdot)
	{
		return new BodyState(Vector3.add(b.pos, bdot.pos),
			Vector3.add(b.vel,bdot.vel), b.mass + bdot.mass);
	}

	// multiply arbitrary object (in this case BodyState) by a scalar
	public BodyState mult(BodyState b, double s)
	{
		return new BodyState(Vector3.mult(b.pos, s),
			Vector3.mult(b.vel,s), b.mass*s);
	}

	// observe the integration
	public void observe(double t, BodyState b)
	{
		// printing it, but we could plot it, put results in a vector,
		// or ...
		System.out.printf("t=%4.1f: [%f,%f], [%f,%f]\n",
			t, b.pos.x, b.pos.y, b.vel.x, b.vel.y);
	}

	// run the simulation
	public void Run()
	{
		Earth = new BodyState();
		Earth.mass = MassEarth;
		BodyState spacecraft = new BodyState();
		spacecraft.mass = 100; // [kg]

		// place the spacecraft 400km above Earth.
		spacecraft.pos.x = (RadiusEarth + 400)*1000;

		// calculate the speed of a spacecraft in a
		// 400km Earth circular orbit.
		double v = Math.sqrt(Mu/spacecraft.pos.norm()); // [m/s]
		System.out.printf("Initial velocity is %f\n", v);

		// set the spacecraft velocity vector.
		spacecraft.vel.y = v;

		ODE<BodyState> ode = new ODE<BodyState>();
		// Simluate the spacecraft in motion for ~1 orbit.
		ode.ode4(0.0, 5553.6, 4.0, spacecraft, this, this);
	}

	public static void main(String[] args)
	{
		// Create a simulation and run
		Simulation s = new Simulation();
		s.Run();
	}
}
