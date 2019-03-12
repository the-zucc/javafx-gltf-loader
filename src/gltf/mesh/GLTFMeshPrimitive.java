package gltf.mesh;

import gltf.exception.GLTFException;
import gltf.material.GLTFMaterial;
import gltf.exception.InvalidGLTFTypeException;
import gltf.accessor.GLTFAccessor;
import gltf.accessor.GLTFIntAccessor;
import gltf.type.GLTFAccessorType;
import gltf.type.GLTFComponentType;
import org.json.JSONObject;

public class GLTFMeshPrimitive {
    public final GLTFMeshPrimitiveAttributes attributes;
    public final int indicesAccessorIdx;
    public final int materialIdx;
    public final GLTFMaterial material;
    public final int meshMode;
    public final GLTFIntAccessor indicesAccessor;
    public GLTFMeshPrimitive(GLTFMeshPrimitiveAttributes attributes,
                             int indicesAccessorIdx,
                             int materialIdx,
                             int meshMode,
                             GLTFAccessor[] accessors,
                             GLTFMaterial[] materials) throws InvalidGLTFTypeException {
        this.attributes = attributes;

        this.indicesAccessorIdx = indicesAccessorIdx;
        this.indicesAccessor = this.indicesAccessorIdx != -1 ?
            (GLTFIntAccessor)accessors[indicesAccessorIdx]
                .assertType(
                        new GLTFAccessorType[]{
                                GLTFAccessorType.SCALAR
                        },
                        new GLTFComponentType[]{
                                GLTFComponentType.UNSIGNED_INT
                        })
            : null;

        this.materialIdx = materialIdx;
        this.material = materials[materialIdx];
        this.meshMode = meshMode;

    }

    public static GLTFMeshPrimitive fromJSONObject(JSONObject jObj,
                                                   GLTFAccessor[] accessors,
                                                   GLTFMaterial[] materials)
        throws GLTFException {
        try{
            return new GLTFMeshPrimitive(
                GLTFMeshPrimitiveAttributes.fromJSONObject(
                    jObj.getJSONObject("attributes"),
                    accessors
                ),
                jObj.has("indices") ?
                    jObj.getInt("indices")
                    : -1,
                jObj.has("material") ?
                    jObj.getInt("material")
                    : -1,
                jObj.has("mode") ?
                    jObj.getInt("mode")
                    : 4,
                accessors,
                materials
            );
        }catch(ArrayIndexOutOfBoundsException aioobe){
            aioobe.printStackTrace();
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }catch(Exception e){
            e.printStackTrace();
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }
        return null;
    }
}
