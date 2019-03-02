package gltf.mesh;

import gltf.exception.InvalidGLTFTypeException;
import gltf.accessor.GLTFAccessor;
import gltf.accessor.GLTFFloatAccessor;
import gltf.type.GLTFAccessorType;
import gltf.type.GLTFComponentType;

public class GLTFMeshPrimitiveAttributes {
    protected final int positionsAccessorIdx;
    protected final GLTFFloatAccessor positionsAccessor;

    protected final int normalsAccessorIdx;
    protected final GLTFFloatAccessor normalsAccessor;

    protected final int tangentsAccessordIdx;
    protected final GLTFFloatAccessor tangentsAccessor;

    protected final int texCoords0AccessorIdx;
    protected final GLTFAccessor texCoords0Accessor;

    protected final int texCoords1AccessorIdx;
    protected final GLTFAccessor texCoords1Accessor;

    protected final int color0AccessorIdx;
    protected final GLTFAccessor color0Accessor;

    protected final int joints0AccessorIdx;
    protected final GLTFAccessor joints0Accessor;

    protected final int weights0AccessorIdx;
    protected final GLTFAccessor weights0Accessor;


    protected GLTFMeshPrimitiveAttributes(int positionsAccessorIdx,
                                          int normalsAccessorIdx,
                                          int tangentsAccessordIdx,
                                          int texCoords0AccessorIdx,
                                          int texCoords1AccessorIdx,
                                          int color0AccessorIdx,
                                          int joints0AccessorIdx,
                                          int weights0AccessorIdx,
                                          GLTFAccessor[] accessors) throws InvalidGLTFTypeException {

        this.positionsAccessorIdx = positionsAccessorIdx;
        this.positionsAccessor = (GLTFFloatAccessor)(accessors[positionsAccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.MAT3
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT
                }));

        this.normalsAccessorIdx = normalsAccessorIdx;
        this.normalsAccessor = (GLTFFloatAccessor)(accessors[normalsAccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC3
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT
                }));

        this.tangentsAccessordIdx = tangentsAccessordIdx;
        this.tangentsAccessor = (GLTFFloatAccessor)(accessors[normalsAccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC4
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT
                }
        ));

        this.texCoords0AccessorIdx = texCoords0AccessorIdx;
        this.texCoords0Accessor = accessors[texCoords0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC2
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT,
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                }
        );

        this.texCoords1AccessorIdx = texCoords1AccessorIdx;
        this.texCoords1Accessor = accessors[texCoords0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC2
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT,
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                }
        );

        this.color0AccessorIdx = color0AccessorIdx;
        this.color0Accessor = accessors[color0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC3,
                        GLTFAccessorType.VEC4
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT,
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                }
        );

        this.joints0AccessorIdx = joints0AccessorIdx;
        this.joints0Accessor = accessors[joints0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC4
                },
                new GLTFComponentType[]{
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                }
        );

        this.weights0AccessorIdx = weights0AccessorIdx;
        this.weights0Accessor = accessors[weights0AccessorIdx].assertType(
                new GLTFAccessorType[]{
                        GLTFAccessorType.VEC4
                },
                new GLTFComponentType[]{
                        GLTFComponentType.FLOAT,
                        GLTFComponentType.UNSIGNED_BYTE,
                        GLTFComponentType.UNSIGNED_SHORT
                }
        );
    }
}
