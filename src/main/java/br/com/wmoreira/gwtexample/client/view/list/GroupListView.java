package br.com.wmoreira.gwtexample.client.view.list;

import java.util.ArrayList;
import java.util.List;

import br.com.wmoreira.gwtexample.client.service.GroupService;
import br.com.wmoreira.gwtexample.client.service.GroupServiceAsync;
import br.com.wmoreira.gwtexample.client.view.core.ViewPort;
import br.com.wmoreira.gwtexample.client.view.form.GroupFormView;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.core.shared.GWT;
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

public class GroupListView implements Viewable {

	private GroupServiceAsync groupService;

	public GroupListView() {
		groupService = GWT.create(GroupService.class);
	}

	@Override
	public void showView() {
		
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel panel = new HorizontalPanel();
		final List<Group> list = new ArrayList<>();
		
		final CellTable<Group> grid = buildGrid();

		Label title = new Label("Listar Grupos");
		title.setHeight("25px");

		panel.setSpacing(5);

		final Button edit = new Button("Editar");
		final Button delete = new Button("Deletar");

		edit.setEnabled(false);
		delete.setEnabled(false);

		final AsyncCallback<List<Group>> findAllCallback = new AsyncCallback<List<Group>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
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
				Window.alert("Ocorreu um erro: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {

				groupService.findAll(findAllCallback);
				Window.alert("Grupo removido com sucesso!");
				edit.setEnabled(false);
				delete.setEnabled(false);
			}
		};

		edit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				new GroupFormView(list.get(grid.getKeyboardSelectedRow()))
						.showView();
			}
		});

		delete.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				groupService.delete(list.get(grid.getKeyboardSelectedRow())
						.getId(), deleteCallback);
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

		grid.setRowData(list);

		vPanel.add(title);
		vPanel.add(panel);

		groupService.findAll(findAllCallback);

		ViewPort.setContentView(vPanel, grid);
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
