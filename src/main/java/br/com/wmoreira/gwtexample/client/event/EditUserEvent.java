package br.com.wmoreira.gwtexample.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditUserEvent extends GwtEvent<EditUserEventHandler> {
    public static Type<EditUserEventHandler> TYPE = new Type<EditUserEventHandler>();
    
    @Override
    public Type<EditUserEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(EditUserEventHandler handler) {
	handler.onEditUser(this);
    }

}
