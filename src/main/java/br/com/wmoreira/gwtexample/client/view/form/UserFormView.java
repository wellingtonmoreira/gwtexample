package br.com.wmoreira.gwtexample.client.view.form;

import java.sql.Date;

import br.com.wmoreira.gwtexample.client.service.UserService;
import br.com.wmoreira.gwtexample.client.service.UserServiceAsync;
import br.com.wmoreira.gwtexample.client.view.core.ViewPort;
import br.com.wmoreira.gwtexample.client.view.list.UserListView;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class UserFormView
    implements Viewable {

    private boolean          edit;
    private User             editObject;
    private UserServiceAsync userService;

    public UserFormView() {
	userService = GWT.create(UserService.class);
	edit = false;
    }

    public UserFormView(User editObject) {
	this();
	this.edit = true;
	this.editObject = editObject;
    }

    @Override
    public void showView() {
	Label title = new Label(edit ? "Editar Usuário" : "Cadastrar Usuário");
	title.setHeight("25px");

	FormPanel form = new FormPanel();

	VerticalPanel rows = new VerticalPanel();

	rows.setSpacing(10);

	HorizontalPanel row1 = new HorizontalPanel();

	Label nameLabel = new Label("Nome");
	final TextBox nameField = new TextBox();

	nameLabel.setWidth("65px");

	Label passwordLabel = new Label("Senha");
	final PasswordTextBox passwordField = new PasswordTextBox();

	passwordLabel.setWidth("65px");

	row1.add(nameLabel);
	row1.add(nameField);
	row1.add(passwordLabel);
	row1.add(passwordField);

	HorizontalPanel row2 = new HorizontalPanel();

	final CheckBox enabledCheckbox = new CheckBox("Ativo?");

	Label emailLabel = new Label("E-mail");
	final TextBox emailField = new TextBox();

	emailLabel.setWidth("65px");

	row2.add(emailLabel);
	row2.add(emailField);
	row2.add(enabledCheckbox);

	HorizontalPanel buttons = new HorizontalPanel();

	final AsyncCallback<Integer> createCallback = new AsyncCallback<Integer>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Ocorreu um erro: " + caught.getMessage());

	    }

	    @Override
	    public void onSuccess(Integer result) {
		Window.alert("Usuário cadastrado com sucesso!");
		new UserListView().showView();
	    }
	};

	final AsyncCallback<Integer> updateCallback = new AsyncCallback<Integer>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Ocorreu um erro: " + caught.getMessage());

	    }

	    @Override
	    public void onSuccess(Integer result) {
		Window.alert("Usuário atualizado com sucesso!");
		new UserListView().showView();
	    }
	};

	Button cancelButton = new Button("Cancelar");
	Button saveButton = new Button("Salvar");

	cancelButton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		new UserListView().showView();
	    }
	});

	saveButton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		User user = new User(nameField.getText(), passwordField.getText(), new Date(new java.util.Date().getTime()),
		                     enabledCheckbox.getValue(), emailField.getText());
		if (edit) {
		    user.setId(editObject.getId());
		    userService.update(user, updateCallback);
		} else {
		    userService.create(user, createCallback);
		}
	    }
	});

	if (edit) {
	    nameField.setText(editObject.getName());
	    passwordField.setText(editObject.getPassword());
	    enabledCheckbox.setValue(editObject.isEnabled());
	    emailField.setText(editObject.getEmail());
	    buttons.add(cancelButton);
	}

	buttons.add(saveButton);

	rows.add(row1);
	rows.add(row2);
	rows.add(buttons);

	form.add(rows);

	ViewPort.setContentView(title, form);
    }

}
