package Behavioral.State.Code;


public class Canvas {
    private Tool currentTool;

    public Tool getCurrentTool() { return currentTool; }
    public void setCurrentTool(Tool currentTool) { this.currentTool = currentTool; }

    // onMouseClick() of Canvas delegates the task to the onMouseClick() of Tool
    public void onMouseClick() {
        currentTool.onMouseClick();
    }
}
