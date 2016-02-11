/**
 * Listens for editor events to notify visualizer.
 * TODO: Visualizer = Editor Listener?
 */
/*
    Copywrite 2016 Will Winder

    This file is part of Universal Gcode Sender (UGS).

    UGS is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UGS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UGS.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.willwinder.ugs.nbp.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JEditorPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Element;

/**
 *
 * @author wwinder
 */
public class EditorListener implements CaretListener {
    HighlightListener hl = null;
    public void setHighlightListener(HighlightListener hl) {
        this.hl = hl;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        JEditorPane jep = null;
        if (e.getSource() instanceof JEditorPane) {
            jep = (JEditorPane) e.getSource();

            Element map = jep.getDocument().getDefaultRootElement();
            int startIndex = map.getElementIndex(jep.getSelectionStart());
            int endIndex   = map.getElementIndex(jep.getSelectionEnd());

            if (hl != null) {
                Collection<Integer> selectedLines = new ArrayList<>();
                for (int i = startIndex; i <= endIndex; i++) {
                    selectedLines.add(i);
                }
                hl.highlightsChanged(selectedLines);
            }
        }
    }
}
