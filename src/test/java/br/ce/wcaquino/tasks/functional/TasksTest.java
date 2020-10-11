package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
		
	public WebDriver acessaAplicacao() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		
		WebDriver driver = acessaAplicacao();

		try {

			// clicar am Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selinium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("20/10/2020");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);

		} finally {

			// fechar o brwoser
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		
		WebDriver driver = acessaAplicacao();

		try {

			// clicar am Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("20/10/2020");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);

		} finally {

			// fechar o brwoser
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		
		WebDriver driver = acessaAplicacao();

		try {

			// clicar am Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selinium");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);

		} finally {

			// fechar o brwoser
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaComDataDoPassado() {
		
		WebDriver driver = acessaAplicacao();

		try {

			// clicar am Add Todo
			driver.findElement(By.id("addTodo")).click();

			// escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selinium");

			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("20/10/2010");

			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();

			// validar mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);

		} finally {

			// fechar o brwoser
			driver.quit();
		}

	}


}
