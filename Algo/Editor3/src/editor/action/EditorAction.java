/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.action;

import javax.swing.AbstractAction;

/**
 *
 * @author TuvaCe
 */
public abstract class EditorAction extends AbstractAction {

    protected char ch;

    public EditorAction(String name) {
        super(name);
    }

    public String getName() {
        return (String) getValue(AbstractAction.NAME);
    }
}
