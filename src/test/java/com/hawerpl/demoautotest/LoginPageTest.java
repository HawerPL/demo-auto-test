package com.hawerpl.demoautotest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;


import static com.codeborne.selenide.Selenide.*;

public class LoginPageTest {
    LoginPage loginPage = new LoginPage();

    Config config = new Config();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open(config.getProperty("app_url"));
    }

    @Test
    public void loginWrongUsername() {
        loginPage.usernameInput.setValue("");
    }
}
