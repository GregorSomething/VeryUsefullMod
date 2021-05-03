package me.gregorsomething.veryusefullmod.util;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleEffect;


public class ParticleSpawner {

    private static MinecraftClient mc = VeryUsefullModClient.mc;

    public  void addParticle(ParticleEffect type, double x, double y, double z) {
        if (mc.world != null) {
            mc.world.addParticle(type, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }
    public void addParticleArea(ParticleEffect type, double x, double y, double z, double deltaX, double deltaY, double deltaZ) {
        for (int i = 0; i < deltaX; i++) {
            for (int j = 0; j < deltaY; j++) {
                for (int k = 0; k < deltaZ; k++) {
                    this.addParticle(type, x + i + Math.random(), y + j + Math.random(), z + k + Math.random());
                }
            }
        }
    }
    public void addParticleArea(ParticleEffect type, double x, double y, double z, double deltaX, double deltaY, double deltaZ, int count) {
        for (int i = 0; i < count; i++) {
            this.addParticleArea(type, x, y, z, deltaX, deltaY, deltaZ);
        }
    }
}
