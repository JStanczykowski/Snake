package sample;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import sample.direction_snake;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class snake {
    ArrayList<Circle> snake_body= new ArrayList<Circle>();
    private int length =0;
    public Circle snake_circle;
    private direction_snake direction_s=direction_snake.UP;


    private static final int position = 8;
    public int getLength(){
        return this.length;
    }
    public void setDirection_s(direction_snake direction_s) {
        this.direction_s = direction_s;
    }

    public void newSnake(Pane root, int x, int y) {
        snake_circle = new Circle(x / 2, y / 2, 8);
        snake_body.add(snake_circle);
       snake_body.get(0).setFill(Color.GREEN);
        root.getChildren().add(snake_body.get(0));
    }

    private Circle endTail(){
        if(length==0){
            return snake_body.get(0);
        }
        return snake_body.get(length-1);
    }
    public void eat(Circle food){
        Circle Tail = endTail();
        food.setCenterX(Tail.getCenterX());
        food.setCenterX(Tail.getCenterX());
        food.setFill(Color.GREEN);
        snake_body.add(length++,food);
    }

    public void run(){
        for(int i=length-1;i>=0;i--){
            if(i==0){
                snake_body.get(i).setCenterX(getCenterX());
                snake_body.get(i).setCenterY(getCenterY());
            }
            else{
                if(snake_body.get(0).getCenterX()==snake_body.get(i).getCenterX()&&
                        snake_body.get(0).getCenterY()==snake_body.get(i).getCenterY()){
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("YOU LOSE! Your score: "+getLength());
                    a.showAndWait();
                    Platform.exit();
                    System.exit(0);
                    break;
                }
                snake_body.get(i).setCenterX(snake_body.get(i-1).getCenterX());
                snake_body.get(i).setCenterY(snake_body.get(i-1).getCenterY());
            }

        }
        if(direction_s==direction_snake.UP){
            setYMinus();
        }
        else if(direction_s==direction_snake.DOWN){
            setYPlus();
        }
        else if(direction_s==direction_snake.LEFT){
            setXMinus();
        }
        else if(direction_s==direction_snake.RIGHT){
            setXPlus();
        }
    }
    Circle getCircle(){
        return snake_circle;
    }
    public double getCenterX(){
        return snake_circle.getCenterX();
    }
    public double getCenterY(){
        return snake_circle.getCenterY();
    }
    public void setXMinus(){
        snake_circle.setCenterX(snake_circle.getCenterX()-position);
    }
    public void setXPlus(){
        snake_circle.setCenterX(snake_circle.getCenterX()+position);
    }
    public void setYMinus(){
        snake_circle.setCenterY(snake_circle.getCenterY()-position);
    }
    public void setYPlus(){
        snake_circle.setCenterY(snake_circle.getCenterY()+position);
    }

}
