package br.com.wmoreira.gwtexample.client.view.form;

import br.com.wmoreira.gwtexample.client.service.GroupService;
import br.com.wmoreira.gwtexample.client.service.GroupServiceAsync;
import br.com.wmoreira.gwtexample.client.view.core.ViewPort;
import br.com.wmoreira.gwtexample.client.view.list.GroupListView;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class GroupFormView
    implements Viewable {

    private boolean           edit;
    private Group             editObject;
    private GroupServiceAsync groupService;

    public GroupFormView() {
	groupService = GWT.create(GroupService.class);
	edit = false;
    }

    public GroupFormView(Group editObject) {
	this();
	this.edit = true;
	this.editObject = editObject;
    }

    @Override
    public void showView() {
	Label title = new Label(edit ? "Editar Grupo" : "Cadastrar Grupo");
	title.setHeight("25px");

	FormPanel form = new FormPanel();

	VerticalPanel rows = new VerticalPanel();

	rows.setSpacing(10);

	HorizontalPanel row1 = new HorizontalPanel();

	Label nameLabel = new Label("Nome");
	final TextBox nameField = new TextBox();

	nameLabel.setWidth("40px");

	row1.add(nameLabel);
	row1.add(nameField);

	HorizontalPanel buttons = new HorizontalPanel();

	final AsyncCallback<Integer> createCallback = new AsyncCallback<Integer>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Ocorreu um erro: " + caught.getMessage());

	    }

	    @Override
	    public void onSuccess(Integer result) {
		Window.alert("Grupo cadastrado com sucesso!");
		new GroupListView().showView();
	    }
	};

	final AsyncCallback<Integer> updateCallback = new AsyncCallback<Integer>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Ocorreu um erro: " + caught.getMessage());

	    }

	    @Override
	    public void onSuccess(Integer result) {
		Window.alert("Grupo atualizado com sucesso!");
		new GroupListView().showView();
	    }
	};

	Button cancelButton = new Button("Cancelar");
	Button saveButton = new Button("Salvar");

	cancelButton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		new GroupListView().showView();
	    }
	});

	saveButton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Group group = new Group(nameField.getText());
		if (edit) {
		    group.setId(editObject.getId());
		    groupService.update(group, updateCallback);
		} else {
		    groupService.create(group, createCallback);
		}
	    }
	});

	if (edit) {
	    nameField.setText(editObject.getName());
	    buttons.add(cancelButton);
	}

	buttons.add(saveButton);

	rows.add(row1);
	rows.add(buttons);

	form.add(rows);

	ViewPort.setContentView(title, form);

    }

}
