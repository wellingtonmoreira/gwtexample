package br.com.wmoreira.gwtexample.client.view.util;

import br.com.wmoreira.gwtexample.client.view.core.ViewPort;

import com.google.gwt.user.client.ui.FlowPanel;

public abstract class ContentView extends FlowPanel implements Viewable{
    @Override
    final public void show() {
	ViewPort.setContentView(this);
    }
}
