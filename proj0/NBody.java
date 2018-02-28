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
}