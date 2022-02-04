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


## ❇️ Step by Step Solution
➡️ The best way to decouple compression logic from the `ImageStorage` class would be to make an interface named `Compressor`, and implement it in several compressor classes where each class will have a unique concrete implementation of the compression logic.
```java
public interface Compressor {
    public void compressImage();
}
```
```java
public class JPEGCompressor implements Compressor {
    @Override
    public void compressImage() {
        System.out.println("Compressing JPEG");
    }
}

public class PNGCompressor implements Compressor {
    @Override
    public void compressImage() {
        System.out.println("Compressing PNG");
    }
}

public class GIFCompressor implements Compressor {
    @Override
    public void compressImage() {
        System.out.println("Compressing GIF");
    }
}
```
We've successfully decoupled compression logic from the `ImageStorage` class. That class no longer contains any compression logic. Introducing new compressors will be just a matter of creating a new class that implements that `Compressor` interface.

➡️ Similarly, we can decouple the filter logic from `ImageStorage` class by creating a new interface `Filter` and implementing it in various filter classes. 
```java
public interface Filter {
    public void applyFilter();
}
```
```java
public class BlackAndWhiteFilter implements Filter {
    @Override
    public void applyFilter() {
        System.out.println("Applying B&W filter");
    }
}

public class SepiaFilter implements Filter {
    @Override
    public void applyFilter() {
        System.out.println("Applying Sepia filter");
    }
}

public class HighContrastFilter implements Filter {
    @Override
    public void applyFilter() {
        System.out.println("Applying High Contrast filter");
    }
}
```
Now our filter logic has been decoupled as well.

➡️ Now, our `ImageStorage` class will look like this:
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
        compressor.compressImage();
    }

    // filter logic based on user input
    public void applyFilter() {
        filter.applyFilter();
    }
}
```
And, our driver code in the `Main` class will look like this:
```java
public class Main {
    public static void main(String[] args) {

        var img = new ImageStorage(new JPEGCompressor(), new HighContrastFilter());

        img.compressImage();    // prints "Compressing JPEG"
        img.applyFilter();      // prints "Applying High Contrast filter"
    }
}
```


## ❇️ Final Solution
We have separated logic for different compressors and filters from our `ImageStorage` class to adhere to the SOLID principles. Now adding new compressor or filter to our codebase will be just a matter of creating new classes and implementing the interfaces. Thus, our code is now easier to maintain and extend.

_N.B._ The difference between the **State** pattern and the **Strategy** pattern:
1. In State pattern, there is a state that determines how the methods will behave. Meaning, the behavior of all methods depend on what state the program is in.
2. In strategy pattern, there is no state that dictates how the methods will behave. They can act independently.


Final solution code can be found ➡️ [here](https://github.com/TahsinTunan/Design-Patterns-in-Java/tree/main/Design%20Patterns/Behavioral/Strategy/Code)
