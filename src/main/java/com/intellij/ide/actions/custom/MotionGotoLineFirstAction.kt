package com.intellij.ide.actions.custom;

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorAction
import com.intellij.openapi.editor.actionSystem.EditorActionHandler


class MotionGotoLineFirstAction : EditorAction(object : EditorActionHandler() {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext) {
        editor.caretModel.moveToOffset(0)
        EditorHelper.scrollCaretIntoView(editor)
    }
})
