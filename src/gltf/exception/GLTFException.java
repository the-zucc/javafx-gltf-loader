package gltf.exception;

import org.json.JSONObject;

public class GLTFException extends Exception{
    public GLTFException(String message) {
        super(message);
    }

    public static void throwGLTFExceptionWithCause(JSONObject jObj) throws GLTFException {
        throw new GLTFException("There is most probably an error " +
            "in the GLTF file provided. Cause of the error: \n"+jObj);
    }
}
