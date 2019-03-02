package gltf.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteReader {
    private static ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
    public static void setByteOrder(ByteOrder byteOrder){
        ByteReader.byteOrder = byteOrder;
    }
    /**
     * wrapper function to get a short array from a byte array. Uses the
     * class' default byte order
     * @param bytes the byte array
     * @return a short array of length bytes.length/2 containing the values
     * from the specified byte array
     */
    public static short[] readShorts(byte[] bytes){
        return readShorts(bytes, 0, bytes.length/2);
    }

    /**
     * wrapper function to get a short array from a byte array.
     * @param bytes the byte array
     * @param b the byte order of the byte array (little-endian, or big-endian)
     * @return a short array of length bytes.length/2 containing the values
     * from the specified byte array
     */
    public static short[] readShorts(byte[] bytes, ByteOrder b){
        return readShorts(bytes, 0, bytes.length/2, b);
    }
    /**
     * wrapper function to get a short array from a byte array.
     * @param bytes the byte array
     * @param off the byte offset where to read
     * @param nElem  the number of elements to read from tne byte array
     * @return @return a short array of length nElem containing the values
     * from the specified byte array
     */
    public static short[] readShorts(byte[] bytes, int off, int nElem){
        return readShorts(bytes, off, nElem, byteOrder);
    }

    /**
     * TODO test this method
     * wrapper function to get a short array from a byte array.
     * @param bytes the byte array
     * @param off the byte offset where to read
     * @param nElem  the number of elements to read from tne byte array
     * @param b the byte order of the byte array (little-endian, or big-endian)
     * @return a short array of length nElem containing the values
     * from the specified byte array
     */
    public static short[] readShorts(byte[] bytes, int off, int nElem, ByteOrder b){
        short[] shorts = new short[nElem];
        ((ByteBuffer)ByteBuffer.wrap(bytes).position(off))
                .order(byteOrder).asShortBuffer()
                .get(shorts, 0, nElem);
        for (short f :
                shorts) {
            System.out.println(f);
        }
        return shorts;
    }

    /**
     * wrapper function to get a float array from a byte array. Uses the
     * class' default byte order
     * @param bytes the byte array
     * @return a float array of length bytes.length/4 containing the values
     * from the specified byte array
     */
    public static float[] readFloats(byte[] bytes){
        return readFloats(bytes, 0, bytes.length/4);
    }

    /**
     * wrapper function to get a float array from a byte array.
     * @param bytes the byte array
     * @param b the byte order of the byte array (little-endian, or big-endian)
     * @return a float array of length bytes.length/4 containing the values
     * from the specified byte array
     */
    public static float[] readFloats(byte[] bytes, ByteOrder b){
        return readFloats(bytes, 0, bytes.length/4, b);
    }

    /**
     * TODO test this method
     * wrapper function to get a float array from a byte array.
     * @param bytes the byte array
     * @param off the byte offset where to read
     * @param nElem  the number of elements to read from tne byte array
     * @param b the byte order of the byte array (little-endian, or big-endian)
     * @return a float array of length nElem containing the values
     * from the specified byte array
     */
    public static float[] readFloats(byte[] bytes, int off, int nElem, ByteOrder b){
        float[] floats = new float[nElem];
        ((ByteBuffer)ByteBuffer.wrap(bytes).position(off))
                .order(byteOrder).asFloatBuffer()
                .get(floats, 0, nElem);
        for (float f :
                floats) {
            System.out.println(f);
        }
        return floats;
    }

    /**
     * wrapper function to get a float array from a byte array.
     * @param bytes the byte array
     * @param off the byte offset where to read
     * @param nElem the number of elements to read from tne byte array
     * @return a float array of length nElem containing the values
     * from the specified byte array
     */
    public static float[] readFloats(byte[] bytes, int off, int nElem) {
        return readFloats(bytes, off, nElem, byteOrder);
    }

    public static int[] readInts(byte[] bytes){
        return readInts(bytes, 0, bytes.length/2);
    }

    public static int[] readInts(byte[] bytes, ByteOrder b){
        return readInts(bytes, 0, bytes.length/2, b);
    }

    public static int[] readInts(byte[] bytes, int off, int nElem) {
        return readInts(bytes, off, nElem, byteOrder);
    }

    private static int[] readInts(byte[] bytes, int off, int nElem, ByteOrder byteOrder) {
        int[] ints = new int[nElem];
        ((ByteBuffer)ByteBuffer.wrap(bytes).position(off))
                .order(byteOrder).asIntBuffer()
                .get(ints, 0, nElem);
        for (int i :
                ints) {
            System.out.println(i);
        }
        return ints;
    }
}
