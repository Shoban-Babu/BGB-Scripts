/**
* This covers the moving premises journey allowing a customer (logged in and anonymous) to create and action a business move.
*/
package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.AccountSummary;
import static bg.framework.app.functional.entities.FunctionalCategory.BGBRegistration;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.Smoke;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.BusinessMovesAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
* @author 292238
*
*///verifyAnonyBusinessMovingIn()

@Test(groups ={Slingshot,Smoke,BGBRegistration})       

//TC_BM_01:Verify whether the logged in customer is able to do Moving out journey a
//TC_BM_06      To verify  whether the user is able to navigate to the "Moving out "page  by clicking Business moves LHN link  in the account summary page for logged in customers

public class BusinessMovesTest extends TestBase{
                public void verifyLoggedInMovingOut(){
                Report.createTestLogHeader("Business moves-LoggedIn", "Verifies the business moves-moving out for logged in journey");
                UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesLoggedIn");
                new LoginAction()
                .bgbloginDetails(userProfile)
                .bgbVerifyLogin(userProfile)
                .clickManageAccountLink(userProfile)
                .clickBusinessMovesLink()
                .enterMovingOutdate(userProfile)
                .clickContinue()
                .verifyBillingAddressPage()
                .selectCurrentAddress()               
                .selectContinueButton()
                .verifySummaryPage()
                .checkMovingNewPremises("No"/*,userProfile*/)
                .verifyConfirmation()
                .verifyAuditEntryMovingOut(userProfile)
                .logout();
                }
                //TC_BM_02:Verify whether the logged in customer is able to do Moving out-Move In journey
                //TC_BM_03      "Verify whether NPS survey link is displayed in all pages of following scenarios.Logged in - MOVING OUT JOURNEY
                //TC_BM_04      Verify whether the "Thank you survey " is getting updated in online DB for logged in customers
                //TC_BM_15: To verify whether the Account details in the RHS are displayed correctly.
                public void verifyLoggedInMovingOutMovingIn(){
                                Report.createTestLogHeader("Business moves-LoggedIn", "Verifies the business moves-moving out moving in for logged in journey");
                    UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesLoggedIn");
                    new LoginAction()
                                .bgbloginDetails(userProfile)
                                .bgbVerifyLogin(userProfile)
                                .clickManageAccountLink(userProfile)
                                .clickBusinessMovesLink()
                                .enterMovingOutdate(userProfile)
                                .clickContinue()
                                .verifyBillingAddressPage()
                                //.selectCurrentAddress()
                                .enterDifferentAddress(userProfile, "Find")
                                .clickContinueButton()
                                .verifySummaryPage() 
                               // .submitNpsSurvey(userProfile)
                                .checkMovingNewPremises("Yes"/*,userProfile*/)
                                .verifyConfirmation()
                               // .submitNpsSurvey(userProfile)
                                .verifyThankYouSurveyPage(userProfile)
                                .verifyAuditEntryMovingOut(userProfile)
                                .logout();
                                }
                //TC_BM_09,100,101,102,103     Verify the link navigations and functionalities of "continue "and "cancel" button in the "Moving out "landing page for Logged in customers
                public void verifyCancelButtonInMovingOutJourney(){
                                Report.createTestLogHeader("Business moves-LoggedIn", "Verify the link navigations and functionalities of continue and cancel button in the Moving out landing page");
                    UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesLoggedIn");
                    new LoginAction()
                                .bgbloginDetails(userProfile)
                                .bgbVerifyLogin(userProfile)
                                .clickManageAccountLink(userProfile)
                                .clickBusinessMovesLink()
                                .enterMovingOutdate(userProfile)
                                .clickContinue()
                                .verifyBillingAddressPage()
                                .selectCurrentAddress()
                                .checkNewMovingInOption("No")
                                .clickCacelButton()
                                .logout();
                }
                
                //TC_BM_27      verify whether  the editable address panel appear When confirm address is clicked
                //TC_BM_30      To verify whether  "Edit billing address "  filed will be  blank  if the  address was provided using address finder, only the manual entered address will be prepopulated in the overlay
                //TC_BM_32      "To verify the  functionalities of the  below links in Billing address page. 1.continue "
                //TC_BM_33      "To verify the  functionalities of the  below links in Billing address page. 1.back   2.cancel"
                //TC_BM_35      Verify  whether the "Summary" screen is getting displayed by clicking continue in the "your billing address " screen.
                //TC_BM_37      verify whether user able to click on the "edit details " link in the "Moving out date "section   and able to edit the details 
                //TC_BM_38      verify whether user able to click on the "edit details " link in the" Billing address section " and able to edit the details 

                public void verifyEditAddressLink(){
                                Report.createTestLogHeader("Business moves-LoggedIn", "verifies whether  the editable address panel appear When confirm address is clicked");
                    UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesLoggedIn");
                    new LoginAction()
                                .bgbloginDetails(userProfile)
                                .bgbVerifyLogin(userProfile)
                                .clickManageAccountLink(userProfile)
                                .clickBusinessMovesLink()
                                .enterMovingOutdate(userProfile)
                                .clickContinue()
                                .enterDifferentAddress(userProfile, "Find")
                                .verifyEditAddressLink(userProfile)
                                .clickContinueButton()
                                .verifySummaryPage()
                                .verifySummaryPageEditDetails()
                                .logout();
                }
                
                //TC_BM_40      Verify the functionality of 'Request a callback' links in summary page of logged in moving out journey.
                //TC_BM_43      "Verify the functionalities  and link navigations of the below.      1.back   2.cancel "
                //TC_BM_51      verify  whether the "confirmation " screen is getting displayed by clicking "submit " button in the "summary " screen .

                public void verifyRequestACallBack_LoggedIn(){
                                Report.createTestLogHeader("Business moves-LoggedIn", "Verify the functionality of Request a callback links in summary page of logged in moving out journey");
                    UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesLoggedIn");
                    new LoginAction()
                                .bgbloginDetails(userProfile)
                                .bgbVerifyLogin(userProfile)
                                .clickManageAccountLink(userProfile)
                                .clickBusinessMovesLink()
                                .enterMovingOutdate(userProfile)
                                .clickContinue()
                                .verifyBillingAddressPage()
                                .selectCurrentAddress()               
                                .selectContinueButton()
                                .verifySummaryPage()
                                .clickRequestCallBack()
                                .checkRequestCallBack()
                                .verifyAuditEntry(userProfile)
                                .logout();
                }
                
                
// ############################################################################## Anonymous flows ############################################################################# //
                
//            TC_BM_56 Verify whether the Anonymous customer is able to do Moving out journey 
//TC_BM_58 Verify whether the Anonymous customer is able to do Moving out journey and verify the following scenario (Non SAP customer - Account number not starting with 6 and not 10 digits)
//TC_BM_60 Verify whether the "Thank you survey " is getting updated in online DB for  anonymous moving out customers
//TC_BM_61      To verify whether the "Moving premise " screen is getting displayed  when "Moving premise " link is clicked in the "Your account " page for anonymous customer
//TC_BM_63      Check whether "Moving out Your details" landing page is getting displayed when the user clicks "Moving out" link in Moving premises page
//TC_BM_65,73                Check whether "Moving details" page is getting displayed for customer while clicking "Continue" button of "Moving out -Your details" page
//TC_BM_82,83,84,86,87,90,92,104 a,b) Validate whether the user is able to proceed "Moving out" journey by navigations in home page for Anonymous customers(end to end flow) for lessthan and more than 15 Accounts
//TC_BM_105,107,108,110 Check whether the "moving out details" page displaying all fields
//TC_BM_123 Verify the functionality of below links of "Summary" Page of anonymous Moving Out customer a) Back b) Cancel
//TC_BM_92      "a)Check whether the ""Enter Moving out address""  overlay is getting displayed and the user can enter address manually by clicking ""Enter your address manually"" link for BGB Anonymous customer  of anonymous  Moving Out        //b)Verify the look and feel of Enter your address manually overlay for BGB Anonymous customer  of anonymous  Moving Out "
//TC_BM_94      Verify whether Enter Moving out address Overlay 'Cancel' link is navigated to Moving details  landing page  of anonymous  Moving Out 
//TC_BM_96      "Verify the functionality of below fields of ""Moving  details  Address confirmation"" Page           of anonymous  Moving Out              a)Continue( button)"
//TC_BM_97      "Verify the functionality of below fields of ""Moving  details  Address confirmation"" Page           of anonymous  Moving Out              a) Back b) Cancel"
//TC_BM_98      Check whether Edit Moving out address"  Overlay page is getting displayed for customer while clicking "Edit details" button of "Moving out Address confirmation " page
//TC_BM_99      Verify  whether Edit Moving out address" Overlay page details  are updated  and get reflected in the Moving Out address confirmation page  of anonymous  Moving Out 
                                
                
                public void verifyAnonyBusinessMovingOut(){
                                Report.createTestLogHeader("Business moves-Anonymous", "Verify whether the Anonymous customer is able to do Moving out journey");
                    UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesAnonymous");
                new BusinessMovesAction()
                .anonymousMovingPremisesHome()
                .clickMovingOutLink()
                .enterYourDetailsMovingOut(userProfile)
                .enterMovingOutdate(userProfile)
                .leavingPropertyDetails(userProfile)
                .verifyBillingAddressPage()
                .selectCurrentAddress()
                .verifyAnnonymousSummaryPage()
                .checkNewMovingInOption("No")
                .linkVerificationMovingOut()
                .clickConfirmAddress()
                .verifyConfirmation()
//            .verifyBusinessMoves_AnonymousCallBack(userProfile)
                .verifyAuditEntryMovingOut(userProfile)
                .verifyThankYouSurveyPage(userProfile);
                }
                
                //TC_BM_57 Verify whether the Anonymous customer is able to do Moving out-Move In journey 
                //TC_BM_59 Verify whether the Anonymous customer is able to do Moving out-Move In journey and verify the following (Non SAP customer - Account number not starting with 6 and not 10 digits)
                //TC_BM_112,122,125,127,128  Check whether "Summary page is getting displayed for customer by selecting the "Add new address" radio button and  by clicking "Continue" button for different address selected address confirmation page  of anonymous  Moving Out 
                //TC_BM_129,131,132   Check whether "Confirmation"  Page is getting displayed in Summary Page when the user Select "Are you moving  into new business premises?"as yes and then complete the   "your new property details"  with details and clicking the "Submit"  Button.

                public void verifyAnonyBusinessMovingOutMovingIn(){
                                Report.createTestLogHeader("Business moves-Anonymous", "Verify whether the Anonymous customer is able to do Moving out Move In journey");
                    UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesAnonymous");
                new BusinessMovesAction()
                .anonymousMovingPremisesHome()
                .clickMovingOutLink()
                .enterYourDetailsMovingOut(userProfile)
                .enterMovingOutdate(userProfile)
                .leavingPropertyDetails(userProfile)
                .verifyBillingAddressPage()
                .selectCurrentAddress()
                .verifyAnnonymousSummaryPage()
                .checkNewMovingInOption("Yes")
                .leavingPropertyDetails(userProfile)
                .verifyConfirmation()
                .verifyAuditEntryMovingOut(userProfile);
}
                
                //TC_BM_128 Verify the functionality of below fields of "Enter new business address" a)Edit address
                                //TC_BM_136 Verify whether anonymous customer is able to do Moving In journey and verify the following, 1. Confirmation mail should be triggered 
                                //2. Audit entry should be made on submission
                                //TC_BM_149    Verify whether Moving details page is displayed for Anonymous Moving - In journey when continue is clicked in your details page.
                                //TC_BM_151    Verify whether the details in RHN in moving details page are displayed as per entered in your details in anonymous moving in journey.
                                //TC_BM_153,154,155,156           Verify whether address is displayed in Moving details page on clicking confirm address.
                                //TC_BM_165,166,167,168Verify whether Billing address page is getting displayed on clicking Continue in Moving details page in Moving - In journey.
                                //TC_BM_185    Verify whether confirmation page is displayed on clicking submit in summary page of Moving In journey.
                                //TC_BM_171    Verify whether related search results are displayed when valid post code is entered and on clicking 'find address'.
                                //TC_BM_173,174,175,176,177  Verify the functionality of Edit address section displayed on clicking 'Edit details' in Billing address page in Moving-in journey.
                                //TC_BM_185    Verify whether the details in RHN in confirmation page is displayed properly, as entered in your details and moving details pages in Moving In journey.
                                //TC_BM_188    Verify the promo links navigations in confirmation page of Moving In journey.
                 
                public void verifyAnonyBusinessMovingIn(){
                                Report.createTestLogHeader("Business moves-Anonymous", "Verifies the anonymous business moveing in journey");
                                UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesAnonymous");
                                new BusinessMovesAction()
                                .anonymousMovingPremisesHome()
                                .clickMovingInLink()
                                .enterYourDetailsMovingIn(userProfile)
                                .leavingPropertyDetails(userProfile)
                                .enterDifferentAddress(userProfile)
                                .verifyAnnonymousSummaryPage()
                                .clickConfirmAddress()
                                .verifyConfirmation()
                                .verifyPodsInConfirmationPage()
                                .verifyAuditEntryMovingOut(userProfile);
                                }
                                
                                //TC_BM_158    Verify whether error message is displayed on clicking continue in moving details page when address is not Identified with BGB.
                                //TC_BM_161    "Verify the following link navigations in error message displayed  when address is not Identified with BGB.       1. Back 2. Cancel
                                //TC_BM_162    Verify the functionality of request a call back link in RHN in error message displayed  when address is not Identified with BGB.

                                public void verifyAnonyBusinessErrorMovingIn(){
                                                Report.createTestLogHeader("Business moves-Anonymous", "Verify whether error message is displayed on clicking continue in moving details page when address is not Identified with BGB");
                                    UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesAnonymous");
                                new BusinessMovesAction()
                                .anonymousMovingPremisesHome()
                                .clickMovingInLink()
                                .enterYourDetailsMovingIn(userProfile)
//                            .enterMovingInDate()
                                .verifyEditAddressLink(userProfile)         
                                .clickContinueButton()
                                .verifyErrorPage()
                                .checkRequestCallBack();
                                
                                }
                                
                                //TC_BM_75      Verify the functionality of 'Request a callback' links in your details page of anonymous moving out journey.
                                public void verifyRequestACallBack_Anonymous(){
                                                Report.createTestLogHeader("Business moves-Anonymous", "Verify the functionality of Request a callback in anonymous moving out journey");
                                    UserProfile userProfile = new TestDataHelper().getUserProfile("BusinessMovesAnonymous");
                                    new BusinessMovesAction()
                                                .verifyBusinessMoves_AnonymousCallBack(userProfile);
                                }
}


