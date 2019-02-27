package gltf;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;

public class GLTFBuffer {
    /**
     * This class contains everything necessary
     * to read the bytes from an on-disk buffer
     * into a byte array.
     */
    protected final byte[] bytes;
    protected GLTFBuffer(FileInputStream inputStream, int length) throws IOException {
        bytes = new byte[length];
        inputStream.read(bytes, 0, length);
    }
    public GLTFBuffer(String path, int length) throws  IOException{
        this(new FileInputStream(path), length);
    }
    public static GLTFBuffer fromBuffer(JSONObject bObj, String gltfRootDir) throws IOException{
        return new GLTFBuffer(new FileInputStream(gltfRootDir+"/"+bObj.getString("uri")),
                bObj.getInt("byteLength"));
    }
}
