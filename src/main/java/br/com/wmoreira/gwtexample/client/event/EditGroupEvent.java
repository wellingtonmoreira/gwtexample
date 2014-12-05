package br.com.wmoreira.gwtexample.client.event;

import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.event.shared.GwtEvent;


public class EditGroupEvent extends GwtEvent<EditGroupEventHandler>{
    public static Type<EditGroupEventHandler> TYPE = new Type<EditGroupEventHandler>();
    
    private Group group;
    
    public EditGroupEvent() {
	// default constructor
    }
    
    public EditGroupEvent(Group group) {
	this.group = group;
    }
    
    @Override
    public Type<EditGroupEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(EditGroupEventHandler handler) {
	handler.onEditGroup(this);
    }
    
    public Group getGroup() {
	return group;
    }

}
