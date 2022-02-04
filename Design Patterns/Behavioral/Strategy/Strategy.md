# Strategy Design Pattern


## ❇️ Description:
**Strategy pattern** is a behavioral design pattern that lets us define a family of algorithms, put each of them into a separate class, and make their objects interchangeable. 


## ❇️ Problem
Let's say we're building an app where users can upload and store images. Here, users can upload images with different file formats and the app compresses them accordingly. The user can also apply filters on the pictures while uploading. Let's make a class `ImageStorage` where we'll have these functionalities:
```java
public enum Compressor {
    JPEG,
    PNG,
    GIF
}
public enum Filter {
    BLACK_AND_WHITE,
    SEPIA,
    HIGH_CONTRAST
}
```
```java
public class ImageStorage {
    private Compressor compressor;
    private Filter filter;

    public ImageStorage(Compressor compressor, Filter filter) {
        this.compressor = compressor;
        this.filter = filter;
    }


    // compression logic depending on the image format
    public void compressImage() {
        if (compressor == Compressor.JPEG) {
            System.out.println("Compressing JPEG");
        }
        else if (compressor == Compressor.PNG) {
            System.out.println("Compressing PNG");
        }
        else if (compressor == Compressor.GIF) {
            System.out.println("Compressing GIF");
        }
    }

    // filter logic based on user input
    public void applyFilter() {
        if (filter == Filter.BLACK_AND_WHITE) {
            System.out.println("Applying B&W filter");
        }
        else if (filter == Filter.SEPIA) {
            System.out.println("Applying Sepia filter");
        }
        else if (filter == Filter.HIGH_CONTRAST) {
            System.out.println("Applying High Contrast filter");
        }
    }
}
```
The driver code will look like this:
```java
public class Main {
    public static void main(String[] args) {

        var img = new ImageStorage(Compressor.JPEG, Filter.HIGH_CONTRAST);

        img.compressImage();    // prints "Compressing JPEG"
        img.applyFilter();      // prints "Applying High Contrast filter"
    }
}
```

As you can already tell, this code is not extensible since adding new compression logic or filters would require us to modify the class again and again. So we need a better approach.

###### _(N.B. In our `ImageStorage` class, we're just printing out various things when the methods are run for demonstration purposes. In real life, they'd contain real functionalities and we'd have to actually pass an Image object in these methods.)_

