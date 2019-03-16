package gltf.jfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.W;

public class TestApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene =  new TestScene(new Group());
        JFXGLTFAsset asset = new JFXGLTFAsset("asset/masterchief/scene.gltf");
        Group asset3D = asset.build3DScene(asset.scenes[0]);
        ((TestScene) scene).root().getChildren().add(asset3D);
        scene.setOnKeyPressed((e) -> {
            switch(e.getCode()){
                case W:
                    asset3D.getTransforms().add(new Translate(
                        0,0,2
                    ));break;
                case A:
                    asset3D.getTransforms().add(new Translate(
                        -2,0,0
                    ));break;
                case S:
                    asset3D.getTransforms().add(new Translate(
                        0,0,-2
                    ));break;
                case D:
                    asset3D.getTransforms().add(new Translate(
                        2,0,0
                    ));
            }

        });
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
