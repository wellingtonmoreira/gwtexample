package br.com.wmoreira.gwtexample.client;

import br.com.wmoreira.gwtexample.client.view.MainView;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    new MainView().showView();
  }
}
