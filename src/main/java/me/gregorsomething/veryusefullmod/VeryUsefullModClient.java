package me.gregorsomething.veryusefullmod;

import me.gregorsomething.veryusefullmod.config.VUMConfig;
import me.gregorsomething.veryusefullmod.logspamfix.JavaFilter;
import me.gregorsomething.veryusefullmod.logspamfix.Log4jFilter;
import me.gregorsomething.veryusefullmod.logspamfix.SystemFilter;
import me.gregorsomething.veryusefullmod.util.LoacatorByNear;
import me.gregorsomething.veryusefullmod.util.ParticleSpawner;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class VeryUsefullModClient implements ClientModInitializer {

    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static VUMConfig VUM_CONF;
    public static ParticleSpawner ParticleSpawner = new ParticleSpawner();
    public static LoacatorByNear LOCATOR = new LoacatorByNear();

    @Override
    public void onInitializeClient() {

        AutoConfig.register(VUMConfig.class, JanksonConfigSerializer::new);
        VUM_CONF = AutoConfig.getConfigHolder(VUMConfig.class).getConfig();

        //TODO
        mc.options.chatHeightFocused = mc.options.chatHeightFocused*2;

        // Log spam remove units
        if (VUM_CONF.filter_enabled) {
            JavaFilter.applyFilter();
            Log4jFilter.applyFilter();
            SystemFilter.applyFilter();
        }

        if (VUM_CONF.cape_elytra) {
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                if (client.player != null) {
                    boolean hasElytra = false;
                    for (ItemStack itemStack : client.player.getArmorItems()) {
                        if (itemStack.getItem().getTranslationKey().replace("item.minecraft.", "").equalsIgnoreCase("elytra")) {
                            hasElytra = true;
                        }
                    }
                    if (hasElytra && !VeryUsefullModClient.mc.options.isPlayerModelPartEnabled(PlayerModelPart.CAPE)) {
                        VeryUsefullModClient.mc.options.togglePlayerModelPart(PlayerModelPart.CAPE, true);
                    }
                    if (!hasElytra && VeryUsefullModClient.mc.options.isPlayerModelPartEnabled(PlayerModelPart.CAPE)) {
                        VeryUsefullModClient.mc.options.togglePlayerModelPart(PlayerModelPart.CAPE, false);
                    }
                }
            });
        }
    }
}
