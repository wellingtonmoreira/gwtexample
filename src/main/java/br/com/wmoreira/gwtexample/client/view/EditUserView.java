package br.com.wmoreira.gwtexample.client.view;

import br.com.wmoreira.gwtexample.client.presenter.EditUserPresenter;
import br.com.wmoreira.gwtexample.client.view.util.AlertDialog;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class EditUserView extends FlowPanel implements EditUserPresenter.Display{
    private final TextBox nameField;
    private final PasswordTextBox passwordField; 
    private final TextBox emailField;
    private final CheckBox enabledCheckbox;
    private final Button cancelButton;
    private final Button saveButton;

    public EditUserView(boolean edit) {
	Label title = new Label(edit ? "Editar Usuário" : "Cadastrar Usuário");
	title.setHeight("25px");
	title.setStyleName("h2");
	FormPanel form = new FormPanel();
	VerticalPanel rows = new VerticalPanel();
	this.setStyleName("content");

	rows.setSpacing(10);

	HorizontalPanel row1 = new HorizontalPanel();

	row1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	
	Label nameLabel = new Label("Nome");
	nameField = new TextBox();

	Label passwordLabel = new Label("Senha");
	passwordField = new PasswordTextBox();

	row1.add(nameLabel);
	row1.add(nameField);
	row1.add(passwordLabel);
	row1.add(passwordField);

	HorizontalPanel row2 = new HorizontalPanel();

	row2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	
	enabledCheckbox = new CheckBox("Ativo?");

	Label emailLabel = new Label("E-mail");
	emailField = new TextBox();

	row2.add(emailLabel);
	row2.add(emailField);
	row2.add(enabledCheckbox);

	HorizontalPanel buttons = new HorizontalPanel();

	cancelButton = new Button("Cancelar");
	saveButton = new Button("Salvar");

	if (edit) {
	    buttons.add(cancelButton);
	}

	buttons.add(saveButton);

	rows.add(row1);
	rows.add(row2);
	rows.add(buttons);

	form.add(rows);

	this.add(title);
	this.add(form);
    }

    @Override
    public ValueBoxBase<String> getNameField() {
	return nameField;
    }

    @Override
    public ValueBoxBase<String> getPasswordField() {
	return passwordField;
    }

    @Override
    public ValueBoxBase<String> getEmailField() {
	return emailField;
    }

    @Override
    public HasValue<Boolean> getEnabledCheckbox() {
	return enabledCheckbox;
    }

    @Override
    public HasClickHandlers getSaveButton() {
	return saveButton;
    }

    @Override
    public HasClickHandlers getCancelButton() {
	return cancelButton;
    }

    @Override
    public void alertDialog(String text) {
	new AlertDialog(text);
    }
}
