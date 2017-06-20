/**
 * @author 208070
 *
 */
package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.BGBRegistration;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.Smoke;

import java.util.Properties;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.AccountSummaryAction;
import bg.framework.app.functional.action.Slingshot.PaymentsAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

public class PaymentsTest extends TestBase{
	private final static String FILE_NAME = "resources/Slingshot/Payments.Properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME).load();
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void verifyPaymentsLink(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile)
		//.verifyCustomerAccountDetails(userProfile)
		//.verifyDataVerification(userProfile)
		.clickManageAccountLink(userProfile)
		//.auditVerificationOfEmail(userProfile)
		//.verifyLoginTimeStamp(userProfile)
		.navigatetoPayments(userProfile)
		//.verifyLinks()
		.navigatetoMakeAPayment()
		.paymentDetails(userProfile)
		.logOut();
	    
	    
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void ErrorValidation(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Error Validation");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile)
//		.verifyCustomerAccountDetails(userProfile)
//		.verifyDataVerification(userProfile)
		.clickManageAccountLink(userProfile)
		.auditVerificationOfEmail(userProfile)
		.verifyLoginTimeStamp(userProfile)
		.navigatetoPayments(userProfile)
		.navigatetoMakeAPayment()
		.errorValidation(userProfile)
		.logOut();
	    
	    
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  TwoCardperAccount(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Two Card per Account");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    int increment=1;
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
//		.verifyCustomerAccountDetails(userProfile)
//		.verifyDataVerification(userProfile)
		do{
			new AccountSummaryAction()
		.clickManageAccountLink(userProfile)
		.auditVerificationOfEmail(userProfile)
		.verifyLoginTimeStamp(userProfile)
		.navigatetoPayments(userProfile);
		if(increment==3)
			new PaymentsAction().navigatetoMakeAPaymentError();
		else
		{
			new PaymentsAction().navigatetoMakeAPayment();
			new PaymentsAction().TwoCardlimit(increment, userProfile);
		}
			increment++;
		}while(increment<=3);
		new PaymentsAction()
		.logOut();
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  AddCardDetails(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Manage Payment Cards Section");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginDetails(userProfile);
		//.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .clickManageAccountLink(userProfile);
		//.navigatetoPayments();
	    new PaymentsAction()
	    .navigateToManageYourPaymentsCards()
	    .cardStorageFunctionality(userProfile)
	    //.auditentryverification_for_managecards(userProfile, pageProperties.getProperty("Payments.AddCardDBMessage"))
	    .logOut(); //.verifyAndDeleteCardsInManageTable()
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  PaymentViaNewCardDetails_SaveCard(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Payment via New card details");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    String SaveCard = "Yes";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .clickManageAccountLink(userProfile)
	    //.clickManageAccountLinknew(userProfile)
		.navigatetoPayments(userProfile);
	    new PaymentsAction()
	    //.navigatetoMakeAPayment()
	    .VerifypaymentViaExistingCardNew(userProfile)	    
	    .logOut();
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  PaymentViaNewCardDetails_CardNotSaved(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Payment via New card details - Card Not Saved for Future");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    String SaveCard = "No";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .clickManageAccountLink(userProfile)
	    .navigatetoPayments(userProfile);
	    new PaymentsAction()
	    //.navigatetoMakeAPayment()
	    .paymentViaNewCard_NotClickSaveButton(userProfile)
	    //.auditentryverification_for_managecards(userProfile, pageProperties.getProperty("Payments.EditCardDBMessage"))//check
	    .logOut();
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  DeleteCardDetails(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Delete Cards Details");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
		.clickManageAccountLink(userProfile)
		.navigatetoPayments(userProfile);
	    new PaymentsAction()
	    .navigateToManageYourPaymentsCards()
	    .verifyAndDeleteCardsInManageTable();
}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  EditCardDetails(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Edit Cards Details");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .clickManageAccountLink(userProfile);
		//.navigatetoPayments();
	    new PaymentsAction()
	    .navigateToManageYourPaymentsCards()
	    .verifyAndEditCardsInManageTable(userProfile);
	   // .auditentryverification_for_managecards(userProfile, pageProperties.getProperty("Payments.EditCardDBMessage"));
}
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  PaymentViaStoreCard(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Payment via Stored Card");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    String SaveCard = "No";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .clickManageAccountLink(userProfile)
	    //.clickManageAccountLinknew(userProfile)
		.navigatetoPayments(userProfile);
	    new PaymentsAction()
	    //.navigatetoMakeAPayment()
	    .VerifypaymentViaExistingCardNew(userProfile)
	    //.auditentryverification_for_managecards(userProfile, pageProperties.getProperty("Payments.EditCardDBMessage"))//check
	    .logOut();
	}
	
	//June -9th Release --> Verify the Incative accounts
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifyInActiveAccounts_DebitAccount(){
	 	Report.createTestLogHeader("Payments", "Verify the Inactive Accounts - Verify it is Debit or credit");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    String SaveCard = "No";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .VerifycustomerReferenceNumber()
	    .clickManageAccountLink(userProfile);
		new PaymentsAction()
	    .VerifyAccountSummaryPage(userProfile)
	    .logOut();
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifyInActiveAccounts_DebitDDAccount(){
	 	Report.createTestLogHeader("Payments", "Verify the Inactive Accounts - Verify the Debit DD customer");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments1");
	    String SaveCard = "No";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .VerifycustomerReferenceNumber()
	    .clickManageAccountLink(userProfile);
		new PaymentsAction()
	    .VerifyPaymentforDebitDDAccount()
	    .logOut();
	}
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifyInActiveAccounts_zeroBalanceAccount(){
	 	Report.createTestLogHeader("Payments", "Verify the Inactive Accounts - Verify the Debit DD customer");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    String SaveCard = "No";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .VerifycustomerReferenceNumber()
	    .clickManageAccountLink(userProfile);
		new PaymentsAction()
	    .VerifyPaymentforZeroBalanceAccount()
	    .logOut();
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifyInActiveAccounts_CreditAccount(){
	 	Report.createTestLogHeader("Payments", "Verify the Inactive Accounts - Verify it is Debit or credit");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    String SaveCard = "No";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .VerifycustomerReferenceNumber()
	    .clickManageAccountLink(userProfile);
		new PaymentsAction()
	    .VerifyAccountSummaryPage_forCreditAccount()
	    .logOut();
	}
	
/////////////////////////////////////////////////  July 7th Release //////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifyErrorMessage_InValidPaymentamount(){
	 	Report.createTestLogHeader("Payments", "Payments Functionality - Payment via New card details - Card Not Saved for Future");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    String SaveCard = "No";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .clickManageAccountLink(userProfile)
	    .navigatetoPayments(userProfile);
	    new PaymentsAction()
	    .navigatetoMakeAPayment()
	    .paymentViaNewCard_InValidAmount()
	    .logOut();
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifyActiveAccounts_DebitAccount(){
	 	Report.createTestLogHeader("Payments", "Verify the Inactive Accounts - Verify it is Debit or credit");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    String SaveCard = "No";
	    new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile);
	    new AccountSummaryAction()
	    .VerifycustomerReferenceNumber()
	    .clickManageAccountLink(userProfile);
		new PaymentsAction()
	    .VerifyAccountSummaryPage(userProfile)
	    .logOut();
	}

///////////////////////////////////////////////////// July 28th Release ///////////////////////////////////////////////////////////////////////
/////////////////////////////////Verify the SetupDD and the Amend DD Customer//////////////////////////////////////////////////////////////////	
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifySetup_DebitAccount(){
	 	Report.createTestLogHeader("Payments", "Verify to Setup the DD Customer");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginSetupDDUser(userProfile);
		new PaymentsAction()
	    .verifySetupDD()
	    .verifySummaryPage()
		.logOut();
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifyAmend_DebitAccount(){
	 	Report.createTestLogHeader("Payments", "Verify to ammend the DD account");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginSetupDDUser(userProfile);
		new PaymentsAction()
	    .verifyamendDD(userProfile)
	    .verifySummaryPage()
		.logOut();
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  VerifyErrormessage_InvalidAccountNumber(){
	 	Report.createTestLogHeader("Payments", "Verify Error message for the Invalid Account");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginSetupDDUser(userProfile);
		new PaymentsAction()
	    .verifyAccountsummary()
		.logOut();
	}

//////////////////////////////////////////// November 7th Release 2016  ///////////////////////////////////////////////////////////////// 
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  payment_link_search_anonymous(){
	 	Report.createTestLogHeader("Payments", "Verify Navigation to Make a Payment Link using  Search Bar - Anonymous");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new PaymentsAction()
	    .open_bgburl()
	    .paymentlink_searchbar();
	}
	
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
	public void  payment_link_search_logedin(){
	 	Report.createTestLogHeader("Payments", "Verify Navigation to Make a Payment Link using  Search Bar - Logged In");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("Payments");
	    new LoginAction()
		.BgbloginDetails(userProfile);
	    new PaymentsAction()
	    .paymentlink_searchbar();
	}
	
}
