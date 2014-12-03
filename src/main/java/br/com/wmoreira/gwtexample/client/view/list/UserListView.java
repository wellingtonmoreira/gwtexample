package br.com.wmoreira.gwtexample.client.view.list;

import java.util.ArrayList;
import java.util.List;

import br.com.wmoreira.gwtexample.client.service.UserService;
import br.com.wmoreira.gwtexample.client.service.UserServiceAsync;
import br.com.wmoreira.gwtexample.client.view.core.ViewPort;
import br.com.wmoreira.gwtexample.client.view.form.UserFormView;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
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

public class UserListView
    implements Viewable {

    private UserServiceAsync userService;

    public UserListView() {
	userService = GWT.create(UserService.class);
    }

    @Override
    public void showView() {

	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel panel = new HorizontalPanel();
	final List<User> list = new ArrayList<>();
	
	final CellTable<User> grid = buildGrid();

	Label title = new Label("Listar Usuários");
	title.setHeight("25px");

	panel.setSpacing(5);

	final Button edit = new Button("Editar");
	final Button delete = new Button("Deletar");

	edit.setEnabled(false);
	delete.setEnabled(false);

	edit.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		new UserFormView(list.get(grid.getKeyboardSelectedRow())).showView();
	    }
	});

	final AsyncCallback<List<User>> findAllCallback = new AsyncCallback<List<User>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
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
		Window.alert("Ocorreu um erro: " + caught.getMessage());
	    }

	    @Override
	    public void onSuccess(Integer result) {
		Window.alert("Usuário removido com sucesso!");
		edit.setEnabled(false);
		delete.setEnabled(false);
		userService.findAll(findAllCallback);
	    }
	};

	delete.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		userService.delete(list.get(grid.getKeyboardSelectedRow()).getId(), deleteCallback);
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

	ViewPort.setContentView(vPanel, grid);
    }

    private CellTable<User> buildGrid() {
	CellTable<User> grid = new CellTable<>();
	grid.setHeight("50%");
	grid.setWidth("50%");

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
