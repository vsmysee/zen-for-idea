package com.intellij.ide.actions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotActionUI {

  public static List<String> hotActions = new ArrayList<>();

  public static JPanel leftPanel;
  public static JPanel rightPanel;
  public static JPanel main;

  private static Map<String, JLabel> labelMap = new HashMap<>();


  public static void hot(char type, String action) {

    String key = type + " => " + action;

    if (!hotActions.contains(key)) {
      hotActions.add(key);
    }

    SwingUtilities.invokeLater(() -> {
      JLabel jLabel = labelMap.get(key);
      if (jLabel != null) {
        jLabel.setText("<html><font color='blue'><b>" + key + "</b></font></html>");
        jLabel.repaint();
      }
    });
  }


  public static void label(String key, JLabel label) {
    labelMap.put(key, label);
  }


  public static void refresh(boolean lazy) {

    if (leftPanel == null || rightPanel == null) {
      return;
    }

    Runnable runnable = () -> {

      Map<String, String> keyMap = TypedHandler.modeMap.get(App.editorMode);
      if (keyMap != null) {
        leftPanel.removeAll();
        rightPanel.removeAll();
        int left = keyMap.keySet().size() / 2;
        int addCount = 0;
        for (String key : keyMap.keySet()) {

          String text = key + " => " + keyMap.get(key);

          JLabel comp = new JLabel(text);
          label(text, comp);

          if (addCount < left) {
            leftPanel.add(comp);
          }
          else {
            rightPanel.add(comp);
          }
          addCount++;
        }

        main.updateUI();
      }

    };


    if (lazy) {
      SwingUtilities.invokeLater(runnable);
    }
    else {
      runnable.run();
    }

  }

}
