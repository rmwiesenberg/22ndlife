package boundary.engine.graph;

import org.ejml.simple.SimpleMatrix;
import boundary.engine.items.GameItem;

public class Transformation {

    private final SimpleMatrix modelMatrix;
    
    private final SimpleMatrix modelViewMatrix;

    private final SimpleMatrix modelLightViewMatrix;

    private final SimpleMatrix lightViewMatrix;

    private final SimpleMatrix ortho2DMatrix;

    private final SimpleMatrix orthoModelMatrix;

    public Transformation() {
        modelMatrix = new SimpleMatrix(4, 4);
        modelViewMatrix = new SimpleMatrix(4, 4);
        modelLightViewMatrix = new SimpleMatrix(4, 4);
        ortho2DMatrix = new SimpleMatrix(4, 4);
        orthoModelMatrix = new SimpleMatrix(4, 4);
        lightViewMatrix = new SimpleMatrix(4, 4);
    }

    public SimpleMatrix getLightViewMatrix() {
        return lightViewMatrix;
    }

    public void setLightViewMatrix(SimpleMatrix lightViewMatrix) {
        this.lightViewMatrix.set(lightViewMatrix);
    }

    public SimpleMatrix updateLightViewMatrix(Vector3f position, Vector3f rotation) {
        return updateGenericViewMatrix(position, rotation, lightViewMatrix);
    }

    public static  SimpleMatrix updateGenericViewMatrix(Vector3f position, Vector3f rotation, SimpleMatrix matrix) {
        // First do the rotation so camera rotates over its position
        return matrix.rotationX((float)Math.toRadians(rotation.x))
                     .rotateY((float)Math.toRadians(rotation.y))
                     .translate(-position.x, -position.y, -position.z);
    }

    public final SimpleMatrix getOrtho2DProjectionMatrix(float left, float right, float bottom, float top) {
        return ortho2DMatrix.setOrtho2D(left, right, bottom, top);
    }
    
    public SimpleMatrix buildModelMatrix(GameItem gameItem) {
        Quaternionf rotation = gameItem.getRotation();
        return modelMatrix.translationRotateScale(
                gameItem.getPosition().x, gameItem.getPosition().y, gameItem.getPosition().z,
                rotation.x, rotation.y, rotation.z, rotation.w,
                gameItem.getScale(), gameItem.getScale(), gameItem.getScale());
    }

    public SimpleMatrix buildModelViewMatrix(GameItem gameItem, SimpleMatrix viewMatrix) {
        return buildModelViewMatrix(buildModelMatrix(gameItem), viewMatrix);
    }
    
    public SimpleMatrix buildModelViewMatrix(SimpleMatrix modelMatrix, SimpleMatrix viewMatrix) {
        return viewMatrix.mulAffine(modelMatrix, modelViewMatrix);
    }

    public SimpleMatrix buildModelLightViewMatrix(GameItem gameItem, SimpleMatrix lightViewMatrix) {
        return buildModelViewMatrix(buildModelMatrix(gameItem), lightViewMatrix);
    }

    public SimpleMatrix buildModelLightViewMatrix(SimpleMatrix modelMatrix, SimpleMatrix lightViewMatrix) {
        return lightViewMatrix.mulAffine(modelMatrix, modelLightViewMatrix);
    }

    public SimpleMatrix buildOrthoProjModelMatrix(GameItem gameItem, SimpleMatrix orthoMatrix) {
        return orthoMatrix.mulOrthoAffine(buildModelMatrix(gameItem), orthoModelMatrix);
    }
}
