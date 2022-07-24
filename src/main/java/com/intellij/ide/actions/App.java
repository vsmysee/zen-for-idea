package com.intellij.ide.actions;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import org.jetbrains.annotations.NotNull;


public class App implements ApplicationComponent {

    public static EditorMode editorMode = EditorMode.CMD;

    private TypedHandler handler;

    @Override
    public void initComponent() {

        EditorActionManager manager = EditorActionManager.getInstance();
        TypedAction action = manager.getTypedAction();

        handler = new TypedHandler(action.getHandler());
        action.setupHandler(handler);

        EditorFactory.getInstance().addEditorFactoryListener(new EditorCreated(), ApplicationManager.getApplication());
    }

    @Override
    public void disposeComponent() {
        EditorActionManager manager = EditorActionManager.getInstance();
        TypedAction action = manager.getTypedAction();
        action.setupHandler(handler.getOriginalTypedHandler());
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "ZenOfIDEA";
    }


}
