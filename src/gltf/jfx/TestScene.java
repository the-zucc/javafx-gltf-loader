package gltf.jfx;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;

public class TestScene extends Scene {
    private Group root;
    public Group root(){
        return this.root;
    }
    private PerspectiveCamera camera;
    public TestScene(Group root) {
        super(root);
        this.root = root;
        this.camera = new PerspectiveCamera(true);
        this.camera.setNearClip(0.5);
        this.camera.setFarClip(1000);
        this.camera.setTranslateZ(-500);
        this.camera.setTranslateY(-500);
        this.camera.getTransforms().add(new Rotate(-45,Rotate.X_AXIS));
        this.setCamera(this.camera);
    }
}
