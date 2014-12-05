package br.com.wmoreira.gwtexample.client.presenter;

import java.util.List;

import br.com.wmoreira.gwtexample.client.event.EditGroupEvent;
import br.com.wmoreira.gwtexample.client.service.GroupServiceAsync;
import br.com.wmoreira.gwtexample.client.service.ServiceFactory;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class GroupsPresenter implements Presenter {

    private final GroupServiceAsync groupService;
    private HandlerManager          eventBus;
    private Display                 display;
    private List<Group>             groups;

    public interface Display {
	HasClickHandlers getEditButton();
	HasClickHandlers getDeleteButton();
	void alertDialog(String text);
	void confirmDialog(String text, ClickHandler okHandler);
	void setData(List<Group> data);
	int getClickedRow(ClickEvent event);
	Widget asWidget();
    }

    public GroupsPresenter(HandlerManager eventBus, Display display) {
	groupService = ServiceFactory.getGroupService();
	this.eventBus = eventBus;
	this.display = display;
	bind();
    }

    private void bind() {
	display.getEditButton().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		eventBus.fireEvent(new EditGroupEvent(groups.get(display.getClickedRow(event))));
	    }
	});

	display.getDeleteButton().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		display.confirmDialog("Confirma a ação?", new ClickHandler() {
		    
		    @Override
		    public void onClick(ClickEvent event) {
			deleteGroup(groups.get(display.getClickedRow(event)).getId());
		    }
		});
	    }
	});
    }

    @Override
    public void go(HasWidgets container) {
	loadContacts();

	container.clear();
	container.add(display.asWidget());
    }

    private void loadContacts() {
	groupService.findAll(new AsyncCallback<List<Group>>() {

	    @Override
	    public void onSuccess(List<Group> result) {
		groups = result;
		display.setData(groups);
	    }

	    @Override
	    public void onFailure(Throwable caught) {
		display.alertDialog("Ocorreu um erro ao listar os grupos: " + caught.getMessage());
	    }
	});
    }

    private void deleteGroup(int id) {
	groupService.delete(id, new AsyncCallback<Integer>() {
	    @Override
	    public void onSuccess(Integer result) {
		loadContacts();
		display.alertDialog("Grupo removido com sucesso!");
	    }

	    @Override
	    public void onFailure(Throwable caught) {
		display.alertDialog("Ocorreu um erro ao remover o grupo: " + caught.getMessage());
	    }
	});
    }

}
