package gltf.material;

import org.json.JSONObject;

public class GLTFTexture {
    public final GLTFTextureSampler sampler;
    public final GLTFImage source;
    public final String name;
    public final JSONObject extras;

    public GLTFTexture(GLTFTextureSampler sampler, GLTFImage source, String name, JSONObject extras) {
        this.sampler = sampler;
        this.source = source;
        this.name = name;
        this.extras = extras;
    }
    public static GLTFTexture fromJSONObject(JSONObject jObj,
                                             GLTFImage[] images,
                                             GLTFTextureSampler[] samplers){
        return new GLTFTexture(
            jObj.has("sampler") ?
                samplers[jObj.getInt("sampler")]
                : null,
            jObj.has("source") ?
                images[jObj.getInt("source")]
                : null,
            jObj.has("name") ?
                jObj.getString("name")
                : "",
            jObj.has("extras") ?
                jObj.getJSONObject("extras")
                : null
        );
    }
}
