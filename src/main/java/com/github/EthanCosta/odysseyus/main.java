package com.github.EthanCosta.odysseyus;

import javafx.application.Application;

import javax.swing.*;
import club.minnced.discord.rpc.*;
import javafx.scene.control.TextField;


public class main {

    public static void main(String[] args) {

        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "863449083246018570";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Discord is Ready!");
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageText = "odysseyus.fr";
        presence.largeImageKey = "odysseyuslogo";
        presence.state = "En jeu !";
        presence.smallImageText = "En construction";

        lib.Discord_UpdatePresence(presence);
        // in a worker thread
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();

        try {
            Class.forName("javafx.application.Application");
            Application.launch(Launcher.class, args);

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erreur:\n" + e.getMessage() + " not find",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );

        }
    }


}
