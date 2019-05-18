/*
 * Copyright (c) 2000-2017 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.RenderAdapter;
import com.teamdev.jxbrowser.chromium.events.RenderEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * This example demonstrates how to restore the Browser instance after its native rendering process
 * has been terminated unexpectedly. In general to restore the Browser instance you just need to
 * load the same or another URL.
 */
public final class RestoreBrowserExample {
    public static void main(String[] args) {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.addRenderListener(new RenderAdapter() {
            @Override
            public void onRenderGone(RenderEvent event) {
                Browser browser = event.getBrowser();
                // Restore Browser instance by loading the same URL
                browser.loadURL(browser.getURL());
            }
        });

        browser.loadURL("http://www.google.com");

        System.out.println("Run 'Task Manager' app and kill the 'browsercore*.exe' "
                + "process with the '--type=renderer' command line argument.");
    }
}
