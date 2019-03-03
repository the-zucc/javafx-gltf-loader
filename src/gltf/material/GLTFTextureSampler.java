package gltf.material;

import org.json.JSONObject;

public class GLTFTextureSampler {
    public final int magnificationFilter;
    public final int minificationFilter;
    public final int wrapS;
    public final int wrapT;
    public final String name;
    public final JSONObject extras;

    public GLTFTextureSampler(int magnificationFilter,
                       int minificationFilter,
                       int wrapS, int wrapT,
                       String name,
                       JSONObject extras)
    {
        this.magnificationFilter = magnificationFilter;
        this.minificationFilter = minificationFilter;
        this.wrapS = wrapS;
        this.wrapT = wrapT;
        this.name = name;
        this.extras = extras;
    }
}
