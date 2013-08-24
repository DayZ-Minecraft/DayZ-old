package dayz.client.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dayz.common.entities.EntityBullet;
import dayz.common.misc.Util;

@SideOnly(Side.CLIENT)
public class RenderBullet extends Render
{
    private ModelBullet modelBullet;

    public RenderBullet(ModelBullet modelBase)
    {
        modelBullet = modelBase;
    }

    public void renderBullet(EntityBullet entityBullet, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        float f2 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        func_110777_b(entityBullet);
        modelBullet.render(entityBullet, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f2);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return new ResourceLocation(Util.ID + ":textures/entities/bullet.png");
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method,
     * always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all
     * probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void
     * doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderBullet((EntityBullet) par1Entity, par2, par4, par6, par8, par9);
    }
}
