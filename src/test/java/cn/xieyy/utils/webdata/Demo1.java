package cn.xieyy.utils.webdata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo1 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",Demo1.class.getClassLoader().getResource("chromedriver.exe").getPath());
		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("https://www.lagou.com/zhaopin/Java/?labelWords=lable");
		clickOption(webDriver, "学历要求", "大专");
		clickOption(webDriver, "融资阶段", "不限");
		clickOption(webDriver, "公司规模", "不限");
		clickOption(webDriver, "行业领域", "不限");
	}

	private static void clickOption(WebDriver webDriver, String chosenTitle, String optionTitle) {
		WebElement chosenElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']//span[contains(text(),'"+chosenTitle+"')]"));
		WebElement optionElement = chosenElement.findElement(By.xpath("../a[contains(text(),'"+optionTitle+"')]"));
		optionElement.click();
	}
}
