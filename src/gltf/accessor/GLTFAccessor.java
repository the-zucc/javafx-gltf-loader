package gltf.accessor;

import gltf.buffer.GLTFBufferView;
import gltf.exception.InvalidGLTFTypeException;
import gltf.type.GLTFAccessorType;
import gltf.type.GLTFComponentType;
import org.json.JSONArray;
import org.json.JSONObject;

public class GLTFAccessor {
    protected final GLTFBufferView bufferView;
    protected final GLTFComponentType componentType;
    protected final int nElem;
    protected final GLTFAccessorType type;
    protected final int byteOffset;

    protected GLTFAccessor(GLTFBufferView bufferView,
                           GLTFComponentType componentType,
                           int byteOffset,
                           int nElem,
                           GLTFAccessorType type){
        this.bufferView = bufferView;
        this.componentType = componentType;
        this.byteOffset = byteOffset;
        this.type = type;
        this.nElem = nElem;
    }

    /**
     * creates a GLTF accessor from a JSONObject of the
     * "accessors" property of the GLTF's root.
     * @param jObj the JSONObject to create the GLTFAccessor object from
     * @param bufferViews the bufferViews array
     * @return the created GLTFAcccessor object
     */
    public static GLTFAccessor fromJSONObject(JSONObject jObj,
                                              GLTFBufferView[] bufferViews)
            throws InvalidGLTFTypeException
    {
        GLTFComponentType componentType = GLTFComponentType.fromTypeId(
                jObj.getInt("componentType")
        );
        GLTFAccessorType dataType = GLTFAccessorType.valueOf(
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

                short[] minShort = new short[dataType.size];
                short[] maxShort = new short[dataType.size];
                for (int i = 0; i < dataType.size; i++) {
                    minShort[i] = (short)minArray.getInt(i);
                    maxShort[i] = (short)maxArray.getInt(i);
                }
                return new GLTFShortAccessor(bufferView,
                        componentType,
                        byteOffset,
                        nElem,
                        minShort,
                        maxShort,
                        dataType);
            case UNSIGNED_INT:
                int[] minInt = new int[dataType.size];
                int[] maxInt = new int[dataType.size];
                for (int i = 0; i < dataType.size; i++) {
                    minInt[i] = minArray.getInt(i);
                    maxInt[i] = maxArray.getInt(i);
                }
                return new GLTFIntAccessor(bufferView,
                        componentType,
                        byteOffset,
                        nElem,
                        minInt,
                        maxInt,
                        dataType);
            case FLOAT:
                float[] minFloat = new float[dataType.size];
                float[] maxFloat = new float[dataType.size];
                for (int i = 0; i < dataType.size; i++) {
                    minFloat[i] = minArray.getFloat(i);
                    maxFloat[i] = maxArray.getFloat(i);
                }
                return new GLTFFloatAccessor(bufferView,
                        componentType,
                        byteOffset,
                        nElem,
                        minFloat,
                        maxFloat,
                        dataType);
        }
        return null;
    }

    /**
     * ensures that the accessor is of the expected types.
     * @param accessorTypes the expected types of the accessor:
     *                     SCALAR, VECT2, VECT3, etc
     * @param componentTypes the expected possible component types
     *                       of the accessor: FLOAT, USINGED_INT, SHORT, etc
     * @return the accessor if the types are as expected
     * @throws InvalidGLTFTypeException if the types are not as expected
     */
    public GLTFAccessor assertType(GLTFAccessorType[] accessorTypes,
                                   GLTFComponentType[] componentTypes) throws InvalidGLTFTypeException{
        boolean rightComponentType = false;
        for(GLTFComponentType componentType: componentTypes){
            if(this.componentType == componentType){
                rightComponentType = true; break;
            }
        }
        boolean rightAccessorType = false;
        for(GLTFAccessorType accessorType: accessorTypes){
            if(this.type == accessorType){
                rightAccessorType = true; break;
            }
        }
        if(!rightAccessorType || !rightComponentType)
            throw new InvalidGLTFTypeException("type check for accessor "+this+" failed");
        return this;
    }
}
