package br.com.wmoreira.gwtexample.client.event;

import com.google.gwt.event.shared.EventHandler;


public interface UsersEventHandler extends EventHandler {
    void onUsers(UsersEvent event);
}
