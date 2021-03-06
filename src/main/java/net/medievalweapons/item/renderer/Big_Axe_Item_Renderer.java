package net.medievalweapons.item.renderer;

import net.medievalweapons.entity.model.Big_Axe_Entity_Model;
import net.medievalweapons.entity.renderer.Big_Axe_Entity_Renderer;
import net.medievalweapons.item.Big_Axe_Item;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public enum Big_Axe_Item_Renderer {
  INSTANCE;

  private final Big_Axe_Entity_Model big_Axe_Entity_Model = new Big_Axe_Entity_Model();

  public boolean render(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded,
      MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model) {
    if (renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND
        || renderMode == ModelTransformation.Mode.FIXED) {
      return false;
    }

    matrices.push();

    model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
    if (!entity.getOffHandStack().isEmpty()) {
      matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(-90.0F));
      matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
      matrices.translate(-0.25D, 0.75D, 0.0D);
    } else {
      if (renderMode == ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND
          || renderMode == ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND) {
        matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(-110.0F));
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-20.0F));
        matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(50.0F));
        matrices.translate(-0.1D, 1.6D, 0.3D);
      } else {
        matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(180.0F));
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-30.0F));
        matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(50.0F));
        matrices.translate(0.0D, 1.3D, 0.3D);
      }
    }
    matrices.scale(1.0F, -1.0F, -1.0F);
    VertexConsumer spear = ItemRenderer.getItemGlintConsumer(vertexConsumers, this.big_Axe_Entity_Model.getLayer(
        Big_Axe_Entity_Renderer.getTexture(((Big_Axe_Item) stack.getItem()).getType())), false, stack.hasGlint());
    this.big_Axe_Entity_Model.render(matrices, spear, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);

    matrices.pop();
    return true;
  }
}