package com.intellij.ide.actions;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CustomShortcutSet;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryAdapter;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightVirtualFile;

public class EditorCreated extends EditorFactoryAdapter {


    private static final String MODE_ACTION = "ZenChangeModeAction";



    public void editorCreated(EditorFactoryEvent event) {
        final Editor editor = event.getEditor();
        VirtualFile file = FileDocumentManager.getInstance().getFile(editor.getDocument());
        if (file == null || file instanceof LightVirtualFile) {
            App.editorMode = EditorMode.INSERT;
            for (Editor e : com.intellij.openapi.editor.EditorFactory.getInstance().getAllEditors()) {
                e.getSettings().setBlockCursor(false);
            }

            return;
        }

        editor.getSettings().setBlockCursor(true);

        AnAction acton = ActionManager.getInstance().getAction(MODE_ACTION);

        if (acton == null) {
            acton = new AnAction() {
                @Override
                public void actionPerformed(AnActionEvent e) {
                    App.editorMode = EditorMode.CMD;
                    for (Editor editor : com.intellij.openapi.editor.EditorFactory.getInstance().getAllEditors()) {
                        editor.getSettings().setBlockCursor(true);
                    }
                }
            };
            ActionManager.getInstance().registerAction(MODE_ACTION, acton);
        }

        acton.registerCustomShortcutSet(CustomShortcutSet.fromString(HotKey.ACTION_KEY), editor.getComponent());

    }

}
