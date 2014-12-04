package br.com.wmoreira.gwtexample.client.view;

import br.com.wmoreira.gwtexample.client.service.GroupService;
import br.com.wmoreira.gwtexample.client.service.GroupServiceAsync;
import br.com.wmoreira.gwtexample.client.view.util.AlertDialog;
import br.com.wmoreira.gwtexample.client.view.util.ContentView;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class EditGroupView extends ContentView {

    private boolean           edit;
    private Group             editObject;
    private GroupServiceAsync groupService;

    public EditGroupView() {
	this.edit = false;
	construct();
    }

    public EditGroupView(Group editObject) {
	this.edit = true;
	this.editObject = editObject;
	construct();
    }
    
    private void construct() {
	groupService = GWT.create(GroupService.class);
	
	FormPanel form = new FormPanel();

	this.setStyleName("content");

	Label title = new Label(edit ? "Editar Grupo" : "Cadastrar Grupo");
	title.setHeight("25px");
	title.setStyleName("h2");

	VerticalPanel rows = new VerticalPanel();

	rows.setSpacing(10);

	HorizontalPanel row1 = new HorizontalPanel();

	row1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

	Label nameLabel = new Label("Nome");
	final TextBox nameField = new TextBox();

	row1.add(nameLabel);
	row1.add(nameField);

	HorizontalPanel buttons = new HorizontalPanel();

	final AsyncCallback<Integer> createCallback = new AsyncCallback<Integer>() {

	    @Override
	    public void onFailure(Throwable caught) {
		new AlertDialog("Ocorreu um erro: " + caught.getMessage()).show();;

	    }

	    @Override
	    public void onSuccess(Integer result) {
		new AlertDialog("Grupo cadastrado com sucesso!").show();
		new GroupsView().show();
	    }
	};

	final AsyncCallback<Integer> updateCallback = new AsyncCallback<Integer>() {

	    @Override
	    public void onFailure(Throwable caught) {
		new AlertDialog("Ocorreu um erro: " + caught.getMessage()).show();;

	    }

	    @Override
	    public void onSuccess(Integer result) {
		new AlertDialog("Grupo atualizado com sucesso!").show();;
		new GroupsView().show();
	    }
	};

	Button cancelButton = new Button("Cancelar");
	Button saveButton = new Button("Salvar");

	cancelButton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		new GroupsView().show();
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

	this.add(title);
	this.add(form);
    }

}
