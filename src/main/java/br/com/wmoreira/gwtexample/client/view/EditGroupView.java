package br.com.wmoreira.gwtexample.client.view;

import br.com.wmoreira.gwtexample.client.presenter.EditGroupPresenter;
import br.com.wmoreira.gwtexample.client.view.util.AlertDialog;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class EditGroupView extends FlowPanel implements EditGroupPresenter.Display {
    
    private final Button saveButton;
    private final Button cancelButton;
    private final TextBox nameField;
    private final Label	title;

    public EditGroupView(boolean edit) {
	
	this.nameField = new TextBox();
	this.setStyleName("content");

	FormPanel form = new FormPanel();

	title = new Label();
	title.setHeight("25px");
	title.setStyleName("h2");

	VerticalPanel rows = new VerticalPanel();
	rows.setSpacing(10);

	HorizontalPanel row1 = new HorizontalPanel();
	row1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

	Label nameLabel = new Label("Nome");

	row1.add(nameLabel);
	row1.add(nameField);

	HorizontalPanel buttons = new HorizontalPanel();

	cancelButton = new Button("Cancelar");
	saveButton = new Button("Salvar");

	if (edit) {
	    buttons.add(cancelButton);
	}

	buttons.add(saveButton);

	rows.add(row1);
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
    public HasClickHandlers getSaveButton() {
	return saveButton;
    }

    @Override
    public HasClickHandlers getCancelButton() {
	return cancelButton;
    }
    
    @Override
    public void setTitle(String text) {
	title.setText(text);
    }

    @Override
    public void alertDialog(String text) {
	new AlertDialog(text);
    }
}
