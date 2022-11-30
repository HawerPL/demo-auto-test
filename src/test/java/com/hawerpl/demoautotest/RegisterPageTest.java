package com.hawerpl.demoautotest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class RegisterPageTest {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    Config config = new Config();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open(config.getProperty("app_url") + "/register");
    }

    @Test
    public void registerUsedUsername(){
        
    }

    @Test
    public void registerEmptyUsername(){

    }

    @Test
    public void registerTooShortUsername(){

    }

    @Test
    public void registerUsedEmail(){

    }

    @Test
    public void registerEmptyEmail(){

    }

    @Test
    public void registerInvalidEmail(){

    }

    @Test
    public void registerEmptyPassword(){

    }

    @Test
    public void registerTooShortPassword(){

    }

    @Test
    public void registerDifferentPasswords(){

    }

    @Test
    public void registerEmptyForm(){
        registerPage.usernameInput.setValue("");
        registerPage.emailInput.setValue("");
        registerPage.passwordInput.setValue("");
        registerPage.confirmPasswordInput.setValue("");
        registerPage.registerButton.click();
        registerPage.errorMessages.shouldHave(sizeGreaterThanOrEqual(3)
                .because("Liczba niespełnionych warunków jest niepoprawna"));
    }

    @Test
    public void registerSuccess(){

        homePage.welcomeMessage.shouldHave(text(String.format("Hi, %s!", config.getProperty("default_username")))
                .because("Wiadomość powitalna jest niepoprawna"));
    }

}
