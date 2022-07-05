package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    private Pane root;
    private int preferred_width=600;
    private int preferred_height=500;
    snake sn = new snake();
    food fo = new food();
    Text t = new Text((preferred_width/2)-25, 50, "This is text");

    private void border(){
        if(sn.getCircle().getCenterX()<0){
            sn.getCircle().setCenterX(preferred_width);
        }
        else if(sn.getCircle().getCenterX()>preferred_width){
            sn.getCircle().setCenterX(0);
        }
        else if(sn.getCircle().getCenterY()<0){
            sn.getCircle().setCenterY(preferred_height);
        }
        else if(sn.getCircle().getCenterY()>preferred_height){
            sn.getCircle().setCenterY(0);
        }
    }
    private  void eatFood(){
        if(fo.food_circle.intersects(sn.getCircle().getBoundsInLocal())){
            sn.eat(fo.getFood_circle());
            fo.newFood(root,preferred_width,preferred_height);
        }
    }

    private void move(){
        Platform.runLater(()->{
            sn.run();
            border();
            eatFood();

            t.setStyle("-fx-font: 24 arial;");
                t.setText("score: "+sn.getLength());


        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
         root = new Pane();
        root.getChildren().add(t);

        sn.newSnake(root,preferred_width,preferred_height);

            Runnable r = () -> {
                try {
                    for (; ; ) {
                        move();
                        if(sn.getLength()<26)
                        Thread.sleep(200-(sn.getLength()*6));
                        else
                            Thread.sleep(200-(26*6));
                    }
                } catch (InterruptedException ie) {

                }
            };


        primaryStage.setTitle("Snake");

        fo.newFood(root,preferred_width,preferred_height);
        primaryStage.setScene(new Scene(root, preferred_width, preferred_height));
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED,event -> {
            KeyCode code= event.getCode();
            if(code==KeyCode.UP){
                sn.setDirection_s(direction_snake.UP);
            }
            else if(code==KeyCode.DOWN){
                sn.setDirection_s(direction_snake.DOWN);
            }
            else if(code==KeyCode.LEFT){
                sn.setDirection_s(direction_snake.LEFT);
            }
            else if(code==KeyCode.RIGHT){
                sn.setDirection_s(direction_snake.RIGHT);
            }
        });
        Thread th1= new Thread(r);
        th1.setDaemon(true);
        th1.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
