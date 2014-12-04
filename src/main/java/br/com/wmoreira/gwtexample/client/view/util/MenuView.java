package br.com.wmoreira.gwtexample.client.view.util;

import br.com.wmoreira.gwtexample.client.view.core.ViewPort;

import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class MenuView extends HorizontalPanel implements Viewable{
    @Override
    final public void show() {
	ViewPort.setMenuView(this);
    }
}
