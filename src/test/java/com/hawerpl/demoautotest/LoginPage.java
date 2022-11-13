package com.hawerpl.demoautotest;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement usernameInput = $("");
    public SelenideElement passwordInput = $("");
    public SelenideElement loginButton = $("");
    public SelenideElement errorMessageInput = $("");
    public SelenideElement registerLink = $("");
}
