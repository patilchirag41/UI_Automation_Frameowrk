package CommonUtilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserLaunch extends ExcelUtil{
	public static WebDriverWait wait;

	public enum DriverType {
		CHROME("chrome"), FIREFOX("firefox"), EDGE("edge"), SAFARI("safari"), OPERA("opera"),
		INTERNET_EXPLORER("internet explorer"), CHROMIUM("chromium");

		private final String driverName;

		DriverType(String driverName) {
			this.driverName = driverName;
		}

		public String getDriverName() {
			return driverName;
		}
	}

	public static  WebDriver driver;

	public static WebDriver getDriver(DriverType driverType, boolean headless) {

		switch (driverType) {
		case CHROME:
			ChromeOptions chromeOptions = new ChromeOptions();
			if (headless) {
				chromeOptions.addArguments("--headless");
			}
			chromeOptions.addArguments("--disable-popup-blocking");
			driver = new ChromeDriver(chromeOptions);
			break;

		case FIREFOX:
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (headless) {
				firefoxOptions.addArguments("--headless");
			}
			firefoxOptions.addArguments("-private");
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case EDGE:
			EdgeOptions edgeOptions = new EdgeOptions();
			if (headless) {
				edgeOptions.addArguments("--headless");
			}
			edgeOptions.addArguments("--inprivate");
			driver = new EdgeDriver(edgeOptions);
			break;

		case SAFARI:
			driver = new SafariDriver();
			break;

		case CHROMIUM:
			driver = new ChromeDriver(); // Since Chromium uses ChromeDriver
			break;

		default:
			throw new IllegalArgumentException("Invalid driver: " + driverType.getDriverName());
		}

		return driver;
	}

	public static void setBrowserToLaunch(DriverType BrowserName, boolean headless, String URL) {
		driver = getDriver(BrowserName, headless);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(CommonMethods.getProperty("config", "WaitTime"))));
		wait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(CommonMethods.getProperty("config", "WaitTime"))));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(URL);

	}
}
