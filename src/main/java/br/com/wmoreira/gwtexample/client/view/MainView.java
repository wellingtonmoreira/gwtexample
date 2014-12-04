package br.com.wmoreira.gwtexample.client.view;

import br.com.wmoreira.gwtexample.client.view.core.ViewPort;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class MainView
    implements Viewable {

    @Override
    public void show() {
	HorizontalPanel panel = new HorizontalPanel();
	panel.setWidth("100%");
	panel.setHeight("50px");

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

	MenuItem subItemCreate = new MenuItem("Cadastrar", new Scheduler.ScheduledCommand() {

	    @Override
	    public void execute() {
		new EditUserView().show();

	    }
	});

	MenuItem subItemList = new MenuItem("Listar", new Scheduler.ScheduledCommand() {

	    @Override
	    public void execute() {
		new UsersView().show();
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

	MenuItem subItemCreate = new MenuItem("Cadastrar", true, new ScheduledCommand() {

	    @Override
	    public void execute() {
		new EditGroupView().show();
	    }
	});
	MenuItem subItemList = new MenuItem("Listar", true, new ScheduledCommand() {

	    @Override
	    public void execute() {
		new GroupsView().show();
	    }
	});

	subMenuGroup.addItem(subItemCreate);
	subMenuGroup.addItem(subItemList);

	groupMenu = new MenuItem("Grupos", subMenuGroup);

	return groupMenu;
    }

}
