package br.com.wmoreira.gwtexample.client.view.util;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ConfirmDialog
    extends DialogBox {

    public ConfirmDialog(String text, ClickHandler okHandler) {
	ClickHandler closeHandler = new ClickHandler() {

	    public void onClick(ClickEvent event) {
		ConfirmDialog.this.hide();
	    }
	};
	
	setText(text);
	setAnimationEnabled(true);
	setGlassEnabled(true);

	HorizontalPanel buttonPanel = new HorizontalPanel();
	Button cancel = new Button("Não");
	Button ok = new Button("Sim");
	
	buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	buttonPanel.setWidth("100%");

	ok.addClickHandler(okHandler);

	cancel.addClickHandler(closeHandler);
	ok.addClickHandler(closeHandler);
	
	buttonPanel.add(cancel);
	buttonPanel.add(ok);

	setWidget(buttonPanel);
	this.center();
	this.setPopupPosition(getPopupLeft(), getPopupTop());
    }
    
    public ConfirmDialog(String text, ClickHandler okHandler, ClickHandler cancelHandler) {
	ClickHandler closeHandler = new ClickHandler() {

	    public void onClick(ClickEvent event) {
		ConfirmDialog.this.hide();
	    }
	};
	
	setText(text);
	setAnimationEnabled(true);
	setGlassEnabled(true);

	HorizontalPanel buttonPanel = new HorizontalPanel();
	Button cancel = new Button("Não");
	Button ok = new Button("Sim");
	
	buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	buttonPanel.setWidth("100%");

	cancel.addClickHandler(cancelHandler);
	ok.addClickHandler(okHandler);

	cancel.addClickHandler(closeHandler);
	ok.addClickHandler(closeHandler);
	
	buttonPanel.add(cancel);
	buttonPanel.add(ok);

	setWidget(buttonPanel);
	this.center();
	this.setPopupPosition(getPopupLeft(), getPopupTop());
    }

}
