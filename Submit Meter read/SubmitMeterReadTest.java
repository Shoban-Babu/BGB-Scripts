package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.SubmitMeterRead;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import org.testng.annotations.Test;
import bg.framework.app.functional.action.Slingshot.SubmitMeterReadAction;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import bg.framework.app.functional.test.common.TestBase;



public class SubmitMeterReadTest extends TestBase{
//TC_SMR_01_Validate whether the user is able to perform submit meter read journey by Clicking the Submit meter read on account page.
//TC_SMR_12_Verify the "Submit"  button in submit meter read page and validate its functionality	
//TC_SMR_06	Verify whether the accounts are displayed with search option if customer have more than 3 meter
//TC_SMR_25	
	//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
    public void verifySubmitMeterRead()  {
        Report.createTestLogHeader("SubmitMeterRead", "Verify submit meter read journey by through global SMR page and in SAP");
        SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourney");
        new SubmitMeterReadAction()
                .BgbnavigateToLogin()
                .BgbloginDetails(smrProfile)
                .BgbverifyAfterLogin()
                .clickSmrGlobalLink()
                .enterMeterDial(smrProfile)
                .Bgblogout()
                .BgbnavigateToLogin()
                .BgbloginDetails(smrProfile)
                .BgbverifyAfterLogin()
                .clickSmrGlobalLink()
                .verifyDataInSapIsu(smrProfile)
                .Bgblogout();
    }
//TC_SMR_05_Verify whether the customer can submit meter read for individual meter and also for all meter (Multiple).
	
	//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
    public void verifySubmitMeterReadMultipleMeter()  {
        Report.createTestLogHeader("SubmitMeterRead", "Verify whether the customer can submit meter read for all meter (Multiple)");
        SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrWith3Meters");
        new SubmitMeterReadAction()
                .BgbnavigateToLogin()
                .BgbloginDetails(smrProfile)
                .BgbverifyAfterLogin()
                .selectSubmitMeterRead(smrProfile)
                .enterMultiDialRead(smrProfile)
                .Bgblogout();                   
    }
	//TC_SMR_34: Verify whether Customer selects Submit Meter read directly from the website and identifies himself by providing Account/meter & personal details. 
	@Test(groups ={Slingshot,Regression,SubmitMeterRead})
	public void anonymousSAPGasCustomer(){						 
		 Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the anonymous SMR page for Gas customer");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
		new SubmitMeterReadAction()
		.openSMRpage("Gas")
		.verifyAnonymousSAPGasCustomer(smrProfile)		
		//.verifySapIsu(smrProfile)
		.verifyMeterReadConfirmation(smrProfile)
		.verifyThankYouSurveyPage(smrProfile);
		//.verifyAnonymousSAPGasCustomer(smrProfile);
	}
	@Test(groups ={Slingshot,Regression,SubmitMeterRead})
	public void verifyAndValidateSMRTitleFieldError(){				 
		Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the anonymous submit meter read page for Title field error message validation");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
		new SubmitMeterReadAction()
		.openSMRpage("Gas")
		.verifyAndValidateSMRTitleFieldContent(smrProfile);			
	}
	//TC_SMR_36: Verify whether if the Gas customer is not  identified within CRM, should navigate to SMR 2nd page with  site number field.
	@Test(groups ={Slingshot,Regression,SubmitMeterRead})
	public void serviceDeskCustomerGas(){
		Report.createTestLogHeader("Anonymous Submit meter read", "Verifies whether if the Gas customer is not identified within CRM navigates to SMR second page with site number");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
		new SubmitMeterReadAction()
		.openSMRpage("Gas")
		.verifyServiceDeskCustomerGas(smrProfile);		
	}
	@Test(groups ={Slingshot,Regression,SubmitMeterRead})
	public void serviceDeskCustomerElectricity(){
		Report.createTestLogHeader("Anonymous Submit meter read", "Verifies whether if the Electricity customer is not identified within CRM navigates to SMR second page with site number");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
		new SubmitMeterReadAction()
		.openSMRpage("Electricity")
		.verifyElectricityServiceDeskCustomer(smrProfile);		
}
//TC_SMR_17	verify whether While displaying previous meter read it displays the last read( can be plausible or implausible)
//TC_SMR_19	Verify whether If validation is successful Submit Meter Read call is made to SAP ISU else customer is shown a error message to correct the reading and submit again
//TC_SMR_24 Verify whether the single meter is mapped to MPRN/MPAN No is able to do SMR journey
//TC_SMR_32 Verify whether the current meter read details are displayed on the Account Overview page when the customer logins again to the account on the same day after the meter read submission.	
//TC_SMR_33	To verify whether the customer is able to amend the incorrect  meter details from the error message overlay.
	//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
	public void validateErrorUnsuccessfulMeterReads(){		
		//TC_Account overview_01: Verify the "Account Summary" Page sorted as list format when the accounts for BP is less than 5.		 
		 Report.createTestLogHeader("Submit Meter Read", "Verify error message for incorrect meter read and user able to submit");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("ErrorMeterRead");
		new SubmitMeterReadAction()
	        .BgbnavigateToLogin()
	        .BgbloginDetails(smrProfile)
	        .BgbverifyAfterLogin()
	        .clickSmrGlobalLink()
	        .unsuccessfulMeterReads(smrProfile)
	        .Bgblogout()
	        .BgbnavigateToLogin()
	        .BgbloginDetails(smrProfile)
	        .BgbverifyAfterLogin()
	        .clickSmrGlobalLink()
		    .verifyMeterReadAfterRelogin(smrProfile)
	        .Bgblogout();                  
}
//TC_SMR_26 Verify the "back"  button in submit meter read page and validate its functionality
	//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
    public void verifyBackButtonInSmrPage()  {
        Report.createTestLogHeader("SubmitMeterRead", "verify Back link in SubmitMeterRead page");
        SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourney");
        new SubmitMeterReadAction()
                .BgbnavigateToLogin()
                .BgbloginDetails(smrProfile)
                .BgbverifyAfterLogin()
                .selectSubmitMeterRead(smrProfile)
//                .globalSubmitMeterRead(smrProfile)
                .verifyBackButtonInGlobalSmrPage();
	}
//TC_SMR_22 Verify whether SMR more than 3 meter landing page is displayed for Accounts with more than 2 meters 
//TC_SMR_25 Verify whether single meter associated with MPAN/MPRN No is able to search in  SMR more than 3 meter  landing page 	
	//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
	public void verifySearchByInAccountsSmrPage(){		
		//TC_Account overview_01: Verify the "Account Summary" Page sorted as list format when the accounts for BP is less than 5.		 
		 Report.createTestLogHeader("Submit Meter Read", "Verify the SearchBy drop displayed for more than 3 meter");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSmrMoreThan3Meters");
		new SubmitMeterReadAction()
	        .BgbnavigateToLogin()
	        .BgbloginDetails(smrProfile)
	        .BgbverifyAfterLogin()
	        .selectSubmitMeterRead(smrProfile)
	        .verifySearchByInAccountSmrPage(smrProfile)
	        .enterMeterDial(smrProfile)
            .Bgblogout();
	}
//TC_SMR_08  verify that appropriate error message is displayed when the user selects "MPAN/MPRN" in search dropdown
	//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
    public void verifyErrorMessageForMprnNumber()  {
        Report.createTestLogHeader("SubmitMeterRead", "Verify that appropriate error message when the user selects invalid MPRN");
        SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourney");
        new SubmitMeterReadAction()
                .BgbnavigateToLogin()
                .BgbloginDetails(smrProfile)
                .BgbverifyAfterLogin()
                .clickSmrGlobalLink()
                .verifyErrorMsgMprnNumber();
	}
//TC_SMR_38: Verify whether If submission is unsuccessful an error message is show to customer informing him that no reading is posted to SAP
	
//TC_Thank you survey_01	Complete the Thank you survey in logged in SMR Gas thank you page and verify whether the data is updated in online DB
//TC_NPS survey_06 Click on NPS survey link on SMR page and complete the survey and verify whether the data is updated in online DB	
//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifySurveyInSubmitMeterReadPage()  {
    Report.createTestLogHeader("SubmitMeterRead", "Verify Thank you and NPS survey in submit meter read journey");
    SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("ThankYouSurvey");
    new SubmitMeterReadAction()
            .BgbnavigateToLogin()
            .BgbloginDetails(smrProfile)
            .BgbverifyAfterLogin()
            .clickSmrGlobalLink()
            .submitNpsSurvey(smrProfile)
            .enterMeterDial(smrProfile)
            .verifyThankYouSurveyPage(smrProfile)
            .Bgblogout();
}


@Test(groups ={Slingshot,Regression,SubmitMeterRead})
  public void verifyImplausibleOverlay()  {
      Report.createTestLogHeader("Anonymous Submit meter read", "Verifies that appropriate overlay message occurs when the user enters implausible meter read");
      SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
      new SubmitMeterReadAction()
		.openSMRpage("Gas")
		.enterAnonymousGasCustomerData(smrProfile)
		.verifyImplausibleReads(smrProfile);
	}
	/*@Test(groups ={Slingshot,Regression,SubmitMeterRead})
  public void verifyDBForAnonymousSMR()  {
      Report.createTestLogHeader("SubmitMeterRead", "Verifies whether details are stored in online db when customer submits through Anonymous SMR page");
      SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
      new SubmitMeterReadAction()
          .dbVerification(smrProfile);
	}*/
	@Test(groups ={Slingshot,Regression,SubmitMeterRead})
  public void verifyBackToYourAccountLink()  {
      Report.createTestLogHeader("Anonymous Submit meter read", "Verifies back to your account button navigates to respective page or not");
      SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
      new SubmitMeterReadAction()
		.openSMRpage("Gas")
		.verifyServiceDeskCustomerGas(smrProfile)
		.verifyBackToYourAccountLink();
	}

@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void firstNameErrorMsgValidation(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the anonymous submit meter read page for First name error message validation");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
	new SubmitMeterReadAction()
	.openSMRpage("Gas")
	.firstNameErrorMsgValidation(smrProfile);			
}
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void surNameErrorMsgValidation(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the anonymous submit meter read page for Sur name error message validation");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
	new SubmitMeterReadAction()
	.openSMRpage("Gas")
	.surNameErrorMsgValidation(smrProfile);			
}
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void validateAccountNumberField(){		 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the anonymous submit meter read page for Account number error message validation");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
	new SubmitMeterReadAction()
	.openSMRpage("Gas")
	.validateAccountNumberField(smrProfile);			
}
@Test(groups ={Slingshot,Regression,SubmitMeterRead})

public void validateEmailAddressField(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the anonymous submit meter read page for Email address error message validation");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
	new SubmitMeterReadAction()
	.openSMRpage("Gas")
	.validateEmailAddressField(smrProfile);			
}
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void validateMSNField(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the anonymous submit meter read page for MSN field error message validation");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
	new SubmitMeterReadAction()
	.openSMRpage("Gas")
	.validateMSNField(smrProfile);			
}

@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void validateTitleField(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the anonymous submit meter read page for MSN field error message validation");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
	new SubmitMeterReadAction()
	.openSMRpage("Gas")
	.validateMSNField(smrProfile);			
}

//TC_SMR_26 Verify the "back"  button in submit meter read page and validate its functionality
	//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
  public void verifyCancelButtonInGlobalSmrPage()  {
      Report.createTestLogHeader("SubmitMeterRead", "verify Cancel link in Global SubmitMeterRead page");
      SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourney");
      new SubmitMeterReadAction()
              .BgbnavigateToLogin()
              .BgbloginDetails(smrProfile)
              .BgbverifyAfterLogin()
              .clickSmrGlobalLink()
              .globalSubmitMeterRead(smrProfile)
              .verifyCancelButtonInGlobalSmrPage();
	}
//@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyAccountDetailsINSMR()  {
    Report.createTestLogHeader("SubmitMeterRead", "Verify Account Details in Submit meter read page");
    SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourney");
    new SubmitMeterReadAction()
            .BgbnavigateToLogin()
            .BgbloginDetails(smrProfile)
            .BgbverifyAfterLogin()
            .verifyAccountDetailsSMR(smrProfile)            
            .Bgblogout();
}	
//TC_SMR_34: Verify whether Customer selects Submit Meter read directly from the website and identifies himself by providing Account/meter & personal details. 
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyThankYouSurveyInAnonymousSMR(){						 
	 Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the anonymous SMR page for Gas customer");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser");
	new SubmitMeterReadAction()
	.openSMRpage("Gas")
	.verifyAnonymousSAPGasCustomer(smrProfile)		
	//.verifySapIsu(smrProfile)
	.verifyMeterReadConfirmation(smrProfile);
	//.verifyThankYouSurveyPage(smrProfile);
	//.verifyAnonymousSAPGasCustomer(smrProfile);


}
//************************************************BP2 Test  needs to be executed************
//*********************************************************Anonymous SMR ********************

//Anonymous Gas Customer //
//TC_SMR_Anon_001 Validate whether the user is able to proceed "Submit meter read" journey by the deep link https://10.224.70.18/business/meterread/submit-meter-read
//TC_SMR_Anon_005 Check whether the user can submit Gas read by clicking "Get started" link of Gas read panel
//TC_SMR_Anon_071 Check whether "Submit meter metering" page is getting displayed for  non SAP-ISU customer while clicking "Continue" button of "Your details page" of Gas meter read
//TC_SMR_Anon_078  Check the confirmation page is displayed on Successful submission of Gas meter read
//TC_SMR_Anon_073  Verify the link navigations of "Submit meter metering" page for non SAP-ISU customer of Gas meter read
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyAnonymousGasCustomer()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the anonymous SMR page for Gas customer");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SAPSMRGasUserforsingleMeter");
  new SubmitMeterReadAction()
      .openSMRpage("Gas")
      .verifyServiceDeskCustomerGas(smrProfile);                        
}

//Anonymous Electric Customer //
//TC_SMR_Anon_002 Verify whether the Submit meter read landing page for anounymous customer is getting displayed
//TC_SMR_Anon_006 Check whether the user can submit Elecricity read by clicking "Get started" link of Electricity read panel 
//TC_SMR_Anon_007 Validate whether Electricity meter read landing page is getting displayed with "Your details"  page by clicking "Get started" link of Electricity read panel
//TC_SMR_Anon_033 Check whether "Submit meter metering" page is getting displayed for  non SAP-ISU customer while clicking "Continue" button of "Your details page"
//TC_SMR_Anon_042 Verify the link navigations of Electricity meter read confirmation page
//TC_SMR_Anon_043 Check the confirmation Email will be triggered on Successful submission of Electricity meter read
//TC_SMR_Anon_045 Verify the link navigations of Electricity meter read confirmation Email
//TC_SMR_Anon_040 Check the confirmation page is displayed on Successful submission of Electricity meter read
//TC_SMR_Anon_086 Verify whether Submit meter read is successful for customers submitting within MR window for anonymous customers for electric customer.
//TC_SMR_Anon_087 Verify whether Submit meter read is successful for customers submitting outside MR window for anonymous customers for collective acct.
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyAnonymousElectricCustomer()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the anonymous SMR page for Electric customer");
SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRElectricityUserforsingleMeter");

new SubmitMeterReadAction()
	.openSMRpage("Electricity")
	.verifyElectricityServiceDeskCustomer(smrProfile)
	.verifyLeadTable(smrProfile); 	
}

//SAP Gas Customer
//TC_SMR_Anon_066 Check whether "Submit meter metering" page is getting displayed for SAP-ISU customer while clicking "Continue" button of "Your details page" of Gas meter read
//TC_SMR_Anon_046 Validate whether Gas meter read landing page is getting displayed with "Your details"  page by clicking "Get started" link of Gas read panel
//TC_SMR_Anon_080 Verify the link navigations of Gas meter read confirmation page
//TC_SMR_Anon_083 Verify the link navigations of Gas meter read confirmation Email
//TC_SMR_Anon_086 Verify whether Submit meter read is successful for customers submitting within MR window for anonymous customers for gas customer.
//TC_SMR_Anon_035 Verify the link navigations of "Submit meter metering" page for non SAP-ISU customer
//TC_SMR_Anon_081 Check the confirmation Email will be triggered on Successful submission of Gas meter read


////////////////////////////////////////////Zeeshan//////////////////////Anonymous Elec Customer//////////////////////////////////////////////////////////////////////////////////////////////
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifySAPElecCustomer()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the SAP SMR page for Electricity customer");
SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SAPSMRGasUserforsingleMeter");
		 new SubmitMeterReadAction()
		 .openSMRpage("Electricity")
		.verifyAnonymousSAPElecCustomersRewrite(smrProfile);
}

/////////////////////////////////////////////////////////////////Anonymous Gas Customer///////////////////////////////////////////////////////////////////////////////
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifySAPGasCustomer()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the SAP SMR page for Electricity customer");
SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SAPSMRGasUserforsingleMeter");
		 new SubmitMeterReadAction()
		 .openSMRpage("Electricity")
		.verifyAnonymousSAPGasCustomersRewrite(smrProfile);
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//SAP Electric Customer
//TC_SMR_Anon_027 Check whether "Submit meter metering" page is getting displayed for SAP-ISU customer while clicking "Continue" button of "Your details page"
//TC_SMR_Anon_031 Check whether error messaege is getting displayed when CRM customer selects the check box to receive reminder Email for SAP-ISU customer
//TC_SMR_Anon_029 Verify the link navigations of "Submit meter metering" page for SAP-ISU customer
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifySAPElectricCustomer()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the SAP SMR page for Electric customer");
SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SAPSMRElectricityUserforsingleMeter");
		new SubmitMeterReadAction()
		.openSMRpage("Electricity")
		.verifyAnonymousSAPElecCustomersRewrite(smrProfile)
		//.verifyAnonymousSAPElectricCustomers(smrProfile)
		.verifyLeadTable(smrProfile);
	
}

//verify the Gas OverLay Link's In Your details //
//TC_SMR_Anon_054 Check whether Email address overlay is getting displayed by clicking "Why we need this?" link of Email address field of "Your details"  page of Gas meter read
//TC_SMR_Anon_057 Check whether "What is an Account number?" overlay is getting displayed by clicking "Where can I find this?" link of Account number field of "Your details"  page of Gas meter read
//TC_SMR_Anon_060 Check whether "What is a MPRN?" overlay is getting displayed by clicking "Where can I find this?" link of MPRN number field  of "Your details"  page of Gas meter read
//TC_SMR_Anon_063 Check whether "Meter Serial number?" overlay is getting displayed by clicking "Where can I find this?" link of Meter serial number field  of "Your details"  page of Gas meter read
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyYourdetailsGasOverLayLink()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "verify the Gas OverLay Link's In Your details");

		new SubmitMeterReadAction()
		.openSMRpage("Gas")		
		.verifyGasWhyWeNeedThisLink()
		.verifyGasAcctnoWhereCanIfindthisLink()
		.verifyGasMeterPointWhereCanIfindthisLink()
		.verifyGasMeterIDWhereCanIfindthisLink();
}

//verify the Gas reading meter Overlay in Reading Page//
//TC_SMR_Anon_037 Check whether "What is a Site number?" overlay is getting displayed while clicking "Where can I find this?" of Site number field
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyMeterReadingGasOverLayLink()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the Your details Gas Page Overlay Links");
SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRGas");
		new SubmitMeterReadAction()
		.openSMRpage("Gas")
		.verifyGassitenoWhereCanIfindthisLink(smrProfile);
		
}

//verify the Electricity OverLay Link's In Your details  
//TC_SMR_Anon_015 Check whether Email address overlay is getting displayed by clicking "Why we need this?" link of Email address field
//TC_SMR_Anon_018 Check whether "What is an Account number?" overlay is getting displayed by clicking "Where can I find this?" link of Account number field
//TC_SMR_Anon_021 Check whether "What is an MPAN/Supply or S number?" overlay is getting displayed by clicking "Where can I find this?" link of Supply number field
//TC_SMR_Anon_024 Check whether "What is a Meter ID?" overlay is getting displayed by clicking "Where can I find this?" link of Meter ID field

@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyYourdetailsElectricityOverLayLink()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the Your details Electricity Page Overlay Links");
		new SubmitMeterReadAction()
		.openSMRpage("Electricity")		
		.verifyElectricWhyWeNeedThisLink()
		.verifyElectricAcctnoWhereCanIfindthisLink()
		.verifyElectricMeterPointWhereCanIfindthisLink()
		.verifyElectricMeterIDWhereCanIfindthisLink();
}


//verify the Electricity reading meter Overlay in Reading Page//
//TC_SMR_Anon_075  Check whether "What is a Site number?" overlay is getting displayed while clicking "Where can I find this?" of Site number field of Gas meter read
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyMeterReadingElectricityOverLayLink()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "verify the Electricity reading meter Overlay in Reading Page");
SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SAPSMRElectricityUserforsingleMeter");
		new SubmitMeterReadAction()
		.openSMRpage("Electricity")
		.verifyElectricitysitenoWhereCanIfindthisLink(smrProfile);		
		
}
//verify the Gas Your details Page Navigation Link //
//TC_SMR_Anon_048 Verify the link navigations of "Your details"  page of Gas meter read
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyGasYourdetailsPageNavigationLink()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the Your details Gas Page Navigation Links");
		new SubmitMeterReadAction()
		.openSMRpage("Gas")		
		.verifyGasNavigationLinks();
}

//verify the Electricity Your details Page Navigation Link //
//TC_SMR_Anon_009 Verify the link navigations of "Your details"  page of Electricity meter read
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyElectricityYourdetailsPageNavigationLink()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the Your details Electricity Page Navigation Links");

		new SubmitMeterReadAction()
		.openSMRpage("Electricity")		
		.verifyElectricNavigationLinks();
		
}

//verify the Submit Meter page Navigation Links //
//TC_SMR_Anon_004 Verify the link navigations of Submit meter read landing page 
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifySubmitMeterReadingLandingPageLink()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the link navigations of Submit meter read landing page");
		new SubmitMeterReadAction()
		.openSMRpage1()
		.verifySubmitMeterreadLandingPageNavigationLinks();
}

//TC_SMR_Anon_020  Validate the functionality of "Supply/S number " field and its appropriate error messages
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void mpanNameErrorMsgValidation(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the functionality of Supply field and its appropriate error messages");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SAPSMRGasUserforsingleMeter");
	new SubmitMeterReadAction()
	.openSMRpage("Electricity")
	.ValidatempanErrormsg(smrProfile);			
}

//TC_SMR_Anon_050 Validate the functionality of "Title" drop down and its appropriate error messages of "Your details"  page of Gas meter read
//TC_SMR_Anon_011 Validate the functionality of "Title" drop down and its appropriate error messages
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void TitileFieldErrorValidation(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the functionality of Title drop down and its appropriate error messages");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SAPSMRElectricityUserforsingleMeter");
	new SubmitMeterReadAction()
	.openSMRpage("Electricity")
	.verifyAndValidateSMRTitleFieldContent(smrProfile); 
}

//validate Reading value  error msgs for electricity
//TC_SMR_Anon_039 Check the confirmation page is displayed on Successful submission of Electricity meter read
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void readingElectricityErrorMsgValidation(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify the anonymous submit meter read page for  error message validation");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRUser1");
	new SubmitMeterReadAction()
	.openSMRpage("Electricity")
	.validateElectricityreadingFiledErrormsg(smrProfile);			
}

//validate Reading value  error msgs for Gas
//TC_SMR_Anon_077 Validate the functionality of Gas reading dials and its appropriate error messages for non SAP-ISU customer
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void readingGasErrorMsgValidation(){			 
	Report.createTestLogHeader("Anonymous Submit meter read", "Verify functionality of Gas reading dials and its appropriate error messages for non SAP-ISU customer");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRGas");
	new SubmitMeterReadAction()
	.openSMRpage("Gas")
	.validateGasreadingFiledErrormsg(smrProfile);			
}

//verify electric and Gas cancel button in Your details
//TC_SMR_Anon_065 Verify the cancel button of your details page
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyCancelButtonOnGasYourDetails()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the Gas meter read Your details Back button");
		new SubmitMeterReadAction()
		.openSMRpage("Gas")
		.yourdetailsCancelButtons();
}

//verify electric and Gas cancel button in Your details
//TC_SMR_Anon_026 Verify the cancel button of your details page
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyCancelButtonOnElecYourDetails()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the Elec meter read Your details Back button");
		new SubmitMeterReadAction()
		.openSMRpage("Electricity")
		.yourdetailsCancelButton();
		
}

//******************************************BP2 Test  needs to be executed**********************************//

//*********************************************Global SMR**************************************************************************//

//TC_SMR_Global_001 Vaildate whether the user is able to proceed "Submit meter read" journey by RHN navigation in in account summary page
//TC_SMR_Global_002 Verify whether the Submit meter read landing page is getting displayed with Global search fields
//TC_SMR_Global_005 Check whether the fields are present in  Submit meter read landing page 
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifySearchByGlobalSmrPage()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify whether the Submit meter read landing page is getting displayed with Global search fields");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneySingleDial");
	new SubmitMeterReadAction()	
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .SearchByAccoutnNumber(smrProfile)
  .enterGlobalMeterDials(smrProfile)
  .verifyAuditLeadTable(smrProfile);

}
//TC_SMR_Global_004 Verify the link navigations of Submit meter read landing page is getting displayed with Global search fields
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyGlobalNavigationLinks()	
{	
	Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the Your details Gas Page Navigation Links");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GloabalSMRGas");
		new SubmitMeterReadAction()
		.BgbnavigateToLogin()
	  .BgbloginDetails(smrProfile)
	  .BgbverifyAfterLogin()
	  .clickSubmitMeterReadLink()	
		.verifyGlobalNavigationLink();
}

//TC_SMR_Global_017 Check whether Single/multiple Search results are getting displayed while giving valid Account number in search field

@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifySearchMeterByAcctno()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify multiple Search results are getting displayed while giving valid Account number in search field");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMeter11");
	new SubmitMeterReadAction()
	
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .SearchByAccoutnNumber(smrProfile)
  .enterMeterDials(smrProfile)
  //.enterGlobalMeterDials(smrProfile)
	.verifyMeterReadConfirmationTitle()
	.verifyAuditLeadTable(smrProfile);
      
}
//TC_SMR_Global_016 Check whether Single Search result is getting displayed while giving valid MPRN in search field
//TC_SMR_Global_035 Verify whether Submit meter read is successful for customers submitting meter read Gas customer

@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyMeterByMeterpointreference()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify whether Single Search result is getting displayed while giving valid MPRN in search field");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRUserMultiMeters");
	new SubmitMeterReadAction()
	
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .searchByMeterpointreference(smrProfile)
  	.enterGlobalMeterDials(smrProfile)
	.verifyMeterReadConfirmationTitle()
	.verifyAuditLeadTable(smrProfile);
}
//TC_SMR_Global_018 Check whether Single/multiple Search results are getting displayed while giving valid Site postcode in search field
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyMultiDialMeterBySitepostcode()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify whether Single or multiple Search results are getting displayed while giving valid Site postcode in search field");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersitepost");
	new SubmitMeterReadAction()
	
	.BgbnavigateToLogin()
	.BgbloginDetails(smrProfile)
	.BgbverifyAfterLogin()
	.clickSubmitMeterReadLink()
	.searchBySitepostcode(smrProfile)
    .enterGlobalMeterDials(smrProfile)
    .verifyMeterReadConfirmationTitle()
	.verifyAuditLeadTable(smrProfile);
      
}
//TC_SMR_Global_015 Check whether Single Search result is getting displayed while giving valid Supply number in search field
//TC_SMR_Global_035 Verify whether Submit meter read is successful for customers submitting meter read Electricity customer
//TC_SMR_Global_036Verify whether Submit meter read is successful for customers submitting outside MR window.
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyMultiDialMetersearchBySSupplynumberGas()
{		
	 Report.createTestLogHeader("Submit Meter Read", "erify Global Meter Read For Gas Account");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersite");
	new SubmitMeterReadAction()
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .searchByAccountnumber(smrProfile)
  .enterMeterDialsGlobal(smrProfile)
  //.enterGlobalMeterDials(smrProfile)
  .verifyMeterReadConfirmationTitle()
	.verifyAuditLeadTable(smrProfile);        
}

@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyMultiDialMetersearchBySSupplynumberElectricity()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify Global Meter Read For Electricity Account");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersite");
	new SubmitMeterReadAction()
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile) 
  .BgbverifyAfterLogin() 
  .clickManageAccountAndSMRLink(smrProfile)
  .searchByAccountnumber(smrProfile)
  .enterMeterDialsGlobalelec(smrProfile)
  .verifyMeterReadConfirmationTitle()
	.verifyAuditLeadTable(smrProfile);        
}

//TC_SMR_Global_019 Check whether appropriate error message is getting displayed when Number of meters more than maximum
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void verifyMorethanMaximumMeter()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify whether appropriate error message is getting displayed when Number of meters more than maximum");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMeter11");
	new SubmitMeterReadAction()
	
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .SearchByAccoutnNumber(smrProfile)
	.VerifyNumberofmetersmorethanmaximum();
  
}

//have to execute and verify the output
//TC_SMR_Global_008  Check the functionality of "Search term" field and its appropriate error message
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void ValidateSearchTermError()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify the functionality of Search term field and its appropriate error message");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersite");
	new SubmitMeterReadAction()
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .searchByInvalidata();
          
}

//TC_SMR_Global_007 Check whether error message is getting displayed when Drop down is not selected
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void ValidateGlobalSearchField()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify whether error message is getting displayed when Drop down is not selected");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersite");
	new SubmitMeterReadAction()
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .VerifyAndValidateSMRSearchField();           
}

//TC_SMR_Global_009 Validate whether Account number overlay is getting displayed by clicking link in "Global Submit meter read" landing page
//TC_SMR_Global_011 Validate whether Bill number overlay is getting displayed by clicking link in "Global Submit meter read" landing page
//TC_SMR_Global_013 Validate whether Site postcode overlay is getting displayed by clicking link in "Global Submit meter read" landing page
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void ValidateOverlay()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify whether Account number overlay is getting displayed by clicking link in Global Submit meter read landing page");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersite");
	new SubmitMeterReadAction()
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .VerifySearchFieldOverlay();        
}

//TC_SMR_Global_030 Verify the link navigations of "Upload meter reads" page
//TC_SMR_Global_034 Check the  functionality of Submit and cancel
//TC_SMR_Global_028 Check whether "Upload meter reads" page is getting displayed when the user clicks "Upload meter reads here" link in Submit meter read landing page
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void VerifyLinkNavigationsOfUploadMeter()
{		
	 Report.createTestLogHeader("Submit Meter Read","Verify the link navigations of Upload meter reads page");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersite");
	new SubmitMeterReadAction()
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .clickUploadMeterReadLinks()
	.verifyUploadMeterPageNavigationLinks();
}


//TC_SMR_Global_023 Check whether meter read validation overlay is getting displayed for implausible meter read.
//TC_SMR_Global_025 Check whether the customer can force submit the meter read by clicking Submit button in overlay
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void ValidateImplausibleMeterRead()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify whether the customer can force submit the meter read by clicking Submit button in overlay");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersite");
	new SubmitMeterReadAction()
	
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .SearchByAccoutnNumber(smrProfile)
  .verifyImplausibleReadds(smrProfile);

 }
//TC_SMR_Global_026 Check whether the customer can change/edit the meter read by clicking Close link in that overlay
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void ValidateImplausibleMeterReadOverlayClose()
{		
	 Report.createTestLogHeader("Submit Meter Read", "Verify whether the customer can change/edit the meter read by clicking Close link in that overlay");
	SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("GlobalSMRMultiDialMetersite");
	new SubmitMeterReadAction()
	
	.BgbnavigateToLogin()
  .BgbloginDetails(smrProfile)
  .BgbverifyAfterLogin()
  .clickSubmitMeterReadLink()
  .SearchByAccoutnNumber(smrProfile)
  .verifyImplausibleReadss(smrProfile);
  
 }



//******************************************************************************sundar test**************login smr*****************************************
//*********************************************************************************************************************************************************
//TC_SMR log 001: Validate whether the user is able to proceed "Submit meter read" journey by Clicking "Submit meter read" direct link in account summary page.
//TC_SMR log 002: Verify whether the Submit meter read landing page is getting displayed with meter details for less than 3 meters
//TC_SMR log 004: Verify the link navigations of Submit meter read landing page for less than 3 meters a)LHN b)RHN
//TC_SMR log 008: Check the below mentined item's functionality for less than 3 meters a)Submit (button) b)Back (link) c)Cancel (link)
//TC_SMR log 037: Check whether confirmation page is getting displayed after successful meter read submission
//TC_SMR log 039: Verify the Link navigations of meter read confirmation page
//TC_SMR log 040: Check whether Confirmation email is triggered to customer after successful submission of meter read
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrMangaeAccountlinkSubmitMeterNavigationLink(){
     Report.createTestLogHeader("SubmitMeterRead", "Verify whether the Submit meter read landing page is getting displayed with meter details for less than 3 meters");
     SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SMRLoggedin");
     new SubmitMeterReadAction()
          .BgbnavigateToLogin()
          .BgbloginDetails(smrProfile)
          .clickManageAccountLinkWithAccNo(smrProfile)
          .submitMeterReadLink(smrProfile)
          .smrUpto3meters()                 
          .enterMultiDialReads(smrProfile)          
          .smrAuditDetailsEntry(smrProfile);
}

@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrMangaeAccountlinkSubmitMeterLinks(){
	 Report.createTestLogHeader("SubmitMeterRead", "Verify submit meter link from mangae account link in account overview page");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SMRLoggedin");
	 new SubmitMeterReadAction()
	     .BgbnavigateToLogin()
	     .BgbloginDetails(smrProfile)
	     .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .enterMultiDialReads(smrProfile)
	     .smrAuditDetailsEntry(smrProfile);
}
//TC_SMR log 005: Validate whether the address displayed for the particular meter is billing address for collective account for less than 3 meters.
//TC_SMR log 029: Validate whether the address displayed for the particular meter is billing address for collective account for more than 3 meters
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void billingCollectiveUptoAndAbove3Meters(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates whether address displayed for particular meter is billing address for collective account for less than 3 meters");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("MeterReadCollectiveAndElec");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin()
	     .BgbloginDetails(smrProfile)
	     .SubmitMeterreadManage(smrProfile)
	     .smrCollectiveBilling(smrProfile);
}
//TC_SMR log 006: Validate whether the address displayed for the particular meter is Site address for Normal account for less than 3 meters
//TC_SMR log 028: Validate whether the address displayed for the particular meter is site address for normal account for more than 3 meters
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void siteNormalUptoAndAbove3Meters(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates whether address displayed for particular meter is Site address for normal account for less than 3 meters");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("MeterReadCollectiveAndElec");
	 new SubmitMeterReadAction()
	 	.BgbnavigateToLogin()
	    .BgbloginDetails(smrProfile)
	    .clickManageAccountLinkWithAccNo(smrProfile)
	    .submitMeterReadLink(smrProfile)
	    .smrNormalSite(smrProfile);
}
//TC_SMR log 009: Verify whether the Submit meter read landing page is getting displayed with meter details for more than 3 meters
//TC_SMR log 011: Verify the link navigations of Submit meter read landing page for more than 3 meters a)LHN b)RHN c)upload document d)download template
//TC_SMR log 012: Check whether the user can download meter read spreadsheet template by clicking  "Download template" link in "Do u have lots of reads?" panel
//TC_SMR log 015: Check whether "Supply number" overlay is getting displayed for electricity meters by clicking "What's this?" link
//TC_SMR log 017: Check whether "Meter point reference number" overlay is getting displayed for gas meters by clicking "What's this?" link for more than three meters for GAS meters.

@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrAbove3MetersSearchOptions(){
	Report.createTestLogHeader("SubmitMeterRead", "Verifies smr landing page for more than 3 meters");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneyymorethan15accts");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin()
	     .BgbloginDetails(smrProfile)
	     .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .smrMoreThan3MetersSearchOptions()
	     .smrWhatsThisElectricity()
	     .smrWhatsThisGas();
	  //   .smrDownloadTemplateLink(); 
}
  
//TC_SMR log 019: Validate whether the search fields are getting displayed to search meters for more than 3 meters
//TC_SMR log 020: Check whether the user can select menu from "Search by" drop down field a)MPAN b)MPRN  c)Site Address Post code
//TC_SMR log 022: Check whether appropriate error message is getting displayed for blank "Search by" field for more than 3 meters
//TC_SMR log 023: Check whether appropriate error message is getting displayed when Number of meters more than maximum.
//TC_SMR log 026:Validate whether the search results for particular search criteria is getting displayed for more than 3 meters
//TC_SMR log 031: Check the below mentioned item's functionality  for more than 3 meters a)Submit (button) b)Back (link) c)Cancel (link)


////////////////////////////////////////////////////////////////////////////GASS Login meter Read/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrLoggedInMeterReadGas(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates whether the overlay appears for implausible meter read submission for one meter");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneyMprn1");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin() 
	     .BgbloginDetails(smrProfile)
	     .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .smrMoreThaMaximumErrorMessage()
	     .smrmeteroverlay()
	     .meterReadingDue()
	     .enterMeterDials(smrProfile);
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrLoggedInMeterReadElec(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates whether the overlay appears for implausible meter read submission for one meter");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneyMprn1");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin() 
	     .BgbloginDetails(smrProfile)
	     .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .smrMoreThaMaximumErrorMessage()
	     .smrmeteroverlay()
	     .meterReadingDue()
	     .enterMeterDialselec(smrProfile);
}




@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrGlobalMeterRead(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates whether the overlay appears for implausible meter read submission for one meter");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneyMprn1");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin() 
	     .BgbloginDetails(smrProfile)
	     .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .smrMoreThaMaximumErrorMessage()
	     .smrmeteroverlay()
	     //.smrBlankSearchClick()
	     //.verifySearchByInAccountSmrPage1(smrProfile)
	     //.smrAbove3MeterSearchAndLinks()
	     .enterMeterDials(smrProfile);
}


//TC_SMR log 032:Check whether meter read validation overlay is getting displayed for implausible meter read for Single meter
//TC_SMR log 034: Check whether the customer can force submit the meter read by clicking Submit button in overlay and meter read get raised in SAP
//TC_SMR log 034:b)Check whether force submit the meter read processes in SAP 
//TC_SMR log 034:c)verify whether the  appropriate response for  an implausible MR  is available in logs.
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrImplausibleMeterReadSubmitOverlayforSingleMeterAndSAPVerification(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates whether the overlay appears for implausible meter read submission for one meter");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("MeterReadCollectiveAndElec");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin()
	     .BgbloginDetails(smrProfile)
	      .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .verifyMultiDialImplausibleReads(smrProfile)
	     .submitButton()
	     .overlay();
}
//TC_SMR log 032: Check whether meter read validation overlay is getting displayed for implausible meter read for multiple meter
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrImplausibleMeterReadSubmitOverlayforMultipleMeter(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates whether the overlay appears for implausible meter read submission for multiple meter");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneyy");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin()
	     .BgbloginDetails(smrProfile)
	     .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .verifyMultiDialImplausibleReads(smrProfile)
	     .submitButton()
	     .overlay();
	     
}
//TC_SMR log 035: Check whether the customer can change/edit the meter read by clicking Close link in that overlay
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrImplausibleMeterReadCloseLinkInOverlay(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates the close link in implausible overlay");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneyy");
	 new SubmitMeterReadAction()
	 	.BgbnavigateToLogin()
	 	.BgbloginDetails(smrProfile)
	 	.clickManageAccountLinkWithAccNo(smrProfile)
	 	.submitMeterReadLink(smrProfile)
	 	.verifyMultiDialImplausibleReads(smrProfile)
	 	.overlayclose();
	 }

//TC_SMR log 044: Verify whether Submit meter read is successful for customers submitting within MR window for logged in customer a)Gas customer b)Electricity customer
//TC_SMR log 044: b)Check whether  the new meter read for each appears correctly on SAP  and the bill is generated in SAP.
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrConfirmationPageWithinMRGas(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates meter read submission and Email to customer");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SMRLoggedin");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin()
	     .BgbloginDetails(smrProfile)
	     .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .enterMultiDialReads(smrProfile)
	     .overlay()
	     .emailConfirmation()
	     .verifyAuditLeadTable(smrProfile);
}
//TC_SMR log 044: Verify whether Submit meter read is successful for customers submitting within MR window for logged in customer a)Gas customer b)Electricity customer
//TC_SMR log 044: b)Check whether  the new meter read for each appears correctly on SAP  and the bill is generated in SAP.
//TC_SMR log 045: Verify whether Submit meter read is successful for customers submitting within MR window for logged in customer. Collective account
//TC_SMR log 045: b)Check whether  the new meter read for each appears correctly on SAP  and the bill is generated in SAP
@Test(groups ={Slingshot,Regression,SubmitMeterRead})
public void smrConfirmationPageWithinMRCollective(){
	Report.createTestLogHeader("SubmitMeterRead", "Validates whether Submit meter read is successful for customers submitting within MR window for logged in customer");
	 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SMRLoggedin");
	 new SubmitMeterReadAction()
	 	 .BgbnavigateToLogin()
	     .BgbloginDetails(smrProfile)
	     .clickManageAccountLinkWithAccNo(smrProfile)
	     .submitMeterReadLink(smrProfile)
	     .enterMultiDialReads(smrProfile)
	     .emailConfirmation()
	     .verifyAuditLeadTable(smrProfile);
}
	//************************************************************************ SMR Changes (CR-594) ****************************************************************
    //**************************************************************************************************************************************************************

	//TS_SMR_02(E to E)  Verify whether the anonymous Elec customer is able to do SMR journey,verify BPCP and Audit details for single register
	//a)TS_SMR_09		 Verify whether onclicking the submit button,confirmation page is displayed.
	@Test(groups ={Slingshot,Regression,SubmitMeterRead})
	public void AnonymousElecSmrCR_Multipleregister_newuser(){
		Report.createTestLogHeader("Anonymous SMR", "Verify whether the anonymous Elec customer is able to do SMR journey,verify BPCP and Audit details for single register");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRElecCRdata");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("CrmDetailsforSMR");
		UserProfile userProfile = new TestDataHelper().getUserProfile("ViewuserVerifynew");		
	    new SubmitMeterReadAction()
	       .openSMRpageCRGas("Elec")
	      .verifyAnonymousSAPElecCus_CR_Multiplereg_newuser(smrProfile) ;
	     // .VerifySAPCRM_SMR(crmuserProfile,userProfile,smrProfile);
	}
	//TS_SMR_02b(E to E)  Verify whether the anonymous Elec customer is able to do SMR journey,verify BPCP and Audit details for Multiple register already enrolled user
		@Test(groups ={Slingshot,Regression,SubmitMeterRead})
		public void AnonymousElecSmrCR_Multipleregister_Enrolleduser(){
			Report.createTestLogHeader("Anonymous SMR", "Verifies the anonymous SMR page for Gas customer");
			SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRElecCRdata");								
		    new SubmitMeterReadAction()
		       .openSMRpageCRGas("Elec")
		       .verifyAnonymousSAPElecCus_CR_Multiplereg_enrolleduser(smrProfile);  
		   }
	//TS_SMR_02c(E to E)  Verify whether the anonymous Elec customer is able to do SMR journey,verify BPCP and Audit details for single register  without alert
			@Test(groups ={Slingshot,Regression,SubmitMeterRead})
			public void AnonymousElecSmrCR_Multipleregister_newuser_noalretchecked(){
				Report.createTestLogHeader("Anonymous SMR", "Verify whether the anonymous Elec customer is able to do SMR journey,verify BPCP and Audit details for single register  without alert");
				SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRElecCRdata");					
			    new SubmitMeterReadAction()
			       .openSMRpageCRGas("Elec")
			      .verifyAnonymousSAPElecCus_CR_Multiplereg_newuser_alretnonchecked(smrProfile);  
			   }
	//TS_SMR_02(E to E)  Verify whether the anonymous Elec customer is able to do SMR journey,verify BPCP and Audit details for single register  without alert via email url
	//TS_SMR_12(E to E)  Verify whether the anonymous Elec customer is able to do SMR journey,verify BPCP and Audit details for single register  without alert via email url
			@Test(groups ={Slingshot,Regression,SubmitMeterRead})
			public void AnonymousElecSmrCR_Multipleregister_newuser_emailurl(){
				Report.createTestLogHeader("Anonymous SMR", "Verifies the anonymous SMR page for Gas customer");
				SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("Anonymousnonalertbpcp");
				CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("CrmDetailsforSMR");
				UserProfile userProfile = new TestDataHelper().getUserProfile("ViewuserVerifynew");		
			    new SubmitMeterReadAction()
			       .openSMRpageforemailurl(smrProfile)
			      .verifyAnonymousSAPElecCus_CR_Multiplereg_newuser_emailurl(smrProfile);
			     // .VerifySAPCRM_SMR_noalret(smrProfile,crmuserProfile,userProfile);
			   }
	//TS_SMR_10  Verify whether Collective accounts cannot be able to submit the meter reads through anonymous journey
			@Test(groups ={Slingshot,Regression,SubmitMeterRead})
			public void AnonymousElecSmrCR_collectiveuser(){
				Report.createTestLogHeader("Anonymous SMR", "Verifies the anonymous SMR page for Gas customer");
				SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("Anonymouscollective");				
			    new SubmitMeterReadAction()
			    .openSMRpageCRGas("Elec")
			    .verifyAnonymousSAPElec_collective(smrProfile);
			}
			
	//TS_SMR_11(E to E)  Verify whether the anonymous  Gas customer is able to do SMR journey,verify BPCP and Audit details for single register	with alert	
			 @Test(groups ={Slingshot,Regression,SubmitMeterRead})
			 public void AnonymousGasSmrCR_singleregister_alreatchecked(){
				Report.createTestLogHeader("Anonymous SMR", "Verify whether the anonymous  Gas customer is able to do SMR journey");
				SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRGasCRdata");
				CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("CrmDetailsforSMR");
				UserProfile userProfile = new TestDataHelper().getUserProfile("ViewuserVerifynew");		
			    new SubmitMeterReadAction()
			      .openSMRpageCRGas("Gas")			       
			       .verifyAnonymousSAPGasCus_CR_Multiplereg_newuser_alertchecked(smrProfile)   ;
			      // .VerifySAPCRM_SMR(crmuserProfile,userProfile,smrProfile);
			 	}
	//TS_SMR_11c(E to E)  Verify whether the anonymous  Gas customer is able to do SMR journey,verify BPCP and Audit details for single register without alert	
			 @Test(groups ={Slingshot,Regression,SubmitMeterRead})
			 public void AnonymousGasSmrCR_singleregister_noalrertchecked(){
				Report.createTestLogHeader("Anonymous SMR", "Verify whether the anonymous  Gas customer is able to do SMR journey without alret");
				SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRGasCRdata");
			    new SubmitMeterReadAction()
			      .openSMRpageCRGas("Gas")			       
			       .verifyAnonymousSAPGasCus_CR_Multiplereg_newuser_alretnonchecked(smrProfile);     
			 	}
	//TS_SMR_11b(E to E)  Verify whether the anonymous Gas customer is able to do SMR journey,verify BPCP and Audit details for Multiple register already enrolled user
				@Test(groups ={Slingshot,Regression,SubmitMeterRead})
				public void AnonymousGasSmrCR_Multipleregister_Enrolleduser(){
					Report.createTestLogHeader("Anonymous SMR", "Verifies the anonymous SMR page for Gas customer");
					SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("AnonymousSMRGasCRdata");						
				    new SubmitMeterReadAction()
				       .openSMRpageCRGas("Gas")				     
				      .verifyAnonymousSAPGasCus_CR_Multiplereg_enrolleduser(smrProfile);  
				   }
				
				
				@Test(groups ={Slingshot,Regression,SubmitMeterRead})
				public void verifySAPElecCustomer_DBAuditEntry()	
				{	
					Report.createTestLogHeader("Anonymous Submit meter read", "Verifies the SAP SMR page for Electricity customer");
				SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SAPSMRGasUserforsingleMeter");
						 new SubmitMeterReadAction()
						 .openSMRpage("Electricity")
						.verifyAnonymousSAPElecCustomersRewrite(smrProfile);
				}
	
	
}
