package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.Platform;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static Platform platform;
    private static ExtentHtmlReporter htmlReporter;

    private static String reportFileName = "report.html";
    private static String macPath = System.getProperty("user.dir")+ "/TestReport";
    private static String linuxPath = System.getProperty("user.dir")+ "/TestReport";
    private static String windowsPath = System.getProperty("user.dir")+ "\\TestReport";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String lixReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {

            String css = "#img {\n" +
                    "    width: 116px;\n" +
                    "    height: 37px;\n" +
                    "    float: left;\n" +
                    "    position: absolute;\n" +
                    "    margin-left: -85px;\n" +
                    "    margin-top: 5px;\n" +
                    "}";

            String js = " $(document).ready(function() {\n" +


                    "          $('head').append('<link rel=\"icon\" href=\"\">');\n" +

                    "        });";
            platform = getCurrentPlatform();
            String fileName = getReportFileLocation(platform);
            extent = new ExtentReports();
            htmlReporter = new ExtentHtmlReporter(fileName);

            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setDocumentTitle("Teste API  Restassured");
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setReportName(
                    "<img id='img' src='' />\n" +
                            "\t<span style='margin-left:65px'>Relat&oacute;rio de execu&ccedil;&atilde;o dos testes</span>\n");
            htmlReporter.config().setJS(js);
            htmlReporter.config().setCSS(css);

            htmlReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

        return extent;
    }

    private static String getReportFileLocation (Platform platform) {
        String reportFileLocation = null;
        switch (platform) {
            case MAC:
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case LINUX:
                reportFileLocation = lixReportFileLoc;
                createReportPath(linuxPath);
                System.out.println("ExtentReport Path for WINDOWS: " + linuxPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }

    private static void createReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    private static Platform getCurrentPlatform () {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }

}

