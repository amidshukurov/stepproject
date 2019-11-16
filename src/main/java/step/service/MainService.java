package step.service;

import step.Menu;

public class MainService {
  public String menuContent() {
    return new Menu().show();
  }

}
