/**
 * Planet
 */
public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistanceX(Planet p) {
        return p.xxPos - xxPos;
    }

    public double calcDistanceY(Planet p) {
        return p.yyPos - yyPos;
    }

    public double calcDistance(Planet p) {
        double dx = calcDistanceX(p);
        double dy = calcDistanceY(p);
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return (G * p.mass * mass) / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double F = calcForceExertedBy(p);
        double dx = calcDistanceX(p);
        double r = calcDistance(p);
        return (F * dx) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double F = calcForceExertedBy(p);
        double dy = calcDistanceY(p);
        double r = calcDistance(p);
        return (F * dy) / r;
    }

    public double calcNetForceExertedByX(Planet[] a) {
        double netForceX = 0;
        for (Planet p : a) {
            if (p != this) {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] a) {
        double netForceY = 0;
        for (Planet p : a) {
            if (p != this) {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }
}