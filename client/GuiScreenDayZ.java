package dayz.client;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import dayz.DayZ;

public class GuiScreenDayZ extends GuiIngame
{
    private static final RenderItem itemRenderer = new RenderItem();
    private final Random rand = new Random();
    private final Minecraft mc;

    /** ChatGUI instance that retains all previous chat data */
    private final GuiNewChat persistantChatGUI;
    private int updateCounter = 0;

    /** The string specifying which record music is playing */
    private String recordPlaying = "";

    /** How many ticks the record playing message will be displayed */
    private int recordPlayingUpFor = 0;
    private boolean recordIsPlaying = false;

    /** Previous frame vignette brightness (slowly changes by 1% each frame) */
    public float prevVignetteBrightness = 1.0F;

    public GuiScreenDayZ(Minecraft par1Minecraft)
    {
    	super(par1Minecraft);
        this.mc = par1Minecraft;
        this.persistantChatGUI = new GuiNewChat(par1Minecraft);
    }

    /**
     * Render the ingame overlay with quick icon bar, ...
     */
    public void renderGameOverlay(float par1, boolean par2, int par3, int par4)
    {
        ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        FontRenderer var8 = this.mc.fontRenderer;
        this.mc.entityRenderer.setupOverlayRendering();
        GL11.glEnable(GL11.GL_BLEND);

        if (Minecraft.isFancyGraphicsEnabled())
        {
            this.renderVignette(this.mc.thePlayer.getBrightness(par1), var6, var7);
        }
        else
        {
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        }

        ItemStack var9 = this.mc.thePlayer.inventory.armorItemInSlot(3);

        if (this.mc.gameSettings.thirdPersonView == 0 && var9 != null && var9.itemID == Block.pumpkin.blockID)
        {
            this.renderPumpkinBlur(var6, var7);
        }

        if (!this.mc.thePlayer.isPotionActive(Potion.confusion))
        {
            float var10 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * par1;

            if (var10 > 0.0F)
            {
                this.renderPortalOverlay(var10, var6, var7);
            }
        }

        int var11;
        int var12;
        int var13;
        int var14;
        int var15;
        int var17;
        int var16;
        int var19;
        int var18;
        String var31;
        boolean var33;
        byte var48;

        if (!this.mc.playerController.func_78747_a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/gui.png"));
            InventoryPlayer var20 = this.mc.thePlayer.inventory;
            this.zLevel = -90.0F;
            this.drawTexturedModalRect(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
            this.drawTexturedModalRect(var6 / 2 - 91 - 1 + var20.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/dayz/images/icons.png"));
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
            this.drawTexturedModalRect(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(GL11.GL_BLEND);
            var33 = this.mc.thePlayer.hurtResistantTime / 3 % 2 == 1;

            if (this.mc.thePlayer.hurtResistantTime < 10)
            {
                var33 = false;
            }

            var11 = this.mc.thePlayer.getHealth();
            var12 = this.mc.thePlayer.prevHealth;
            this.rand.setSeed((long)(this.updateCounter * 312871));
            boolean var21 = false;
            FoodStats var22 = this.mc.thePlayer.getFoodStats();
            var14 = var22.getFoodLevel();
            var13 = var22.getPrevFoodLevel();
            this.mc.mcProfiler.startSection("bossHealth");
            this.renderBossHealth();
            this.mc.mcProfiler.endSection();
            int var23;
            int var25;
            int var27;
            int var28;
            boolean var30;

            if (this.mc.playerController.shouldDrawHUD())
            {
                var23 = var6 / 2 - 91;
                var15 = var6 / 2 + 91;
                this.mc.mcProfiler.startSection("expBar");
                var16 = this.mc.thePlayer.xpBarCap();

                if (var16 > 0)
                {
                    short var24 = 182;
                    var18 = (int)(this.mc.thePlayer.experience * (float)(var24 + 1));
                    var17 = var7 - 32 + 3;

                    if (var18 > 0)
                    {
                        ;
                    }
                }

                var19 = var7 - 39;
                var18 = var19 - 10;
                var17 = this.mc.thePlayer.getTotalArmorValue();
                int var42 = -1;

                if (this.mc.thePlayer.isPotionActive(Potion.regeneration))
                {
                    var42 = this.updateCounter % 25;
                }

                this.mc.mcProfiler.endStartSection("healthArmor");
                int var43;
                int var10000;

                for (var25 = 0; var25 < 10; ++var25)
                {
                    if (var17 > 0)
                    {
                        var10000 = var23 + var25 * 8;

                        if (var25 * 2 + 1 < var17)
                        {
                            ;
                        }

                        if (var25 * 2 + 1 == var17)
                        {
                            ;
                        }

                        if (var25 * 2 + 1 > var17)
                        {
                            ;
                        }
                    }

                    byte var26 = 16;

                    if (this.mc.thePlayer.isPotionActive(Potion.poison))
                    {
                        var43 = var26 + 36;
                    }

                    boolean var29 = false;

                    if (var33)
                    {
                        var29 = true;
                    }

                    var10000 = var23 + var25 * 8;
                    var27 = var19;

                    if (var11 <= 4)
                    {
                        var27 = var19 + this.rand.nextInt(2);
                    }

                    if (var25 == var42)
                    {
                        var27 -= 2;
                    }

                    var30 = false;

                    if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled())
                    {
                        var30 = true;
                    }

                    if (var33)
                    {
                        if (var25 * 2 + 1 < var12)
                        {
                            ;
                        }

                        if (var25 * 2 + 1 == var12)
                        {
                            ;
                        }
                    }

                    if (var25 * 2 + 1 < var11)
                    {
                        ;
                    }

                    if (var25 * 2 + 1 == var11)
                    {
                        ;
                    }
                }

                this.mc.mcProfiler.endStartSection("food");
                int var47;

                for (var25 = 0; var25 < 10; ++var25)
                {
                    var48 = 16;
                    var30 = false;

                    if (this.mc.thePlayer.isPotionActive(Potion.hunger))
                    {
                        var47 = var48 + 36;
                        var30 = true;
                    }

                    if (this.mc.thePlayer.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (var14 * 3 + 1) == 0)
                    {
                        var10000 = var19 + (this.rand.nextInt(3) - 1);
                    }

                    if (var21)
                    {
                        var30 = true;
                    }

                    var27 = var15 - var25 * 8 - 9;

                    if (var21)
                    {
                        if (var25 * 2 + 1 < var13)
                        {
                            ;
                        }

                        if (var25 * 2 + 1 == var13)
                        {
                            ;
                        }
                    }

                    if (var25 * 2 + 1 < var14)
                    {
                        ;
                    }

                    if (var25 * 2 + 1 == var14)
                    {
                        ;
                    }
                }

                this.mc.mcProfiler.endStartSection("air");

                if (this.mc.thePlayer.isInsideOfMaterial(Material.water))
                {
                    var25 = this.mc.thePlayer.getAir();
                    var43 = MathHelper.ceiling_double_int((double)(var25 - 2) * 10.0D / 300.0D);
                    var47 = MathHelper.ceiling_double_int((double)var25 * 10.0D / 300.0D) - var43;

                    for (var28 = 0; var28 < var43 + var47; ++var28)
                    {
                        if (var28 < var43)
                        {
                            ;
                        }
                    }
                }

                this.mc.mcProfiler.endSection();
            }

            FontRenderer var37 = this.mc.fontRenderer;
            var25 = var6;

            if (!this.mc.thePlayer.capabilities.isCreativeMode)
            {
                this.drawTexturedModalRect(var6 - 135, 15, 0, 235, 9, 9);

                for (var27 = 0; var27 < 10; ++var27)
                {
                    this.drawTexturedModalRect(var25 - 135 + 9 + var27 * 9, 15, 9, 235, 9, 9);
                    this.drawTexturedModalRect(var25 - 135 + 9 + var27 * 9, 51, 9, 247, 9, 9);
                }

                this.drawTexturedModalRect(var25 - 135 + 99, 15, 21, 235, 9, 9);

                for (var27 = 0; var27 < 3; ++var27)
                {
                    this.drawTexturedModalRect(var25 - 135, 24 + var27 * 9, 0, 244, 9, 9);
                    this.drawTexturedModalRect(var25 - 135 + 99, 24 + var27 * 9, 21, 244, 9, 9);
                }

                this.drawTexturedModalRect(var25 - 135, 51, 0, 247, 9, 9);
                this.drawTexturedModalRect(var25 - 135 + 99, 51, 21, 247, 9, 9);

                for (var27 = 0; var27 < 3; ++var27)
                {
                    for (var28 = 0; var28 < 10; ++var28)
                    {
                        this.drawTexturedModalRect(var28 * 9 + var25 - 135 + 9, var27 * 9 + 24, 3, 246, 9, 9);
                    }
                }

                var27 = 0;

                if (this.mc.thePlayer.getFoodStats().getFoodLevel() <= 15)
                {
                    ++var27;
                }

                if (this.mc.thePlayer.getFoodStats().getFoodLevel() <= 10)
                {
                    ++var27;
                }

                if (this.mc.thePlayer.getFoodStats().getFoodLevel() <= 5)
                {
                    ++var27;
                }

                this.drawTexturedModalRect(var25 - 10 - 19, var7 - 10 - 32, 237, 128 + var27 * 32, 19, 32);
                var28 = 0;
                var48 = 19;

                if (!this.mc.thePlayer.isPotionActive(Potion.poison))
                {
                    var48 = 0;
                }

                if (this.mc.thePlayer.getHealth() <= 15)
                {
                    ++var28;
                }

                if (this.mc.thePlayer.getHealth() <= 10)
                {
                    ++var28;
                }

                if (this.mc.thePlayer.getHealth() <= 5)
                {
                    ++var28;
                }

                this.drawTexturedModalRect(var25 - 10 - 19, var7 - 10 - 69, 218 - var48, 128 + var28 * 32, 19, 32);
                var30 = false;

                if (this.mc.thePlayer.isPotionActive(Potion.moveSlowdown))
                {
                    this.drawTexturedModalRect(var25 - 10 - 19, var7 - 10 - 141, 161, 128, 19, 32);
                }
            }

            ItemStack var50 = this.mc.thePlayer.inventory.getCurrentItem();

            if (var50 != null)
            {
                var28 = var50.getItem().shiftedIndex;
                String var54;

                if (var28 == DayZ.makarov.shiftedIndex || var28 == DayZ.remington.shiftedIndex || var28 == DayZ.ak74u.shiftedIndex || var28 == DayZ.glock17.shiftedIndex || var28 == DayZ.dbshotgun.shiftedIndex)
                {
                    var54 = var50.getItem().getItemDisplayName(var50);
                    int var55 = var50.getItem().getMaxDamage() - var50.getItemDamage();
                    var31 = var54 + " | " + var55;
                    var37.drawStringWithShadow(var31, var25 - var37.getStringWidth(var31) - 32, var7 - 14, 16777215);
                }
            }

            if (this.mc.thePlayer.isPotionActive(Potion.poison) && this.mc.thePlayer.getActivePotionEffect(Potion.poison).getDuration() == 0)
            {
                this.mc.thePlayer.removePotionEffect(Potion.poison.id);
            }

            if (this.mc.thePlayer.isPotionActive(Potion.moveSlowdown) && this.mc.thePlayer.getActivePotionEffect(Potion.moveSlowdown).getDuration() == 0)
            {
                this.mc.thePlayer.removePotionEffect(Potion.moveSlowdown.id);
            }

            GL11.glDisable(GL11.GL_BLEND);
            this.mc.mcProfiler.startSection("actionBar");
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();

            for (var23 = 0; var23 < 9; ++var23)
            {
                var15 = var6 / 2 - 90 + var23 * 20 + 2;
                var16 = var7 - 16 - 3;
                this.renderInventorySlot(var23, var15, var16, par1);
            }

            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            this.mc.mcProfiler.endSection();
        }

        float var34;
        int var36;

        if (this.mc.thePlayer.getSleepTimer() > 0)
        {
            this.mc.mcProfiler.startSection("sleep");
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            var36 = this.mc.thePlayer.getSleepTimer();
            var34 = (float)var36 / 100.0F;

            if (var34 > 1.0F)
            {
                var34 = 1.0F - (float)(var36 - 100) / 10.0F;
            }

            var11 = (int)(220.0F * var34) << 24 | 1052704;
            drawRect(0, 0, var6, var7, var11);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            this.mc.mcProfiler.endSection();
        }

        int var35;
        String var41;

        if (this.mc.playerController.func_78763_f() && this.mc.thePlayer.experienceLevel > 0)
        {
            this.mc.mcProfiler.startSection("expLevel");
            var33 = false;
            var11 = var33 ? 16777215 : 8453920;
            var41 = "" + this.mc.thePlayer.experienceLevel;
            var35 = (var6 - var8.getStringWidth(var41)) / 2;
            var36 = var7 - 31 - 4;
            var8.drawString(var41, var35 + 1, var36, 0);
            var8.drawString(var41, var35 - 1, var36, 0);
            var8.drawString(var41, var35, var36 + 1, 0);
            var8.drawString(var41, var35, var36 - 1, 0);
            var8.drawString(var41, var35, var36, var11);
            this.mc.mcProfiler.endSection();
        }

        if (this.mc.isDemo())
        {
            this.mc.mcProfiler.startSection("demo");
            var41 = "";

            if (this.mc.theWorld.getWorldTime() >= 120500L)
            {
                var41 = StatCollector.translateToLocal("demo.demoExpired");
            }
            else
            {
                var41 = String.format(StatCollector.translateToLocal("demo.remainingTime"), new Object[] {StringUtils.ticksToElapsedTime((int)(120500L - this.mc.theWorld.getWorldTime()))});
            }

            var11 = var8.getStringWidth(var41);
            var8.drawStringWithShadow(var41, var6 - var11 - 10, 5, 16777215);
            this.mc.mcProfiler.endSection();
        }

        if (this.mc.gameSettings.showDebugInfo)
        {
            this.mc.mcProfiler.startSection("debug");
            GL11.glPushMatrix();
            var8.drawStringWithShadow("Minecraft 1.3.2 (" + this.mc.debug + ")", 2, 2, 16777215);
            var8.drawStringWithShadow(this.mc.debugInfoRenders(), 2, 12, 16777215);
            var8.drawStringWithShadow(this.mc.getEntityDebug(), 2, 22, 16777215);
            var8.drawStringWithShadow(this.mc.debugInfoEntities(), 2, 32, 16777215);
            var8.drawStringWithShadow(this.mc.getWorldProviderName(), 2, 42, 16777215);
            long var39 = Runtime.getRuntime().maxMemory();
            long var45 = Runtime.getRuntime().totalMemory();
            long var51 = Runtime.getRuntime().freeMemory();
            long var53 = var45 - var51;
            var31 = "Used memory: " + var53 * 100L / var39 + "% (" + var53 / 1024L / 1024L + "MB) of " + var39 / 1024L / 1024L + "MB";
            this.drawString(var8, var31, var6 - var8.getStringWidth(var31) - 2, 2, 14737632);
            var31 = "Allocated memory: " + var45 * 100L / var39 + "% (" + var45 / 1024L / 1024L + "MB)";
            this.drawString(var8, var31, var6 - var8.getStringWidth(var31) - 2, 12, 14737632);
            this.drawString(var8, String.format("x: %.5f", new Object[] {Double.valueOf(this.mc.thePlayer.posX)}), 2, 64, 14737632);
            this.drawString(var8, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[] {Double.valueOf(this.mc.thePlayer.boundingBox.minY), Double.valueOf(this.mc.thePlayer.posY)}), 2, 72, 14737632);
            this.drawString(var8, String.format("z: %.5f", new Object[] {Double.valueOf(this.mc.thePlayer.posZ)}), 2, 80, 14737632);
            this.drawString(var8, "f: " + (MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3), 2, 88, 14737632);
            var19 = MathHelper.floor_double(this.mc.thePlayer.posX);
            var18 = MathHelper.floor_double(this.mc.thePlayer.posY);
            var17 = MathHelper.floor_double(this.mc.thePlayer.posZ);

            if (this.mc.theWorld != null && this.mc.theWorld.blockExists(var19, var18, var17))
            {
                Chunk var32 = this.mc.theWorld.getChunkFromBlockCoords(var19, var17);
                this.drawString(var8, "lc: " + (var32.getTopFilledSegment() + 15) + " b: " + var32.getBiomeGenForWorldCoords(var19 & 15, var17 & 15, this.mc.theWorld.getWorldChunkManager()).biomeName + " bl: " + var32.getSavedLightValue(EnumSkyBlock.Block, var19 & 15, var18, var17 & 15) + " sl: " + var32.getSavedLightValue(EnumSkyBlock.Sky, var19 & 15, var18, var17 & 15) + " rl: " + var32.getBlockLightValue(var19 & 15, var18, var17 & 15, 0), 2, 96, 14737632);
            }

            this.drawString(var8, String.format("ws: %.3f, fs: %.3f, g: %b", new Object[] {Float.valueOf(this.mc.thePlayer.capabilities.getWalkSpeed()), Float.valueOf(this.mc.thePlayer.capabilities.getFlySpeed()), Boolean.valueOf(this.mc.thePlayer.onGround)}), 2, 104, 14737632);
            GL11.glPopMatrix();
            this.mc.mcProfiler.endSection();
        }

        if (this.recordPlayingUpFor > 0)
        {
            this.mc.mcProfiler.startSection("overlayMessage");
            var34 = (float)this.recordPlayingUpFor - par1;
            var11 = (int)(var34 * 256.0F / 20.0F);

            if (var11 > 255)
            {
                var11 = 255;
            }

            if (var11 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(var6 / 2), (float)(var7 - 48), 0.0F);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                var12 = 16777215;

                if (this.recordIsPlaying)
                {
                    var12 = Color.HSBtoRGB(var34 / 50.0F, 0.7F, 0.6F) & 16777215;
                }

                var8.drawString(this.recordPlaying, -var8.getStringWidth(this.recordPlaying) / 2, -4, var12 + (var11 << 24));
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            }

            this.mc.mcProfiler.endSection();
        }

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, (float)(var7 - 48), 0.0F);
        this.mc.mcProfiler.startSection("chat");
        this.persistantChatGUI.drawChat(this.updateCounter);
        this.mc.mcProfiler.endSection();
        GL11.glPopMatrix();

        if (this.mc.gameSettings.keyBindPlayerList.pressed && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1))
        {
            this.mc.mcProfiler.startSection("playerList");
            NetClientHandler var40 = this.mc.thePlayer.sendQueue;
            List var38 = var40.playerInfoList;
            var12 = var40.currentServerMaxPlayers;
            var35 = var12;

            for (var36 = 1; var35 > 20; var35 = (var12 + var36 - 1) / var36)
            {
                ++var36;
            }

            var14 = 300 / var36;

            if (var14 > 150)
            {
                var14 = 150;
            }

            var13 = (var6 - var36 * var14) / 2;
            byte var44 = 10;
            drawRect(var13 - 1, var44 - 1, var13 + var14 * var36, var44 + 9 * var35, Integer.MIN_VALUE);

            for (var15 = 0; var15 < var12; ++var15)
            {
                var16 = var13 + var15 % var36 * var14;
                var19 = var44 + var15 / var36 * 9;
                drawRect(var16, var19, var16 + var14 - 1, var19 + 8, 553648127);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_ALPHA_TEST);

                if (var15 < var38.size())
                {
                    GuiPlayerInfo var46 = (GuiPlayerInfo)var38.get(var15);
                    var8.drawStringWithShadow(var46.name, var16, var19, 16777215);
                    this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("/gui/icons.png"));
                    byte var49 = 0;
                    boolean var56 = false;

                    if (var46.responseTime < 0)
                    {
                        var48 = 5;
                    }
                    else if (var46.responseTime < 150)
                    {
                        var48 = 0;
                    }
                    else if (var46.responseTime < 300)
                    {
                        var48 = 1;
                    }
                    else if (var46.responseTime < 600)
                    {
                        var48 = 2;
                    }
                    else if (var46.responseTime < 1000)
                    {
                        var48 = 3;
                    }
                    else
                    {
                        var48 = 4;
                    }

                    this.zLevel += 100.0F;
                    this.drawTexturedModalRect(var16 + var14 - 12, var19, 0 + var49 * 10, 176 + var48 * 8, 10, 8);
                    this.zLevel -= 100.0F;
                }
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

    /**
     * Renders dragon's (boss) health on the HUD
     */
    private void renderBossHealth()
    {
        if (BossStatus.bossName != null && BossStatus.field_82826_b > 0)
        {
            --BossStatus.field_82826_b;
            FontRenderer var1 = this.mc.fontRenderer;
            ScaledResolution var2 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
            int var3 = var2.getScaledWidth();
            short var4 = 182;
            int var5 = var3 / 2 - var4 / 2;
            int var6 = (int)(BossStatus.healthScale * (float)(var4 + 1));
            byte var7 = 12;
            this.drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
            this.drawTexturedModalRect(var5, var7, 0, 74, var4, 5);

            if (var6 > 0)
            {
                this.drawTexturedModalRect(var5, var7, 0, 79, var6, 5);
            }

            String var8 = BossStatus.bossName;
            var1.drawStringWithShadow(var8, var3 / 2 - var1.getStringWidth(var8) / 2, var7 - 10, 16777215);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/icons.png"));
        }
    }

    private void renderPumpkinBlur(int par1, int par2)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
        Tessellator var3 = Tessellator.instance;
        var3.startDrawingQuads();
        var3.addVertexWithUV(0.0D, (double)par2, -90.0D, 0.0D, 1.0D);
        var3.addVertexWithUV((double)par1, (double)par2, -90.0D, 1.0D, 1.0D);
        var3.addVertexWithUV((double)par1, 0.0D, -90.0D, 1.0D, 0.0D);
        var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        var3.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Renders the vignette. Args: vignetteBrightness, width, height
     */
    private void renderVignette(float par1, int par2, int par3)
    {
        par1 = 1.0F - par1;

        if (par1 < 0.0F)
        {
            par1 = 0.0F;
        }

        if (par1 > 1.0F)
        {
            par1 = 1.0F;
        }

        this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(par1 - this.prevVignetteBrightness) * 0.01D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
        GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
        Tessellator var4 = Tessellator.instance;
        var4.startDrawingQuads();
        var4.addVertexWithUV(0.0D, (double)par3, -90.0D, 0.0D, 1.0D);
        var4.addVertexWithUV((double)par2, (double)par3, -90.0D, 1.0D, 1.0D);
        var4.addVertexWithUV((double)par2, 0.0D, -90.0D, 1.0D, 0.0D);
        var4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        var4.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * Renders the portal overlay. Args: portalStrength, width, height
     */
    private void renderPortalOverlay(float par1, int par2, int par3)
    {
        if (par1 < 1.0F)
        {
            par1 *= par1;
            par1 *= par1;
            par1 = par1 * 0.8F + 0.2F;
        }

        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, par1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
        float var4 = (float)(Block.portal.blockIndexInTexture % 16) / 16.0F;
        float var5 = (float)(Block.portal.blockIndexInTexture / 16) / 16.0F;
        float var6 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16.0F;
        float var7 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16.0F;
        Tessellator var8 = Tessellator.instance;
        var8.startDrawingQuads();
        var8.addVertexWithUV(0.0D, (double)par3, -90.0D, (double)var4, (double)var7);
        var8.addVertexWithUV((double)par2, (double)par3, -90.0D, (double)var6, (double)var7);
        var8.addVertexWithUV((double)par2, 0.0D, -90.0D, (double)var6, (double)var5);
        var8.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)var4, (double)var5);
        var8.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Renders the specified item of the inventory slot at the specified location. Args: slot, x, y, partialTick
     */
    private void renderInventorySlot(int par1, int par2, int par3, float par4)
    {
        ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[par1];

        if (var5 != null)
        {
            float var6 = (float)var5.animationsToGo - par4;

            if (var6 > 0.0F)
            {
                GL11.glPushMatrix();
                float var7 = 1.0F + var6 / 5.0F;
                GL11.glTranslatef((float)(par2 + 8), (float)(par3 + 12), 0.0F);
                GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
                GL11.glTranslatef((float)(-(par2 + 8)), (float)(-(par3 + 12)), 0.0F);
            }

            itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, par2, par3);

            if (var6 > 0.0F)
            {
                GL11.glPopMatrix();
            }

            itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, par2, par3);
        }
    }

    /**
     * The update tick for the ingame UI
     */
    public void updateTick()
    {
        if (this.recordPlayingUpFor > 0)
        {
            --this.recordPlayingUpFor;
        }

        ++this.updateCounter;
    }

    public void setRecordPlayingMessage(String par1Str)
    {
        this.recordPlaying = "Now playing: " + par1Str;
        this.recordPlayingUpFor = 60;
        this.recordIsPlaying = true;
    }

    /**
     * returns a pointer to the persistant Chat GUI, containing all previous chat messages and such
     */
    public GuiNewChat getChatGUI()
    {
        return this.persistantChatGUI;
    }

    public int getUpdateCounter()
    {
        return this.updateCounter;
    }
}
