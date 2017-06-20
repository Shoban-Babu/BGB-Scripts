package bg.framework.app.functional.test.Slingshot;

import org.testng.annotations.Test;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import java.text.ParseException;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.AccountOverViewAction;
import bg.framework.app.functional.action.Slingshot.AccountSummaryAction;
import bg.framework.app.functional.action.Slingshot.MultiUserMultiViewAction;
import bg.framework.app.functional.action.Slingshot.PaperlessBillingAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

public class PaperlessBillingTest extends TestBase {

//TC_PB_10(E to E) Verify the end to end flow when customer opt in by clicking "Switch to paperless " checkbox Globallly
//TC_PB_28         Verify whether overlay is displayed when the customer selects the checkbox in account overview screen when global paperless is to be set up.	
	
	@Test(groups ={Slingshot,Regression,Regression})
	public void VerifyglobalSwithchtopaperlessSetup() 
	{
			Report.createTestLogHeader("PaperlessBilling", "Verify  the end to end flow when paperless billing option is chosen in Account overview"); 
		    UserProfile userProfile = new TestDataHelper().getUserProfile("SwitchtoglobalPaperless");
		  	new PaperlessBillingAction()
		  	.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.BgbverifyAfterLogin();	
		  	new PaperlessBillingAction()
		  	.verifyManageAccountLink(userProfile)
		  	.gloablpaperlesssetupAction()	
		  	.VerifyAudit_EmailtriggeredAction(userProfile);
	}
	
//TC_PB_12(E to E) Verify the end to end flow when customer opt out by clicking "Switch to paperbills " checkbox Globally
//TC_PB_37         Verify whether overlay is displayed when the customer selects the checkbox in account overview screen when global paperless is not to be set up.
//TC_PB_40         Verify whether confirmation overlay is displayed when 'Yes' is selected in the PB overlay when global paperless is not  set up.	
//TC_PB_28 	       Verify whether confirmation email is triggered to the customer when user opts for billing through paper for global level
//TC_PB_55         Verify whether confirmation overlay is closed on clicking close link/cross symbol when account specific is not set up.
//TC_PB_42         Verify whether confirmation overlay is closed on clicking close link/cross symbol when global paperless is  not set up.	
	@Test(groups ={Slingshot,Regression})
	public void VerifyglobalSwithchtopaperSetup() throws ParseException
	{				 
			Report.createTestLogHeader("PaperlessBilling", "whether confirmation overlay is displayed when 'Yes' is selected in the PB overlay when global paperless is not  set up."); 
			UserProfile userProfile = new TestDataHelper().getUserProfile("SwitchtoglobalPaper");					
			new PaperlessBillingAction()
		  	.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.BgbverifyAfterLogin();		
			new PaperlessBillingAction()
			.gloablpaperetupAction() // have to update online audit entry and SAP verification details.
			.VerifyAudit_EmailtriggeredAction(userProfile);
	}

//TC_PB_15(E to E) Verify the end to end flow when customer opt in by clicking "Switch to paperless " checkbox for specfic account.
//TC_PB_14   Verify whether "Switch to paperless " checkbox is displayed in account Summary page  when the customer did not opt in Paperless Billing for specfic account.	
//TC_PB_13 	 Verify the  "Paperless billing " static text  is displayed in account overview page  when the customer opted in Paperless Billing for some acounts of a BP ORG.
//TC_PB_47   Verify whether the "what's this "? overlay is getting displayed on clicking the link and  verify its look and feel in the account summary page when user opted out of paperless	
	@Test(groups ={Slingshot,Regression,Regression})
	public void VerifySwitchtopaperlessforSpecificacct() 
	{
		Report.createTestLogHeader("PaperlessBilling", "Verify  the end to end flow when paperless billing option is chosen in Account overview"); 
		UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecifics");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("CrmDetailsforpaperbilling"); 
	  	new PaperlessBillingAction()
	  	.BgbnavigateToLogin()
		.BgbloginDetails(userProfile)
		.BgbverifyAfterLogin();					
	  	new PaperlessBillingAction()
	  	.verifyManageAccountLink(userProfile)
	  	.specificpaperlesssetupAction()	
	  	.VerifyAudit_EmailtriggeredAction(userProfile);
	 // 	.VerifySAPCRM_PBChange(crmuserProfile,userProfile,"Paper Less Invoice")
		//.VerifySAPISU_PBChange(userProfile);
	}
	
 
				
//TC_PB_17(E to E) Verify the end to end flow when customer opt out by clicking "Switch to paperbills " checkbox for specfic account.
//TC_PB_49 Verify whether the "what's this "? overlay is getting displayed on clicking the link and  verify its look and feel in the account summary page when user opted into paperless
//TC_PB_50 Verify whether overlay is displayed when the customer selects the checkbox in account summary  screen when account specific paperless is to be set up.
//TC_PB_53 Verify whether confirmation overlay is displayed when 'Yes' is selected in the PB overlay when account specific  is to be set up.	
	@Test(groups ={Slingshot,Regression})
	public void VerifySwitchtopaperforSpecificacct() throws ParseException
	{				 
		Report.createTestLogHeader("PaperlessBilling", "Verify the end to end flow when customer opt out by clicking 'Switch to paperbills' checkbox for specfic account"); 
		UserProfile userProfile = new TestDataHelper().getUserProfile("paperswitchspecific");
		//CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("CrmDetailsforpaperbilling"); 
		new PaperlessBillingAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile)
		.BgbverifyAfterLogin();		
		new PaperlessBillingAction()
		.verifyManageAccountLink(userProfile)
		.specificpapersetupAction()		
		.VerifyAudit_EmailtriggeredAction(userProfile);		
		//.VerifySAPCRM_PBChange(crmuserProfile,userProfile,"PrintedInvoice")
		//.VerifySAPISU_PBChange(userProfile);
		
	}
	
//TC_PB_07 Verify whether NPS survey link is displayed in all pages of following scenarios.	Logged in paperless journey.

	@Test(groups ={Slingshot,Regression})
	public void VerifysubmitNPSsurvey() throws ParseException{				 
		Report.createTestLogHeader("PaperlessBilling", "Verify  whether NPS survey link is displayed in all pages of following scenarios"); 
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");					
		new PaperlessBillingAction()
	  	.BgbnavigateToLogin()
		.BgbloginDetails(userProfile)
		.BgbverifyAfterLogin();		
		new PaperlessBillingAction()
		.submitNpsSurvey(userProfile);	  	
	}
	
//TC_PB_01(E to E) Verify  the end to end flow when paperless billing option is chosen in Account overview(collective account)
		@Test(groups ={Slingshot,Regression})
		public void VerifyCollectivepaperlesssetup() throws ParseException{				 
		Report.createTestLogHeader("PaperlessBilling", "Verify  the end to end flow when paperless billing option is chosen in Account overview(collective account)"); 
		UserProfile userProfile = new TestDataHelper().getUserProfile("collectivePaperlessBilling");					
		new PaperlessBillingAction()
	  	.BgbnavigateToLogin()
		.BgbloginDetails(userProfile)
		.BgbverifyAfterLogin();		
		new PaperlessBillingAction()
		.verifyManageAccountLink(userProfile)
		.specificpaperlesssetupAction()	
	  	.VerifyAudit_EmailtriggeredAction(userProfile);
		}
//TC_PB_02  Verify whether the paper less billing link is displayed only for super users and full access users in the account summary and account overview page.		
	@Test(groups ={Slingshot,Regression})
	public void VerifyPBAccountoverviewandsummarylink() throws ParseException{				 
		Report.createTestLogHeader("PaperlessBilling", "Verify whether the paper less billing link is displayed only for super users and full access users in the account summary and account overview page.");
		UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecifics");						
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile)
		.BgbverifyAfterLogin();	
		new PaperlessBillingAction()
		.paperlesslink(userProfile);					
		}	
//TC_PB_05 Verify whether paperless billing can be enabled/ disabled only at account level in more than 15 accounts
//TC_PB_18 Verify whether customers with more than 15 accounts cannot proceed with paperless billing at global level.
//TC_PB_19 Verify whether customers with more than 15 accounts can proceed with paperless billing at account level.	
		@Test(groups ={Slingshot,Regression,Regression})
		public void morethan15Acctspaperless() 
		{
			Report.createTestLogHeader("PaperlessBilling", "Verify whether customers with more than 15 accounts can proceed with paperless billing at account level"); 
			UserProfile userProfile = new TestDataHelper().getUserProfile("morethan15AcctsSwitchtopaperlessacctspecifics");
		  	new PaperlessBillingAction()
		  	.BgbnavigateToLogin()
			.BgbloginDetails(userProfile)
			.BgbverifyAfterLogin();					
		  	new PaperlessBillingAction()
		  //	.morethan15acctsacctgloabllevel(userProfile);
		  	.morethan15Accts(userProfile)
		  	.verifyManageAccountLink(userProfile)
		  	.specificpaperlesssetupAction()	
		  	.VerifyAudit_EmailtriggeredAction(userProfile);
		}
		
//TC_PB_03	Verify whether only Enterprise super users have PB option(by default ticked ) in step 2 of self registration.
//TC_PB_11	Verify whether "Switch to paperbills " checkbox is displayed in account overview page  when the customer opted in Paperless Billing during Registration.
//TC_PB_17  Verify whether "Switch to paperbills " checkbox is displayed in account overview page  when the customer opted in Paperless Billing for specfic account.
		@Test(groups ={Slingshot,Regression})
		public void VerifyEnterprisecustomer() throws ParseException{				 
		    Report.createTestLogHeader("PaperlessBilling", "Verify whether only Enterprise super users have PB option(by default ticked ) in step 2 of self registration."); 
		    UserProfile userProfile = new TestDataHelper().getUserProfile("EnterprisePaperlessBilling");					
		    new PaperlessBillingAction()
		    .PaperlessRegistrationPage_Enterprise(userProfile);							
		     new LoginAction()
		    .BgbnavigateToLogin()
		    .bgbloginDetails(userProfile);
		     new PaperlessBillingAction()
		     .paperlesslink(userProfile)	
		     .verifyManageAccountLink(userProfile)
			 .specificpapersetupAction();		
			//.VerifyAudit_EmailtriggeredAction(userProfile);
			}	
			
//TC_PB_09 Verify whether "Switch to paperless " checkbox is displayed in account overview page  when the customer did not opt in Paperless Billing during Registration.	

		@Test(groups ={Slingshot,Regression})
		public void VerifythePaperlessjourney() throws ParseException{				 
		    Report.createTestLogHeader("PaperlessBilling", "Verify whether 'Switch to paperless'Checkbox is displayed in account overview page  when the customer did not opt in Paperless Billing during Registration"); 
		    UserProfile userProfile = new TestDataHelper().getUserProfile("PaperlessBilling1");					
		    new PaperlessBillingAction()
		    .PaperlessRegistrationPage(userProfile);							
		     new LoginAction()
		    .BgbnavigateToLogin()
		    .bgbloginDetails(userProfile);
		     new PaperlessBillingAction()
		     .verifyManageAccountLink(userProfile)
		   	.specificpaperlesssetupAction()	
		     .VerifyAudit_EmailtriggeredAction(userProfile);		  	  
			}
//TC_PB_04 Verify whether - EDI/TVI customers cannot opt-in for paperless billing online		
		@Test(groups ={Slingshot,Regression})
			public void VerifyEDI_TVIcustomervalidation() throws ParseException{				 
			Report.createTestLogHeader("PaperlessBilling", "Verify whether - EDI TVI customers cannot opt-in for paperless billing online"); 
			UserProfile userProfile = new TestDataHelper().getUserProfile("EDIPaperlessBilling");								
			 new LoginAction()
			 .BgbnavigateToLogin()
			 .bgbloginDetails(userProfile);
			 new PaperlessBillingAction()
			 .verifyManageAccountLink(userProfile)
			 .ValidateEDITVIuser();						
			}
		
//TC_PB_06 Verify whether corporate customers can be enabled/ disabled only at account level 	
	@Test(groups ={Slingshot,Regression})
		public void Verifycorporatecustomer() throws ParseException{				 
		Report.createTestLogHeader("PaperlessBilling", "Verify  the end to end flow when paperless billing option is chosen in Account overview"); 
				UserProfile userProfile = new TestDataHelper().getUserProfile("corporatepaperlessbilling");						
				new PaperlessBillingAction()
				.PaperlessRegistrationPage_corp(userProfile);			
				new LoginAction()
				.BgbnavigateToLogin()
				.bgbloginDetails(userProfile);
				new PaperlessBillingAction()
				.Validatecorporateuser();								
}		
	
}
