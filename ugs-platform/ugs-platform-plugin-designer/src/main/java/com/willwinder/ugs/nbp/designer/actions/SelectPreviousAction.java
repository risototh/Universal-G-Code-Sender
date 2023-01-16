/*
    Copyright 2023 Will Winder

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
package com.willwinder.ugs.nbp.designer.actions;

import com.willwinder.ugs.nbp.designer.entities.Entity;
import com.willwinder.ugs.nbp.designer.logic.Controller;
import com.willwinder.ugs.nbp.designer.logic.ControllerFactory;
import com.willwinder.ugs.nbp.lib.services.LocalizingService;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

/**
 * @author Joacim Breiler
 */
@ActionID(
        id = "com.willwinder.ugs.nbp.designer.actions.SelectPreviousAction",
        category = LocalizingService.CATEGORY_DESIGNER)
@ActionRegistration(
        displayName = "Select previous",
        lazy = false
)
@ActionReferences({
        @ActionReference(
                path = "Shortcuts",
                name = "SD-P")
})
public class SelectPreviousAction extends AbstractDesignAction {

    public SelectPreviousAction() {
        putValue("menuText", "Select previous");
        putValue(NAME, "Select previous");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Controller controller = ControllerFactory.getController();
        List<Entity> entities = controller.getDrawing().getEntities();
        if (!entities.isEmpty()) {
            int currentIndex = controller.getSelectionManager()
                    .getSelection()
                    .stream()
                    .findFirst()
                    .map(entities::indexOf)
                    .orElse(0);

            if (currentIndex > 0) {
                currentIndex--;
            }
            controller.getSelectionManager().setSelection(Collections.singletonList(entities.get(currentIndex)));
        }
    }
}
