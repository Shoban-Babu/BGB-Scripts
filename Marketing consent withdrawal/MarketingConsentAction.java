/**
 * 
 */
package bg.framework.app.functional.action.tetris;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.tetris.CorporateCallback_GasElecPage;
import bg.framework.app.functional.page.tetris.MarketingConsentPage;

/**
 * @author 292238
 *
 */
public class MarketingConsentAction {
	public MarketingConsentAction openMarketingConsentpage(UserProfile userProfile){
		MarketingConsentPage marketingConsentPage = new MarketingConsentPage();
		marketingConsentPage.openMarketingConsentPage();
		return this;	
	}
	public MarketingConsentAction verifyAndEntervalues(UserProfile userProfile){
		MarketingConsentPage marketingConsentPage = new MarketingConsentPage();
		marketingConsentPage.enterValuesInMarketingConsentPage(userProfile);
		return this;	
	}
	public MarketingConsentAction verifyThankYouPage(UserProfile userProfile){
		MarketingConsentPage marketingConsentPage = new MarketingConsentPage();
		marketingConsentPage.verifyThankYouPage();
		return this;	
	}
	public MarketingConsentAction verifyDBDetails(UserProfile userProfile){
		MarketingConsentPage marketingConsentPage = new MarketingConsentPage();
		marketingConsentPage.verifyDBDetails(userProfile);
		return this;	
	}
	public MarketingConsentAction validateFields(UserProfile userProfile){
		MarketingConsentPage marketingConsentPage = new MarketingConsentPage();
		marketingConsentPage.validateFieldsInMisc(userProfile);
		return this;
	}
}
