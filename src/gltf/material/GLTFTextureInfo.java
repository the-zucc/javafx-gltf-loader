package gltf.material;

import org.json.JSONObject;

public abstract class GLTFTextureInfo {
    public final GLTFTexture texture;
    public final int texCoordIdx;
    public final JSONObject extras;

    public GLTFTextureInfo(GLTFTexture texture,
                           int texCoordIdx,
                           JSONObject extras)
    {
        this.texture = texture;
        this.texCoordIdx = texCoordIdx;
        this.extras = extras;
    }

}
