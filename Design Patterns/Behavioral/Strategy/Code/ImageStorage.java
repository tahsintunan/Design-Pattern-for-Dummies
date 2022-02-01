package Behavioral.Strategy.Code;


public class ImageStorage {
    private Compressor compressor;
    private Filter filter;

    public ImageStorage(Compressor compressor, Filter filter) {
        this.compressor = compressor;
        this.filter = filter;
    }


    // compression logic depending on the image format
    public void compressImage() {
        compressor.compressImage();
    }

    // filter logic based on user input
    public void applyFilter() {
        filter.applyFilter();
    }
}
