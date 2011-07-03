/**
* Copyright (C) 2010  Thomas Volk
* <p/>
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* <p/>
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
* <p/>
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package de.voolk.marbles.web.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings({"rawtypes", "serial"})
public abstract class ConfirmationActionPanel extends Panel {
	private static final long serialVersionUID = 2L;
	public ConfirmationActionPanel(String id, String text) {
        super(id);
        add(new Label("text", text));
        add(new Link("execute") {
            @Override
            public void onClick() {
                ConfirmationActionPanel.this.execute();
            }
        });
        add(new Link("cancel") {
            @Override
            public void onClick() {
                ConfirmationActionPanel.this.cancel();
            }
        });
    }

    public abstract void execute();
    public abstract void cancel();
}
