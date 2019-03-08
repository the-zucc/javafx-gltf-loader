package gltf.material;

import org.json.JSONObject;

public class GLTFOcclusionTextureInfo extends GLTFTextureInfo{

    public GLTFOcclusionTextureInfo(GLTFTexture texture, int texCoordIdx, float strength, JSONObject extras) {
        super(texture, texCoordIdx, extras);
    }
    public static GLTFOcclusionTextureInfo fromJSONObject(JSONObject jObj, GLTFTexture[] textures){

    }
}
