package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {
	

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException {
		
		logger.info("****** Starting TC001_AccountRegistrationTest ******");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		logger.info("Providing Customer Details...");
		
		Thread.sleep(2000);
		regpage.setfirstName(randomAlphabetic().toUpperCase());
		regpage.setLastName(randomAlphabetic().toUpperCase());
		regpage.setEmail(randomAlphabetic()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String pwd=randomAlphanumeric();
		regpage.setPassword(pwd);
		regpage.setConfirmPassword(pwd);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		Thread.sleep(5000);
		
		logger.info("Validating expected message..");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
		Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);	
		}
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("****** Finished TC001_AccountRegistrationTest ******");
	}
	
}






