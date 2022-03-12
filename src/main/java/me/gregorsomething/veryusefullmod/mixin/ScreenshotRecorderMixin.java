package me.gregorsomething.veryusefullmod.mixin;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;
import net.minecraft.MinecraftVersion;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ScreenshotRecorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.time.LocalDateTime;

@Mixin(ScreenshotRecorder.class)
public class ScreenshotRecorderMixin {

    private static MinecraftClient MC = VeryUsefullModClient.mc;

    @Inject(method = "getScreenshotFilename(Ljava/io/File;)Ljava/io/File;", at = @At("TAIL"), cancellable = true)
    private static void getScreenshotFilename(File iDir, CallbackInfoReturnable cir) {
        String scFinal = VeryUsefullModClient.VUM_CONF.SC_DATA;
        LocalDateTime localDateTime = LocalDateTime.now();

        // Camera Entity Stuff
        scFinal = scFinal.replaceAll("%gm", MC.getGame().getCurrentSession().getGameMode().toLowerCase());
        scFinal = scFinal.replaceAll("%GM", MC.getGame().getCurrentSession().getGameMode().toUpperCase());
        scFinal = scFinal.replaceAll("%posx", Math.round(MC.cameraEntity.getX()) + "");
        scFinal = scFinal.replaceAll("%posy", Math.round(MC.cameraEntity.getY()) + "");
        scFinal = scFinal.replaceAll("%posz", Math.round(MC.cameraEntity.getZ()) + "");
        scFinal = scFinal.replaceAll("%yaw", Math.round(MC.cameraEntity.getYaw()) + "");
        scFinal = scFinal.replaceAll("%pitch", Math.round(MC.cameraEntity.getPitch()) + "");
        // World/Server Data Stuff
        scFinal = scFinal.replaceAll("%gver", MinecraftVersion.GAME_VERSION.getName());
        scFinal = scFinal.replaceAll("sver", MinecraftVersion.GAME_VERSION.getWorldVersion() + "");
        //scFinal = scFinal.replaceAll("%sname", MC.getServer().getName());
        // Date
        scFinal = scFinal.replaceAll("%Y", localDateTime.getYear() + "");
        scFinal = scFinal.replaceAll("%y", String.format("%-2d", localDateTime.getYear()));
        scFinal = scFinal.replaceAll("%mo", String.format("%d", localDateTime.getMonthValue()));
        scFinal = scFinal.replaceAll("%MO", String.format("%02d", localDateTime.getMonthValue()));
        scFinal = scFinal.replaceAll("%d", String.format("%d", localDateTime.getDayOfMonth()));
        scFinal = scFinal.replaceAll("%D", String.format("%02d", localDateTime.getDayOfMonth()));
        // Time
        scFinal = scFinal.replaceAll("%h", String.format("%d", localDateTime.getHour()));
        scFinal = scFinal.replaceAll("%H", String.format("%02d", localDateTime.getHour()));
        scFinal = scFinal.replaceAll("%m", String.format("%d", localDateTime.getMinute()));
        scFinal = scFinal.replaceAll("%M", String.format("%02d", localDateTime.getMinute()));
        scFinal = scFinal.replaceAll("%s", String.format("%d", localDateTime.getSecond()));
        scFinal = scFinal.replaceAll("%S", String.format("%02d", localDateTime.getSecond()));

        String name;
        File mDir = null;
        if (scFinal.contains("/")) {
            String[] nameParts = scFinal.split("/");
            name = nameParts[nameParts.length - 1];
            if (nameParts.length > 2) {
                mDir = new File(iDir, nameParts[0]);
                mDir.mkdir();
                for (int i = 1; i < nameParts.length - 1; i++) {
                    mDir = new File(mDir, nameParts[i]);
                    mDir.mkdir();
                }
            } else {
                mDir = new File(iDir, nameParts[0]);
                mDir.mkdir();
            }
        } else {
            mDir = iDir;
            name = scFinal;
        }
        // Near copy paste of original method
        int i = 1;
        while(true) {
            File file = new File(mDir, name + (i == 1 ? "" : "-" + i) + ".png");
            if (!file.exists()) {
                cir.setReturnValue(file);
                return;
            }
            ++i;
        }

    }
}
