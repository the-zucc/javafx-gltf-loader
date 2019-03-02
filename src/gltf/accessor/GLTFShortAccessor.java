package gltf.accessor;

import gltf.accessor.GLTFAccessor;
import gltf.buffer.GLTFBufferView;
import gltf.type.GLTFAccessorType;
import gltf.type.GLTFComponentType;

public class GLTFShortAccessor extends GLTFAccessor {
    public final short[] min;
    public final short[] max;
    public final short[] data;

    public GLTFShortAccessor(GLTFBufferView bufferView,
                             GLTFComponentType componentType,
                             int byteOffset, int nElem,
                             short[] min, short[] max,
                             GLTFAccessorType dataType) {
        super(bufferView, componentType, byteOffset, nElem, dataType);
        this.min = min;
        this.max = max;
        this.data = this.bufferView.getShorts(this.byteOffset, this.nElem);
    }
}
