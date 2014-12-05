package br.com.wmoreira.gwtexample.client.presenter;

import java.sql.Date;

import br.com.wmoreira.gwtexample.client.event.UsersEvent;
import br.com.wmoreira.gwtexample.client.service.ServiceFactory;
import br.com.wmoreira.gwtexample.client.service.UserServiceAsync;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.Widget;


public class EditUserPresenter implements Presenter {

    private UserServiceAsync userService;
    private HandlerManager eventBus;
    private Display display;
    private User user;
    
    public interface Display {
	ValueBoxBase<String> getNameField();
	ValueBoxBase<String> getPasswordField();
	ValueBoxBase<String> getEmailField();
	HasValue<Boolean> getEnabledCheckbox();
	HasClickHandlers getSaveButton();
	HasClickHandlers getCancelButton();
	void setTitle(String text);
	void alertDialog(String text);
	Widget asWidget();
    }
    
    public EditUserPresenter(HandlerManager eventBus, Display display) {
	userService = ServiceFactory.getUserService();
	this.eventBus = eventBus;
	this.display = display;
	display.setTitle("Cadastrar Usuário");
	bind();
    }
    
    public EditUserPresenter(HandlerManager eventBus, Display display, User user) {
	userService = ServiceFactory.getUserService();
	
	this.eventBus = eventBus;
	this.display = display;
	display.setTitle("Editar Usuário");
	this.user = user;
	bind(this.user);
    }
    
    private void bind() {
	display.getSaveButton().addClickHandler(new ClickHandler() {
	    
	    @Override
	    public void onClick(ClickEvent event) {
		doAddUser();
	    }
	});
    }
    
    private void bind(User user) {
	display.getNameField().setText(user.getName());
	display.getPasswordField().setText(user.getPassword());
	display.getEmailField().setText(user.getEmail());
	display.getEnabledCheckbox().setValue(user.isEnabled());
	display.getSaveButton().addClickHandler(new ClickHandler() {
	    
	    @Override
	    public void onClick(ClickEvent event) {
		doEditUser();
	    }
	});
	
	display.getCancelButton().addClickHandler(new ClickHandler() {
	    
	    @Override
	    public void onClick(ClickEvent event) {
		eventBus.fireEvent(new UsersEvent());
	    }
	});
    }
    
    @Override
    public void go(HasWidgets container) {
	container.clear();
	container.add(display.asWidget());
    }
    
    private void doAddUser() {
	User user = new User();
	user.setName(display.getNameField().getText());
	user.setPassword(display.getPasswordField().getText());
	user.setEmail(display.getEmailField().getText());
	user.setEnabled(display.getEnabledCheckbox().getValue());
	user.setCreationDate(new Date(new java.util.Date().getTime()));
	
	userService.create(user, new AsyncCallback<Integer>() {
	    
	    @Override
	    public void onSuccess(Integer result) {
		display.alertDialog("Usuário cadastrado com sucesso!");
		eventBus.fireEvent(new UsersEvent());
	    }
	    
	    @Override
	    public void onFailure(Throwable caught) {
		display.alertDialog("Ocorreu um erro ao inserir o grupo: " + caught.getMessage());
	    }
	});
    }
    
    private void doEditUser() {
	user.setName(display.getNameField().getText());
	user.setPassword(display.getPasswordField().getText());
	user.setEmail(display.getEmailField().getText());
	user.setEnabled(display.getEnabledCheckbox().getValue());
	
	userService.update(user, new AsyncCallback<Integer>() {
	    
	    @Override
	    public void onSuccess(Integer result) {
		display.alertDialog("Grupo atualizado com sucesso!");
		eventBus.fireEvent(new UsersEvent());
	    }
	    
	    @Override
	    public void onFailure(Throwable caught) {
		display.alertDialog("Ocorreu um erro ao atualizar o grupo: " + caught.getMessage());
	    }
	});
    }

}
