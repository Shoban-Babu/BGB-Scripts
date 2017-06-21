/**
 * 
 */
package bg.framework.app.functional.test.tetris;

import static bg.framework.app.functional.entities.FunctionalCategory.Tetris;
import org.testng.annotations.Test;
import bg.framework.app.functional.action.tetris.EnergyAdvisorToolAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author 292238
 *
 */
public class EnergyAdvisorToolTest extends TestBase{
	@Test(groups={Tetris})	
	public void verifyCorporateEnergyAdvisorTool() throws Exception{
		Report.createTestLogHeader("Energy Advisor Tool Page","Verifies functionality of corporate Energy Advisor Tool Page");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		String strJourney = "/corporate";
		new EnergyAdvisorToolAction().openEnergyAdvisorToolPage(strJourney)
		.verifyAndEntervalues(userProfile);		
	}
	@Test(groups={Tetris})	
	public void verifyBusinessEnergyAdvisorTool() throws Exception{
		Report.createTestLogHeader("Energy Advisor Tool Page","Verifies functionality of business Energy Advisor Tool Page");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		String strJourney = "/business";
		new EnergyAdvisorToolAction().openEnergyAdvisorToolPage(strJourney)
		.verifyAndEntervalues(userProfile);
		//.verifyResultAndRetrivalPage(userProfile);
		//.verifyDBDetails(userProfile);
	}
	@Test(groups={Tetris})	
	public void validateEnergyAdvisorToolFields() throws Exception{
		Report.createTestLogHeader("Energy Advisor Tool Page","Validates fields of business Energy Advisor Tool Page");		
		UserProfile userProfile= new TestDataHelper().getUserProfile("tetris-contactUs");
		String strJourney = "/business";
		new EnergyAdvisorToolAction().openEnergyAdvisorToolPage(strJourney)
		.validateFieldsInEAT(userProfile);
	}
}
