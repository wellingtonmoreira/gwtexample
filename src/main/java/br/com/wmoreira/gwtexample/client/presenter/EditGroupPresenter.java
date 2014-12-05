package br.com.wmoreira.gwtexample.client.presenter;

import br.com.wmoreira.gwtexample.client.event.GroupsEvent;
import br.com.wmoreira.gwtexample.client.service.GroupServiceAsync;
import br.com.wmoreira.gwtexample.client.service.ServiceFactory;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.Widget;


public class EditGroupPresenter implements Presenter {

    private GroupServiceAsync groupService;
    private HandlerManager eventBus;
    private Display display;
    
    public interface Display {
	ValueBoxBase<String> getNameField();
	HasClickHandlers getSaveButton();
	HasClickHandlers getCancelButton();
	void setTitle(String text);
	void alertDialog(String text);
	Widget asWidget();
    }
    
    public EditGroupPresenter(HandlerManager eventBus, Display display) {
	groupService = ServiceFactory.getGroupService();
	
	this.eventBus = eventBus;
	this.display = display;
	display.setTitle("Cadastrar Grupo");
	bind();
    }
    
    public EditGroupPresenter(HandlerManager eventBus, Display display, Group group) {
	groupService = ServiceFactory.getGroupService();
	
	this.eventBus = eventBus;
	this.display = display;
	display.setTitle("Editar Grupo");
	bind(group);
    }
    
    private void bind() {
	display.getSaveButton().addClickHandler(new ClickHandler() {
	    
	    @Override
	    public void onClick(ClickEvent event) {
		doAddGroup();
	    }
	});
    }
    
    private void bind(Group group) {
	display.getNameField().setText(group.getName());
	
	display.getSaveButton().addClickHandler(new ClickHandler() {
	    
	    @Override
	    public void onClick(ClickEvent event) {
		doEditGroup();
	    }
	});
	
	display.getCancelButton().addClickHandler(new ClickHandler() {
	    
	    @Override
	    public void onClick(ClickEvent event) {
		eventBus.fireEvent(new GroupsEvent());
	    }
	});
    }
    
    @Override
    public void go(HasWidgets container) {
	container.clear();
	container.add(display.asWidget());
    }
    
    private void doAddGroup() {
	groupService.create(new Group(display.getNameField().getText()), new AsyncCallback<Integer>() {
	    
	    @Override
	    public void onSuccess(Integer result) {
		display.alertDialog("Grupo cadastrado com sucesso!");
		eventBus.fireEvent(new GroupsEvent());
	    }
	    
	    @Override
	    public void onFailure(Throwable caught) {
		display.alertDialog("Ocorreu um erro ao inserir o grupo: " + caught.getMessage());
	    }
	});
    }
    
    private void doEditGroup() {
	groupService.update(new Group(display.getNameField().getText()), new AsyncCallback<Integer>() {
	    
	    @Override
	    public void onSuccess(Integer result) {
		display.alertDialog("Grupo atualizado com sucesso!");
		eventBus.fireEvent(new GroupsEvent());
	    }
	    
	    @Override
	    public void onFailure(Throwable caught) {
		display.alertDialog("Ocorreu um erro ao atualizar o grupo: " + caught.getMessage());
	    }
	});
    }

}
