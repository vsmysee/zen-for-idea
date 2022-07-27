package com.intellij.ide.actions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull com.intellij.openapi.wm.ToolWindow toolWindow) {

        JPanel main = new JPanel(new BorderLayout());
        HotActionUI.main = main;

        HotActionUI.leftPanel = new JPanel();
        HotActionUI.leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        HotActionUI.leftPanel.setLayout(new BoxLayout(HotActionUI.leftPanel, BoxLayout.Y_AXIS));

        main.add(HotActionUI.leftPanel, BorderLayout.WEST);


        HotActionUI.rightPanel = new JPanel();
        HotActionUI.rightPanel.setLayout(new BoxLayout(HotActionUI.rightPanel, BoxLayout.Y_AXIS));
        HotActionUI.rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        main.add(HotActionUI.rightPanel, BorderLayout.EAST);


        JPanel center = new JPanel();
        center.setSize(100, 20);
        main.add(center, BorderLayout.CENTER);

        HotActionUI.refresh(false);

        main.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    if (App.editorMode == EditorMode.CMD) {
                        App.editorMode = EditorMode.MOVE;
                    } else if (App.editorMode == EditorMode.MOVE) {
                        App.editorMode = EditorMode.SELECT;
                    } else if (App.editorMode == EditorMode.SELECT) {
                        App.editorMode = EditorMode.CMD;
                    }

                    HotActionUI.refresh(true);
                }
            }
        });

        toolWindow.getContentManager().addContent(ContentFactory.SERVICE.getInstance().createContent(main, "", false));
    }


}
