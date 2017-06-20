package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.DirectDebit;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import org.testng.annotations.Test;
import bg.framework.app.functional.action.Slingshot.DirectDebitAction;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import bg.framework.app.functional.entities.DirectDebit;
import bg.framework.app.functional.entities.UserProfile;
public class DirectDebitTest extends TestBase{
	
	DirectDebit directDebit;
	
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifySetUpDirectDebit() {
		Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetDirectDebit");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.directDebitForm(directDebit)
		.verifyPaymentLink()
		.verifyBankDetailsInIsu(directDebit);
	}

	/*TC_DD_06 - verify whether customer can enable DD by filling information and submitting the form online.    
    Precondition: If Customer is eligible for DD and it is not enabled in online*/
	//@Test(groups ={Slingshot,Regression,DirectDebit})
	public void validateDirectDebitForDiffAccounts() {
		Report.createTestLogHeader("Direct Debit Journey", "verify Direct Debit with different accounts");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetDirectDebitDiffAccountTypes");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.directDebitForDiffAccounts(directDebit);
	}        
	// TC_DD_09 - Verify whether on Selecting   Continue button along with Yes option, it should display the "Download Direct Debit Mandate" link    
	//Mandatory field : BP number in userprofile
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifyDownloadDirectDebitLink() {
		Report.createTestLogHeader("Direct Debit Journey", "verify functionality of Download Direct Debit Mandate link");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("DdLinkAndSummary");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.directDebitLinkAndBackToSummary(directDebit);
	}     	    
	// TC_DD_18 - Verify already DD registered customer is navigated to "Amend Direct Debit" page when he clicks on "Direct Debit Journey" link    
	// TC_DD_20 - Verify whether on Selecting   Continue button along with No option, it should display the existing bank details with edit option.
	// TC_DD_21 - verify the edit link functionality in Amend Direct Debit page , when chosing "No" option
	// TC_DD_24 - Verify Customer can amend DD details filling information and submitting the form Online. 
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifyAmendDirectDebitPage() {
		Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendDirectDebit");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.amendDirectDebitPage(directDebit)
		.submitAndEditFieldsInAmendPage(directDebit)
		.submitDirectDebit(directDebit)
		.verifyBankDetailsInIsu(directDebit);
	}
	// TC_DD_19 - Verify whether on Selecting   Continue button along with Yes option, it should display the "Download Direct Debit Mandate" link in Amend Direct Debit page    
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifyDownloadDirectLinkInAmendPage() {
		Report.createTestLogHeader("Direct Debit Journey", "Verify functionality of DownloadDirectDebit link in Amend Direct Debit page");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AdLinkAndVerifySummary");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.dDLinkAndBackToSummaryInAmendPage(directDebit);
	}
	// TC_DD_22 - Verify Customer is not allowed to amend the DD with same bank details and proper error message is displayed.    
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifyAmendWithExistingBankDetails() {
		Report.createTestLogHeader("Direct Debit Journey", "Verify customer is not allowed to amend the DD with same bank details and proper error message is displayed");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendDirectDebitForError");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.amendDirectDebitPage(directDebit)
		.submitDirectDebitWithSameInformation();
	}
	// TC_DD_30 - verify If customer has already amended a DD in last 28 days, customer is not able to amend the DD
	// TC_DD_32 - Verify Customer transaction is shown in summary page of DD setup.
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifyAmendBefore28Days() {
		Report.createTestLogHeader("Direct Debit Journey", "verify if customer has already amended a DD in last 28 days customer is not able to amend the DD");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendData28Days");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.amendDirectDebitPage(directDebit)
		.submitAndEditFieldsInAmendPage(directDebit)
		.submitDirectDebit(directDebit)
		.accSummaryAfterBackToYourAccount(directDebit);
	}
	//TC_DD_31 Verify Customer transaction is shown in summary page of DD setup.
	//TC_DD_42 Verify whether page navigates to 'Your Account' page when 'Back to Account Summary' button is selected.
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifyDirectDebitYourAccountPage() {
		Report.createTestLogHeader("Direct Debit Journey", "Verify Customer transaction and Your Account page when Back to Account Summary button is selected");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("DdBackToAccount");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.verifyYourAccountPage(directDebit)
		.backToSelectAccountVerifyOverlay(directDebit);
	}
	// TC_DD_11 - Verify if a customer tries to enter DD details 3 times wrongly in a day, online is not allowing DD setup for next 24 hours
	// TC_DD_34,35,36	  
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifyDdBankDetails24HoursErrorMsg() {
		Report.createTestLogHeader("Direct Debit Journey", "Verify Customer is not allowed to amend the DD with same bank details and proper error message is displayed");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("DdInvalidAnd24hrsError");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.verifyDdInvalidDetails24hours(directDebit);      
	}	  
	//TC_DD_23 - Verify if a customer tries to enter Amend details 3 times wrongly in a day, online is not allowing next 24 hours  
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifyAmendBankDetails24HoursErrorMsg() {
		Report.createTestLogHeader("Direct Debit Journey", "Verify if a customer tries to enter Amend details 3 times wrongly in a day, online is not allowing next 24 hours");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendInvalidAnd24hrsError");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.verifyAmendDdInvalidDetails24hours(directDebit);      
	}
	//TC_ThankYouSurvey_03 - Complete the Thank You survery in Direct Debit page and verify whether the data is updated in online DB
	//TC_NPS survey_05 Click on NPS survey link on DD page and complete the survey and verify whether the data is updated in online DB 
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifySuveryInDirectDebitPage() {
		Report.createTestLogHeader("Direct Debit Journey", "Complete the Thank You survery in Direct Debit page and verify whether the data is updated in online DB");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("DdThankYouPage");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.directDebitsubmitNpsSurvey(directDebit) 
		.verifyThankYouSurveyPage(directDebit);
	} 
	//TC_ThankYouSurvey_04 - Complete the Thank You survey in Amend Direct Debit page and verify whether the data is updated in online DB
	//TC_NPS survey_07 Click on NPS survey link on Amend DD page and complete the survey and verify whether the data is updated in online DB
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifySuveryInAmendDirectPage() {
		Report.createTestLogHeader("Direct Debit Journey", "Complete the Thank You survery in Amend page and verify whether the data is updated in online DB");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AdThankYouPage");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.amendDirectDebitPage(directDebit)
		.submitNpsSurvey(directDebit)
		.submitAndEditFieldsInAmendPage(directDebit)
		.submitAmendDebit()
		.verifyThankYouSurveyPage(directDebit);

	} 
	
	//************************************************************************************** RP3 journey****************************************************************
	//*******************************************************************************************************************************************************************
//TC_Fixed DD _01 Verify whether customer is able to set up fixed direct debit successfully and verify the confirmation email,SAP and DB	
//TC_Fixed DD _13 Verify the functionalities of the "select "button  in the set up the Direct Debit landing page.
//TC_Fixed DD _15 Verify whether the page remains in the Set up Direct debit landing page, on clicking close/ cross symbol in the "please call us "overlay displayed
//TC_Fixed DD _25 Verify the functionality of the "continue " link  of the Fixed direct debit landing page when "Yes " is selected
//TC_Fixed DD _32 Verify whether summary page is displayed on clicking  "continue" in set up fixed direct debit landing page
//TC_Fixed DD _45 Verify the functionality of Back to your account link.	
//TC_Fixed DD _48 Verify whether the "Thank you email (Fixed)" is getting triggered for the customers once fixed direct debit is set up.	
	
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifySetUpFixedDirectDebit() {
		Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetupDirectDebitdata1");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.Setup_FixeddirectDebitForm(directDebit);
		//.verifyBankDetailsInIsu(directDebit);
	}
//TC_Fixed DD_03(E to E)  Verify whether customer is able to set up variable direct debit successfully  and verify the confirmation email,SAP and DB
//TC_Fixed DD_60 Verify the below links navigations of the variable direct debit landing page when "Yes " is selected
//TC_Fixed DD_61 Verify whether summary page is displayed on clicking continue in set up  variable direct debit landing page	
//TC_Fixed_DD_62 Verify the values are displayed in summary page as per the details entered in set up variable direct debit landing page.
//TC_Fixed_DD_64 Verify whether Direct Debit Guarantee is displayed on clicking the link in summary page.
//TC_Fixed_DD_65 Verify whether the page remains in the Summary page, on clicking close/ cross symbol in the "Direct Debit Guarantee" overlay displayed 	
//TC_Fixed DD_66 Verify the link navigations, 1. Set up Direct Debit
//TC_Fixed DD_69 Verify whether the link navigations of the confirmation page is displayed once variable direct debit is set up.
//TC_Fixed_DD_70 Verify the functionality of Back to your account link.
//TC_Fixed_DD_71 Verify the pod link navigations in confirmation screen once variable direct debit is set up.
//TC_Fixed DD _159(E to E) Verify whether elec  customer is able to set up variable direct debit successfully		
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifySetupVariableDirectDebit() {
		Report.createTestLogHeader("Variable Direct Debit Journey", "Verify whether gas customer is able to set up variable direct debit successfully ");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetupDirectDebitdata1");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		//.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.verifySetupVariableDirectdebitPage(directDebit);
		//.verifyBankDetailsInIsu(directDebit);
	}
//TC_Fixed DD _02 Verify whether customer is able to amend fixed direct debit successfully and verify  the confirmation email,SAP and DB
//TC_Fixed DD _97 Verify the values are displayed in summary page as per the details entered in Amend fixed direct debit landing page.	
//TC_Fixed DD _102 Verify whether the page remains in the Summary page, on clicking close/ cross symbol in the "Direct Debit Guarantee" overlay displayed 	
		 @Test(groups ={Slingshot,Regression,DirectDebit})		
		public void verifyAmendFixedDirectDebitTest() {
			Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendFixedDirectDebit");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.amendDirectDebitPage(directDebit)
			.submitAndEditFieldsInAmendPage(directDebit)
			.submitAmendFixedDirectDebit(directDebit);
		//	.verifyBankDetailsInIsu(directDebit);
		}
		 
//TC_Fixed DD _04 Verify whether customer is able to amend variable direct debit successfully and verify the confirmation email,SAP and DB
//TC_Fixed DD _160(E to E) Verify whether elec customer is able to amend variable direct debit successfully
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void verifyAmendVariableDirectDebitPage() {
			Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendvariableDirectDebit");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.verifyVariableamendDirectDebitPage(directDebit)
			.submitAndEditFieldsInAmendPage(directDebit)
			.submitAmendVariableDirectDebit(directDebit);
			//.verifyBankDetailsInIsu(directDebit);
		}		

//TC_Fixed DD _19 Verify the functionality of ‘Download Direct Debit Mandate.’ link  in the Set up a  Fixed direct debit landing page when "No " is selected
//TC_Fixed DD _20 Verify the functionality of "Back to Account Summary " button when "No " is selected		
//TC_Fixed DD _25(a) Verify the below links navigations of the Fixed direct debit landing page when "Yes " is selected  1.Back 2.cancel
//TC_Fixed DD _39(a) Verify the link navigations,		 1. Back		 2. Cancel		
	 
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void verifySetupfixedDirectDebitPagebackandcancel() {
			Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetupDirectDebitdata1");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)			
			.clickDirectDebitLink()
			.clickSetupFixedDirectDebitLink()
			.verifyFDBLinkAndBackToSummary()
			.clickDirectDebitLink()
			.SetupFixeddirectdebitlink(directDebit);
		}
//TC_Fixed DD _60(a) Verify the below links navigations of the variable direct debit landing page when "Yes " is selected 		1.Back		2.cancel
//TC_Fixed DD _66(a) Verify the link navigations,		1. Back		2. Cancel	
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void verifySetupVariableDirectDebitPagebackandcancel() {
			Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetupDirectDebitdata2");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.ClickSetupVariableDirectdebit(directDebit)
			.verifyFDBLinkAndBackToSummary()
			.SetupVariabledirectdebitlink(directDebit);
		}
//TC_Fixed DD _80 Verify whether the page remains in the account Summary page, on clicking close/ cross symbol in the "Direct Debit Guarantee" overlay displayed 	
//TC_Fixed DD _81 Verify whether  the "Amend Fixed Direct Debit " is getting displayed on clicking select in "Amend  DD " landing page  
//TC_Fixed DD _84 Verify the functionality of ‘Download Direct Debit Mandate.’ link  in the Amend  Fixed direct debit landing page when "No " is selected 
//TC_Fixed DD _85 Verify the functionality of "Back to Account Summary " button when "No " is selected 
//TC_Fixed DD _90 Verify the below links navigations of the  amend Fixed direct debit landing page when "Yes " is selected 1.continue  2.Back	3.cancel  		
//TC_Fixed DD _103 Verify the link navigations, 1.Amend  Direct Debit  2.Back	3.cancel
//TC_Fixed DD _105 Verify whether the 'Edit these details' links is navigating to Amend  fixed direct debit landing page.
//TC_Fixed DD _111 Verify the functionality of Back to your account link.
//TC_Fixed DD _112 Verify the pod link navigations in confirmation screen once fixed direct debit is amended
//TC_Fixed DD _121 Verify the functionality of ‘Download Direct Debit Mandate.’ link  in the Amend variable direct debit landing page when "No " is selected
//TC_Fixed DD _122 Verify the functionality of "Back to Account Summary " button when "No " is selected		
//TC_Fixed DD _132 Verify the values are displayed in summary page as per the details entered in Amend fixed direct debit landing page.
//TC_Fixed DD _135 Verify whether Direct Debit Guarantee is displayed on clicking the link in summary page.
//TC_Fixed DD _137 Verify whether the page remains in the Summary page, on clicking close/ cross symbol in the "Direct Debit Guarantee" overlay displayed 
//TC_Fixed DD _138 Verify the link navigations,	1. Amend  Direct Debit		2. Back		3. Cancel
//TC_Fixed DD _140 Verify whether the 'Edit these details' links is navigating to Amend  fixed direct debit landing page.
//TC_Fixed DD _143 Verify whether confirmation page is getting displayed on clicking 'Amend  direct debit' in summary page.
//TC_Fixed DD _146 Verify the functionality of Back to your account link.
//TC_Fixed DD _147 Verify the pod link navigations in confirmation screen once fixed direct debit is amended
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void verifyAmendFixedDirectDebitPagebackancancel() {
			Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendFixedDirectDebit");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.clickDirectDebitLink()
			.verifyFDBLinkAndBackToSummary()
			.AmendVariabledirectdebitlink(directDebit);
		}
//TC_Fixed DD _111 Verify the functionality of Back to your account link.
//TC_Fixed DD _112 Verify the pod link navigations in confirmation screen once fixed direct debit is amended
//TC_Fixed DD _121 Verify the functionality of ‘Download Direct Debit Mandate.’ link  in the Amend variable direct debit landing page when "No " is selected
//TC_Fixed DD _122 Verify the functionality of "Back to Account Summary " button when "No " is selected
//TC_Fixed DD _127 Verify the below links navigations of the variable direct debit landing page when "Yes " is selected	1.continue
//TC_Fixed DD _128 Verify the below links navigations of the variable direct debit landing page when "Yes " is selected 1.Back 2.cancel  		
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void verifyAmendVariableDirectDebitPagebackandcancel() {
			Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendvariableDirectDebit");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.AmendVariableDirectdebitpodlink()
			.verifyFDBLinkAndBackToSummary()
			.AmendVariabledirectdebitlink(directDebit);
		}		
//TC_Fixed DD _28 Verify whether the proper error message is displayed when User attempts to amend DD bank account info x 3 incorrectly. 24 hour freeze. - Please try again online in 24 hours or call us on 0800 316 2010.		
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void ValidateAmendvariableDirectDebitErrorMessage() {
			Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendvariableDirectDebit");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.ValidateAmendvariablesetup(directDebit);		
		}
//TC_Fixed DD _28 Verify whether the proper error message is displayed when User attempts to amend DD bank account info x 3 incorrectly. 24 hour freeze. - Please try again online in 24 hours or call us on 0800 316 2010.		
			@Test(groups ={Slingshot,Regression,DirectDebit})
			public void ValidateAmendFixedDirectDebitErrorMessage() {
				Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
				DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetDirectDebit");
				new DirectDebitAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(directDebit)
				.BgbverifyAfterLogin(directDebit)
				.selectAccount(directDebit)
				.ValidateAmendfixedsetup(directDebit);		
			}
//TC_Fixed DD _28 Verify whether the proper error message is displayed when User attempts to amend DD bank account info x 3 incorrectly. 24 hour freeze. - Please try again online in 24 hours or call us on 0800 316 2010.		
			@Test(groups ={Slingshot,Regression,DirectDebit})
			public void ValidateSetupvariableDirectDebitErrorMessage() {
				Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
				DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendvariableDirectDebit");
				new DirectDebitAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(directDebit)
				.BgbverifyAfterLogin(directDebit)
				.selectAccount(directDebit)
				.validateSetupvariableDirectdebit(directDebit);		
			}
//TC_Fixed DD _28 Verify whether the proper error message is displayed when User attempts to amend DD bank account info x 3 incorrectly. 24 hour freeze. - Please try again online in 24 hours or call us on 0800 316 2010.		
			@Test(groups ={Slingshot,Regression,DirectDebit})
			public void ValidateSetupFixedDirectDebitErrorMessage() {
				Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
				DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetupDirectDebitdata");
				new DirectDebitAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(directDebit)
				.BgbverifyAfterLogin(directDebit)
				.selectAccount(directDebit)
				.validateSetupFixedDirectdebit(directDebit);	
			}				
		
//TC_Fixed DD _15 Verify whether the page remains in the Set up Direct debit landing page, on clicking close/ cross symbol in the "please call us "overlay displayed 	
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void VerifyFixedDirectDebitcallusOverlay() {
			Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetupDirectDebitdata");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)			
			.clickDirectDebitLink()
			.clickSetupFixedDirectDebitLink()
			.PleasecallusOverlay();			
		}		
//TC_Fixed DD _25(a),TC_Fixed DD _39(a) Verify the below links navigations of the Fixed direct debit landing page when "Yes " is selected back and cancel  	
			public void verifySetupFixedDirectDebitbackandcancel() {
				Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
				DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendDirectDebit");
				 UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVlessthanfifteenaccts");
				new DirectDebitAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(directDebit)
				.BgbverifyAfterLogin(directDebit)
				.ManageAccount(userProfile)						
				.SetupFixedDirectDebitPagebackandcancel(directDebit); 
				}
			
//TC_Fixed DD _19 Verify the functionality of ‘Download Direct Debit Mandate.’ link  in the Set up a  Fixed direct debit landing page when "No " is selected
//TC_Fixed DD _20 Verify the functionality of "Back to Account Summary " button when "No " is selected	
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifySetUpFixedDirectDebitAndbactosummary() {
		Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetDirectDebit");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.verifyFDBLinkAndBackToSummary();		
	}
//TC_Fixed DD _55 Verify the functionality of ‘Download Direct Debit Mandate.’ link  in the Set up a  variable direct debit landing page when "No " is selected
//TC_Fixed DD _56 Verify the functionality of "Back to Account Summary " button when "No " is selected	
//	
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void verifySetUpVariableDirectDebitAndbactosummary() {
			Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetupDirectDebitdata1");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.verifyVDBLinkAndBackToSummary(directDebit);		
	}
	
	//TC_Fixed DD _81 Verify whether  the "Amend Fixed Direct Debit " is getting displayed on clicking select in "Amend  DD " landing page 
	//TC_Fixed DD _84 Verify the functionality of ‘Download Direct Debit Mandate.’ link  in the Amend  Fixed direct debit landing page when "No " is selected
	//TC_Fixed DD _85 Verify the functionality of "Back to Account Summary " button when "No " is selected		
	@Test(groups ={Slingshot,Regression,DirectDebit})
			public void verifyFixedAmendDirectDebitPagebacktosummary() {
				Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
				DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendvariableDirectDebit");
				// UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVlessthanfifteenaccts"); 
				new DirectDebitAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(directDebit)
				.BgbverifyAfterLogin(directDebit)
				.selectAccount(directDebit)
				.AmendDirectDebit()		
				.DirectDebitLinkAndBackToSummaryInAmendPage(directDebit)
				.AmendDirectDebit()	;					
	}
	//TC_Fixed DD _97  Verify the values are displayed in summary page as per the details entered in Amend fixed direct debit landing page.
	//TC_Fixed DD _100 Verify whether Direct Debit Guarantee is displayed on clicking the link in summary page.
	//TC_Fixed DD _102 Verify whether the page remains in the Summary page, on clicking close/ cross symbol in the "Direct Debit Guarantee" overlay displayed 
	//TC_Fixed DD _111 Verify the functionality of Back to your account link.		
	//TC_Fixed DD _112 Verify the pod link navigations in confirmation screen once fixed direct debit is amended				
			@Test(groups ={Slingshot,Regression,DirectDebit})
			public void verifyPerpopulatedFixedAmendDirectDebitPage() {
				Report.createTestLogHeader("Direct Debit Journey", "Verify already DD registered customer is navigated to Amend Direct Debit page when he clicks on Direct Debit link");
				DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("AmendvariableDirectDebit");				
				new DirectDebitAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(directDebit)
				.BgbverifyAfterLogin(directDebit)
				.selectAccount(directDebit)
				.Prepopulated_AmenddirectDebitForm(directDebit);
					}	
			
//TC_Fixed DD _05 Verify whether the thank you survey is updated in Online DB for the following journeys,
//TC_Fixed DD _06 Verify whether NPS survey link is displayed in all pages of following scenarios,
	
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifySetupVariableDirectDebitSurvey() {
		Report.createTestLogHeader("Variable Direct Debit Journey", "Verify whether gas customer is able to set up variable direct debit successfully ");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("DdThankYouPage");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)		
		.selectAccount(directDebit)
		.verifySetupVariableDirectdebitPageSurvey(directDebit)
		.verifyThankYouSurveyPage(directDebit);
	}
			
//TC_Fixed DD _12 Verify whether collective contract account,  customers are not able to set up the Direct Debit 	
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void validatecollectiveAcctcustomer() {
			Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetDirectDebit");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.collectiveAcctOverlay(directDebit);			
		}	

//TC_Fixed DD _33 Verify the values are displayed in summary page as per the details entered in set up fixed direct debit landing page.
//TC_Fixed DD _36 Verify whether Direct Debit Guarantee is displayed on clicking the link in summary page.
//TC_Fixed DD _38 Verify whether the page remains in the Summary page, on clicking close/ cross symbol in the "Direct Debit Guarantee" overlay displayed
//TC_Fixed DD _41 Verify whether the 'Edit these details' links is navigating to Set up fixed direct debit landing page.
//TC_Fixed DD _42 Verify whether confirmation page is getting displayed on clicking 'Set up direct debit' in summary page.		
	@Test(groups ={Slingshot,Regression,DirectDebit})
	public void verifySetUpFixedDirectDebitConfirmationPage() {
		Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
		DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetDirectDebit");
		new DirectDebitAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(directDebit)
		.BgbverifyAfterLogin(directDebit)
		.selectAccount(directDebit)
		.Prepopulated_directDebitForm(directDebit)
		.verifyPaymentLink()
		.verifyBankDetailsInIsu(directDebit);
	}
	
//TC_Fixed DD _39 Verify the link navigations of the DD
	
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void verifySetUpFixedDirectDebitLinkNavigation() {
			Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetDirectDebit");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.directDebitForm(directDebit)
			.verifyPaymentLink()
			.verifyBankDetailsInIsu(directDebit);
		}
//TC_Fixed DD _62 Verify the values are displayed in summary page as per the details entered in set up variable direct debit landing page.
//TC_Fixed DD _127 Verify the below links navigations of the variable direct debit landing page when "Yes " is selected 1.continue		
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void verifySetUpVariableDirectDebitConfirmationPage() {
			Report.createTestLogHeader("Direct Debit Journey", "verify whether customer can enable DD by filling information and submitting the form online");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("SetDirectDebit");
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.selectAccount(directDebit)
			.prepopulated_verifyVariableDirectdebitPage(directDebit)
			.verifyPaymentLink()
			.verifyBankDetailsInIsu(directDebit);
		}		
//TC_Fixed DD _12(a) Verify whether collective contract account customers are able to proceed with Variable Direct Debit.		
			@Test(groups ={Slingshot,Regression,DirectDebit})
			public void Validatecollectivecustomer() {
				Report.createTestLogHeader("Variable Direct Debit Journey", "Verify whether collective contract account customers are able to proceed with Variable Direct Debit.");
				DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("VDdBackToAccount");
				 UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVlessthanfifteenaccts"); 	
				new DirectDebitAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(directDebit)
				.BgbverifyAfterLogin(directDebit)
				.ManageAccount(userProfile)
				.validatPDvWarrentuser();				
			}
//TC_Fixed DD _155 Verify whether if a customer is on dunning path due to DD default/in PDV warrant stage should not be allowed to setup fixed DD. 		
			@Test(groups ={Slingshot,Regression,DirectDebit})
			public void ValidatePDvarrent() {
				Report.createTestLogHeader("Variable Direct Debit Journey", "Verify whether if a customer is on dunning path due to DD default/in PDV warrant stage should not be allowed to setup fixed DD.");
				DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("VDdBackToAccount");
				 UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVlessthanfifteenaccts"); 	
				new DirectDebitAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(directDebit)
				.BgbverifyAfterLogin(directDebit)
				.ManageAccount(userProfile)
				.validatecollectivecustomer();				
			}
			
//TC_Fixed DD _78 Verify whether the "Error  overlay " is getting displayed when the customer tries to Amend DD within 28 days of set up/Amend Direct debit.
//TC_Fixed DD _80 Verify whether the page remains in the account Summary page, on clicking close/ cross symbol in the "Direct Debit Guarantee" overlay displayed 		
		@Test(groups ={Slingshot,Regression,DirectDebit})
		public void ValidateAmendDDwithin28days() {
			Report.createTestLogHeader("Variable Direct Debit Journey", "cccccccccccccccccccccccc");
			DirectDebit directDebit = new TestDataHelper().getDirectDebitUserProfile("VDdBackToAccount");
			 UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVlessthanfifteenaccts"); 	
			new DirectDebitAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(directDebit)
			.BgbverifyAfterLogin(directDebit)
			.ManageAccount(userProfile)
			.AmendDirectDebit()
			.ErrorValidationAmendwithin28days();
		}

								
}
