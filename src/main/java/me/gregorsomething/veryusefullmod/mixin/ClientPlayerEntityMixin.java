package me.gregorsomething.veryusefullmod.mixin;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;
import me.gregorsomething.veryusefullmod.util.Chat;
import me.gregorsomething.veryusefullmod.util.MathUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.LocalDateTime;
import java.util.Objects;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "sendChatMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private void onClientMsgSend(String message, CallbackInfo info) {
        //If it starts with prefix
        if (message.startsWith("?")) info.cancel();
        else return;
        String[] args = message.substring(1).split(" ");
        if (VeryUsefullModClient.VUM_CONF.chat_commands) {
            switch (args[0].toLowerCase()) {
                case "calc":
                    Chat.sendMsgClient("&c&l[VUM]&7 Calculate: " + message.substring(6));
                    Chat.sendMsgClient("&c&l[VUM]&7 =" + MathUtil.eval(message.substring(6)));
                    break;
                case "time":
                    LocalDateTime localDateTime = LocalDateTime.now();
                    Chat.sendMsgClient("&c&l[VUM]&7 Time and date: " +
                            formatNumber(localDateTime.getDayOfMonth()) + "/" +
                            formatNumber(localDateTime.getMonthValue()) + "/" +
                            localDateTime.getYear() + "    " +
                            formatNumber(localDateTime.getHour()) + ":" +
                            formatNumber(localDateTime.getMinute()) + ":" +
                            formatNumber(localDateTime.getSecond()));
                    break;
                case "chat":
                    BaseText toSend = new LiteralText("");
                    BaseText start = new TranslatableText("text.autoconfig.veryusefullmod.option.chat_symbols");
                    start.setStyle(start.getStyle().withBold(true));
                    start.setStyle(start.getStyle().withColor(Formatting.RED));
                    toSend.append(start);
                    for (String entry: VeryUsefullModClient.VUM_CONF.chat_symbols) {
                        if (entry.split("##").length == 4) {
                            String[] data = entry.split("##");
                            toSend.append(Chat.getMsgWithExtra("\n&r" + data[0], "&r" + data[1], "&r" + data[2], data[3], ClickEvent.Action.COPY_TO_CLIPBOARD));
                        }
                    }
                    Chat.send(toSend);
                    break;
                case "test":
                    MinecraftClient mc = MinecraftClient.getInstance();
                    Chat.sendMsgClient("&c&l[VUM]&7 TEST :(");
                    double x = Objects.requireNonNull(mc.cameraEntity).getX();
                    double y = Objects.requireNonNull(mc.cameraEntity).getY();
                    double z = Objects.requireNonNull(mc.cameraEntity).getZ();
                    VeryUsefullModClient.ParticleSpawner.addParticleArea(ParticleTypes.CLOUD, x, y, z, 10, 10, 10, 10);
                    Chat.sendMsgClient("&c&l[VUM]&7 TEST :)");
                    mc.inGameHud.getChatHud().addMessage(Chat.getMsgWithExtra("&6&lNii", "&cNaa", "&btaga","?taga", ClickEvent.Action.SUGGEST_COMMAND));
            }
        }
    }

    private static String formatNumber(int number) {
        String str = ("00" + number);
        return str.substring(Math.max(str.length() - 2, 0));
    }

    }
