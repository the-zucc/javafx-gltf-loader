package gltf.material;

import gltf.exception.GLTFException;
import org.json.JSONArray;
import org.json.JSONObject;

public class GLTFpbrMetallicRoughness {
    public final GLTFTextureInfo baseColorTexture;
    public final float[] baseColorFactor;
    public final float metallicFactor;
    public final float roughnessFactor;
    public final GLTFTextureInfo metallicRoughnessTexture;
    public final JSONObject extras;
    public GLTFpbrMetallicRoughness(GLTFTextureInfo baseColorTexture,
                                    float[] baseColorFactor,
                                    float metallicFactor,
                                    float roughnessFactor,
                                    GLTFTextureInfo metallicRoughnessTexture,
                                    JSONObject extras) {
        this.baseColorTexture = baseColorTexture;
        this.baseColorFactor = baseColorFactor;
        this.metallicFactor = metallicFactor;
        this.roughnessFactor = roughnessFactor;
        this.metallicRoughnessTexture = metallicRoughnessTexture;
        this.extras = extras;
    }

    public static GLTFpbrMetallicRoughness fromJSONObject(JSONObject jObj, GLTFTexture[] textures) throws GLTFException {
        try{
            float[] baseColorFactor = new float[4];
            if(jObj.has("baseColorFactor")){
                JSONArray jarr = jObj.getJSONArray("baseColorFactor");
                for (int i = 0; i < jarr.length(); i++) {
                    baseColorFactor[i] = jarr.getFloat(i);
                }
            }
            return new GLTFpbrMetallicRoughness(
                jObj.has("baseColorTexture") ?
                    GLTFTextureInfo.fromJSONObject(jObj.getJSONObject("baseColorTexture"), textures)
                    : null,
                jObj.has("baseColorFactor") ?
                    baseColorFactor
                    : null,
                jObj.has("metallicFactor") ?
                    jObj.getFloat("metallicFactor")
                    : 1.0f,
                jObj.has("roughnessFactor") ?
                    jObj.getFloat("roughnessFactor")
                    : 1.0f,
                jObj.has("metallicRoughnessTexture") ?
                    GLTFTextureInfo.fromJSONObject(jObj.getJSONObject("metallicRoughnessTexture"), textures)
                    : null,
                jObj.has("extras") ?
                    jObj.getJSONObject("extras")
                    : null
            );
        }catch(Exception e){
            e.printStackTrace();
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }
        return null;
    }
    //TODO add this maybe
}
