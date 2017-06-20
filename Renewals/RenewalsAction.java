package bg.framework.app.functional.action.Slingshot;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AccountOverviewPage;
import bg.framework.app.functional.page.Slingshot.RenewalsPage;
import bg.framework.app.functional.page.Slingshot.SapCrmPage;
import bg.framework.app.functional.page.common.LegacyLoginPage;

public class RenewalsAction {
	static String [] DatafromCRM1 = new String [10];
	static String [] DatafromCRM2 = new String [10];
	static String [] DatafromCRM3 = new String [10];
		
	public RenewalsAction verify1YearFixedRate(CrmUserProfile crmuserProfile, UserProfile userProfile) throws ParseException{
		SapCrmPage sapcrmpage=new SapCrmPage();
		DatafromCRM1=sapcrmpage.verify1YearFixedRate(crmuserProfile);
		System.out.println(DatafromCRM1 + "in Action Page..................");
		return this;
	}
	
	public RenewalsAction energyPlanRenewalPage() {
		new RenewalsPage().energyPlanRenewalPage();
		return this;
		
	}

	public RenewalsAction energyPlanRenewalQuote1(UserProfile userProfile) {
		new RenewalsPage().energyPlanRenewalQuote(userProfile, DatafromCRM1[0]);
		return this;
	}
	public RenewalsAction energyPlanRenewalQuote2(UserProfile userProfile) {
		new RenewalsPage().energyPlanRenewalQuote(userProfile, DatafromCRM2[0]);
		return this;
	}
	public RenewalsAction energyPlanRenewalQuote3(UserProfile userProfile) {
		new RenewalsPage().energyPlanRenewalQuote(userProfile, DatafromCRM3[0]);
		return this;
	}



	public RenewalsAction energy1YearFixedRate(UserProfile userProfile) {
		System.out.println(DatafromCRM1 + "energy1YearFixedRate ........in Action Page..................");
		new RenewalsPage().energy1YearFixedRate(userProfile, DatafromCRM1[1],DatafromCRM1[2]);
		return this;
		
	}

	public RenewalsAction energy1YearRenewalSummary(UserProfile userProfile) {
		new RenewalsPage().energy1YearRenewalSummary(userProfile, DatafromCRM1[0],DatafromCRM1[1], DatafromCRM1[2]);
		return this;
		
	}
	
	public RenewalsAction energy1YearRenewalConfirmation(UserProfile userProfile) {
		new RenewalsPage().energy1YearRenewalConfirmation(userProfile);
		return this;
		
	}

	public RenewalsAction energy2YearFixedRate(UserProfile userProfile) {
		new RenewalsPage().energy2YearFixedRate(userProfile, DatafromCRM2[1],DatafromCRM2[2]);
		return this;
	}
	public RenewalsAction energy2YearRenewalSummary(UserProfile userProfile) {
		new RenewalsPage().energy2YearRenewalSummary(userProfile, DatafromCRM2[0],DatafromCRM2[1], DatafromCRM2[2]);
		return this;
		
	}

	public RenewalsAction energy3YearFixedRate(UserProfile userProfile) {
		new RenewalsPage().energy3YearFixedRate(userProfile, DatafromCRM3[1],DatafromCRM3[2]);
		return this;
	}
	
	public RenewalsAction energy3YearRenewalSummary(UserProfile userProfile) {
		new RenewalsPage().energy3YearRenewalSummary(userProfile, DatafromCRM3[0],DatafromCRM3[1], DatafromCRM3[2]);
		return this;
	}
	public RenewalsAction energyPlanRenewalLink() {
		new RenewalsPage().energyPlanRenewalLink();
		return new RenewalsAction();
	}

	public RenewalsAction verifyLinksinRenewalsQuote() {
		new RenewalsPage().verifyLinksinRenewalsQuote();
		return new RenewalsAction();
		
	}

	public RenewalsAction verifyLinksinDontRenew() {
		new RenewalsPage().verifyLinksinDontRenew();
		return new RenewalsAction();
		
		
	}

	public RenewalsAction energy2YearRenewalConfirmation(UserProfile userProfile) {
		new RenewalsPage().energy2YearRenewalConfirmation();
		return this;
	}

	public RenewalsAction verify3YearFixedRate(CrmUserProfile crmuserProfile, UserProfile userProfile)throws ParseException {
		SapCrmPage sapcrmpage=new SapCrmPage();
		DatafromCRM3=sapcrmpage.verify3YearFixedRate(crmuserProfile);
		return this;
	}

	public RenewalsAction energy3YearRenewalConfirmation(UserProfile userProfile) {
		new RenewalsPage().energy3YearRenewalConfirmation();
		return this;
	}
	public RenewalsAction navigateToRenewalsDue(UserProfile userProfile) {
		new RenewalsPage().navigateToRenewalsDue(userProfile);
		return this;
	}

	public RenewalsAction verify2YearFixedRate(CrmUserProfile crmuserProfile, UserProfile userProfile)throws ParseException 
			 {
		SapCrmPage sapcrmpage=new SapCrmPage();
		DatafromCRM2=sapcrmpage.verify2YearFixedRate(crmuserProfile);
		return this;
	}

	public RenewalsAction BgbloginDetails(UserProfile userProfile) {
	        LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
	        AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
	        legacyLoginPage.BgbloginUser(userProfile);
//	        accOverviewpage.verifyAccountDetails(userProfile);
//	        legacyLoginPage.loginUserDetails(userProfile);
	        return new RenewalsAction();
	    }
	
	public RenewalsAction navigaeToRenewalsDue(UserProfile userProfile) {
        new RenewalsPage().navigateToRenewalsDue(userProfile);
        return new RenewalsAction();
 }
public RenewalsAction contractSelection(String Contract) {
 new RenewalsPage().contractSelection(Contract);
 return new RenewalsAction();
}
public RenewalsAction renewalRejection() {
 new RenewalsPage().renewalRejection();
 return new RenewalsAction();
}

public RenewalsAction VerifyRenewalcontent() {
 new RenewalsPage().VerifyRenewalcontent();
 return new RenewalsAction();
}

public RenewalsAction AccountOverviewPageBanner() {
	 new RenewalsPage().AccountOverviewPageBanner();
	 return new RenewalsAction();
	}

public RenewalsAction verifyAuditTabledetails(UserProfile userProfile){
	RenewalsPage Renewal = new RenewalsPage();
	Renewal.verifyTable(userProfile);
	return this;
}
public RenewalsAction VerifyingRenewalsDuebutton(UserProfile userProfile){
	RenewalsPage Renewal = new RenewalsPage();
	Renewal.VerifyingRenewalsDuebutton(userProfile);
	return this;
}
	
	}

