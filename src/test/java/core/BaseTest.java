package core;

import com.aventstack.extentreports.ExtentReports;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import listeners.TestListener;
import org.apache.commons.io.output.WriterOutputStream;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import tests.Login;
import utilities.ExtentManager;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Locale;

public class BaseTest implements Constants{

    public static Faker faker = new Faker(new Locale("pt-BR"));
    private static ExtentReports extent;
    public StringWriter requestWriter;
    public PrintStream requestCapture;
    public StringWriter responseWriter;
    public PrintStream responseCapture;

    @BeforeTest
    public static void setUp(){
        extent = ExtentManager.getInstance();
        String customerCode= System.getProperty("customerCode");
        String url = null;

        if(customerCode==null){
            url = APP_BASE_URL_DEV;
        }else {
            if (customerCode.equals("HML")) {
                url = APP_BASE_URL_HML;
            }
            if (customerCode.equals("DEV")) {
                url = APP_BASE_URL_DEV;
            }
        }
        String token = new Login().retornaTokenLogin(url);
        RestAssured.baseURI = url;
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = reqBuilder.build();
        //RestAssured.requestSpecification.header("authorization",token);
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        RestAssured.responseSpecification = resBuilder.build();
    }

    @BeforeMethod
    public void initLog(){
        requestWriter = new StringWriter();
        responseWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
        responseCapture = new PrintStream(new WriterOutputStream(responseWriter));
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.addFilter((new RequestLoggingFilter(requestCapture)));
        reqBuilder.addFilter((new ResponseLoggingFilter(responseCapture)));
        RestAssured.requestSpecification = reqBuilder.build();
    }

    @AfterMethod
    public void addLog(){
        extent.flush();
        requestCapture.flush();
        responseCapture.flush();
        TestListener.insertLogToReport("Request: ", requestWriter.toString() );
        TestListener.insertLogToReport("Response: ",responseWriter.toString());
    }
}
