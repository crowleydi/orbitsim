//
// Interface that the ODE class uses to integrate
// over arbitrary object types.
//
public interface IOdeFunc<Y>
{
	// function to be integrated
	// Ydot = func(t, Y)
	public Y func(double t, Y y);

	// functions we need for computations 
	// must return Y+dy
	public Y add(Y y, Y dy);

	// must return Y*x
	public Y mult(Y y, double x);
}
