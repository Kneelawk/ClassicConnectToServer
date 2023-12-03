package com.kneelawk.connecttoserver;

import com.kneelawk.connecttoserver.premain.ConnectToServerLog;
import com.kneelawk.nilclassicapi.api.client.event.ClientLifecycleEvents;

@SuppressWarnings("unused")
public class ConnectToServerMain implements Runnable {

    @Override
    public void run() {
        ClientLifecycleEvents.CLIENT_CREATED.register(client -> {
            String server = System.getProperty("com.kneelawk.connecttoserver.server");
            String portStr = System.getProperty("com.kneelawk.connecttoserver.port");

            ConnectToServerLog.log.info("Server: {}, port: {}", server, portStr);

            if (server != null && portStr != null) {
                int port;
                try {
                    port = Integer.parseInt(portStr);
                } catch (NumberFormatException e) {
                    ConnectToServerLog.log.error("Unable to parse server port.");
                    return;
                }

                ConnectToServerLog.log.info("Connecting to server...");

                client.server = server;
                client.port = port;
            }
        });

        ClientLifecycleEvents.CLIENT_STARTING.register(client -> {
            // fix NPE
            if (client.user.mpPass == null) {
                client.user.mpPass = "";
            }
        });
    }
}
