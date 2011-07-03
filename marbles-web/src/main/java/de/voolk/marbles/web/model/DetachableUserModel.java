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
package de.voolk.marbles.web.model;

import de.voolk.marbles.persistence.beans.User;
import org.apache.wicket.model.LoadableDetachableModel;


public class DetachableUserModel extends LoadableDetachableModel<User> {
	private static final long serialVersionUID = 2L;
	private User user;

    public DetachableUserModel(User user) {
        this.user = user;
    }

    @Override
    protected User load() {
        return user;
    }
}
