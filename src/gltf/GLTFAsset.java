package gltf;

import gltf.accessor.GLTFAccessor;
import gltf.buffer.GLTFBuffer;
import gltf.buffer.GLTFBufferView;
import gltf.exception.GLTFException;
import gltf.material.GLTFImage;
import gltf.material.GLTFMaterial;
import gltf.material.GLTFTexture;
import gltf.material.GLTFTextureSampler;
import gltf.mesh.GLTFMesh;
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
    private final JSONArray imagesJSON;
    private final JSONArray samplersJSON;
    private final JSONArray texturesJSON;
    private final JSONArray materialsJSON;
    private final JSONArray meshesJSON;

    public final GLTFBuffer[] buffers;
    public final GLTFBufferView[] bufferViews;
    public final GLTFAccessor[] accessors;
    public final GLTFImage[] images;
    public final GLTFTextureSampler[] samplers;
    public final GLTFTexture[] textures;
    public final GLTFMaterial[] materials;
    public final GLTFMesh[] meshes;




    public GLTFAsset(String filePath) throws IOException,GLTFException {
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
        this.imagesJSON = obj.getJSONArray("images");
        this.images = new GLTFImage[this.imagesJSON.length()];
        for(int i = 0; i < imagesJSON.length(); i++){
            this.images[i] = GLTFImage.fromJSONObject(
                this.imagesJSON.getJSONObject(i),
                bufferViews,
                gltfDir
            );
        }
        this.samplersJSON = obj.getJSONArray("samplers");
        this.samplers = new GLTFTextureSampler[this.samplersJSON.length()];
        for (int i = 0; i < this.samplersJSON.length(); i++) {
            this.samplers[i] = GLTFTextureSampler.fromJSONObject(
                this.samplersJSON.getJSONObject(i)
            );
        }
        this.texturesJSON = obj.getJSONArray("textures");
        this.textures = new GLTFTexture[this.texturesJSON.length()];
        for (int i = 0; i < this.texturesJSON.length(); i++) {
            this.textures[i] = GLTFTexture.fromJSONObject(
                this.texturesJSON.getJSONObject(i),
                this.images,
                this.samplers
            );
        }
        
        this.materialsJSON = obj.getJSONArray("materials");
        this.materials = new GLTFMaterial[this.materialsJSON.length()];
        for (int i = 0; i < this.materialsJSON.length(); i++) {
            this.materials[i] = GLTFMaterial.fromJSONObject(
                this.materialsJSON.getJSONObject(i),
                this.textures
                );
        }
        this.meshesJSON = obj.getJSONArray("meshes");
        this.meshes = new GLTFMesh[this.meshesJSON.length()];
        for (int i = 0; i < this.meshesJSON.length(); i++) {
            this.meshes[i] = GLTFMesh.fromJSONObject(
                this.meshesJSON.getJSONObject(i)
            );
        }
    }

    private GLTFBuffer getBufferFromIndex(int i) throws IOException {
        JSONObject bObj = (JSONObject)this.buffersJSON.get(i);
        return GLTFBuffer.fromBuffer(bObj, this.gltfDir);
    }
    public static void main(String[] args){
        int size = 100;
        GLTFAsset[] assets = new GLTFAsset[size];
        try {
            for (int i = 0; i < size; i++) {
                assets[i] = new GLTFAsset("asset/scene.gltf");
            }
        } catch (GLTFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(""/*asset.buffers[0].bytes.length*/);
        //System.out.println(asset.bufferViews.length);
    }
}
