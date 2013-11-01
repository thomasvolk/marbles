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
package de.voolk.marbles.test;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import java.io.File;

public class Jetty {
	public static class Context {
	    private final String name;
	    private final String webapp;
	    private WebAppContext webAppContext;
        private final String projectName;
	    
		public Context(String name, String webapp, String projectName) {
			super();
			this.name = name;
			this.webapp = webapp;
            this.projectName = projectName;
		}
		
		public String getName() {
			return name;
		}
		public WebAppContext getWebAppContext() {
			if(webAppContext == null) {
				webAppContext = new WebAppContext();
				webAppContext.setContextPath("/" + name);
		        webAppContext.setWar(getWarPath());
			}
			return webAppContext;
		}
		public String getWarPath() {
			String war = webapp;
            if(!new File(war).exists()) {
                war = projectName + "/" + war;
            }
			return war;
		}

		@Override
		public String toString() {
			return String.format("Context[%s, %s]", getName(), getWarPath());
		}
		
	}
    private final Server server;

    public Jetty(String name, String webapp, int port, String projectName) {
    	this(port);
    	addContext(new Context(name, webapp, projectName));
    }

    public Jetty(int port) {
        server = new Server();
        SocketConnector connector = new SocketConnector();

        // Set some timeout options to make debugging easier.
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(port);
        server.setConnectors(new Connector[] { connector });
    }

    public void run() {
        try {
            start();
            System.in.read();
            System.out.println(">>> STOPPING SERVER");
            stop();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }

	public void stop() throws Exception, InterruptedException {
		server.stop();
		server.join();
	}

	public void start() throws Exception {
		System.out.println(">>> STARTING SERVER");
		server.start();
	}
    
    public void addContext(Context ctx) {
    	WebAppContext webAppContext = ctx.getWebAppContext();
    	webAppContext.setServer(server);
    	System.out.println(">>> ADD CONTEXT: " + ctx.toString());
		server.addHandler(webAppContext);
    }
}