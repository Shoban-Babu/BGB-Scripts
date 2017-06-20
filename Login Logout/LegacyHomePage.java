package bg.framework.app.functional.page.common;

import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.reFactoring.RegistrationPage;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;

import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LegacyHomePage extends BasePage {
	
	private final static String FILE_NAME = "resources/common/" + ApplicationConfig.BRAND
			+ "HomePage.properties";

	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();

	private final static String BgbFILE_NAME = "resources/Slingshot/HomePage.properties";     
	private static Properties BgbpageProperties = new PropertyLoader(BgbFILE_NAME).load();

	public void navigateToLoginPage() {
		System.out.println("file location:"+pageProperties);
		if(browser.isElementVisible(pageProperties.getProperty("HomePage.Logout"))) {
			browser.clickWithLinkText(pageProperties.getProperty("HomePage.Logout"));
			browser.wait(getWaitTime());
			browser.open(ApplicationConfig.APP_BG_URL);
		}

		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("HomePage.LoginPageLink"))) {
			browser.clickWithXpath(pageProperties.getProperty("HomePage.LoginPageLink"));
			Report.updateTestLog("Login Link is Selected Successfully ", "PASS");
		} 

		browser.wait(getWaitTime());

	}
	
	public void navigateToHelpAndAdvicePage(){
		browser.wait(getWaitTime());
		browser.open(ApplicationConfig.APP_BG_URL+"/business/help-and-advice/");
	}

	
	
	public void navigateYourAccLoginPage(){
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("HomePage.YourAccount"))) {
			browser.clickWithXpath(pageProperties.getProperty("HomePage.YourAccount"));
			Report.updateTestLog("Login Link is Selected Successfully ", "PASS");
		} 

		browser.wait(getWaitTime());
	}

	public void navigateToContactUsPage() {
		browser.clickWithXpath(pageProperties.getProperty("HomePage.ContactUs"));
		browser.wait(getWaitTime());
	}

	public void navigateToGetAQuotePage() {
		browser.wait(getWaitTime());
	}

	public void navigateToOurTariffsPage() {
		System.out.println(pageProperties.getProperty("HomePage.CompareTariffs"));
		if (browser.isTextPresent(pageProperties.getProperty("HomePage.CompareTariffs"))) {
			browser.clickWithLinkText(pageProperties
					.getProperty("HomePage.CompareTariffs"));
			Report.updateTestLog("Navigate to our tariff page", "PASS");
		} else {
			Report.updateTestLog("Navigate to our tariff page", "FAIL");
		}
		browser.wait(getWaitTime());
	}

	public void navigateToProductsAndServicesPage() {
		browser.wait(getWaitTime());

		if (ApplicationConfig.BRAND.equalsIgnoreCase("fusion")) {

			if (browser.isElementVisibleWithXpath(pageProperties.getProperty("HomePage.ProductsAndServices"))) {
				browser.clickWithXpath(pageProperties.getProperty("HomePage.ProductsAndServices"));
				Report.updateTestLog("Products And Services Link is Selected Successfully ", "PASS");
			} else {
				Report.updateTestLog("Navigate to Gas and Elecrticity page", "FAIL");        
			}
		}

		else if (ApplicationConfig.BRAND.equalsIgnoreCase("bg")) {
			if (browser.isTextPresent(pageProperties.getProperty("HomePage.Energy"))) {
				browser.clickWithLinkText(pageProperties.getProperty("HomePage.Energy"));

				Report.updateTestLog("Navigate to Energy tariff page", "PASS");
			} else {

				Report.updateTestLog("Navigate to Energy tariff page", "FAIL");
			}
		}      

	}

	public void navigateTOGasAndElectricityPage() {
		if (ApplicationConfig.BRAND.equalsIgnoreCase("BG")) {
			verifyAndClickWithLinkText(pageProperties.getProperty("HomePage.Energy"), "Energy page");}
				else {
			
			verifyAndClickWithXpath(pageProperties.getProperty("HomePage.Energy"), "Energy page");}
	}


	public void navigateToGetAPricePage() {

		if (ApplicationConfig.BRAND.equalsIgnoreCase("BG")) {
			if (browser.isTextPresent(pageProperties.getProperty("HomePage.GetAPrice"))) {
				browser.clickWithLinkText(pageProperties.getProperty("HomePage.GetAPrice"));
			}
			browser.wait(getWaitTime());
		} else {
			browser.open(ApplicationConfig.APP_FUSION_URL
					+ "/GetAQuote/");
		}
	}

	public void navigateToSelfServeRegistrationPage() {
		if (ApplicationConfig.BRAND.equalsIgnoreCase("BG")) {
			verifyAndClickWithXpath(pageProperties.getProperty("HomePage.Register"),"Register Now");
					}
		else{
			verifyAndClickWithXpath(pageProperties.getProperty("HomePage.Register"),"Register Now");
					}
	}

	public void navigateToHelpandAdvicePage() {
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("HomePage.HelpAndAdviceLink"))) {
			browser.clickWithXpath(pageProperties
					.getProperty("HomePage.HelpAndAdviceLink"));
			Report.updateTestLog("Home page Help & Advice Link Click", "PASS");
			browser.wait(getWaitTime());
		}
	}

	public void navigateToBoilersAndHeating() {
		if (browser.isTextPresent(pageProperties
				.getProperty("HomePage.BoilersAndHeating"))) {
			verifyAndClickWithLinkText(
					pageProperties.getProperty("HomePage.BoilersAndHeating"),
					"Boilers & Heating");
			browser.wait(getWaitTime());
		}
	}

	public void navigateToChangeEmailAddressPage() {
		if (browser.isTextPresent(pageProperties
				.getProperty("HomePage.ChangeEmailAddress"))) {
			verifyAndClickWithLinkText(
					pageProperties.getProperty("HomePage.ChangeEmailAddress"),
					"Change Email Address");
			browser.wait(getWaitTime());
		}
	}
	public void navigateToForgottenPassword() {
		verifyAndClickWithLinkText(pageProperties.getProperty("HomePage.ForgottenPasswordLink"), "I've Forgotten My Password Link");
	}
	public void navigatetoForgotEmailPage() {
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("HomePage.ForgotEmail1"))) {
			browser.clickWithXpath(pageProperties.getProperty(("HomePage.ForgotEmail1")));
			browser.wait(getWaitTime());
		}
	}
	public void navigatetoForgotEmailViaForgotPassword() {
		browser.clickWithXpath(pageProperties.getProperty("HomePage.ForgotPasswordID"));
		browser.wait(getWaitTime());
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("HomePage.ForgotEmailID"))) {
			browser.clickWithXpath(pageProperties.getProperty("HomePage.ForgotEmailID"));
		}
	}

	public ArrayList<String> navigateToCQ5LoginPage(){
		browser.open(ApplicationConfig.APP_BG_URL+":9002/etc/centrica-tools/errormessages-maintenance.html");
		RegistrationPage registrationPage=new RegistrationPage();
		ArrayList<String> a1=registrationPage.openCQ5();
		return a1;

	}

	public void BgbnavigateToLoginPage() {
		if(browser.isElementVisible(pageProperties.getProperty("HomePage.Logout"))) {
			browser.click(pageProperties.getProperty("HomePage.Logout"));
			browser.wait(getWaitTime());
			browser.open(ApplicationConfig.APP_BG_URL);
		}
		System.out.println("login url ApplicationConfig.APP_BG_URL:"+ApplicationConfig.APP_BG_URL+"BgbpageProperties.getPropertycommon.BgbLoginLink:"+
				BgbpageProperties.getProperty("common.BgbLoginLink"));
		browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.BgbLoginLink"));
		//browser.open("https://10.224.70.50/business/your-account/login?cid=HPLoginButton");
		browser.wait(getWaitTime());

	}

	public void loginfromNewRegPage() {
		browser.wait(getWaitTime());
		browser.open(ApplicationConfig.APP_BG_URL+"/business/your-account/register");

	}

	public void openUrlVerifyLandingPage(){

		BasePage basep=new BasePage();
		getWaitTime();
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("HomePage.LoginPageLink"))) {
			Report.updateTestLog("Login Page displayed successfully", "Pass");
		} else{
			Report.updateTestLog("Login Page not displayed successfully", "Fail");
		}

	}

	public void verifyBrowserBackPage() {
		browser.browserBack();
		verifyPageTitle("Login to Your Account - British Gas");	 
	}

	public void navigateToBgbRegistrationPage() {
		browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.BgbRegistrationLink"));
		//getWaitTime();
	}

	public void navigateToCsaRegistrationPage() {
		//browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.BgbCsaLink"));		
		browser.open(BgbpageProperties.getProperty("common.BgbCsaLinknew"));
		//browser.open(BgbpageProperties.getProperty("common.BgbCsaLinkwave"));
		
	}
	public void LoginPage() {
		Report.updateTestLog("Error Message is Displayed successfully","WARN");
		
	}
	public void AccountSummaryPage() {
		Report.updateTestLog("Account Summary Page Displayed successfully","WARN");
		
	}
	
		public void BgcnavigateToLoginPage() {
			if(browser.isElementVisible(pageProperties.getProperty("HomePage.Logout"))) {
				browser.clickWithLinkText(pageProperties.getProperty("HomePage.Logout"));
				browser.wait(getWaitTime());
				browser.open(ApplicationConfig.APP_BG_URL);
			}
			browser.open(ApplicationConfig.APP_BG_URL+"corporate/login.html");
			browser.wait(getWaitTime());
		}
		 public void BgcnavigatetoCorporate() {
				if(browser.isElementVisible(pageProperties.getProperty("HomePage.Logout"))) {
					browser.clickWithLinkText(pageProperties.getProperty("HomePage.Logout"));
					browser.wait(getWaitTime());
					browser.open(ApplicationConfig.APP_BG_URL);
				}
				browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.BgcCorporateLink"));
				browser.wait(getWaitTime());
				
			}
		 public void clickRegistration(String triggerPoint){
			 browser.open(ApplicationConfig.APP_BG_URL+"/business/your-account/login");

			 
			 
				getWaitTime();   
				Report.updateTestLog("Business Home Page is loaded", "WARN");
			 
			    if(triggerPoint=="Header Registration Link"){
			          
			          verifyAndClickWithXpath(pageProperties.getProperty("HomePage.HeaderRegistrationLink"), "Header Registration Link");
			          
			          
			    }
			    else if(triggerPoint=="Register Now Button"){
			    	//verifyAndClickWithLinkText("Register now", "Register now button");
			          verifyAndClickWithXpath(pageProperties.getProperty("HomePage.RegisterNowButton"), "Register Now Button");
			    }
			    else{
			          browser.open(ApplicationConfig.APP_BG_URL+BgbpageProperties.getProperty("common.BgbRegistrationlandingPageLink"));
			    }
			    
			}
		 
		 
		 
}

