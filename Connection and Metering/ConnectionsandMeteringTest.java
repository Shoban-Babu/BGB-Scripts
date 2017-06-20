/**
 * 
 */
package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.BGBRegistration;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.RP3Smoke;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.ConnectionsandMeteringAction;
import bg.framework.app.functional.action.Slingshot.ContactUsBusinessAction;
import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 208070
 *
 */
public class ConnectionsandMeteringTest extends TestBase  {
	static String[] WR={"New electricity supply cable & meter", "Fit new meter", "Alterations to current supply", "Meter removal/disconnection", "De-energise or re-energise meter"};
		
		//TC_CM_01 -- Verify whether the "Your request ' page in Request a call back  is getting displayed on clicking the connection and metering link in mega menu
		//TC_CM_09 -- Verify the functionality of the "call me back " button of  "Your request '' page in Request a call back.  
		//TC_CM_15 -- Verify whether thank you page is displayed when details are entered in your request and 'Call me back' button is clicked.
		//TC_CM_17 -- Verify whether the customer is able to submit the request a call back in ConnectionsAndMetering and the submitted request is stored in CRM.
		//TC_CM_18 -- Verify the LHN and promo links in the thank you page.
		//TC_CM_19 -- Verify whether audit entry is made in Online DB on submission of request a call back in C&M.
		//TC_CM_47 -- Verify whether the customer is able to submit the request a call back in connections&metering and the submitted request is stored in online DB.
		//TC_CM_48 -- Verify whether audit entry is made in Online DB on submission of request a call back in C&M for collective account
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
		public void verifyRequestCallBackLogged(){
			Report.createTestLogHeader("Connections & Metering", "Verify the functionality of the call me back button of Your request page in Request a call back");
			UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
			new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.navigatetoConnectionsandMetering()
			//.validateCMPageLoggedIn("LoggedIn", userProfile, "Electricity", "New electricity supply cable & meter")
			.logout();
			//.ValidateinCRM();
			
		}
		//TC_CM_02 -- Verify whether the "Telephone number " field is prepopulated if telephone number was entered during registration /MPD in   "Your request ' page in Request a call back
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
		public void verifyTelephoneNo(){
			Report.createTestLogHeader("Connections & Metering", "Verify whether the Telephone number field is prepopulated if telephone number was entered during registration");
			UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
			new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.retrieveTelephoneNo(userProfile)
			.navigatetoConnectionsandMetering()
			.verifyTelephone(userProfile)
			.logout();
		}
		
		//TC_CM_07 -- Verify the link navigations of the Your request page in Request a call back  
		//TC_CM_10 -- Verify whether user able to view the PDF on clicking the "Download our ‘Connections & Metering information pack"  of the  "Your request" page in Request a call back.   
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
		public void verifyLinksInYourRequestPageLoggedIn(){
			Report.createTestLogHeader("Connections & Metering", "Verify the link navigations of the Your request page in Request a call back ");
			UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
			new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.navigatetoConnectionsandMetering()
			.verifyLinks()
			.logout();
			
}
		//TC_CM_12 -- Verify whether the proper error message is displayed when neither radio button selected in 'Type of connection' field - Please select Business Gas, Business Electricity or both.
		//TC CM_13 -- Verify whether the proper error message is displayed when no field is selected in Work request - Please select your work request.
		//TC CM_14 -- Verify whether the proper error message is displayed when neither radio button selected in 'Select callback time' field - Please select a time for us to call you
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
		public void verifyErrorsinYourRequestPageLoggedIn(){
			Report.createTestLogHeader("Connections & Metering", "Verify whether the proper error message is displayed");
			UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
			new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.navigatetoConnectionsandMetering()
			//.verifyAndEnterCMPage(userProfile)
			.verifyErrors(userProfile, "No Options")
			.logout();
		}
		//TC_CM_32 -- Verify whether the "Your request" page is getting displayed  on clicking continue in "Your details" page of  Request a call back.
		//TC_CM_20 -- Verify whether the "Your details" page in Request a call back  is getting displayed on clicking the connection and metering link in mega menu
		//TC_CM_39 -- Verify the functionality of the "call me back " button of  "Your request '' page in Request a call back.
		//TC_CM_46 -- Verify whether thank you page is displayed when details are entered in your request and 'Call me back' button is clicked.
		
		
		
/*
 * April Changes N Zeeshan Ahamed
 * 
 */
		
		
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
			public void verifyRequestCallBackAnonymous(){
				Report.createTestLogHeader("Connections & Metering", "Verify the functionality of the call me back button of Your request page in Request a call back for Anonymous Users");
				UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
				new HomePageAction()
				.BgbnavigateToLogin();
				new ConnectionsandMeteringAction()
				.navigatetoConnectionsandMetering()
				.validateCMPageAnonymous(userProfile, "gas", "New electricity supply cable & meter","gas","9AM");
			}
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
			public void verifyRequestCallBackLoggedIn(){
				Report.createTestLogHeader("Connections & Metering", "Verify the functionality of the call me back button of Your request page in Request a call back");
				UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
				new HomePageAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(userProfile)
				.navigatetoConnectionsandMetering()
				.validateCMPageLoggedIn("LoggedIn", userProfile, "Electricity", "New electricity supply cable & meter","1PM")
				.logout();
				
			}
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
			public void verifyRequestCallBackAnonymousHome(){
				Report.createTestLogHeader("Connections & Metering", "Verify the functionality of the call me back button of Your request page in Request a call back for Anonymous Users");
				UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
				new HomePageAction()
				.BgbnavigateToLogin();
				new ConnectionsandMeteringAction()
				.navigatetoConnectionsandMetering()
				.navigatetoHome();
				//.validateCMPageAnonymous(userProfile, "electricity", "New electricity supply cable & meter","electricity","9AM");
			}
			
			
			
			
			
	   //TC_CM_23 -- Verify the link navigations of the Your details page in Request a call back  
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
		public void verifyLinksInYourRequestPageAnonymous(){
			Report.createTestLogHeader("Connections & Metering", "Verify the link navigations of the Your details page in Request a call back");
			UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
			new HomePageAction()
			.BgbnavigateToLogin();
			new ConnectionsandMeteringAction()
			.navigatetoConnectionsandMetering()
			.verifyLinks();
		}
		//TC_CM_30 -- Verify whether the proper error message is displayed when Neither radio button selected in Are you a British Gas Business customer - Please select yes or no.
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
		public void verifyErrorsinYourDetailsPageAnonymous(){
			Report.createTestLogHeader("Connections & Metering", "Verify whether the proper error message is displayed when Neither radio button selected in Are you a British Gas Business customer - Please select yes or no.");
			UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
			new HomePageAction()
			.BgbnavigateToLogin();
			new ConnectionsandMeteringAction()
			.navigatetoConnectionsandMetering()
			.verifyErrorinCM(userProfile);
		}
		
		//TC_CM_31 -- Verify the request a call back selecting the following options in work request for first five data

		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
		public void verifyWorkRequestDataLoggedIn(){
			Report.createTestLogHeader("Connections & Metering", "Verify the request a call back selecting the following options in work request for first five data");
			UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
			new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.navigatetoConnectionsandMetering();
			for(int i=0;i<WR.length;i++){
			//	new ConnectionsandMeteringAction()
			//	.validateCMPageLoggedIn("LoggedIn", userProfile, "Electricity", WR[i]);
			}
		}
		//TC_CM_38 -- Verify the functionality of Back in your request page in Anonymous request a callback.
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
		public void verifyBackFunctionalityAnonymous(){
			Report.createTestLogHeader("Connections & Metering", "Verify the functionality of Back in your request page in Anonymous request a callback");
			UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
			new HomePageAction()
			.BgbnavigateToLogin();
			new ConnectionsandMeteringAction()
			.navigatetoConnectionsandMetering()
			.verifyAndEnterCMPage(userProfile,"9AM")
			.browserBack();
		}
		
		//TC_CM_41 -- Verify whether the proper error message is displayed when neither radio button selected in 'Type of connection' field - Please select Business Gas, Business Electricity or both.
		//TC_CM_42 -- Verify whether the proper error message is displayed when no field is selected in Work request - Please select your work request.
		//TC CM_43 -- Verify whether the proper error message is displayed when neither radio button selected in 'Select callback time' field - Please select a time for us to call you
				@SuppressWarnings("unchecked")
				@Test(groups ={Slingshot,RP3Smoke,BGBRegistration})	
				public void verifyErrorsinYourRequestPageAnonymous(){
					Report.createTestLogHeader("Connections & Metering", "Verify whether the proper error message is displayed");
					UserProfile userProfile = new TestDataHelper().getUserProfile("ConnectionsAndMetering");
					new HomePageAction()
					.BgbnavigateToLogin();
					new ConnectionsandMeteringAction()
					.navigatetoConnectionsandMetering()
					.verifyAndEnterCMPage(userProfile,"9AM")
					.verifyErrors(userProfile, "No Options");
				}	
}