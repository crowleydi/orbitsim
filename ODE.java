//
// Class which provides a 4th order ODE integration.
//
public class ODE<Y>
{
	//
	// integrate from t0 to tfinal in step size ts
	// y0 is initial state
	//
	public Y ode4(double t0, double tf, double ts, Y y0,
		IOdeFunc<Y> func, IOdeObserver<Y> obs)
	{
		Y y = y0;
		double t;
		for (t = t0; t < tf; t += ts)
		{
			if (t + ts > tf)
				ts = tf - t;
			// let the observer know our current state
			if (obs != null) obs.observe(t, y);

			Y y1 = y;
			Y k1 = func.mult(func.func(t, y1), ts);

			Y y2 = func.add(y,func.mult(k1,0.5));
			Y k2 = func.mult(func.func(t + 0.5*ts, y2), ts);

			Y y3 = func.add(y,func.mult(k2,0.5));
			Y k3 = func.mult(func.func(t + 0.5*ts, y3), ts);

			Y y4 = func.add(y,k3);
			Y k4 = func.mult(func.func(t + ts, y4), ts);

			// ksum = k1 + 2*k2 + 2*k3 + k4
			Y ksum = func.add(func.add(k1,func.add(func.mult(k2,2.),func.mult(k3,2.))),k4);
			y = func.add(y, func.mult(ksum, 1/6.0));
		}

		// let the observer know our final state
		if (obs != null) obs.observe(t, y);

		return y;
	}

	public Y ode4(double t0, double tf, double ts, Y y0,
		IOdeFunc<Y> func)
	{
		return ode4(t0, tf, ts, y0, func, null);
	}
}
