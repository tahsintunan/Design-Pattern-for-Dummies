# State Design Pattern

## ❇️ Description:
**State pattern** is a design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class. We use polymorphism to achieve this. It falls under **behavioral** pattern category.


## ❇️ Problem
Let's say we want to build a drawing application like MS Paint. In MS Paint, we have access to many different tools, each made for doing different task. For example, when we click our mouse button, the pencil tool draws things, the eraser erases things we've drawn, and the color picker let's us pick color. The canvas acts differently depending on the tool we've selected. So we need a class `Canvas` that gives us these functionalities. Let's write down a dummy code for this:
```java
enum Tool {
    PENCIL,
    ERASER,
    COLOR_PICKER
}

public class Canvas {
    // currentTool holds the currently selected tool
    private Tool currentTool;

    // getter and setter for currentTool
    public Tool getCurrentTool() { return currentTool; }
    public void setCurrentTool(Tool currentTool) { this.currentTool = currentTool; }

    // when we click our mouse button, this method provides different functionality based on what tool is currently selected
    public void onMouseClick() {
        if (currentTool == Tool.PENCIL) {
            // functionality of pencil tool
            System.out.println("Draw with pencil");
        }
        else if (currentTool == Tool.ERASER) {
            // functionality of eraser tool
            System.out.println("Erase with eraser");
        }
        else if (currentTool == Tool.COLOR_PICKER) {
            // functionality of color picker tool
            System.out.println("Pick color with color picker");
        }
    }
}
```
The `Main` class would look like this:
```java
public class Main {
    public static void main(String[] args) {
        var canvas = new Canvas();

        canvas.setCurrentTool(Tool.COLOR_PICKER);
        canvas.onMouseClick();  // should print "Pick color with color picker"

        canvas.setCurrentTool(Tool.ERASER);
        canvas.onMouseClick();  // should print "Erase with eraser"
    }
}
```
Our current approach has the following problems:
1. It's neither extendable nor maintainable. Filling our `onMouseClick()` method with if-else statements is not only a bad approach, if we were to have 30 different tools instead of 3, our code would become unreadable and difficult to maintain.
2. All the functionalities of different tools will be in one single class `Canvas`. The tool functionality is tightly coupled with `Canvas` class.
3. Violates the **Open-Closed principle**. If we want to add new tools, we'll have to modify our existing code again and again. 

###### _(N.B. In our `Canvas` class, we're just printing out various things when `onMouseClick()` method is called for demonstration purposes. In real life, they'd contain real functionalities.)_


## ❇️ Step by Step Solution
➡️ Our `onMouseClick()` method in the `Canvas` class currently has all the tool functionalities as concrete implementations. To remove all the tool functionalities from this `onMouseClick()` method, we will need abstract implementation of the functionalities. For that, we can create an **abstract class** or an **interface** named `Tool`, which will have the abstract implementation of the method `onMouseClick()`. Deriving from `Tool`, we'll have our actual tools separated in different classes, each having their own implementation of that method. Let's see this in code:
```java
public interface Tool {
    public void onMouseClick();
}
```
```java
public class Pencil implements Tool {
    @Override
    public void onMouseClick() {
        // functionality of pencil tool
        System.out.println("Draw with pencil");
    }
}

public class Eraser implements Tool {
    @Override
    public void onMouseClick() {
        // functionality of eraser tool
        System.out.println("Erase with eraser");
    }
}

public class ColorPicker implements Tool {
    @Override
    public void onMouseClick() {
        // functionality of color picker tool
        System.out.println("Pick color with color picker");
    }
}
```
###### _(N.B. We could have used an abstract class instead of an interface as well. But since we have no common functionalities among the tools in this example, an interface makes more sense.)_


