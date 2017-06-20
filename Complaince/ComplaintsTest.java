package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.BGBRegistration;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.Smoke;

import bg.framework.app.functional.action.Slingshot.ComplaintsAction;
import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.action.Slingshot.SapCrmAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import org.testng.annotations.Test;

@Test(groups ={Slingshot,Smoke,BGBRegistration})
public class ComplaintsTest extends TestBase {
	
	public void verifyAnonymousRaiseComplaints_Gas_Elect_Journey(){ 
		UserProfile userProfile = new TestDataHelper().getUserProfile("ContactUs");
		Report.createTestLogHeader("Complaints Journey-Anonymous", "Verifies the complaints functionality for Anonymous");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbVerifyLogin(userProfile);
		new HomePageAction()
		.navigateToHelpAndAdvicePage();
		new ComplaintsAction()
		.verifyComplaintPage()
		.selectGasandEelctComplaint()
		.RaiseGasElectComplaint(userProfile);
		}
	
	public void verifyAnonymousRaiseComplaints_Careplan_Journey(){ 
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvUserAccessDetails");
		Report.createTestLogHeader("Complaints Journey-Anonymous", "Verifies the complaints functionality for Anonymous");
	    new HomePageAction()
		.navigateToHelpAndAdvicePage();
		new ComplaintsAction()
		.verifyComplaintPage()
		.selectCarePlanComplaint();
			}
	
	public void verifyAnonymousRaiseComplaints_BoilerInstall_Journey(){ 
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvUserAccessDetails");
		Report.createTestLogHeader("Complaints Journey-Anonymous", "Verifies the complaints functionality for Anonymous");
	    new HomePageAction()
		.navigateToHelpAndAdvicePage();
		new ComplaintsAction()
		.verifyComplaintPage();
		}
	
	public void verifyAnonymousselectAQuery_Journey(){ 
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvUserAccessDetails");
		Report.createTestLogHeader("Complaints Journey-Anonymous", "Verifies the complaints functionality for Anonymous");
	    new HomePageAction()
		.navigateToHelpAndAdvicePage();
		new ComplaintsAction()
		.verifyComplaintPage()
		.selectquery();
		}


}
