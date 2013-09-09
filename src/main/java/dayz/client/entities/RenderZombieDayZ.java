package dayz.client.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import dayz.common.entities.EntityZombieDayZ;
import dayz.common.misc.Util;

public class RenderZombieDayZ extends RenderLiving
{
    public RenderZombieDayZ(ModelBase par1ModelBase, float shadowSize)
    {
        super(par1ModelBase, shadowSize);
    }

    public void renderZombie(EntityZombieDayZ entityZombie, double x, double y, double z, float yaw, float partialTickTime)
    {
        super.doRenderLiving(entityZombie, x, y, z, yaw, partialTickTime);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime)
    {
        renderZombie((EntityZombieDayZ) entity, x, y, z, yaw, partialTickTime);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        if (entity instanceof EntityZombieDayZ)
        {
            EntityZombieDayZ zombie = (EntityZombieDayZ) entity;
            return new ResourceLocation(Util.ID + ":textures/entities/" + zombie.texture);
        }
        return null;
    }
}
