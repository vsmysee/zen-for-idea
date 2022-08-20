package com.intellij.ide.actions.custom;

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorAction
import com.intellij.openapi.editor.actionSystem.EditorActionHandler


class MotionGotoLineLastAction : EditorAction(object : EditorActionHandler() {
    override fun doExecute(editor: Editor, caret: Caret?, dataContext: DataContext) {
        var len = editor.document.textLength
        if (editor.document.charsSequence[editor.document.textLength - 1] == '\n') {
            len = len - 1
        }
        editor.caretModel.moveToOffset(len)
        EditorHelper.scrollCaretIntoView(editor)
    }
})
