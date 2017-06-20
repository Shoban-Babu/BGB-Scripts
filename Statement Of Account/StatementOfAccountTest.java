/**
 * 
 */
package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.AccountOverview;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.SubmitMeterRead;

import java.text.ParseException;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.StatementOfAccountAction;
import bg.framework.app.functional.action.Slingshot.SubmitMeterReadAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.SMRAccountDetails;
//import bg.framework.app.functional.entities.StatementOfAccount;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 255501
 *
 */
public class StatementOfAccountTest extends TestBase {
	
	//TC_SOA_001 Validate whether the user is able to proceed Statement of account journey by Clicking "Billing" link (LHN) on account summary page.
	//TC_SOA_002 Validate whether the user is able to perform Statement of account journey by Clicking "View statement of account" link in Billing landing page
	//TC_SOA_004 Validate whether the user is able to view Statement of account and PDF by giving specific date range for Gas account
	//TC_SOA_025 Check whether the email is triggered for customer after successful completion of SOA journey
	//TC_SOA_019 Validate whether the user can download the printable version in PDF format by clicking "Download statement PDF" link in RHN
    //TC_SOA_013 Check whether the user can select a specfic date range for a selected account 
	//TC_SOA_017 Validate whether Statement of account for selected date range is displayed in tabular format  in online
	@Test(groups ={Slingshot,Regression})
	public void verifyStatementofAccountForGasCustomer() throws ParseException{				 
		Report.createTestLogHeader("Statement Of Account", "Verifies the Statement of account and Download the PDF by giving specific date range for Gas account");
		UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalGasAccount");
				new LoginAction()
				.BgbnavigateToLogin()
				.bgbloginDetails(userProfile);	
				new StatementOfAccountAction()
				.VerifyBillingLinkAction(userProfile)
				.VerifyStatementOfAccountLinkAction(userProfile)
				.ClickFromAndToDateInDatePicker()
				.ClickviewButton()
				.ClickDownLoadPDFLink()
				.verifyLeadTable(userProfile);
				}
	
	//TC_SOA_003 Validate whether the user is able to perform Statement of account journey by Clicking "View statement of account" link in LHN of Billing landing page
	//TC_SOA_005 Validate whether the user is able to view Statement of account and PDF by giving specific date range for Electricity account
	@Test(groups ={Slingshot,Regression})
	public void verifyLHNStatementofAccountForElecCustomer() throws ParseException{				 
		Report.createTestLogHeader("Statement Of Account", "Verifies the Statement of account and Download the PDF by giving specific date range for Electric account");
		UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalElecAccount");
				new LoginAction()
				.BgbnavigateToLogin()
				.bgbloginDetails(userProfile);
				new StatementOfAccountAction()
				.VerifyBillingLinkAction(userProfile)
				.VerifyStatementOfAccountLinkAction(userProfile)
				.VerifyLHNStatementOfAccountLinkAction()
				.ClickFromAndToDateInDatePicker()
				.ClickviewButton()
				.ClickDownLoadPDFLink()
				.verifyLeadTable(userProfile);			
			
}
	//TC_SOA_006 Validate whether the user is able to view Statement of account and PDF by giving specific date range for Collective gas account
	@Test(groups ={Slingshot,Regression})
	public void verifyStatementofAccountForCollectiveGasCustomer() throws ParseException{				 
		Report.createTestLogHeader("Statement Of Account", "Verifies the Statement of account and Download the PDF by giving specific date range for Collective Gas account");
		UserProfile userProfile = new TestDataHelper().getUserProfile("SOACollectiveGasAccount");
				new LoginAction()
				.BgbnavigateToLogin()
				.bgbloginDetails(userProfile);
				new StatementOfAccountAction()
				.verifyManageAccountLink(userProfile)
				.VerifyBillingLinkAction1()
				.VerifyStatementOfAccountLinkAction(userProfile)
				.ClickFromAndToDateInDatePicker()
				.ClickviewButton();
			//	.ClickDownLoadPDFLink();
			    //.verifyLeadTable(userProfile);
				}
	
	//TC_SOA_007 Validate whether the user is able to view Statement of account and PDF by giving specific date range for Collective electricity account
		@Test(groups ={Slingshot,Regression})
		public void verifyStatementofAccountForCollectiveElecCustomer() throws ParseException{				 
			Report.createTestLogHeader("Statement Of Account", "Verifies the Statement of account and Download the PDF by giving specific date range for Collective Electric account");
			UserProfile userProfile = new TestDataHelper().getUserProfile("SOACollectiveElecAccount");
			new LoginAction()
				.BgbnavigateToLogin()
				.bgbloginDetails(userProfile);
				new StatementOfAccountAction()
				.verifyManageAccountLink(userProfile)
				//.VerifyBillingLinkAction1()
				.VerifyStatementOfAccountLinkAction(userProfile)
				.ClickFromAndToDateInDatePicker()
				.ClickviewButton()
				.verifyLeadTable(userProfile);
				}
//	TC_SOA_014  Verify the functionality of date fields 
		
	@Test(groups ={Slingshot,Regression})
	public void validateFromDateToDate() throws ParseException{				 
		Report.createTestLogHeader("Statement Of Account", "Verify the functionality of date fields From and To validation");
		UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalGasAccount");
				new LoginAction()
				.BgbnavigateToLogin()
				.bgbloginDetails(userProfile);
				new StatementOfAccountAction()
				.VerifyBillingLinkAction(userProfile)
				.VerifyStatementOfAccountLinkAction(userProfile)
				.FromDateToDateValidation(userProfile);
	}
				
	// TC_SOA_012 Validate whether the user can able to view the next or previous page of your statement history by selecting 
	@Test(groups ={Slingshot,Regression})
	public void verifyLHNStatementofAccount() throws ParseException{				 
		Report.createTestLogHeader("Statement Of Account", "Validate whether the user can able to view the next or previous page of your statement history by selecting ");
		UserProfile userProfile = new TestDataHelper().getUserProfile("SOAPagevalidation");
				new LoginAction()
				.BgbnavigateToLogin()
				.bgbloginDetails(userProfile);
				new StatementOfAccountAction()
				.VerifyBillingLinkAction(userProfile)
				.VerifyStatementOfAccountLinkAction(userProfile)
				.VerifyLHNStatementOfAccountLinkAction()
				.ClickviewButton()		
				.verifyPaginationNumbersLink();
}
	
	//TC_SOA_012 Page Navigation 
	@Test(groups ={Slingshot,Regression})
	public void verifySOAPageNavigation() throws ParseException{				 
		Report.createTestLogHeader("Statement Of Account", "Verifies the Statement of account and SAP verification ");
		UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalGasAccountt");
				new LoginAction()
				.BgbnavigateToLogin()
				.bgbloginDetails(userProfile);
				new StatementOfAccountAction()
				.VerifyBillingLinkAction(userProfile)
				.VerifyStatementOfAccountLinkAction(userProfile) 
				.ClickFromAndToDateInDatePicker()
				.ClickviewButton()			  
				.verifySOADataThroughQTP(userProfile);
				
}
	//TC_SOA_015 Validate whether the date picker From date To date Overlay Error 
		@Test(groups ={Slingshot,Regression})
		public void ValidateFromdateTodateOverlayError() throws ParseException
		{				 
			Report.createTestLogHeader("Statement Of Account", "Validate whether the date picker From date To date Overlay Error");
			UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalGasAccount");
					new LoginAction()
					.BgbnavigateToLogin()
					.bgbloginDetails(userProfile);
					 new StatementOfAccountAction()
					.VerifyBillingLinkAction(userProfile)
					.VerifyStatementOfAccountLinkAction(userProfile)
					.FromDateToDateErrorValidation(userProfile); //fromdate todate error validatation
		}
	//TC_SOA_020 Validate the bread crumb link navigations
		@Test(groups ={Slingshot,Regression})
		public void ValidateBreadCrumbLinks() throws ParseException{				 
			Report.createTestLogHeader("Statement Of Account", "Verifies the Breadcrumb Link Navigation");
			UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalGasAccount");
					new LoginAction()
					.BgbnavigateToLogin()
					.bgbloginDetails(userProfile);
					 new StatementOfAccountAction()
					 .VerifyBillingLinkAction(userProfile)
					  .VerifyStatementOfAccountLinkAction(userProfile)
					  .verifyBreadCrumbLinks();	
	}		
   //TC_SOA_022 Verify the link navigations of "Billing" landing page
				@Test(groups ={Slingshot,Regression})
				public void ValidateBillingNavigationLinks() throws ParseException{				 
					Report.createTestLogHeader("Statement Of Account", "Verifies the Billing Navigation Links");
					UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalGasAccount");
							new LoginAction()
							.BgbnavigateToLogin()
							.bgbloginDetails(userProfile);
							new StatementOfAccountAction()
							.VerifyBillingLinkAction(userProfile)
							.verifyBillingNavigatinoLinks();
			}
//TC_SOA_008 Validate whether the user can view the default "your statement history" for last three months in View Statement  page
			
				@Test(groups ={Slingshot,Regression})
				public void VerifyDefault3MonthsDifferenceInFromDateTodate() throws ParseException{				 
					Report.createTestLogHeader("Statement Of Account", "Verifies the Statement of account and Download the PDF by giving specific date range for Gas account");
					UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalGasAccount");
							new LoginAction()
							.BgbnavigateToLogin()
							.bgbloginDetails(userProfile);
							new StatementOfAccountAction()
							.VerifyBillingLinkAction(userProfile)
							.VerifyStatementOfAccountLinkAction(userProfile)
							.FromDateToDateMonthsValidation();
							

			}
//TC_SOA_024	Verify the link navigations of "Statement of account" landing page and Footers			
				@Test(groups ={Slingshot,Regression})
				public void VerifySOABackToYourAccount() throws ParseException{				 
					Report.createTestLogHeader("Statement Of Account", "Verifies the Statement of account and Download the PDF by giving specific date range for Gas account");
					UserProfile userProfile = new TestDataHelper().getUserProfile("SOANormalGasAccount");
							new LoginAction()
							.BgbnavigateToLogin()
							.bgbloginDetails(userProfile)	;
							 new StatementOfAccountAction()
							.VerifyBillingLinkAction(userProfile)
							.VerifyStatementOfAccountLinkAction(userProfile)
							.FromDateToDateMonthsValidation()
							.SOABackToYourAccount(userProfile);
							
		}
	
			
	
}
