package com.kneelawk.connecttoserver.premain;

import nilloader.api.ClassTransformer;

@SuppressWarnings("unused")
public class ConnectToServerPremain implements Runnable {
    @Override
    public void run() {
        ClassTransformer.register(new SocketConnectionTrans());
    }
}
