package Behavioral.Strategy.Code;

public class PNGCompressor implements Compressor {
    @Override
    public void compressImage() {
        System.out.println("Compressing PNG");
    }
}
