package editor.action;

import editor.Document;
import editor.Editor;

import java.awt.event.ActionEvent;

public class NavigationAction extends EditorAction {

    Editor editor;
    String direction;

    public NavigationAction(String name,String direction, Editor ed) {
        super(name);
        this.editor = ed;
        this.direction = direction;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Document doc = editor.getDocument();
        switch(direction) {

            case "left": doc.left();
            break;

            case "right": doc.right();
            break;

            case "up": doc.up();
            break;

            case "down": doc.down();
            break;

            default: System.out.println("Uknown direction");
            break;
        }
    }
}