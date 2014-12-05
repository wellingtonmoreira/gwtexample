package br.com.wmoreira.gwtexample.client;

import br.com.wmoreira.gwtexample.client.event.EditGroupEvent;
import br.com.wmoreira.gwtexample.client.event.EditGroupEventHandler;
import br.com.wmoreira.gwtexample.client.event.EditUserEvent;
import br.com.wmoreira.gwtexample.client.event.EditUserEventHandler;
import br.com.wmoreira.gwtexample.client.event.GroupsEvent;
import br.com.wmoreira.gwtexample.client.event.GroupsEventHandler;
import br.com.wmoreira.gwtexample.client.event.UsersEvent;
import br.com.wmoreira.gwtexample.client.event.UsersEventHandler;
import br.com.wmoreira.gwtexample.client.presenter.MainPresenter;
import br.com.wmoreira.gwtexample.client.presenter.Presenter;
import br.com.wmoreira.gwtexample.client.view.MainView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements ValueChangeHandler<String> {

    private final HandlerManager eventBus;
    private HasWidgets           menuContainer;
    private HasWidgets           contentContainer;

    public AppController(HandlerManager eventBus) {
	this.eventBus = eventBus;
	bind();
    }

    private void bind() {
	History.addValueChangeHandler(this);

	eventBus.addHandler(GroupsEvent.TYPE, new GroupsEventHandler() {

	    @Override
	    public void onGroups(GroupsEvent event) {
		doGroups();
	    }
	});

	eventBus.addHandler(EditGroupEvent.TYPE, new EditGroupEventHandler() {

	    @Override
	    public void onEditGroup(EditGroupEvent event) {
		doEditGroup();
	    }
	});

	eventBus.addHandler(UsersEvent.TYPE, new UsersEventHandler() {

	    @Override
	    public void onUsers(UsersEvent event) {
		doUsers();
	    }
	});

	eventBus.addHandler(EditUserEvent.TYPE, new EditUserEventHandler() {

	    @Override
	    public void onEditUser(EditUserEvent event) {
		doEditUser();
	    }
	});
    }
    
    private void doGroups() {
	History.newItem(ViewToken.GROUPS.toString());
    }
    
    private void doEditGroup() {
	History.newItem(ViewToken.EDIT_GROUP.toString());
    }
    
    private void doUsers() {
	History.newItem(ViewToken.USERS.toString());
    }
    
    private void doEditUser() {
	History.newItem(ViewToken.EDIT_USER.toString());
    }

    public void go(HasWidgets menuContainer, HasWidgets contentContainer) {
	this.menuContainer = menuContainer;
	this.contentContainer = contentContainer;

	if ("".equals(History.getToken())) {
	    History.newItem(ViewToken.MAIN.toString());
	} else {
	    History.fireCurrentHistoryState();
	}
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
	String token = event.getValue();
	ViewToken parsedToken = ViewToken.valueOf(token);

	if (parsedToken != null) {
	    Presenter presenter = null;

	    if (parsedToken == ViewToken.MAIN) {
		presenter = new MainPresenter(eventBus, new MainView());
	    } else if (parsedToken == ViewToken.GROUPS) {
		
	    } else if (parsedToken == ViewToken.EDIT_GROUP) {
		
	    } else if (parsedToken == ViewToken.USERS) {
		
	    } else if (parsedToken == ViewToken.EDIT_USER) {
		
	    }

	    if (presenter != null) {
		if (presenter instanceof MainPresenter) {
		    presenter.go(menuContainer);
		} else {
		    presenter.go(contentContainer);
		}
	    }
	}
    }

    private enum ViewToken {
	MAIN, GROUPS, EDIT_GROUP, USERS, EDIT_USER;
    }
}
