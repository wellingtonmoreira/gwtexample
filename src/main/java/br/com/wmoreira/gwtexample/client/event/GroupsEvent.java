package br.com.wmoreira.gwtexample.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class GroupsEvent extends GwtEvent<GroupsEventHandler> {

    public static Type<GroupsEventHandler> TYPE = new Type<GroupsEventHandler>();
    
    @Override
    public GwtEvent.Type<GroupsEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(GroupsEventHandler handler) {
	handler.onGroups(this);
    }

}
