package gltf.mesh;

import gltf.material.GLTFMaterial;
import gltf.exception.InvalidGLTFTypeException;
import gltf.accessor.GLTFAccessor;
import gltf.accessor.GLTFIntAccessor;
import gltf.type.GLTFAccessorType;
import gltf.type.GLTFComponentType;

public class GLTFMeshPrimitive {
    protected final GLTFMeshPrimitiveAttributes attributes;
    protected final int indicesAccessorIdx;
    protected final int materialIdx;
    protected final int meshMode;
    protected final GLTFIntAccessor indicesAccessor;
    public GLTFMeshPrimitive(GLTFMeshPrimitiveAttributes attributes,
                             int indicesAccessorIdx,
                             int materialIdx,
                             int meshMode,
                             GLTFAccessor[] accessors,
                             GLTFMaterial[] materials) throws InvalidGLTFTypeException {
        this.attributes = attributes;

        this.indicesAccessorIdx = indicesAccessorIdx;
        this.indicesAccessor = (GLTFIntAccessor)accessors[indicesAccessorIdx]
                .assertType(
                        new GLTFAccessorType[]{
                                GLTFAccessorType.SCALAR
                        },
                        new GLTFComponentType[]{
                                GLTFComponentType.UNSIGNED_INT
                        });

        this.materialIdx = materialIdx;
        this.meshMode = meshMode;
    }
}
