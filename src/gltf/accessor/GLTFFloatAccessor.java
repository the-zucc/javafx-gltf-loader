package gltf.accessor;

import gltf.buffer.GLTFBufferView;
import gltf.type.GLTFAccessorType;
import gltf.type.GLTFComponentType;

public class GLTFFloatAccessor extends GLTFAccessor {
    public final float[] min;
    public final float[] max;
    public final float[] data;
    public GLTFFloatAccessor(GLTFBufferView bufferView,
                             GLTFComponentType componentType,
                             int byteOffset, int nElem,
                             float[] min, float[] max,
                             GLTFAccessorType dataType) {
        super(bufferView, componentType, byteOffset, nElem, dataType);
        this.min = min;
        this.max = max;
        this.data = this.bufferView.getFloats(this.byteOffset, this.nElem);
    }
}
