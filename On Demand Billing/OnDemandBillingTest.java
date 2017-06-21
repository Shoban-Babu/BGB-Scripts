/**
 * 
 */
package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.Tetris;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.SubmitMeterReadAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 292238
 *
 */
import static bg.framework.app.functional.entities.FunctionalCategory.Tetris;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.SubmitMeterReadAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 292238
 *
 */
public class OnDemandBillingTest extends TestBase{
	
	//TC_ODB_001: Validate whether the user is able to perform On Demand billing journey by Clicking the Submit meter read on account page
	//TC_ODB_009:Validate whether real time bill is generated for customer when all meters are submiitted with plausible meter read.
	//TC_ODB_014:Verify whether the following fields are correctly displayed in the On Demand Billing confirmation page for the particular customer
	//TC_ODB_015:Verify whether the following amounts updated from SAP ISU
	//TC_ODB_019:Verify the confirmation email is triggered after completing On Demand Billing journey
	//TC_ODB_023: Verify the functionality of 'Pay new balance' link in On demand billing screen
	@Test(groups={Tetris})
	public void verifyODB_Gas(){
		Report.createTestLogHeader("OnDemandBillingTest", "Verifies On demand billing journey for Gas");
		 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("ODB_Gas");	
		 new SubmitMeterReadAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(smrProfile)
         .bgbverifyAfterLogin(smrProfile)
         .clickSMRLink(smrProfile)
         .enterMeterRead(smrProfile)
         .verifyMeterReadConfirmation()
         .verifyMailSentAuditConfirmation(smrProfile)
         .verifyODBWithISU(smrProfile)
         .verifyLinkNavigations(smrProfile)
         .verifyPayNewBalanceLink();
	}
	
	@Test(groups={Tetris})
	public void verifyODB_Elec(){
		Report.createTestLogHeader("OnDemandBillingTest", "Verifies On demand billing journey for Elec");
		 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("ODB_Elec");	
		 new SubmitMeterReadAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(smrProfile)
         .BgbverifyAfterLogin()
         .clickSMRLink(smrProfile)
         .enterMeterRead(smrProfile)
         .verifyMeterReadConfirmation()
         .verifyODBWithISU(smrProfile)
          .verifyLinkNavigations(smrProfile)
         .verifyPayNewBalanceLink();;		
	}
	//TC_ODB_003:Validate whether inactive customers are not able to perform On Demand Billing
	//TC_ODB_011:Verify whether normal meter read confirmation page will be displayed when realtime bill is not generated
	@Test(groups={Tetris})
	public void verifyODB_CollectiveAcc(){
		Report.createTestLogHeader("OnDemandBillingTest", "Verifies On demand billing journey for collective account");
		 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("ODB_CollectiveAccount");	
		 new SubmitMeterReadAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(smrProfile)
         .BgbverifyAfterLogin()
         .clickSMRLink(smrProfile)
         .enterMeterRead(smrProfile)
         .verifyMeterReadConfirmation()
         .verifyODB_CollectiveAccount(smrProfile);		
	}
	//TC_ODB_002:Validate whether the user is able to perform submit meter read journey by Clicking the Submit meter read using the "Manage Account" 
	//in Account Overview landing page 
	@Test(groups={Tetris})
	public void verifySMR(){
		Report.createTestLogHeader("OnDemandBillingTest", "Verifies user is able to perform submit meter read journey by Clicking the Submit meter read using the Manage Account in Account Overview ");
		 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("ODB_Elec");	
		 new SubmitMeterReadAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(smrProfile)
         .BgbverifyAfterLogin()
         .clickManageAccountAndSMRLink(smrProfile)
         .enterMeterRead(smrProfile)
         .verifyMeterReadConfirmation();		
	}
	//TC_ODB_010:Verify whether real time bill details are not generated when customer submits some of the reads as implausible meter reads 
	@Test(groups={Tetris})
	public void verifySMRForInactive(){
		Report.createTestLogHeader("OnDemandBillingTest", "Validates customers are not able to perform On Demand Billing for inactive account");
		 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("ODB_Inactive");	
		 new SubmitMeterReadAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(smrProfile)
         .BgbverifyAfterLogin()
         .clickManageAccountAndSMRLink(smrProfile)
         .verifyInactiveAccount();
         	
	}
	//TC_ODB_007:Validate whether user is able to select the search criteria to proceed with On Demand Billing for more than 3 meters
	//TC_ODB_018:Verify the functionality of search field 
	@Test(groups={Tetris})
	public void verifyODB_Morethan3Meter(){
		Report.createTestLogHeader("OnDemandBillingTest", "Verifies On demand billing journey for more than 3 meters");
		 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("Morethan3Gas");	
		 new SubmitMeterReadAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(smrProfile)
         .BgbverifyAfterLogin()
         .clickSMRLink(smrProfile)
         .enterMeterRead(smrProfile)
         .verifyMeterReadConfirmation();	
	}
	//TC_ODB_006:Validate whether error message is displayed in search field for more than 3 devices
	@Test(groups={Tetris})
	public void validateMPRNNumber(){
		Report.createTestLogHeader("OnDemandBillingTest", "Verifies On demand billing journey for MPRN field validation");
		 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("Morethan3Gas");	
		 new SubmitMeterReadAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(smrProfile)
         .BgbverifyAfterLogin()
         .clickSMRLink(smrProfile)
         .validateMprnNumber();
	}
	//TC_ODB_013: Verify the link navigations in the On Demand billing screen
	//TC_ODB_025	Verify the "Back to your account" button functionality in the confirmation page in NHH  and NDM electricity multi register meter
	//TC_ODB_026	Verify the link navigations in the breadcrumb in On demand billing screen in NHH  and NDM electricity multi register meter
	//TC_ODB_027: Verify the link navigations in the pods in On demand billing screen 
	@Test(groups={Tetris})
	public void verifyLinkNavigations(){
		Report.createTestLogHeader("OnDemandBillingTest", "Verifies the link navigations in the pods in On demand billing screen");
		 SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("ODB_Gas");	
		 new SubmitMeterReadAction()
         .BgbnavigateToLogin()
         .BgbloginDetails(smrProfile)
         .BgbverifyAfterLogin()
         .clickSMRLink(smrProfile)
         .enterMeterRead(smrProfile)
         .verifyMeterReadConfirmation()
         .verifyPayNewBalanceLink()
         .verifyLinkNavigations(smrProfile)
         .verifyPodLink();	
	}
}

