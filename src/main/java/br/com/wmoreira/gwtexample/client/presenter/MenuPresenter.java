package br.com.wmoreira.gwtexample.client.presenter;

import br.com.wmoreira.gwtexample.client.event.EditGroupEvent;
import br.com.wmoreira.gwtexample.client.event.EditUserEvent;
import br.com.wmoreira.gwtexample.client.event.GroupsEvent;
import br.com.wmoreira.gwtexample.client.event.UsersEvent;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

public class MenuPresenter implements Presenter {

    private final HandlerManager eventBus;
    private final Display display;
    
    public MenuPresenter(HandlerManager eventBus, Display display) {
	this.eventBus = eventBus;
	this.display = display;
    }

    public interface Display {
	MenuItem getEditUser();
	MenuItem getUsers();
	MenuItem getEditGroup();
	MenuItem getGroups();
	Widget asWidget();
    }

    public void bind() {
	display.getGroups().setScheduledCommand(new ScheduledCommand() {
	    @Override
	    public void execute() {
		eventBus.fireEvent(new GroupsEvent());
	    }
	});

	display.getEditGroup().setScheduledCommand(new ScheduledCommand() {
	    @Override
	    public void execute() {
		eventBus.fireEvent(new EditGroupEvent());
	    }
	});

	display.getUsers().setScheduledCommand(new ScheduledCommand() {

	    @Override
	    public void execute() {
		eventBus.fireEvent(new UsersEvent());
	    }
	});

	display.getEditUser().setScheduledCommand(new ScheduledCommand() {

	    @Override
	    public void execute() {
		eventBus.fireEvent(new EditUserEvent());
	    }
	});
    }

    @Override
    public void go(HasWidgets container) {
	bind();
	container.clear();
	container.add(display.asWidget());
    }

}
