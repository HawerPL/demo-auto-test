package com.hawerpl.demoautotest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginPageTest {
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    Config config = new Config();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open(config.getProperty("app_url") + "/login");
    }

    @Test
    public void loginWrongUsername() {
        loginPage.usernameInput.setValue("QAZWSXEDC!@#$-123");
        loginPage.passwordInput.setValue(config.getProperty("default_password"));
        loginPage.loginButton.click();

        loginPage.errorMessages.shouldHave(text("Podano nieprawidłowe poświadczenia")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void loginEmptyUsername() {
        loginPage.usernameInput.setValue("");
        loginPage.passwordInput.setValue(config.getProperty("default_password"));
        loginPage.loginButton.click();

        loginPage.errorMessages.shouldHave(text("Podano nieprawidłowe poświadczenia")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void loginWrongPassword() {
        loginPage.passwordInput.setValue("QAZWSXEDC!@#$-123");
        loginPage.usernameInput.setValue(config.getProperty("default_username"));
        loginPage.loginButton.click();

        loginPage.errorMessages.shouldHave(text("Podano nieprawidłowe poświadczenia")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void loginEmptyPassword() {
        loginPage.passwordInput.setValue("");
        loginPage.usernameInput.setValue(config.getProperty("default_username"));
        loginPage.loginButton.click();

        loginPage.errorMessages.shouldHave(text("Podano nieprawidłowe poświadczenia")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void loginEmptyForm(){
        loginPage.passwordInput.setValue("");
        loginPage.usernameInput.setValue("");
        loginPage.loginButton.click();

        loginPage.errorMessages.shouldHave(text("Podano nieprawidłowe poświadczenia")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void loginRegisterLink(){
        loginPage.registerLink.click();

        webdriver().shouldHave(url(config.getProperty("app_url") + "/register"));
        registerPage.registerForm.shouldBe(visible.because("Formularz rejestracji nie jest widoczny"));
    }
}
