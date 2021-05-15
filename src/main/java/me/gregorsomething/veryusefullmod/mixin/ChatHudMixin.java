package me.gregorsomething.veryusefullmod.mixin;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;
import me.gregorsomething.veryusefullmod.sneakInMorse.MorseMain;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.regex.Pattern;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;I)V", at = @At("HEAD"))
    public void addMessage(Text text, int messageId, CallbackInfo info) {
        if (isMsg(text.getString()) && VeryUsefullModClient.VUM_CONF.morse_things.morse_msg)
            MorseMain.msgInMorse(getMsgContent(text.getString()));

    }

    public boolean isMsg(String text) {
        //^\[\w* -> Mina\]
        return Pattern.compile(getMsgRegex()).matcher(text).find();
    }

    public String getMsgContent(String text) {
        if (!isMsg(text)) return "";
        return text.split(getMsgRegex())[1];
    }

    public String getMsgRegex() {
        if (VeryUsefullModClient.VUM_CONF.morse_things.regex_primary_bool) {
            return VeryUsefullModClient.VUM_CONF.morse_things.regex_primary;
        } else {
            return VeryUsefullModClient.VUM_CONF.morse_things.regex_secondary;
        }
    }
}
