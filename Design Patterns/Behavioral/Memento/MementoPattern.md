# Memento Design Pattern

## ❇️ Description:
**Memento** pattern is used to restore state of an object to a previous state. Memento pattern falls under **behavioral** pattern category. This pattern is usually used to implement **undo** mechanism. Meeaning, if we want our software to have the ability to return back to it's previous state, **Memento** pattern is the way to go. 

## ❇️ Problem
Let's think of a scenerio where we're building a text editor software and we want to implement the undo mechanism in it. 
So let's have a new class named `Editor`. 
```java
public class Editor {
    // contentState holds the current state of the contents in the editor
    private String contentState;

    // Getter and Setter
    public String getContentState() { return contentState; }
    public void setContentState(String contentState) { this.contentState = contentState; }

    // This method should undo the last change
    public void undo() {
        // undo logic goes here
    }
}
```

In the driver `Main` class, we should be able to create a new `Editor` object. Then we should have the ability to change contents of the editor as much as we want. We should also be able to undo the changes (multiple times, if necessary) and go back to previous state of the editor.
```java
public class Main {
    public static void main(String[] args) {
        // new Editor instance
        var editor = new Editor();

        // setting new contentState several times
        editor.setContentState("Content-1");
        editor.setContentState("Content-2");
        editor.setContentState("Content-3");
        editor.setContentState("Content-4");
        editor.setContentState("Content-5");

        editor.undo();
        System.out.println(editor.getContentState());    // should return "Content-4"

        editor.undo();
        editor.undo();
        editor.undo();
        System.out.println(editor.getContentState());    // should return "Content-1"
    }
}
```
## ❇️ Step by Step Solution

➡️ The simplest way to solve this problem would be to introduce a new field named `previousContentState` in the Editor class. So every time we try to update the `contentState`, it first stores the current `contentState` to `previousContentState` and then update the `contentState`. This way, if we try to ever undo the change, the `Editor` will return the `previousContentState`.
```java
public class Editor {
    private String contentState;
    private String previousContentState;

    // setContentState first updates the previousContentState, then updates the contentState
    public void setContentState(String contentState) {
        this.previousContentState = this.contentState;
        this.contentState = contentState; 
    }
    public String getContentState() { return contentState; }

    // undo will return the previousContentState
    public String undo() {
        return this.previousContentState;
    }
}
```


