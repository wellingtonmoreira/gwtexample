package br.com.wmoreira.gwtexample.client.view;

import java.util.List;

import br.com.wmoreira.gwtexample.client.presenter.UsersPresenter;
import br.com.wmoreira.gwtexample.client.view.util.AlertDialog;
import br.com.wmoreira.gwtexample.client.view.util.ConfirmDialog;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class UsersView extends FlowPanel implements UsersPresenter.Display{
    
    private final Button editButton;
    private final Button deleteButton;
    private final CellTable<User> grid;
    
    public UsersView() {
	this.setStyleName("content");	

	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel panel = new HorizontalPanel();

	grid = buildGrid();

	Label title = new Label("Listar Usuários");
	title.setHeight("25px");
	title.setStyleName("h2");

	panel.setSpacing(5);

	editButton = new Button("Editar");
	deleteButton = new Button("Deletar");

	editButton.setEnabled(false);
	deleteButton.setEnabled(false);

	panel.add(editButton);
	panel.add(deleteButton);

	grid.addDomHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		editButton.setEnabled(true);
		deleteButton.setEnabled(true);

	    }
	}, ClickEvent.getType());

	vPanel.add(title);
	vPanel.add(panel);

	this.add(vPanel);
	this.add(grid);
    }

    private CellTable<User> buildGrid() {
	CellTable<User> grid = new CellTable<>();
	grid.setHeight("50%");
	grid.setWidth("100%");

	grid.addColumn(new TextColumn<User>() {

	    @Override
	    public String getValue(User object) {
		return String.valueOf(object.getId());
	    }

	}, "ID");

	grid.addColumn(new TextColumn<User>() {

	    @Override
	    public String getValue(User object) {
		return object.getName();
	    }
	}, "Nome");

	grid.addColumn(new TextColumn<User>() {

	    @Override
	    public String getValue(User object) {
		return object.getCreationDate().toString();
	    }
	}, "Data de Criação");

	grid.addColumn(new TextColumn<User>() {

	    @Override
	    public String getValue(User object) {
		return object.isEnabled() ? "Sim" : "Não";
	    }
	}, "Ativo?");

	grid.addColumn(new TextColumn<User>() {

	    @Override
	    public String getValue(User object) {
		return object.getEmail();
	    }
	}, "E-mail");

	return grid;
    }

    @Override
    public HasClickHandlers getEditButton() {
	return editButton;
    }

    @Override
    public HasClickHandlers getDeleteButton() {
	return deleteButton;
    }

    @Override
    public void alertDialog(String text) {
	new AlertDialog(text);
    }

    @Override
    public void confirmDialog(String text, ClickHandler okHandler) {
	new ConfirmDialog(text, okHandler);
    }

    @Override
    public void setData(List<User> data) {
	grid.setRowData(data);
    }

    @Override
    public int getClickedRow(ClickEvent event) {
	return grid.getKeyboardSelectedRow();
    }
}
