package com.intellij.ide.actions;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TypedHandler implements TypedActionHandler {

    public static Map<EditorMode, Map<String, String>> modeMap = new HashMap<>();

    static {
        KeyDef.init();
    }

    private TypedActionHandler origHandler;

    public TypedHandler(TypedActionHandler handler) {
        this.origHandler = handler;
    }


    private void setCursors(EditorMode editorMode) {
        for (Editor editor : EditorFactory.getInstance().getAllEditors()) {
            editor.getSettings().setBlockCursor(editorMode != EditorMode.INSERT);
        }
    }

    private static Map<Character, EditorMode> modMapping = new HashMap<>();

    static {
        modMapping.put('i', EditorMode.INSERT);
        modMapping.put('y', EditorMode.MOVE);
        modMapping.put('S', EditorMode.SELECT);
    }

    @Override
    public void execute(@NotNull Editor editor, char charTyped, @NotNull DataContext dataContext) {

        if (App.editorMode == EditorMode.CMD) {
            if (modMapping.containsKey(charTyped)) {
                App.editorMode = modMapping.get(charTyped);
                HotActionUI.refresh(true);
                setCursors(App.editorMode);
                return;
            }
        }

        if (App.editorMode == EditorMode.INSERT) {
            origHandler.execute(editor, charTyped, dataContext);
            return;
        }

        Map<String, String> keyMapping = modeMap.get(App.editorMode);
        if (keyMapping.containsKey(String.valueOf(charTyped))) {
            HotActionUI.hot(charTyped, keyMapping.get(String.valueOf(charTyped)));
            doAction(dataContext, keyMapping.get(String.valueOf(charTyped)));
        }

    }


    private void doAction(final DataContext dataContext, String actionName) {
        final AnAction action = ActionManager.getInstance().getAction(actionName);
        if (action != null) {
            ApplicationManager.getApplication().invokeLater(() -> {
                try {
                    action.actionPerformed(AnActionEvent.createFromAnAction(action, null, "", dataContext));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }


    public TypedActionHandler getOriginalTypedHandler() {
        return origHandler;
    }


}
