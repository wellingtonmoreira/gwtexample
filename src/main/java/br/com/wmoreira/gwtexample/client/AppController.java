package br.com.wmoreira.gwtexample.client;

import br.com.wmoreira.gwtexample.client.event.EditGroupEvent;
import br.com.wmoreira.gwtexample.client.event.EditGroupEventHandler;
import br.com.wmoreira.gwtexample.client.event.EditUserEvent;
import br.com.wmoreira.gwtexample.client.event.EditUserEventHandler;
import br.com.wmoreira.gwtexample.client.event.GroupsEvent;
import br.com.wmoreira.gwtexample.client.event.GroupsEventHandler;
import br.com.wmoreira.gwtexample.client.event.UsersEvent;
import br.com.wmoreira.gwtexample.client.event.UsersEventHandler;
import br.com.wmoreira.gwtexample.client.presenter.EditGroupPresenter;
import br.com.wmoreira.gwtexample.client.presenter.GroupsPresenter;
import br.com.wmoreira.gwtexample.client.presenter.MenuPresenter;
import br.com.wmoreira.gwtexample.client.presenter.Presenter;
import br.com.wmoreira.gwtexample.client.view.EditGroupView;
import br.com.wmoreira.gwtexample.client.view.GroupsView;
import br.com.wmoreira.gwtexample.client.view.MenuView;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

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
		Group group = event.getGroup();
		if (group == null) {
		    doEditGroup();		    
		} else {
		    doEditGroup(group);
		}
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
    
    private void doEditGroup(Group group) {
	History.newItem(ViewToken.EDIT_GROUP, false);
	Presenter presenter = new EditGroupPresenter(eventBus, new EditGroupView(true), group);
	presenter.go(contentContainer);
	
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
	    History.newItem(ViewToken.MAIN);
	} else {
	    History.fireCurrentHistoryState();
	}
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
	String token = event.getValue();
	Presenter presenter = null;

	new MenuPresenter(eventBus, new MenuView()).go(menuContainer);
	
	if (token == ViewToken.MAIN) {
	    contentContainer.clear();
	} else if (token == ViewToken.GROUPS) {
	    presenter = new GroupsPresenter(eventBus, new GroupsView());
	} else if (token == ViewToken.EDIT_GROUP) {
	    presenter = new EditGroupPresenter(eventBus, new EditGroupView(false));
	} else if (token == ViewToken.USERS) {

	} else if (token == ViewToken.EDIT_USER) {

	}

	if (presenter != null) {
	    presenter.go(contentContainer);
	}
    }

    private class ViewToken {
	static final String MAIN = "principal";
	static final String GROUPS = "grupos";
	static final String EDIT_GROUP = "form-grupo";
	static final String USERS = "usuarios";
	static final String EDIT_USER = "form-usuario";
    }
}
