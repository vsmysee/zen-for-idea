package com.intellij.ide.actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;

public class RunAction {


    public static void run(String actionName, DataContext dataContext) {

        final AnAction action = ActionManager.getInstance().getAction(actionName.trim());
        if (action == null) {
            return;
        }

        ApplicationManager.getApplication().invokeLater(() -> {
            try {
                AnActionEvent evnet = new AnActionEvent(null, dataContext, "", new Presentation(), ActionManager.getInstance(), 0);
                action.actionPerformed(evnet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
