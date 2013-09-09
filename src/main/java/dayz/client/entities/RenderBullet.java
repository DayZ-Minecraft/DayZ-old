package dayz.client.entities;

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

    public void renderBullet(EntityBullet entityBullet, double x, double y, double z, float yaw, float partialTickTime)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glTranslatef((float) x, (float) y, (float) z);
        float f2 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        bindEntityTexture(entityBullet);
        modelBullet.render(entityBullet, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f2);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return new ResourceLocation(Util.ID + ":textures/entities/bullet.png");
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime)
    {
        renderBullet((EntityBullet) entity, x, y, z, yaw, partialTickTime);
    }


}
