package br.com.wmoreira.gwtexample.client.view;

import br.com.wmoreira.gwtexample.client.view.util.ViewPort;
import br.com.wmoreira.gwtexample.client.view.util.Viewable;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class MainView
    implements Viewable {

//    private static final String SERVER_ERROR = "An error occurred while "
//	      + "attempting to contact the server. Please check your network "
//	      + "connection and try again.";
//    
//    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
//    private final Messages messages = GWT.create(Messages.class);
//    
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

	subMenuGroup.addItem(subItemCreate);
	subMenuGroup.addItem(subItemList);
	
	groupMenu = new MenuItem("Grupos", subMenuGroup);
	
	return groupMenu;
    }

}
