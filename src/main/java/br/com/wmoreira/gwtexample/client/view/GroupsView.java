package br.com.wmoreira.gwtexample.client.view;

import java.util.List;

import br.com.wmoreira.gwtexample.client.presenter.GroupsPresenter;
import br.com.wmoreira.gwtexample.client.view.util.AlertDialog;
import br.com.wmoreira.gwtexample.client.view.util.ConfirmDialog;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

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

public class GroupsView extends FlowPanel implements GroupsPresenter.Display {
    private final CellTable<Group> grid;
    private final Button editButton;
    private final Button deleteButton;
    
    public GroupsView() {
	grid = buildGrid();
	editButton = new Button("Editar");
	deleteButton = new Button("Deletar");
	this.setStyleName("content");
	
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();

	Label title = new Label("Listar Grupos");

	title.setHeight("25px");
	title.setStyleName("h2");

	hPanel.setSpacing(5);


	editButton.setEnabled(false);
	deleteButton.setEnabled(false);

	hPanel.add(editButton);
	hPanel.add(deleteButton);

	grid.addDomHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		editButton.setEnabled(true);
		deleteButton.setEnabled(true);

	    }
	}, ClickEvent.getType());

	vPanel.add(title);
	vPanel.add(hPanel);

	this.add(vPanel);
	this.add(grid);
    }

    private CellTable<Group> buildGrid() {
	CellTable<Group> grid = new CellTable<>();
	grid.setHeight("50%");
	grid.setWidth("100%");

	grid.addColumn(new TextColumn<Group>() {

	    @Override
	    public String getValue(Group object) {
		return String.valueOf(object.getId());
	    }

	}, "ID");

	grid.addColumn(new TextColumn<Group>() {

	    @Override
	    public String getValue(Group object) {
		return object.getName();
	    }
	}, "Nome");

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
    public void setData(List<Group> data) {
	grid.setRowData(data);
    }

    @Override
    public int getClickedRow(ClickEvent event) {
	return grid.getKeyboardSelectedRow();
    }

    @Override
    public void alertDialog(String text) {
	new AlertDialog(text);
    }

    @Override
    public void confirmDialog(String text, ClickHandler okHandler) {
	new ConfirmDialog(text, okHandler);
    }

}
