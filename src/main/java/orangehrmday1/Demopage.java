package orangehrmday1;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Demopage {

	public static void main(String[] args) throws BiffException, IOException, InterruptedException {

		String data[][] = null;

		String excelpath = "Orangehrmtesting.xls";

		FileInputStream fis = new FileInputStream(excelpath);

		Workbook wb = Workbook.getWorkbook(fis);

		Sheet sheet = wb.getSheet("Contactsales");

		int row = sheet.getRows();
		int column = sheet.getColumns();

		data = new String[row][column];

		Cell cell;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {

				cell = sheet.getCell(j, i);

				String celldata = cell.getContents();

				data[i][j] = celldata;

				System.out.print(data[i][j] + " ");

			}

			System.out.println();
		}

		Demopage obj = new Demopage();
		for (int i = 0; i < data.length; i++) {
			obj.contactsales(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4]);
		}

	}

	void demoLogin(String fullname, String businessemail, String phonenumber) throws InterruptedException {

		int sleeptime = 1000;

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://www.orangehrm.com/");

		Thread.sleep(sleeptime);
		driver.manage().window().maximize();

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/ul/li[1]/a/button")).click();

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_FullName")).sendKeys(fullname);

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_Email")).sendKeys(businessemail);

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"Form_getForm_Country\"]")).click();

		Thread.sleep(sleeptime);
//		driver.findElement(By.xpath("//*[@id=\"Form_getForm_Country\"]")).click();
		driver.findElement(By.id("Form_getForm_Country")).click();

		WebElement el = driver.findElement(By.id("Form_getForm_Country"));
		Select obj = new Select(el);
		obj.selectByVisibleText("United States");

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_Contact")).sendKeys(phonenumber);

//		Thread.sleep(sleeptime);
//		driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).isSelected();

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"Form_getForm_action_submitForm\"]")).click();

		Thread.sleep(sleeptime);
		driver.close();
	}

	void contactsales(String fullname, String phnumber, String email, String jobtittle, String discription)
			throws InterruptedException {

		int sleeptime = 1000;

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/");

		Thread.sleep(sleeptime);
		driver.manage().window().maximize();

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/ul/li[2]/a/button")).click();

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_FullName")).sendKeys(fullname);

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_Contact")).sendKeys(phnumber);

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_Email")).sendKeys(email);

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_Country")).click();
		WebElement el = driver.findElement(By.id("Form_getForm_Country"));
		Select obj = new Select(el);
		obj.selectByVisibleText("United States");

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_NoOfEmployees")).click();
		WebElement we = driver.findElement(By.id("Form_getForm_NoOfEmployees"));
		Select object = new Select(we);
		object.selectByVisibleText("76 - 100");

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_JobTitle")).sendKeys(jobtittle);

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_Comment")).sendKeys(discription);

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).click();

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"Form_getForm_action_submitForm\"]")).click();
	}
}
