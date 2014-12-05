package br.com.wmoreira.gwtexample.client.presenter;

import java.util.List;

import br.com.wmoreira.gwtexample.client.event.EditUserEvent;
import br.com.wmoreira.gwtexample.client.service.ServiceFactory;
import br.com.wmoreira.gwtexample.client.service.UserServiceAsync;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class UsersPresenter implements Presenter {

    private final UserServiceAsync  userService;
    private HandlerManager          eventBus;
    private Display                 display;
    private List<User>              users;

    public interface Display {
	HasClickHandlers getEditButton();
	HasClickHandlers getDeleteButton();
	void alertDialog(String text);
	void confirmDialog(String text, ClickHandler okHandler);
	void setData(List<User> data);
	int getClickedRow(ClickEvent event);
	Widget asWidget();
    }

    public UsersPresenter(HandlerManager eventBus, Display display) {
	userService = ServiceFactory.getUserService();
	this.eventBus = eventBus;
	this.display = display;
	bind();
    }

    private void bind() {
	display.getEditButton().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		eventBus.fireEvent(new EditUserEvent(users.get(display.getClickedRow(event))));
	    }
	});

	display.getDeleteButton().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		display.confirmDialog("Confirma a ação?", new ClickHandler() {
		    
		    @Override
		    public void onClick(ClickEvent event) {
			deleteUser(users.get(display.getClickedRow(event)).getId());
		    }
		});
	    }
	});
    }

    @Override
    public void go(HasWidgets container) {
	loadUsers();

	container.clear();
	container.add(display.asWidget());
    }

    private void loadUsers() {
	userService.findAll(new AsyncCallback<List<User>>() {

	    @Override
	    public void onSuccess(List<User> result) {
		users = result;
		display.setData(users);
	    }

	    @Override
	    public void onFailure(Throwable caught) {
		display.alertDialog("Ocorreu um erro ao listar os usuários: " + caught.getMessage());
	    }
	});
    }

    private void deleteUser(int id) {
	userService.delete(id, new AsyncCallback<Integer>() {
	    @Override
	    public void onSuccess(Integer result) {
		loadUsers();
		display.alertDialog("Usuário removido com sucesso!");
	    }

	    @Override
	    public void onFailure(Throwable caught) {
		display.alertDialog("Ocorreu um erro ao remover o usuário: " + caught.getMessage());
	    }
	});
    }

}
