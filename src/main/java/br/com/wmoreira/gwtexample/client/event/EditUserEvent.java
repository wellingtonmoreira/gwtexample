package br.com.wmoreira.gwtexample.client.event;

import br.com.wmoreira.gwtexample.shared.business.entity.User;

import com.google.gwt.event.shared.GwtEvent;

public class EditUserEvent extends GwtEvent<EditUserEventHandler> {
    public static Type<EditUserEventHandler> TYPE = new Type<EditUserEventHandler>();
    private User user;
    
    public EditUserEvent() {
	// default constructor
    }
    
    public EditUserEvent(User user) {
	this.user = user;
    }
    
    @Override
    public Type<EditUserEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(EditUserEventHandler handler) {
	handler.onEditUser(this);
    }
    
    public User getUser(){
	return user;
    }

}
