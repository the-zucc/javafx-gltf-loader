package gltf.material;


import gltf.accessor.GLTFAccessor;
import org.json.JSONObject;

public class GLTFMaterial {
    protected final GLTFpbrMetallicRoughness pbrMetallicRoughness;
    public final GLTFImage emissiveMap;
    public final GLTFImage normalMap;
    public final GLTFImage occlusionMap;

    public GLTFMaterial(String materialName,
                        GLTFpbrMetallicRoughness pbrMetallicRoughness,
                        GLTFImage normalMap,
                        GLTFImage occlusionMap,
                        GLTFImage emissiveMap
                        )
    {
        this.pbrMetallicRoughness = pbrMetallicRoughness;
        this.normalMap = normalMap;
        this.occlusionMap = occlusionMap;
        this.emissiveMap = emissiveMap;
    }

    public static GLTFMaterial fromJSONObject(JSONObject jsonObject, GLTFImage[] images) {
        //TODO do this here
    }
}
