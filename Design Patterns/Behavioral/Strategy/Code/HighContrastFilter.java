package Behavioral.Strategy.Code;

public class HighContrastFilter implements Filter {
    @Override
    public void applyFilter() {
        System.out.println("Applying High Contrast filter");
    }
}
