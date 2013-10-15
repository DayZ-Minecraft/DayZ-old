package dayz.client.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLoot extends ModelBase
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;

    public ModelLoot()
    {
        textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-1F, 0F, -2F, 16, 3, 1);
        Shape1.setRotationPoint(-6F, 22F, 4F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0.296706F, 0.5934119F, -0.122173F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 3F, -3F, 2, 4, 8);
        Shape2.setRotationPoint(0F, 20F, 0F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0.2443461F, 0.5934119F, 0.7504916F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, -3F, 0F, 3, 11, 1);
        Shape3.setRotationPoint(0F, 22F, 0F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 1.22173F, 0.0174533F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, -7F, 0F, 1, 17, 2);
        Shape4.setRotationPoint(0F, 23F, 0F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 1.37881F, 0.7679449F, 0.0174533F);
        Shape5 = new ModelRenderer(this, 0, 5);
        Shape5.addBox(-6F, -1F, -2F, 13, 2, 3);
        Shape5.setRotationPoint(0F, 23F, 0F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0.1396263F, 0.0349066F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 4, 2, 5);
        Shape6.setRotationPoint(-2F, 22F, -5F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        super.setRotationAngles(par6, par6, par6, par6, par6, par6, par7Entity);
    }

}
