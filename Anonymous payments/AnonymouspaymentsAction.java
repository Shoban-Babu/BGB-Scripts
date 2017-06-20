package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AnonymouspaymentsPage;

public class AnonymouspaymentsAction {
	LoginAction loginaction = new LoginAction();
	
	public AnonymouspaymentsAction clickonMakePaymentoption_anonymous(UserProfile userProfile){
		
		AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
		anonymouspaymentsPage.navigatetobgburl_clickonMyPaymentoption(userProfile);
		return new AnonymouspaymentsAction();
		
	}
	
public AnonymouspaymentsAction invalidpostcode_validation(UserProfile userProfile){
		
		AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
		anonymouspaymentsPage.invalidpostcode_validation(userProfile);
		return new AnonymouspaymentsAction();
		
	}
public AnonymouspaymentsAction entering_valid_accno_postcode(UserProfile userProfile){
	
	AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
	anonymouspaymentsPage.entering_valid_accno_postcode(userProfile);
	return new AnonymouspaymentsAction();
	
}

public AnonymouspaymentsAction childaccount_validtion(UserProfile userProfile){
	
	AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
	anonymouspaymentsPage.childaccount_validtion(userProfile);
	return new AnonymouspaymentsAction();
	
}

public AnonymouspaymentsAction directDebitaccount_validtion(UserProfile userProfile){
	
	AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
	anonymouspaymentsPage.directDebitaccount_validtion(userProfile);
	return new AnonymouspaymentsAction();
	
}
public AnonymouspaymentsAction paymentverification_for_lessthan_twopounds(UserProfile userProfile){
	AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
	anonymouspaymentsPage.paymentverification_for_lessthan_twopounds(userProfile);
	return new AnonymouspaymentsAction();
}

public AnonymouspaymentsAction paymentverification_for_greaterthanpayment_amount(UserProfile userProfile){
	AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
	anonymouspaymentsPage.paymentverification_for_greaterthanpayment_amount(userProfile);
	return new AnonymouspaymentsAction();
}

public AnonymouspaymentsAction confirm_payment_page_validation(UserProfile userProfile){
	AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
	anonymouspaymentsPage.confirm_payment_page_validation(userProfile);
	return new AnonymouspaymentsAction();
}

public AnonymouspaymentsAction confirm_payge_validation(UserProfile userProfile){
	AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
	anonymouspaymentsPage.confirm_page_validation(userProfile);
	//anonymouspaymentsPage.quit();
	return new AnonymouspaymentsAction();

}

public AnonymouspaymentsAction quit(){
	AnonymouspaymentsPage anonymouspaymentsPage = new AnonymouspaymentsPage();
	anonymouspaymentsPage.quit();
	return new AnonymouspaymentsAction();
	
}

}
