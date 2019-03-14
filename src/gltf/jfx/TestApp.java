package gltf.jfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class TestApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene =  new TestScene(new Group());
        JFXGLTFAsset asset = new JFXGLTFAsset("asset/scene.gltf");
        ((TestScene) scene).root().getChildren().add(asset.build3DScene(asset.scenes[0]));
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
