package dayz.client.entities;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderGrenade extends Render
{
    /**
     * Have the icon index (in items.png) that will be used to render the image.
     * Currently, eggs and snowballs uses this
     * classes.
     */
    private int itemIconIndex;

    public RenderGrenade()
    {
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
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.1F, 0.1F, 0.1F);
        // loadTexture("/dayz/images/items.png");
        Tessellator var10 = Tessellator.instance;
        func_77026_a(var10, itemIconIndex);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    private void func_77026_a(Tessellator par1Tessellator, int par2)
    {
        float var3 = (par2 % 16 * 16 + 0) / 256.0F;
        float var4 = (par2 % 16 * 16 + 16) / 256.0F;
        float var5 = (par2 / 16 * 16 + 0) / 256.0F;
        float var6 = (par2 / 16 * 16 + 16) / 256.0F;
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV(0.0F - var8, 0.0F - var9, 0.0D, var3, var6);
        par1Tessellator.addVertexWithUV(var7 - var8, 0.0F - var9, 0.0D, var4, var6);
        par1Tessellator.addVertexWithUV(var7 - var8, var7 - var9, 0.0D, var4, var5);
        par1Tessellator.addVertexWithUV(0.0F - var8, var7 - var9, 0.0D, var3, var5);
        par1Tessellator.draw();
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
