package baseclass;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    public WebDriver driver;

    public String launchBrowser(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barsiwaldeepesh\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        return url;

    }
}
