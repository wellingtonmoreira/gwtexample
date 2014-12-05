package br.com.wmoreira.gwtexample.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class UsersEvent extends GwtEvent<UsersEventHandler> {
    public static Type<UsersEventHandler> TYPE = new Type<UsersEventHandler>();
    
    @Override
    public Type<UsersEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(UsersEventHandler handler) {
	handler.onUsers(this);
    }
}
