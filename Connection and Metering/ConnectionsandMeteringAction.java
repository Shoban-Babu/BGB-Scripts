/**
 * 
 */
package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.BusinessMovesPage;
import bg.framework.app.functional.page.Slingshot.ConnectionsandMeteringPage;

/**
 * @author 208070
 *
 */
public class ConnectionsandMeteringAction {
	
	public ConnectionsandMeteringAction navigatetoConnectionsandMetering() {
		new ConnectionsandMeteringPage().navigatetoConnectionsandMetering();
		return new ConnectionsandMeteringAction();	
		
	}
	public ConnectionsandMeteringAction navigatetoHome() {
		new ConnectionsandMeteringPage().navigatetoHome();
		return new ConnectionsandMeteringAction();	
		
	}
	
	
	
	public ConnectionsandMeteringAction validateCMPageAnonymous(UserProfile userProfile,String strLink, String WR,String Connection,String Callback) {
		new ConnectionsandMeteringPage().validateCMPageAnonymous(userProfile, strLink, WR,Connection,Callback);
		return new ConnectionsandMeteringAction();	
		
	}
	public ConnectionsandMeteringAction validateCMPageLoggedIn(String LoggedIn, UserProfile userProfile,String strLink, String WR,String Callback) {
		new ConnectionsandMeteringPage().validateCMPageLoggedIn(LoggedIn, userProfile, strLink, WR,Callback);
		return new ConnectionsandMeteringAction();	
		
	}
	
	public ConnectionsandMeteringAction verifyLinks() {
		new ConnectionsandMeteringPage().verifyLinks();
		return new ConnectionsandMeteringAction();	
		
	}
	
	public ConnectionsandMeteringAction verifyErrors(UserProfile userProfile, String string) {
		new ConnectionsandMeteringPage().verifyErrors(userProfile, string);
		return new ConnectionsandMeteringAction();	
		
	}
	
	public ConnectionsandMeteringAction verifyAndEnterCMPage(UserProfile userProfile,String Callback) {
		new ConnectionsandMeteringPage().verifyAndEnterCMPage(userProfile,Callback);
		return new ConnectionsandMeteringAction();	
	}
	
	public ConnectionsandMeteringAction verifyAndEnterrequestCMPage(String UserLogin, UserProfile userProfile, String string, String WR) {
	//	new ConnectionsandMeteringPage().verifyAndEnterrequestCMPage(UserLogin, userProfile, string, WR);
		return new ConnectionsandMeteringAction();	
	}
	
	public ConnectionsandMeteringAction verifyErrorinCM(UserProfile userProfile) {
		new ConnectionsandMeteringPage().verifyErrorinCM(userProfile);
		return new ConnectionsandMeteringAction();	
		
	}
	
	public ConnectionsandMeteringAction verifyTelephone(UserProfile userProfile) {
		new ConnectionsandMeteringPage().verifyTelephone(userProfile);
		return new ConnectionsandMeteringAction();	
	}
	
	public ConnectionsandMeteringAction browserBack() {
		new ConnectionsandMeteringPage().browserBack();
		return new ConnectionsandMeteringAction();	
		
	}
	public ConnectionsandMeteringAction ValidateinCRM() {
		new ConnectionsandMeteringPage().ValidateinCRM();
		return new ConnectionsandMeteringAction();	
		
	}
	public ConnectionsandMeteringAction logout() {
		ConnectionsandMeteringPage ConnectionsandMeteringPage = new ConnectionsandMeteringPage();
		ConnectionsandMeteringPage.logout();
		return this;
		
	}

	}
