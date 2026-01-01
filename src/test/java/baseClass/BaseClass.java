package baseClass;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; //log4j
	public Properties p;
	
	@BeforeClass(groups={"Regression", "Sanity", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br1) throws IOException {
		
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		String br=br1.toLowerCase();
		if(br.equals("chrome"))
		{
		driver =new ChromeDriver(); 
		}else if(br.equals("edge"))
		{
			driver =new EdgeDriver(); 
		}else if(br.equals("firefox"))
		{
		driver =new FirefoxDriver();
		}else
		{
		 System.out.println("Invalid Browser name"); 
		}
		logger=LogManager.getLogger(this.getClass()); 
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get(p.getProperty("appurl")); //reading from properties file
	    driver.manage().window().maximize();
		
	}
	@AfterClass(groups={"Regression", "Sanity", "Master"})
	public void tearDown() {
		
		driver.quit();
	}
	

	public String randomAlphabetic(){
		String generatedAlphabetic=RandomStringUtils.randomAlphabetic(5);
		return generatedAlphabetic;

	}

	public String randomNumber(){
		String randomNum=RandomStringUtils.randomNumeric(10);
		return randomNum;
	}

	public String randomAlphanumeric(){
		String generatedAlphabetic=RandomStringUtils.randomAlphanumeric(3);
		String generatedNum=RandomStringUtils.randomNumeric(3);
		return(generatedAlphabetic+generatedNum);
	}
	
	public String captureScreen(String tname)
	{
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
		File sourcefile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\"+tname+"_" + timestamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourcefile.renameTo(targetFile);
		
		return targetFilePath;
	}
	
}




