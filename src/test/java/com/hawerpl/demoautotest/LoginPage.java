package com.hawerpl.demoautotest;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement loginForm = $("form[id='form-login']");
    public SelenideElement usernameInput = $("input[id='username']");
    public SelenideElement passwordInput = $("input[id='password']");
    public SelenideElement loginButton = $("input[name='loginSubmit']");
    public SelenideElement errorMessages = $("#form-login .form-error");
    public SelenideElement registerLink = $("#register-link");
}
