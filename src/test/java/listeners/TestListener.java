package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import core.BaseTest;
import utilities.ExtentManager;

public class TestListener extends BaseTest implements ITestListener {

    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Iníciado o  Relatório");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {

        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(result.getMethod().getMethodName()), ' '),result.getMethod().getDescription());
        String[] t = result.getMethod().toString().split("[.]");

        extentTest.assignCategory(t[0]);
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {

        test.get().pass("Test executado com sucesso!");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {

        test.get().fail("O caso de teste "+StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(result.getMethod().getMethodName()), ' ')
                +" falhou, favor analizar o erro! <br>" + result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {

        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    public static  void insertLogToReport(String node,String log){
        if(test.get()!=null) {
            test.get().info(node+"<br><pre>"+log+"</pre>");
        }
    }
}
