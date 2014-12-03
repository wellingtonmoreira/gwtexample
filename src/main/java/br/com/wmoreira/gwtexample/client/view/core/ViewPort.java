package br.com.wmoreira.gwtexample.client.view.core;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class ViewPort {

    public static void setMenuView(Widget... object) {
	RootPanel.get(Panel.MENUPANEL.getPanelName()).clear(true);
	for (Widget w : object) {
	    RootPanel.get(Panel.MENUPANEL.getPanelName()).add(w);
	}
    }

    public static void setContentView(Widget... object) {
	RootPanel.get(Panel.CONTENTPANEL.getPanelName()).clear(true);
	for (Widget w : object) {
	    RootPanel.get(Panel.CONTENTPANEL.getPanelName()).add(w);
	}
    }

    private enum Panel {
	MENUPANEL("menuPanel"), CONTENTPANEL("contentPanel");

	private String panelName;

	Panel(String panelName) {
	    this.panelName = panelName;
	}

	public String getPanelName() {
	    return panelName;
	}
    }
}
