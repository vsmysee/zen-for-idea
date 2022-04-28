package com.intellij.ide.actions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IDEAToolWindowFactory implements ToolWindowFactory {

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

    JPanel main = new JPanel(new BorderLayout());
    UiHelper.main = main;

    UiHelper.leftPanel = new JPanel();
    UiHelper.leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    UiHelper.leftPanel.setLayout(new BoxLayout(UiHelper.leftPanel, BoxLayout.Y_AXIS));

    main.add(UiHelper.leftPanel, BorderLayout.WEST);


    UiHelper.rightPanel = new JPanel();
    UiHelper.rightPanel.setLayout(new BoxLayout(UiHelper.rightPanel, BoxLayout.Y_AXIS));
    UiHelper.rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    main.add(UiHelper.rightPanel, BorderLayout.EAST);


    JPanel center = new JPanel();
    center.setSize(100, 20);
    main.add(center, BorderLayout.CENTER);

    UiHelper.refresh(false);

    main.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          if (App.editorMode == App.EditorMode.CMD) {
            App.editorMode = App.EditorMode.MOVE;
          }
          else if (App.editorMode == App.EditorMode.MOVE) {
            App.editorMode = App.EditorMode.SELECT;
          }
          else if (App.editorMode == App.EditorMode.SELECT) {
            App.editorMode = App.EditorMode.CMD;
          }

          UiHelper.refresh(true);
        }
      }
    });

    toolWindow.getContentManager().addContent(ContentFactory.SERVICE.getInstance().createContent(main, "", false));
  }


}
