package br.com.wmoreira.gwtexample.client;

import br.com.wmoreira.gwtexample.client.presenter.Presenter;
import br.com.wmoreira.gwtexample.client.service.GroupServiceAsync;
import br.com.wmoreira.gwtexample.client.service.UserServiceAsync;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements ValueChangeHandler<String>{
	private final HandlerManager eventBus;
	private final UserServiceAsync userService;
	private final GroupServiceAsync groupService;
	private HasWidgets menuContainer;
	private HasWidgets contentContainer;

	public AppController(HandlerManager eventBus, UserServiceAsync userService, GroupServiceAsync groupService) {
		this.eventBus = eventBus;
		this.userService = userService;
		this.groupService = groupService;
		bind();
	}
	
	private void bind() {
		History.addValueChangeHandler(this);
	}
	
	public void go(HasWidgets menuContainer, HasWidgets contentContainer) {
		this.menuContainer = menuContainer;
		this.contentContainer = contentContainer;
		
		if ("".equals(History.getToken())) {
			History.newItem("main");
		} else {
			History.fireCurrentHistoryState();
		}
		
	}
	
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
		if (token != null) {
			Presenter presenter = null;
			
			if (token.equals("main")) {
				
			}
			
			if (presenter != null) {
				if (token.equals("main")) {
					presenter.go(menuContainer); 
				} else {
					presenter.go(contentContainer);					
				}
			}
		}
		
	}


}
