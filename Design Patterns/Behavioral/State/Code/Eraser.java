package Behavioral.State.Code;

public class Eraser implements Tool {
    @Override
    public void onMouseClick() {
        // functionality of eraser tool
        System.out.println("Erase with eraser");
    }
}
