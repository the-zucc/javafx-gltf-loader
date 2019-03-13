package gltf;

import gltf.exception.GLTFException;
import org.json.JSONArray;
import org.json.JSONObject;

public class GLTFScene {
    public final GLTFNode[] nodes;

    public GLTFScene(GLTFNode[] nodes) {
        this.nodes = nodes;
    }

    public static GLTFScene fromJSONObject(JSONObject jObj, GLTFNode[] nodes) throws GLTFException {
        try{
            GLTFNode[] sceneNodes = new GLTFNode[]{};
            if(jObj.has("nodes")){
                JSONArray jarr = jObj.getJSONArray("nodes");
                sceneNodes = new GLTFNode[jarr.length()];
                for (int i = 0; i < jarr.length(); i++) {
                    sceneNodes[i] = nodes[jarr.getInt(i)];
                }
            }
            return new GLTFScene(
                sceneNodes
            );
        }catch(Exception e){
            e.printStackTrace();
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }
        return null;
    }
}
