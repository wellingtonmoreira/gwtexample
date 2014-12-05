package br.com.wmoreira.gwtexample.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class EditGroupEvent extends GwtEvent<EditGroupEventHandler>{
    public static Type<EditGroupEventHandler> TYPE = new Type<EditGroupEventHandler>();
    
    @Override
    public Type<EditGroupEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(EditGroupEventHandler handler) {
	handler.onEditGroup(this);
    }

}
