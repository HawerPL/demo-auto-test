package com.hawerpl.demoautotest;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    public SelenideElement registerForm = $("form[id='form-register']");
    public SelenideElement usernameInput = $("input[id='username']");
    public SelenideElement emailInput = $("input[id='email']");
    public SelenideElement passwordInput = $("input[id='password']");
    public SelenideElement confirmPasswordInput = $("input[id='confirmPassword']");
    public SelenideElement registerButton = $("input[name='registerSubmit']");
    public ElementsCollection errorMessages = $("#form-register").findAll(".form-error");
    public SelenideElement loginLink = $("#login-link");

}
