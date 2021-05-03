package me.gregorsomething.veryusefullmod;

import me.gregorsomething.veryusefullmod.config.VUMConfig;
import me.gregorsomething.veryusefullmod.logspamfix.JavaFilter;
import me.gregorsomething.veryusefullmod.logspamfix.Log4jFilter;
import me.gregorsomething.veryusefullmod.logspamfix.SystemFilter;
import me.gregorsomething.veryusefullmod.util.ParticleSpawner;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class VeryUsefullModClient implements ClientModInitializer {

    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static VUMConfig VUM_CONF;
    public static ParticleSpawner ParticleSpawner = new ParticleSpawner();

    @Override
    public void onInitializeClient() {

        AutoConfig.register(VUMConfig.class, JanksonConfigSerializer::new);
        VUM_CONF = AutoConfig.getConfigHolder(VUMConfig.class).getConfig();

        // Log spam remove units
        if (VUM_CONF.filter_enabled) {
            JavaFilter.applyFilter();
            Log4jFilter.applyFilter();
            SystemFilter.applyFilter();
        }

    }
}
