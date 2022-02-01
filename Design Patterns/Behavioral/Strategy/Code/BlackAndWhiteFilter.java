package Behavioral.Strategy.Code;

public class BlackAndWhiteFilter implements Filter {
    @Override
    public void applyFilter() {
        System.out.println("Applying B&W filter");
    }
}
