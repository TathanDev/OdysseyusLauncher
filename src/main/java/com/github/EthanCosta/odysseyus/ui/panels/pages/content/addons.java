package com.github.EthanCosta.odysseyus.ui.panels.pages.content;

import com.github.EthanCosta.odysseyus.Launcher;
import com.github.EthanCosta.odysseyus.ui.PanelManager;
import fr.flowarg.flowupdater.download.json.CurseFileInfo;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.List;

public class addons extends contentpanel {
    private final Saver saver = Launcher.getInstance().getSaver();
    GridPane contentPane = new GridPane();
    CheckBox dynamic_surroundings = new CheckBox("Dynamic Surroundings");
    public static List<CurseFileInfo> modAddons = new ArrayList<>();



    public String getName() {
        return "settings";
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
        title.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 25f));
        title.getStyleClass().add("addons-title");
        setLeft(title);
        setCanTakeAllSize(title);
        setTop(title);
        title.setTextAlignment(TextAlignment.LEFT);
        title.setTranslateY(40d);
        title.setTranslateX(25d);
        contentPane.getChildren().add(title);

        //replay Mod
        dynamic_surroundings.getStyleClass().add("addons-dyna-sound");
        dynamic_surroundings.setMaxWidth(300);
        dynamic_surroundings.setTranslateX(5);
        dynamic_surroundings.setTranslateY(15);
        dynamic_surroundings.selectedProperty().addListener((e, old, newValue) -> {
           if (newValue) {

               modAddons.add(new CurseFileInfo(238891, 3502320)); //dynamic surrounding
               System.out.println("Dynamic Surrounding ajouté");
           } else {
               modAddons.remove(new CurseFileInfo(238891, 3502320));
               System.out.println("Enlevé");

           }

        });
        contentPane.getChildren().add(dynamic_surroundings);






    }


}
