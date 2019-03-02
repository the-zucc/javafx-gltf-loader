package gltf;

public class Constants {
    private static final String IMAGE_LOAD_ERROR =
            "there was a problem loading the image " +
            "from %error_source% into GLTFImage %target_image%. This is either a " +
            "problem with the provided GLTF file, or a bug. If " +
            "you believe this is a bug, please file a bug report at:" +
            "https://github.com/the-zucc/javafx-gltf-loader. Thank you!";

    public static final String ILE_BINARY_SOURCE_PLACEHOLDER = "%buffer_view%";
    private static final String ILE_BINARY_SOURCE = "bufferView "+ILE_BINARY_SOURCE_PLACEHOLDER;
    public static final String IMAGE_LOAD_ERROR_BINARY = IMAGE_LOAD_ERROR.replace(
            "%error_source%",
            ILE_BINARY_SOURCE
    );
    public static final String ILE_EXTERNAL_SOURCE_PLACHOLDER = "%image_path%";
    private static final String ILE_EXTERNAL_SOURCE = "path "+ILE_EXTERNAL_SOURCE_PLACHOLDER;
    public static final String IMAGE_LOAD_ERROR_EXTERNAL = IMAGE_LOAD_ERROR.replace(
            "%error_source%",
            ILE_EXTERNAL_SOURCE
    );
}
