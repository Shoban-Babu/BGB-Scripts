package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.BGBRegistration;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.Smoke;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.ContactUsNewAction;
import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/*
 * Created By:Bala
 * BGB BAU December 2016 Release
 */

/*********************************************************************************Anonymous Contact Us***************************************************************************************************************************/			

public class ContactUsNewTest extends TestBase {

	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	
    public void verifyAnonymousContactus() throws Exception {
		UserProfile userProfile = new TestDataHelper().getUserProfile("ContactUs"); 
		 Report.createTestLogHeader("ContactUs", "To Verify user can perform contact us anonymously");
		  new ContactUsNewAction()
		  .navigateToContactUsPage()
		  .submitQuerry(userProfile,"Anonymous");
	}
	
	/*********************************************************************************OAM Contact us***************************************************************************************************************************/				
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	
    public void verifyLoggedInContactus() throws Exception {
		UserProfile userProfile = new TestDataHelper().getUserProfile("ContactUs"); 
		 Report.createTestLogHeader("ContactUs", "To Verify OAM user can perform contact us sucessfully");
		 new LoginAction()
			.BgbloginDetails(userProfile)
			.BgbVerifyLogin(userProfile);
		  new ContactUsNewAction()
		  .navigateToContactUsPageLogin()
		  .submitQuerry(userProfile,"OAM");
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	
    public void verifyLoggedInLandingFunctinalityInContactus() throws Exception {
		UserProfile userProfile = new TestDataHelper().getUserProfile("ContactUs"); 
		 Report.createTestLogHeader("ContactUs", "To Verify Contact Us Form OAM Landing Page");
		  new ContactUsNewAction()
		  .navigateToContactUsPage()
		  .verifyLoginLandingPageFunctionality(userProfile);
	}
	
}
