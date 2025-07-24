package ExtentReport_Lecture;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext context) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport"+System.currentTimeMillis()/1000+".html");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer Name", "Local Host");
		extent.setSystemInfo("Environment", "QC");
		extent.setSystemInfo("Tester Name", "Ronak");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Browser Name", "Google Chrome");
		
	  }
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case PASSED is : "+result.getName());
	  }
	
	public void onTestFailure(ITestResult result) {
	    test = extent.createTest(result.getName());
	    test.log(Status.FAIL, "Test Case FAILED is : "+result.getName());
	    test.log(Status.FAIL, "Test Case FAILED cause : "+result.getThrowable());
	  }
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED is : "+result.getName());
	}
	
//	This is a Mandatory Method without it you won't be able to see the output.
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
