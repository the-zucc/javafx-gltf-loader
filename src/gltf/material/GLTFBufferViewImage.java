package gltf.material;

import gltf.Constants;
import gltf.buffer.GLTFBufferView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class GLTFBufferViewImage extends GLTFImage{
    public GLTFBufferViewImage(int bufferViewIdx,
                               GLTFBufferView[] bufferViews,
                               String mimeType)
            throws IOException
    {
        super(readImage(bufferViews[bufferViewIdx]));
    }
    private static BufferedImage readImage(GLTFBufferView bv)
            throws IOException
    {
        try {
            return ImageIO.read(
                new ByteArrayInputStream(
                    bv.getBytes(0, bv.byteLength)
                )
            );
        }catch(IOException ioe){
            throw new IOException(
                Constants.IMAGE_LOAD_ERROR_BINARY.replace(
                    Constants.ILE_BINARY_SOURCE_PLACEHOLDER,
                    bv.name)
            );
        }
    }
}
