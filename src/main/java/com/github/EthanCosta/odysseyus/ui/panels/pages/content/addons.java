package com.github.EthanCosta.odysseyus.ui.panels.pages.content;

import com.github.EthanCosta.odysseyus.ui.PanelManager;
import fr.flowarg.flowupdater.download.json.CurseFileInfo;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class addons extends contentpanel {
    //private final Saver saver = Launcher.getInstance().getSaver();
    GridPane contentPane = new GridPane();
    CheckBox dynamic_surroundings = new CheckBox("Dynamic Surroundings");
    CheckBox boobs_mod = new CheckBox("Wildfire's Female Gender Mod");
    CheckBox inventory_hud_plus = new CheckBox("Inventory HUD +");
    CheckBox item_physics = new CheckBox("ItemPhysic Full");
    CheckBox better_animation2 = new CheckBox("Better Animations Collection");
    CheckBox Controllable = new CheckBox("Controllable");





    public static List<CurseFileInfo> modAddons = new ArrayList<>();



    public String getName() {
        return "addons";
    }

    @Override
    public String getStylesheetPath() {
        return "css/content/addons.css";
    }


    @Override
    public void onShow() {
        super.onShow();
    }

    public void init(PanelManager panelManager) {
        super.init(panelManager);


        // Background
        this.layout.getStyleClass().add("settings-layout");
        this.layout.setPadding(new Insets(40));
        setCanTakeAllSize(this.layout);

        // Content
        contentPane.getStyleClass().add("content-pane");
        setCanTakeAllSize(contentPane);
        this.layout.getChildren().add(contentPane);

        // Titre
        Label title = new Label("Addons");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 30f));
        title.getStyleClass().add("addons-title");
        setLeft(title);
        setCanTakeAllSize(title);
        setTop(title);
        title.setTextAlignment(TextAlignment.LEFT);
        title.setTranslateY(10d);
        title.setTranslateX(10d);
        contentPane.getChildren().add(title);

        //Dyna

        dynamic_surroundings.getStyleClass().add("addons-mods");
        setLeft(dynamic_surroundings);
        setTop(dynamic_surroundings);
        dynamic_surroundings.setTranslateX(10d);
        dynamic_surroundings.setTranslateY(60d);
        setCanTakeAllSize(dynamic_surroundings);
        dynamic_surroundings.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {


                modAddons.add(new CurseFileInfo(238891, 3502320)); //dynamic surrounding
                System.out.println("Dynamic Surroundings add");
            } else {
                modAddons.remove(new CurseFileInfo(238891, 3502320));
                System.out.println("Dynamic Surroundings remove");

            }

        });
        contentPane.getChildren().add(dynamic_surroundings);



        //Boobs Mod
        boobs_mod.getStyleClass().add("addons-mods");
        setLeft(boobs_mod);
        setTop(boobs_mod);
        boobs_mod.setTranslateX(10d);
        boobs_mod.setTranslateY(85d);
        setCanTakeAllSize(boobs_mod);
        boobs_mod.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                modAddons.add(new CurseFileInfo(481655, 3459078)); //Wildfire's Female Gender Mod
                System.out.println("Wildfire's Female Gender Mod ajouté");
            } else {
                modAddons.remove(new CurseFileInfo(481655, 3459078)); //Wildfire's Female Gender Mod
                System.out.println("Wildfire's Female Gender Mod supprimé de la liste");

            }

        });
        contentPane.getChildren().add(boobs_mod);

        //Inventory HUD +

        inventory_hud_plus.getStyleClass().add("addons-mods");
        setLeft(inventory_hud_plus);
        setTop(inventory_hud_plus);
        inventory_hud_plus.setTranslateX(10d);
        inventory_hud_plus.setTranslateY(110d);
        setCanTakeAllSize(inventory_hud_plus);
        inventory_hud_plus.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                modAddons.add(new CurseFileInfo(357540, 3505181)); //Wildfire's Female Gender Mod
                System.out.println("Inventory HUD+ ajouté");
            } else {
                modAddons.remove(new CurseFileInfo(357540, 3505181)); //Wildfire's Female Gender Mod
                System.out.println("Inventory HUD+ supprimé de la liste");

            }

        });
        contentPane.getChildren().add(inventory_hud_plus);

        //item_physics

        item_physics.getStyleClass().add("addons-mods");
        setLeft(item_physics);
        setTop(item_physics);
        item_physics.setTranslateX(10d);
        item_physics.setTranslateY(135d);
        setCanTakeAllSize(item_physics);
        item_physics.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                modAddons.add(new CurseFileInfo(258587, 3285731)); //Item
                modAddons.add(new CurseFileInfo(257814, 3419983)); //creative core
                System.out.println("Item Physics ajouté");
            } else {
                modAddons.remove(new CurseFileInfo(258587, 3285731)); //Item
                modAddons.remove(new CurseFileInfo(257814, 3419983)); //creative core

                System.out.println("Item Physics supprimé de la liste");

            }

        });
        contentPane.getChildren().add(item_physics);



        better_animation2.getStyleClass().add("addons-mods");
        setLeft(better_animation2);
        setTop(better_animation2);
        better_animation2.setTranslateX(10d);
        better_animation2.setTranslateY(160d);
        setCanTakeAllSize(better_animation2);
        better_animation2.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                modAddons.add(new CurseFileInfo(323976, 3517404)); //animation
                modAddons.add(new CurseFileInfo(495476, 3517499)); //Puzzle Libs

                System.out.println("Better Animations Collection ajouté");
            } else {
                modAddons.remove(new CurseFileInfo(323976, 3517404)); //Animations
                modAddons.remove(new CurseFileInfo(495476, 3517499)); //Puzzle Libs
                System.out.println("Better Animations Collection supprimé de la liste");

            }

        });
        contentPane.getChildren().add(better_animation2);

        Controllable.getStyleClass().add("addons-mods");
        setLeft(Controllable);
        setTop(Controllable);
        Controllable.setTranslateX(10d);
        Controllable.setTranslateY(185d);
        setCanTakeAllSize(Controllable);
        Controllable.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                modAddons.add(new CurseFileInfo(317269, 3519536)); //Controllable

                System.out.println("Controllable ajouté");
            } else {
                modAddons.remove(new CurseFileInfo(317269, 3519536)); //Controllable
                System.out.println("Controllable supprimé de la liste");

            }

        });
        contentPane.getChildren().add(Controllable);


        //Explications
        Label titleInfos = new Label("Informations");
        titleInfos.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 20f));
        titleInfos.getStyleClass().add("addons-title");
        setLeft(titleInfos);
        setCanTakeAllSize(titleInfos);
        setTop(titleInfos);
        titleInfos.setTextAlignment(TextAlignment.LEFT);
        titleInfos.setTranslateY(225d);
        titleInfos.setTranslateX(10d);
        contentPane.getChildren().add(titleInfos);

        //Explications
        Label infosLabel = new Label("Ces mods ne sont pas nécessaires au fonctionnement d'Odysseyus et servent à améliorer\nvotre expérience de jeu.\nPour toutes informations : https://odysseyus.fr/addons");
        infosLabel.setFont(Font.font("Verdana", FontPosture.REGULAR, 12f));
        infosLabel.getStyleClass().add("addons-title");
        setLeft(infosLabel);
        setCanTakeAllSize(infosLabel);
        setTop(infosLabel);
        infosLabel.setTextAlignment(TextAlignment.LEFT);
        infosLabel.setTranslateY(255d);
        infosLabel.setTranslateX(10d);
        contentPane.getChildren().add(infosLabel);



    }


}