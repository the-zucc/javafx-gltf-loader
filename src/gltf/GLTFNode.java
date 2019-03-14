package gltf;

import gltf.accessor.GLTFAccessor;
import gltf.exception.GLTFException;
import gltf.mesh.GLTFMesh;
import gltf.utils.JSONUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class GLTFNode {
    public static final float[] DEFAULT_TRANSFORMATION_MATRIX = {
        1.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f
    };
    public final static float[] DEFAULT_ROTATION = {0,0,0,1};
    public final static float[] DEFAULT_SCALE = {1,1,1};
    public final static float[] DEFAULT_TRANSLATION= {0,0,0};

    public final GLTFCamera camera;
    public final int[] childrenIdxes;
    private final GLTFNode[] nodes;
    public final GLTFSkin skin;
    public final float[] transformationMatrix;
    public final GLTFMesh mesh;
    public final float[] rotation;
    public final float[] scale;
    public final float[] translation;
    public final float[] weights;
    public final String name;
    public final JSONObject extras;

    public GLTFNode(
                    GLTFCamera camera,
                    int[] childrenIdxes,
                    GLTFNode[] nodes,
                    GLTFSkin skin,
                    float[] transformationMatrix,
                    GLTFMesh mesh,
                    float[] rotation,
                    float[] scale,
                    float[] translation,
                    float[] weights,
                    String name,
                    JSONObject extras
                    )
    {
        this.camera = camera;
        this.childrenIdxes = childrenIdxes;
        this.nodes = nodes;
        this.skin = skin;
        this.transformationMatrix = transformationMatrix;
        this.mesh = mesh;
        this.rotation = rotation;
        this.scale = scale;
        this.translation = translation;
        this.weights = weights;
        this.extras = extras;
        this.name = name;

    }
    public GLTFNode(int cameraIdx,
                    int[] childrenIdxes,
                    int skinIdx,
                    JSONArray matrix,
                    int meshIdx,
                    JSONArray rotation,
                    JSONArray scale,
                    JSONArray translation,
                    JSONArray weights,
                    String name,
                    JSONObject extras,
                    GLTFCamera[] cameras,
                    GLTFNode[] nodes,
                    GLTFSkin[] skins,
                    GLTFMesh[] meshes)
    {
        this(cameraIdx == -1 ?
                null
                : cameras[cameraIdx],
            childrenIdxes == null ?
                new int[]{}
                : childrenIdxes,
            nodes == null ?
                new GLTFNode[]{}
                : nodes,
            skinIdx == -1 ?
                null
                : skins[skinIdx],
            matrix == null ?
                DEFAULT_TRANSFORMATION_MATRIX
                : JSONUtils.JSONToFloatArray(matrix),
            meshIdx == -1 ?
                null
                : meshes[meshIdx],
            rotation == null ?
                DEFAULT_ROTATION
                : JSONUtils.JSONToFloatArray(rotation),
            scale == null ?
                DEFAULT_SCALE
                : JSONUtils.JSONToFloatArray(scale),
            translation == null ?
                DEFAULT_TRANSLATION
                : JSONUtils.JSONToFloatArray(translation),
            weights == null ?
                new float[]{}
                : JSONUtils.JSONToFloatArray(weights),
            name,
            extras
        );
    }
    public static GLTFNode[] getChildrenFromIdxes(int[] children, GLTFNode[] nodes){
        GLTFNode[] returnVal = new  GLTFNode[children.length];
        for (int i = 0; i < children.length; i++) {
            returnVal[i] = nodes[children[i]];
        }
        return returnVal;
    }
    public static GLTFNode fromJSONObject(JSONObject jObj,
                                              GLTFCamera[] cameras,
                                              GLTFNode[] nodes,
                                              GLTFMesh[] meshes,
                                              GLTFSkin[] skins)
        throws GLTFException
    {
        //TODO code this method
        try{
            return new GLTFNode(
                jObj.has("camera") ?
                    jObj.getInt("camera")
                    : -1,
                jObj.has("children") ?
                    JSONUtils.JSONToIntArray(jObj.getJSONArray("children"))
                    : null,
                jObj.has("skin") ?
                    jObj.getInt("skin")
                    : -1,
                jObj.has("matrix") ?
                    jObj.getJSONArray("matrix")
                    : null,
                jObj.has("mesh") ?
                    jObj.getInt("mesh")
                    : -1,
                jObj.has("rotation") ?
                    jObj.getJSONArray("rotation")
                    : null,
                jObj.has("scale") ?
                    jObj.getJSONArray("scale")
                    : null,
                jObj.has("translation") ?
                    jObj.getJSONArray("translation")
                    : null,
                jObj.has("weights") ?
                    jObj.getJSONArray("weights")
                    : null,
                jObj.has("name") ?
                    jObj.getString("name")
                    : null,
                jObj.has("extras") ?
                    jObj.getJSONObject("extras")
                    : null,
                cameras, nodes, skins, meshes
            );
        }catch(Exception e){
            e.printStackTrace();
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }
        return null;
    }

    private GLTFNode[] children = null;
    public GLTFNode[] getChildren(){
        if(children == null){
            int len = this.childrenIdxes.length;
            children = new GLTFNode[len];
            for (int i = 0; i < len; i++) {
                children[i] = nodes[childrenIdxes[i]];
            }
        }
        return children;
    }
}
