package dayz.client.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import dayz.common.entities.EntityZombieDayZ;
import dayz.common.misc.Util;

public class RenderZombieDayZ extends RenderLiving
{
    public RenderZombieDayZ(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderZombie(EntityZombieDayZ entityZombie, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entityZombie, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderZombie((EntityZombieDayZ) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderZombie((EntityZombieDayZ) par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity)
    {
        if (entity instanceof EntityZombieDayZ)
        {
            EntityZombieDayZ zombie = (EntityZombieDayZ) entity;
            return new ResourceLocation(Util.ID + ":textures/entities/" + zombie.texture);
        }
        return null;
    }
}
