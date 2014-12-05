package br.com.wmoreira.gwtexample.client;

import br.com.wmoreira.gwtexample.client.service.GroupService;
import br.com.wmoreira.gwtexample.client.service.GroupServiceAsync;
import br.com.wmoreira.gwtexample.client.service.UserService;
import br.com.wmoreira.gwtexample.client.service.UserServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	UserServiceAsync userService = GWT.create(UserService.class);
	GroupServiceAsync groupService = GWT.create(GroupService.class);
	
	HandlerManager eventBus = new HandlerManager(null);
    AppController appViewer = new AppController(eventBus, userService, groupService);
    
    appViewer.go(RootPanel.get("menuPanel"), RootPanel.get("contentPanel"));
  }
}
