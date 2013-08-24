package dayz.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dayz.client.entities.ModelBullet;
import dayz.client.entities.ModelCrawler;
import dayz.client.entities.ModelZombieDayZ;
import dayz.client.entities.RenderBullet;
import dayz.client.entities.RenderCrawler;
import dayz.client.entities.RenderGrenade;
import dayz.client.entities.RenderZombieDayZ;
import dayz.common.entities.EntityBullet;
import dayz.common.entities.EntityCrawler;
import dayz.common.entities.EntityGrenade;
import dayz.common.entities.EntityZombieDayZ;

public class ClientProxy
{
    public static void preload(FMLPreInitializationEvent event)
    {

    }

    public static void load(FMLInitializationEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieDayZ.class, new RenderZombieDayZ(new ModelZombieDayZ(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCrawler.class, new RenderCrawler(new ModelCrawler(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet(new ModelBullet()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade());
    }

    public static void postload(FMLPostInitializationEvent event)
    {

    }
}