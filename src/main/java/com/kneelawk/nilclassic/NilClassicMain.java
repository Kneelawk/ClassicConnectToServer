package com.kneelawk.nilclassic;

import com.mojang.minecraft.User;
import com.mojang.minecraft.level.tile.Tile;

import com.kneelawk.nilclassic.premain.NilClassicLog;

@SuppressWarnings("unused")
public class NilClassicMain {
    public static void init() {
        NilClassicLog.log.info("Hello from main!");

        // We can do all kinds of things here. Here we add water to the creative menu.
        NilClassicLog.log.info("Adding water to selection screen...");
        User.creativeTiles.add(Tile.water);
    }
}
