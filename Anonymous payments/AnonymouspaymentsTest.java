package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.AnonymouspaymentsAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;


public class AnonymouspaymentsTest extends TestBase {
	
	//TC - End to End Validation / Confirmation Page etc
	@Test(groups ={Slingshot,Regression})
	public void AnonymouspaymentsforBGB_confirmationPage_validation_e2ejourney(){
	Report.createTestLogHeader("Anonymous Payments for BGB", "Verify Payment Confirmation Page and E2E Journey");
	UserProfile userProfile = new TestDataHelper().getUserProfile("AnonymouspaymentsBGB");
	new AnonymouspaymentsAction().clickonMakePaymentoption_anonymous(userProfile)
	.entering_valid_accno_postcode(userProfile)
	.confirm_payment_page_validation(userProfile)
	.confirm_payge_validation(userProfile);	
	}
	
	//TC for Invalid Post Code Validation
	@Test(groups ={Slingshot,Regression})
	public void AnonymouspaymentsforBGB_Invalidpostcodevalidation (){
	Report.createTestLogHeader("Anonymous Payments for BGB", "Verify Invalid Postcode error messages for Anonymous payments for BGB customers");
	UserProfile userProfile = new TestDataHelper().getUserProfile("AnonymouspaymentsBGB");
	new AnonymouspaymentsAction().clickonMakePaymentoption_anonymous(userProfile)
	.invalidpostcode_validation(userProfile);
		}
	
	// TC for validating the error messages for Less than 2 Pounds.
	@Test(groups ={Slingshot,Regression})
	public void AnonymouspaymentsforBGB_lessthanTwoPoundPayment(){
	Report.createTestLogHeader("Anonymous Payments for BGB", "Verify Error Messages for Less than 2 Pounds payments");
	UserProfile userProfile = new TestDataHelper().getUserProfile("AnonymouspaymentsBGB");
	new AnonymouspaymentsAction().clickonMakePaymentoption_anonymous(userProfile)
	.entering_valid_accno_postcode(userProfile)
	//.paymentverification_for_lessthan_twopounds(userProfile)
	.confirm_payment_page_validation(userProfile)
	.confirm_payge_validation(userProfile);	
		}
	
	// TC for validating the error messages for more than the Payment Amount submitted. 	
	@Test(groups ={Slingshot,Regression})
	public void AnonymouspaymentsforBGB_greaterthanPaymentAmountValidation(){
		Report.createTestLogHeader("Anonymous Payments for BGB", "Verify the Error Messages for More than the Payment Amount");
		UserProfile userProfile = new TestDataHelper().getUserProfile("AnonymouspaymentsBGB");
		new AnonymouspaymentsAction().clickonMakePaymentoption_anonymous(userProfile)
		.entering_valid_accno_postcode(userProfile)
		.paymentverification_for_greaterthanpayment_amount(userProfile);
			}
	
	//TC - Direct Debit Validation 
		@Test(groups ={Slingshot,Regression})
		public void AnonymouspaymentsforBGB_directdebit_validation(){
		Report.createTestLogHeader("Anonymous Payments for BGB", "Direct Debit Account verification for Anonymous payments BGB customers");
		UserProfile userProfile = new TestDataHelper().getUserProfile("AnonymouspaymentsBGB");
		new AnonymouspaymentsAction().clickonMakePaymentoption_anonymous(userProfile)
		.directDebitaccount_validtion(userProfile);
		}
		
	// TC for Child Account Validation
		
		@Test(groups ={Slingshot,Regression})
		public void AnonymouspaymentsforBGB_Childcodevalidation (){
		Report.createTestLogHeader("Anonymous Payments for BGB", "Child Account verification for Anonymous payments BGB customers");
		UserProfile userProfile = new TestDataHelper().getUserProfile("AnonymouspaymentsBGB");
		new AnonymouspaymentsAction().clickonMakePaymentoption_anonymous(userProfile)
		.childaccount_validtion(userProfile);
			}
		
}
