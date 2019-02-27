package gltf;

import org.json.JSONArray;
import org.json.JSONObject;

public class GLTFAccessor<CType extends Number> {
    protected final GLTFBufferView bufferView;
    protected final GLTFComponentType componentType;
    protected final CType[] min;
    protected final CType[] max;
    protected final int nElem;
    protected final CType[] data;
    protected final GLTFDataType type;
    protected final int byteOffset;

    protected GLTFAccessor(GLTFBufferView bufferView,
                           GLTFComponentType componentType,
                           int byteOffset,
                           int nElem,
                           CType[] min,
                           CType[] max,
                           GLTFDataType type){
        this.bufferView = bufferView;
        this.componentType = componentType;
        this.byteOffset = byteOffset;
        this.min = min;
        this.max = max;
        this.type = type;
        this.nElem = nElem;
        this.data = (CType[])new Object[this.nElem];
    }

    /**
     * creates a GLTF accessor from a JSONObject of the
     * "accessors" property of the GLTF's root.
     * @param jObj the JSONObject to create the GLTFAccessor object from
     * @param bufferViews the bufferViews array
     * @return the created GLTFAcccessor object
     */
    protected static GLTFAccessor fromJSONObject(JSONObject jObj,
                                                 GLTFBufferView[] bufferViews)
            throws InvalidGLTFTypeException
    {
        GLTFComponentType componentType = GLTFComponentType.fromTypeId(
                jObj.getInt("componentType")
        );
        GLTFDataType dataType = GLTFDataType.valueOf(
                String.valueOf(jObj.get("type"))
        );
        int bufferViewIdx = jObj.getInt("bufferView");
        GLTFBufferView bufferView = bufferViews[bufferViewIdx];

        int byteOffset = jObj.has("byteOffset") ?
                        jObj.getInt("byteOffset") : 0;

        int nElem = jObj.getInt("count");

        JSONArray minArray = jObj.getJSONArray("min");
        JSONArray maxArray = jObj.getJSONArray("max");

        switch(componentType){
            case BYTE:
            case UNSIGNED_BYTE:
                //edit if a case is seen where
                //an accessor uses the BYTE componentType
                return null;
            case SHORT:
            case UNSIGNED_SHORT:

                Short[] minShort = new Short[dataType.size];
                Short[] maxShort = new Short[dataType.size];
                for (int i = 0; i < dataType.size; i++) {
                    minShort[i] = (short)minArray.getInt(i);
                    maxShort[i] = (short)maxArray.getInt(i);
                }
                return new GLTFAccessor<Short>(bufferView,
                        componentType,
                        byteOffset,
                        nElem,
                        minShort,
                        maxShort,
                        dataType);
            case UNSIGNED_INT:
                Integer[] minInt = new Integer[dataType.size];
                Integer[] maxInt = new Integer[dataType.size];
                for (int i = 0; i < dataType.size; i++) {
                    minInt[i] = minArray.getInt(i);
                    maxInt[i] = maxArray.getInt(i);
                }
                return new GLTFAccessor<Integer>(bufferView,
                        componentType,
                        byteOffset,
                        nElem,
                        minInt,
                        maxInt,
                        dataType);
            case FLOAT:
                Float[] minFloat = new Float[dataType.size];
                Float[] maxFloat = new Float[dataType.size];
                for (int i = 0; i < dataType.size; i++) {
                    minFloat[i] = minArray.getFloat(i);
                    maxFloat[i] = maxArray.getFloat(i);
                }
                return new GLTFAccessor<Float>(bufferView,
                        componentType,
                        byteOffset,
                        nElem,
                        minFloat,
                        maxFloat,
                        dataType);
        }
        return null;
    }
}
