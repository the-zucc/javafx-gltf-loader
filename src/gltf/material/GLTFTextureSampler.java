package gltf.material;

import gltf.exception.GLTFException;
import org.json.JSONObject;

public class GLTFTextureSampler {
    public final GLTFTextureFilter magnificationFilter;
    public final GLTFTextureFilter minificationFilter;
    public final GLTFTextureWrapMode wrapS;
    public final GLTFTextureWrapMode wrapT;
    public final String name;
    public final JSONObject extras;

    public GLTFTextureSampler(GLTFTextureFilter magnificationFilter,
                       GLTFTextureFilter minificationFilter,
                       GLTFTextureWrapMode wrapS, GLTFTextureWrapMode wrapT,
                       String name,
                       JSONObject extras)
    {
        this.magnificationFilter = magnificationFilter;//TODO check if values respect GLTF schema
        this.minificationFilter = minificationFilter;//TODO check if values respect GLTF schema
        this.wrapS = wrapS;
        this.wrapT = wrapT;
        this.name = name;
        this.extras = extras;
    }

    public static GLTFTextureSampler fromJSONObject(JSONObject jObj) throws GLTFException {
        try{
            //TODO code return statement here
        }catch(Exception e){
            e.printStackTrace();
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }
        return null;
    }
}
