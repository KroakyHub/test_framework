package com.kroakyhub.testfrog.base;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BasePageObjectRepository {
	EventFiringWebDriver driver;
		
	public BasePageObjectRepository(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
}
