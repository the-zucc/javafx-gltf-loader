package gltf.material;

import gltf.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GLTFExternalImage extends GLTFImage{
    protected final String relativePath;

    public GLTFExternalImage(String path, String gltfRootDir)
            throws IOException
    {
        super(readImage(gltfRootDir + "/" + path));
        this.relativePath = gltfRootDir + "/" + path;

    }
    private static BufferedImage readImage(String relativePath)
            throws IOException
    {
        try{
            return ImageIO.read(new File(relativePath));
        }catch(IOException ioe){
            throw new IOException(
                Constants.IMAGE_LOAD_ERROR_EXTERNAL
                    .replace(
                            Constants.ILE_EXTERNAL_SOURCE_PLACHOLDER,
                            relativePath
                    )
            );
        }
    }
}
