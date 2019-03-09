package gltf.mesh;

import org.json.JSONObject;

public class GLTFMesh {
    public final GLTFMeshPrimitive[] primitives;

    public GLTFMesh(GLTFMeshPrimitive[] primitives) {
        this.primitives = primitives;
    }
    public static GLTFMesh fromJSONObject(JSONObject jObj){
        //TODO code this method
        return null;
    }
}
