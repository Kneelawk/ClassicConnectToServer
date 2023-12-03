package com.kneelawk.connecttoserver.premain;

import java.util.Arrays;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

import com.mojang.minecraft.net.Packet;

@Patch.Class("com.mojang.minecraft.comm.SocketConnection")
public class SocketConnectionTrans extends MiniTransformer {
    public static class Hooks {
        public static void onSendPacketError(Packet packet, Object[] data, int index) {
            ConnectToServerLog.log.error("Failed to encode packet: {}", packetName(packet));
            ConnectToServerLog.log.error("Data: {}", Arrays.toString(data));
            ConnectToServerLog.log.error("Erroring index: {}", index);
        }
        
        private static String packetName(Packet packet) {
            if (packet == Packet.IDENTIFICATION) return "IDENTIFICATION";
            if (packet == Packet.LEVEL_INITIALIZE) return "LEVEL_INITIALIZE";
            if (packet == Packet.LEVEL_DATA_CHUNK) return "LEVEL_DATA_CHUNK";
            if (packet == Packet.LEVEL_FINALIZE) return "LEVEL_FINALIZE";
            if (packet == Packet.SET_TILE_C2S) return "SET_TILE_C2S";
            if (packet == Packet.SET_TILE_S2C) return "SET_TILE_S2C";
            if (packet == Packet.SPAWN_PLAYER) return "SPAWN_PLAYER";
            if (packet == Packet.TELEPORT) return "TELEPORT";
            if (packet == Packet.POSITION_AND_ORIENTATION_UPDATE) return "POSITION_AND_ORIENTATION_UPDATE";
            if (packet == Packet.POSITION_UPDATE) return "POSITION_UPDATE";
            if (packet == Packet.ORIENTATION_UPDATE) return "ORIENTATION_UPDATE";
            if (packet == Packet.DESPAWN_PLAYER) return "DESPAWN_PLAYER";
            if (packet == Packet.MESSAGE) return "MESSAGE";
            if (packet == Packet.DISCONNECT_PLAYER) return "DISCONNECT_PLAYER";
            if (packet == Packet.UPDATE_USER_TYPE) return "UPDATE_USER_TYPE";
            return "unknown packet id: " + packet.id;
        }
    }

    @Patch.Method("sendPacket(Lcom/mojang/minecraft/net/Packet;[Ljava/lang/Object;)V")
    public void patchSendPacket(PatchContext ctx) {
        ctx.search(INVOKEVIRTUAL("com/mojang/minecraft/net/ConnectionManager", "disconnect", "(Ljava/lang/Exception;)V")).jumpAfter();

        ctx.add(
            ALOAD(1),
            ALOAD(2),
            ILOAD(3),
            INVOKESTATIC("com/kneelawk/connecttoserver/premain/SocketConnectionTrans$Hooks", "onSendPacketError", "(Lcom/mojang/minecraft/net/Packet;[Ljava/lang/Object;I)V")
        );
    }
}
