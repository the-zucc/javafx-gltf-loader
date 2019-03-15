package gltf.jfx;

import gltf.GLTFAsset;
import gltf.GLTFNode;
import gltf.GLTFScene;
import gltf.accessor.GLTFFloatAccessor;
import gltf.exception.GLTFException;
import gltf.material.GLTFMaterial;
import gltf.mesh.GLTFMesh;
import gltf.mesh.GLTFMeshPrimitive;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Affine;
import javafx.scene.transform.MatrixType;
import javafx.scene.transform.Translate;

import java.io.IOException;

public class JFXGLTFAsset extends GLTFAsset {
    public final Image[] images;
    public final PhongMaterial[] materials;
    public final Group[] meshes;
    public final Group[] scenesRoots;

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
        scenesRoots = new Group[super.scenes.length];
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
            //TODO apply emissiveFactor
        }

        if(gltfMaterial.pbrMetallicRoughness != null) {
            if (gltfMaterial.pbrMetallicRoughness.baseColorTexture != null) {
                material.setDiffuseMap(
                    SwingFXUtils.toFXImage(
                        gltfMaterial.pbrMetallicRoughness
                            .baseColorTexture
                            .texture
                            .source
                            .image,
                        null
                    )
                );
                //TODO apply diffuseFactor into diffuseColor maybe.
            }
            if(gltfMaterial.pbrMetallicRoughness.baseColorFactor != null){
                float[] color = gltfMaterial.pbrMetallicRoughness.baseColorFactor;
                material.setDiffuseColor(
                    new Color(
                        color[0],color[1], color[2], color[3]
                    )
                );
            }
        }
        //TODO apply occlusion when supported by JavaFX.
        return material;
    }
    public static TriangleMesh GLTFMeshPrimitiveToTriangleMesh(GLTFMeshPrimitive primitive){
        TriangleMesh returnVal = new TriangleMesh();
        //TODO
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
            //TODO apply correct texCoord: TEXCOORD_0 or TEXCOORD_1 according to texCoord property.
            //TODO apply correct data types (UNSIGNED_BYTE, FLOAT, UNSIGNED_SHORT)
            returnVal.getTexCoords().addAll(
                ((GLTFFloatAccessor/*TODO PLACHOLDER FOR NOW*/)
                    primitive.attributes.texCoords0Accessor)
                    .data
            );
        }
        return returnVal;
    }
    public static Group GLTFMeshToGroup(GLTFMesh mesh, PhongMaterial[] materials){
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

        return returnVal;
    }
    public Group build3DScene(GLTFScene scene){
        Group returnVal = new Group();

        for (int i = 0; i < scene.nodes.length; i++) {
            returnVal.getChildren().add(build3dNode(scene.nodes[i]));
        }
        //for debug purposes
        returnVal.getChildren().add(new Box(100, 100, 100));
        returnVal.setScaleX(4);
        returnVal.setScaleY(4);
        returnVal.setScaleZ(4);
        return returnVal;
    }
    public Group build3dNode(GLTFNode node){
        Group returnVal = new Group();

        for (int i = 0; i < node.getChildren().length; i++) {
            returnVal.getChildren().add(
                build3dNode(node.getChildren()[i])
            );
        }
        if(node.mesh != null){
            returnVal.getChildren().add(GLTFMeshToGroup(node.mesh, materials));
        }

        //add the node's transform
        Affine transformationMatrix = new Affine();
        double[] mtx = new double[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mtx[i+4*j] = node.transformationMatrix[j+4*i];
            }
        }
        try{
            transformationMatrix.append(mtx, MatrixType.MT_3D_4x4, 0);
        }catch(Exception e){
            //catch an error in the transformation
            System.out.println(node.name);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(" "+mtx[4*i+j]);
                }
                System.out.println("");
            }
            e.printStackTrace();
        }

        transformationMatrix.append(new Translate(
            node.translation[0],
            node.translation[1],
            node.translation[2]
        ));
        returnVal.getTransforms().add(transformationMatrix);
        return returnVal;
    }
    public static void main(String[] args) {
        try {
            //idk why, lol.
            TestApp.launch(
                (Class<? extends Application>)ClassLoader
                    .getSystemClassLoader()
                    .loadClass("gltf.jfx.TestApp")
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
