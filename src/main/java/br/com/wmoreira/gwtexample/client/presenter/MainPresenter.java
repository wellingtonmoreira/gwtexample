package br.com.wmoreira.gwtexample.client.presenter;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

public class MainPresenter implements Presenter{

	public interface Display{
		MenuItem getEditUser();
		MenuItem getUsers();
		MenuItem getEditGroup();
		MenuItem getGroups();
		Widget asWidget();
	}
	
	private final HandlerManager eventBus;
	private final Display display;
	
	public MainPresenter(HandlerManager eventBus, Display view) {
		this.eventBus = eventBus;
		this.display = view;
	}
	
	public void bind() {
		display.getGroups().setScheduledCommand(new ScheduledCommand() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
			}
		});
		
		display.getEditGroup().setScheduledCommand(new ScheduledCommand() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				
			}
		});
		
		display.getUsers().setScheduledCommand(new ScheduledCommand() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				
			}
		});
		
		display.getEditUser().setScheduledCommand(new ScheduledCommand() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				
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
