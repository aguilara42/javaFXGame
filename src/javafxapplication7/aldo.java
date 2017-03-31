/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import com.sun.javafx.geom.Shape;
import static java.lang.Math.random;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class aldo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    static Scene mainScene;
    static boolean startScreen = true;
    static GraphicsContext gc;
    static int WIDTH = 1200;
    static int HEIGHT = 800;
    static int score = 0;
    static int dif = 5;
    static Player player = new Player(60, 600, 50, Color.BLACK);
    static ArrayList<Food> foodList = new ArrayList<Food>();
    static Food food1 = new Food(50, 50, 50, Color.AQUA);
    static Food food2 = new Food(50, 50, 50, Color.AQUA);
    static Food food3 = new Food(50, 50, 50, Color.AQUA);
    static Food food4 = new Food(50, 50, 50, Color.AQUA);
    static Food food5 = new Food(50, 50, 50, Color.AQUA);
    static Random rand = new Random();
    static boolean gamePlaying;

    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle("aguilar.io");

        Group root = new Group();
        mainScene = new Scene(root);

        mainStage.setScene(mainScene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        root.getChildren().add(canvas);

        final ArrayList<String> input = new ArrayList<String>();

        mainScene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        });

        mainScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });

        gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setLineWidth(1);

        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);
        foodList.add(food5);
        for (int i = 0; i < foodList.size(); i++) {
            randomize(i);
        }

        new AnimationTimer() {
            double dx = 1;
            double dy = 1;
            double lastmove = 1;
            double lastmovex = 1;

            public void handle(long currentNanoTime) {
                if (startScreen) {
                    Text startText = new Text();
                    startText.setText("press N to start");
                    startText.setX(300);
                    startText.setY(250);
                    startText.setFont(new Font(60));
                    startText.setFill(Color.PURPLE);
                    root.getChildren().add(startText);
                    System.out.println("StartScreen = true");
                    
                    if (input.contains("LEFT")) {
                        gamePlaying = true;
                        startText.setFill(Color.WHITE);
                        startScreen = false;
                        System.out.println("n is pressed");
                    }
                if (gamePlaying){
                    System.out.println("gameIsPlaying");
                int enemy = rand.nextInt(4);
                render();

                if (player.x > 0) {
                    if (input.contains("LEFT")) {
                        player.x = player.x - 3;

                        foodList.get(enemy).setX((int) (foodList.get(enemy).getX() - rand.nextInt(8)));
                        lastmovex *= -1;

                        foodList.get(enemy).setY((int) (foodList.get(enemy).getY() - rand.nextInt(8)));
                        lastmove *= -1;
                    }
                }

                if (player.x + player.getRadius() < 1200) {
                    if (input.contains("RIGHT")) {
                        player.x = player.x + 3;

                        foodList.get(enemy).setX((int) (foodList.get(enemy).getX() + rand.nextInt(8)));
                        lastmovex *= -1;
                    }
                }
                if (player.y > 0) {
                    if (input.contains("UP")) {
                        player.y = player.y - 3;
                        foodList.get(enemy).setY((int) (foodList.get(enemy).getY() - rand.nextInt(8)));
                        lastmove *= -1;
                    }
                }
                if (player.y + player.getRadius() < 800) {
                    if (input.contains("DOWN")) {
                        player.y = player.y + 3;
                        foodList.get(enemy).setY((int) (foodList.get(enemy).getY() + rand.nextInt(8)));
                        lastmove *= -1;
                    }
                }
                double dx = 1;
                double dy = 1;
                double lastmove = 1;
                double lastmovex = 1;

                for (int i = 0; i < foodList.size(); i++) {
                    double move = rand.nextInt();

                    int speed = 2;

                    if (dy < 0 && lastmove == 1 && move > 1) {
                        dy *= -1;
                    } else if (dy > 0 && lastmove == -1 && move > 1) {
                        dy *= -1;
                    } else if (!(dy > 0 && lastmove == 1) && !(dy < 0 && lastmove == -1)) {
                        lastmove *= -1;
                    }

                    if (dx < 0 && lastmovex == 1 && move > 1) {
                        dx *= -1;
                    } else if (dx > 0 && lastmovex == -1 && move > 1) {
                        dx *= -1;
                    } else if (!(dx > 0 && lastmovex == 1) && !(dx < 0 && lastmovex == -1)) {
                        lastmovex *= -1;
                    }

                    if (foodList.get(i).getX() > 0) {
                        if (dx == -1) {
                            foodList.get(i).setX((int) (foodList.get(i).getX() - rand.nextInt(speed)));
                            lastmovex *= -1;
                        }

                    }

                    if (foodList.get(i).getX() + foodList.get(i).getRadius() < 1180) {
                        if (dx == 1) {
                            foodList.get(i).setX((int) (foodList.get(i).getX() + rand.nextInt(speed)));
                            lastmovex *= -1;
                        }
                    }
                    if (foodList.get(i).getY() > 20) {
                        if (dy == 1) {
                            foodList.get(i).setY((int) (foodList.get(i).getY() - rand.nextInt(speed)));
                            lastmove *= -1;
                        }
                    }
                    if (foodList.get(i).getY() + player.getRadius() < 790) {
                        if (dy == -1) {
                            foodList.get(i).setY((int) (foodList.get(i).getY() + rand.nextInt(speed)));
                            lastmove *= -1;
                        }
                    }

                    if (player.hitBox().getBoundsInParent().intersects(foodList.get(i).hitBox().getBoundsInParent()) && foodList.get(i).alive) {
                        score++;
                        foodList.get(i).alive = false;
                        player.setRadius((player.getRadius() + foodList.get(i).getRadius() / 12));
                        randomize(i);

                    }
                }
            }
            }
            }
        }
                .start();

        mainStage.show();
    }

    public static void randomize(int a) {

        foodList.get(a).setX(rand.nextInt(1200));
        foodList.get(a).setY(rand.nextInt(800));
        foodList.get(a).setRadius(rand.nextInt((int) player.getRadius() + 4) + 20);
        foodList.get(a).alive = true;

    }

    public static void render() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        String pointsText = "Food: " + (100 * score);
        gc.fillText(pointsText, 580, 36);
        gc.strokeText(pointsText, 580, 36);
        if (player.getX() > 1200 || player.getY() > 800 || player.getX() < 0 || player.getY() < 0 || score == 32) {
            player.y = 400 - player.getRadius();
            player.x = 600 - player.getRadius();
            for (int i = 0; i < foodList.size(); i++) {
                foodList.get(i).alive = false;
                foodList.remove(i);
            }
            String winText = "YOU'VE WON!!!!!";
            gc.fillText(winText, 580, 600);
            gc.strokeText(winText, 580, 600);
            player.setRadius(player.getRadius() + .3);
        }
        player.drawplayer(gc);
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).alive) {
                foodList.get(i).drawplayer(gc);
            }
        }
    }

}
