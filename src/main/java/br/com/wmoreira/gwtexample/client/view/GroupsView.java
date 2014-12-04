package br.com.wmoreira.gwtexample.client.view;

import java.util.ArrayList;
import java.util.List;

import br.com.wmoreira.gwtexample.client.service.GroupService;
import br.com.wmoreira.gwtexample.client.service.GroupServiceAsync;
import br.com.wmoreira.gwtexample.client.view.util.AlertDialog;
import br.com.wmoreira.gwtexample.client.view.util.ConfirmDialog;
import br.com.wmoreira.gwtexample.client.view.util.ContentView;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class GroupsView extends ContentView {
    private GroupServiceAsync groupService;
    final List<Group> list;
    final CellTable<Group> grid;
    final Button edit;
    final Button delete;
    
    public GroupsView() {
	groupService = GWT.create(GroupService.class);
	list = new ArrayList<Group>();
	grid = buildGrid();
	edit = new Button("Editar");
	delete = new Button("Deletar");
	this.setStyleName("content");
	
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();

	Label title = new Label("Listar Grupos");

	title.setHeight("25px");
	title.setStyleName("h2");

	hPanel.setSpacing(5);


	edit.setEnabled(false);
	delete.setEnabled(false);

	final AsyncCallback<List<Group>> findAllCallback = new AsyncCallback<List<Group>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		new AlertDialog(caught.getMessage()).show();
	    }

	    @Override
	    public void onSuccess(List<Group> result) {
		list.clear();
		list.addAll(result);
		grid.setRowData(list);
	    }
	};

	final AsyncCallback<Integer> deleteCallback = new AsyncCallback<Integer>() {

	    @Override
	    public void onFailure(Throwable caught) {
		new AlertDialog("Ocorreu um erro: " + caught.getMessage()).show();
	    }

	    @Override
	    public void onSuccess(Integer result) {

		groupService.findAll(findAllCallback);
		new AlertDialog("Grupo removido com sucesso!").show();
		edit.setEnabled(false);
		delete.setEnabled(false);
	    }
	};

	edit.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		new EditGroupView(list.get(grid.getKeyboardSelectedRow())).show();
	    }
	});

	delete.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		ClickHandler okHandler = new ClickHandler() {
		    
		    @Override
		    public void onClick(ClickEvent event) {
			groupService.delete(list.get(grid.getKeyboardSelectedRow()).getId(), deleteCallback);
		    }
		};
		new ConfirmDialog("Confirma a ação?", okHandler);
	    }
	});

	hPanel.add(edit);
	hPanel.add(delete);

	grid.addDomHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		edit.setEnabled(true);
		delete.setEnabled(true);

	    }
	}, ClickEvent.getType());

	grid.setRowData(list);

	vPanel.add(title);
	vPanel.add(hPanel);

	groupService.findAll(findAllCallback);

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

}
