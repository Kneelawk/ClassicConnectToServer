package com.kneelawk.nilclassic.premain;

import nilloader.api.ClassTransformer;
import nilloader.api.ModRemapper;

// All entrypoint classes must implement Runnable.
@SuppressWarnings("unused")
public class NilClassicPremain implements Runnable {

    @Override
    public void run() {
        NilClassicLog.log.info("Hello from premain!");

        // Any class transformers need to be registered with NilLoader like this.
        ClassTransformer.register(new MinecraftTransformer());
    }

}
