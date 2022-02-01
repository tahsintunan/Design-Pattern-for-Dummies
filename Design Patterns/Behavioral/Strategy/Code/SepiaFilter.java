package Behavioral.Strategy.Code;

public class SepiaFilter implements Filter {
    @Override
    public void applyFilter() {
        System.out.println("Applying Sepia filter");
    }
}
