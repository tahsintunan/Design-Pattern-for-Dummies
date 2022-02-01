package Behavioral.Strategy.Code;

public class JPEGCompressor implements Compressor {
    @Override
    public void compressImage() {
        System.out.println("Compressing JPEG");
    }
}
