package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.*;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.RegistrationPage;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import bg.framework.app.functional.action.Slingshot.BgbRegistrationAction;
import bg.framework.app.functional.action.Slingshot.CustomerServiceAgentAction;
import bg.framework.app.functional.action.common.LoginAction;

public class CustomerServiceAgentTest extends TestBase {

	//TC_CSA_02 To verify the content and layout of customer service agent screen	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyCustomerServiceAgentScreen() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To verify the content and layout of customer service agent screen");
		new CustomerServiceAgentAction()
		.navigateToCsaRegistration()
		.verifyCsaContentScreen();
	}

	//TC_CSA_05 To validate the "Email address " field in CSA journey Register a user section.	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyEmailRegisterErrorMessage() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To validate the Email address field in CSA journey Register a user section");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		deregisterinBgbonline(userProfile); 
		//Register a user 
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToCsaRegister()
		.registerdetails(userProfile);
		//verify Lookup User functionality
		new CustomerServiceAgentAction()
		.navigateToCsaRegistration()
		.verifyRegisterEmailErrorMessage(userProfile);
	}

	//TC_CSA_06 To validate the functionality of the register button in Register a user section	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyRegisterPage() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To validate the functionality of the register button in Register a user section");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		deregisterinBgbonline(userProfile); 
		new CustomerServiceAgentAction()
		.navigateToCsaRegistration()
		.clickAndverifyRegister(userProfile);
	}	

	//TC_CSA_12(a),(b),(c),(d),(e) To validate the "Customer BP number" field in CSA journey look up user section.	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyBpNumberErrorMessage() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To validate the Customer BP number field in CSA journey look up user section");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		new CustomerServiceAgentAction()
		.navigateToCsaRegistration()
		.valdiateBpNumber(userProfile);
	}		

	//TC_CSA_14 verify whether CSA agent able to find the user by providing the valid BP contact person number or Email address and clicks on find user
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyLookUpUserInCsa() throws Exception {

		Report.createTestLogHeader("CSA", "To verify updated user details page through Look up user navigation");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		deregisterinBgbonline(userProfile);  
		//Register a user 
		new HomePageAction()
			.navigateToCsaRegistration()
			.navigateToCsaRegister()
			.registerdetails(userProfile)
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.BgbverifyAfterLogin(userProfile);
		//verify Lookup User functionality

		new CustomerServiceAgentAction()
			.navigateToCsaRegistration()
			.verifyFindUser(userProfile)
			.verifyUpdateUserDetailsPage()
			.verifyUserAccountInformation(userProfile);
	}
	
	/*TC_CSA_18 a To verify whether the available action are displayed with the following details 
	- user account status - Locked*/
	/*TC_CSA_18 b To verify whether update user details page is displayed when CSA agent selects and clicks on submit button 
	-selecting  Freeze account */
	//TC_CSA_20 To verify  and validate the functionality of the User account  status to change the  options for Active, locked(radio button) and Freeze account	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyUpdatedStatusReflects() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To verify whether CSA agent able to find the user by providing the valid BP contact person number or email address and clicks on find user");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		deregisterinBgbonline(userProfile); 
		//Register a user 
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToCsaRegister()
		.registerdetails(userProfile);
		//verify Lookup User functionality
		new CustomerServiceAgentAction()
		.navigateToCsaRegistration()
		.verifyFindUser(userProfile)
		.updateAndVerifyStatus(userProfile);
	}	

	//TC_CSA_18 c To verify whether the available action are displayed with the following details
	//-send forget password link 	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyPasswordResetLink() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To verify the Password Reset functionality");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		deregisterinBgbonline(userProfile); 
		//Register a user 
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToCsaRegister()
		.registerdetails(userProfile);
		//verify Lookup User functionality
		new CustomerServiceAgentAction()
		.navigateToCsaRegistration()
		.verifyFindUser(userProfile)
		.verifyPasswordLink(userProfile);	        
	}

	//TC_CSA_13 To validate the "Email address " field in CSA journey lookup user section.	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyEmailLookUpErrorMessage() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To validate the Email address field in CSA journey lookup user section");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");	        
		new CustomerServiceAgentAction()
		.navigateToCsaRegistration()
		.verifyLookUpEmailErrorMessage(userProfile);
	}	
	
	//TC_CSA_09 To verify whether the user receives the link and able to land on  the registration landing page through the link sent by the CSA agent	
	//TC_CSA_25 To Verify whether Activation email is sent to the customer when a CSA agent performs a registration journey.	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyRegistrationbyCsaAgentEmail() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To Verify whether activation email is sent to the customer when a CSA agent performs a registration journey");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		deregisterinBgbonline(userProfile); 
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToCsaRegister()
		.registerdetails(userProfile)
		.clickLoginlinkAfterRegistration()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile)
		.logOut();
	}
	
	//TC_CSA_18 c verify Look up user functionality through Bp number	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void verifyLookUpUserBpnumber() throws Exception {
		Report.createTestLogHeader("CSA Journey", "To verify the Look Up user functionality through Bp number");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		deregisterinBgbonline(userProfile); 
		//Register a user 
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToCsaRegister()
		.registerdetails(userProfile);
		//verify Lookup User functionality
		new CustomerServiceAgentAction()
		.navigateToCsaRegistration()
		.verifyFindUserBpNumber(userProfile);
	}	
	
	// Newly Updated -- Syed
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void RegisterInCSAUser() throws Exception {
		Report.createTestLogHeader("CSA Journey", "Register a CSA User");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		//new OnlineDBConnector().deRegisterNew(userProfile.getEmail());
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToCsaRegister()
		.registerdetailsnew(userProfile);
		//verify Lookup User functionality
		new CustomerServiceAgentAction()
		.CompleteRegistration(userProfile)
		.UpdateRegistrationdetails(userProfile)
		.ConfirmationPage();
	}	
	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void VerifyLookupUser() throws Exception {
		Report.createTestLogHeader("CSA Journey", "Register a CSA User");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToLookupUser();
		//verify Lookup User functionality
		new CustomerServiceAgentAction()
		.changeLookupsettings(userProfile)
		.UpdateLookupdetails(userProfile);
		
	}	
	
////////////////////// BGB Wave - July Release ///////////////////////////////////////////////////////
/////// Change in the De-Activate an Account and reactivate an account	
	
	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void VerifyDeactivateUser() throws Exception {
		Report.createTestLogHeader("CSA Journey", "De-Activate an Account for an Super User Account");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		new LoginAction()
        .bgbloginDetails(userProfile);
		new HomePageAction()
		.AccountSummaryPage();
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToLookupUser();
		
		//verify Lookup User functionality
		new CustomerServiceAgentAction()
		.changeLookupsettings(userProfile)
		.DeactivateAccount();
		new LoginAction()
        .bgbloginDetails(userProfile);
		new HomePageAction()
		.navigateLoginPage();
		new CustomerServiceAgentAction()
		.Verify_Table(userProfile);
		
	}	

	@Test(groups ={Slingshot,Regression,CsaAgent})	
	public void VerifyReactivateUser() throws Exception {
		Report.createTestLogHeader("CSA Journey", "De-Activate an Account for an RBP User Account");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		new LoginAction()
        .bgbloginDetails(userProfile);
		new HomePageAction()
		.AccountSummaryPage();
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToLookupUser();
		
		//verify Lookup User functionality
		new CustomerServiceAgentAction()
		.changeLookupsettings(userProfile)
		.ReactivateAccount();
		new LoginAction()
        .bgbloginDetails(userProfile);
		new HomePageAction()
		.AccountSummaryPage();
		new CustomerServiceAgentAction()
		.Verify_Table_New(userProfile);
		
	}	

	@Test(groups ={Slingshot,Regression,CsaAgent})
	public void RegisterPendingStandardUser_InCSA() throws Exception
	{
		Report.createTestLogHeader("CSA Journey", "Register pending customer");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");

		new HomePageAction()
		.clickRegistration("Header Registration Link");
		new BgbRegistrationAction()
		.navigateToAccountDetailsPage()
		.verifyAccountDetailsPage(userProfile)
		.registerForAnOnlineAccountNew(userProfile);
		
		new HomePageAction()
		.navigateToCsaRegistration()
		.navigateToCsaRegister()
		.CSApageverify(userProfile);
		
		new BgbRegistrationAction()
		
		.Activate_EmailUrl(userProfile)		
		.thankYouRegistered();
			
		new BgbRegistrationAction()
		.verifyAccountOverviewForSuperUserNew()
		.verifyAuditEntryForEmailNew(userProfile);
		//.registerAuditValidataionNew(userProfile);
	}


}