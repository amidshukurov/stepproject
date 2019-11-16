package step.controller;

import step.service.MainService;

public class MainController {
  private final MainService mainService;
  public MainController() {
    this.mainService = new MainService();
  }

}
