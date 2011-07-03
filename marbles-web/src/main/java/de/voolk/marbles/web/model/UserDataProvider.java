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
import de.voolk.marbles.persistence.services.IAuthentificationService;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;
import java.util.List;

public class UserDataProvider implements IDataProvider<User> {
    private static final long serialVersionUID = 2L;
    private List<? extends User> users;
    private IAuthentificationService authentificationService;

    public UserDataProvider(IAuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    private List<? extends User> getUsers() {
        if(users == null) {
            users = this.authentificationService.findAllUsers();
        }
        return users;
    }

    @Override
    public Iterator<? extends User> iterator(int first, int count) {
        return getUsers().subList(first, first + count).iterator();
    }

    @Override
    public int size() {
        return getUsers().size();
    }

    @Override
    public IModel<User> model(User rUser) {
        return new DetachableUserModel(rUser);
    }

    @Override
    public void detach() {
    }
}
