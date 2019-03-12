package gltf.jfx;

import gltf.GLTFAsset;
import gltf.exception.GLTFException;
import gltf.exception.InvalidGLTFTypeException;
import gltf.material.GLTFMaterial;
import gltf.mesh.GLTFMesh;
import gltf.mesh.GLTFMeshPrimitive;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

import java.io.IOException;

public class JFXGLTFAsset extends GLTFAsset {
    public final Image[] images;
    public final PhongMaterial[] materials;
    public final Group[] meshes;

    public JFXGLTFAsset(String filePath) throws IOException, GLTFException {
        super(filePath);
        this.images = new Image[super.images.length];
        for (int i = 0; i < super.images.length; i++) {
            this.images[i] = SwingFXUtils.toFXImage(super.images[i].image, null);
        }
        this.materials = new PhongMaterial[super.materials.length];
        for (int i = 0; i < super.materials.length; i++) {
            GLTFMaterial gltfMaterial = super.materials[i];
            this.materials[i] = GLTFMaterialToPhongMaterial(gltfMaterial);
        }
        this.meshes = new Group[super.meshes.length];
        for (int i = 0; i < super.meshes.length; i++) {
            Group meshGroup = new Group();
            GLTFMesh mesh = super.meshes[i];

        }

    }
    public static PhongMaterial GLTFMaterialToPhongMaterial(GLTFMaterial gltfMaterial){
        PhongMaterial material = new PhongMaterial();
        if(gltfMaterial.normalMap != null){
            material.setBumpMap(
                SwingFXUtils.toFXImage(
                    gltfMaterial
                        .normalMap
                        .texture
                        .source
                        .image,
                    null)
            );
        }
        if(gltfMaterial.emissiveMap != null){
            material.setSelfIlluminationMap(
                SwingFXUtils.toFXImage(gltfMaterial.
                        emissiveMap
                        .texture
                        .source
                        .image,
                    null)
            );
        }

        //TODO apply emissiveFactor
        //TODO apply occlusion when supported by JavaFX.
        return material;
    }
    public static TriangleMesh GLTFMeshPrimitiveToTriangleMesh(GLTFMeshPrimitive primitive){
        TriangleMesh returnVal = new TriangleMesh();
        //TODO code this method
        returnVal.getPoints().addAll(
            primitive.attributes.positionsAccessor.data
        );
        if(primitive.indicesAccessor != null){
            returnVal.getFaces().addAll(
                primitive.indicesAccessor.data
            );
        }else{
            System.out.println("No indices were given for the faces. ");
            //TODO fill in the indices array if no indices were given.
        }
        if(primitive.attributes.texCoords0Accessor != null){
            //TODO apply correct texCoord: TEXCOORD_0 or TEXCOORD_1
            //TODO apply correct data types (UNSIGNED_BYTE, FLOAT, UNSIGNED_SHORT)
            returnVal.getFaces().addAll(
                primitive.attributes.texCoords0Accessor
            );
        }
        returnVal.getTexCoords().addAll(

        );

        return returnVal;
    }
    public static MeshView GLTFMeshToGroup(GLTFMesh mesh, PhongMaterial[] materials){
        Group returnVal = new Group();
        for (int i = 0; i < mesh.primitives.length; i++) {
            GLTFMeshPrimitive primitive = mesh.primitives[i];
            MeshView mv = new MeshView(
                GLTFMeshPrimitiveToTriangleMesh(
                    primitive
                )
            );
            mv.setMaterial(
                materials[primitive.materialIdx]
            );
            returnVal.getChildren().add(mv);
        }
    }
    public Group create3DNode(){

    }
}
