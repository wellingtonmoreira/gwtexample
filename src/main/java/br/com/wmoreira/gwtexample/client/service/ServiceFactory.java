package br.com.wmoreira.gwtexample.client.service;

import com.google.gwt.core.shared.GWT;


public class ServiceFactory {

    private static GroupServiceAsync groupService;
    private static UserServiceAsync userService;
    
    public static GroupServiceAsync getGroupService() {
	if (groupService == null) {
	    groupService = GWT.create(GroupService.class);
	}
	
	return groupService;
    }
    
    public static UserServiceAsync getUserService() {
	if (userService == null) {
	    userService = GWT.create(UserServiceAsync.class);
	}
	
	return userService;
    }
    
}
