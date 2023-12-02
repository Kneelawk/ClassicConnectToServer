package com.kneelawk.nilclassic.premain;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

import com.kneelawk.nilclassic.NilClassicMain;

@Patch.Class("com.mojang.minecraft.Minecraft")
public class MinecraftTransformer extends MiniTransformer {

    // Mini is the transformer framework bundled with NilLoader. It's pretty low level, but tries
    // to file off a lot of the sharp edges from doing ASM patches. This is a really minimal example
    // of a patch to just print out something when the Minecraft class static-inits. This is chosen
    // as the example as it works on multiple versions.

    // NilLoader will automatically reobfuscate references to classes, fields, and methods in your
    // patches based on your currently selected mapping. This patch carefully avoids obfuscated
    // things to provide a semi-version-agnostic example.

    @Patch.Method("<init>(Ljava/awt/Canvas;Lcom/mojang/minecraft/MinecraftApplet;IIZ)V")
    public void patchInit(PatchContext ctx) {
        // <init> has 2 returns, let's apply before the other one too
        ctx.search(RETURN()).jumpBefore();

        ctx.add(
            INVOKESTATIC("com/kneelawk/nilclassic/premain/MinecraftTransformer$Hooks", "onInit", "()V")
        );

        // Equivalent to "TAIL" in Mixin
        ctx.jumpToLastReturn();

        ctx.add(
            INVOKESTATIC("com/kneelawk/nilclassic/premain/MinecraftTransformer$Hooks", "onInit", "()V")
        );
    }

    public static class Hooks {
        public static void onInit() {
            // Invoke our code that depends on Minecraft classes
            NilClassicMain.init();
        }
    }

}
