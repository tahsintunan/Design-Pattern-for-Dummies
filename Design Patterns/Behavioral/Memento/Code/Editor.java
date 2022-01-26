package Behavioral.Memento.Code;

public class Editor {
    private EditorState editorState;

    public void setEditorState(EditorState editorState) {
        this.editorState = editorState;
    }
    public EditorState getEditorState() { return editorState; }


    public void undo(EditorStateManager editorStateManager) {
        this.editorState = editorStateManager.getPreviousState();
    }
}
