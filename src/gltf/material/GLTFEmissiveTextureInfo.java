package gltf.material;

import gltf.exception.GLTFException;
import org.json.JSONObject;

public class GLTFEmissiveTextureInfo extends GLTFTextureInfo{
    public GLTFEmissiveTextureInfo(GLTFTexture texture, int texCoord, JSONObject extras) {
        super(texture, texCoord, extras);
    }
    public GLTFEmissiveTextureInfo fromJSONObject(JSONObject jObj, GLTFTexture[] textures) throws GLTFException {
        try{
            return new GLTFEmissiveTextureInfo(
                textures[jObj.getInt("index")],
                jObj.has("texCoord") ?
                    jObj.getInt("texCoord")
                    : null,
                jObj.has("extras") ?
                    jObj.getJSONObject("extras")
                    : null
            );
        }catch(Exception e){
            GLTFException.throwGLTFExceptionWithCause(jObj);
            //TODO catch all exception types separately
        }
        return null;
    }
}
