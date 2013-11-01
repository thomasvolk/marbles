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
package de.voolk.marbles;

import de.voolk.marbles.test.Jetty;

public class Marbles {
    private static final String NAME = "marbles";
    private static final String WEBAPP = "src/main/webapp";
    private static final int PORT = 9099;

    public static void main(String[] args) throws Exception {
        //System.setProperty("marbles.env", "inttest");
        new Jetty(NAME, WEBAPP, PORT, "marbles-web").run();
    }
}