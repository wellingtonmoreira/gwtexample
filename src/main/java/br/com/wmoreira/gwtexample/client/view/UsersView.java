package br.com.wmoreira.gwtexample.client.view;

import java.util.ArrayList;
import java.util.List;

import br.com.wmoreira.gwtexample.client.service.UserService;
import br.com.wmoreira.gwtexample.client.service.UserServiceAsync;
import br.com.wmoreira.gwtexample.client.view.util.AlertDialog;
import br.com.wmoreira.gwtexample.client.view.util.ConfirmDialog;
import br.com.wmoreira.gwtexample.client.view.util.ContentView;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

import com.google.gwt.core.client.GWT;
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

public class UsersView extends ContentView{

    private UserServiceAsync userService;

    public UsersView() {
	userService = GWT.create(UserService.class);
	this.setStyleName("content");

	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel panel = new HorizontalPanel();
	final List<User> list = new ArrayList<>();

	final CellTable<User> grid = buildGrid();

	Label title = new Label("Listar Usuários");
	title.setHeight("25px");
	title.setStyleName("h2");

	panel.setSpacing(5);

	final Button edit = new Button("Editar");
	final Button delete = new Button("Deletar");

	edit.setEnabled(false);
	delete.setEnabled(false);

	edit.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		new EditUserView(list.get(grid.getKeyboardSelectedRow())).show();
	    }
	});

	final AsyncCallback<List<User>> findAllCallback = new AsyncCallback<List<User>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		new AlertDialog(caught.getMessage()).show();
	    }

	    @Override
	    public void onSuccess(List<User> result) {
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

		userService.findAll(findAllCallback);
		new AlertDialog("Usuário removido com sucesso!").show();
		edit.setEnabled(false);
		delete.setEnabled(false);
	    }
	};

	delete.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		ClickHandler okHandler = new ClickHandler() {

		    @Override
		    public void onClick(ClickEvent event) {
			userService.delete(list.get(grid.getKeyboardSelectedRow()).getId(), deleteCallback);
		    }
		};
		new ConfirmDialog("Confirma a ação?", okHandler);
	    }
	});

	panel.add(edit);
	panel.add(delete);

	grid.addDomHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		edit.setEnabled(true);
		delete.setEnabled(true);

	    }
	}, ClickEvent.getType());

	vPanel.add(title);
	vPanel.add(panel);

	userService.findAll(findAllCallback);

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
}
