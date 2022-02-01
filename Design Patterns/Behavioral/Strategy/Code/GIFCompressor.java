package Behavioral.Strategy.Code;

public class GIFCompressor implements Compressor {
    @Override
    public void compressImage() {
        System.out.println("Compressing GIF");
    }
}
