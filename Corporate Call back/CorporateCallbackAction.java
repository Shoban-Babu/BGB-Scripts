/**
 * 
 */
package bg.framework.app.functional.action.tetris;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.tetris.CorporateCallback_GasElecPage;
import bg.framework.app.functional.page.tetris.CorporateContactUsPage;

/**
 * @author 292238
 *
 */
public class CorporateCallbackAction {
	public CorporateCallbackAction verifyAndClick(String strLink){
		CorporateCallback_GasElecPage corporateCallbackPage = new CorporateCallback_GasElecPage();
		corporateCallbackPage.gotoGasAndElecMenu();
		corporateCallbackPage.openLink(strLink);
		return this;
	}
	public CorporateCallbackAction verifyAndClickGasElec(String strLink){
		CorporateCallback_GasElecPage corporateCallbackPage = new CorporateCallback_GasElecPage();
		corporateCallbackPage.gotoGasAndElecMenu();		
		return this;
	}
	public CorporateCallbackAction verifyDB(UserProfile userProfile,String strLink){
		CorporateCallback_GasElecPage corporateCallbackPage = new CorporateCallback_GasElecPage();
		corporateCallbackPage.verifyDB(userProfile,strLink);
		return this;
	}
	public CorporateCallbackAction verifyCorporateFixedPriceAction(UserProfile userProfile,String strLink){
		CorporateCallback_GasElecPage corporateCallbackPage = new CorporateCallback_GasElecPage();
		corporateCallbackPage.verifyFixedPricePage(userProfile,strLink);		
		return this;	
	}
	public CorporateCallbackAction verifyCallBackAction(UserProfile userProfile,String strLink){
		CorporateCallback_GasElecPage corporateCallbackPage = new CorporateCallback_GasElecPage();		
		corporateCallbackPage.verifyCallBackPage(userProfile,strLink);			
		return this;	
	}
	public CorporateCallbackAction verifyAndClickSmarterWorking_Insight(String strLink){
		CorporateCallback_GasElecPage corporateCallbackPage = new CorporateCallback_GasElecPage();
		corporateCallbackPage.gotoSmartWorkingMegaMenu();
		corporateCallbackPage.openSmarterWorkingLink(strLink,0);
		return this;
	}	
	public CorporateCallbackAction verifyAndClickSmarterWorking_CaseStudies(String strLink){
		CorporateCallback_GasElecPage corporateCallbackPage = new CorporateCallback_GasElecPage();
		corporateCallbackPage.gotoSmartWorkingMegaMenu();
		corporateCallbackPage.openSmarterWorkingLink(strLink,1);
		return this;
	}
	public CorporateCallbackAction validateFirstnameFields(UserProfile userProfile){
		CorporateCallback_GasElecPage corporateCallbackPage = new CorporateCallback_GasElecPage();
		corporateCallbackPage.clickCallBackPage(userProfile);
		return this;
	}
	
}
