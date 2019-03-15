package gltf.jfx;

import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

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
        Point3D cameraPos = new Point3D(5, 5, 5);
        this.camera.getTransforms().add(
            new Translate(
                cameraPos.getX(),
                cameraPos.getY(),
                cameraPos.getZ()
            )
        );
        lookAt(this.camera, cameraPos, Point3D.ZERO);
        this.setCamera(this.camera);
    }
    public void lookAt(Node cam, Point3D cameraPosition, Point3D lookAtPos) {
        //Create direction vector
        Point3D camDirection = lookAtPos.subtract(cameraPosition.getX(), cameraPosition.getY(), cameraPosition.getZ());
        camDirection = camDirection.normalize();

        double xRotation = Math.toDegrees(Math.asin(-camDirection.getY()));
        double yRotation =  Math.toDegrees(Math.atan2( camDirection.getX(), camDirection.getZ()));

        Rotate rx = new Rotate(xRotation, cameraPosition.getX(), cameraPosition.getY(), cameraPosition.getZ(), Rotate.X_AXIS);
        Rotate ry = new Rotate(yRotation, cameraPosition.getX(), cameraPosition.getY(), cameraPosition.getZ(),  Rotate.Y_AXIS);

        cam.getTransforms().addAll( ry, rx,
            new Translate(
                cameraPosition.getX(),
                cameraPosition.getY(),
                cameraPosition.getZ()));
    }
}
