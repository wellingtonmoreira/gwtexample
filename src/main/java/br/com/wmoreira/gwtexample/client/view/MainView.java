package br.com.wmoreira.gwtexample.client.view;

import br.com.wmoreira.gwtexample.client.view.list.GroupListView;
import br.com.wmoreira.gwtexample.client.view.util.ViewPort;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class MainView
    implements Viewable {

    @Override
    public void showView() {
	HorizontalPanel panel = new HorizontalPanel();
	panel.setWidth("100%");

	MenuBar menu = new MenuBar();
	menu.setAutoOpen(true);

	menu.addItem(buildUserMenu());
	menu.addItem(buildGroupMenu());

	panel.add(menu);

	ViewPort.setMenuView(panel);
    }

    private MenuItem buildUserMenu() {
	MenuItem userMenu;

	MenuBar subMenuUser = new MenuBar(true);

	MenuItem subItemCreate = new MenuItem("Criar", new Scheduler.ScheduledCommand() {

	    @Override
	    public void execute() {
		// TODO Auto-generated method stub

	    }
	});

	MenuItem subItemList = new MenuItem("Listar", new Scheduler.ScheduledCommand() {

	    @Override
	    public void execute() {
		// TODO Auto-generated method stub

	    }
	});

	subMenuUser.addItem(subItemCreate);
	subMenuUser.addItem(subItemList);

	userMenu = new MenuItem("Usuários", subMenuUser);

	return userMenu;
    }

    private MenuItem buildGroupMenu() {
	MenuItem groupMenu;

	MenuBar subMenuGroup = new MenuBar(true);

	MenuItem subItemCreate = new MenuItem("Criar", true, new ScheduledCommand() {
	    
	    @Override
	    public void execute() {
				
	    }
	});
	MenuItem subItemList = new MenuItem("Listar", true, new ScheduledCommand() {

	    @Override
	    public void execute() {
		new GroupListView().showView();
	    }
	});

	// subMenuGroup.addItem(subItemCreate);
	subMenuGroup.addItem(subItemCreate);
	subMenuGroup.addItem(subItemList);

	groupMenu = new MenuItem("Grupos", subMenuGroup);

	return groupMenu;
    }

}
