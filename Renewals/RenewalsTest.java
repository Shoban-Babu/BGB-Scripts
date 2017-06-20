package bg.framework.app.functional.test.Slingshot;

import bg.framework.app.functional.action.Slingshot.AccountSummaryAction;
import bg.framework.app.functional.action.Slingshot.BgbRegistrationAction;
import bg.framework.app.functional.action.Slingshot.RenewalsAction;
import bg.framework.app.functional.action.Slingshot.SapCrmAction;
import static bg.framework.app.functional.entities.FunctionalCategory.BGBRegistration;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Smoke;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import org.testng.annotations.Test;

public class RenewalsTest extends TestBase {
	public static String Contract;
	
	//TC_Renewal_007 a) Verify whether 'Your energy plan is due for renewal' link is displayed in account overview page when the contract is due for renewal.(less than 15 accounts)
	//TC_Renewal_009 Verify whether the promo page is displayed on clicking 'Your energy plan is due for renewal' in Account overview page  for Auto renewal for current DD/CC.
	//TC_Renewal_011 Verify the link navigations in the promo page.
	//TC_Renewal_012 Verify the functionality of request a callback link in RHN in promo page.
	//TC_Renewal_014 Verify the link 'View fixed price energy plan prices' in promo page.
	//TC_Renewal_015 Verify whether  Renewal quote page is getting displayed on clicking the 'View fixed price energy plan prices' in promo page.
	//TC_Renewal_017 Verify the field values from SAP in 1 year fixed rate in Gas account
	//TC_Renewal_020 Verify the field values from SAP in 1 year fixed rate in Electricity account
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	    public void verify1YearFixedRateRenewals() throws Exception {
	        Report.createTestLogHeader("Renewals", "Verify the field values from SAP in 1 year fixed rate");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
	        CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");
	        new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile);
	        new SapCrmAction()
			 .loginDetails(crmuserProfile);
	       	new RenewalsAction()
			.verify1YearFixedRate(crmuserProfile, userProfile)
	       	.BgbloginDetails(userProfile)
	     	.energyPlanRenewalLink()
	        .energyPlanRenewalPage()
	        .energyPlanRenewalQuote1(userProfile)
	        .energy1YearFixedRate(userProfile)
	        .energy1YearRenewalSummary(userProfile)
			.energy1YearRenewalConfirmation(userProfile);
		}
		
	//TC_Renewal_018 Verify the field values from SAP in 2 year fixed rate in Gas account
	//TC_Renewal_021 Verify the field values from SAP in 2 year fixed rate in Electricity account
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Smoke,BGBRegistration})	
		    public void verify2YearFixedRateRenewals() throws Exception {
		        Report.createTestLogHeader("Renewals", "Verify the field values from SAP in 2 year fixed rate");
		        UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
		        CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");
		        new HomePageAction()
				.BgbnavigateToLogin()
				.BgbloginDetails(userProfile);
		        new SapCrmAction()
				 .loginDetails(crmuserProfile);
				new RenewalsAction()
				.verify2YearFixedRate(crmuserProfile, userProfile)
				.BgbloginDetails(userProfile)
		      	.energyPlanRenewalLink()
		        .energyPlanRenewalPage()
		        .energyPlanRenewalQuote2(userProfile)
		        .energy2YearFixedRate(userProfile)
		        .energy2YearRenewalSummary(userProfile)
				.energy2YearRenewalConfirmation(userProfile);
		     }
			
		//TC_Renewal_019 Verify the field values from SAP in 3 year fixed rate in Gas account
		//TC_Renewal_022 Verify the field values from SAP in 3 year fixed rate in Electricity account
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	    public void verify3YearFixedRateRenewals() throws Exception {
	        Report.createTestLogHeader("Renewals", "Verify the field values from SAP in 3 year fixed rate");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
	        CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");
	        new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile);
	        new SapCrmAction()
			 .loginDetails(crmuserProfile);
			new RenewalsAction()
			.verify3YearFixedRate(crmuserProfile, userProfile)
			.BgbloginDetails(userProfile)
	      	.energyPlanRenewalLink()
	        .energyPlanRenewalPage()
	        .energyPlanRenewalQuote3(userProfile)
	        .energy3YearFixedRate(userProfile)
	        .energy3YearRenewalSummary(userProfile)
			.energy3YearRenewalConfirmation(userProfile);
			}
		//TC_Renewal_024	Verify the functionality of Back link in Renewal quote page
		//TC_Renewal_025	Verify the functionality of Cancel link in Renewal quote page 
		//TC_Renewal_026	Verify the functionality of request a callback link in RHN in Renewal quote 
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	    public void verifyBackCancelRequestCallBackinRenewalsQuote() throws Exception {
	        Report.createTestLogHeader("Renewals", "Verify the functionality of Back link in Renewal quote page");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
	        new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile);
	        new RenewalsAction()
	        .verifyLinksinRenewalsQuote();
		}
		//TC_Renewal_034	Verify whether Decline info page is getting displayed when clicking 'I don't want to renew ' link in Renewal quote page
		//TC_Renewal_036	Verify the functionality of 'Request a call back' in RHN in Decline info page.
		//TC_Renewal_037	Verify the link navigations in Decline info page.
		//TC_Renewal_039	Verify the functionality of 'Back' link in Decline info page.

		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	    public void verifyBackCancelRequestCallBackinDontwanttoRenew() throws Exception {
	        Report.createTestLogHeader("Renewals", "Verify the functionality of Back Cancel RequestCallBack in Dont want to Renew");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
	        new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile);
	        new RenewalsAction()
	        .verifyLinksinDontRenew();
		}
		
		
		
/*
 * May Release 2016 WAVE Renewals
 */
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	    public void verifyRenewalsPrice() throws Exception {
	        Report.createTestLogHeader("Renewals", "verifyRenewalsPrice");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
	        new HomePageAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile);
	        /*new AccountSummaryAction()
	        .clickRenewaldueButton();*/
	        new RenewalsAction()
	        .navigateToRenewalsDue(userProfile)
	        
	        .VerifyRenewalcontent();
	      
		}
		
		@SuppressWarnings("unchecked")
        @Test(groups ={Slingshot,Smoke,BGBRegistration})       
     public void verifyRenewalsContract_FirstYear() throws Exception {
         Report.createTestLogHeader("Renewals", "Verify the functionalty of Renewal Contract");
         UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
        // CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");
         new HomePageAction()
               .BgbnavigateToLogin()
               .BgbloginDetails(userProfile);
         new RenewalsAction()
               .navigaeToRenewalsDue(userProfile)
               .VerifyRenewalcontent()
               .contractSelection("First");
               new HomePageAction()
               .BgbnavigateToLogin()
               .BgbloginDetails(userProfile);
         new RenewalsAction()
               .VerifyingRenewalsDuebutton(userProfile)
               .verifyAuditTabledetails(userProfile);
               
 
        }
		@SuppressWarnings("unchecked")
        @Test(groups ={Slingshot,Smoke,BGBRegistration})       
     public void verifyRenewalsContract_SecondYear() throws Exception {
         Report.createTestLogHeader("Renewals", "Verify the functionalty of Renewal Contract");
         UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
        // CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");
         new HomePageAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(userProfile);
   new RenewalsAction()
         .navigaeToRenewalsDue(userProfile)
         .VerifyRenewalcontent()
         .contractSelection("Second")
   .verifyAuditTabledetails(userProfile);   
         /*new HomePageAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(userProfile);
   new RenewalsAction()
         .VerifyingRenewalsDuebutton(userProfile)
         .verifyAuditTabledetails(userProfile);*/     
  }
		
		
		@SuppressWarnings("unchecked")
        @Test(groups ={Slingshot,Smoke,BGBRegistration})       
     public void verifyRenewalsContract_ThirdYear() throws Exception {
         Report.createTestLogHeader("Renewals", "Verify the functionalty of Renewal Contract");
         UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
        // CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");
         new HomePageAction()
               .BgbnavigateToLogin()
               .BgbloginDetails(userProfile);
    
               new RenewalsAction()
               .navigaeToRenewalsDue(userProfile)
               .VerifyRenewalcontent()
               .contractSelection("Third")
               .verifyAuditTabledetails(userProfile);  
               new HomePageAction()
               .BgbnavigateToLogin()
               .BgbloginDetails(userProfile);
         new RenewalsAction()
               .VerifyingRenewalsDuebutton(userProfile)
               .verifyAuditTabledetails(userProfile);   
        }
		
		
		@SuppressWarnings("unchecked")
        @Test(groups ={Slingshot,Smoke,BGBRegistration})       
     public void verifyRenewalsRejection() throws Exception {
         Report.createTestLogHeader("Renewals", "Verify the Renewal Rejection Functionality");
         UserProfile userProfile = new TestDataHelper().getUserProfile("Renewals");
        // CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");
         new HomePageAction()
               .BgbnavigateToLogin()
               .BgbloginDetails(userProfile);
    
               new RenewalsAction()
               .navigaeToRenewalsDue(userProfile)
               .renewalRejection();
 
        }

		
		
		
}
