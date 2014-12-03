package br.com.wmoreira.gwtexample.client.view.util;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ViewPort {

	public static void setMenuView(Widget object) {
		RootPanel.get(Panel.MENUPANEL.getPanelName()).add(object);
	}

	public static void setContentView(Widget object) {
		RootPanel.get(Panel.CONTENTPANEL.getPanelName()).add(object);
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
