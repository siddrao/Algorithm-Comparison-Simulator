/*
 * Copyright (c) 2000-2018 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserCore;
import com.teamdev.jxbrowser.chromium.internal.Environment;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import java.awt.BorderLayout;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * The example demonstrates how to use JavaFX BrowserView in JFXPanel.
 */
public class JFXPanelSample {

    public static void main(String[] args) {
        // This example initializes Browser in JavaFX UI thread.
        // On macOS the Chromium engine must be initialized in non-UI JavaFX thread.
        if (Environment.isMac()) {
            BrowserCore.initialize();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        final JFXPanel fxPanel = new JFXPanel();

        JFrame frame = new JFrame("JFXPanel Sample");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new JButton("Button"), BorderLayout.NORTH);
        frame.add(fxPanel, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });
    }

    private static void initFX(JFXPanel fxPanel) {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(view);
        borderPane.setTop(new Button("Button"));
        Scene scene = new Scene(borderPane, 700, 500);
        fxPanel.setScene(scene);

        browser.loadURL("https://www.google.com");
    }
}
