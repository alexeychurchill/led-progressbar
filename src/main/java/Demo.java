import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import led.progressbar.LedProgressBar;

public class Demo extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        //...
        LedProgressBar ledProgressBar = new LedProgressBar();
        root.setCenter(ledProgressBar);
//        root.setCenter(new Led());
        //...
        Slider progressSlider = new Slider(0.0, 1.0, 0.5);
        ledProgressBar.progressProperty().bind(progressSlider.valueProperty());
        //...
        Slider ledCountSlider = new Slider(1.0, 15.0, 7.0);
        ledCountSlider.valueProperty().addListener(observable -> {
            ledProgressBar.setLedCount((int) ledCountSlider.getValue() % 16);
        });
        //...
        VBox settingsVBox = new VBox(
                5.0,
                new Label("Progress"),
                progressSlider,
                new Label("LED count"),
                ledCountSlider
        );
        settingsVBox.setMinWidth(200);
        root.setRight(settingsVBox);
        //...
        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add("sampleStylesheet.css");
        primaryStage.setTitle("LED ProgressBar demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
