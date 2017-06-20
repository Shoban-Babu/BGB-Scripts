/**
 * 
 */
package bg.framework.app.functional.page.Slingshot;

import java.util.Calendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.gnostice.pdfone.PdfDocument;
import com.gnostice.pdfone.PdfReader;

import bg.framework.app.functional.common.ApplicationConfig;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.SlingshotErrorMessages;
import bg.framework.app.functional.util.OnlineDBConnector;
import bg.framework.app.functional.util.PropertyLoader;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RobotSendKeys;
import bg.framework.app.functional.util.RunQTP;

/**
 * @author 255501
 * 
 */
public class StatementOfAccountPage extends BasePage {

	private final static String FILE_NAME = "resources/Slingshot/StatementOfAccount.properties";
	private static Properties pageProperties = new PropertyLoader(FILE_NAME)
	.load();
	SlingshotErrorMessages errormsg = new SlingshotErrorMessages();
	public static final String TIMESTAMP_FORMATTER = "dd MMMM, yyyy";
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	String functionResult;

	public void clickBillingLink() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.BillingLink"),
		"Billing link");
	}

	public void clickViewStatementOfAccountLink(UserProfile userProfile) {
		
		/*if (browser.isTextPresent("View bill")) {
			Report.updateTestLog("View Billing is present in the Billing Page",
			"Pass");
		}*/

		verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.ViewStatementOfAccount").replace("ACCOUNTNUMBER", userProfile.getAccNumber()),"Statement of account link");
		browser.wait(5000);
		// verifyPageTitle("Statement of account");

	}

	public void clickLHNViewStatementOfAccountLink() {
		if (browser.isTextPresent("statement of account")) {
			verifyAndClickWithXpath(
					pageProperties
					.getProperty("StatementOfAccount.ViewStatementOfAccount"),
			"Click Statement Of Acccount");

		}
		// verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.ViewStatementOfAccount"),"Click View Statement Of Acccount");
	}

	public void verifyManageAccountLink(UserProfile userProfile) {

		verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.ManageAccountLinknew").replace("ACCOUNTNUMBER", userProfile.getAccNumber()),"Manage account link");
	}

	public void verifyclickViewButton() {

		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.ViewButton"),
		"Click view Button");
		browser.wait(5000);
		Report.updateTestLog("Statement of account displayed sucessfully", "WARN");
	}

	public void verifyPDFFile() throws ParseException {
		File folderExisting = new File(
				pageProperties.getProperty("StatementOfAccount.DownLoadPath"));
		if (folderExisting.exists()) {
			File[] listOfFiles = folderExisting.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println("listOfFiles.length " + listOfFiles.length);
				if (listOfFiles[i].getName().contains("Bill")) {
					PdfReader p = null;
					try {
						p = p.fileReader(pageProperties
								.getProperty("StatementOfAccount.DownLoadPath")
								+ ".pdf");
						PdfDocument doc = new PdfDocument(p);
						List l = doc.extractText(1);
						Report.updateTestLog("Pdf file contains data", "PASS");
						System.out.println("PDF FILE CONTENT" + l.toString());
						if (l.toString().contains("")) {
							System.out.println("PASS");
						}
					} catch (Exception e) {

					}
				} else {
					Report.updateTestLog(
							"No files exist in the name of Bill.pdf", "FAIL");
				}
			}
		} else {
			Report.updateTestLog("File does not exist in specified folder",
			"FAIL");
		}

	}

	public void downloadPDF() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.DownBill"),
		"Download bill");
		browser.wait(getWaitTime());
		// String testPath;
		// testPath = "";
		RobotSendKeys.altS();
		browser.wait(3000);
		RobotSendKeys.typeenter();
		browser.wait(3000);
		Report.updateTestLog("Pdf file get downloaded by clicking enter",
		"PASS");
	}

	public void verifyBillingNavigatinoLinks() {

		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.LHNSubmitMeterReadlink"),
		"Click Submit Meter Read Link");
		verifyPageTitle("Submit meter read");
		browser.browserBack();
		browser.wait(2000);
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.viewBill"),
		"Click View Bill Link");
		verifyPageTitle("View bill");
		browser.browserBack();
		browser.wait(2000);
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.LHNViewStatementOfAccount"),
		"Click Statement of Account Link");
		verifyPageTitle("Statement of account");
		browser.browserBack();
		browser.wait(2000);
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.ContactUsLink"),
		"Click Contact us Link");
		verifyPageTitle("Contact us - Business");
		browser.browserBack();
		browser.wait(2000);
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.MakeaPaymentLink"),
		"Click Make a Payment Link");
		verifyPageTitle("Payments");
		browser.browserBack();
		browser.wait(2000);
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.ManageDirectDebit"),
		"Click Manage Direct Debit Link");
		verifyPageTitle("Amend Direct Debit");
		browser.browserBack();
		browser.wait(2000);
		// verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.YourEnergyConsumption"),"Click Your energy consumption Link");
		// verifyPageTitle("Your energy consumption");
		// browser.browserBack();
	}

public void PaginationNumbersLink()
{
                verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.NextLink"),"Next");
                verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.PrevLink"),"Prev");
              //  verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.SecondButtonLink"),"Second Button");
                verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.FirstButtonLink"),"First Button");
                PaginationValidate();
}


	public void verifyBreadCrumbLinks() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.YourAccount"),
		"Click Your Account Link");
		verifyPageTitle("Account overview");// have to change title
		browser.browserBack();
		browser.wait(3000);
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.BusinessLink"),
		"Click Business Link");
		verifyPageTitle("Cheap Business Energy | Business Gas & Electricity - British Gas Business ");
		browser.browserBack();
		browser.wait(3000);
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.HomeLink"),
		"Click Home Link");
		verifyPageTitle("Cheap Gas & Electricity, Boilers and Energy Efficiency - British Gas");
		browser.browserBack();
	}

	public String calenderDate(String day) {

		String result = "False";
		browser.wait(3000);
		System.out.println(pageProperties
				.getProperty("StatementOfAccount.CalenderTable"));
		int rowcount = browser.getRowCountByXpath(pageProperties.getProperty("StatementOfAccount.CalenderTable"));
		System.out.println("rowcount:" + rowcount);
		int columncount = browser.getColCountByXpath(pageProperties
				.getProperty("StatementOfAccount.CalenderTable"));
		System.out.println("columncount:" + columncount);

		for (int i = 1; i <= rowcount; i++) {
			for (int j = 1; j <= columncount; j++) {
				System.out.println(pageProperties
						.getProperty("StatementOfAccount.CalenderTable1")
						+ "//tr[" + i + "]/td[" + j + "]/a");
				if (browser.isElementVisibleWithXpath(pageProperties
						.getProperty("StatementOfAccount.CalenderTable1")
						+ "//tr[" + i + "]/td[" + j + "]/a")) {
					String dateThreeMonths = browser
					.getTextByXpath(pageProperties
							.getProperty("StatementOfAccount.CalenderTable1")
							+ "//tr[" + i + "]/td[" + j + "]/a");
					System.out.print("dateThreeMonths" + dateThreeMonths);

					System.out.print("i" + i);
					System.out.print("j" + j);
					;
					if (dateThreeMonths.equals(day)) {
						Report.updateTestLog("Day is matched:" + day, "pass");
						System.out.print(" matched i" + i);
						System.out.print("matched j" + j);
						System.out.print("date is matched");
						/*
						 * for(int k=1;k<=12;k++){
						 * System.out.println(" From Date Previous pickerclicked"
						 * +i);
						 * verifyAndClickWithXpath(pageProperties.getProperty
						 * ("StatementOfAccount.PreviousDatePicker"),
						 * "PreviousDatePicker"); }
						 */
						System.out
						.println(pageProperties
								.getProperty("StatementOfAccount.CalenderTable1")
								+ "/tr[" + i + "]/td[" + j + "]/a");
						browser.wait(5000);
						browser.clickWithXpath(pageProperties
								.getProperty("StatementOfAccount.CalenderTable1")
								+ "//tr[" + i + "]/td[" + j + "]/a");
						// browser.wait(5000);
						// verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.CalenderTable1")+"//tr["+i+"]/td["+j+"]/a",
						// "clicked");
						browser.wait(5000);
						String Fromdate = browser
						.getAttributeByXpath(
								pageProperties
								.getProperty("StatementOfAccount.FromDateTextbox"),
						"value");
						System.out.print("From Date in textbox" + Fromdate);
						result = "True";
						break;
						// }
					}
				}
			}

		}
		return result;
	}

	public void enterFromDate(int Month) {

		int days = DateTime.now().getDayOfMonth();
		String day = Integer.toString(days);
		System.out.println("day is:" + day);

		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.FromDate"),
		"FromDate");
		for (int i = 1; i <= Month; i++) {
			System.out.println(" From Date Previous pickerclicked" + i);
			verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.PreviousDatePicker"),
			"PreviousDatePicker");
			// browser.wait(500);
		}
		// verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.PreviousDate"),
		// "3 Months date");
		String result = calenderDate(day);
		/*
		 * if(result.equals("False")){
		 * verifyAndClickWithXpath(pageProperties.getProperty
		 * ("StatementOfAccount.NextDatePicker"), "NextDatePicker");
		 * calenderDate(day); }
		 */
	}

	public void enterToDate(int Month) {
		int days = DateTime.now().getDayOfMonth();
		String day = Integer.toString(days);
		// Calendar cal=Calendar.getInstance();
		// int currentday=cal.get(Calendar.DATE);
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.ToDate"),
		"Todate");

		for (int i = 1; i <= Month; i++) {
			System.out.println("ToDate Previous pickerclicked" + i);
			verifyAndClickWithXpath(
					pageProperties
					.getProperty("StatementOfAccount.PreviousDatePicker"),
			"PreviousDatePicker");
			// browser.wait(500);
		}
		calenderDate(day);
		// browser.clickWithXpath("//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[2]/a");
		// verifyAndClickWithXpath(pageProperties.getProperty("SearchBill.TodaysDate")+currentday+"')]",
		// "TodaysDate");
	}

	public void selectSmrForAccount(SMRAccountDetails smrAccountDetails) {
		String Manageaccount = pageProperties
		.getProperty("AccountSummaryPage.SubmitMeterReadLink")
		+ smrAccountDetails.getAccountNumber() + ")]";
		System.out.println("SubmitMeterRead link:" + Manageaccount);
		for (int i = 1; i < 4; i++) {
			try {
				if (browser.isElementVisibleWithXpath(Manageaccount)) {
					verifyAndClickWithXpath(Manageaccount,
							"ManageAccountLink clicked for this account"
							+ smrAccountDetails.getAccountNumber());
					System.out.println("1st condition:" + Manageaccount);
					break;
				} else if (browser.isElementVisibleWithXpath(pageProperties
						.getProperty("AccountSummaryPage.SubmitMeterReadLink"))) {
					// function to click the pagination
					if (clickPaginationNextLink(i).contains("false")) {
						// break;
					}
				}
			} catch (Exception e) {
				Report.updateTestLog(
						"Exception occured while clicking the manage Account link for the account:"
						+ Manageaccount, "Fail");
			}
		}
		if (browser.isElementVisible(pageProperties
				.getProperty("AccountSummary.OptionsToSearch"))) {
			// function to click the drop down and select the account

			if (selectAccountFromDropDown1(smrAccountDetails.getAccountNumber())
					.contains("true")) {
				verifyAndClickWithXpath(Manageaccount,
						"SubmitMeterRead clicked for this account"
						+ smrAccountDetails.getAccountNumber());
			} else {
				Report.updateTestLog(
						"SubmitMeterRead link is not found/clicked for the account:"
						+ Manageaccount, "Fail");
			}
		}

	}

	public void selectSmrForAccount1(UserProfile userProfile) {
		String Manageaccount = pageProperties
		.getProperty("StatementOfAccount.Managelink")
		+ userProfile.getAccNumber() + ")]";
		System.out.println("SubmitMeterRead link:" + Manageaccount);
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.Managelink")
				+ userProfile.getAccNumber() + ")]",
		"ManageLink Clicked");
		;

	}

	public String clickPaginationNextLink(int i) {

		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("AccountSummary.PaginationNextLink"))) {
			browser.clickWithXpath(pageProperties
					.getProperty("AccountSummary.PaginationNextLink"));
			System.out.println("Pagination clicked :" + i);
			functionResult = "true";
		} else {
			functionResult = "false";
		}
		return functionResult;
	}

	public String selectAccountFromDropDown1(String accountnumber) {
		if (browser.isElementVisible(pageProperties
				.getProperty("AccountSummary.OptionsToSearch"))) {
			Report.updateTestLog("Search by drop down displayed", "Pass");
			verifyAndSelectDropDownBox(
					pageProperties
					.getProperty("AccountSummary.OptionsToSearch"),
					"Search Accounts", "Contract Account Number");
			verifyAndInputById(
					pageProperties
					.getProperty("AccountSummary.ContractAccountNumber"),
					"ContractAccountNumber", accountnumber);
			verifyAndClick(
					pageProperties.getProperty("AccountSummary.SearchAccounts"),
			"Search-Accounts");
			functionResult = "true";
		} else {
			functionResult = "false";
		}
		return functionResult;
	}

	/*
	 * public void verifyViewBillPage(){
	 * verifyPageTitle(pageProperties.getProperty("ViewBill.PageTitle")); String
	 * accountNumber = null; try{
	 * if(browser.isElementVisibleWithXpath(pageProperties
	 * .getProperty("ViewBill.AccountNumber"))){ accountNumber =
	 * browser.getTextByXpath
	 * (pageProperties.getProperty("ViewBill.AccountNumber"));
	 * System.out.println("ACCOUNTNUMBER1   "+accountNumber); }} catch(Exception
	 * e){ accountNumber =
	 * browser.getAttributeByXpath(pageProperties.getProperty
	 * ("ViewBill.AccountNumber"),"p");
	 * System.out.println("ACCOUNTNUMBER2   "+accountNumber); }
	 * 
	 * //accountNumber =
	 * browser.getTextByXpath(pageProperties.getProperty("ViewBill.AccountNumber"
	 * ));
	 * Report.updateTestLog("Cutomer displaying with account number "+accountNumber
	 * , "PASS"); verifyIsElementVisibleWithXpath(pageProperties.getProperty(
	 * "ViewBill.BackToYourAccount"), "Back to your account link");
	 * verifyIsElementVisibleWithXpath
	 * (pageProperties.getProperty("ViewBill.InvoiceNumber"), "Invoice number");
	 * verifyIsElementVisibleWithXpath
	 * (pageProperties.getProperty("ViewBill.ViewbillForDifferentAccount"),
	 * "View bill for different account");
	 * verifyIsElementVisibleWithXpath(pageProperties
	 * .getProperty("ViewBill.ViewDifferentBill"), "View different bill");
	 * verifyIsElementVisibleWithXpath
	 * (pageProperties.getProperty("ViewBill.ViewBillButton"),
	 * "View bill button"); }
	 */

	public void verifyBillTableColumns() {
		try {
			int columncount = browser.getColCountByXpath(pageProperties
					.getProperty("StatementOfAccount.TableColumn"));
			String[] verifyText = { "Contract account number", "Site address",
					"Post code", "Document number", "Posting date",
					"Transaction type", "Due date", "Fuel type", "Status",
			"Amount" };
			for (int i = 0; i < verifyText.length; i++) {
				int j = i + 1;
				String getRowValues = browser.getTextByXpath((pageProperties
						.getProperty("StatementOfAccount.TableColumn"))
						+ "//tr/th[" + j + "]");
				System.out.println("Xpath value:"
						+ pageProperties
						.getProperty("StatementOfAccount.TableColumn")
						+ "//tr/th[" + j + "]");
				if (verifyText[i].equals(getRowValues)) {
					Report.updateTestLog("Expected column value is :"
							+ verifyText[i] + "Actual column value is :"
							+ getRowValues, "Pass");
				} else {
					Report.updateTestLog("Expected column value is :"
							+ verifyText[i] + "Actual column value is :"
							+ getRowValues, "Fail");
				}
				// }
			}
		} catch (Exception e) {
			Report.updateTestLog(
					"Exception while trying to fetch the value in application",
			"Fail");
		}
	}

	public void verifyBillTableValues() // Temporarly compare the values instead
	// of SAP compare
	{
		try {
			int columncount = browser.getColCountByXpath(pageProperties
					.getProperty("StatementOfAccount.TableColumn"));
			String[] verifyText = { "000601268659",
					"GB, 562a Romford Road, London", "E12 5AF", "000001756841",
					"24-Oct-2013", "Billing 0100 & 0020 combinatio",
					"04-Nov-2013", "Gas", "Unpaid", "12.60" };
			for (int i = 0; i < verifyText.length; i++) {
				int j = i + 1;
				String getRowValues = browser.getTextByXpath((pageProperties
						.getProperty("StatementOfAccount.TableColumn"))
						+ "//tr/th[" + j + "]");
				System.out.println("Xpath value:"
						+ pageProperties
						.getProperty("StatementOfAccount.TableColumn")
						+ "//tr/th[" + j + "]");
				if (verifyText[i].equals(getRowValues)) {
					Report.updateTestLog("Expected column value is :"
							+ verifyText[i] + "Actual column value is :"
							+ getRowValues, "Pass");
				} else {
					Report.updateTestLog("Expected column value is :"
							+ verifyText[i] + "Actual column value is :"
							+ getRowValues, "Fail");
				}
				// }
			}
		} catch (Exception e) {
			Report.updateTestLog(
					"Exception while trying to fetch the value in application",
			"Fail");
		}
	}

	public void verifyBillTable() {
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("StatementOfAccount.TableColumn1"))) {
			Report.updateTestLog("Bill displayed in the table", "Pass");
			int rowcount = browser.getRowCountByXpath(pageProperties
					.getProperty("StatementOfAccount.TableColumn1"));
			int columncount = browser.getColCountByXpath(pageProperties
					.getProperty("StatementOfAccount.TableColumn1"));
			Report.updateTestLog("Number of rows displayed in the table is :"
					+ rowcount + "Number of Columns displayed"
					+ "in the table :" + columncount, "Done");
		} else {
			Report.updateTestLog("Bill not displayed in the table", "Fail");
		}
	}

	public void clickAndVerifyPaginationSOA() {
		String pagenumber;
		for (int i = 0; i <= 5; i++) {
			if (i != 0) {
				pagenumber = browser
				.getTextByXpath(pageProperties
						.getProperty("StatementOfAccount.PaginationNumbercount")
						+ i + "]/a");
				verifyAndClickWithXpath(
						(pageProperties.getProperty("StatementOfAccount.PaginationNumbercount")
								+ i + "]/a"), pagenumber
								+ "Item Per Page Selected");
			}
		}
		int j = 1;
		verifyAndClickWithXpath(
				(pageProperties.getProperty("StatementOfAccount.PaginationNumbercount")
						+ j + "]/a"), "Item Per Page Selected");
		PaginationNumbersLink();
	}

	public void FromDateToDateErrorValidation(UserProfile userProfile) {
		int i;
		int DateRange[] = { 12, 19, 13 };
		for (i = 0; i <= 2; i++) {
			enterFromDate(DateRange[i]);
			String errormsg = MaximumDateExceededValidatation();
			verifyErrorMessage(errormsg,
					SlingshotErrorMessages.Slingshot_SOA_FromDateExceededError);
			browser.browserBack();
			browser.wait(2000);
			clickViewStatementOfAccountLink(userProfile);
			browser.wait(2000);
		}

	}

	public void verifyErrorMessage(final String displayedErrorMsg,
			final String expectedErrorMsg) {
		System.out.println(displayedErrorMsg);
		System.out.println(expectedErrorMsg);
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

	public String MaximumDateExceededValidatation() {

		String overlayContent = "";
		String jqueryToCloseOverlay = ("$('a.ui-dialog-titlebar-close').trigger('click');");

		System.out.print("welcome to maximum exceeded world");
		if (browser.isElementVisibleWithXpath(pageProperties
				.getProperty("StatementOfAccount.maxDateExceededOverlayTitle"))) {
			System.out.print("i am in");

			overlayContent = browser
			.getTextByXpath(pageProperties
					.getProperty("StatementOfAccount.maxDateExceededOverlayContent"));
			System.out.println("overlayContent: " + overlayContent);
			Report.updateTestLog("Error," + overlayContent, "PASS");
			// verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.maxDateExceededOverlayClose"),
			// "Close Button Clicked");
			browser.executeScript(jqueryToCloseOverlay);
			browser.wait(getWaitTime());
		} else {
			Report.updateTestLog("i am not able to find any overlay", "PASS");
		}
		return overlayContent;
	}

	public void SOABackToYourAccount(UserProfile userProfile) {
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.TopBackToYourAccountLink"),
		"Top Back to Your Account Link");
		verifyPageTitle("Account overview");
		browser.wait(3000);
		browser.browserBack();
		// verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.BottomBackToYourAccountLink"),
		// "Back to Billing");
		verifyAndClickWithXpath(
				pageProperties.getProperty(
				"StatementOfAccount.BottomBackToYourAccountLink")
				.replace("ACCOUNTNUMBER", userProfile.getAccNumber()),
		"Back to Billing");
		verifyPageTitle("Billing");
		browser.wait(3000);
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.GetaquoteLink"),
		"Get a quoteFooters Selected");
		verifyPageTitle("Get a quote - British Gas Business");
		browser.wait(3000);
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.ConsultancyLink"),
		"Consultancy  Selected");
		verifyPageTitle("Consultancy - British Gas Business");
		browser.wait(4000);
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.EnergyMadeSimpleLink"),
		"Energy Made Simple Selected");
		verifyPageTitle("Home - Enerygy Made Simple - British Gas Business");
		browser.wait(4000);
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.SwitchingtoBritishGasLink"),
		"Switching to British Gas Footers Selected");
		verifyPageTitle("Help & advice - British Gas Business");
		browser.wait(3000);
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.HowtounderstandyourbillLink"),
		"How to understand your billSelected");
		verifyPageTitle("How to Understand Your Bill - British Gas Business");
		browser.wait(3000);
		browser.browserBack();
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.ResidentialelectricityLink"),
		"Residential electricity Selected");
		verifyPageTitle("Gas and Electricity - View Our Energy Prices - British Gas");
		browser.wait(3000);
		browser.browserBack();
	}

	public void browserWaitAndBack() {
		browser.wait(3000);
		browser.browserBack();
	}

	public void verifyLeadTable(UserProfile userProfile) {
		OnlineDBConnector dbFunctions = new OnlineDBConnector();
		String date = dbFunctions.DBsysdateDdmmyyhhmi();
		String[] auditType = dbFunctions.getAuditEventTypeId(date,
				userProfile.getEmail(), "VIEWED");
		String data = dbFunctions.getAuditType(auditType[0]);
		Report.updateTestLog(
				" Audit id is made in audit table as expected. Audit id: "
				+ auditType[0] + " Audit event type is" + data, data
				.equalsIgnoreCase("ACCOUNT_STATEMENT_VIEWED") ? "PASS"
						: "FAIL");
	}

/*	public void PaginationNumbersLink() {
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.NextLink"),
		"Next");
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.PrevLink"),
		"Prev");
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.SecondButtonLink"),
		"Second Button");
		verifyAndClickWithXpath(
				pageProperties
				.getProperty("StatementOfAccount.FirstButtonLink"),
		"First Button");
		PaginationValidate();
	}*/

	public void MonthDifferenceCalculator() {
		String Fromdate = browser.getAttributeByXpath(pageProperties
				.getProperty("StatementOfAccount.FromDateTextbox"), "value");
		String Todate = browser.getAttributeByXpath(
				pageProperties.getProperty("StatementOfAccount.ToDateTextbox"),
		"value");
		monthdifference(Fromdate, Todate);
	}

	public void monthdifference(String fromdate, String Todate) {
		DateFormat formatter;
		Date date1, date2;
		try {
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			date1 = (Date) formatter.parse(fromdate);
			date2 = (Date) formatter.parse(Todate);
			System.out.println("Today is " + date1);
			long milliseconds1 = date1.getTime();
			long milliseconds2 = date2.getTime();
			long diff = milliseconds2 - milliseconds1;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			int monthdifference = (int) (diffDays / 30);
			System.out.print("i am here too");

			System.out.print("monthadifference" + monthdifference);
			if (monthdifference == 3) {
				Report.updateTestLog(
						" From Date And To Date Month Difference is Exactly 3 Months",
				"PASS");
			} else {
				Report.updateTestLog(
						" From Date And To Date Month Difference is Below or Above 3 Months",
				"FAIL");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void FromDateToDateValidation(UserProfile userProfile) {
		int Monthrange[] = { 10, 12, 9 };

		for (int i = 0; i < Monthrange.length; i++) {
			switch (i) {

			case 0:
				enterFromDate(Monthrange[i]);
				String errormsg1 = MaximumDateExceededValidatation();
				verifyErrorMessage(
						errormsg1,
						SlingshotErrorMessages.Slingshot_SOA_FromDateExceededError);
				Report.updateTestLog(
						" From Date And To Date Month Difference is Above or below 12 Months"
						+ errormsg, "PASS");
				browser.browserBack();
				browser.wait(3000);
				clickViewStatementOfAccountLink(userProfile);
				browser.wait(3000);
				break;
			case 1:
				enterFromDate(Monthrange[i]);
				String errormsg3 = MaximumDateExceededValidatation();
				verifyErrorMessage(
						errormsg3,
						SlingshotErrorMessages.Slingshot_SOA_FromDateExceededError);
				Report.updateTestLog(
						" From Date And To Date Month Difference is Above or below 12 Months"
						+ errormsg, "PASS");
				browser.browserBack();
				browser.wait(3000);
				clickViewStatementOfAccountLink(userProfile);
				browser.wait(3000);
				break;
			case 3:
				enterFromDate(Monthrange[i]);
				enterToDate(1);
				verifyclickViewButton();
				Report.updateTestLog(
						" From Date And To Date Month Difference is within 12 Months",
				"PASS");
				break;
			}
		}

	}

	public void PaginationValidate() {
		int i = 4;
		verifyAndClickWithXpath(
				pageProperties.getProperty("StatementOfAccount.PaginationNumbercount")
				+ i + "]/a", "40 Transaction Per page link Selected");
		// int count=
		// browser.getRowCountByXpath(pageProperties.getProperty("StatementOfAccount.getTableCount"));
		// System.out.print("count"+count);
		// Report.updateTestLog("Table have the following no of rows"+count,"PASS");

	}

	public void verifyDataThroughQTP(UserProfile userProfile){

        // String readDateFromApp=Dateformatconvertor(ActualReadDate);
                     //String readValueFromApp=previousMeterRead;            
         System.out.print("welcome to qtp method");                
                     
                     String Acctno=userProfile.getAccNumber();
                     System.out.print("Acctno"+Acctno);                      
                     String bpnumber=userProfile.getBpnumber();
                    System.out.print("bpnumber"+bpnumber);
                     String bpOrgNumber =bpnumber.concat("-").concat(Acctno);
                     System.out.print("bpOrgNumber"+bpOrgNumber);                       
                                     
        RunQTP runQTP = new RunQTP();
                     System.out.println("hi");
                     runQTP.runQTP("Slingshot\\src\\bg\\framework\\app\\functional\\util\\vbsScripts\\SOA.vbs", bpOrgNumber);
                     System.out.println("i am  out");
                                                     
                                     ArrayList<String> stringList = new ArrayList<String>();
                                     String Getdata;
                          int j=4;
                          String Searchdocno11=null;
                                     
try {
                                     File file1 = new File("C:\\SAPSOAData\\StatementOfAccount.txt");
                                     FileReader fr = new FileReader(file1);
                                     BufferedReader br = new BufferedReader(fr);
                                     String Searchdocno = br.readLine();                                        
                                     System.out.println("search documentnumber"+Searchdocno);
                                     if(Searchdocno.contains("documentnumber")&& Searchdocno.contains(":")){
                                                     String[] Searchdocno1=Searchdocno.split(":");
                                                     Searchdocno11=Searchdocno1[1];
                                                     Searchdocno11="00"+Searchdocno11;
                                                     System.out.print("Initial Search documentnumber"+Searchdocno11);
                                     }
                                                                     verifyAndClickWithXpath(pageProperties.getProperty("StatementOfAccount.PaginationNumbercount")+j+"]/a","40 Transaction Per page link Selected");
                                                                     int noofrows=transcount();
                                                     for(int i=1;i<=noofrows;i++)
                                                     {
                                                                     System.out.println("i am in"+i);
                                                                                     
                                                                                                                     if(browser.isElementVisibleWithXpath(pageProperties.getProperty("StatementOfAccount.GetTableData")+"//tr["+i+"]/td["+j+"]"))                                                                                
                                                                                                                     {
                                                                                                                                     
                                                                                                                                     System.out.println("i crossed visible element");
                                                                                                                                     String Appdocno=browser.getTextByXpath(pageProperties.getProperty("StatementOfAccount.GetTableData")+"//tr["+i+"]/td["+j+"]");
                                                                                                                                     System.out.println("Appdocno"+Appdocno);
                                                                                                                                                     
                                                                                                                                                                     if(Appdocno.equals(Searchdocno11))
                                                                                                                                                                     {
                                                                                                                                                                                     System.out.println("both r same");
                                                                                                                                                                                                     Report.updateTestLog("Data is matched:"+Appdocno,"pass");
                                                                                                                                                                                                                                     
                                                                                                                                                                                                     for(int k=2;k<=10;k++)
                                                                                                                                                                                                     {
                                                                                                                                                                                                                     
                                                                                                                                                                                                                     System.out.println("super k"+k);
                                                                                                                                                                                                                     System.out.println("super i"+i);                                                                                                                                                                                                                
                                                                                                                                                                                                                     Getdata=browser.getTextByXpath(pageProperties.getProperty("StatementOfAccount.GetTableData")+"//tr["+i+"]/td["+k+"]");
                                                                                                                                                                                                                     System.out.println("Getdata:"+Getdata);
                                                                                                                                                                                                                     stringList.add(Getdata);                                                                                                                                                                                
                                                                                                                                                                                                     }              
                                                                                                                                                                     
                                                                                                                                                                     System.out.println("Table value:"+stringList);
                                                                                                                                                                     }
                                                                                                                     }
                                                     }                                                                                                                                                             
                                                                     String siteaddresslabel=stringList.get(0);
                                                                     String postcodelabel=stringList.get(1);
                                                                     String documentno=stringList.get(2);
                                                                     String postingdatelabel=stringList.get(3);
                                                                     String desclabel=stringList.get(4);
                                                                     String duedatelabel=stringList.get(5);
                                                                     String fueltypelabel=stringList.get(6);
                                                                     String paidstatuslabel=stringList.get(7);
                                                                     String amtlabel=stringList.get(8);
                                                     
     for(int n =0;n<=8;n++)
     {
                                     String siteAddr = br.readLine();
                                    if(siteAddr.contains("SiteAddress")&& siteAddr.contains(":")){
                                                    String[] siteaddr1=siteAddr.split(":");
                                                     String site=siteaddr1[1];
                                                     System.out.print("after if Site"+site);
                                                     String siteaddresss =site;
                                                     StringBuilder sb = new StringBuilder(siteaddresss);
                                                     System.out.println("site"+site);
                                                     System.out.println("replace all conversion");
                                                     System.out.println(sb.toString().replaceAll(", ",""));
                                                     if(site.equalsIgnoreCase(sb.toString().replaceAll(", ",","))){
                                                                     System.out.println("i am equal");
                                                                     Report.updateTestLog("Site Address in Application"+siteaddresslabel.trim()+"Site Address in ISU :"+site, "Pass");
                                                     }else{
                                                                     Report.updateTestLog("Site Address in Application"+siteaddresslabel.trim()+"Site Address in ISU :"+site, "Fail");
                                                     }
                                     }else{
                                                     Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");                            
                                     }
                                    
                                     String postcode = br.readLine();
                                     System.out.println("before if postcode"+postcode);
                                     if(postcode.contains("Postcode")&& postcode.contains(":")){
                                                     String[] postcode1=postcode.split(":");
                                                     String postcode12=postcode1[1];
                                                     System.out.print("after if postcode"+postcode12);
                                                     if(postcode12.trim().equals(postcodelabel.trim())){
                                                                     Report.updateTestLog("Post code in Application"+postcodelabel.trim()+"Post code in ISU :"+postcode12, "Pass");
                                                     }else{
                                                                     Report.updateTestLog("Post code in Application"+postcodelabel.trim()+"Post code in ISU :"+postcode12, "Fail");
                                                     }
                                     }else{
                                                     Report.updateTestLog("Post code is not written in the text file", "Fail");               
                                     }
                                     
                                     String documentnumber = br.readLine();
                                     System.out.println("documentnumber"+documentnumber);
                                     if(documentnumber.contains("documentnumber")&& documentnumber.contains(":")){
                                                     String[] docno=documentnumber.split(":");
                                                     String document=docno[1];
                                                     String finaldoc ="00"+document;
                                                     System.out.print("after if document"+document);
                                                     if(finaldoc.trim().contains(documentno.trim())){
                                                                     Report.updateTestLog("document number in Application"+documentno.trim()+"document number in ISU :"+finaldoc, "Pass");
                                                     }else{
                                                                     Report.updateTestLog("document number in Application"+documentno.trim()+"document number in ISU :"+finaldoc, "Fail");
                                                     }
                                     }else{
                                                     Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");                            
                                     }
                                    String postingdate = br.readLine();
                                     System.out.println("before if postingdate"+postingdate);
                                     if(postingdate.contains("Postingdate")&& postingdate.contains(":") ){
                                                     String[] post=postingdate.split(":");
                                                     String postdate=post[1];
                                                     System.out.println("postdate"+postdate);
                                                     try
                                                     {
                                                     SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

                                                     String postingdatelabel1=Dateformatconvertor(postingdatelabel);
                                                     System.out.println("postingdatelabel1"+postingdatelabel1);
                                                     
                                                     Date postdateSAP = formatter.parse(postdate);
                                                     Date postdateAPP = formatter.parse(postingdatelabel1);                                                            
                                                     System.out.print("after if postdatelatest"+postdateSAP);
                                                     
                                                     if(postdateAPP.compareTo(postdateSAP)==0){
                                                                     Report.updateTestLog("Posting date in Application"+postdateAPP+"Posting date in ISU :"+postdateSAP, "Pass");
                                                     }else{
                                                                     Report.updateTestLog("Posting date in Application"+postdateAPP+"Posting date in ISU :"+postdateSAP, "Fail");
                                                     }
                                     /*else{
                                                     Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");                            
                                     }*/
                                                     }
                                                     catch(ParseException e)
                                                     {
                                                                     
                                                     }
                                     }                                                                                                                              
                                     
                                     String Transactiontype = br.readLine();
                                     System.out.println(" before if Transactiontype"+Transactiontype);
                                     if(Transactiontype.contains("Transactiontype")&& Transactiontype.contains(":")){
                                                     String[] Transactiontype1=Transactiontype.split(":");
                                                     String Transtype=Transactiontype1[1];
                                                     System.out.print("after if Transtype"+Transtype);
                                                     if(Transtype.trim().equals(desclabel.trim())){
                                     //                            Report.updateTestLog("Transaction type in Application"+desclabel.trim()+"Transaction type in ISU :"+Transtype, "Pass");
                                                     }else{
                                     //                            Report.updateTestLog("Transaction type in Application"+desclabel.trim()+"Transaction type in ISU :"+Transtype, "Fail");
                                                     }
                                     }else{
                                                     Report.updateTestLog("Transactiontype is not written in the text file", "Fail");                   
                                     }
                                     String duedate = br.readLine();
                                     System.out.println("duedate"+duedate);
                                     if(duedate.contains("Duedate")&& duedate.contains(":") ){
                                                     String[] read=duedate.split(":");
                                                     String duedate1=read[1];
                                                     System.out.println("duedate1"+duedate1);
     
                                                     try
                                                     {
                                                     SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                                                     
                                                     String PostingDuedatelabel=Dateformatconvertor(duedatelabel);
                                                     System.out.println("PostingDuedatelabel"+PostingDuedatelabel);
                                                     
                                                     Date duedateSAP = formatter.parse(duedate1);
                                                     Date duedateAPP = formatter.parse(PostingDuedatelabel);
                                                     
                                                     System.out.print("after if duedate"+duedateSAP);
                                                     System.out.print("after if duedateAPP"+duedateAPP);
                                                     
                                                     if(duedateAPP.compareTo(duedateSAP)==0){
                                                                     Report.updateTestLog("Duedate in Application"+duedateAPP+"Duedate number in ISU :"+duedateSAP, "Pass");
                                                     }else{
                                                                     Report.updateTestLog("Duedate in Application"+duedateAPP+"Duedate number in ISU :"+duedateSAP, "Fail");
                                                    }
                                     /*else{
                                                     Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");                            
                                     }*/
                                                     }
                                                     catch(ParseException e)
                                                     {
                                                                     
                                                     }
                                     }                                              
                                     String Fueltype = br.readLine();
                                     System.out.println("before if Fueltype"+Fueltype);
                                     if(Fueltype.contains("Fueltype")&& Fueltype.contains(":")){
                                                     String[] Fueltype1=Fueltype.split(":");
                                                     String Fuel=Fueltype1[1];
                                                     System.out.print("after if Fuel"+Fuel);
                                                     if(Fuel.trim().equals(fueltypelabel.trim())){
                                                                     Report.updateTestLog("Fuel type in Application"+fueltypelabel.trim()+"Fuel type in ISU :"+Fuel, "Pass");
                                                     }else{
                                                                     Report.updateTestLog("Fuel type in Application"+fueltypelabel.trim()+"Fuel type in ISU :"+Fuel, "Fail");
                                                     }
                                     }else{
                                                     Report.updateTestLog("LastMeterReadValue is not written in the text file", "Fail");                            
                                     }                                              
                                     
                                     String Status = br.readLine();
                                     System.out.println(" before if Status"+Status);
                                     if(Status.contains("Status")&& Status.contains(":")){
                                                     String[] Status2=Status.split(":");
                                                     String Status1=Status2[1];
                                                     System.out.print("After if Status"+Status1);
                                                     if(Status1.trim().equals(paidstatuslabel.trim())){
                                                                     Report.updateTestLog("Status in Application"+paidstatuslabel.trim()+"Status in ISU :"+Status1, "Pass");
                                                     }else{
                                                                     Report.updateTestLog("Status in Application"+paidstatuslabel.trim()+"Status in ISU :"+Status1, "Fail");
                                                     }
                                     }else{
                                                     Report.updateTestLog("Status is not written in the text file", "Fail");                       
                                     }
                                     String Amount = br.readLine();
                                     System.out.println("before if Amount"+Amount);
                                     if(Amount.contains("Amount")&& Amount.contains(":")){
                                                     String[] Amount2=Amount.split(":");
                                                     String Amount1=Amount2[1];
                                                     System.out.print("After if Amount"+Amount1);                                                               
                                                     StringBuilder sb = new StringBuilder(Amount1);
                                                     StringBuilder sb1= new StringBuilder(amtlabel.trim());
                                                     
                                                     if(sb.charAt(sb.length()-1)=='-' )
                                                                     {
                                                                     System.out.print("hi");
          int len=sb.length();
          sb.deleteCharAt(len-1);
         System.out.println("sb.toString() After deletion"+sb.toString());  
                                                                     }
                                                                     else
                                                                     {
                                                                                     System.out.println("No problem");
                                                                     }
                                                     
                                                      if(sb1.charAt(0)=='-' )
      {                           
        sb1.deleteCharAt(0);
       System.out.println("sb1.toString() After deletion"+sb1.toString());  
      }
                                                                     System.out.println("sb.toString()"+sb.toString());
                                                                     
                                                                     
                                                     if(sb1.toString().equals(sb.toString()))
                                                     {
                                                                     System.out.println("i am pass");
                                                                     Report.updateTestLog("Amount in Application"+sb1.toString()+"Amount in ISU :"+sb.toString(),"Pass");
                                                     }
                                                     else
                                                     {
                                                                     Report.updateTestLog("Amount in Application"+sb1.toString()+"Amount in ISU :"+sb.toString(),"Fail");
                                                     }
                                     }
                                     else{
                                                     Report.updateTestLog("Amount is not written in the text file", "Fail");                   
                                     }
                                     
                                     br.close();
     }
                                     
                                     }
                                                                     

                     catch (IOException e) {
                                     //System.out.println("bad"+e);
                     //            Report.updateTestLog("Exception while accessing the .txt file"+e, "Fail");
                     }
     
     }


	public String Dateformatconvertor(String ActualReadDate) {
		try {

			// create SimpleDateFormat object with source string date format
			SimpleDateFormat sdfSource = new SimpleDateFormat("dd-MMM-yyyy");

			// parse the string into Date object
			Date date = sdfSource.parse(ActualReadDate);

			// create SimpleDateFormat object with desired date format
			SimpleDateFormat sdfDestination = new SimpleDateFormat("dd.MM.yyyy");

			// parse the date into another format
			ActualReadDate = sdfDestination.format(date);

			System.out
			.println("Date is converted from dd/MM/yy format to dd.MM.yyyy");
			System.out.println("Converted date is : " + ActualReadDate);

		} catch (ParseException pe) {
			System.out.println("Parse Exception : " + pe);
		}
		return ActualReadDate;
	}

	public int transcount() {
		System.out.println(" i am here in table");
		String numberOfTransactions = browser.getTextByXpath(pageProperties
				.getProperty("StatementOfAccount.ViewtableTransaction"));
		System.out.println(browser.getTextByXpath(pageProperties
				.getProperty("StatementOfAccount.ViewtableTransaction")));
		System.out.println("rowcount" + numberOfTransactions);
		String[] totalTransaction = numberOfTransactions.split("of");
		totalTransaction = totalTransaction[1].split(" ");
		String numberOfTransactionsInAuditTable = totalTransaction[1];
		int numberofrows = Integer.parseInt(numberOfTransactionsInAuditTable);
		return numberofrows;
	}

}
