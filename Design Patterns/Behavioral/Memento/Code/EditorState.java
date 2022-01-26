package Behavioral.Memento.Code;

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
