package org.ocuryb.oriontek;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase1 {

    static WebDriver driver;
    public static void main(String[] args) {
        String urlInicial = "https://www.amazon.com/";

       try{

           System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
           driver = new ChromeDriver();
           driver.get(urlInicial);

           SearchMeth();
           CompareMeth();
           Waits();

       } catch (NoSuchElementException ne){
           System.err.println("WebElement no encontrado: "+ne.getMessage());
        }catch (WebDriverException we){
            System.err.println("Fallo del WebDriver: "+we.getMessage());
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }finally{
            driver.quit();
        }

    }

     static void SearchMeth() {

        WebElement txtBuscar = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        WebElement btnBuscar = driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input"));
        txtBuscar.sendKeys("Acer Aspire 5 Slim Laptop, Plateado");
        btnBuscar.click();

        WebElement lblObjeto = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[4]/div[1]/div[3]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span"));
        lblObjeto.click();
    }

     static void CompareMeth(){
        String primerPrecio, segundoPrecio;
        WebElement lblPrecio = driver.findElement(By.xpath("//*[@id=\"priceblock_ourprice\"]"));
        WebElement btnCarro = driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]"));

        primerPrecio = lblPrecio.getText();
        btnCarro.click();

        Waits();

        btnCarro = driver.findElement(By.cssSelector("#attach-sidesheet-view-cart-button > span > input"));
        btnCarro.click();
        lblPrecio = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[5]/div/div[2]/div[4]/form/div[2]/div[3]/div[4]/div/div[2]/p/span"));
        segundoPrecio = lblPrecio.getText();

        if(primerPrecio.equals(segundoPrecio)){
            System.out.println("Prueba exitosa, ambos precios son identicos: Precio inicial: "+primerPrecio+" , Precio en carrito: "+segundoPrecio);
        }else{
            System.out.println("Prueba no exitosa, los precios difieren: Precio inicial: "+primerPrecio+" , Precio en carrito: "+segundoPrecio);
        }
    }

    static void Waits (){
        try {
            Thread.sleep(5*1000);
        }catch(Exception e) {
            System.out.println(e);
        }
    }


}
