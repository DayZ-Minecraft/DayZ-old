package dayz.client;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dayz.client.entities.ModelBullet;
import dayz.client.entities.ModelCrawler;
import dayz.client.entities.ModelZombieDayZ;
import dayz.client.entities.RenderBullet;
import dayz.client.entities.RenderCrawler;
import dayz.client.entities.RenderZombieDayZ;
import dayz.common.CommonProxy;
import dayz.common.entities.EntityBullet;
import dayz.common.entities.EntityCrawler;
import dayz.common.entities.EntityZombieDayZ;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preload(FMLPreInitializationEvent event)
    {
        super.preload(event);
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        GameRegistry.registerPlayerTracker(new ClientPlayerHandler());
    }

    @Override
    public void load(FMLInitializationEvent event)
    {
        super.load(event);
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieDayZ.class, new RenderZombieDayZ(new ModelZombieDayZ(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCrawler.class, new RenderCrawler(new ModelCrawler(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet(new ModelBullet()));
    }

    @Override
    public void postload(FMLPostInitializationEvent event)
    {
        super.postload(event);
    }
}