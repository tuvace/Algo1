/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import editor.action.*;
import editor.display.CharacterDisplay;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

/**
 * Editor is the main class of the editor application. It is mainly responsible
 * for creating the document and display models, and to connect them up.
 *
 * @author evenal
 */
public class Editor extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Editor editor = new Editor();
        editor.setVisible(true);

        editor.doc = new Document(editor.display);
    }

    private InputMap inputMap;
    private ActionMap actionMap;
    private CharacterDisplay display;
    Document doc;

    public Editor() throws HeadlessException {
        super("Simple Text Editor");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        display = new CharacterDisplay();
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(display, BorderLayout.CENTER);

        /**
         * The inputMap and actionMap determine what happens when the user
         * presses a key on the keyboard. The keys are not hard-coded to the
         * actions. The keyboard is
         */
        inputMap = display.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = display.getActionMap();
        addKeyMappings();
        pack();
    }

    protected void exit() {
        for (java.awt.Window win : java.awt.Dialog.getWindows()) {
            win.dispose();
        }
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
            frame.dispose();
        }
    }

    /**
     * Add a key mapping, which binds an action to a particular key (represented
     * by the keyStroke). Whenever the key is pressed, the actionPerformed()
     * method in the action will be called
     *
     * @param keyStroke key to bind
     * @param action    action to bind the key to
     */
    public void addKeyMapping(KeyStroke keyStroke, EditorAction action) {
        inputMap.put(keyStroke, action.getName());
        actionMap.put(action.getName(), action);
    }

    // Defined Ascii table for characters available.
    public void addKeyMappings() {
        inputMap.clear();
        actionMap.clear();
        for (char ch = '\b'; ch <= 'ø'; ch++) {
            String name = "insertChar";
            EditorAction action = new InsertAction(name, this);
            addKeyMapping(KeyStroke.getKeyStroke(ch), action);

            if (ch == '\b') {
                name = "deleteChar";
                EditorAction actions = new DeleteAction(name, this);
                addKeyMapping(KeyStroke.getKeyStroke(ch), actions);
            }
            if (ch == '\n') {
                name = "lineShift";
                EditorAction actionss = new EnterAction(name, this);
                addKeyMapping(KeyStroke.getKeyStroke(ch), actionss);
            }
            addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.ALT_MASK), new NavigationAction("moveLeft", "left", this));
            addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.ALT_MASK), new NavigationAction("moveRight", "right", this));
            addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.ALT_MASK), new NavigationAction("moveUp", "up", this));
            addKeyMapping(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.ALT_MASK), new NavigationAction("moveDown", "down", this));


        }
    }


    public CharacterDisplay getDisplay() {
        return display;
    }

    public Document getDocument() {
        return doc;
    }
}
