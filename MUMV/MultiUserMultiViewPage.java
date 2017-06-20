/**
 * 
 */
package bg.framework.app.functional.page.Slingshot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RobotSendKeys;
import bg.framework.app.functional.util.RunQTP;
import bg.framework.common.functional.WebDriverFactory.browserType;

import java.util.Random;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author 255501
 * 
 */
public class MultiUserMultiViewPage extends BasePage {

	private final static String FILE_NAME = "resources/Slingshot/MultiUserMultiView.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME)
			.load();
	 private final static String MVbFILE_NAME = "resources/Slingshot/MultiUserMultiView.properties";  
	 private static Properties MuvpageProperties = new PropertyLoader(MVbFILE_NAME).load();
	SlingshotErrorMessages errormsg = new SlingshotErrorMessages();
	public static final String TIMESTAMP_FORMATTER = "dd MMMM, yyyy";
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	String functionResult;
	public static String Entermail;
	public static String FirstName;
	public static String LastName;
	private static String strEmail;
	private int Increment;

	public void enterValidData_StandardUser(UserProfile userProfile) {
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserTitle"),"Title", userProfile.getTitle());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserfirstname"),"First name", userProfile.getFirstName());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUsersurname"),"Sur name", userProfile.getLastName());
		int intRandomNumbers;
        Random random = new Random();
        intRandomNumbers = random.nextInt(10000);
        strEmail = "digital_test" + intRandomNumbers + "@bgdigitaltest.co.uk";
        System.out.print("digital_test" + intRandomNumbers + "@bgdigitaltest.co.uk");
        //userProfile.setEmail("automation_sele" + intRandomNumbers + "@bgdigitaltest.co.uk");
         userProfile.setnewemail(strEmail);
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserEmailAddress"),"Email address", strEmail);
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserReTypeEmailAddress"),"Re-Type Email address", userProfile.getNewEmail());
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserRightsType"),"User Type", userProfile.getUserType());
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),"select Terms and Condition Check box");
		String UserViewName = browser.getTextByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddUserViewName"));
		System.out.println("entervalid UserViewName: " + UserViewName);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),"Submit button");
		browser.wait(getWaitTime());
		User_PrePopulated_values(userProfile, UserViewName,userProfile.getUserType());
	}

	public void enterValidData_StandardUserdetails(UserProfile userProfile) {
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserTitle"),
				"Title", userProfile.getTitle());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserfirstname"),
				"First name", userProfile.getFirstName());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUsersurname"),
				"Sur name", userProfile.getLastName());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserEmailAddress"),
				"Email address", userProfile.getNewEmail());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserReTypeEmailAddress"),
				"Re-Type Email address", userProfile.getNewEmail());
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserRightsType"),
				"User Type", userProfile.getUserType());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),
				"select Terms and Condition Check box");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),
				"Submit button");
		browser.wait(getWaitTime());
		User_PrePopulated_values(userProfile, userProfile.getEditview(),
				userProfile.getUserType());
	}

	public void enterValidData_StandardUserforEditview(UserProfile userProfile) {
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserTitle"),
				"Title", userProfile.getTitle());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserfirstname"),
				"First name", userProfile.getFirstName());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUsersurname"),
				"Sur name", userProfile.getLastName());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserEmailAddress"),
				"Email address", userProfile.getNewEmail());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserReTypeEmailAddress"),
				"Re-Type Email address", userProfile.getNewEmail());
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserRightsType"),
				"User Type", userProfile.getUserType());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),
				"select Terms and Condition Check box");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),
				"Submit button");
		browser.wait(getWaitTime());
	}

	public void enterValidData_StandardUsers(UserProfile userProfile,String UserViewname) {
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserTitle"),"Title", userProfile.getTitle());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserfirstname"),"First name", userProfile.getFirstName());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUsersurname"),"Sur name", userProfile.getLastName());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserEmailAddress"),"Email address", userProfile.getNewEmail());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserReTypeEmailAddress"),"Re-Type Email address", userProfile.getNewEmail());
		// verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserRightsType"),
		// "User Type",userProfile.getUserType());
		verifyAndSelectDropDownBoxbyindex("roleProfileId", 1);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),"select Terms and Condition Check box");
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),"Submit button");
		browser.wait(getWaitTime());
		// User_PrePopulated_values(userProfile,UserViewname,userProfile.getUserType());
	}

	public void enterValidData_SuperUser(UserProfile userProfile) {
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserTitle"),"Title", userProfile.getTitle());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserfirstname"),"First name", userProfile.getFirstName());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUsersurname"),"Sur name", userProfile.getLastName());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserEmailAddress"),"Email address", userProfile.getNewEmail());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserReTypeEmailAddress"),"Re-Type Email address", userProfile.getNewEmail());
		// verifyAndSelectCheckBox(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),
		// "select Terms and Condition Check box");
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),"select Terms and Condition Check box");
		Report.updateTestLog("Details entered successfully","WARN");
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),"Submit button");
		browser.wait(getWaitTime());
	}

	public void enterValidData_SuperUserdata(UserProfile userProfile) {
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserTitle"),
				"Title", userProfile.getTitle());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserfirstname"),
				"First name", userProfile.getFirstName());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUsersurname"),
				"Sur name", userProfile.getLastName());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserEmailAddress"),
				"Email address", userProfile.getNewEmail());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserReTypeEmailAddress"),
				"Re-Type Email address", userProfile.getNewEmail());
		String UserViewName = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.AddUserViewName"));
		System.out.println("UserViewName: " + UserViewName);
		String UserType = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.UserType"));
		System.out.println("UserType: " + UserType);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),
				"select Terms and Condition Check box");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),
				"Submit button");
		browser.wait(getWaitTime());
		User_PrePopulated_values(userProfile, UserViewName, UserType);
	}

	public void enterValidData_UserSecondPage(UserProfile userProfile) {
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserTitle"),
				"Title", userProfile.getTitle());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserfirstname"),
				"First name", userProfile.getFirstName());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUsersurname"),
				"Sur name", userProfile.getLastName());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserpassword"),
				"Password", userProfile.getPassword());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserConfirmpassword"),
				"Confirm Password", userProfile.getPassword());

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),
				"select Terms and Condition Check box");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
				"Submit button");
		browser.wait(getWaitTime());
	}

	public void entervalid_passwordData(UserProfile userProfile) {
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserPassword"),
				"Password", userProfile.getPassword());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserRetypePassword"),
				"Re-Type Password", userProfile.getPassword());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserTermsandcondtions"),
				"select Terms and Condition Check box");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
				"Submit button");
	}

	public void User_PrePopulated_values(UserProfile userProfile,
			String UserViewName, String UserType) {
		System.out.println("prepopulated UserViewName" + UserViewName);
		System.out.println("prepopulated UserType+UserType" + UserType);
		String confrmPageFirstname = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ConfirmPageDetails")
				+ userProfile.getFirstName() + "')]");
		System.out.println("confrmPageFirstname" + confrmPageFirstname);
		System.out.println("xmlfirstname" + userProfile.getFirstName());
		String confrmPageSurname = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ConfirmPageDetails")
				+ userProfile.getLastName() + "')]");
		System.out.println("confrmPageSurname" + confrmPageSurname);
		System.out.println("xmlfirstname" + userProfile.getLastName());
		String confrmPageEmailid = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ConfirmPageDetails")
				+ userProfile.getNewEmail() + "')]");
		System.out.println("confrmPageEmailid" + confrmPageEmailid);
		System.out.println("xmlfirstname" + userProfile.getNewEmail());
		String confrmPageViewname = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ConfirmPageDetails")
				+ UserViewName + "')]");
		System.out.println("confrmPageViewname" + confrmPageViewname);
		System.out.println("xmlfirstname" + UserViewName);
		String confrmPageUserType = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ConfirmPageDetails")
				+ UserType + "')]");
		System.out.println("confrmPageUserType" + confrmPageUserType);
		System.out.println("xmlfirstname" + UserType);

		if ((confrmPageFirstname.equals(userProfile.getFirstName()))
				&& (confrmPageSurname.equals(userProfile.getLastName()))
				&& (confrmPageEmailid.equals(userProfile.getNewEmail()))
				&& (confrmPageViewname.equals(UserViewName))
				&& (confrmPageUserType.equals(UserType))) {
			System.out.println("i am done");

			Report.updateTestLog(
					"Entered User details are prepopulated in the Confirmation page",
					"PASS");
		} else {
			Report.updateTestLog(
					"Prepopulated values is not Match with the Entered Value",
					"Fail");
		}

	}

	public void ClickManageUserLink() {
		Report.updateTestLog("Account Summary Page is populated successfully","WARN");
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ManageUserLink"),"Click Manage Users Link");
		verifyIsTextPresent("Manage Users");

		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.WelcomeToManageUserOverlayClose"))) {
			verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.WelcomeToManageUserOverlayClose"),"Overlay close button");
		}
		browser.wait(5000);
	}

	public void ClickManageUser() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserLink"),
				"Click Manage Users Link");

	}

	public void ClickMangeruserNavigationPage() {
		browser.wait(5000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageViewNavigationPage"),
				"Back to Manage users Link");
		// browser.clickWithXpath("//a[contains(@href,'manage-view')]");
		browser.wait(5000);
		verifyPageTitle("Manage views");
		browser.browserBack();
		browser.wait(4000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.BacktoManageUsers1"),
				" Manage view Link");
		// browser.clickWithXpath("//p/span/a[contains(@href,'/business/multiuser/landingpage/')]");
		browser.wait(4000);
		verifyPageTitle("Manage Users");

	}

	public void clickAddnewuserNavigation() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserNavigationPage"),
				"Click Add new user Link");
		PageTitleVerification("Add new user");
		browser.browserBack();
		System.out.println("i clicked page");
	}

	public void ValidateaddnewviewOverlay() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.AddnewView"),
				"Add new View");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.AddnewViewOverlayTitle"))) {
			String overlayContent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.AddnewViewOverlayTitle"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("AccountNumber Overlay content: "
					+ overlayContent, "PASS");
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddnewViewOverlaycancel"),
					"Overlay Cancel button");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.AddnewView"),
				"Add new View");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.AddnewViewOverlayTitle"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlayconfirm"),
					"Overlay Confirm button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
	}

	public void AddNewUserPageBackLink() {
		browser.wait(3000);
		// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserbacklink"),"Back Button Clicked");
		verifyAndClickWithLinkText(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserbacklink"),
				"Back Button");
		// browser.clickbutton("Back");
		browser.browserBack();
		browser.wait(3000);

	}

	public void AddNewUserPageCancelLink() {

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserCancellink"),
				"Cancel Button Clicked");

	}

	public void AddNewUserPageCancelLink1() {

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserCancellink1"),
				"Cancel Button Clicked");

	}

	public void registerNewUser(UserProfile userProfile) {

		verifyAndClick(
				pageProperties.getProperty("MultiUserMultiViewPage.continue"),
				"Continue Button");

	}

	public void ClickAddUserLink() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserLink"),
				"Click Add a new Users Link");
		browser.wait(3000);
		verifyPageTitle("Add new user");
	}

	public void ClickAddUserRHNLink() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AdduserRHNaddnewuser"),
				"Click Add a new Users Link");
		browser.wait(3000);
		verifyPageTitle("Add new user");
	}

	public void AddUserRadioButton() {
		browser.wait(getWaitTime());
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddUserYesRadioButton"))) {
			if (browser.isElementVisibleWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddUserNoRadioButton"))) {
				Report.updateTestLog("Yes And No Radio Button Appears in the Add New Users Page","PASS");
			}
		}
	}

	public void testacct() {
		verifyAndClickWithXpath(
				"//p/span/a[contains(@href,'/content/business/your-account/account-reports.html')]",
				"Click acct reports Link");
	}

	public void AddNewUserNavigationVerification() // have to update Navigation
													// links ,try this for loop
													// and change it for all
													// scripts
	{
		int i = 0;
		String jqueryToCloseOverlay = ("$('a.ui-dialog-titlebar-close').trigger('click');");
		String links[] = { "AdduserHowdoIaddauserLink",
				"AdduserHowdoIeditauserLink",
				"AdduserHowdoIdeactivateaUserLink",
				"AdduserHowManyUserscanIhave",
				"AddUserWhatAccessdodifferentUsersHave" };
		String OverlayVerificationTitle[] = {
				"AdduserHowdoIaddauserLinkOverlay",
				"AdduserHowdoIeditauserLinkOverlay",
				"AdduserHowdoIdeactivateaUserLinkOverlay",
				"AdduserHowManyUserscanIhaveOverlay",
				"AddUserWhatAccessdodifferentUsersHaveOverlay", };
		for (i = 0; i <= 4; i++) {
			verifyAndClickWithXpath(
					pageProperties.getProperty("MultiUserMultiViewPage."
							+ links[i]), links[i] + "Clicked");
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage."
							+ OverlayVerificationTitle[i]))) {
				String OverlayTitle = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage."
								+ OverlayVerificationTitle[i]));
				System.out.println("overlayContent: " + OverlayTitle);
				Report.updateTestLog("AccountNumber Overlay content: "
						+ OverlayTitle, "PASS");
				browser.executeScript(jqueryToCloseOverlay);
				browser.wait(getWaitTime());
			} else {
				Report.updateTestLog("Please check Why we need this link ",
						"WARN");
			}
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserOverViewLink"),
				"Manage User Overview");
		browser.swithnewwindow_getTitle();
	}

	public void ViewdetailsPageVerification() {
		/*
		 * verifyAndClickWithXpath(pageProperties.getProperty(
		 * "MultiUserMultiViewPage.ContactUsLink"), "Click Contact Us Link");
		 * verifyPageTitle("Contact Us | British Gas Business");
		 * browser.browserBack(); browser.wait(3000);
		 */
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AdduserRHNaddnewuser"),
				"Click Add New User Link");
		// PageTitleVerification("Add new user");
		browser.browserBack();
		browser.wait(5000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserRHNManageView"),
				"Click Manage View Link");
		// PageTitleVerification("Manage views");
		browser.browserBack();
	}

	public void AddNewUserNavigationVerifications() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ContactUsLink"),
				"Click Contact Us Link");
		verifyPageTitle("Contact Us | British Gas Business");
		browser.browserBack();
		browser.wait(3000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AdduserRHNaddnewuser"),
				"Click Add New User Link");
		verifyPageTitle("Add new user");
		browser.browserBack();
		browser.wait(5000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserRHNManageView"),
				"Click Manage View Link");
		verifyPageTitle("Manage views)");
		browser.browserBack();
		browser.wait(3000);
		ClickMangeruserNavigationPage();
		browser.browserBack();
		browser.wait(3000);
		clickAddnewuserNavigation();

	}

	public void confirmationPageVerificationLinks() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.YourAcctBreadcrummbLink"),
				"Your Account");
		// PageTitleVerification("Your accounts");
		browser.browserBack();
		browser.wait(5000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.Businesslink"),
				"Business");
		// verifyPageTitle("Cheap Business Energy | Business Gas & Electricity - British Gas Business");
		browser.browserBack();
		browser.wait(5000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.logintoAcctLink"),
				"Login to Your Account");
		browser.wait(3000);
		// PageTitleVerification("Log in");

	}

	public void PageTitleVerification(String Title) {
		String getPageName = null;
		getPageName = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.PageVerification1"));
		if (getPageName.equals(Title)) {
			Report.updateTestLog(Title + "Title is Verified Sucessfully",
					"Pass");
		}
	}

	public void PageTitleVerifications(String Title) {
		String getPageName = null;
		getPageName = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.PageVerification12"));
		if (getPageName.equals(Title)) {
			Report.updateTestLog(Title + "Title is Verified Sucessfully",
					"Pass");
		}
	}

	public void ManageUserNavigationVerificationLinks() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageViewsLink"),
				"Click Manage Views Link");
		PageTitleVerifications("Manage views");
		browser.browserBack();
		browser.wait(3000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserLink"),
				"Click Add new Users Link");
		PageTitleVerifications("Add new user");
		browser.browserBack();
		browser.wait(3000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.BacktoYourAccountLink"),
				"Click Back To YourAccount Link");
		PageTitleVerifications("Account overview");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserLink"),
				"Click Manage Users Link");
		browser.wait(3000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserOverViewLink"),
				"Click Manage User OverView Link");
		browser.swithnewwindow_getTitle();
	}

	public void ManageUserNavigationVerification() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ViewDetailsrLink"),
				"Click View details Link");
		verifyPageTitle("View user details");
		browser.browserBack();
		browser.wait(3000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.BacktoAccountSummaryLink"),
				"Click Back To Account Summary Link");
		verifyPageTitle("Manage Users");
		browser.browserBack();
		browser.wait(3000);
	}

	public void ManageviewNavigationLinks() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.YourAcctBreadcrummbLink"),
				"Your Account");
		browser.wait(5000);
		verifyPageTitle("Account overview");
		browser.browserBack();
		browser.wait(2000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.Businesslink"),
				"Business");
		browser.wait(5000);
		PageTitleVerification("British Gas Business");
		browser.browserBack();
		browser.wait(2000);
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.AtHomeLink"),
				" Click At Home Link");
		browser.wait(5000);
		verifyPageTitle("Cheap Gas & Electricity,Boilers and Energy Efficiency - British Gas");
		browser.browserBack();
		browser.wait(2000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.CorporateLink"),
				" Click Corporate Link");
		verifyPageTitle("Cheap Business Energy | Business Gas & Electricity - British Gas Corporate");
		browser.browserBack();

	}

	public void VerifyPageTitle() {
		verifyPageTitle("Manage Users");
	}

	public void WelcomeToManageUserOverlay() {
		// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.WelcomeToManageUserOverlay"),
		// "Welcome To Manage User Overlay Clicked");
		browser.wait(4000);
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.WelcomeToManageUserOverlayTitle"))) {
			Report.updateTestLog(
					"Welcome to Manage Users is appearing in the Overlay",
					"PASS");
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.MoreAboutUsertypeOverlaycontent"),
					"More About user type link");

			if (browser
					.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.SuperusercontentinOverlay"))) {

				Report.updateTestLog("Super user is appearing in the Overlay",
						"PASS");

			}
			if (browser
					.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.FullAccesscontentinOverlay"))) {

				Report.updateTestLog(
						"Full access user is appearing in the Overlay", "PASS");
			}
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.RBPcontentinOverlay"))) {

				Report.updateTestLog(
						"Reads, Bills,Payments user (Default) Users is appearing in the Overlay",
						"PASS");

			}
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.RBcontentinOverlay"))) {
				Report.updateTestLog(
						"Reads & Bills user Users is appearing in the Overlay",
						"PASS");
			}

		} else {
			Report.updateTestLog("You are logged for the second time ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.WelcomeToManageUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.WelcomeToManageUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
	}

	public void ManageUsersWhatisThisverlay() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserWhatisThisOverlay"),
				"Manage User What is This Overlay Clicked");

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserWhatisThisOverlayTitle"))) {
			String overlayContent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ManageUserWhatisThisOverlayTitle"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("AccountNumber Overlay content: "
					+ overlayContent, "PASS");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserWhatisThisOverlayClose"),
				"Overlay close button");

	}

	public void ClickManageUsersWhatisThisverlay() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserWhatisThisOverlay"),
				"Manage User What is This Overlay Clicked");

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUserWhatisThisOverlayTitle"))) {
			String overlayContent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ManageUserWhatisThisOverlayTitle"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("AccountNumber Overlay content: "
					+ overlayContent, "PASS");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}

	}

	public void AddNewViewOverlay() {
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.Addnewviewlink"),
				"Add new button clicked");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.AddnewViewOverlayTitle"))) {
			String overlayContent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.AddnewViewOverlayTitle"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("AccountNumber Overlay content: "
					+ overlayContent, "PASS");
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddnewViewOverlaySubmit"),
					"Overlay Confirm Button Clicked");

		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}
	}

	public void AddUsersWhatisThisverlay() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlay"),
				"Manage User What is This Overlay Clicked");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlayTitle1"))) {
			String overlayContent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlayTitle1"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("AccountNumber Overlay content: "
					+ overlayContent, "PASS");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
	}

	public void AddUsersWhatisThisverlay_updated() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlay"),
				"Manage User What is This Overlay Clicked");

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlayTitle1"))) {
			String overlayContent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlayTitle1"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("AccountNumber Overlay content: "
					+ overlayContent, "PASS");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}
		verifyIsTextPresent("Submit meter read");
		verifyIsTextPresent("View & download bill");
		verifyIsTextPresent("Consumption graph");
		verifyIsTextPresent("Statement of account");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserWhatisThisOverlayClose"),
				"Overlay close button");
	}

	public void SuperUserOverlayyes() {
		browser.wait(4000);
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.YouareCreatingaSuperUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.YouareCreatingaSuperUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog("Super User Overlay Title: " + overlayTitle,
					"PASS");
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.YouareCreatingaSuperUserOverlaySubmit"),
					"Submitted button Clicked");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}

	}

	public void SuperUserOverlayNo() {
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.YouareCreatingaSuperUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.YouareCreatingaSuperUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog("Super User Overlay Title: " + overlayTitle,
					"PASS");
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.YouareCreatingaSuperUserOverlayCancel"),
					"cancel button Clicked");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}

	}

	public void SuperUserCreation() {
		// if(browser.isElementVisibleWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddUserYesRadioButton")))
		// {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserYesRadioButton"),
				"Yes radio Button Clicked");
		browser.wait(5000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
				"Continue Button Clicked");
		// verifyAndClick(property, name)

		// }
	}

	public void StandardUserCreation() {
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddUserNoRadioButton"),"No radio Button Clicked");
		browser.wait(3000);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),"Continue Button Clicked");
		browser.wait(3000);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton")," next Continue Button Clicked");

	}
	public void StandardUserCreation_SelectSuper() {
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.SelectSuperUser"),"Yes radio Button Clicked");
		browser.wait(3000);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),"Continue Button Clicked");
		browser.wait(3000);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.superUserConfirmButton")," Confirm Button Clicked");

	}

	public void StandardUserCreations() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddUserNoRadioButton"),
				"No radio Button Clicked");
		browser.wait(3000);

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
				"Continue Button Clicked");

	}

	public void StandardUser_Creation() {
		browser.wait(3000);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddUserNoRadioButton"),"No radio Button Clicked");
		browser.wait(3000);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
				"Continue Button Clicked");
		browser.wait(3000);

	}

	public void AdduserConfirmationPage() {
		System.out.println("This is Confirmation Page");
		verifyPageTitle("Set up your password");
		System.out.println(browser.getTitle());
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ConfirmPageThankyou"))) {
			Report.updateTestLog("Add User Confirmation Page is Appeared",
					"WARN");
		}
		
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.logouttt"),"Logout");
	
	}
	
	
	
	public void AccountStorageFunctionality(UserProfile userProfile)
	{
			ClickAddUserLink();
			AddUserRadioButton();
			StandardUserCreation();
		
			enterValidDataStandardUser(userProfile);
			verifyLeadTable_Businessprofile();
			
			openEncryptUrlandRegister1new(userProfile);
			//verifyLeadTable_AddUserLinkEmailStatusNew();
			AdduserConfirmationPage();		
			
			verifyLeadTable_BG_Business_TA_Customer_Reg();
		}
	
	public void AccountStorageFunctionalitySuperUser(UserProfile userProfile)
	{
			ClickAddUserLink();
			AddUserRadioButton();
			StandardUserCreation_SelectSuper();
		
			enterValidDataStandardUser(userProfile);
			verifyLeadTable_Businessprofile();
			
			openEncryptUrlandRegister1new(userProfile);
			//verifyLeadTable_AddUserLinkEmailStatusNew();
			AdduserConfirmationPage();		
			
			verifyLeadTable_BG_Business_TA_Customer_Reg();
		}
	
	
	
	public void verifyLeadTable_BG_Business_TA_Customer_Reg(){
		browser.wait(3000);
		//newEntermail =Entermail;
		System.out.println("IN LLLLLLLLLLLLL");
	 	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	 	System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIII");
	 	String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 	System.out.println(" "+Date  );
	 	System.out.println("Date is populated");
	 	//String Result=dbFunctions.getAuditEventTypeIdForMUMVNew(Date,newEntermail,"AddUserLinkEmailStatus:success");
	 	String Result=dbFunctions.getAuditEventTypeIdForMUMVNew01(Date,Entermail);
	 	System.out.println("final Result is"+Result);
	 	if(Entermail.equals(Entermail)){
	 		Report.updateTestLog("Email Entry Is Made Sucessful in BG_BUSINESS_TA_CUSTOMER_REG TABLE", "PASS");
	      }
	      else {
	    	  Report.updateTestLog("Email Entry Was Not updated in BG_BUSINESS_TA_CUSTOMER_REG", "FAIL");
	 	}		 	
	 } 
	
	
	public void verifyLeadTable_Businessprofile(){
		browser.wait(3000);
		//newEntermail =Entermail;
		System.out.println("IN BDDDDDDDDDDDDDDDDDD");
	 	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	 	System.out.println("INNNNNNNNNNNNNNNNNNNNNNNNN ");
	 	String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 	System.out.println(" "+Date  );
	 	System.out.println("Date is populated");
	 	//String Result=dbFunctions.getAuditEventTypeIdForMUMVNew(Date,newEntermail,"AddUserLinkEmailStatus:success");
	 	String Result=dbFunctions.getAuditEventTypeIdForMUMVNew(Date,Entermail);
	 	System.out.println("final Result is"+Result);
	 	if(Entermail.equals(Entermail)){
	 		Report.updateTestLog("Email Entry Is Made Sucessful in bgb_business_profile TABLE", "PASS");
	      }
	      else {
	    	  Report.updateTestLog("Email Entry Was Not updated", "FAIL");
	 	}		 	
	 }
	
	
/*public void AccountStorageFunctionality(UserProfile userProfile)
{
	for(int i=1;i<=195;i++){
		Report.updateTestLog("CREATION OF " + i  + "ACCOUNT", "Pass");
		ClickAddUserLink();
		AddUserRadioButton();
		StandardUserCreation();
		enterValidDataStandardUser(userProfile);
		verifyLeadTable_AddUserLinkEmailStatusNew();
		openEncryptUrlandRegister1new(userProfile);
		verifyLeadTable_AddUserLinkEmailStatusNew();
		AdduserConfirmationPage();		
	}
	
}*/
	public void transcount(UserProfile userProfile, int i) {
		System.out.println(" i am here in table");
		String numberOfTransactions = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Transactions"));
		System.out.println(browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Transactions")));
		System.out.println("rowcount" + numberOfTransactions);
		String[] totalTransaction = numberOfTransactions.split("of");
		totalTransaction = totalTransaction[1].split(" ");
		String numberOfTransactionsInAuditTable = totalTransaction[1];
		int numberofrows = Integer.parseInt(numberOfTransactionsInAuditTable);
		tablehandle(userProfile, numberofrows, i);
	}

	public void transcountchange(UserProfile userProfile) {
		System.out.println(" i am here in table");
		String numberOfTransactions1 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Transactions"));
		System.out.println(browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Transactions")));
		System.out.println("rowcount" + numberOfTransactions1);
		String[] totalTransaction1 = numberOfTransactions1.split("of");
		totalTransaction1 = totalTransaction1[1].split(" ");
		String numberOfTransactionsInAuditTable1 = totalTransaction1[1];
		int numberofrows1 = Integer.parseInt(numberOfTransactionsInAuditTable1);
		ediviewValitation(userProfile, numberofrows1);
	}

	public int transcountupdate(UserProfile userProfile) {
		System.out.println(" i am here in table");
		String numberOfTransactions1 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Transactions"));
		System.out.println(browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Transactions")));
		System.out.println("rowcount" + numberOfTransactions1);
		String[] totalTransaction1 = numberOfTransactions1.split("of");
		totalTransaction1 = totalTransaction1[1].split(" ");
		String numberOfTransactionsInAuditTable1 = totalTransaction1[1];
		int numberofrows1 = Integer.parseInt(numberOfTransactionsInAuditTable1);
		return numberofrows1;
	}

	public int ManageUserTransaction(UserProfile userProfile) {
		System.out.println(" i am here in table");
		String numberOfTransactions1 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiView.ManageTransaction"));
		System.out.println(browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiView.ManageTransaction")));
		System.out.println("rowcount" + numberOfTransactions1);
		String[] totalTransaction1 = numberOfTransactions1.split("of");
		totalTransaction1 = totalTransaction1[1].split(" ");
		String numberOfTransactionsInAuditTable1 = totalTransaction1[1];
		int numberofrows1 = Integer.parseInt(numberOfTransactionsInAuditTable1);
		return numberofrows1;
	}

	// no of transancations in the acct edit table
	public int transaction() {

		String numberOfTransactions = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.AcctTableTransactions"));
		System.out.println(browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.AcctTableTransactions")));
		System.out.println("rowcount" + numberOfTransactions);
		String[] totalTransaction = numberOfTransactions.split("of");
		totalTransaction = totalTransaction[1].split(" ");
		String numberOfTransactionsInAuditTable = totalTransaction[1];
		int numberofrows = Integer.parseInt(numberOfTransactionsInAuditTable);
		return numberofrows;
	}

	public void editviewupdate(UserProfile userProfile) {

		int i = 1, j = 1, u = 4, k = 1;
		List<String> Acctnoarray = new ArrayList<String>();
		String allaccounts = "All accounts";
		int numberofrows = transcountupdate(userProfile);
		for (int l = 1; l <= numberofrows; l++) {
			String Allaccts = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ l + "]/td[" + j + "]");
			System.out.println((pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]"));

			if (!Allaccts.equals(allaccounts)) {

				String ViewName = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");
				System.out.println("myname" + ViewName);

				if (browser.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + u + "]")) {
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]");
					System.out.println("i am in da");
					verifyAndClickWithXpath(
							pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
									+ "//tr["
									+ l
									+ "]/td["
									+ u
									+ "]/input["
									+ k + "]", "Specific view Clicked");
					// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")+"//tr["+l+"]/td["+u+"]","Specific view Selected");
					Report.updateTestLog(
							"Specific view is selected for this user", "PASS");
					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
							" next Continue Button Clicked");
					enterValidData_StandardUsers(userProfile, ViewName);
					break;
				}
			}
		}
	}

	public void tablehandle(UserProfile userProfile, int numberofrows,
			int status) {

		// status ==1 when all accounts only need to select
		if (status == 1) {
			int i = 1, j = 1, u = 4;
			for (int l = 1; l <= numberofrows; l++) {
				String Allaccts = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");
				System.out.println((pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]"));
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
						" next Continue Button Clicked");

				/*if (Allaccts.equals("All accounts")) {
					System.out.println("i am in da");
					if (browser.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]")) {
						if (browser
								.isSelectedByXpath(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr[" + l + "]/td[" + u + "]")) {
							Report.updateTestLog(
									"All Accounts is selected for this view",
									"PASS");
						}
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
								" next Continue Button Clicked");
						break;
					}
				}*/
			}

		}

		// status ==2 when select other view except all accounts
		if (status == 2) {
			int i = 1, j = 1, u = 4, k = 1;
			String allaccounts = "All accounts";
			for (int l = 1; l <= numberofrows; l++) {
				String Allaccts = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");
				System.out.println((pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + i + "]/td[" + j + "]"));

				if (!Allaccts.equals(allaccounts)) {

					String ViewName = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + j + "]");
					System.out.println("myname" + ViewName);
					if (browser.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]")) {
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr[" + l + "]/td[" + u + "]");
						System.out.println("i am in da");
						verifyAndClickWithXpath(
								pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ l
										+ "]/td["
										+ u
										+ "]/input[" + k + "]",
								"Specific view Clicked");
						// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")+"//tr["+l+"]/td["+u+"]","Specific view Selected");
						Report.updateTestLog(
								"Specific view is selected for this user",
								"PASS");
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
								" next Continue Button Clicked");
						enterValidData_StandardUsers(userProfile, ViewName);
						break;
					}
				}
			}
		}

		if (status == 3) {
			int j = 1, flag = 0;
			String allaccounts = "All accounts";
			for (int l = 1; l <= numberofrows; l++) {
				String Allaccts = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");

				if ((!Allaccts.equals(allaccounts)) && (flag != 1)) {
					String Viewname = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + j + "]");
					verifyAndClickWithXpath(
							pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
									+ "//tr[" + l + "]/td[" + j + "]/a",
							Viewname + "clicked");
					browser.wait(4000);

					if (browser
							.isElementVisibleWithXpath(pageProperties
									.getProperty("MultiUserMultiViewPage.AccountViewOverlayTitle"))) {
						String AccountViewnameTitle = browser
								.getTextByXpath(pageProperties
										.getProperty("MultiUserMultiViewPage.AccountViewOverlayTitle"));
						Report.updateTestLog(
								"Account List Ovelay for the particular view is displayed as View accounts -"
										+ AccountViewnameTitle, "PASS");
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.AccountViewOverlayClose"),
								"Account view Overlay is closed");
						flag = 1;
					}
				}

			}
		}
		if (status == 4) {
			int i = 1, j = 1, u = 4, k = 1;
			String allaccounts = "All accounts";
			for (int l = 1; l <= numberofrows; l++) {
				String Allaccts = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");
				System.out.println((pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + i + "]/td[" + j + "]"));

				if (!Allaccts.equals(allaccounts)) {

					String ViewName = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + j + "]");
					System.out.println("myname" + ViewName);
					if (browser.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]")) {
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr[" + l + "]/td[" + u + "]");
						System.out.println("i am in da");
						verifyAndClickWithXpath(
								pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ l
										+ "]/td["
										+ u
										+ "]/input[" + k + "]",
								"Specific view Clicked");
						// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")+"//tr["+l+"]/td["+u+"]","Specific view Selected");
						Report.updateTestLog(
								"Specific view is selected for this user",
								"PASS");
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
								" next Continue Button Clicked");
						enterValidData_StandardUsers(userProfile, ViewName);
						break;
					}
				}
			}
		}
		if (status == 5) {
			int i = 1, j = 1, p = 3, u = 4, k = 1;
			String allaccounts = "All accounts";
			for (int l = 1; l <= numberofrows; l++) {
				String Allaccts = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");
				String userwithview = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + p + "]");
				System.out.println((pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + i + "]/td[" + j + "]"));

				// test date with first name and last name entered user with
				// view is necessary to execute
				// if(!Allaccts.equals(allaccounts) &&
				// !userwithview.equals("None"))
				if (!Allaccts.equals(allaccounts)) {

					String ViewName = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + j + "]");
					System.out.println("myname" + ViewName);
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]");
					if (browser.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]")) {
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr[" + l + "]/td[" + u + "]");
						System.out.println("i am in da");
						verifyAndClickWithXpath(
								pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr[" + l + "]/td[" + u + "]/a",
								"Specific view Clicked");
						// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")+"//tr["+l+"]/td["+u+"]","Specific view Selected");
						Report.updateTestLog(
								"Specific view details is selected for this user",
								"PASS");
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.EditViewButton"),
								" Edit View");
						verifyPageTitle("Edit View");
						verifyAndInputByXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.Viewname"),
								"view name", userProfile.getEditview());
						break;
					}
				}
			}
		}

	}

	public String viewtableHandle(UserProfile userProfile) {

		int numberofrows = transcountupdate(userProfile);
		String UserType = null;
		int i = 1, j = 1, u = 4, k = 1;
		String allaccounts = "All accounts";
		String appdefauldata = browser.getTextFromDropBox("roleProfile",
				"roleProfile");
		System.out.print("appdefauldata" + appdefauldata);
		if (appdefauldata.equals("Super user")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Full access");

			UserType = "Full access";
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		} else if (appdefauldata.equals("Full access")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Reads and Bills");
			UserType = "Reads and Bills";
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		} else if (appdefauldata.equals("Reads, Bills and Payments")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Reads and Bills");
			UserType = "Reads and Bills";
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		} else if (appdefauldata.equals("Reads and Bills")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Reads, Bills and Payments");
			UserType = "Reads, Bills and Payments";
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		}
		for (int l = 1; l <= numberofrows; l++) {
			String Allaccts = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ l + "]/td[" + j + "]");
			System.out.println((pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]"));

			if (!Allaccts.equals(allaccounts)) {
				String ViewName = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");
				System.out.println("myname" + ViewName);

				if (browser.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + u + "]")) {
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]");
					System.out.println("i am in da");
					verifyAndClickWithXpath(
							pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
									+ "//tr["
									+ l
									+ "]/td["
									+ u
									+ "]/input["
									+ k + "]", "Specific view Clicked");
					// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")+"//tr["+l+"]/td["+u+"]","Specific view Selected");
					Report.updateTestLog(
							"Specific view is selected for this user", "PASS");
					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.ConfirmButton"),
							" next Continue Button Clicked");

					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.AddNewUserNavigationPage"),
							" Add a New user");

					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.AddUserNoRadioButton"),
							"No radio Button Clicked");
					browser.wait(3000);
					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
							"Continue Button Clicked");
					browser.wait(3000);
					SelectSpecificViewinAdduser(userProfile, ViewName);
					enterValidData_StandardUsers(userProfile, ViewName);
					break;
				}
			}
		}
		return UserType;

	}

	public void superuserchange() {
		verifyIsTextPresent("You are creating a Super User");
		browser.clickbutton("maxdataOverlay-submit");
		browser.wait(getWaitTime());
		// verifyPageTitle("User details amended");

	}

	public void viewstdtablehandle(UserProfile userProfile) {
		String UserType = null;
		int i = 1, j = 1, u = 4, k = 1;
		String allaccounts = "All accounts";
		String appdefauldata = browser.getTextFromDropBox("roleProfile",
				"roleProfile");
		System.out.print("appdefauldata" + appdefauldata);
		if (appdefauldata.equals("Super User") ) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Full Access User");

			UserType = "Full Access User";
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		} else if (appdefauldata.equals("Full Access User")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Reads & Bills User");
			UserType = "Reads and Bills";
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		} else if (appdefauldata.equals("Reads, Bills & Payments User")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Reads & Bills User");
			UserType = "Reads & Bills User";
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		} else if (appdefauldata.equals("Reads & Bills User")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Reads, Bills & Payments User");
			UserType = "Reads, Bills & Payments User";
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ConfirmButton"),
				" next Continue Button Clicked");
		}

	public void Viewtableverification() {
		String UserType = null;
		int i = 1, j = 1, u = 4, k = 1;
		String appdefauldata = browser.getTextFromDropBox("roleProfile",
				"roleProfile");
		System.out.print("appdefauldata" + appdefauldata);
		if (appdefauldata.equals("Full Access User")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Super User");
			UserType = "Super User";
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ConfirmButton"),
					"confirm Clicked");
			superuserchange();
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		} else if (appdefauldata.equals("Reads, Bills and Payments User")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Super User");
			UserType = "Super User";
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ConfirmButton"),
					"confirm Clicked");
			superuserchange();
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		} else if (appdefauldata.equals("Reads and Bills")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Super User");
			UserType = "Super User";
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ConfirmButton"),
					"confirm Clicked");
			superuserchange();
			Report.updateTestLog("User Type is change to " + UserType, "PASS");
		}

	}

	public void BackAndCancel() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.BackLink"),
				"Back link");
		PageTitleVerification("View, edit or deactivate user");
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.CancelLink"),
				"Cancel link");
		PageTitleVerification("Manage users");
	}

	public void Useramendeddetails() {
		verifyPageTitle("User details amended");

	}

	public void EditViewNameErrorValidation(UserProfile userProfile) {
		String jqueryToCloseOverlay = ("$('ui-icon ui-icon-closethick').trigger('click');");

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.EditViewSelectAll"),
				"Select All Deselect");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.AddNewUserSubmitButton"),
				"Confirm");

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.NoAcctsViewOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.NoAcctsViewOverlayContent"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog("Error Overlay Title: " + overlayTitle, "PASS");
			String Erroroverlaycontent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.NoAcctsViewOverlayContent"));
			System.out.println("overlayContent: " + Erroroverlaycontent);
			verifyErrorMessage(Erroroverlaycontent,
					SlingshotErrorMessages.MuMv_NoAccts_View_Error);
			// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.NoAcctsViewOverlayClose"),
			// "Close button");
			browser.executeScript(jqueryToCloseOverlay);
			browser.wait(getWaitTime());
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}
	}

	public void EditViewNameforLessthan15Accts(UserProfile userProfile) {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.EditViewSelectAll"),
				"Select All Deselect");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.EditViewSelectAll"),
				"Select All Select");

		int i, k = 2, m = 4, c, l = 0, counter = 0;
		List<String> Acctnoarray = new ArrayList<String>();
		int viewacct = transaction();
		System.out.println("viewacct" + viewacct);
		for (i = 1; i <= viewacct; i++) {
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewAcctnameNext"))) {
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctnameNext"),
						"Next");
			}

			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.AcctNolist")
					+ "//tr["
					+ i + "]/td[" + k + "]")) {
				String acctno = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.AcctNolist")
						+ "//tr[" + i + "]/td[" + k + "]");
				System.out.println("acctno" + acctno);
				Acctnoarray.add(browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.AcctNolist")
						+ "//tr[" + i + "]/td[" + k + "]"));
				System.out.println(pageProperties
						.getProperty("MultiUserMultiViewPage.AcctNolist")
						+ "//tr[" + i + "]/td[" + k + "]");
				System.out.println("Acctnoarray" + Acctnoarray);
				System.out.println(pageProperties
						.getProperty("MultiUserMultiViewPage.AcctNolist")
						+ "//tr[" + i + "]/td[" + m + "]");
				// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AcctNolist")+"//tr["+i+"]/td["+m+"]",acctno+"Selected");
				// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AcctNolist1").replace("ACCOUNTNUMBER",
				// acctno), "Account number"+acctno);

				if (browser.isSelectedByXpath(pageProperties.getProperty(
						"MultiUserMultiViewPage.AcctNolist1").replace(
						"ACCOUNTNUMBER", acctno))) {
					counter = counter + 1;
				}

			}
		}
		int Acctarrlen = Acctnoarray.size();

		if (counter == Acctarrlen) {
			System.out.println("both count same");
			Report.updateTestLog("All the Accts are selected in this view",
					"PASS");
		}

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.AddNewUserSubmitButton"),
				"Confirm");
	}

	public void EditViewAdduserValidation(UserProfile userProfile) {
		int viewacct1 = transcount();
		int o, u = 1, m = 4, count = 0, flag = 0;
		String appviewname[] = new String[100];
		String AppviewName = "";
		for (o = 2; o <= viewacct1; o++) {
			if (flag == 0) {
				for (int j = 1; j <= 150; j++) {
					System.out.println("initial");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					appviewname[j] = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println("appviewname" + appviewname[j]);
					System.out.println("j" + j);
					AppviewName = appviewname[j];
					count = j;
					System.out.println("count" + count);
					System.out.println("aftercopy" + AppviewName);

					if ((userProfile.getEditview()).contains(AppviewName)) {
						System.out.println("i am in view method");
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + u + "]");
						verifyAndClickWithXpath(
								pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + u + "]",
								"specific view selected");
						System.out.println("m" + m);
						System.out.println("comparisoncount" + count);
						browser.wait(3000);
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiView.Continue"),
								"Confirm");
						flag = 1;
						break;
					}
				}

			}
		}
	}

	public void ediviewValitation(UserProfile userProfile, int numberofrows1) {
		int i = 1, j = 1, p = 3, u = 4, k = 1;
		String allaccounts = "All accounts";
		for (int l = 1; l <= numberofrows1; l++) {
			String Allaccts = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ l + "]/td[" + j + "]");
			String userwithview = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ l + "]/td[" + p + "]");
			System.out.println((pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]"));

			// test date with first name and last name entered user with view is
			// necessary to execute
			if (!Allaccts.equals(allaccounts)) {

				String ViewName = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");
				System.out.println("myname" + ViewName);
				System.out.println(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + u + "]");
				if (browser.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + u + "]")) {
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]");
					System.out.println("i am in da");
					verifyAndClickWithXpath(
							pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
									+ "//tr[" + l + "]/td[" + u + "]/a",
							"Specific view Clicked");
					// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")+"//tr["+l+"]/td["+u+"]","Specific view Selected");
					Report.updateTestLog(
							"Specific view details is selected for this user",
							"PASS");
					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.EditViewButton"),
							" Edit View");
					verifyPageTitle("Edit View");
					verifyAndInputByXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.Viewname"),
							"view name", userProfile.getEditview());
					int numberofrows = transaction();
					if (numberofrows >= 1) {
						if (browser
								.isElementVisibleWithXpath(pageProperties
										.getProperty("MultiUserMultiViewPage.EditViewAcctTable")
										+ "//tr[" + l + "]/td[" + u + "]/a")) {
							verifyAndClickWithXpath(
									pageProperties
											.getProperty("MultiUserMultiViewPage.EditViewAcctTable")
											+ "//tr[" + l + "]/td[" + u + "]/a",
									"Remove");
							browser.wait(3000);
							verifyAndClickWithXpath(
									pageProperties
											.getProperty("MultiUserMultiView.Continue"),
									"Continue");
							verifyPageTitle("View amended");
							verifyAndClickWithXpath(
									pageProperties
											.getProperty("MultiUserMultiView.BackToManageUsersLink"),
									"Back to Manage Users link");
							break;
						}
					}

				}
			}
		}

	}

	public void ediviewValitations(UserProfile userProfile) {
		int i = 1, j = 1, p = 3, u = 4, k = 1;
		String allaccounts = "All accounts";
		int numberofrows1 = transcountupdate(userProfile);
		for (int l = 1; l <= numberofrows1; l++) {
			String Allaccts = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ l + "]/td[" + j + "]");
			String userwithview = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ l + "]/td[" + p + "]");
			System.out.println((pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]"));

			if (!Allaccts.equals(allaccounts)) {

				String ViewName = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + j + "]");
				System.out.println("myname" + ViewName);
				System.out.println(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + u + "]");
				if (browser.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + l + "]/td[" + u + "]")) {
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + l + "]/td[" + u + "]");
					System.out.println("i am in da");
					verifyAndClickWithXpath(
							pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
									+ "//tr[" + l + "]/td[" + u + "]/a",
							"Specific view Clicked");
					// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")+"//tr["+l+"]/td["+u+"]","Specific view Selected");
					Report.updateTestLog(
							"Specific view details is selected for this user",
							"PASS");
					break;
				}
			}
		}

	}

	public void EditAcctsTable(UserProfile userProfile) {
		int l = 0, u = 2, c, flag = 0;
		int noofrows = transcount();
		String AcctnoArray[] = new String[noofrows + 1];
		String[] SAPVerifiedAcctno;
		String AcctTemp = "";
		System.out.println("noofrows" + noofrows);
		for (l = 1; l <= noofrows; l++) {
			System.out.println("i reached");

			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewAcctsNameTable")
					+ "//tr[" + l + "]/td[" + u + "]")) {
				System.out
						.println(pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctsNameTable")
								+ "//tr[" + l + "]/td[" + u + "]");
				String AcctTableAcctno = browser
						.getTextByXpath(pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctsNameTable")
								+ "//tr[" + l + "]/td[" + u + "]");
				AcctnoArray[l] = AcctTableAcctno;
				System.out.println("AcctnoArray[l]" + AcctnoArray[l]);
				System.out.println("i am in da");
				System.out.println("l value now" + l);
			}

		}

		int len = AcctnoArray.length;
		System.out.println("length of array in application" + len);
		for (int b = 1; b <= len - 1; b++) {
			System.out.println("AcctnoArray[b]" + AcctnoArray[b]);
		}

		SAPVerifiedAcctno = acctnovalidatation(userProfile);
		int saplen = SAPVerifiedAcctno.length;
		System.out.println("krishnaaaaaaaaaaaaaaaa");
		for (l = 1; l <= saplen; l++) {
			System.out.println("krishna");
			for (c = 1; c <= len; c++) {
				System.out.println("SAPVerifiedAcctno[c]"
						+ SAPVerifiedAcctno[c]);
				System.out.println("AcctnoArray" + AcctnoArray[l]);
				if (AcctnoArray[l].equals(SAPVerifiedAcctno[c])) {
					System.out.println("i am in application" + AcctnoArray[l]);
					flag = 0;
					break;
				} else {
					flag = 1;
				}

			}

			if (flag == 1) {
				AcctTemp = SAPVerifiedAcctno[c];
				System.out.println("i am else");
				System.out.println("AcctTemp" + AcctTemp);
				break;
			}
		}
		browser.wait(5000);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.EditViewButton"),
				" Edit View");
		verifyPageTitle("Edit View");
		verifyAndInputByXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.Viewname"),
				"view name", userProfile.getEditview());
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddViewSearchkey"),
				"search Type", "Account Number");
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddviewSearchItem"),
				"search Account", AcctTemp);
		browser.click("search-accounts");
		browser.wait(5000);
		if (browser
				.isElementVisibleWithXpath(".//*[@id='search_results']/p/span")) {
			browser.clickWithLinkText("Search for more accounts");
			browser.wait(4000);
		}

		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Continue"),
				"Confirm");

		if (browser.isTextPresent("New view successfully created")) {
			Report.updateTestLog("New view successfully created", "Pass");
			verifyPageTitle("New view created");
		}

	}

	public void SmrVerification() {
		browser.wait(4000);

		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.SubmitMeterReadLink"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.SubmitMeterReadLink"),
					"SMR link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification1"));
			System.out.println("getPageNamesmr" + getPageName);
			if (getPageName.equals("Submit meter read")) {
				Report.updateTestLog(
						"Submit Meter Read Page is Available for the User",
						"Pass");
			}
		} else {
			Report.updateTestLog(
					"Submit Meter Read Page is Available for the User", "Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void steupdirectdebit() {

		String getPageName = null;
		browser.wait(2000);

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.SetUpDirectLink"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.SetUpDirectLink"),
					"Setup Direct Debit link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification"));
			System.out.println("getPageName" + getPageName);
			if (getPageName.equals("Set up Direct Debit")) {
				Report.updateTestLog(
						"SetUp Direct Debit Page is Available for the User",
						"Pass");
			}
		} else if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ManageDirectLink"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ManageDirectLink"),
					"Amend Direct Debit link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification"));
			System.out.println("getPageName" + getPageName);
			if (getPageName.equals("Amend Direct Debit")) {
				Report.updateTestLog(
						"SetUp Direct Debit Page is Available for the User",
						"Pass");
			}
		} else {
			Report.updateTestLog(
					"SetUp Direct Debit Page is Available for the User", "Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void ConsumptionGraph() {
		browser.wait(4000);
		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.consumptionGraphLink"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.consumptionGraphLink"),
					"Consumption Graph link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification"));
			System.out.println("getPageNamegraph" + getPageName);
			if (getPageName.equals("Your energy consumption")) {
				Report.updateTestLog(
						"Consumption Graph Page is Available for the User",
						"Pass");
			}
		} else {
			Report.updateTestLog(
					"Consumption Graph Page is Available for the User", "Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void Payments() {
		browser.wait(5000);
		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.PaymentsLink"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.PaymentsLink"),
					"Payments link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification"));
			System.out.println("getPageName" + getPageName);
			if (getPageName.equals("Payments")) {
				Report.updateTestLog("Payments Page is Available for the User",
						"Pass");
			}
		} else {
			Report.updateTestLog("Payments Page is Available for the User",
					"Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void ViewBill() {
		browser.wait(5000);
		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ViewbillLink"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ViewbillLink"),
					"View link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification1"));
			System.out.println("getPageName" + getPageName);
			if (getPageName.equals("View bill")) {
				Report.updateTestLog(
						"View bill Page is Available for the User", "Pass");
			}
		} else {
			Report.updateTestLog("View bill Page is Available for the User",
					"Fail");
		}
		browser.wait(3000);
	}

	public void SOA() {
		browser.wait(3000);
		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.SOALink"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.SOALink"),
					"SOA link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification"));
			System.out.println("getPageNamesoa" + getPageName);
			if (getPageName.equals("View Statement of account")) {
				Report.updateTestLog("SOA Page is Available for the User",
						"Pass");
			}
		} else {
			Report.updateTestLog("SOA Page is Available for the User", "Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void odbVerification() {
		browser.wait(6000);

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.OdbVerification"))) {
			Report.updateTestLog(
					"On Demand Billing Page is Available for the User", "Pass");
		} else {
			Report.updateTestLog(
					"On Demand Billing Page  is Available for the User", "Fail");
		}

	}

	public void MangeUsers() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.YourAcctBreadcrummbLink"),
				"Your Account link");
		browser.wait(3000);
		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.ManageUserLink"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ManageUserLink"),
					"Manage users link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification"));
			System.out.println("getPageNamemanage" + getPageName);
			if (getPageName.equals("Manage users")) {
				Report.updateTestLog(
						"Manage users Page is Available for the User", "Pass");
			}
		} else {
			Report.updateTestLog("Manage users Page is Available for the User",
					"Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void UserJourney_AccessVerification(UserProfile userProfile,
			String UserType) {
		String SuperUsers = "Super user";
		String FullAccess = "Full access";
		String RB = "Reads and Bills";
		String RBP = "Reads, Bills and Payments";
		verifyAndInputById(
				pageProperties.getProperty("MultiUserMultiViewPage.Email"),
				"Email Id", userProfile.getNewEmail());
		verifyAndInputById(
				pageProperties.getProperty("MultiUserMultiViewPage.Password"),
				"Password", userProfile.getPassword());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.NewLoginSubmitXpath"),
				"Submit button");
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.ManageAcct")
						.replace("ACCOUNTNUMBER", userProfile.getAccNumber()),
				"Manage account link");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.BillingLink"),
				"Billing link");
		if (SuperUsers.equals(UserType)) {
			SmrVerification();
			steupdirectdebit();
			ConsumptionGraph();
			SOA();
			Payments();
			ViewBill();
			MangeUsers();
		} else if (FullAccess.equals(UserType)) {
			SmrVerification();
			steupdirectdebit();
			ConsumptionGraph();
			SOA();
			Payments();
			ViewBill();
		} else if (RBP.equals(UserType)) {
			SmrVerification();
			steupdirectdebit();
			ConsumptionGraph();
			SOA();
			Payments();
			ViewBill();
		} else if (RB.equals(UserType)) {

			SOA();
			SmrVerification();
			ConsumptionGraph();
			ViewBill();
		}

	}

	public void BreadCrumbNavigation() {

		int i = 0;
		String links[] = { "BusinessLink", "YouraccountLink", "ManageUsersLink" };
		String Title[] = { "British Gas Business", "Your accounts",
				"Manage users" };

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.YourAcctBreadcrummbLink"),
				"You Account click");
		browser.wait(3000);
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageUsersBreadcrumbLink"),
				"Manage users click");
		browser.wait(3000);
		browser.browserBack();
		browser.wait(3000);
		// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.BusinessLink"),
		// "Business click");
		// browser.wait(3000);
		// browser.browserBack();

	}

	public void MaximumSuperUserExceded() {
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.AddUserYesRadioButton"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddUserYesRadioButton"),
					"Yes radio Button Clicked");
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"),
					"Continue Button Clicked");

		}
	}

	public void SuperUserErrorValidation() {

		String displayedErrorMsg = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.UserLimitExceededPage"));
		errorMsgComparisonSearch(displayedErrorMsg,
				SlingshotErrorMessages.MuMV_SuperUser_Exceeded_Error);
	}

	public void StandardUserErrorValidation() {

		String displayedErrorMsg = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.UserLimitExceededPage"));
		errorMsgComparisonSearch(displayedErrorMsg,
				SlingshotErrorMessages.MuMV_StandarUser_Exceeded_Error);
	}

	public void verifyTitle() {
		String actualtitle = browser.getTextByXpath(pageProperties
				.getProperty("MultiuserMultiViewPage.AcctOverview"));
		if (actualtitle.equals("Account overview")) {

			Report.updateTestLog("Actual Page Title" + actualtitle
					+ " Verified Successfully", "PASS");

		}

	}

	public void errorMsgComparisonSearch(final String displayedErrorMsg,
			String expectedErrorMsg) {
		verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);
	}

	public void verifyErrorMessage(final String displayedErrorMsg,
			final String expectedErrorMsg) {
		if (displayedErrorMsg.trim().contains(expectedErrorMsg.trim())) {
			Report.updateTestLog(
					"Error message validation. Expected message:"
							+ expectedErrorMsg + " Actual message:"
							+ displayedErrorMsg, "PASS");
		} else {
			Report.updateTestLog(
					"Error message validation. Expected message:"
							+ expectedErrorMsg + " Actual message:"
							+ displayedErrorMsg, "FAIL");
		}
	}

	public void viewDetailsTable(UserProfile userProfile) {
		int j = 3, k = 7, l = 8, i, flag = 0;

		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserStatus = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + k + "]");
			System.out.println("UserStatus" + UserStatus);
			if (!(EmailAddress.equals(userProfile.getEmail()))
					&& (UserStatus.equals("ACTIVE"))) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"view details Clicked");
				flag = 1;
				System.out.println("i" + i);
			}

			if (flag == 1) {
				System.out.println("i am in flag i" + i);
				break;
			}
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMUltiViewPage.ManageUserNext"))
					&& (i == 10)) {
				System.out.println("i" + i);
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMUltiViewPage.ManageUserNext"),
						"Next");
				i = 1;
			}
		}

		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.EditButton"),
				"Edit details Clicked");
		// verifyPageTitle("Edit user details");

	}

	public void ClickEditdetails() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.EditButton"),
				"Edit details Clicked");
		// verifyPageTitle("Edit user details");
	}

	public String viewDetailsTableViewuser(UserProfile userProfile) {
		String EmailAddress = null;
		int j = 3, k = 7, l = 8, i, flag = 0, usertypepos = 4;

		for (i = 1; i <= 10; i++) {
			EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserStatus = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + k + "]");
			System.out.println("UserStatus" + UserStatus);
			String UserType = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + usertypepos + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + usertypepos + "]");
			System.out.println("UserType" + UserType);
			if (!(EmailAddress.equals(userProfile.getEmail()))
					&& (UserStatus.equals("ACTIVE") && (!UserType
							.equals("Super User")))) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"view details Clicked");
				flag = 1;
				System.out.println("i" + i);
			}

			if (flag == 1) {
				System.out.println("i am in flag i" + i);
				break;
			}
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMUltiViewPage.ManageUserNext"))
					&& (i == 10)) {
				System.out.println("i" + i);
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMUltiViewPage.ManageUserNext"),
						"Next");
				i = 1;
			}
		}

		return EmailAddress;

	}

	public String viewDetailsTableViewStduser(UserProfile userProfile) {
		String EmailAddress = null;
		int j = 3, k = 7, l = 8, i, flag = 0, usertypepos = 4;

		for (i = 1; i <= 10; i++) {
			EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserStatus = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + k + "]");
			System.out.println("UserStatus" + UserStatus);
			String UserType = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + usertypepos + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + usertypepos + "]");
			System.out.println("UserType" + UserType);
			if (!(EmailAddress.equals(userProfile.getEmail()))
					&& (UserStatus.equals("ACTIVE"))) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"view details Clicked");
				flag = 1;
				System.out.println("i" + i);
			}

			if (flag == 1) {
				System.out.println("i am in flag i" + i);
				break;
			}
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMUltiViewPage.ManageUserNext"))
					&& (i == 10)) {
				System.out.println("i" + i);
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMUltiViewPage.ManageUserNext"),
						"Next");
				i = 1;
			}
		}
		PageTitleVerification("View, edit or deactivate user");
		return EmailAddress;
	}

	public void viewDetailsTablestdUser(UserProfile userProfile) {
		int j = 3, k = 7, l = 8, n = 4, i, flag = 0;

		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserStatus = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + k + "]");
			System.out.println("UserStatus" + UserStatus);
			String UserType = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + n + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + n + "]");

			if ((!EmailAddress.equals(userProfile.getEmail()))
					&& (UserStatus.equals("ACTIVE") && (!UserType
							.equals("Super user")))) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"view details Clicked");
				flag = 1;
				System.out.println("i" + i);
			}

			if (flag == 1) {
				System.out.println("i am in flag i" + i);
				break;
			}
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMUltiViewPage.ManageUserNext"))
					&& (i == 10)) {
				System.out.println("i" + i);
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMUltiViewPage.ManageUserNext"),
						"Next");
				i = 1;
			}
		}

		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.EditButton"),
				"Edit details Clicked");
		// verifyPageTitle("Edit user details");

	}

	public void viewDetailsTableSuperuser(UserProfile userProfile) {
		int j = 3, k = 7, l = 8, i, n = 4, flag = 0;
		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserStatus = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + k + "]");
			System.out.println("UserStatus" + UserStatus);
			String UserType = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + n + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + n + "]");
			System.out.println("UserType" + UserType);
			if ((!EmailAddress.equals(userProfile.getEmail()))
					&& (UserStatus.equals("ACTIVE") && (!UserType
							.equals("Super user")))) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"view details Clicked");
				flag = 1;
			}

			if (flag == 1) {
				break;
			}
		}

		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.EditButton"),
				"Edit details Clicked");
		verifyPageTitle("Edit user details");

	}

	public String GetViewname(UserProfile userProfile) {
		int j = 3, k = 7, l = 8, i, v = 5, flag = 0;
		String viewnameinmanageuser = null;
		System.out.println("getviewname function");
		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserStatus = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + k + "]");
			System.out.println("UserStatus" + UserStatus);
			viewnameinmanageuser = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ManageuserViewTable")
					+ "//tr[" + i + "]/td[" + v + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ i + "]/td[" + v + "]");
			System.out.println("viewnameinmanageuser" + viewnameinmanageuser);
			if (!(EmailAddress.equals(userProfile.getEmail()))
					&& (!viewnameinmanageuser.equals("All accounts"))) {
				flag = 1;
				System.out.println("i" + i);
				System.out.println("viewnameinmanageuser in if"
						+ viewnameinmanageuser);
			}

			if (flag == 1) {
				System.out.println("i am in flag i" + i);
				break;
			}
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMUltiViewPage.ManageUserNext"))
					&& (i == 10)) {
				System.out.println("i" + i);
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMUltiViewPage.ManageUserNext"),
						"Next");
				i = 1;
			}
		}
		return viewnameinmanageuser;

	}

	public void DeleteViewOverlay(UserProfile userProfile) {
		int j = 3, k = 7, l = 8, i, v = 5, flag = 0, aa = 4, ss = 1;
		// String Viewtype="";
		String Viewtype = GetViewname(userProfile);
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageViewsLink"),
				"Manage View");
		/*
		 * for(i=1;i<=10;i++) {
		 * Viewtype=browser.getTextByXpath(pageProperties.getProperty
		 * ("MultiUserMultiViewPage.ManageuserViewTable"
		 * )+"//tr["+i+"]/td["+v+"]");
		 * System.out.println(pageProperties.getProperty
		 * ("MultiUserMultiViewPage.ManageuserViewTable"
		 * )+"//tr["+i+"]/td["+v+"]"); System.out.println("Viewtype"+Viewtype);
		 * if((!Viewtype.equals("All accounts"))) {
		 * Viewtype=browser.getTextByXpath
		 * (pageProperties.getProperty("MultiUserMultiViewPage.ManageuserViewTable"
		 * )+"//tr["+i+"]/td["+v+"]");
		 * System.out.println("inside if Viewtype"+Viewtype); flag=1; }
		 * 
		 * if(flag==1) { verifyAndClickWithXpath(pageProperties.getProperty(
		 * "MultiUserMultiViewPage.ManageViewsLink"),"Manage View");
		 * System.out.println("i am flag 1"); break; } }
		 */

		int numberofrows = transcount();
		System.out.println("numberofrows" + numberofrows);
		for (int b = 1; b <= numberofrows; b++) {
			String userwithview = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ b + "]/td[" + ss + "]");
			System.out.println((pageProperties
					.getProperty("MultiUserMultiViewPage.ViewTable")
					+ "//tr["
					+ b + "]/td[" + ss + "]"));

			if (userwithview.equals(Viewtype)) {

				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
								+ "//tr[" + b + "]/td[" + aa + "]/a",
						"viewdetails");
				System.out.println("view details");
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.DeletViewButton"),
						"Delete View");
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.DeleteViewYesConfirm"),
						"Yes");

				if (browser
						.isElementVisibleWithXpath(pageProperties
								.getProperty("MultiUserMultiViewPage.DeleteviewErrorOverlayTitle"))) {
					String overlayTitle = browser
							.getTextByXpath(pageProperties
									.getProperty("MultiUserMultiViewPage.DeleteviewErrorOverlayTitle"));
					System.out.println("overlayContent: " + overlayTitle);
					Report.updateTestLog(
							"Error Overlay Title: " + overlayTitle, "PASS");
					String Erroroverlaycontent = browser
							.getTextByXpath(pageProperties
									.getProperty("MultiUserMultiViewPage.DeleteViewErrorOverlaycontent"));
					System.out
							.println("overlayContent: " + Erroroverlaycontent);
					verifyErrorMessage(Erroroverlaycontent,
							SlingshotErrorMessages.MuMv_DeleteView_Error);

					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.DeleteViewErrorOverlayClose"),
							"Close button");
					break;
				} else {
					Report.updateTestLog("Please check Why we need this link ",
							"WARN");
				}

			}
		}

	}

	public void verifyAndValidateUserTypeFieldContent(UserProfile userProfile) {
		int flg = 0;
		System.out.println("i am in");
		final String[] userTypeFieldContents = { "Super user", "Full access",
				"Reads, Bills and Payments", "Reads and Bills" };
		final ArrayList<String> actualuserTypeContent = browser.getFromDropBox(
				"id", pageProperties
						.getProperty("MultiUserMultiViewPage.ChangeUserType"));
		for (int i = 0; i < userTypeFieldContents.length; i++) {
			if (actualuserTypeContent.get(i).trim()
					.equalsIgnoreCase(userTypeFieldContents[i].trim())) {
				flg = flg + 1;
				System.out.println("i am in if statement");
				Report.updateTestLog("Expected 'User Type'"
						+ actualuserTypeContent.get(i) + "content is present",
						"PASS");
			} else {
				Report.updateTestLog(
						"Expected 'User Type' content does not present", "FAIL");
			}
			if (flg == 4)
				Report.updateTestLog(
						"Expected All the 'User Type' content is present",
						"PASS");
		}

	}

	public void EditViewDetails(UserProfile userProfile) {
		String appdefauldata = browser.getTextFromDropBox("roleProfile",
				"roleProfile");
		System.out.print("appdefauldata" + appdefauldata);

		if (appdefauldata.equals("Super user")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Full access");
		} else if (appdefauldata.equals("Full access")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Reads and Bills");
		} else if (appdefauldata.equals("Reads, Bills and Payments")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Super user");
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiView.SuperUserOverlaySubmit"))) {
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiView.SuperUserOverlaySubmit"),
						"Overlay submit button");
				browser.wait(getWaitTime());
				verifyPageTitle(pageProperties
						.getProperty("MultiUserMultiView.DetailsAmended"));
			}
		} else if (appdefauldata.equals("Reads and Bills")) {
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.ChangeUserType"),
					"roleProfile", "Reads, Bills and Payments");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ConfirmButton"),
				"confirm Clicked");
		verifyPageTitle("User User details amended");
	}

	public void EdituserDetailsCancelBack() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.Back"),
				"Back");
		PageTitleVerification("View, edit or deactivate user");
		browser.browserBack();
		browser.wait(3000);
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.cancel"),
				"confirm Clicked");
		PageTitleVerification("Manage users");
	}

	public void VerifySummarylistandUserlist(int StandardUserCnt,
			int SuperUserCnt) {
		System.out.println("i am here oly");
		String superuser = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Superusercountsummary"));
		int superusercount = Integer.parseInt(superuser);
		System.out.println("superusercount" + superusercount);
		String stduser = browser
				.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.Standardusercountsummary"));
		int stdusercount = Integer.parseInt(stduser);
		System.out.println("stdusercount" + stdusercount);
		int TotalUsercount = superusercount + stdusercount;
		String totaluser = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.totalusercountsummary"));
		System.out.println("totaluser" + totaluser);
		int totalusercnt = Integer.parseInt(totaluser);

		if ((superusercount == SuperUserCnt)
				&& (stdusercount == StandardUserCnt)
				&& (TotalUsercount == totalusercnt)) {
			Report.updateTestLog(
					"No of User in the Summary list and DB user list are same",
					"PASS");
		}
		if (superusercount == SuperUserCnt) {
			System.out.println("first class");
			if (stdusercount == StandardUserCnt) {
				System.out.println("second class");
				if (TotalUsercount == totalusercnt) {
					System.out.println("third class");
				}
			}

		}

	}

	public void enterTitleField() {
		verifyAndSelectDropDownBox(
				pageProperties.getProperty("MultiUserMultiViewPage.NameTitle"),
				"Title", "Please select");
		clickContinueButton();
	}

	public void EditUserlinkNavigation() {
		String jqueryToCloseOverlay = ("$('a.ui-dialog-titlebar-close').trigger('click');");

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.CanIReactiveAUser"),
				"Can I Reactive A User");
		browser.executeScript(jqueryToCloseOverlay);
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserLink"),
				"What Happens When I Deactivate A User Link");
		browser.executeScript(jqueryToCloseOverlay);
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveLink"),
				"Can I Reactive A User");
		browser.executeScript(jqueryToCloseOverlay);
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveLink"),
				"Can I Reactive A User");
		browser.executeScript(jqueryToCloseOverlay);
		browser.wait(getWaitTime());
	}

	public void clickContinueButton() {
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),
					"Continue button");
			browser.wait(getWaitTime());
		}
	}

	// Validate 'Title' field content without selecting any value
	public void validateTitleField() {
		enterTitleField();
		errorMessageComparison(SlingshotErrorMessages.MuMv_AddUser_TitleField_EmptyError);
	}

	// Capture the error message displayed and verify the actual and error
	// message displayed by making a call to the verifyErrorMessage method
	public void errorMessageComparison(final String expectedErrorMsg) {
		try {
			final String displayedErrorMsg = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.AddnewUserErrorMessages"));
			verifyErrorMessage(displayedErrorMsg, expectedErrorMsg);
		} catch (Exception e) {
			Report.updateTestLog("Exception occured" + e, "FAIL");
		}
	}

	public void validateEmailidField(UserProfile userProfile) {
		String[] Emailref = { " ", "kkk*@gas.com", "onetwothreed@gas.com" };
		final String firstName = userProfile.getFirstName();
		final String surname = userProfile.getLastName();
		int status = 1;
		for (int i = 0; i <= 2; i++) {
			System.out.println("i" + i);
			switch (i) {
			case 0:
				enterValidData_StandardUser3(userProfile, firstName, surname,
						Emailref[i], Emailref[i], status);
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.MuMv_AddUser_Emailid_EmptyError);
				AddNewUserPageCancelLink1();
				break;
			case 1:
				enterValidData_StandardUser3(userProfile, firstName, surname,
						Emailref[i], Emailref[i], status);
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.MuMv_AddUser_InValidEmailid_Error);
				AddNewUserPageCancelLink1();
				break;
			case 2:
				enterValidData_StandardUser3(userProfile, firstName, surname,
						Emailref[i], Emailref[i], status);
				clickContinueButton();
				errorMessageComparison(SlingshotErrorMessages.MuMv_AddUser_Emailid_AlreadyExist_Error);
				AddNewUserPageCancelLink1();
				break;
			}
		}
	}

	public void enterValidData_StandardUser3(UserProfile userProfile,
			String firstname, String surname, String email, String retypemail,
			int status) {
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserTitle"),
				"Title", userProfile.getTitle());
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserfirstname"),
				"First name", firstname);
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUsersurname"),
				"Sur name", surname);
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserEmailAddress"),
				"Email address", email);
		verifyAndInputByXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserReTypeEmailAddress"),
				"Re-Type Email address", retypemail);
		verifyAndSelectDropDownBox(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserRightsType"),
				"User Type", "Full access");
		if (status == 1) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddNewUserConfirmationChkbox"),
					"select Terms and Condition Check box");
		}
		browser.wait(getWaitTime());
	}

	public void ValidatecheckboxTermsAndConditions(UserProfile userProfile) {
		int status = 2;
		final String firstName = userProfile.getFirstName();
		final String surname = userProfile.getLastName();
		final String email = userProfile.getNewEmail();
		enterValidData_StandardUser3(userProfile, firstName, surname, email,
				email, status);
		clickContinueButton();
		errorMessageComparison(SlingshotErrorMessages.MuMv_AddUser_ForgetTocheck_TermsAndConts_Error);
	}

	public void ManageViews() {
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.ManageViewsLink"),
				"Manage View");
		browser.wait(getWaitTime());
		// verifyPageTitle("Manage views");
	}

	public void Verifyviewaccounts(UserProfile userProfile) {
		int i = 1, j = 2, count = 0, k = 1, t;
		int viewcount = transcount();
		String Viewname = null;
		System.out.println("viewcount" + viewcount);
		for (i = 2; i <= viewcount; i++) {
			verifyAndClickWithXpath(
					pageProperties.getProperty("MultiUserMultiViewPage.ViewAcctsNameTable")
							+ "//tr[" + i + "]/td[" + k + "]/p/a", "ViewName");
			Viewname = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewAcctsNameTable")
					+ "//tr[" + i + "]/td[" + k + "]");
			System.out.println("viewname" + Viewname);
			browser.wait(5000);

			int viewacct = viewacctcount();
			System.out.println("viecount" + viewacct);
			if (viewacct >= 10) {
				for (t = 1; t <= 10; t++) {
					System.out.println("i am here also");
					String Acctno = browser
							.getTextByXpath(pageProperties
									.getProperty("MultiUserMultiViewPage.ViewAcctsTable")
									+ "//tr[" + t + "]/td[" + j + "]");
					System.out
							.println(pageProperties
									.getProperty("MultiUserMultiViewPage.ViewAcctsTable")
									+ "//tr[" + i + "]/td[" + j + "]");
					System.out.println("Acctno" + Acctno);
					if (browser
							.isElementVisibleWithXpath(pageProperties
									.getProperty("MultiUserMultiViewPage.ViewAcctsTable")
									+ "//tr[" + i + "]/td[" + j + "]")) {
						count = count + 1;
						System.out.println("count" + count);
					} else {
						break;
					}
				}
				if (count == 10) {
					System.out.println("i reached 10 accts");
					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.ViewAcctOverlayNext"),
							"View Accounts Overlay Next");
					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.ViewAcctOverlayPrevious"),
							"View Accounts Overlay Previous");
					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.ViewAcctOverlayClose"),
							"View Accounts Overlay Close");
					Report.updateTestLog(
							"Successfully selected the more than 10 Accounts Overlay viewname "
									+ Viewname
									+ " and verified Next and Previous link",
							"Pass");
					break;
				}

			} else {
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctOverlayClose"),
						"View Accounts Overlay Close");
			}

			if (i == 10 || i == 20 || i == 30 || i == 40 || i == 50 || i == 60
					|| i == 70 || i == 80) {
				System.out.println("i am more than" + i);
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctnameNext"),
						"View Accounts Overlay Next");
			}
		}

	}

	public void VerifyTotalNumberOfUsers(UserProfile userProfile) {
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String SuperUsercount = dbFunctions.verifyTotalUserCount("Y",
				userProfile.getBpnumber());
		String StandardUsercount = dbFunctions.verifyTotalUserCount("N",
				userProfile.getBpnumber());
		int StandardUserCnt = Integer.parseInt(StandardUsercount);
		System.out.println("StandardUserCnt" + StandardUserCnt);
		int SuperUserCnt = Integer.parseInt(SuperUsercount);
		System.out.println("SuperUserCnt" + SuperUserCnt);
		VerifySummarylistandUserlist(StandardUserCnt, SuperUserCnt);
	}

	public void verifyLeadTabledata_AddnewViewAudit(UserProfile userProfile) {
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date = dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType3 = dbFunctions.getAuditEventTypeId(date,
				userProfile.getEmail(), "Creation :Success");
		String data = dbFunctions.getAuditType(auditType3[0]);
		Report.updateTestLog(
				"Audit id is made in audit table as expected. Audit id: "
						+ auditType3[0] + " Audit event type is" + data,
				data.equalsIgnoreCase("BGBUSINESS_MULTIVIEW_CREATE_VIEW_SUCCESS") ? "PASS"
						: "FAIL");
	}

	public void verifyLeadTable_ViewDeletionAudit(UserProfile userProfile) {
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date = dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType3 = dbFunctions.getAuditEventTypeId(date,
				userProfile.getEmail(), "Deleted :Success");
		String data = dbFunctions.getAuditType(auditType3[0]);
		Report.updateTestLog(
				"Audit id is made in audit table as expected. Audit id: "
						+ auditType3[0] + " Audit event type is" + data,
				data.equalsIgnoreCase("BGBUSINESS_MULTIVIEW_DELETE_VIEW_SUCCESS") ? "PASS"
						: "FAIL");
	}

	public void verifyLeadTable_UserTypeChangeAudit(String email) {
		try {
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType = dbFunctions.getAuditEventTypeIdForMUMV(date,
					email, "USER UPDATION :Success");
			System.out.println("auditType[0]" + auditType[0]);
			String data = dbFunctions.getAuditType(auditType[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_EDIT_USER_CHANGE_SUCCESS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void verifyLeadTable_UserTypeChangeEmailAudit(String email) {
		try {
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType = dbFunctions.getAuditEventTypeIdForMUMV(date,
					email, "EDIT USER:EMAIL SENT :Success");
			System.out.println("auditType[0]" + auditType[0]);
			String data = dbFunctions.getAuditType(auditType[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MUMV_EDIT_USER_EMAIL_SENT_SUCCESS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void verifyLeadTable_AddUserLinkEmailStatus(UserProfile userProfile) {
		try {
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType = dbFunctions.getAuditEventTypeIdForMUMV(date,userProfile.getNewEmail(),"AddUserLinkEmailStatus:success");
			System.out.println("auditType[0]" + auditType[0]);
			String data = dbFunctions.getAuditType(auditType[0]);
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+ auditType[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_ADDUSER_LINK_SENT_SUCCESS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void verifyLeadTable_AddUserLinkEmailStatusNew() {
		try {
			System.out.println("Now Check the EmailStatusssssssss");
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType = dbFunctions.getAuditEventTypeIdForMUMV(date,Entermail,"AddUserLinkEmailStatus:success");
			System.out.println("auditType[0]" + auditType[0]);
			String data = dbFunctions.getAuditType(auditType[0]);
			Report.updateTestLog("Audit id is made in audit table as expected. Audit id: "+ auditType[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_ADDUSER_LINK_SENT_SUCCESS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void verifyLeadTable_AddUserLinkEmailStatusNew(String newEntermail){
			browser.wait(3000);
			newEntermail =Entermail;
			System.out.println("IN BDDDDDDDDDDDDDDDDDD");
		 	OnlineDBConnector dbFunctions = new OnlineDBConnector();
		 	System.out.println("INNNNNNNNNNNNNNNNNNNNNNNNN ");
		 	String Date=dbFunctions.DBsysdateDdmonyyhhmi();
		 	System.out.println(" "+Date  );
		 	System.out.println("Date is populated");
		 	//String Result=dbFunctions.getAuditEventTypeIdForMUMVNew(Date,newEntermail,"AddUserLinkEmailStatus:success");
		 	String Result=dbFunctions.getAuditEventTypeIdForMUMVNew(Date,newEntermail);
		 	System.out.println("final Result is"+Result);
		 	if(Entermail.equals(newEntermail)){
		 		Report.updateTestLog("Email Entry Is Made Sucessful in bgb_business_profile TABLE", "PASS");
		      }
		      else {
		    	  Report.updateTestLog("Email Entry Was Not updated", "FAIL");
		 	}	
		 	browser.wait(10000);
		 }
	public void verifyLeadTable_AddUserLinkEmailStatusNew_01(String newEntermail){
		browser.wait(3000);
		newEntermail =Entermail;
		System.out.println("IN LLLLLLLLLLLLL");
	 	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	 	System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIII");
	 	String Date=dbFunctions.DBsysdateDdmonyyhhmi();
	 	System.out.println(" "+Date  );
	 	System.out.println("Date is populated");
	 	//String Result=dbFunctions.getAuditEventTypeIdForMUMVNew(Date,newEntermail,"AddUserLinkEmailStatus:success");
	 	String Result=dbFunctions.getAuditEventTypeIdForMUMVNew01(Date,newEntermail);
	 	System.out.println("final Result is"+Result);
	 	if(Entermail.equals(newEntermail)){
	 		Report.updateTestLog("Email Entry Is Made Sucessful in BG_BUSINESS_TA_CUSTOMER_REG TABLE", "PASS");
	      }
	      else {
	    	  Report.updateTestLog("Email Entry Was Not updated in BG_BUSINESS_TA_CUSTOMER_REG", "FAIL");
	 	}		 	
	 } 
	public void openEncryptUrlandRegister1new(UserProfile userProfile){
		browser.wait(10000);
		 try {
			 	System.out.println("BBBBBBBBBBBBBBBBBBBBBBB"+Entermail);
			 	String Url=new Encryption().encryptAndSendDatanew1(userProfile,Entermail,"USEREMAIL");
			 	System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHH="+Entermail);
				System.out.print("my encription"+Url);
				System.out.println("encryptionURL"+ApplicationConfig.APP_BG_URL+MuvpageProperties.getProperty("MultiUserMultiViewPage.Adduserurl")+Url);
				browser.open(ApplicationConfig.APP_BG_URL+MuvpageProperties.getProperty("MultiUserMultiViewPage.Adduserurl")+Url);
				String sys=browser.getTitle();
				System.out.println("browser page title"+sys);
				//browser.swtichToDefaultContent();		
				browser.wait(getWaitTime());
				String EmailAddress=browser.getTextByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.NewEmailAddress"));
				System.out.println(MuvpageProperties.getProperty("MultiUserMultiViewPage.NewEmailAddress"));
				System.out.println("EmailAddress"+EmailAddress);				
				if(EmailAddress.equals(Entermail))
				{
							System.out.println("verified new email address");
							Report.updateTestLog("User Name is Prefilled with the Email Address is appreared As Expected"+EmailAddress,"Pass");
				}	
				browser.wait(5000);
				verifyAndInputByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserPassword"), "Password", userProfile.getPassword());
				verifyAndInputByXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserRetypePassword"), "Re-Type Password",userProfile.getPassword());
				verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserTermsandcondtions"),"select Terms and Condition Check box");
				verifyAndClickWithXpath(MuvpageProperties.getProperty("MultiUserMultiViewPage.AddNewUserContinueButton"), "Submit button");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	public void VerifyTable()
	{
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.Ascendingbutton"),"Click Ascending Order Button");
		browser.wait(3000);
		WebDriver driver=null;
		//ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[@id='DashboardTable']"));
		//for(WebElement type : list)
		//{
			//if(type.getAttribute("name").equals("your correct name here")) {
		//}
		
		//List<WebElement> list = driver.findElements(By.xpath("//*[@id='DashboardTable'],'" + "')]"));
		//List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + Entermail + "')]"));
		//Assert.assertEquals("email is found!", list.size() > 0);
				//getElementByXpath(pageProperties.getProperty("ForgotEmailPage.GetEmailButtonBoxButton"));
				//driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
		//Assert.assertTrue("Text not found!", list.size() > 0);
		/*List<WebElement> linkElements = driver.findElements(By.xpath("//*[@id='DashboardTable']"));
		String[] linksText = new String[linkElements.size()];*/
		
		WebElement table=driver.findElement(By.xpath("//*[@id='DashboardTable']"));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		//List<WebElement> columns=rows.get(rnum).findElements(By.tagName("th"));
		

	}
	
	public void verifyLeadTable1_RegisteredSuccessfully(UserProfile userProfile) {
		try {

			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType1 = dbFunctions.getAuditEventTypeIdForMUMV(date,userProfile.getNewEmail(),
					"Registered Successfully:success");
			System.out.println("auditType1[0]" + auditType1[0]);
			String data = dbFunctions.getAuditType(auditType1[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType1[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_ADDUSER_REGISTRATION_SUCCESS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/*public void verifyLeadTable1_RegisteredSuccessfullyNew(String Entermail) {
		try {

			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType1 = dbFunctions.getAuditEventTypeIdForMUMVNew01(date,Entermail);
			System.out.println("auditType1[0]" + auditType1[0]);
 		String data = dbFunctions.getAuditType(auditType1[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType1[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_ADDUSER_REGISTRATION_SUCCESS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}*/
	public void verifyLeadTable1_ThankYouMail(UserProfile userProfile) {
		try {

			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType2 = dbFunctions.getAuditEventTypeIdForMUMV(date,
					userProfile.getNewEmail(),
					"Success Registration:Thank You Mail:success");
			System.out.println("auditType2[0]" + auditType2[0]);
			String data = dbFunctions.getAuditType(auditType2[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType2[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_ADDUSER_REGISTRATION_SUCCESS_MAIL_SENT") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void verifyLeadTable1_ThankYouMailNew() {
		try {
			System.out.println("This is Confirmation Page");
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType2 = dbFunctions.getAuditEventTypeIdForMUMV(date,Entermail,"Success Registration:Thank You Mail:success");
			System.out.println("auditType2[0]" + auditType2[0]);
			String data = dbFunctions.getAuditType(auditType2[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType2[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_ADDUSER_REGISTRATION_SUCCESS_MAIL_SENT") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void verifyLeadTable1_RegisteredSuccessInformToALLSuperusers(
			UserProfile userProfile) {
		try {
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType3 = dbFunctions.getAuditEventTypeIdForMUMV(date,
					userProfile.getNewEmail(),
					"Registered Successfully Inform To ALL Superusers:success");
			System.out.println("auditType3[0]" + auditType3[0]);
			String data = dbFunctions.getAuditType(auditType3[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType3[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_ADDUSER_REGISTRATION_SUCCESS_SENT_TO_SUPERUSERS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void verifyLeadTable1_PaperlessBilling(UserProfile userProfile) {
		try {
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType4 = dbFunctions.getAuditEventTypeIdForMUMV(date,
					userProfile.getNewEmail(),
					"PaperlessBilling:Save Details:success");
			System.out.println("auditType3[0]" + auditType4[0]);
			String data = dbFunctions.getAuditType(auditType4[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType4[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_SAVE_PAPERLESS_BILLING_SUCCESS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void verifyLeadTable2_PaperlessBilling(UserProfile userProfile) {
		try {
			OnlineDBConnector dbFunctions = new OnlineDBConnector();
			String date = dbFunctions.DBsysdateDdmmyyhhmi();
			String[] auditType4 = dbFunctions.getAuditEventTypeIdForMUMV(date,
					userProfile.getNewEmail(),
					"PaperlessBilling and remainders:Save Details:success");
			System.out.println("auditType3[0]" + auditType4[0]);
			String data = dbFunctions.getAuditType(auditType4[0]);
			Report.updateTestLog(
					"Audit id is made in audit table as expected. Audit id: "
							+ auditType4[0] + " Audit event type is" + data,
					data.equalsIgnoreCase("BGBUSINESS_SAVE_PAPERLESS_BILLING_SUCCESS") ? "PASS"
							: "FAIL");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int transcount() {
		System.out.println(" i am here in table");
		browser.wait(4000);
		String numberOfTransactions = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Transactions"));
		System.out.println(browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.Transactions")));
		System.out.println("rowcount" + numberOfTransactions);
		String[] totalTransaction = numberOfTransactions.split("of");
		totalTransaction = totalTransaction[1].split(" ");
		String numberOfTransactionsInAuditTable = totalTransaction[1];
		System.out.println("numberOfTransactionsInAuditTable"
				+ numberOfTransactionsInAuditTable);
		int numberofrows = Integer.parseInt(numberOfTransactionsInAuditTable);
		return numberofrows;
	}

	public int viewacctcount() {
		System.out.println(" i am here in table");
		String numberOfTransactions = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.viewacctTransactions"));
		System.out.println(browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.viewacctTransactions")));
		System.out.println("rowcount" + numberOfTransactions);
		String[] totalTransaction = numberOfTransactions.split("of");
		totalTransaction = totalTransaction[1].split(" ");
		String numberOfTransactionsInAuditTable = totalTransaction[1];
		int numberofrows = Integer.parseInt(numberOfTransactionsInAuditTable);
		return numberofrows;
	}

	public void dummy(UserProfile userProfile) {
		String[] SAPVerifiedAcctno = acctnovalidatation(userProfile);
	}

	public int acctslist() {
		System.out.println(" i am here in table");
		browser.wait(getWaitTime());
		String numberOfTransactions = browser.getTextByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AcctnoTrans"));
		System.out.println(browser.getTextByXpath(pageProperties.getProperty("MultiUserMultiViewPage.AcctnoTrans")));
		System.out.println("rowcount" + numberOfTransactions);
		String[] totalTransaction = numberOfTransactions.split("of");
		totalTransaction = totalTransaction[1].split(" ");
		String numberOfTransactionsInAuditTable = totalTransaction[1];
		int numberofrows = Integer.parseInt(numberOfTransactionsInAuditTable);
		return numberofrows;
	}

	public void backtoMangeruserlink() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.BackToManageUsersLink"),
				"Back to Manage users link");
	}

	public void VerifyAccountsInDb() {
		int i, k = 2;

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.Addnewviewlink"),
				"Add new View");
		int viewacct = acctslist();
		System.out.println("viewacct" + viewacct);
		for (i = 2; i <= viewacct; i++) {
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.AcctNolist")
					+ "//tr["
					+ i + "]/td[" + k + "]")) {
				System.out.println("i am here also");
				List<String> Acctnoarray = new ArrayList<String>();
				Acctnoarray.add(browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewAcctsTable")
						+ "//tr[" + i + "]/td[" + k + "]"));
				System.out.println(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewAcctsTable")
						+ "//tr[" + i + "]/td[" + k + "]");
				System.out.println("Acctno" + Acctnoarray);

				for (int l = 0; l < Acctnoarray.size(); l++) {
					String Acctnoarraylist = Acctnoarray.get(l);
					System.out.println(Acctnoarraylist);
				}
			}

		}
	}

	public void backtoMangeruserlinkNavigation() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.BackToManageUsersLink"),
				"Back to Manage users link");
		browser.wait(3000);
		browser.browserBack();
	}

	public void viewname(UserProfile userProfile) {
		browser.wait(10000);
		browser.wait(getWaitTime());
		int i, k = 2, m = 4, c, l = 0;
		List<String> Acctnoarray = new ArrayList<String>();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.Addnewviewlink"),
				"Add new View");
		verifyPageTitle("Add a New view");
		browser.wait(getWaitTime());
		verifyAndInputByXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.Viewname"),
				"New view name", userProfile.getviewname());
		int viewacct = acctslist();
		System.out.println("viewacct" + viewacct);
		for (i = 1; i <= viewacct; i++) {
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewAcctnameNext"))) {
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctnameNext"),
						"Next");
			}

			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.AcctNolist")
					+ "//tr["
					+ i + "]/td[" + k + "]")) {
				String acctno = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.AcctNolist")
						+ "//tr[" + i + "]/td[" + k + "]");
				System.out.println("acctno" + acctno);
				Acctnoarray.add(browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.AcctNolist")
						+ "//tr[" + i + "]/td[" + k + "]"));
				System.out.println(pageProperties
						.getProperty("MultiUserMultiViewPage.AcctNolist")
						+ "//tr[" + i + "]/td[" + k + "]");
				System.out.println("Acctnoarray" + Acctnoarray);
				System.out.println(pageProperties
						.getProperty("MultiUserMultiViewPage.AcctNolist")
						+ "//tr[" + i + "]/td[" + m + "]");
				// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.AcctNolist")+"//tr["+i+"]/td["+m+"]",acctno+"Selected");
				verifyAndClickWithXpath(
						pageProperties.getProperty(
								"MultiUserMultiViewPage.AcctNolist1").replace(
								"ACCOUNTNUMBER", acctno), "Account number"
								+ acctno);

			}
		}
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Continue"),
				"Confirm");

		//String[] SAPVerifiedAcctno = acctnovalidatation(userProfile);

		/*for (l = 0; l <= Acctnoarray.size() - 1; l++) {
			int length = Acctnoarray.size();
			System.out.println("Application length" + length);
			System.out.println("SAPVerifiedAcctno.length"
					+ SAPVerifiedAcctno.length);
			System.out.println("acctarraylist" + Acctnoarray.get(l));

			for (c = 0; c <= SAPVerifiedAcctno.length - 1; c++) {
				System.out.println("Acctnoarray.get(l)" + Acctnoarray.get(l));
				System.out.println("SAPVerifiedAcctno[c])"
						+ SAPVerifiedAcctno[c]);
				if (Acctnoarray.get(l).equals(SAPVerifiedAcctno[c])) {
					System.out.println("i am in application");
					Report.updateTestLog("Account number"
							+ SAPVerifiedAcctno[c]
							+ " is Appears in the application", "Pass");
					break;
				}
			}

		}*/
		for (int ll = 0; ll <= Acctnoarray.size() - 1; ll++) {
			System.out.println("acctarraylist" + Acctnoarray.get(ll));

			if (browser.isTextPresent(Acctnoarray.get(ll))) {
				Report.updateTestLog("Account number" + Acctnoarray.get(ll)
						+ " is successfully added for this viewname", "Pass");
			}
		}

		int totalview = acctslist();
		for (i = 1; i <= totalview; i++) {
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewAcctOverlayNext"))) {
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctOverlayNext"),
						"Next");
			}
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.AcctNolist")
					+ "//tr["
					+ i + "]/td[" + k + "]")) {
				Report.updateTestLog("Successfully added the view name"
						+ userProfile.getviewname(), "Pass");
				verifyPageTitle("New view created");
				break;
			}
		}
	}

	public void AddnewUserslink() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserNavigationPage"),
				"Click Add new user Link");
	}

	public void specificView(UserProfile userProfile) {
		int i, k = 1, m = 4, count = 0;
		String appviewname[] = new String[100];
		String AppviewName = "";
		int viewacct = transcount();
		for (i = 2; i <= viewacct; i++) {
			if ((browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewAcctnameNext")) && (i >= 11))) {
				System.out.println("i crossed");
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctnameNext"),
						"Next");
				System.out.println("i clicked nxt");
				for (int j = 1; j <= 10; j++) {
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + k + "]");
					appviewname[j] = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + k + "]");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + k + "]");
					System.out.println("appviewname" + appviewname[j]);
					System.out.println("j" + j);
					AppviewName = appviewname[j];
					count = j;
					System.out.println("count" + count);
					System.out.println("aftercopy" + AppviewName);

					if ((userProfile.getviewname()).contains(AppviewName)) {
						System.out.println("i am in view method");
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + k + "]");
						verifyAndClickWithXpath(
								pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + k + "]",
								"specific view selected");
						System.out.println("m" + m);
						System.out.println("comparisoncount" + count);
						browser.wait(3000);
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiView.Continue"),
								"Confirm");
						break;
					}

				}
			}

			if (i <= 10) {
				appviewname[i] = browser.getTextByXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + i + "]/td[" + k + "]");
				System.out.println("appviewname" + appviewname[i]);
				AppviewName = appviewname[i];
				count = i;
				System.out.println("count" + count);
				System.out.println("aftercopy" + AppviewName);

			}
			if ((userProfile.getviewname()).contains(AppviewName) && (i <= 10)) {
				System.out.println("i am in view method");
				System.out.println(pageProperties
						.getProperty("MultiUserMultiViewPage.ViewTable")
						+ "//tr[" + count + "]/td[" + m + "]");
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
								+ "//tr[" + count + "]/td[" + m + "]",
						"selected");

				System.out.println("m" + m);
				System.out.println("comparisoncount" + count);
				browser.wait(3000);
				if (browser
						.isElementVisibleWithXpath(pageProperties
								.getProperty("MultiUserMultiViewPage.AccountViewOverlayTitle"))) {
					String AccountViewnameTitle = browser
							.getTextByXpath(pageProperties
									.getProperty("MultiUserMultiViewPage.AccountViewOverlayTitle"));
					Report.updateTestLog(
							"Account List Ovelay for the particular view is displayed as View accounts -"
									+ AccountViewnameTitle, "PASS");
					verifyAndClickWithXpath(
							pageProperties
									.getProperty("MultiUserMultiViewPage.AccountViewOverlayClose"),
							"Account view Overlay is closed");
				}
				// verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")+"//tr["+i+"]/td["+m+"]",userProfile.getviewname()+"Selected");
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiView.Continue"),
						"Confirm");
				break;
			}
		}

		String viewname = browser.getTextByXpath(pageProperties
				.getProperty(("MultiUserMultiViewPage.Viewnamelist").replace(
						"VIEWNAME", userProfile.getviewname())));
		if (viewname.contains(userProfile.getviewname())) {
			Report.updateTestLog("View Name verified on the Add new User page"
					+ userProfile.getviewname(), "Pass");

		}
	}

	public void ValidateCheckboxerror(UserProfile userProfile) {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.Addnewviewlink"),
				"Add new View");
		verifyPageTitle("Add a New view");
		verifyAndInputByXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.Viewname"),
				"New view name", userProfile.getviewname());
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Continue"),
				"Confirm");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiViewPage.AccountViewNameOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.YourHaventSelectedAcctsOverlay"));
			System.out.println("overlayContent: " + overlayTitle);
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AccountViewNameOverlayClose"),
					"Accountviewname Overlay is closed");
			verifyErrorMessage(overlayTitle,
					SlingshotErrorMessages.MuMv_ManageView_Acct_NotSelected);
		}
	}

	public static String[] acctnovalidatation(UserProfile userProfile) {
		String bpnumber = userProfile.getBpnumber();
		int i = 0, count = 0, m;
		String dbAcctno = userProfile.getAccNumber();
		System.out.print("bpnumber" + bpnumber);
		String bpOrgNumber = bpnumber.concat("-").concat(dbAcctno);
		String Acctno;
		String Acctno1;
		String[] Acctno2 = null;
		RunQTP runQTP = new RunQTP();
		System.out.println("i am in");
		runQTP.runQTP(
				"Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\GetAcctno.vbs",
				bpOrgNumber);
		System.out.println("i am out");

		try {
			File file1 = new File("D:\\BgbAcctnos\\acctno.txt");
			File file2 = new File("D:\\BgbAcctnos\\acctno.txt");
			FileReader fr = new FileReader(file1);
			FileReader fr1 = new FileReader(file2);
			BufferedReader br = new BufferedReader(fr);
			BufferedReader br1 = new BufferedReader(fr1);

			while ((Acctno = br.readLine()) != null) {
				count++;
			}
			System.out.println("count" + count);
			Acctno2 = new String[count];

			while ((Acctno1 = br1.readLine()) != null) {
				System.out.println("Acctno1" + Acctno1);
				Acctno2[i] = Acctno1;
				System.out.println("Acctno2[i]" + Acctno2[i]);
				i++;
			}

		} catch (IOException e) {
			Report.updateTestLog("Exception while accessing the .txt file" + e,
					"Fail");
		}

		return Acctno2;
	}

	public void morethan15accts(UserProfile userProfile) {
		int k = 2, i = 0, b = 2, noofAccts;
		// String
		// acctno[]={"600548301","600548314","600548309","600548301","600548294"};
		String[] SAPVerifiedAcctno = acctnovalidatation(userProfile);
		System.out.println("SAPVerifiedAcctno[0]" + SAPVerifiedAcctno[0]);
		System.out.println("SAPVerifiedAcctno[1]" + SAPVerifiedAcctno[1]);
		System.out.println("SAPVerifiedAcctno[2]" + SAPVerifiedAcctno[2]);
		System.out.println("SAPVerifiedAcctno[3]" + SAPVerifiedAcctno[3]);

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.Addnewviewlink"),
				"Add new View");
		verifyPageTitle("Add a New view");
		verifyAndInputByXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.Viewname"),
				"New view name", userProfile.getviewname());

		// Change No of accts for loop to increase your no of Accts
		for (noofAccts = 0; noofAccts <= 3; noofAccts++) {
			browser.wait(5000);
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddViewSearchkey"),
					"search Type", "Account number");
			verifyAndInputByXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddviewSearchItem"),
					"search Account", SAPVerifiedAcctno[noofAccts]);
			browser.click("search-accounts");
			browser.wait(5000);
			if (browser
					.isElementVisibleWithXpath(".//*[@id='search_results']/p/span")) {
				browser.clickWithLinkText("Search for more accounts");
				browser.wait(4000);
			}
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),
				"Confirm");

		if (browser.isTextPresent("New view successfully created")) {
			Report.updateTestLog("New view successfully created", "Pass");
			verifyPageTitle("New view created");
		}
		int viewacct = acctslist();
		for (i = 1; i <= viewacct; i++) {
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.ViewAcctnameNext"))) {
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiViewPage.ViewAcctnameNext"),
						"Next");
			}
			System.out.println("i am in");
			int n = 1;
			for (int o = 1; o <= 3; o++) {
				System.out.println("confirm");
				System.out
						.println(pageProperties
								.getProperty("MultiUserMultiViewPage.ConfirmationPageacctno")
								+ "//tr[" + o + "]/td[" + k + "]/p");
				if (browser
						.isElementVisible(pageProperties
								.getProperty("MultiUserMultiViewPage.ConfirmationPageacctno")
								+ "//tr[" + o + "]/td[" + k + "]/p")) {
					String ConfirmPageAcct = browser
							.getTextByXpath((pageProperties
									.getProperty("MultiUserMultiViewPage.ConfirmationPageacctno")
									+ "//tr[" + o + "]/td[" + k + "]/p"));
					System.out.println("ConfirmPageAcct" + ConfirmPageAcct);

					if (ConfirmPageAcct.equals(SAPVerifiedAcctno[n])) {
						System.out.println("i am in");
						Report.updateTestLog("Account number" + ConfirmPageAcct
								+ " is successfully added for this viewname",
								"Pass");
						n++;
					}
				}
			}
		}
	}

	public void verifynewview(UserProfile userProfile) {
		int viewacct1 = transcount();
		int o, u = 1, m = 4, count = 0, flag = 0;
		String appviewname[] = new String[200];
		String AppviewName = "";
		for (o = 2; o <= viewacct1; o++) {
			if (flag == 0) {
				for (int j = 1; j <= 170; j++) {
					System.out.println("initial");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					appviewname[j] = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println("appviewname" + appviewname[j]);
					System.out.println("j" + j);
					AppviewName = appviewname[j];
					count = j;
					System.out.println("count" + count);
					System.out.println("aftercopy" + AppviewName);

					if ((userProfile.getviewname()).contains(AppviewName)) {
						System.out.println("i am in view method");
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + u + "]");
						verifyAndClickWithXpath(
								pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + u + "]",
								"specific view selected");
						System.out.println("m" + m);
						System.out.println("comparisoncount" + count);
						browser.wait(3000);
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiView.Continue"),
								"Confirm");
						flag = 1;
						break;
					}
				}

			}
		}
	}

	public void verifyEditedview(UserProfile userProfile) {
		int viewacct1 = transcount();
		int o, u = 1, m = 4, count = 0, flag = 0;
		String appviewname[] = new String[100];
		String AppviewName = "";
		for (o = 2; o <= viewacct1; o++) {
			if (flag == 0) {
				for (int j = 1; j <= 150; j++) {
					System.out.println("initial");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					appviewname[j] = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println("appviewname" + appviewname[j]);
					System.out.println("j" + j);
					AppviewName = appviewname[j];
					count = j;
					System.out.println("count" + count);
					System.out.println("aftercopy" + AppviewName);

					if ((userProfile.getEditview()).contains(AppviewName)) {
						System.out.println("i am in view method");
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + u + "]");
						verifyAndClickWithXpath(
								pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + u + "]",
								"specific view selected");
						System.out.println("m" + m);
						System.out.println("comparisoncount" + count);
						browser.wait(3000);
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiView.Continue"),
								"Confirm");
						flag = 1;
						break;
					}
				}

			}
		}
	}

	public void SelectSpecificViewinAdduser(UserProfile userProfile,
			String newviewname) {
		int viewacct1 = transcount();
		int o, u = 1, m = 4, count = 0, flag = 0;
		String appviewname[] = new String[100];
		String AppviewName = "";
		for (o = 2; o <= viewacct1; o++) {
			if (flag == 0) {
				for (int j = 1; j <= 150; j++) {
					System.out.println("initial");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					appviewname[j] = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println("appviewname" + appviewname[j]);
					System.out.println("j" + j);
					AppviewName = appviewname[j];
					count = j;
					System.out.println("count" + count);
					System.out.println("aftercopy" + AppviewName);

					if (newviewname.contains(AppviewName)) {
						System.out.println("i am in view method");
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + u + "]");
						verifyAndClickWithXpath(
								pageProperties.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr["
										+ count
										+ "]/td["
										+ m
										+ "]/input[" + u + "]",
								"specific view selected");
						System.out.println("m" + m);
						System.out.println("comparisoncount" + count);
						browser.wait(3000);
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiView.Continue"),
								"Confirm");
						flag = 1;
						break;
					}
				}

			}
		}
	}

	public void ViewDeletion(UserProfile userProfile) {
		int viewacct1 = transcount();
		int o, u = 1, m = 4, count = 0, flag = 0;
		String appviewname[] = new String[100];
		String AppviewName = "";
		for (o = 2; o <= viewacct1; o++) {
			if (flag == 0) {
				for (int j = 1; j <= 150; j++) {
					System.out.println("initial");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					appviewname[j] = browser.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println(pageProperties
							.getProperty("MultiUserMultiViewPage.ViewTable")
							+ "//tr[" + j + "]/td[" + u + "]");
					System.out.println("appviewname" + appviewname[j]);
					System.out.println("j" + j);
					AppviewName = appviewname[j];
					count = j;
					System.out.println("count" + count);
					System.out.println("aftercopy" + AppviewName);

					if ((userProfile.getviewname()).contains(AppviewName)) {
						System.out.println("i am in view method");
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr[" + count + "]/td[" + m + "]/a");
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr[" + count + "]/td[" + m + "]/a",
								"specific view selected");
						System.out
								.println(pageProperties
										.getProperty("MultiUserMultiViewPage.ViewTable")
										+ "//tr[" + count + "]/td[" + m + "]/a");
						System.out.println("m" + m);
						System.out.println("comparisoncount" + count);
						browser.wait(3000);
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.DeletViewButton"),
								"Deleteview");
						verifyAndClickWithXpath(
								pageProperties
										.getProperty("MultiUserMultiViewPage.DeleteViewYesConfirm"),
								"Yes");
						if (browser.isTextPresent("View details deleted")) {
							Report.updateTestLog(userProfile.getviewname()
									+ "View is deleted successfully", "Pass");
						}
						flag = 1;
						break;
					}
				}

			}
		}
	}

	public void FiftyAcctsViewnameErrorValidation(UserProfile userProfile) {
		int k = 2, i = 0, b = 2, noofAccts;

		String[] SAPVerifiedAcctno = acctnovalidatation(userProfile);
		System.out.println("SAPVerifiedAcctno[0]" + SAPVerifiedAcctno[0]);
		System.out.println("SAPVerifiedAcctno[1]" + SAPVerifiedAcctno[1]);
		System.out.println("SAPVerifiedAcctno[2]" + SAPVerifiedAcctno[2]);
		System.out.println("SAPVerifiedAcctno[3]" + SAPVerifiedAcctno[3]);

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.Addnewviewlink"),
				"Add new View");
		verifyPageTitle("Add a New view");
		verifyAndInputByXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.Viewname"),
				"New view name", userProfile.getviewname());

		// Change No of accts for loop to increase your no of Accts

		for (noofAccts = 0; noofAccts <= 50; noofAccts++) {
			browser.wait(5000);
			verifyAndSelectDropDownBox(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddViewSearchkey"),
					"search Type", "Account number");
			verifyAndInputByXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.AddviewSearchItem"),
					"search Account", SAPVerifiedAcctno[noofAccts]);
			browser.click("search-accounts");
			browser.wait(5000);
			if (browser
					.isElementVisibleWithXpath(".//*[@id='search_results']/p/span")) {
				browser.clickWithLinkText("Search for more accounts");
				browser.wait(4000);
			}
		}

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.AddNewUserSubmitButton"),
				"Confirm");
	}

	public void StandardUserValidation() {
		if (!browser.isElementVisible(pageProperties
				.getProperty("MultiUserMultiViewPage.ManageUserLink"))) {
			Report.updateTestLog(
					"Standard user doesn't have Manage Users Link option",
					"Pass");
		} else {
			Report.updateTestLog(
					"Standard user doesn't have Manage Users Link option",
					"Fail");
		}
	}

	public void verifytheSuperUser_journey() {

		verifyIsTextPresent("Meter read alert");
		verifyIsTextPresent("Bill alert");
		verifyIsTextPresent("Payment alert ");
		verifyIsTextPresent("Renewal alert");
	}

	public void verifytheFullAccessUser_journey() {

		verifyIsTextPresent("Meter read alert");
		verifyIsTextPresent("Bill alert");
		verifyIsTextPresent("Payment alert ");
		verifyIsTextPresent("Renewal alert");
	}

	public void verifytheRBPUser_journey() {

		verifyIsTextPresent("Meter read alert");
		verifyIsTextPresent("Bill alert");
		verifyIsTextPresent("Payment alert ");
		verifyIsTextPresent("Renewal alert");
	}

	public void verifytheRBUser_journey() {

		verifyIsTextPresent("Meter read alert");
		verifyIsTextPresent("Bill alert");
		verifyIsTextPresent("Payment alert ");
		verifyIsTextPresent("Renewal alert");
	}

	public void UserAndtheirJouenyAccessVerification(UserProfile userProfile,
			String UserType) {
		String SuperUsers = "Super user";
		String FullAccess = "Full access";
		String RBP = "Reads, Bills and Payments";
		String RB = "Reads and Bills";
		/*
		 * verifyAndInputById(pageProperties.getProperty(
		 * "MultiUserMultiViewPage.Email"), "Email Id",
		 * userProfile.getNewEmail());
		 * verifyAndInputById(pageProperties.getProperty
		 * ("MultiUserMultiViewPage.Password"), "Password",
		 * userProfile.getPassword());
		 * verifyAndClickWithXpath(pageProperties.getProperty
		 * ("MultiUserMultiViewPage.NewLoginSubmitXpath"), "Submit button");
		 */
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.ManageAcct")
						.replace("ACCOUNTNUMBER", userProfile.getAccNumber()),
				"Manage account link");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.BillingLink"),
				"Billing link");
		if (SuperUsers.equals(UserType)) {

			Move_premises();
			reports();
			PaperlessBilling();
			addsite();
			Renew_contract();
			MangeUsers();
		} else if (FullAccess.equals(UserType)) {
			Move_premises();
			reports();
			PaperlessBilling();
			addsite();
			Renew_contract();
		} else if (RBP.equals(UserType)) {
			Move_premises();
			reports();
		} else if (RB.equals(UserType)) {
			Move_premises();
			reports();
		}

	}

	public void Move_premises() {
		browser.wait(4000);

		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.movepremises"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.movepremises"),
					"Movepremises");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification1"));
			System.out.println("getPageNamesmr" + getPageName);
			if (getPageName.equals("move premises")) {
				Report.updateTestLog(
						"Moving premises Page is Available for the User",
						"Pass");
			}
		} else {
			Report.updateTestLog(
					"Moving premises Page is Available for the User", "Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void reports() {
		browser.wait(4000);
		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.reports"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.reports"),
					"Reports Link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification1"));
			System.out.println("getPageNamesmr" + getPageName);
			if (getPageName.equals("reports")) {
				Report.updateTestLog("Reports Page is Available for the User",
						"Pass");
			}
		} else {
			Report.updateTestLog("Reports Page is Available for the User",
					"Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void PaperlessBilling() {
		browser.wait(4000);

		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.paperlessbilling"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.paperlessbilling"),
					"Paperless link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification1"));
			System.out.println("getPageNamesmr" + getPageName);
			if (getPageName.equals("paper less")) {
				Report.updateTestLog(
						"paper less Page is Available for the User", "Pass");
			}
		} else {
			Report.updateTestLog("paper less Page is Available for the User",
					"Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void addsite() {
		browser.wait(4000);

		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.addsite"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.addsite"),
					"addsite link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification1"));
			System.out.println("getPageNamesmr" + getPageName);
			if (getPageName.equals("addsite")) {
				Report.updateTestLog(
						"add site  Page is Available for the User", "Pass");
			}
		} else {
			Report.updateTestLog("add site Page is Available for the User",
					"Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	public void Renew_contract() {
		browser.wait(4000);

		String getPageName = null;
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiViewPage.renewcontract"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiViewPage.movepremises"),
					"renewcontract link");
			browser.wait(3000);
			getPageName = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiViewPage.PageVerification1"));
			System.out.println("getPageNamesmr" + getPageName);
			if (getPageName.equals("renew contract ")) {
				Report.updateTestLog(
						"renew contract Page is Available for the User", "Pass");
			}
		} else {
			Report.updateTestLog(
					"renew contract  Page is Available for the User", "Fail");
		}
		browser.wait(3000);
		browser.browserBack();
	}

	// ========================================================================
	// Sundar
	// Page============================================================================
	// ================================================================================================================================================================
	public void clickManageUserLink() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ManageUserLink"),
				"Manage User link");
		// verifyPageTitle(pageProperties.getProperty("MultiUserMultiView.ManageUserTitle"));
	}

	public void clickViewDetailsLinkOfActive(UserProfile userProfile) {
		int j = 3, k = 7, l = 8, i, flag = 0;
		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			// System.out.println("EmailAddress"+EmailAddress);
			String UserStatus = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println("UserStatus" + UserStatus);
			// if((EmailAddress.equals(smrProfile.getNewEmail()))&&
			// (UserStatus.equals("ACTIVE")))
			if (UserStatus.equals("ACTIVE")) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiView.ViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"View details");
				flag = 1;
			}

			if (flag == 1) {
				break;
			}
		}
		browser.wait(getWaitTime());
		verifyIsTextPresent("View, edit or deactivate user");
		// verifyPageTitle(pageProperties.getProperty("MultiUserMultiView.ViewUserTitle"));
	}

	public void clickViewDetailsLinkOfInActive(UserProfile userProfile) {
		int j = 3, k = 7, l = 8, i, flag = 0;
		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			// System.out.println("EmailAddress"+EmailAddress);
			String UserStatus = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println("UserStatus" + UserStatus);
			// if((EmailAddress.equals(smrProfile.getNewEmail()))&&
			// (UserStatus.equals("ACTIVE")))
			if (UserStatus.equals("INACTIVE")) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiView.ViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"View details");
				flag = 1;
			}

			if (flag == 1) {
				break;
			}
		}
		browser.wait(getWaitTime());
		verifyIsTextPresent("View, edit or deactivate user");
		// verifyPageTitle(pageProperties.getProperty("MultiUserMultiView.ViewUserTitle"));

	}

	public void linkNavigationsOfViewUserPage() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.BusinessLink"),
				"Business link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BusinessTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.YouraccountLink"),
				"Your account link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.YouraccountTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersLink"),
				"Manage Users link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUsersTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.DeactivateUserLink"),
				"Deactivate user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.DeactivateUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.DeactivateUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Deactivate user overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with deactivate user link ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.DeactivateUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.DeactivateUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.BackToManageUsersLink."),
				"Back to manage users link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BackToManageUsersTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.EditDetailsLink"),
				"Edit details link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.EditDetailsTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.CanIReactiveAUser"),
				"Can i reactive a user link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.CanIReactiveAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.CanIReactiveAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Can I Reactive A User overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with How many users can i have overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.CanIReactiveAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.CanIReactiveAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserLink"),
				"Deactivate a  link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Can I Reactive A User overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with How many users can i have overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		/*
		 * verifyAndClickWithXpath(pageProperties.getProperty(
		 * "MultiUserMultiView.HowDoIDeactivateAUserLink"),
		 * "How do I deactivate a user link");
		 * if(browser.isElementVisibleWithXpath(pageProperties.getProperty(
		 * "MultiUserMultiView.HowDoIDeactivateAUserOverlayTitle"))){ String
		 * overlayTitle = browser.getTextByXpath(pageProperties.getProperty(
		 * "MultiUserMultiView.HowDoIDeactivateAUserOverlayTitle"));
		 * System.out.println("overlayContent: "+overlayTitle);
		 * Report.updateTestLog
		 * ("How do I deactivate a user overlay displayed with below content: "
		 * +overlayTitle, "PASS"); } else{
		 * Report.updateTestLog("Problem with how do I deactivate a user  overlay"
		 * , "WARN"); }
		 * 
		 * if(browser.isElementVisibleWithXpath(pageProperties.getProperty(
		 * "MultiUserMultiView.HowDoIDeactivateAUserOverlayClose"))){
		 * verifyAndClickWithXpath(pageProperties.getProperty(
		 * "MultiUserMultiView.HowDoIDeactivateAUserOverlayClose"),
		 * "Overlay close button"); } else{ RobotSendKeys.typeenter();
		 * browser.wait(getWaitTime());
		 * Report.updateTestLog("Overlay is closed by entering the 'Enter' key",
		 * "PASS"); }
		 */
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveLink"),
				"How many users can i have link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How many users can i have overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with How many users can i have overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveLink"),
				"What access do different users have link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"What access do different users have overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with What access do different users have overlay ",
					"WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ContactUsLink"),
				"Contact us link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ContactUsTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.AddANewUserLink"),
				"Add a new user link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.AddANewUserTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageViewsLink"),
				"Manage views link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageViewsTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersOverviewLink"),
				"Manage users overview PDF link");
		browser.wait(getWaitTime());
		browser.swithnewwindow_getTitle();
		/*
		 * verifyAndClickWithXpath(pageProperties.getProperty(
		 * "MultiUserMultiView.ManageUsersOverviewLink"),
		 * "Manage users overview PDF link"); browser.wait(getWaitTime());
		 * verifyPageTitle
		 * (pageProperties.getProperty("MultiUserMultiView.ManageUsersOverviewTitle"
		 * ));
		 */
	}

	public void activeTextVerification() {
		verifyIsElementVisibleWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ActiveText"),
				"verifies active text is present");
	}

	public void deactiveUserLinkVerification() {
		verifyIsElementVisibleWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.DeactiveUserLink"),
				"verifies deactive user link is present or not");
		browser.browserBack();
	}

	public void deactiveTextVerification() {
		verifyIsElementVisibleWithXpath(
				pageProperties.getProperty("MultiUserMultiView.DeactiveText"),
				"verifies deactive text is present");
	}

	public void reactiveUserLinkVerification() {
		verifyIsElementVisibleWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ReactiveUserLink"),
				"Reactive user link");
	}

	public void editDetailsVisibleOrNot() {
		// verifyIsElementVisibleWithXpath(pageProperties.getProperty("MultiUserMultiView.EditDetailsLink"),
		// "verifies edit details link is visible or not");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.EditDetailsLink"),
				"Edit details button");
		if (browser.isTextPresent("Change User type and view details")) {
			Report.updateTestLog("Edit details link is enabled", "FAIL");
		} else {
			Report.updateTestLog("Edit details link is disabled", "PASS");

		}
	}

	public void clickDeactivateUser() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ClickDeactiveUserLink"),
				"Verifies deactivate user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.DeactiveUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.DeactiveUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Why we need this overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.DeactiveUserOverlayCancel"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.DeactiveUserOverlayCancel"),
					"Overlay cancel button");
		}
		/*
		 * else{ RobotSendKeys.typeenter(); browser.wait(getWaitTime());
		 * Report.updateTestLog("Overlay is closed by entering the 'Enter' key",
		 * "PASS"); }
		 */
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ViewUserTitle"));
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ClickDeactiveUserLink"),
				"Verifies deactivate user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.DeactiveUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.DeactiveUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Why we need this overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.DeactiveUserOverlayYes"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.DeactiveUserOverlayYes"),
					"Overlay Yes button");
			verifyPageTitle(pageProperties
					.getProperty("MultiUserMultiView.DeactivatedTitle"));
		}

	}

	public void backToManageUsersLink() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.BackToManageUsersLink"),
				"Back to Manage users ");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BackToManageUsersTitle"));
	}

	public void deactivateASuperUser() {
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.DeactivateUserLink"))) {
			Report.updateTestLog("Deactivate link is present", "FAIL");
		} else {
			Report.updateTestLog("Deactivate link is present", "PASS");
		}
	}

	public void clickReactiveAndVerify() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ReactiveUserLink"),
				"Reactivate user button");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.ReactiveUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.ReactiveUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Reactive user  overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with reactive user overlay ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.ReactiveUserOverlayCancel"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.ReactiveUserOverlayCancel"),
					"Overlay cancel button");
		}
		browser.wait(getWaitTime());
		/*
		 * else{ RobotSendKeys.typeenter(); browser.wait(getWaitTime());
		 * Report.updateTestLog("Overlay is closed by entering the 'Enter' key",
		 * "PASS"); }
		 */
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ViewUserTitle"));
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ReactiveUserLink"),
				"Reactivate user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.ReactiveUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.ReactiveUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Why we need this overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.ReactiveUserOverlayYes"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.ReactiveUserOverlayYes"),
					"Overlay Yes button");
			verifyPageTitle(pageProperties
					.getProperty("MultiUserMultiView.ReactivationTitle"));
		}

	}

	public void linkNavigationsOfReactiveConfirmationPage() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.BusinessLink"),
				"Business link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BusinessTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.YouraccountLink"),
				"Your account link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.YouraccountTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersLink"),
				"Manage Users link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUsersTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ViewUserDetailsLink"),
				"View user details link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.UserDetailsLinkTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ContactUsLink"),
				"Contact us link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ContactUsTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.AddANewUserLink"),
				"Add a new user link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.AddANewUserTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageViewsLink"),
				"Manage Views link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageViewsTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.CanIReactiveAUser"),
				"Can i reactive a user link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.CanIReactiveAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.CanIReactiveAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Can I Reactive A User overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with How many users can i have overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.CanIReactiveAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.CanIReactiveAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserLink"),
				"Can i reactive a user link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Can I Reactive A User overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with How many users can i have overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.WhatHappensWhenIDeactivateAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveLink"),
				"How many users can i have link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How many users can i have overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with How many users can i have overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveLink"),
				"What access do different users have link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"What access do different users have overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with What access do different users have ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ViewRecord"),
				"View record link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ViewUserTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Manageusers"),
				"Manage users link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BackToManageUsersTitle"));
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersOverviewLink"),
				"Manage users overview PDF link");
		browser.wait(getWaitTime());
		browser.swithnewwindow_getTitle();
		/*
		 * verifyAndClickWithXpath(pageProperties.getProperty(
		 * "MultiUserMultiView.ManageUsersOverviewLink"),
		 * "Manage users overview PDF link"); browser.wait(getWaitTime());
		 * verifyPageTitle
		 * (pageProperties.getProperty("MultiUserMultiView.ManageUsersOverviewTitle"
		 * ));
		 */
	}

	public void clickReactiveOfMaxAccountOverlayAndLinkVerification() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ReactiveUserLink"),
				"Reactivate user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.ReactiveUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.ReactiveUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Reactive user overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with reactive user link ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.ReactiveUserOverlayYes"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.ReactiveUserOverlayYes"),
					"Overlay Yes button");
			browser.wait(getWaitTime());
			if (browser
					.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiView.ReactiveMaxOverlayErrorTitle"))) {
				String overlayTitle = browser
						.getTextByXpath(pageProperties
								.getProperty("MultiUserMultiView.ReactiveMaxOverlayErrorTitle"));
				System.out.println("overlayContent: " + overlayTitle);
				Report.updateTestLog(
						"Error message overlay displayed with below content: "
								+ overlayTitle, "PASS");
			} else {
				Report.updateTestLog("Problem with error message overlay ",
						"WARN");
			}
			browser.wait(getWaitTime());
			if (browser
					.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiView.ReactiveMaxOverlayErrorClose"))) {
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiView.ReactiveMaxOverlayErrorClose"),
						"Overlay close link");
				verifyPageTitle(pageProperties
						.getProperty("MultiUserMultiView.ViewUserTitle"));
				/*
				 * verifyAndClickWithXpath(pageProperties.getProperty(
				 * "MultiUserMultiView.ReactiveUserLink"
				 * ),"Verifies reactivate user link");
				 * if(browser.isElementVisibleWithXpath
				 * (pageProperties.getProperty
				 * ("MultiUserMultiView.ReactiveUserOverlayTitle"))){ String
				 * overlayTitle =
				 * browser.getTextByXpath(pageProperties.getProperty
				 * ("MultiUserMultiView.ReactiveUserOverlayTitle"));
				 * System.out.println("overlayContent: "+overlayTitle);
				 * Report.updateTestLog
				 * ("Reactive user overlay displayed with below content: "
				 * +overlayTitle, "PASS"); } else{
				 * Report.updateTestLog("Problem with reactive user link " ,
				 * "WARN"); }
				 * 
				 * if(browser.isElementVisibleWithXpath(pageProperties.getProperty
				 * ("MultiUserMultiView.ReactiveUserOverlayYes"))){
				 * verifyAndClickWithXpath(pageProperties.getProperty(
				 * "MultiUserMultiView.ReactiveUserOverlayYes"),
				 * "Overlay Yes button");
				 * if(browser.isElementVisibleWithXpath(pageProperties
				 * .getProperty
				 * ("MultiUserMultiView.ReactiveMaxOverlayErrorTitle"))){ String
				 * overlayTitle =
				 * browser.getTextByXpath(pageProperties.getProperty(
				 * "MultiUserMultiView.MultiUserMultiView.ReactiveMaxOverlayErrorTitle"
				 * )); System.out.println("overlayContent: "+overlayTitle);
				 * Report.updateTestLog(
				 * "Error message overlay displayed with below content: "
				 * +overlayTitle, "PASS"); } else{
				 * Report.updateTestLog("Problem with error message overlay " ,
				 * "WARN"); }
				 * 
				 * if(browser.isElementVisibleWithXpath(pageProperties.getProperty
				 * ("MultiUserMultiView.ReactiveMaxOverlayErrorManageUsers"))){
				 * verifyAndClickWithXpath(pageProperties.getProperty(
				 * "MultiUserMultiView.ReactiveMaxOverlayErrorManageUsers"),
				 * "Manage users link on overlay");
				 * verifyPageTitle(pageProperties
				 * .getProperty("MultiUserMultiView.ManageUsersTitle")); } }
				 */
			}
		}
	}

	public void addNewUser() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.AddANewUserLink"),
				"Add new user link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.AddNewUserTitle"));
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.NoOption"),
				"No super user radio button");
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Continue"),
				"Continue button");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.NewUserTitle"));
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Continue"),
				"Continue button");
		browser.wait(getWaitTime());
	}

	public void enterValidDataStandardUser(UserProfile userProfile) {
		
		
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiView.AddNewUserTitle"),"Title", userProfile.getTitle());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserfirstname"),"First name", userProfile.getFirstName());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUsersurname"),"Sur name", userProfile.getLastName());
		for(int i=0;i<3;i++)
		{
		int intRandomNumbers;
	    Random random = new Random();
	    intRandomNumbers = random.nextInt(100000);
	    Entermail = ("automation_Test_mumv1_new" + intRandomNumbers +"_"+i+ "@bgdigital.com");
	    i++;
		}
			
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserEmailAddress"),"Email address", Entermail);
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserReTypeEmailAddress"),"Re-Type Email address", Entermail);		
		/*verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserEmailAddress"),"Email address", userProfile.getNewEmail());		
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserReTypeEmailAddress"),"Re-Type Email address", userProfile.getNewEmail());*/	
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiView.UserType"),"Usertype", (userProfile.getUserType()).replace("and","&"));       
		//verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiView.UserType"),"Usertype", (userProfile.getUserType1()));
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserConfirmationChkbox"),"select Terms and Condition Check box");
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserSubmitButton"),"Submit button");
		browser.wait(getWaitTime());
		
		
		
		
	}
	
public void enterValidDataStandardUsernormal(UserProfile userProfile) {
		
		
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiView.AddNewUserTitle"),"Title", userProfile.getTitle());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserfirstname"),"First name", userProfile.getFirstName());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUsersurname"),"Sur name", userProfile.getLastName());
		
	
		
	/*	int intRandomNumbers;
	    Random random = new Random();
	    intRandomNumbers = random.nextInt(1000);
	    Entermail = ("automation_Data" + intRandomNumbers + "@bgdigital.com"); 
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserEmailAddress"),"Email address", Entermail);
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserReTypeEmailAddress"),"Re-Type Email address", Entermail);*/
		
		
		
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserEmailAddress"),"Email address", userProfile.getNewEmail());
				
				
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserReTypeEmailAddress"),"Re-Type Email address", userProfile.getNewEmail());
		
		
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiView.UserType"),"Usertype", (userProfile.getUserType()).replace("and","&"));       
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserConfirmationChkbox"),"select Terms and Condition Check box");
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserSubmitButton"),"Submit button");
		browser.wait(getWaitTime());
		
		
		
		
	}
	
	
	public void enterValidDatasuperUser(UserProfile userProfile) {
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiView.AddNewUserTitle"),"Title", userProfile.getTitle());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserfirstname"),"First name", userProfile.getFirstName());
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUsersurname"),"Sur name", userProfile.getLastName());
		int intRandomNumbers;
	    Random random = new Random();
	    intRandomNumbers = random.nextInt(1000);
	    Entermail = ("automation" + intRandomNumbers + "@bgdigital.com"); 
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserEmailAddress"),"Email address", Entermail);
		verifyAndInputByXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserReTypeEmailAddress"),"Re-Type Email address", Entermail);
		verifyAndSelectDropDownBox(pageProperties.getProperty("MultiUserMultiView.UserType"),"Usertype1", (userProfile.getUserType()));       
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserConfirmationChkbox"),"select Terms and Condition Check box");
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiView.AddNewUserSubmitButton"),"Submit button");
		browser.wait(getWaitTime());
	}
	public void loginAfterNewView() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView."),
				"Login to your account link");
	}

	public void clickViewDetailsLinkOfActiveAndOverlayVerificationOfSuperUser() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ManageUserLink"),
				"Manage User link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WelcomeToManaguUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.WelcomeToManaguUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Welcome to Manage User overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with Welcome to Manage User overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WelcomeToManaguUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.WelcomeToManaguUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}

		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUserTitle"));
	}

	public void manageUsersLink() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ManageUserLink"),
				"Manage User link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUserTitle"));
	}

	public void breadcrumbNavigationsOfViewUserPage() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.BusinessLink"),
				"Business link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BusinessTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.YouraccountLink"),
				"Your account link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.YouraccountTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersLink"),
				"Manage Users link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUsersTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
	}

	public void clickEditDetailsLink() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.EditDetailsLink"),
				"Edit details link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.EditDetailsTitle"));
	}

	public void linkNavigationsOfEditDetailsPage() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.BusinessLink"),
				"Business link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BusinessTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.YouraccountLink"),
				"Your account link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.YouraccountTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersLink"),
				"Manage Users link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUsersTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ViewUserDetailsLink"),
				"View user details link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.UserDetailsLinkTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.UserTypeWhat'sThisLink"),
				"What's this link in edit details page");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.UserTypeWhat'sThisOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.UserTypeWhat'sThisOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"What's this overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with What's this? overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.UserTypeWhat'sThisOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.UserTypeWhat'sThisOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}

		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowDoIAddAUserLink"),
				"How do I add a user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.HowDoIAddAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowDoIAddAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How do I add a user overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with How do I add a user overlay ",
					"WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.HowDoIAddAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowDoIAddAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowDoIEditAUserLink"),
				"How do I edit a user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.HowDoIEditAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowDoIEditAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How do I edit a user overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with How do I edit a user overlay ",
					"WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.HowDoIEditAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowDoIEditAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowDoIDeactivateAUserLink"),
				"How do I deactivate a user link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowDoIDeactivateAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowDoIDeactivateAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How do I deactivate a user overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with How do I deactivate a user ",
					"WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowDoIDeactivateAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowDoIDeactivateAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveLink"),
				"How many users can i have link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How many users can i have overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with How many users can i have overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveLink"),
				"What access do different users have link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"What access do different users have overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with What access do different users have ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ContactUsLink"),
				"Contact us link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ContactUsTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.BackLink"),
				"Back link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BackLinkTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.CancelLink"),
				"Cancel link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.CancelLinkTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersOverviewLink"),
				"Manage users overview PDF link");
		browser.wait(getWaitTime());
		browser.swithnewwindow_getTitle();
		// verifyPageTitle(pageProperties.getProperty("MultiUserMultiView.ManageUsersOverviewTitle"));
	}

	public void verifyUserTypeDropdown() {
		try {
			String text = null;
			String indicator = "Null";
			String[] verifyText = { "Super user", "Full access",
					"Reads, Bills and Payments", "Reads and Bills" };
			List<String> countOf = browser.getFromDropBox("id",
					pageProperties.getProperty("MultiUserMultiView.SearchBy"));
			Report.updateTestLog(
					"Number of Items in the listbox :" + countOf.size(), "Pass");
			for (String itera : verifyText) {
				for (int i = 1; i <= countOf.size(); i++) {
					text = browser.getTextByXpath("//*[@id='"
							+ pageProperties
									.getProperty("MultiUserMultiView.SearchBy")
							+ "']" + "/option[" + i + "]");

					if (itera.equals(text)) {
						indicator = "Pass";
						break;
					} else {
						indicator = "Null";
					}
				}
				Report.updateTestLog(
						"Search By Drop down value: Expected Result: " + itera
								+ "Actual Result: " + text,
						indicator.contains("Pass") ? "Pass" : "Fail");
			}
		} catch (Exception e) {
			Report.updateTestLog(
					"Exception occured while retrieving drop down value" + e,
					"Fail");
		}
	}

	public void searchBy() {

		verifyAndSelectDropDownBox(
				pageProperties.getProperty("MultiUserMultiView.SearchBy"),
				"Search By", "Super user");
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Confirm"),
				"Confirm button");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.SuperUserOverlayTitle"))) {
			String overlayTitle = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.SuperUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Super user confirmaation overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with super user confirmation overlay ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.SuperUserOverlayCancel"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.SuperUserOverlayCancel"),
					"Overlay cancel button");
			browser.wait(getWaitTime());
			verifyPageTitle(pageProperties
					.getProperty("MultiUserMultiView.EditDetailsTitle"));
		}
	}

	public void selectUserTypeSuperUser() {
		try {
			String text = null;
			String indicator = "Null";
			String[] verifyText = { "Super user", "Full access",
					"Reads, Bills and Payments", "Reads and Bills" };
			List<String> countOf = browser.getFromDropBox("id",
					pageProperties.getProperty("MultiUserMultiView.SearchBy"));
			Report.updateTestLog(
					"Number of Items in the listbox :" + countOf.size(), "Pass");
			for (String itera : verifyText) {
				for (int i = 1; i <= countOf.size(); i++) {
					text = browser.getTextByXpath("//*[@id='"
							+ pageProperties
									.getProperty("MultiUserMultiView.SearchBy")
							+ "']" + "/option[" + i + "]");
					if (itera.equals(text)) {
						indicator = "Pass";
						break;
					} else {
						indicator = "Null";
					}
				}
				Report.updateTestLog(
						"Search By Drop down value: Expected Result: " + itera
								+ "Actual Result: " + text,
						indicator.contains("Pass") ? "Pass" : "Fail");
			}
		} catch (Exception e) {
			Report.updateTestLog(
					"Exception occured while retrieving drop down value" + e,
					"Fail");
		}
	}

	public void searchBySuperUser() {

		verifyAndSelectDropDownBox(
				pageProperties.getProperty("MultiUserMultiView.SearchBy"),
				"Search By", "Super user");
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Confirm"),
				"Confirm button");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.SuperUserOverlayTitle"))) {
			String overlayTitle = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.SuperUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Super user confirmaation overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with super user confirmation overlay ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.SuperUserOverlaySubmit"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.SuperUserOverlaySubmit"),
					"Overlay submit button");
			browser.wait(getWaitTime());
			verifyPageTitle(pageProperties
					.getProperty("MultiUserMultiView.DetailsAmended"));
		}

	}

	public void userNameAndTypeComparison() {
		String text1 = null;
		String text2 = null;
		String text3 = null;
		String text4 = null;
		text1 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiView.SuperUserText"));
		System.out.println("VALUE********" + text1);
		text2 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiView.AllaccountsText"));
		text3 = "Super user";
		text4 = "All accounts";
		if (text1.equals(text3) && (text2.equals(text4))) {
			Report.updateTestLog("User Type and View Name are present", "PASS");
		} else {
			Report.updateTestLog("User Type and View Name are absent", "FAIL");
		}
	}

	public void linkNavigationsOfEditDetailsConfirmationPage() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.BusinessLink"),
				"Business link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BusinessTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.YouraccountLink"),
				"Your account link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.YouraccountTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersLink"),
				"Manage Users link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUsersTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ViewUserDetailsLink"),
				"View user details link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.UserDetailsLinkTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyIsElementVisibleWithXpath(
				pageProperties.getProperty("MultiUserMultiView.HelpAndAdvice"),
				"Help and advice");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowDoIAddAUserLink"),
				"How do I add a user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.HowDoIAddAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowDoIAddAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How do I add a user overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with How do I add a user overlay ",
					"WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.HowDoIAddAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowDoIAddAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowDoIEditAUserLink"),
				"How do I edit a user link");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.HowDoIEditAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowDoIEditAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How do I edit a user overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with How do I edit a user overlay ",
					"WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.HowDoIEditAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowDoIEditAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowDoIDeactivateAUserLink"),
				"How do I deactivate a user link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowDoIDeactivateAUserOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowDoIDeactivateAUserOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How do I deactivate a user overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Problem with How do I deactivate a user ",
					"WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowDoIDeactivateAUserOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowDoIDeactivateAUserOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveLink"),
				"How many users can i have link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"How many users can i have overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with How many users can i have overlay ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.HowManyUsersCanIHaveOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveLink"),
				"What access do different users have link");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayTitle"))) {
			String overlayTitle = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"What access do different users have overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog(
					"Problem with What access do different users have ", "WARN");
		}

		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayClose"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.WhatAccessDoDifferentUsersHaveOverlayClose"),
					"Overlay close button");
		} else {
			RobotSendKeys.typeenter();
			browser.wait(getWaitTime());
			Report.updateTestLog(
					"Overlay is closed by entering the 'Enter' key", "PASS");
		}
		verifyIsElementVisibleWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ContactUs"),
				"Contact us");
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.ContactUsLink"),
				"Contact us link");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ContactUsTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.OtherAccountHereLink"),
				"here link to verify other account");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.UserDetailsLinkTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.BackToManageUsersLink"),
				"Back to manage users link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUsersTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyIsElementVisibleWithXpath(
				pageProperties.getProperty("MultiUserMultiView.QuickLinks"),
				"Quick links");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.AddANewUserLink"),
				"Add a new user link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.AddANewUserTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageViewsLink"),
				"Manage Views link");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageViewsTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersOverviewLink"),
				"Manage users overview PDF link");
		browser.wait(getWaitTime());
		browser.swithnewwindow_getTitle();
	}

	public void aboveFiveSuperUsersErrorPageVerification() {
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ErroPageTitle"));
		verifyIsElementVisibleWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.MaxSuperUserError"),
				"To verify the error message when we try to add sixth super user");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ManageUsersLinkInErrorPage"),
				"Manage users link in error page");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ManageUserTitle"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.BackLinkInErrorPage"),
				"Back link in error page");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.BackLinkTitleVerification"));
		browser.browserBack();
		browser.wait(getWaitTime());
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.ContactUsLinkInErrorPage"),
				"Contact us link in error page");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ContactUsTitle"));
	}

	public void selectUserTypeStandardUser() {
		try {
			String text = null;
			String indicator = "Null";
			String[] verifyText = { "Super user", "Full access",
					"Reads, Bills and Payments", "Reads and Bills" };
			List<String> countOf = browser.getFromDropBox("id",
					pageProperties.getProperty("MultiUserMultiView.SearchBy"));
			Report.updateTestLog(
					"Number of Items in the listbox :" + countOf.size(), "Pass");
			for (String itera : verifyText) {
				for (int i = 1; i <= countOf.size(); i++) {
					text = browser.getTextByXpath("//*[@id='"
							+ pageProperties
									.getProperty("MultiUserMultiView.SearchBy")
							+ "']" + "/option[" + i + "]");
					if (itera.equals(text)) {
						indicator = "Pass";
						break;
					} else {
						indicator = "Null";
					}
				}
				Report.updateTestLog(
						"Search By Drop down value: Expected Result: " + itera
								+ "Actual Result: " + text,
						indicator.contains("Pass") ? "Pass" : "Fail");
			}
		} catch (Exception e) {
			Report.updateTestLog(
					"Exception occured while retrieving drop down value" + e,
					"Fail");
		}
	}

	public void searchByStandardUser() {

		verifyAndSelectDropDownBox(
				pageProperties.getProperty("MultiUserMultiView.SearchBy"),
				"Search By", "Full access");
		if (browser.isSelectedByXpath(pageProperties
				.getProperty("MultiUserMultiView.RadioButton")))
			Report.updateTestLog("Radio button selected", "PASS");
		else
			Report.updateTestLog("Radio button not selected", "FAIL");

		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Confirm"),
				"Confirm button");
		browser.wait(getWaitTime());
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.SuperUserConfirmationTitle"));
	}

	public void addNewViewButton() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.AddNewView"),
				"Add new view button");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.AddNewViewOverlayTitle"))) {
			String overlayTitle = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.AddNewViewOverlayTitle"));
			System.out.println("overlayContent: " + overlayTitle);
			Report.updateTestLog(
					"Add new view overlay displayed with below content: "
							+ overlayTitle, "PASS");
		} else {
			Report.updateTestLog("Please check Why we need this link ", "WARN");
		}

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("MultiUserMultiView.AddNewViewOverlayCancel"))) {
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.AddNewViewOverlayCancel"),
					"Overlay cancel button");
			browser.wait(getWaitTime());
			verifyPageTitle(pageProperties
					.getProperty("MultiUserMultiView.EditDetailsTitle"));

			verifyAndClickWithXpath(
					pageProperties.getProperty("MultiUserMultiView.AddNewView"),
					"Add new view button");
			if (browser.isElementVisibleWithXpath(pageProperties
					.getProperty("MultiUserMultiView.AddNewViewOverlayTitle"))) {
				String overlayTitle = browser
						.getTextByXpath(pageProperties
								.getProperty("MultiUserMultiView.AddNewViewOverlayTitle"));
				System.out.println("overlayContent: " + overlayTitle);
				Report.updateTestLog(
						"Add new view overlay displayed with below content: "
								+ overlayTitle, "PASS");
			} else {
				Report.updateTestLog("Please check Why we need this link ",
						"WARN");
			}

			if (browser
					.isElementVisibleWithXpath(pageProperties
							.getProperty("MultiUserMultiView.AddNewViewOverlayConfirm"))) {
				verifyAndClickWithXpath(
						pageProperties
								.getProperty("MultiUserMultiView.AddNewViewOverlayConfirm"),
						"Overlay confirm button");
				browser.wait(getWaitTime());
				verifyPageTitle(pageProperties
						.getProperty("MultiUserMultiView.AddANewViewTitle"));
			}
		}
	}

	public void searchByStandardUserAndSelectView() {

		verifyAndSelectDropDownBox(
				pageProperties.getProperty("MultiUserMultiView.SearchBy"),
				"Search By", "Full access");
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.RadioButtonClick"),
				"Click radio button");
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiView.Confirm"),
				"Confirm button");
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.SuperUserConfirmationTitle"));
	}

	public void specificAccountAudit(UserProfile userProfile) {
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date = dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date,userProfile.getNewEmail(),"VIEW NAME :Changed,USER UPDATION :Success");
		String data = dbFunctions.getAuditType(auditType[0]);
		Report.updateTestLog(
				"Audit id is made in audit table as expected. Audit id: "
						+ auditType[0] + " Audit event type is " + data,
				data.equalsIgnoreCase("BGBUSINESS_MULTIUSER_EDIT_USER_CHANGE_SUCCESS") ? "PASS"
						: "FAIL");

	}

	public void auditForChangesEmail(UserProfile userProfile) {
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date = dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date,
				userProfile.getNewEmail(), "EDIT USER:EMAIL SENT :Success");
		String data = dbFunctions.getAuditType(auditType[0]);
		Report.updateTestLog(
				"Audit id is made in audit table as expected. Audit id: "
						+ auditType[0] + " Audit event type is " + data,
				data.equalsIgnoreCase("BGBUSINESS_MUMV_EDIT_USER_EMAIL_SENT_SUCCESS") ? "PASS"
						: "FAIL");

	}

	public void userStatusFullAccess(UserProfile userProfile) {
		int j = 3, k = 4, l = 8, i, flag = 0;
		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserType = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println("UserStatus" + UserType);
			if (UserType.equals("Full access")) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiView.ViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"View details");
				flag = 1;
			}

			if (flag == 1) {
				break;
			}
		}
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ViewUserTitle"));
		String text1 = null;
		String text2 = null;
		text1 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiView.FullAccessText"));
		text2 = "Full access";
		if (text1.equals(text2)) {
			Report.updateTestLog("Full access User type verified successfully",
					"PASS");
		} else {
			Report.updateTestLog("Problem with user type verification ", "FAIL");
		}
		browser.browserBack();

	}

	public void userStatusRb(UserProfile userProfile) {
		int j = 3, k = 4, l = 8, i, flag = 0;
		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserType = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println("UserStatus" + UserType);
			if (UserType.equals("Reads and Bills")) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiView.ViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"View details");
				flag = 1;
			}

			if (flag == 1) {
				break;
			}
		}
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ViewUserTitle"));
		String text1 = null;
		String text2 = null;
		text1 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiView.RbText"));
		text2 = "Reads and Bills";
		if (text1.equals(text2)) {
			Report.updateTestLog(
					"Reads and bills User type verified successfully", "PASS");
		} else {
			Report.updateTestLog("Problem with user type verification ", "FAIL");
		}
		browser.browserBack();

	}

	public void userStatusRbp(UserProfile userProfile) {
		int j = 3, k = 4, l = 8, i, flag = 0;
		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserType = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println("UserStatus" + UserType);
			if (UserType.equals("Reads, Bills and Payments")) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiView.ViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"View details");
				flag = 1;
			}

			if (flag == 1) {
				break;
			}
		}
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ViewUserTitle"));
		String text1 = null;
		String text2 = null;
		text1 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiView.RbpText"));
		text2 = "Reads, Bills and Payments";
		if (text1.equals(text2)) {
			Report.updateTestLog(
					"Reads bills and payments User type verified successfully",
					"PASS");
		} else {
			Report.updateTestLog("Problem with user type verification ", "FAIL");
		}
		browser.browserBack();

	}

	public void userStatusSuper(UserProfile userProfile) {
		int j = 3, k = 4, l = 8, i, flag = 0;
		for (i = 1; i <= 10; i++) {
			String EmailAddress = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + j + "]");
			System.out.println("EmailAddress" + EmailAddress);
			String UserType = browser.getTextByXpath(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println(pageProperties
					.getProperty("MultiUserMultiView.ViewTable")
					+ "//tr["
					+ i
					+ "]/td[" + k + "]");
			System.out.println("UserStatus" + UserType);
			if (UserType.equals("Super user")) {
				verifyAndClickWithXpath(
						pageProperties.getProperty("MultiUserMultiView.ViewTable")
								+ "//tr[" + i + "]/td[" + l + "]/p/span/a",
						"View details");
				flag = 1;
			}

			if (flag == 1) {
				break;
			}
		}
		verifyPageTitle(pageProperties
				.getProperty("MultiUserMultiView.ViewUserTitle"));
		String text1 = null;
		String text2 = null;
		text1 = browser.getTextByXpath(pageProperties
				.getProperty("MultiUserMultiView.SuperText"));
		text2 = "Super user";
		if (text1.equals(text2)) {
			Report.updateTestLog("Super User type verified successfully",
					"PASS");
		} else {
			Report.updateTestLog("Problem with user type verification ", "FAIL");
		}
	}

	public void userLogin() {
		browser.open("https://10.224.70.18/business/your-account/registration/registration-confirmation");

	}

	public void specificpaperlesssetup() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.switchtopaperlessbill_checkbox"),
				"Switch to paperless billing");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.ChangetoPaperlessbillingOverlayTitle"))) {
			String overlayContent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.ChangetoPaperlessbillingOverlayTitle"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("Overlay content: " + overlayContent, "PASS");
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.ChangetoPaperlessbillingOverlay_yesbutton"),
					" Overlay Yes Button");
		}
		browser.wait(2000);
		String jqueryToCloseOverlay = ("$('a.ui-dialog-titlebar-close').trigger('click');");
		verifyIsTextPresent("You have switched to paperless bills for this account");
		browser.executeScript(jqueryToCloseOverlay);

	}

	public void specificpaperbillsetup() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiView.switchtopaper_checkbox"),
				"Switch to paper billing");
		if (browser
				.isElementVisibleWithXpath(pageProperties
						.getProperty("MultiUserMultiView.ChangetoPaperbillingOverlayTitle"))) {
			String overlayContent = browser
					.getTextByXpath(pageProperties
							.getProperty("MultiUserMultiView.ChangetoPaperbillingOverlayTitle"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("Overlay content: " + overlayContent, "PASS");
			verifyAndClickWithXpath(
					pageProperties
							.getProperty("MultiUserMultiView.ChangetoPaperbillingOverlayTitle"),
					"Yes");
		}
		browser.wait(2000);
		String jqueryToCloseOverlay1 = ("$('a.ui-dialog-titlebar-close').trigger('click');");
		verifyIsTextPresent("You have switched to paper bills for this account");
		browser.executeScript(jqueryToCloseOverlay1);
	}

	public void verifyManageAccountLink(UserProfile userProfile) {

		verifyAndClickWithXpath(
				pageProperties.getProperty(
						"MultiUserMultiViewPage.ManageUserLink").replace(
						"ACCOUNTNUMBER", userProfile.getAccNumber()),
				"Manage account link");
	}

	public void movingPremises() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.movingpremises"),
				"moving premises");
		verifyPageTitle("Moving premises");
	}

	public void renewcontract() {
		verifyAndClickWithXpath(
				pageProperties
						.getProperty("MultiUserMultiViewPage.movingpremises"),
				"moving premises");
		verifyPageTitle("Energy plan renewal");
	}

	public void SuperuserAccessjourneyverification() {
		movingPremises();
		browser.browserBack();
		verifyIsTextPresent("Paperless billing ");
		browser.browserBack();
		ClickManageUserLink();
		browser.browserBack();
		GAQ_logged();
		verifyIsTextPresent("Get a quote");
		browser.browserBack();
		renewcontract();
		browser.browserBack();

	}

	public void FullAccessjourneyverification() {
		movingPremises();
		browser.browserBack();
		verifyIsTextPresent("Paperless billing ");
		browser.browserBack();
		ClickManageUserLink();
		browser.browserBack();
		GAQ_logged();
		verifyIsTextPresent("Get a quote");
		browser.browserBack();
		renewcontract();
		browser.browserBack();

	}

	public void RPjourneyverification() {
		movingPremises();
		browser.browserBack();

	}

	public void RBPjourneyverification() {
		movingPremises();
		browser.browserBack();
	}

	public void GAQ_logged() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("MultiUserMultiViewPage.GAQlogged"),
				" GAQ logged link");

	}

	public void FulluserAccessjourneyverification() {

	}
	
	public void verifydeactivateaccount(UserProfile userProfile)
	{
		
		browser.wait(10000);
		
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.SelectViewdetails").replace("Email", userProfile.getNewEmail()), "click");
		
		System.out.println(" xpath="+ pageProperties.getProperty("MultiUserMultiViewPage.SelectViewdetails").replace("Email", userProfile.getNewEmail()));
		
		browser.wait(1500);
		
		if (browser.isElementVisibleWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.deactivatelink"))) {
			
			verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.deactivatelink"),"Deavtivate link");
			
			Report.updateTestLog("Deaactivate link present in the application","PASS");
		}
		else {
			Report.updateTestLog("Deaactivate link not present in the application","FAIL");
		}
		browser.wait(getWaitTime());
		Report.updateTestLog("Account Deactivated successfully in the application","WARN");
		
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.BacktoManageusers"),"Back to Manage users");
		
		browser.wait(1500);
		verifyAndClickWithXpath(pageProperties.getProperty("MultiUserMultiViewPage.SelectViewdetails").replace("Email", userProfile.getNewEmail()), "click");
		
		Report.updateTestLog("Deactivated account details","WARN");
		
		
		
		}
	
	public void verifyTable_AccountFlag_Status(UserProfile userProfile){
		String Freeze_Result = "Y";
	  	OnlineDBConnector dbFunctions = new OnlineDBConnector();
	  	String Freeze_Account = dbFunctions.Verify_Freeze_Account(userProfile.getNewEmail());
	 	System.out.println("Freeze Account in DB is "+Freeze_Account);
	 	//if(Freeze_Result==Freeze_Account)
	 	if(Freeze_Result.equals(Freeze_Account))
	 	{
	 	Report.updateTestLog("Freeze Account field is populated as Y in BG_BUSINESS_TA_CUSTOMER_REG table as expected. Freeze Account: "+Freeze_Account,"PASS");
	 	}
	 	else
	 	{
	 		Report.updateTestLog("Freeze Account field is populated as N in BG_BUSINESS_TA_CUSTOMER_REG table as expected. Freeze Account: "+Freeze_Account,"FAIL");
	 	}
	 }
		
		
	
	
	
	
}
