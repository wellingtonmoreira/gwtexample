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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
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

    private UserServiceAsync service;

    public UserListView() {
	service = GWT.create(UserService.class);
    }

    @Override
    public void showView() {

	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel panel = new HorizontalPanel();
	final CellTable<User> grid = new CellTable<>();
	final List<User> list = new ArrayList<>();

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

	delete.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Window.alert("Deleting User with ID " + list.get(grid.getKeyboardSelectedRow()).getId());
	    }
	});

	panel.add(edit);
	panel.add(delete);

	grid.setHeight("50%");
	grid.setWidth("50%");
	grid.addDomHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		edit.setEnabled(true);
		delete.setEnabled(true);

	    }
	}, ClickEvent.getType());

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
		return object.getPassword();
	    }
	}, "Senha");

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

	//list.add(new User(1, "joaobluffs", "123456", new Date(new java.util.Date().getTime()), true, "joao@bluffs.com"));
	//list.add(new User(2, "martinfowler", "654321", new Date(new java.util.Date().getTime()), true, "martin@fowler.com"));

	grid.setRowData(list);

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {

	    @Override
	    public void execute() {}
	});

	vPanel.add(title);
	vPanel.add(panel);

	AsyncCallback<List<User>> findAllCallback = new AsyncCallback<List<User>>() {

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	    }

	    @Override
	    public void onSuccess(List<User> result) {
		list.clear();
		Window.alert("Successful call");
		list.addAll(result);

	    }
	};
	
	service.findAll(findAllCallback);

	ViewPort.setContentView(vPanel, grid);
    }
}
