package com.github.EthanCosta.odysseyus.ui.panels.pages;

import com.github.EthanCosta.odysseyus.Launcher;
import com.github.EthanCosta.odysseyus.ui.panel.Panel;
import com.github.EthanCosta.odysseyus.ui.PanelManager;
import com.github.EthanCosta.odysseyus.ui.panels.pages.content.Home;
import com.github.EthanCosta.odysseyus.ui.panels.pages.content.contentpanel;
import com.github.EthanCosta.odysseyus.ui.panels.pages.content.settings;
import com.github.EthanCosta.odysseyus.ui.panels.pages.content.addons;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    contentpanel currentPage = null;

    Button homeBtn, settingsBtn, addonsBTN;

    Saver saver = Launcher.getInstance().getSaver();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/app.css";
    }

    @Override
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
        homeBtn.setOnMouseClicked(e -> setPage(new Home(), homeBtn));

        settingsBtn = new Button("Paramètres");
        settingsBtn.getStyleClass().add("sidemenu-nav-btn");
        settingsBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.GEARS));
        setCanTakeAllSize(settingsBtn);
        setTop(settingsBtn);
        settingsBtn.setTranslateY(130d);
        settingsBtn.setOnMouseClicked(e -> setPage(new settings(), settingsBtn));

        addonsBTN = new Button("Addons");
        addonsBTN.getStyleClass().add("sidemenu-nav-btn");
        addonsBTN.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.PLUS));
        setTop(addonsBTN);
        addonsBTN.setTranslateY(170d);
        addonsBTN.setOnMouseClicked(e -> setPage(new addons(), addonsBTN));


        sidemenu.getChildren().addAll(homeBtn, settingsBtn, addonsBTN);

        // Pseudo + avatar
        GridPane userPane = new GridPane();
        setCanTakeAllWidth(userPane);
        userPane.setMaxHeight(80);
        userPane.setMinWidth(80);
        userPane.getStyleClass().add("user-pane");
        setBottom(userPane);

        String avatarUrl = "https://minotar.net/helm/" + (
                saver.get("offline-username") != null ?
                        Launcher.getInstance().getAuthInfos().getUsername() + ".png"    :
                        Launcher.getInstance().getAuthInfos().getUuid() + ".png"
        );
        ImageView avatarView = new ImageView();
        Image avatarImg = new Image(avatarUrl);
        avatarView.setImage(avatarImg);
        avatarView.setPreserveRatio(true);
        avatarView.setFitHeight(50d);
        setCenterV(avatarView);
        setCanTakeAllSize(avatarView);
        setLeft(avatarView);
        avatarView.setTranslateX(15d);
        userPane.getChildren().add(avatarView);

        Label usernameLabel = new Label(Launcher.getInstance().getAuthInfos().getUsername());
        usernameLabel.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 25f));
        setCanTakeAllSize(usernameLabel);
        setCenterV(usernameLabel);
        setLeft(usernameLabel);
        usernameLabel.getStyleClass().add("username-label");
        usernameLabel.setTranslateX(75d);
        setCanTakeAllWidth(usernameLabel);
        userPane.getChildren().add(usernameLabel);

        Button logoutBtn = new Button();
        logoutBtn.getStyleClass().add("logout-icon");
        FontAwesomeIconView logoutIcon = new FontAwesomeIconView(FontAwesomeIcon.SIGN_OUT);
        setCanTakeAllSize(logoutBtn);
        setCenterV(logoutBtn);
        setRight(logoutBtn);
        logoutBtn.getStyleClass().add("logout-btn");
        logoutBtn.setGraphic(logoutIcon);
        logoutBtn.setOnMouseClicked(e -> {
            if (currentPage instanceof Home && ((Home) currentPage).isDownloading()) {
                return;
            }
            saver.remove("accessToken");
            saver.remove("clientToken");
            saver.remove("msAccessToken");
            saver.remove("msRefreshToken");
            saver.remove("offline-username");
            saver.save();
            Launcher.getInstance().setAuthInfos(null);
            this.panelManager.showPanel(new login());
        });
        userPane.getChildren().add(logoutBtn);

        sidemenu.getChildren().add(userPane);





    }

    @Override
    public void onShow() {
        super.onShow();
        setPage(new Home(), homeBtn);
    }

    public void setPage(contentpanel panel, Node navButton) {
        if (currentPage instanceof Home && ((Home) currentPage).isDownloading() ) {
            return;
        }
        if (activeLink != null)
            activeLink.getStyleClass().remove("active");
        activeLink = navButton;
        activeLink.getStyleClass().add("active");

        this.navContent.getChildren().clear();
        if (panel != null) {
            this.navContent.getChildren().add(panel.getLayout());
            currentPage = panel;
            if (panel.getStylesheetPath() != null) {
                this.panelManager.getStage().getScene().getStylesheets().clear();
                this.panelManager.getStage().getScene().getStylesheets().addAll(
                        this.getStylesheetPath(),
                        panel.getStylesheetPath()
                );
            }
            panel.init(this.panelManager);
            panel.onShow();
        }
    }
}
