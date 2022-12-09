package com.hawerpl.demoautotest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class RegisterPageTest {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    static Config config = new Config();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open(config.getProperty("app_url") + "/register");
    }

    @AfterAll
    public static void endModule(){
        config.incrementProperty("iteration");
    }

    @Test
    public void registerUsedUsername(){
        registerPage.usernameInput.setValue(config.getProperty("default_username"));
        registerPage.emailInput.setValue(String.format("test@test%s.com", config.getProperty("iteration")));
        registerPage.passwordInput.setValue("qwerty123");
        registerPage.confirmPasswordInput.setValue("qwerty123");

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts("Podana nazwa użytkownika jest już w użyciu")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void registerEmptyUsername(){
        registerPage.usernameInput.setValue("");
        registerPage.emailInput.setValue(String.format("test@test%s.com", config.getProperty("iteration")));
        registerPage.passwordInput.setValue("qwerty123");
        registerPage.confirmPasswordInput.setValue("qwerty123");

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts(String.format("Login musi zawierać co najmniej %s znaki",
                config.getProperty("min_username_length")))
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void registerTooShortUsername(){
        registerPage.usernameInput.setValue(config.getProperty("short_username"));
        registerPage.emailInput.setValue(String.format("test@test%s.com", config.getProperty("iteration")));
        registerPage.passwordInput.setValue("qwerty123");
        registerPage.confirmPasswordInput.setValue("qwerty123");

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts(String.format("Login musi zawierać co najmniej %s znaki",
                config.getProperty("min_username_length")))
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void registerUsedEmail(){
        registerPage.usernameInput.setValue(String.format("user_test%s", config.getProperty("iteration")));
        registerPage.emailInput.setValue(config.getProperty("default_email"));
        registerPage.passwordInput.setValue("qwerty123");
        registerPage.confirmPasswordInput.setValue("qwerty123");

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts("Podany adres email jest już w użyciu")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void registerEmptyEmail(){
        registerPage.usernameInput.setValue(String.format("user_test_%s", config.getProperty("iteration")));
        registerPage.emailInput.setValue("");
        registerPage.passwordInput.setValue("qwerty123");
        registerPage.confirmPasswordInput.setValue("qwerty123");

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts("Niepoprawny adres email")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void registerInvalidEmail(){
        registerPage.usernameInput.setValue(String.format("user_test_%s", config.getProperty("iteration")));
        registerPage.emailInput.setValue(config.getProperty("invalid_email"));
        registerPage.passwordInput.setValue("qwerty1234567890");
        registerPage.confirmPasswordInput.setValue("qwerty1234567890");

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts("Niepoprawny adres email")
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void registerEmptyPassword(){
        registerPage.usernameInput.setValue(String.format("user_test_%s", config.getProperty("iteration")));
        registerPage.emailInput.setValue(String.format("test@test%s.com", config.getProperty("iteration")));
        registerPage.passwordInput.setValue("");
        registerPage.confirmPasswordInput.setValue("");

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts(String.
                format("Hasło musi zawierać co najmniej %s znaków",
                config.getProperty("min_password_length")))
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void registerTooShortPassword(){
        registerPage.usernameInput.setValue(String.format("user_test_%s", config.getProperty("iteration")));
        registerPage.emailInput.setValue(String.format("test@test%s.com", config.getProperty("iteration")));
        registerPage.passwordInput.setValue(config.getProperty("short_password"));
        registerPage.confirmPasswordInput.setValue(config.getProperty("short_password"));

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts(String.
                format("Hasło musi zawierać co najmniej %s znaków",
                        config.getProperty("min_password_length")))
                .because("Niepoprawny komunikat"));
    }

    @Test
    public void registerDifferentPasswords(){
        registerPage.usernameInput.setValue(String.format("user_test_%s", config.getProperty("iteration")));
        registerPage.emailInput.setValue(String.format("test@test%s.com", config.getProperty("iteration")));
        registerPage.passwordInput.setValue("qwerty1234567890");
        registerPage.confirmPasswordInput.setValue("qwerty123456");

        registerPage.registerButton.click();

        registerPage.errorMessages.shouldHave(texts("Hasła muszą być identyczne")
                .because("Niepoprawny komunikat"));
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
        registerPage.usernameInput.setValue(String.format("user_test_%s", config.getProperty("iteration")));
        registerPage.emailInput.setValue(String.format("test@test%s.com", config.getProperty("iteration")));
        registerPage.passwordInput.setValue("qwerty1234567890");
        registerPage.confirmPasswordInput.setValue("qwerty1234567890");

        registerPage.registerButton.click();

        homePage.welcomeMessage.shouldHave(text(String.format("Hi, user_test_%s!", config.getProperty("iteration")))
                .because("Wiadomość powitalna jest niepoprawna"));
    }

}
