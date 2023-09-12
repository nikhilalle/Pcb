package src.lib;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    static String fileURLPath = "";
    static{
        String projectPath = System.getProperty("user.dir");
        String fileURLPath = projectPath+"\\src\\data\\login.xlsx";
        //fileURLPath = fileURL;
    }
    private WebDriver driver;
    /**
     * initialize the browser
     * */
    @BeforeClass
    public void initBrowser() throws IOException {
        String webURL = readProperty().getProperty("URL");
        String browserName = readProperty().getProperty("BrowserName");
        switch (browserName){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "headless":
                // will do later
                break;
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("disable-infobars");
               // options.addArguments("headless");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
        }
        driver.get(webURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }
    /**
     * read property files
     * **/
    public static Properties readProperty() throws IOException {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath + "/src/config/config.properties");
        FileInputStream fin = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(fin);
        return prop;
    }
    /**
     * close project
     * **/
    @AfterClass
    public void tearDown(){
        getDriver().quit();
    }

    /**
     * Data providers
     * **/

    }
