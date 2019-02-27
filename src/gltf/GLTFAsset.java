package gltf;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GLTFAsset {
    private final File gltfFile;
    private final String gltfDir;
    private final JSONObject obj;
    private final JSONArray buffersJSON;
    private final JSONArray bufferViewsJSON;
    private final JSONArray accessorsJSON;

    public final GLTFBuffer[] buffers;
    public final GLTFBufferView[] bufferViews;
    public final GLTFAccessor[] accessors;


    public GLTFAsset(String filePath) throws IOException, InvalidGLTFTypeException {
        this.gltfFile = new File(filePath);
        this.gltfDir = this.gltfFile.getParent();
        String content = new Scanner(this.gltfFile).useDelimiter("\\Z").next();

        //get root
        this.obj = new JSONObject(content);
        //get buffers
        this.buffersJSON = this.obj.getJSONArray("buffers");

        this.buffers = new GLTFBuffer[buffersJSON.length()];
        for(int i = 0; i < this.buffersJSON.length(); i++){
            buffers[i] = getBufferFromIndex(i);
        }
        this.bufferViewsJSON = this.obj.getJSONArray("bufferViews");
        this.bufferViews = new GLTFBufferView[this.bufferViewsJSON.length()];
        for(int i = 0; i < bufferViewsJSON.length(); i++){
            this.bufferViews[i] = new GLTFBufferView(
                    (JSONObject)this.bufferViewsJSON.get(i),
                    this.buffers);
        }
        this.accessorsJSON = obj.getJSONArray("accessors");
        this.accessors = new GLTFAccessor[this.accessorsJSON.length()];
        for (int i = 0; i < accessorsJSON.length(); i++) {
            this.accessors[i] = GLTFAccessor.fromJSONObject(
                    this.accessorsJSON.getJSONObject(i),
                    this.bufferViews);
        }
    }

    private GLTFBuffer getBufferFromIndex(int i) throws IOException {
        JSONObject bObj = (JSONObject)this.buffersJSON.get(i);
        return GLTFBuffer.fromBuffer(bObj, this.gltfDir);
    }
    public static void main(String[] args){
        GLTFAsset asset = null;
        try {
            asset = new GLTFAsset("asset/scene.gltf");
        } catch (InvalidGLTFTypeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(asset.buffers[0].bytes.length);
        System.out.println(asset.bufferViews.length);
    }
}
