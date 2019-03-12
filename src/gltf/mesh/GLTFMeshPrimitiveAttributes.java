package gltf.mesh;

import gltf.exception.GLTFException;
import gltf.exception.InvalidGLTFTypeException;
import gltf.accessor.GLTFAccessor;
import gltf.accessor.GLTFFloatAccessor;
import gltf.type.GLTFAccessorType;
import gltf.type.GLTFComponentType;
import org.json.JSONObject;

public class GLTFMeshPrimitiveAttributes {
    protected final int positionsAccessorIdx;
    public final GLTFFloatAccessor positionsAccessor;

    protected final int normalsAccessorIdx;
    public final GLTFFloatAccessor normalsAccessor;

    protected final int tangentsAccessorIdx;
    public final GLTFFloatAccessor tangentsAccessor;

    protected final int texCoords0AccessorIdx;
    public final GLTFAccessor texCoords0Accessor;

    protected final int texCoords1AccessorIdx;
    public final GLTFAccessor texCoords1Accessor;

    protected final int color0AccessorIdx;
    public final GLTFAccessor color0Accessor;

    protected final int joints0AccessorIdx;
    public final GLTFAccessor joints0Accessor;

    protected final int weights0AccessorIdx;
    public final GLTFAccessor weights0Accessor;


    public GLTFMeshPrimitiveAttributes(int positionsAccessorIdx,
                                          int normalsAccessorIdx,
                                          int tangentsAccessordIdx,
                                          int texCoords0AccessorIdx,
                                          int texCoords1AccessorIdx,
                                          int color0AccessorIdx,
                                          int joints0AccessorIdx,
                                          int weights0AccessorIdx,
                                          GLTFAccessor[] accessors)
        throws InvalidGLTFTypeException
    {

        this.positionsAccessorIdx = positionsAccessorIdx;
        this.positionsAccessor = this.positionsAccessorIdx != -1 ?
            (GLTFFloatAccessor)(accessors[positionsAccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC3
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT
                }))
            : null;

        this.normalsAccessorIdx = normalsAccessorIdx;
        this.normalsAccessor = this.normalsAccessorIdx != -1 ?
            (GLTFFloatAccessor)(accessors[normalsAccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC3
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT
                }))
            : null;

        this.tangentsAccessorIdx = tangentsAccessordIdx;
        this.tangentsAccessor = this.tangentsAccessorIdx != -1 ?
            (GLTFFloatAccessor)(accessors[tangentsAccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC4
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT
                }))
            : null;

        this.texCoords0AccessorIdx = texCoords0AccessorIdx;
        this.texCoords0Accessor = this.texCoords0AccessorIdx != -1 ?
            accessors[texCoords0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC2
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT,
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                })
            : null;

        this.texCoords1AccessorIdx = texCoords1AccessorIdx;
        this.texCoords1Accessor = this.texCoords1AccessorIdx != -1 ?
            accessors[texCoords0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC2
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT,
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                })
            : null;

        this.color0AccessorIdx = color0AccessorIdx;
        this.color0Accessor = this.color0AccessorIdx != -1 ?
            accessors[color0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC3,
                        GLTFAccessorType.VEC4
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT,
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                })
            : null;

        this.joints0AccessorIdx = joints0AccessorIdx;
        this.joints0Accessor = this.joints0AccessorIdx != -1 ?
            accessors[joints0AccessorIdx].assertType(
            new GLTFAccessorType[]{
                GLTFAccessorType.VEC4
            },
            new GLTFComponentType[]{
                GLTFComponentType.UNSIGNED_BYTE,
                GLTFComponentType.UNSIGNED_SHORT
            })
            : null;

        this.weights0AccessorIdx = weights0AccessorIdx;
        this.weights0Accessor = this.weights0AccessorIdx != -1 ?
            accessors[weights0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC4
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT,
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                })
            : null;
    }

    public static GLTFMeshPrimitiveAttributes fromJSONObject(JSONObject jObj,
                                                             GLTFAccessor[] accessors)
        throws GLTFException
    {
        try{
            return new GLTFMeshPrimitiveAttributes(
                jObj.has("POSITION") ?
                    jObj.getInt("POSITION")
                    : -1,
                jObj.has("NORMAL") ?
                    jObj.getInt("NORMAL")
                    : -1,
                jObj.has("TANGENT") ?
                    jObj.getInt("TANGENT")
                    : -1,
                jObj.has("TEXCOORD_0") ?
                    jObj.getInt("TEXCOORD_0")
                    : -1,
                jObj.has("TEXCOORD_1") ?
                    jObj.getInt("TEXCOORD_1")
                    : -1,
                jObj.has("COLOR_0") ?
                    jObj.getInt("COLOR_0")
                    : -1,
                jObj.has("JOINTS_0") ?
                    jObj.getInt("JOINTS_0")
                    : -1,
                jObj.has("WEIGHTS_0") ?
                    jObj.getInt("WEIGHTS_0")
                    : -1,
                accessors
            );
        }catch(Exception e){
            e.printStackTrace();
            GLTFException.throwGLTFExceptionWithCause(jObj);
        }
        return null;
    }
}
