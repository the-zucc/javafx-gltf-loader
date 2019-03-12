package gltf.material;

import gltf.exception.GLTFException;
import org.json.JSONObject;

public class GLTFOcclusionTextureInfo extends GLTFTextureInfo{

    public final float strength;

    public GLTFOcclusionTextureInfo(GLTFTexture texture, int texCoordIdx, float strength, JSONObject extras) {
        super(texture, texCoordIdx, extras);
        this.strength = strength;
    }
    public GLTFOcclusionTextureInfo(int textureIdx,
                                    int texCoordIdx,
                                    Float strength,
                                    JSONObject extras,
                                    GLTFTexture[] textures){
        this(textures[textureIdx],
            texCoordIdx == -1 ?
                0
                : texCoordIdx,
            strength,
            extras);
    }
    public static GLTFOcclusionTextureInfo fromJSONObject(JSONObject jObj, GLTFTexture[] textures) throws GLTFException {
        try{
            return new GLTFOcclusionTextureInfo(
                jObj.getInt("index"),
                jObj.has("texCoord") ?
                    jObj.getInt("texCoord")
                    : 0,
                jObj.has("Strength") ?
                    jObj.getFloat("strength")
                    : 1,
                jObj.has("extras") ?
                    jObj.getJSONObject("extras")
                    : null,
                textures
            );
        }catch(Exception e){
            e.printStackTrace();
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }
        return null;
    }
}
