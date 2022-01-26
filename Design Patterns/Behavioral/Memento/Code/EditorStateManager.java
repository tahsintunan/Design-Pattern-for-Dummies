package Behavioral.Memento.Code;
import java.util.Stack;

public class EditorStateManager {
    private final Stack<EditorState> previousEditorStates = new Stack<>();

    // updates the stack with the last editorState
    public void savePreviousState(EditorState editorState) {
        this.previousEditorStates.push(editorState);
    }

    // takes out the last editorState from the stack and returns it
    public EditorState getPreviousState() {
        return this.previousEditorStates.pop();
    }
}
