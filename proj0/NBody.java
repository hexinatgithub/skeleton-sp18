/**
 * NBody
 */
public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        // skip the number of planets.
        in.readDouble();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        /* Start reading in file */
        In in = new In(fileName);
        // read number of planets.
        int number = in.readInt();
        // skip the number of planets and radius.
        in.readDouble();

        Planet[] planets = new Planet[number];
        /* Read all planet information */
        for (int i = 0; i < number; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];

        Planet[] planets = readPlanets(fileName);
        // radius of universe.
        double radius = readRadius(fileName);

        /* Starting drawing universe */
        StdDraw.setScale(-radius, radius);
        /* Clears the drawing window. */
        StdDraw.clear();
        // Draw universe background.
        StdDraw.picture(0, 0, "images/starfield.jpg");

        // Draw all planets.
        for (Planet p : planets) {
            p.draw();
        }

        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            // calculate each of planets net force.
            for (int i = 0; i < planets.length; i++) {
                Planet p = planets[i];
                xForces[i] = p.calcNetForceExertedByX(planets);
                yForces[i] = p.calcNetForceExertedByY(planets);
            }

            // Draw universe background.
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // update each of planets position, velocity and acceleration.
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos,
                    planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}