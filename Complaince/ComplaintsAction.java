package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.ComplaintsPage;
import bg.framework.app.functional.page.Slingshot.MakingComplaintsPage;



public class ComplaintsAction {
	
	public ComplaintsAction verifyComplaintPage(){
		ComplaintsPage ComplaintsPage = new ComplaintsPage();
		ComplaintsPage.ComplaintsFindmore();
		return this;
	}
	
	public ComplaintsAction selectGasandEelctComplaint(){
		ComplaintsPage ComplaintsPage = new ComplaintsPage();
		ComplaintsPage.selectGasandEelctComplaint();
		return this;
	}
	
	public ComplaintsAction selectCarePlanComplaint(){
		ComplaintsPage ComplaintsPage = new ComplaintsPage();
		ComplaintsPage.selectCarePlanComplaint();
		return this;
	}
	
	public ComplaintsAction RaiseGasElectComplaint(UserProfile userprofile){
		ComplaintsPage ComplaintsPage = new ComplaintsPage();
		ComplaintsPage.RaiseGasElectComplaint(userprofile);
		return this;
	}
	
	public ComplaintsAction selectquery(){
		ComplaintsPage ComplaintsPage = new ComplaintsPage();
		ComplaintsPage.selectquery();
		return this;
	}
	
}
