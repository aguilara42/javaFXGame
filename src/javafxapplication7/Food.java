/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Aldo
 */
public class Food{

    public double x, y;
    private Point2D center;
    private double radius;
    private Color color;
    public boolean alive = true;

    public Food(double a, double b, double r, Color c) {
        x = a;
        y = b;
        radius = r;
        setCenter(x, y);

    }

    public void setCenter(double x, double y) {
        center = new Point2D(x, y);
    }

    public void drawplayer(GraphicsContext gc) {

        gc.setFill(Color.AQUA);
        gc.setStroke(Color.BLACK);
        gc.fillOval(this.x, this.y, radius, this.radius);
        gc.strokeOval(x, y, this.radius, this.radius);

    }

    public void setRadius(int r) {
        radius = r;
    }

    public double getRadius() {
        return this.radius;
    }

    /**
     *
     * @return
     */
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }

    public void setX(int a) {
        this.x = a;
    }

    public void setY(int a) {
        this.y = a;
    }

    public Circle hitBox(){
        Circle hit;
        hit = new Circle(x, y, radius*.6); 
        return hit;
    }

}
