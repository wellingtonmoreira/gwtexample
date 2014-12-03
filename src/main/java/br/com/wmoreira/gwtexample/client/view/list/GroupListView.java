package br.com.wmoreira.gwtexample.client.view.list;

import java.util.ArrayList;
import java.util.List;

import br.com.wmoreira.gwtexample.client.view.core.ViewPort;
import br.com.wmoreira.gwtexample.client.view.form.GroupFormView;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class GroupListView
    implements Viewable {

    @Override
    public void showView() {
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel panel = new HorizontalPanel();
	final CellTable<Group> grid = new CellTable<>();
	final List<Group> list = new ArrayList<>();

	Label title = new Label("Listar Grupos");
	title.setHeight("25px");

	panel.setSpacing(5);

	final Button edit = new Button("Editar");
	final Button delete = new Button("Deletar");

	edit.setEnabled(false);
	delete.setEnabled(false);

	edit.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		new GroupFormView(list.get(grid.getKeyboardSelectedRow())).showView();
	    }
	});

	delete.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Window.alert("Deleting Group with ID " + list.get(grid.getKeyboardSelectedRow()).getId());
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

	list.add(new Group(1, "Bla"));
	list.add(new Group(2, "Ble"));

	grid.setRowData(list);

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {

	    @Override
	    public void execute() {}
	});

	vPanel.add(title);
	vPanel.add(panel);

	ViewPort.setContentView(vPanel, grid);
    }

}
