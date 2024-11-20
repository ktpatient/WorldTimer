package com.kitp13.worldtimer;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = WorldTimer.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue TEXT_COLOR = BUILDER.comment("Color of the renderedText in hex Format (0xRRGGBB). E.g (0xFF0000) would be red").defineInRange("textColor", 0x00FFFF,-2147483648, 2147483647);
    private static final ModConfigSpec.ConfigValue<String> STYLE_TYPE = BUILDER.comment("Style of the rendered text. Usages: ['long', 'short']").define("style", "short");
    private static final ModConfigSpec.IntValue X_COORD = BUILDER.comment("The x position of the top left of the text").defineInRange("xcoord", 0, 0 ,Integer.MAX_VALUE);
    private static final ModConfigSpec.IntValue Y_COORD = BUILDER.comment("The y position of the top left of the text").defineInRange("ycoord", 0, 0 ,Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int textColor;
    private static String style;
    public static StyleType timeStyle;
    public static int xcoord, ycoord;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        textColor = TEXT_COLOR.get();
        style = STYLE_TYPE.get();
        if (style == "Short") timeStyle = StyleType.SHORT;
        else if (style == "long") timeStyle = StyleType.LONG;
        else timeStyle = StyleType.SHORT;
        xcoord = X_COORD.get();
        ycoord = Y_COORD.get();
    }
}
