package gltf.utils;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONUtils {
    public static float[] JSONToFloatArray(JSONArray array) throws JSONException{
        float[] returnVal = new float[array.length()];
        for (int i = 0; i < array.length(); i++) {
            try{
                returnVal[i] = array.getFloat(i);
            }catch(RuntimeException re){
                re.printStackTrace();
                throw new JSONException("invalid type");
            }
        }
        return returnVal;
    }
    public static int[] JSONToIntArray(JSONArray array) throws JSONException{
        int[] returnVal = new int[array.length()];
        for (int i = 0; i < array.length(); i++) {
            try{
                returnVal[i] = array.getInt(i);
            }catch(RuntimeException re){
                re.printStackTrace();
                throw new JSONException("invalid type");
            }
        }
        return returnVal;
    }
}
