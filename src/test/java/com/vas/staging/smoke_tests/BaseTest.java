package com.vas.staging.smoke_tests;

import com.codeborne.selenide.Configuration;
import io.github.cdimascio.dotenv.Dotenv;


public class BaseTest {
    public static Dotenv envVas = Dotenv.load();

    static {
        Configuration.baseUrl = envVas.get("BASE_URL_VAS");
    }
}
