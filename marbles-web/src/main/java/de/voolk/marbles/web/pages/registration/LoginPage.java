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
package de.voolk.marbles.web.pages.registration;

import de.voolk.marbles.web.pages.base.AbstractPage;
import de.voolk.marbles.web.pages.base.panel.FooterPanel;
import de.voolk.marbles.web.pages.base.panel.HeaderPanel;
import de.voolk.marbles.web.pages.registration.panel.SignInPanel;

public class LoginPage extends AbstractPage {

	@Override
	protected void init() {
		add(new HeaderPanel("header", getSystemInfoService().getVersion()));
        add(new FooterPanel("footer"));
    	add(new SignInPanel("signInPanel"));
	}

}