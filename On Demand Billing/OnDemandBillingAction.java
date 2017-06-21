/**
 * 
 */
package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.page.Slingshot.OnDemandBillingPage;
import bg.framework.app.functional.page.Slingshot.SubmitMeterReadPage;

/**
 * @author 292238
 *
 */
public class OnDemandBillingAction {
	public OnDemandBillingAction verifyODBWithISU(SMRAccountDetails smrProfile){
		new  OnDemandBillingPage().verifyODBWithISU(smrProfile);
		return this;
	}
	public OnDemandBillingAction verifyODB_CollectiveAccount(SMRAccountDetails smrProfile){
		new  OnDemandBillingPage().verifyODB_CollectiveAccount(smrProfile);
		return this;
	}
	public OnDemandBillingAction verifyMeterReadConfirmation(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyGasConfirmation();
		return this;
	}
	public OnDemandBillingAction verifyLinkNavigations(SMRAccountDetails smrProfile){
		new  OnDemandBillingPage().verifyLinkNavigations(smrProfile);
		return new OnDemandBillingAction();
	}
	public OnDemandBillingAction verifyMailSentAuditConfirmation(SMRAccountDetails smrProfile){
		new  OnDemandBillingPage().verifyMailSentConfirmation(smrProfile);
		return new OnDemandBillingAction();
	}
	public OnDemandBillingAction verifyPayNewBalanceLink(){
		new  OnDemandBillingPage().verifyPayNewBalanceLink();
		return new OnDemandBillingAction();
	}
	public OnDemandBillingAction verifyPodLink(){
		new  OnDemandBillingPage().verifyPodLink();
		return new OnDemandBillingAction();
	}
	
	
}
