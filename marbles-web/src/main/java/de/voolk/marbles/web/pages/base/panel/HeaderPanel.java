package de.voolk.marbles.web.pages.base.panel;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class HeaderPanel extends Panel {
    public HeaderPanel(String id, String version) {
        super(id);
        add(new Label("version", version));
    }
}
