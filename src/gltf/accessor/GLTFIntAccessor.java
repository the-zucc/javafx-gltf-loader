package gltf.accessor;

import gltf.buffer.GLTFBufferView;
import gltf.type.GLTFAccessorType;
import gltf.type.GLTFComponentType;

public class GLTFIntAccessor extends GLTFAccessor {

    public final int[] min;
    public final int[] max;
    public final int[] data;

    public GLTFIntAccessor(GLTFBufferView bufferView,
                           GLTFComponentType componentType,
                           int byteOffset, int nElem,
                           int[] min, int[] max,
                           GLTFAccessorType dataType) {
        super(bufferView, componentType, byteOffset, nElem, dataType);
        this.min = min;
        this.max = max;
        this.data = this.bufferView.getInts(byteOffset, nElem*this.type.size);
    }
}
