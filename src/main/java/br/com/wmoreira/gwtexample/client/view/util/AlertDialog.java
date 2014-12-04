package br.com.wmoreira.gwtexample.client.view.util;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class AlertDialog
    extends DialogBox {

    public AlertDialog(String text) {
	setText(text);
	setAnimationEnabled(true);
	setGlassEnabled(true);

	HorizontalPanel buttonPanel = new HorizontalPanel();
	Button ok = new Button("OK");
	
	buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	buttonPanel.setWidth("100%");
	
	ok.addClickHandler(new ClickHandler() {

	    public void onClick(ClickEvent event) {
		AlertDialog.this.hide();
	    }
	});
	
	buttonPanel.add(ok);
	
	setWidget(buttonPanel);
	this.center();
	this.setPopupPosition(getPopupLeft(), getPopupTop());
    }

}
