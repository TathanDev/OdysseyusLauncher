package com.github.EthanCosta.odysseyus.ui.panels.pages.content;

import com.github.EthanCosta.odysseyus.Launcher;
import com.github.EthanCosta.odysseyus.game.MinecraftInfos;
import com.github.EthanCosta.odysseyus.ui.PanelManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.download.IProgressCallback;
import fr.flowarg.flowupdater.download.Step;
import fr.flowarg.flowupdater.download.json.CurseFileInfos;
import fr.flowarg.flowupdater.download.json.Mod;
import fr.flowarg.flowupdater.utils.ModFileDeleter;
import fr.flowarg.flowupdater.utils.UpdaterOptions;
import fr.flowarg.flowupdater.versions.AbstractForgeVersion;
import fr.flowarg.flowupdater.versions.ForgeVersionBuilder;
import fr.flowarg.flowupdater.versions.VanillaVersion;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.chrono.MinguoEra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Home extends contentpanel {

    private final Saver saver = Launcher.getInstance().getSaver();
    GridPane boxPane = new GridPane();
    Label stepLabel = new Label();
    Label fileLabel = new Label();
    ProgressBar progressBar = new ProgressBar();
    boolean isDownloading = false;


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/content/home.css";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setValignment(VPos.CENTER);
        rowConstraints.setMinHeight(75);
        rowConstraints.setMaxHeight(75);
        this.layout.getRowConstraints().addAll(rowConstraints, new RowConstraints());
        boxPane.getStyleClass().add("box-pane");
        setCanTakeAllSize(boxPane);
        boxPane.setPadding(new Insets(20));
        this.layout.add(boxPane, 0, 0);
        this.layout.getStyleClass().add("home-layout");

        progressBar.getStyleClass().add("download-progress");
        stepLabel.getStyleClass().add("download-status");
        fileLabel.getStyleClass().add("download-status");

        progressBar.setTranslateY(-15);
        setCenterH(progressBar);
        setCanTakeAllSize(progressBar);

        stepLabel.setTranslateY(5);
        setCenterH(stepLabel);
        setCanTakeAllSize(stepLabel);

        fileLabel.setTranslateY(20);
        setCenterH(fileLabel);
        setCanTakeAllSize(fileLabel);

        this.showPlayButton();

    }

    private void showPlayButton() {
        boxPane.getChildren().clear();
        Button playBtn = new Button("Jouer");
        FontAwesomeIconView playIcon = new FontAwesomeIconView(FontAwesomeIcon.GAMEPAD);
        setCanTakeAllSize(playBtn);
        setCenterH(playBtn);
        setCenterV(playBtn);
        playBtn.getStyleClass().add("play-btn");
        playBtn.setGraphic(playIcon);
        playBtn.setOnMouseClicked(e -> this.play());
        boxPane.getChildren().add(playBtn);
    }

    private void play() {
        isDownloading = true;
        boxPane.getChildren().clear();
        setProgress(0, 0);
        boxPane.getChildren().addAll(progressBar, stepLabel, fileLabel);

        Platform.runLater(() -> new Thread(this::update).start());
    }

    public void update() {

        IProgressCallback callback = new IProgressCallback() {
            private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
            private String stepTxt = "";
            private String percentTxt = "0.0%";

            @Override
            public void step(Step step) {
                Platform.runLater(() -> {
                    stepTxt = StepInfo.valueOf(step.name()).getDetails();
                    setStatus(String.format("%s (%s)", stepTxt, percentTxt));

                });
            }

            @Override
            public void update(long downloaded, long max) {
                Platform.runLater(() -> {
                    percentTxt = decimalFormat.format(downloaded * 100.d / max) + "%";
                    setStatus(String.format("%s (%s)", stepTxt, percentTxt));
                    setProgress(downloaded, max);
                });
            }

            @Override
            public void onFileDownloaded(Path path) {
                Platform.runLater(() -> {
                    String p = path.toString();
                    fileLabel.setText("..." + p.replace(Launcher.getInstance().getLauncherDir().toFile().getAbsolutePath(), ""));
                });
            }
        };

        try {


            final VanillaVersion version = new VanillaVersion.VanillaVersionBuilder()
                    .withName(MinecraftInfos.GAME_VERSION)
                    .withVersionType(MinecraftInfos.VERSION_TYPE)
                    .build();

            final List<CurseFileInfos> modInfos = new ArrayList<>();
            modInfos.add(new CurseFileInfos(316867, 3328009)); //ElectroDyna
            modInfos.add(new CurseFileInfos(318646, 3316889)); //nuclear science
            modInfos.add(new CurseFileInfos(443915, 3328078)); //Ballistix

            final List<Mod> files = Mod.getModsFromJson(MinecraftInfos.MODS_LIST_URL);

            final UpdaterOptions options = new UpdaterOptions.UpdaterOptionsBuilder()
                    .withEnableCurseForgePlugin(true)
                    .withEnableOptifineDownloaderPlugin(true)
                    .build();


            final AbstractForgeVersion forge = new ForgeVersionBuilder(MinecraftInfos.FORGE_VERSION_TYPE)
                    .withForgeVersion(MinecraftInfos.FORGE_VERSION)
                    .withMods(files)
                    .withCurseMods(modInfos)
                    .build();


            final FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder()
                    .withVersion(version)
                    .withLogger(Launcher.getInstance().getLogger())
                    .withProgressCallback(callback)
                    .withForgeVersion(forge)
                    .withUpdaterOptions(options)
                    .build();

            updater.update(Launcher.getInstance().getLauncherDir());
            this.startGame(updater.getVersion().getName());


        } catch (Exception exception) {
            Launcher.getInstance().getLogger().err(exception.toString());
        }
    }



    public void startGame(String gameVersion) {
        File gameFolder = Launcher.getInstance().getLauncherDir().toFile();

        GameInfos infos = new GameInfos(
                ".OdysseyusV2",
                true,
                new GameVersion(gameVersion, MinecraftInfos.OLL_GAME_TYPE.setNFVD(MinecraftInfos.OLL_FORGE_DISCRIMINATOR)),
                new GameTweak[]{}
        );


            try {

                ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(infos, GameFolder.FLOW_UPDATER, Launcher.getInstance().getAuthInfos());
                profile.getVmArgs().add(this.getRamArgsFromSaver());
                ExternalLauncher launcher = new ExternalLauncher(profile);
                profile.getArgs().addAll(Arrays.asList("--server=185.248.33.101", "--port=25565"));
                Platform.runLater(() -> panelManager.getStage().hide());


                Process p = launcher.launch();


                Platform.runLater(() -> {
                    try {
                        p.waitFor();
                        Platform.exit();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

            } catch (Exception exception) {
                exception.printStackTrace();
                Launcher.getInstance().getLogger().err(exception.toString());
            }
    }


    public String getRamArgsFromSaver() {
        int val = 1024;
        try {
            if (saver.get("maxRam") != null) {
                val = Integer.parseInt(saver.get("maxRam"));
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException error) {
            saver.set("maxRam", String.valueOf(val));
            saver.save();
        }

        return "-Xmx" + val + "M";
    }

    public void setStatus(String status) {
        this.stepLabel.setText(status);
    }

    public void setProgress(double current, double max) {
        this.progressBar.setProgress(current / max);
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public enum StepInfo {
        READ("Lecture du fichier json..."),
        DL_LIBS("Téléchargement des libraries..."),
        DL_ASSETS("Téléchargement des ressources..."),
        EXTRACT_NATIVES("Extraction des natives..."),
        FORGE("Installation de forge..."),
        FABRIC("Installation de fabric..."),
        MODS("Téléchargement des mods..."),

        EXTERNAL_FILES("Téléchargement des fichier externes..."),
        POST_EXECUTIONS("Exécution post-installation..."),
        END("Bon jeu ! ");
        String details;

        StepInfo(String details) {
            this.details = details;
        }

        public String getDetails() {
            return details;
        }
    }
}
