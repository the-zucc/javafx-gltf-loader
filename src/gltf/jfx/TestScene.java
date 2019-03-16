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
        super(root, 800, 600, true);
        this.root = root;
        this.camera = new PerspectiveCamera(true);
        this.camera.setNearClip(0.5);
        this.camera.setFarClip(5000);
        Point3D cameraPos = new Point3D(0, -20, -20);
        this.camera.getTransforms().add(
            new Translate(
                cameraPos.getX(),
                cameraPos.getY(),
                cameraPos.getZ()
            )
        );
        this.setCamera(this.camera);
        this.camera.setRotationAxis(Rotate.Z_AXIS);
        this.camera.setRotate(180);
        //this.camera.getTransforms().add(new Rotate(Math.toRadians(180), Rotate.Z_AXIS));
        this.lookAt(this.camera, cameraPos, Point3D.ZERO);
        final boolean[] isMoving = {false};
        double[] mpos = {0, 0};
        this.setOnMousePressed((e) -> {
            isMoving[0] = true;
        });
        this.setOnMouseReleased((e) -> {
            isMoving[0] = false;
        });
        this.setOnMouseDragged((e) -> {
            if(isMoving[0]){
                double dx = e.getSceneX() - mpos[0];
                double dy = e.getSceneY() - mpos[0];
                mpos[0] = e.getSceneX();
                mpos[1] = e.getSceneY();
                this.camera.getTransforms().add(new Rotate(Math.toRadians(dx/8), Rotate.Y_AXIS));
                this.camera.getTransforms().add(new Rotate(Math.toRadians(dy/8), Rotate.X_AXIS));
            }
        });
        this.setOnKeyPressed((e) -> {

        });
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
