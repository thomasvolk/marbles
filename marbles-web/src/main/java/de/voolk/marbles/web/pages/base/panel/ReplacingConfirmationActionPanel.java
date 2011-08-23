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
package de.voolk.marbles.web.pages.base.panel;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

public abstract class ReplacingConfirmationActionPanel extends ConfirmationActionPanel {
    private static final long serialVersionUID = 2L;
	private final Component original;

    public ReplacingConfirmationActionPanel(Component original, IModel<String> model) {
        this(original, model.getObject());
    }

    public ReplacingConfirmationActionPanel(Component original, String text) {
        super(original.getId(), text);
        this.original = original;
        original.replaceWith(this);
    }

	@Override
    public void cancel() {
        replaceWith(original);
    }
}
