package gltf.material;


import org.json.JSONArray;
import org.json.JSONObject;

import static gltf.material.GLTFMaterialAlphaMode.OPAQUE;

public class GLTFMaterial {
    public final boolean doubleSided;
    protected final GLTFpbrMetallicRoughness pbrMetallicRoughness;
    public final GLTFNormalTextureInfo normalMap;
    public final GLTFOcclusionTextureInfo occlusionMap;
    public final GLTFTextureInfo emissiveMap;
    public final float[] emissiveFactor;
    public final GLTFMaterialAlphaMode alphaMode;
    public final float alphaCutoff;
    public GLTFMaterial(String materialName,
                        boolean doubleSided,
                        GLTFpbrMetallicRoughness pbrMetallicRoughness,
                        GLTFNormalTextureInfo normalMap,
                        GLTFOcclusionTextureInfo occlusionMap,
                        GLTFTextureInfo emissiveMap,
                        float[] emissiveFactor,
                        GLTFMaterialAlphaMode alphaMode,
                        float alphaCutoff)
    {
        this.doubleSided = doubleSided;
        this.pbrMetallicRoughness = pbrMetallicRoughness;
        this.normalMap = normalMap;
        this.occlusionMap = occlusionMap;
        this.emissiveMap = emissiveMap;
        this.emissiveFactor = emissiveFactor;
        this.alphaMode = alphaMode;
        this.alphaCutoff = alphaCutoff;
    }

    public static GLTFMaterial fromJSONObject(JSONObject jObj, GLTFTexture[] textures) {
        //TODO code this method
        try{
            float[] emissiveFactor = new float[3];
            if(jObj.has("emissiveFactor")){
                JSONArray jarr = jObj.getJSONArray("emissiveFactor");
                for(int i = 0; i < jarr.length(); i++){
                    emissiveFactor[i] = jarr.getFloat(i);
                }
            }
            return new GLTFMaterial(
                jObj.has("name") ?
                    jObj.getString("name")
                    : "",
                jObj.has("doubleSided") ?
                    jObj.getBoolean("doubleSided")
                    : false,
                jObj.has("pbrMetallicRoughness") ?
                    GLTFpbrMetallicRoughness.fromJSONObject(jObj.getJSONObject("pbrMetallicRoughness"), textures)
                    : null,
                jObj.has("normalTexture") ?
                    GLTFNormalTextureInfo.fromJSONObject(jObj.getJSONObject("normalTexture"), textures)
                    : null,
                jObj.has("occlusionTexture") ?
                    GLTFOcclusionTextureInfo.fromJSONObject(jObj.getJSONObject("occlusionTexture"), textures)
                    : null,
                jObj.has("emissiveTexture") ?
                    GLTFEmissiveTextureInfo.fromJSONObject(jObj.getJSONObject("emissiveTexture"), textures)
                    : null,
                jObj.has("emissiveFactor") ?
                    emissiveFactor
                    : null,
                jObj.has("alphaMode") ?
                    GLTFMaterialAlphaMode.valueOf(jObj.getString("alphaMode"))
                    : OPAQUE,
                jObj.has("alphaCutoff") ?
                    jObj.getFloat("alphaCutoff")
                    : 0.5f
            );
        }catch(Exception e){

        }
        return null;
    }
}
