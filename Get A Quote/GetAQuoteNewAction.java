package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.page.common.BasePage;
import bg.framework.app.functional.page.common.LegacyHomePage;
import bg.framework.app.functional.page.common.LegacyLoginPage;

import bg.framework.app.functional.entities.GetAQuoteNew;
import bg.framework.app.functional.page.Slingshot.GetAQuoteNewPage;

public class GetAQuoteNewAction extends BasePage{
	
	public GetAQuoteNewAction BgbNavigatetologinpage()
	{
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.BgbnavigateToLoginPage(); 
		return this;
	}
	
	public GetAQuoteNewAction BgbloginDetails(GetAQuoteNew gaqnew) {
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		legacyLoginPage.BgbGetAQuoteLogin(gaqnew);
		return this;
	}
	
	
	public GetAQuoteNewAction NavigatetoGaqpage()
	{
		GetAQuoteNewPage GAQpage = new GetAQuoteNewPage();
	    GAQpage.NavigatetoGaqpage();
		return this;
	}
	
	public GetAQuoteNewAction EnterPostCode(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
	    GAQpostcode.EnterPostCode(gaqnew);
	    GAQpostcode.verifyEditFunctionality(gaqnew);
		return this;
	}
	
	public GetAQuoteNewAction VerifyEnergyUsagedeatails_CMA(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifymeterdeatilsFunctionality(gaqnew);
		GAQpostcode.verifyQuotesummarypage();
		GAQpostcode.verifyElectricityQuote();
		
		return this;
	}
	
	public GetAQuoteNewAction verifymeterdeatilsFunctionality_Renewals(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifymeterdeatilsFunctionality_Renewals(gaqnew);		
		return this;
	}
	
	public GetAQuoteNewAction verifyQuotesummarypage()
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifyQuotesummarypage();		
		return this;
	}
	
	public GetAQuoteNewAction verifyElectricityQuote()
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifyElectricityQuote();		
		return this;
	}
	
	
	public GetAQuoteNewAction verifysavemeterdeatilsFunctionality(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
	    GAQpostcode.verifysavemeterdeatilsFunctionality(gaqnew);
	 	return this;
	}


	public GetAQuoteNewAction Verifydropdownfuctionality(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifydropdown_filters_check();
		//GAQpostcode.verify_recalculatecalender();
			
		return this;
	}
	
	public GetAQuoteNewAction ElectricityEnergyUsage_Verification(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.ElectricityEnergyUsage_Verification(gaqnew);			
		return this;
	}
	
	public GetAQuoteNewAction GasEnergyUsage_Verification(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.GasEnergyUsage_Verification(gaqnew);			
		return this;
	}
	
	public GetAQuoteNewAction verifyQuotesummarypage_Electricity()
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifyQuotesummarypage_Electricity();			
		return this;
	}
	
	public GetAQuoteNewAction verifysavemeterdeatilswithinput(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifysavemeterdeatilswithinput(gaqnew);			
		return this;
	}
	
	public GetAQuoteNewAction VerifyRenewalcontract()
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.VerifyRenewalcontract();			
		return this;
	}
	
	public GetAQuoteNewAction verifySaveandUpgrade()
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifySaveandUpgrade();			
		return this;
	}
	
	public GetAQuoteNewAction industralcustomer(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.industralcustomer(gaqnew);
		return this;
	}
	
	public GetAQuoteNewAction industralcustomer_confirmation()
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.industralcustomer_confirmation();
		return this;
	}
	
	
	public GetAQuoteNewAction verifyQuotesummarypage_Gas()
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verifyQuotesummarypage_Gas();			
		return this;
	}
	
	public GetAQuoteNewAction VerifyBuyOnlinefuctionality(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.verify_Buyonline();
		GAQpostcode.verifyandenter_customerdeatils(gaqnew);
		return this;
}
	public GetAQuoteNewAction Gas_Buyonline(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.Gas_Buyonline();
		GAQpostcode.verifyandenter_customerdeatils(gaqnew);
		return this;
}
	
	public GetAQuoteNewAction VerifyGetAQuoteSummaryPage(GetAQuoteNew gaqnew)
	{
		GetAQuoteNewPage GAQpostcode = new GetAQuoteNewPage();
		GAQpostcode.GetAQuoteSummarypage();
		return this;
}
	public GetAQuoteNewAction Salesverification()
	{
		GetAQuoteNewPage sales = new GetAQuoteNewPage();
		sales.salesBusinessPageVerification();
		return this;
}
	public GetAQuoteNewAction salesCreditCheckVerification()
	{
		GetAQuoteNewPage sales = new GetAQuoteNewPage();
		sales.salesCreditCheckVerification();
		return this;
}
	public GetAQuoteNewAction salespaymentdeatils()
	{
		GetAQuoteNewPage sales = new GetAQuoteNewPage();
		sales.salespaymentdeatils();
		return this;
}
	
	public GetAQuoteNewAction SalesPurchaseSummarypage()
	{
		GetAQuoteNewPage sales = new GetAQuoteNewPage();
		sales.SalesPurchaseSummarypage();
		return this;
}
	public GetAQuoteNewAction confirmDeatils_verification()
	{
		GetAQuoteNewPage sales = new GetAQuoteNewPage();
		sales.confirmDeatils_verification();
		return this;
}
	public GetAQuoteNewAction salesFinalpageverifcation()
	{
		GetAQuoteNewPage sales = new GetAQuoteNewPage();
		sales.salesFinalpageverifcation();
		return this;
}
	
}
