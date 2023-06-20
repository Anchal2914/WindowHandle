package demo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
//Selenium Imports
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
///


public class WindowHandle {
    ChromeDriver driver;
    public WindowHandle()
    {
        System.out.println("Constructor: WindowHandle");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        driver.close();
        driver.quit();
    }

    
    public  void window() throws InterruptedException{
        System.out.println("Start Test case: WindowHandle");
        
        // Navigate to URL  https://www.w3schools.com/jsref/tryit.aspfilename=tryjsref_win_open
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

        // Get Parent window handle  get.windowhandle()
        String parent=driver.getWindowHandle();

        driver.switchTo().frame("iframeResult");

        // Locate Try it button Using Locator "XPath" //button[contains(text(),"Try it")]
        WebElement tryItButton = driver.findElement(By.xpath("//button[contains(text(),'Try it')]"));

        // Click Try it button Using Locator "XPath" //button[contains(text(),"Try it")].click()
        tryItButton.click();
        Thread.sleep(2000);

        // get child window handle  get.windowhandles()
        Set<String> s =driver.getWindowHandles();

        // switch to child window  switchTo().window(child)
        Iterator<String> I1= s.iterator();
        while(I1.hasNext()){
            String childWindow=I1.next();
            if(!parent.equals(childWindow)){
                driver.switchTo().window(childWindow);

                // get child URL  getCurrentUrl()
                System.out.println("Current URL is: " + driver.switchTo().window(childWindow).getCurrentUrl());

                // get Title of the child window  getTitle()
                System.out.println("Tile of the Child window is: " + driver.switchTo().window(childWindow).getTitle());

                // Take the screenshot of the child window  TakesScreenshot
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(screenshot, new File("C:\\Users\\ancha\\INTV_Sprint\\Activity10\\selenium_starter\\.png"));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                // close the child window  close()
                driver.close();
            }
        }
                
        // switch to parent window  switchTo().window(parent)
        driver.switchTo().window(parent);

        System.out.println("end Test case: WindowHandle");
    }
}


