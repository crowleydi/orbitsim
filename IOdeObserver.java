//
// Interface used by the ODE class to observe
// results of the integration.
//
public interface IOdeObserver<Y>
{
	// this function observes results of the integration
	// the value y at time t
	public void observe(double t, Y y);
}
