package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") //getting data provider from different class
	public void verify_loginDDT(String username, String password, String res)
	{
		
		logger.info("****** Starting TC003_LoginDDT ******");
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(username);
		lp.setPassword(password);
		lp.clickLogin();
		                                                                                                                                                                                     
		//MyAccountPage
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		/*Data is valid -  login success -test pass -logout
		   login failed
Data is invalid - login success -test fail - logout
		   login failed -test pass 
*/
		if(res.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}else
			{
				Assert.assertFalse(false);
			}
		}
		if(res.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}else
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("****** Finished TC003_LoginDDT ******");
	}
		
}












