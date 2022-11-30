package com.hawerpl.demoautotest;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public SelenideElement welcomeMessage = $("h2[id='welcome-message']");
}
