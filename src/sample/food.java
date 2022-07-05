package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.Random;

public class food {
    public Circle food_circle;
    Random rand = new Random();
    public void newFood(Pane root,int x,int y){
        food_circle=new Circle(rand.nextInt(x),rand.nextInt(y),5);
        food_circle.setFill(javafx.scene.paint.Color.RED);
root.getChildren().add(food_circle);
    }
    public Circle getFood_circle(){
        return food_circle;
    }
    public void clear(Pane root){

        root.getChildren().remove(food_circle);
    }
}
