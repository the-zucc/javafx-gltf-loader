package gltf.material;

import gltf.exception.GLTFException;
import org.json.JSONObject;

public class GLTFNormalTextureInfo extends GLTFTextureInfo{

    protected GLTFNormalTextureInfo(GLTFTexture texture,
                                    int texCoord,
                                    float scale,
                                    JSONObject extras) {
        super(texture, texCoord, extras);
    }

    public static GLTFNormalTextureInfo fromJSONObject(JSONObject jObj,
                                                       GLTFTexture[] textures)
            throws GLTFException
    {
        try{
            return new GLTFNormalTextureInfo(
                textures[jObj.getInt("index")],
                jObj.has("texCoord") ?
                    jObj.getInt("texCoord")
                    : 0,
                jObj.has("scale") ?
                    jObj.getFloat("scale")
                    : 1,
                jObj.has("extras") ?
                    jObj.getJSONObject("extras")
                    : null
                );
        }catch(NullPointerException npe){
            //catch the NullPointerException thrown by JSONObject.getInt() in case of a missing index property
            npe.printStackTrace();
            System.out.println("Index property missing from JSONObject:\n"+jObj.toString(1));
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }catch(Exception e){
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }
        return null;
    }
}
