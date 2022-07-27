package com.intellij.ide.actions;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.CustomShortcutSet;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryAdapter;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightVirtualFile;

public class EditorCreated extends EditorFactoryAdapter {

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
        App.editorMode = EditorMode.CMD;

        AnAction acton = ActionManager.getInstance().getAction("MyModAction");
        acton.registerCustomShortcutSet(CustomShortcutSet.fromString(HotKey.ACTION_KEY), editor.getComponent());

    }

}
