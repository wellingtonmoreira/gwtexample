package br.com.wmoreira.gwtexample.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainPresenter implements Presenter{

	public interface Display{
		HasClickHandlers getEditUser();
		HasClickHandlers getUsers();
		HasClickHandlers getEditGroup();
		HasClickHandlers getGroups();
		Widget asWidget();
	}
	
	private final HandlerManager eventBus;
	private final Display display;
	
	public MainPresenter(HandlerManager eventBus, Display view) {
		this.eventBus = eventBus;
		this.display = view;
	}
	
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}

}
