package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.GetAQuoteNew;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.GetAQuoteNewAction;
import bg.framework.app.functional.entities.GetAQuoteNew;

import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

public class GetAQuoteNewTest extends TestBase {

	@Test(groups ={Slingshot,Regression,GetAQuoteNew})
	
	public void verifyGetAQuote_CMApage()
	
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA");
		
		new GetAQuoteNewAction()
		.NavigatetoGaqpage()
		.EnterPostCode(gaqnew)
		.VerifyEnergyUsagedeatails_CMA(gaqnew)
		.Verifydropdownfuctionality(gaqnew)
		.VerifyBuyOnlinefuctionality(gaqnew)
		.VerifyGetAQuoteSummaryPage(gaqnew)
		
		.Salesverification()
		.salesCreditCheckVerification()
		.salespaymentdeatils()
		.SalesPurchaseSummarypage()
		.confirmDeatils_verification()
		.salesFinalpageverifcation();
							
		
	}
	
	@Test(groups ={Slingshot,Regression,GetAQuoteNew})
	public void verifyGetAQuote_CMApageforElectricity()
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes for Electricity", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA_Electricity");
		
		new GetAQuoteNewAction()
		.NavigatetoGaqpage()
		.EnterPostCode(gaqnew)
		.verifysavemeterdeatilsFunctionality(gaqnew)
		.ElectricityEnergyUsage_Verification(gaqnew)
		.verifyQuotesummarypage_Electricity()
		.Verifydropdownfuctionality(gaqnew)
		.VerifyBuyOnlinefuctionality(gaqnew)
		.VerifyGetAQuoteSummaryPage(gaqnew)
		
		.Salesverification()
		.salesCreditCheckVerification()
		.salespaymentdeatils()
		.SalesPurchaseSummarypage()
		.confirmDeatils_verification()
		.salesFinalpageverifcation();
		
	}
	
	@Test(groups ={Slingshot,Regression,GetAQuoteNew})
	public void verifyGetAQuote_CMApageforGas()
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes for Gas", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA_Gas");
		
		 new GetAQuoteNewAction()
		.NavigatetoGaqpage()
		.EnterPostCode(gaqnew)
		.verifysavemeterdeatilsFunctionality(gaqnew)
		.GasEnergyUsage_Verification(gaqnew)
		.verifyQuotesummarypage_Gas()
		.Verifydropdownfuctionality(gaqnew)
		.Gas_Buyonline(gaqnew)
		.VerifyGetAQuoteSummaryPage(gaqnew)
		
		.Salesverification()
		.salesCreditCheckVerification()
		.salespaymentdeatils()
		.SalesPurchaseSummarypage()
		.confirmDeatils_verification()
		.salesFinalpageverifcation();	
		
		
	}
	
	@Test(groups ={Slingshot,Regression,GetAQuoteNew})
    public void verifyGetAQuote_CMApage_Loggedin()
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes for Logged in", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA_Loggedin");
		
		new GetAQuoteNewAction()
		.BgbNavigatetologinpage()
		.BgbloginDetails(gaqnew)		
		.NavigatetoGaqpage()
		.EnterPostCode(gaqnew)
		.VerifyEnergyUsagedeatails_CMA(gaqnew)
		.Verifydropdownfuctionality(gaqnew)
		.VerifyBuyOnlinefuctionality(gaqnew)
		.VerifyGetAQuoteSummaryPage(gaqnew)
		
		.Salesverification()
		.salesCreditCheckVerification()
		.salespaymentdeatils()
		.SalesPurchaseSummarypage()
		.confirmDeatils_verification()
		.salesFinalpageverifcation();
		
	}
	
	@Test(groups ={Slingshot,Regression,GetAQuoteNew})
    public void verifyGetAQuote_CMApage_LoggedinforElectricity()
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes for Logged in_Electricity", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA_LoggedinElec");
		
			new GetAQuoteNewAction()
			.BgbNavigatetologinpage()
			.BgbloginDetails(gaqnew)		
			.NavigatetoGaqpage()
			.EnterPostCode(gaqnew)
			.verifysavemeterdeatilsFunctionality(gaqnew)
			.ElectricityEnergyUsage_Verification(gaqnew)
			.verifyQuotesummarypage_Electricity()
			.Verifydropdownfuctionality(gaqnew)
			.VerifyBuyOnlinefuctionality(gaqnew)
			.VerifyGetAQuoteSummaryPage(gaqnew)
			
			.Salesverification()
			.salesCreditCheckVerification()
			.salespaymentdeatils()
			.SalesPurchaseSummarypage()
			.confirmDeatils_verification()
			.salesFinalpageverifcation();
		
	}
	
	@Test(groups ={Slingshot,Regression,GetAQuoteNew})
    public void verifyGetAQuote_CMApage_LoggedinforGas()
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes for Logged in_Electricity", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA_LoggedinGas");
		
			new GetAQuoteNewAction()
			.BgbNavigatetologinpage()
			.BgbloginDetails(gaqnew)
			.NavigatetoGaqpage()
			.EnterPostCode(gaqnew)
			.verifysavemeterdeatilsFunctionality(gaqnew)
			.GasEnergyUsage_Verification(gaqnew)
			.verifyQuotesummarypage_Gas()
			.Verifydropdownfuctionality(gaqnew)
			.Gas_Buyonline(gaqnew)
			.VerifyGetAQuoteSummaryPage(gaqnew)
			
			.Salesverification()
			.salesCreditCheckVerification()
			.salespaymentdeatils()
			.SalesPurchaseSummarypage()
			.confirmDeatils_verification()
			.salesFinalpageverifcation();
		
		
	}
	
	@Test(groups={Slingshot,Regression,GetAQuoteNew})
	
	public void verifyGetAQuoteCMApage_InRenewals()
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes for Renewals Customer", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA_InRenewals");
		
		new GetAQuoteNewAction()
		.NavigatetoGaqpage()
		.EnterPostCode(gaqnew)
		.verifysavemeterdeatilswithinput(gaqnew)
		.verifymeterdeatilsFunctionality_Renewals(gaqnew)
		.verifyQuotesummarypage()
		.verifyElectricityQuote()
		.Verifydropdownfuctionality(gaqnew)
		.VerifyRenewalcontract();
		
	}
	
	@Test(groups={Slingshot,Regression,GetAQuoteNew})
	public void verifyGetAQuoteCMApage_SaveandUpgrade()
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes for Save and Upgrade customer", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA_SaveandUpgrade");
		
		new GetAQuoteNewAction()
		.NavigatetoGaqpage()
		.EnterPostCode(gaqnew)
		.verifysavemeterdeatilsFunctionality(gaqnew)
		.GasEnergyUsage_Verification(gaqnew)
		.verifyQuotesummarypage_Gas()
		.Verifydropdownfuctionality(gaqnew)
		.verifySaveandUpgrade();
		
	}
	
	@Test
	public void verifyGetAQuoteCMApage_IndustrialCustomer()
	{
		Report.createTestLogHeader("Verify Get A Quote CMA Changes for industrial Customer", "Get A Quote - CMA");
		GetAQuoteNew gaqnew = new TestDataHelper().getGetAQuoteNew("GetAQuoteCMA_IndustrialCustomer");
	    
		new GetAQuoteNewAction()
		.NavigatetoGaqpage()
		.EnterPostCode(gaqnew)
		.verifysavemeterdeatilsFunctionality(gaqnew)
		.verifymeterdeatilsFunctionality_Renewals(gaqnew)
		.industralcustomer(gaqnew)
		.industralcustomer_confirmation();	
	
   }
	
	
	
	
}
	