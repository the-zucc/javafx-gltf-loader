package gltf.material;


import org.json.JSONObject;

public class GLTFMaterial {
    public final boolean doubleSided;
    protected final GLTFpbrMetallicRoughness pbrMetallicRoughness;
    public final GLTFTexture emissiveMap;
    public final GLTFTexture normalMap;
    public final GLTFTexture occlusionMap;

    public GLTFMaterial(String materialName,
                        boolean doubleSided,
                        GLTFpbrMetallicRoughness pbrMetallicRoughness,
                        GLTFTexture normalMap,
                        GLTFTexture occlusionMap,
                        GLTFTexture emissiveMap)
    {
        this.doubleSided = doubleSided;
        this.pbrMetallicRoughness = pbrMetallicRoughness;
        this.normalMap = normalMap;
        this.occlusionMap = occlusionMap;
        this.emissiveMap = emissiveMap;
    }

    public static GLTFMaterial fromJSONObject(JSONObject jsonObject, GLTFTexture[] textures) {
        //TODO code this method
        return null;
    }
}
