package com.github.EthanCosta.odysseyus.ui.panels.pages;

import com.github.EthanCosta.odysseyus.Launcher;
import com.github.EthanCosta.odysseyus.ui.PanelManager;
import com.github.EthanCosta.odysseyus.ui.panel.IPanel;
import com.github.EthanCosta.odysseyus.ui.panel.Panel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class App extends Panel {

    GridPane sidemenu = new GridPane();
    GridPane navContent = new GridPane();

    Node activeLink = null;

    Button homeBtn, settingsBtn;

    Saver saver = Launcher.getInstance().getSaver();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/app.css";
    }

    public void init(PanelManager panelManager) {
        super.init((panelManager));

        //background
        this.layout.getStyleClass().add("app-layout");
        setCanTakeAllSize(this.layout);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.LEFT);
        columnConstraints.setMinWidth(350);
        columnConstraints.setMaxWidth(350);
        this.layout.getColumnConstraints().addAll(columnConstraints, new ColumnConstraints());

        // Side menu
        this.layout.add(sidemenu, 0, 0);
        sidemenu.getStyleClass().add("sidemenu");
        setLeft(sidemenu);
        setCenterH(sidemenu);
        setCenterV(sidemenu);

        // Background Image
        GridPane bgImage = new GridPane();
        setCanTakeAllSize(bgImage);
        bgImage.getStyleClass().add("bg-image");
        this.layout.add(bgImage, 1, 0);

        // Nav content
        this.layout.add(navContent, 1, 0);
        navContent.getStyleClass().add("nav-content");
        setLeft(navContent);
        setCenterH(navContent);
        setCenterV(navContent);

        /*
         * Side menu
         */

        // Titre
        Label title = new Label("Odysseyus Launcher");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 30f));
        title.getStyleClass().add("home-title");
        setCenterH(title);
        setCanTakeAllSize(title);
        setTop(title);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setTranslateY(30d);
        sidemenu.getChildren().add(title);

        // Navigation
        homeBtn = new Button("Accueil");
        homeBtn.getStyleClass().add("sidemenu-nav-btn");
        homeBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.HOME));
        setCanTakeAllSize(homeBtn);
        setTop(homeBtn);
        homeBtn.setTranslateY(90d);
        homeBtn.setOnMouseClicked(e -> setPage(null, homeBtn));

        settingsBtn = new Button("Paramètres");
        settingsBtn.getStyleClass().add("sidemenu-nav-btn");
        settingsBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.GEARS));
        setCanTakeAllSize(settingsBtn);
        setTop(settingsBtn);
        settingsBtn.setTranslateY(130d);
        settingsBtn.setOnMouseClicked(e -> setPage(null, settingsBtn));

        sidemenu.getChildren().addAll(homeBtn, settingsBtn);




    }

    @Override
    public void onShow() {
        super.onShow();
        setPage(null, homeBtn);
    }

    public void setPage(IPanel panel, Node navButton) {
        if (activeLink != null) {
            activeLink.getStyleClass().remove("active");
            activeLink = navButton;
            activeLink.getStyleClass().add("active");
        }

    }




}
