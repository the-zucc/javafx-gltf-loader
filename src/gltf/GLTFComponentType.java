package gltf;

public enum GLTFComponentType {
    BYTE(5120),
    UNSIGNED_BYTE(5121),
    SHORT(5122),
    UNSIGNED_SHORT(5123),
    UNSIGNED_INT(5125),
    FLOAT(5126);
    public final int type;
    GLTFComponentType(int i) {
        this.type = i;
    }

    /**
     * returns the componentType from the id specified.
     * Uses a switch so is complexity should in theory
     * be O(1).
     * @param id the ID of the componentType
     * @return the corresponding GLTFComponentType
     */
    public static GLTFComponentType fromTypeId(int id) throws InvalidGLTFTypeException{
        switch (id){
            case 5120:
                return BYTE;
            case 5121:
                return UNSIGNED_BYTE;
            case 5122:
                return SHORT;
            case 5123:
                return UNSIGNED_SHORT;
            case 5125:
                return UNSIGNED_INT;
            case 5126:
                return FLOAT;
        }
        throw new InvalidGLTFTypeException();
    }
}
