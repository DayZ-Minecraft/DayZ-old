package dayz.client.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import dayz.common.entities.EntityCrawler;
import dayz.common.misc.Util;

public class RenderCrawler extends RenderLiving
{
    public RenderCrawler(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderCrawler(EntityCrawler par1DayZ_EntityCrawler, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1DayZ_EntityCrawler, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderCrawler((EntityCrawler) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderCrawler((EntityCrawler) par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity)
    {
        return new ResourceLocation(Util.ID + ":textures/entities/crawler.png");
    }
}
