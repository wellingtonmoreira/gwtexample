package br.com.wmoreira.gwtexample.client.view.list;

import java.util.ArrayList;
import java.util.List;

import br.com.wmoreira.gwtexample.client.view.util.ViewPort;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class GroupListView implements Viewable {

	@Override
	public void showView() {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.setWidth("100%");
		panel.setHeight("100%");
		
		DataGrid<Group> dataGrid = new DataGrid<>(10);
		dataGrid.setHeight("100%");
		dataGrid.setWidth("100%");
		
		dataGrid.addColumn(new TextColumn<Group>() {

			@Override
			public String getValue(Group object) {
				return object.getName();
			}
		});
		
		List<Group> list = new ArrayList<>();
		
		list.add(new Group(1, "Bla"));
		list.add(new Group(2, "Ble"));
		
		dataGrid.setRowData(list);

		panel.add(dataGrid);
		
		ViewPort.setContentView(panel);
	}

}
