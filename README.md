This code is similar to some C++ code I wrote for simulating
a small satellite in orbit around Earth. This uses a 4th order
ODE to propagate the orbit using the equation of motion.

Simply equates Newton's second law, **F** = m**a**, with Newton's
law of universal gravitation, **F**=**r**G*m1m2*/r^3.

I would like to add additional effects from solar radiation pressure,
atmospheric drag, the Moon, etc. Frame is ECI.
