package ExtentReport_Lecture;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class orangeHRMTest {
	WebDriver driver = new ChromeDriver();
	@BeforeClass
	void testOpenApp() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@Test(priority=1)
	void testLogo() throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='orangehrm-login-logo']//img[@alt='orangehrm-logo']")).isDisplayed(), true);
	}
	@Test(priority=2, dependsOnMethods = {"testLogo"})
	void testURL() {
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	@Test(priority=3, dependsOnMethods = {"testURL"})
	void testTitle() {
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}
	@AfterClass
	void tearDown() {
		driver.quit();
	}
}
