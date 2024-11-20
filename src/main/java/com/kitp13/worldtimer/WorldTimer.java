package com.kitp13.worldtimer;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(WorldTimer.MODID)
public class WorldTimer
{
    public static final String MODID = "worldtimer";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WorldTimer(IEventBus modEventBus, ModContainer modContainer)
    {
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void a(RenderGuiEvent.Post event) {
        if (Minecraft.getInstance().player == null) return;
        GuiGraphics graphics = event.getGuiGraphics();
        long totalTicks = Minecraft.getInstance().level.getGameTime();
        long totalSeconds = totalTicks / 20; // 20 ticks per second
        long days = totalSeconds / (20 * 60 * 24);
        long hours = (totalSeconds / (20 * 60)) % 24;
        long minutes = (totalSeconds / 20) % 60;
        long seconds = totalSeconds % 20;

        switch (Config.timeStyle) {

            case LONG: {
                String s = "Days: " + days + ", Hours: " + hours + ", Minutes: " + minutes + ", Seconds: " + seconds;
                graphics.drawString(Minecraft.getInstance().font, s, Config.xcoord, Config.ycoord, Config.textColor);
            }
                break;
            case SHORT: {
                String s = String.format("%d:%02d:%02d:%02d", days, hours, minutes, seconds);
                graphics.drawString(Minecraft.getInstance().font, s, Config.xcoord, Config.ycoord, Config.textColor);
            }

            break;
            default:
        }
    }
}
