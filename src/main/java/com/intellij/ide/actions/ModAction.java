package com.intellij.ide.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import org.jetbrains.annotations.NotNull;

public class ModAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        App.editorMode = EditorMode.CMD;
        for (Editor editor : EditorFactory.getInstance().getAllEditors()) {
            editor.getSettings().setBlockCursor(true);
            HotActionUI.refresh(true);
        }
    }

}
