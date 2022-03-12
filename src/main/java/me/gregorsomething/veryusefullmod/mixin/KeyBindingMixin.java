package me.gregorsomething.veryusefullmod.mixin;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @Final @Shadow private static Map<InputUtil.Key, KeyBinding> keyToBindings;

    //@Inject(method = "setPressed(Z;)V", at = @At("HEAD"))
    private static void setKeyPressed(InputUtil.Key key, Boolean pressed, CallbackInfo ci) {
        System.out.println("siin");
        KeyBinding keyBinding = (KeyBinding)keyToBindings.get(key);
        if (keyBinding != null) {
            //keyBinding.setPressed(pressed);
            /*
             public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
    use taht
             */
        }
    }
}
