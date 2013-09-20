package dayz.client.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import dayz.common.entities.EntityCrawler;
import dayz.common.misc.Constants;

public class RenderCrawler extends RenderLiving
{
    public RenderCrawler(ModelBase par1ModelBase, float shadowSize)
    {
        super(par1ModelBase, shadowSize);
    }

    public void renderCrawler(EntityCrawler entityCrawler, double x, double y, double z, float yaw, float partialTickTime)
    {
        super.doRenderLiving(entityCrawler, x, y, z, yaw, partialTickTime);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime)
    {
        renderCrawler((EntityCrawler) entity, x, y, z, yaw, partialTickTime);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation(Constants.ID + ":textures/entities/crawler.png");
    }
}
