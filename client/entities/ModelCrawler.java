package dayz.client.entities;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelCrawler extends ModelBase
{
    ModelRenderer Head;
    ModelRenderer Torso;
    ModelRenderer Left_arm;
    ModelRenderer Spine_1;
    ModelRenderer Left_rib_1;
    ModelRenderer Right_rib_1;
    ModelRenderer Right_rib_2;
    ModelRenderer Left_rib_2;
    ModelRenderer Spine_2;
    ModelRenderer Right_arm;

    public ModelCrawler()
    {
        textureWidth = 128;
        textureHeight = 64;
        Head = new ModelRenderer(this, 30, 0);
        Head.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        Head.setRotationPoint(-4F, 14F, -11.5F);
        Head.setTextureSize(128, 64);
        Head.mirror = true;
        setRotation(Head, 0.0F, 0.0F, 0.0F);
        Torso = new ModelRenderer(this, 34, 17);
        Torso.addBox(0.0F, 0.0F, 0.0F, 8, 7, 4);
        Torso.setRotationPoint(-4F, 22F, -4F);
        Torso.setTextureSize(128, 64);
        Torso.mirror = true;
        setRotation(Torso, 1.33843F, 0.0F, 0.0F);
        Left_arm = new ModelRenderer(this, 59, 17);
        Left_arm.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4);
        Left_arm.setRotationPoint(4F, 19F, -2F);
        Left_arm.setTextureSize(128, 64);
        Left_arm.mirror = true;
        setRotation(Left_arm, -1.487144F, 0.0F, 0.0F);
        Spine_1 = new ModelRenderer(this, 44, 29);
        Spine_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        Spine_1.setRotationPoint(-0.5F, 21.5F, 3F);
        Spine_1.setTextureSize(128, 64);
        Spine_1.mirror = true;
        setRotation(Spine_1, 1.33843F, 0.0F, 0.0F);
        Left_rib_1 = new ModelRenderer(this, 49, 30);
        Left_rib_1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        Left_rib_1.setRotationPoint(0.5F, 21.8F, 4F);
        Left_rib_1.setTextureSize(128, 64);
        Left_rib_1.mirror = true;
        setRotation(Left_rib_1, 1.33843F, 0.0F, 0.0F);
        Right_rib_1 = new ModelRenderer(this, 37, 30);
        Right_rib_1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        Right_rib_1.setRotationPoint(-2.5F, 21.8F, 4F);
        Right_rib_1.setTextureSize(128, 64);
        Right_rib_1.mirror = true;
        setRotation(Right_rib_1, 1.33843F, 0.0F, 0.0F);
        Right_rib_2 = new ModelRenderer(this, 37, 33);
        Right_rib_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        Right_rib_2.setRotationPoint(-2.5F, 22.8F, 3.7F);
        Right_rib_2.setTextureSize(128, 64);
        Right_rib_2.mirror = true;
        setRotation(Right_rib_2, 1.33843F, 0.0F, 0.0F);
        Left_rib_2 = new ModelRenderer(this, 51, 33);
        Left_rib_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        Left_rib_2.setRotationPoint(1.5F, 22.8F, 3.7F);
        Left_rib_2.setTextureSize(128, 64);
        Left_rib_2.mirror = true;
        setRotation(Left_rib_2, 1.33843F, 0.0F, 0.0F);
        Spine_2 = new ModelRenderer(this, 44, 33);
        Spine_2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        Spine_2.setRotationPoint(-0.5F, 22F, 4.866667F);
        Spine_2.setTextureSize(128, 64);
        Spine_2.mirror = true;
        setRotation(Spine_2, 0.9666439F, 0.0F, 0.0F);
        Right_arm = new ModelRenderer(this, 17, 17);
        Right_arm.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4);
        Right_arm.setRotationPoint(-8F, 19F, -2F);
        Right_arm.setTextureSize(128, 64);
        Right_arm.mirror = true;
        setRotation(Right_arm, -1.487144F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Head.render(f5);
        Torso.render(f5);
        Left_arm.render(f5);
        Spine_1.render(f5);
        Left_rib_1.render(f5);
        Right_rib_1.render(f5);
        Right_rib_2.render(f5);
        Left_rib_2.render(f5);
        Spine_2.render(f5);
        Right_arm.render(f5);
    }

    private void setRotation(ModelRenderer modelrenderer, float f, float f1, float f2)
    {
        modelrenderer.rotateAngleX = f;
        modelrenderer.rotateAngleY = f1;
        modelrenderer.rotateAngleZ = f2;
    }

    /**
     * Sets the models various rotation angles.
     */
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Left_arm.rotateAngleY = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Right_arm.rotateAngleY = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    }
}
