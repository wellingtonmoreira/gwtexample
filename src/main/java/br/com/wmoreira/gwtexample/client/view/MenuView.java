package br.com.wmoreira.gwtexample.client.view;

import br.com.wmoreira.gwtexample.client.presenter.MenuPresenter;

import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class MenuView extends MenuBar implements MenuPresenter.Display {

    private final MenuItem editGroup;
    private final MenuItem groups;
    private final MenuItem editUser;
    private final MenuItem users;

    public MenuView() {
	this.setWidth("100%");

	this.setAutoOpen(true);

	MenuItem userMenu;

	MenuBar subMenuUser = new MenuBar(true);

	editUser = new MenuItem(SafeHtmlUtils.fromString("Cadastrar"));

	users = new MenuItem(SafeHtmlUtils.fromString("Listar"));

	subMenuUser.addItem(editUser);
	subMenuUser.addItem(users);

	userMenu = new MenuItem("Usuários", subMenuUser);

	this.addItem(userMenu);

	MenuItem groupMenu;

	MenuBar subMenuGroup = new MenuBar(true);

	editGroup = new MenuItem(SafeHtmlUtils.fromString("Cadastrar"));

	groups = new MenuItem(SafeHtmlUtils.fromString("Listar"));

	subMenuGroup.addItem(editGroup);
	subMenuGroup.addItem(groups);

	groupMenu = new MenuItem("Grupos", subMenuGroup);

	this.addItem(groupMenu);
    }

    @Override
    public MenuItem getEditUser() {
	return editUser;
    }

    @Override
    public MenuItem getUsers() {
	return users;
    }

    @Override
    public MenuItem getEditGroup() {
	return editGroup;
    }

    @Override
    public MenuItem getGroups() {
	return groups;
    }
}
