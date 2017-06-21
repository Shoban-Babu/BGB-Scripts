/**
 * 
 */
package bg.framework.app.functional.action.tetris;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.tetris.LargeBusinessElectricityCallBackPage;

/**
 * @author 292238
 *
 */
public class LargeBusinessElectricityCallBackAction {
	public LargeBusinessElectricityCallBackAction openLargeBusinesspage(UserProfile userProfile){
		LargeBusinessElectricityCallBackPage largeElecBusinessPage = new LargeBusinessElectricityCallBackPage();
		largeElecBusinessPage.openLargeBusinessPage();
		return this;	
	}
	public LargeBusinessElectricityCallBackAction verifyAndEntervalues(UserProfile userProfile){
		LargeBusinessElectricityCallBackPage largeElecBusinessPage = new LargeBusinessElectricityCallBackPage();
		largeElecBusinessPage.enterValuesInLargeBusinessPage(userProfile);
		return this;	
	}
	public LargeBusinessElectricityCallBackAction verifyThankYouPage(UserProfile userProfile){
		LargeBusinessElectricityCallBackPage largeElecBusinessPage = new LargeBusinessElectricityCallBackPage();
		largeElecBusinessPage.verifyThankYouPage();
		return this;	
	}
	public LargeBusinessElectricityCallBackAction verifyDBDetails(UserProfile userProfile){
		LargeBusinessElectricityCallBackPage largeElecBusinessPage = new LargeBusinessElectricityCallBackPage();
		largeElecBusinessPage.verifyDBDetails(userProfile);
		return this;	
	}
}
