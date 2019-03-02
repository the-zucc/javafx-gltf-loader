package gltf.buffer;

import gltf.utils.ByteReader;
import org.json.JSONObject;

/**
 * A bufferView represents a subset of data in a buffer,
 * defined by a byte offset into the buffer specified
 * in the byteOffset property and a total byte length
 * specified by the byteLength property of the buffer view.
 */
public class GLTFBufferView {

    public final GLTFBuffer buffer;

    public final int byteOffset;
    public final int byteLength;
    public final int byteStride;
    public final int target;
    public final String name;

    protected GLTFBufferView(GLTFBuffer buffer,
                             int byteOffset,
                             int byteLength,
                             int byteStride,
                             int target,
                             String name){
        this.buffer = buffer;
        this.byteOffset = byteOffset;
        this.byteLength = byteLength;
        this.byteStride = byteStride;
        this.target = target;
        this.name = name;
    }

    public GLTFBufferView(JSONObject bvObj, GLTFBuffer[] buffers){
        this(
                buffers[bvObj.getInt("buffer")],
                bvObj.getInt("byteOffset"),
                bvObj.getInt("byteLength"),
                bvObj.has("byteStride") ? bvObj.getInt("byteStride") : -1,
                bvObj.has("target") ? bvObj.getInt("target") : -1,
                bvObj.getString("name"));
    }

    /**
     * returns a float array containing a subset of data from the bufferView
     * @param byteOffset the offset
     * @param nElem
     * @return
     */
    public float[] getFloats(int byteOffset, int nElem){
        byte[] bytes = this.buffer.bytes;
        return ByteReader.readFloats(bytes,byteOffset+this.byteOffset, nElem);
    }
    public short[] getShorts(int byteOffset, int nElem){
        byte[] bytes = this.buffer.bytes;
        return ByteReader.readShorts(bytes, byteOffset+this.byteOffset, nElem);
    }
    /**
     * returns a byte array containing the bytes from index byteOffset to
     * index byteOfset+nElem. It calls System.arraycopy.
     * @param byteOffset the offset (relative to the start of the
     *                   bufferView) of the data to get
     *                   from the buffer.
     * @param nElem the number of bytes to read into the returned array
     * @return a copy of the buffer from byteOffset to byteOffset+nElem
     */
    public byte[] getBytes(int byteOffset, int nElem){
        byte[] array = new byte[nElem];
        System.arraycopy(this.buffer.bytes,
                this.byteOffset+byteOffset,
                array, 0, nElem);
        return array;
    }
    /**
     * returns a byte array containing the bytes from index byteOffset to
     * index byteOfset+nElem. It calls System.arraycopy.
     * @param byteOffset the offset (relative to the start of the
     *                   bufferView) of the data to get
     *                   from the buffer.
     * @param nElem the number of bytes to read into the returned array
     * @return a copy of the buffer from byteOffset to byteOffset+nElem
     */
    public int[] getInts(int byteOffset, int nElem) {
        byte[] bytes = this.buffer.bytes;
        return ByteReader.readInts(bytes,byteOffset+this.byteOffset, nElem);
    }
}
