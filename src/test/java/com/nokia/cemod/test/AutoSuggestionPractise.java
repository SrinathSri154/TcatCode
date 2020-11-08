package com.nokia.cemod.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoSuggestionPractise {
	
	WebDriver driver;
	WebDriver wait;
	String URL = "https://www.google.com";
	
	
	
	public void First() {
		
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\goturi\\Downloads\\Updated Code\\TCATLatestCodeChanges\\TestSuites\\conf\\chromedriver-windows-32bit.exe");
		
		driver.navigate().to(URL);
		
		
	}

	public static void main(String[] args) {

	}

}
