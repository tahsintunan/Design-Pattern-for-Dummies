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
➡️ But this introduces a problem. In this way, we can undo only once. If we want to undo again, we will have no way of doing that. If we want to undo multiple times, we need a List, or even better, a Stack. Let's take a stack named `previousContentStates` in the `Editor` class and store previous content states in it when updating the content with `setContentState()` method. The `undo()` method will pop the last item from stach and set it to current `contentState`.
```java
public class Editor {
    private String contentState;
    private final Stack<String> previousContentStates = new Stack<>();

    // first pushes the previousContentState to the stack, then updates the contentState
    public void setContentState(String contentState) {
        this.previousContentStates.push(this.contentState);
        this.contentState = contentState;
    }
    public String getContentState() { return contentState; }

    
    // sets the contentState to the last previousContentState from stack
    public void undo() {
        this.contentState = this.previousContentStates.pop();
    }
}
```
➡️ Now, let's consider a possibility. What if some time in the future, we decide to indroduce new fields to our content state? Maybe we'd like to have our doc a title, a font, a fontsize, and we would like to undo all of them. Let's introduce these new fields in our `Editor` class and see how it goes:
```java
public class Editor {
    private String contentState;
    private final Stack<String> previousContentStates = new Stack<>();
    private String title;
    private final Stack<String> previousTitles = new Stack<>();
    private String fontName;
    private final Stack<String> previousFontNames = new Stack<>();
    private double fontSize;
    private final Stack<Double> previousFontSizes = new Stack<>();


    public void setContentState(String contentState, String title, String fontName, double fontSize) {
        this.previousContentStates.push(this.contentState);
        this.previousTitles.push(this.title);
        this.previousFontNames.push(this.fontName);
        this.previousFontSizes.push(this.fontSize);

        this.contentState = contentState;
        this.title = title;
        this.fontName = fontName;
        this.fontSize  =fontSize;
    }

    public String getContentState() { return contentState; }
    public String getTitle() { return title; }
    public String getFontName() { return fontName; }
    public double getFontSize() { return fontSize; }


    public void undo() {
        this.contentState = this.previousContentStates.pop();
        this.title = this.previousTitles.pop();
        this.fontName = this.previousFontNames.pop();
        this.fontSize = this.previousFontSizes.pop();
    }
}
```
This seems to be a huge problem. Just by adding 3 additional fields, we have made the class bloated. We had to made additional stacks for each of them. Our `setContentState()` method became bloated with all those fields. We had to create additional getters for all of them. Our `undo()` method also got bigger. 
Now imagine if we were to introduce 30 different fields instead of 3! The class would become so big that readability would be hindered, it would be harder for us to debug and maintain. It wouldn't be extensible anymore. Long story short, it violates the SOLID principles.
So, it'd be in our best interest to create a separate class that holds all the necessary fields. This will make our code readable, scalable and modular. Let's call this class `EditorState`. 
```java
public class EditorState {
    private String contentState;
    private String title;
    private String fontName;
    private double fontSize;

    public EditorState(String contentState, String title, String fontName, double fontSize) {
        this.contentState = contentState;
        this.title = title;
        this.fontName = fontName;
        this.fontSize = fontSize;
    }

    public String getContentState() {return contentState;}
    public String getTitle() {return title;}
    public String getFontName() {return fontName;}
    public double getFontSize() {return fontSize;}
}
```
The `Editor` class will now look like this:
```java
public class Editor {

    private EditorState editorState;
    private final Stack<EditorState> previousEditorStates = new Stack<>();

    public void setEditorState(EditorState editorState) {
        this.previousEditorStates.push(this.editorState);
        this.editorState = editorState;
    }
    public EditorState getEditorState() { return editorState; }

    public void undo() {
        this.editorState = this.previousEditorStates.pop();
    }
}
```
➡️ The above solution is a better solution, because it let's us undo multiple times without polluting the `Editor` class with lots of fields. However, this solution is violating a very important principle: Single-Responsibility principle. 
To build maintainable software, we should design our classes in such a way that they have only one responsibility. In our case, our `Editor` class has two responsibilities. 
1. Management of editor states (needed for undo mechanism)
2. Providing other features we need from an editor. 

A good way to separate state management from the `Editor` class would be to take state management outside from class `Editor` and put it somewhere else. We can create a new class for that job. Let's name that class `EditorStateManager`.
```java
public class EditorStateManager {
    private final Stack<EditorState> previousEditorStates = new Stack<>();

    // updates the stack with the last editorState
    public void savePreviousState(EditorState editorState) {
        this.previousEditorStates.push(editorState);
    }

    // takes out the last editorState from the stack and returns it
    public EditorState getPreviousState() {
        return this.previousEditorStates.pop();
    }
}
```
Now the `Editor` class should look like this:
```java
public class Editor {
    private EditorState editorState;

    public void setEditorState(EditorState editorState) {
        this.editorState = editorState;
    }
    public EditorState getEditorState() { return editorState; }


    public void undo(EditorStateManager editorStateManager) {
        this.editorState = editorStateManager.getPreviousState();
    }
}
```
Now, we have successfully separated state management from the `Editor` class. Now let's see how our driver code (in `Main` class) would look like:
```java
public class Main {
    public static void main(String[] args) {

        var editor = new Editor();
        var stateManager = new EditorStateManager();

        editor.setEditorState(new EditorState("Content-1", "Title-1", "Arial", 12));

        stateManager.savePreviousState(editor.getEditorState());
        editor.setEditorState(new EditorState("Content-2", "Title-2", "Arial", 13));

        stateManager.savePreviousState(editor.getEditorState());
        editor.setEditorState(new EditorState("Content-3", "Title-3", "Arial", 14));

        stateManager.savePreviousState(editor.getEditorState());
        editor.setEditorState(new EditorState("Content-4", "Title-4", "Arial", 15));

        stateManager.savePreviousState(editor.getEditorState());
        editor.setEditorState(new EditorState("Content-5", "Title-5", "Arial", 16));


        editor.undo(stateManager);
        System.out.println(editor.getEditorState());    // gives us 4th editorState

        editor.undo(stateManager);
        editor.undo(stateManager);
        editor.undo(stateManager);
        System.out.println(editor.getEditorState());    // gives us 1st editorState
    }
}
```
###### _(In the driver code above, we could have eliminated repition and achieved a cleaner code by making another method that did both 1. State Management and 2. Setting EditorState, but that's outside of the scope of **Memento design pattern**)_


## Final Solution
In our representation of the **Memento pattern** above, the `Editor`, `EditorState`, and `EditorStateManager` classes are the three main actors. In the original Gang of Four book, they call these classes `Originator`, `Memento`, and `Caretaker` respectively. 
- `Memento` contains state of an object to be restored
- `Originator` creates and stores states in Memento objects
- `Caretaker` object is responsible to restore object state from Memento

Final solution code can be found [here](https://github.com/TahsinTunan/Design-Patterns-in-Java/tree/main/Design%20Patterns/Behavioral/Memento/Code)
