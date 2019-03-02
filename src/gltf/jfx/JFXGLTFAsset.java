package gltf.jfx;

import gltf.GLTFAsset;
import gltf.exception.InvalidGLTFTypeException;
import javafx.scene.Group;

import java.io.IOException;

public class JFXGLTFAsset extends GLTFAsset {
    public JFXGLTFAsset(String filePath) throws IOException, InvalidGLTFTypeException {
        super(filePath);
    }
}
