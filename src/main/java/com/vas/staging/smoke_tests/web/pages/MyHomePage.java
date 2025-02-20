package com.vas.staging.smoke_tests.web.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyHomePage {
    public void quickUserOptionsVisible() {
        $("#page-content-wrapper > section").shouldBe(visible);
    }

    public void openMydcDialog() {
        $(Selectors.withTagAndText("button", "GET STARTED WITH MYDC")).click();
    }

    public void isMydcDialogOpened() {
        $("form").shouldBe(visible).shouldHave(text("Get started with MYDC"));
    }

    public void closeMydcDialog() {
        $(".vas-modal__container > svg.icon-close").click();
    }

    private SelenideElement getMyConnectionsTable() {
        return $$("table").filterBy(attribute("data-attribute",
                "my-connected-dairies")).first();
    }

    public void myConnectionTableVisible() {
        SelenideElement table = getMyConnectionsTable();

        table.shouldBe(visible, Duration.ofSeconds(8));
    }

    public void myConnectionTableHasElement(String tableElementName) {
        SelenideElement table = getMyConnectionsTable();
        SelenideElement demoRow = table.$$("td").findBy(text(tableElementName));
        demoRow.shouldBe(visible);
    }

    public int getMyConnectionsTableTotalRecords() {
        String records = $$("span").filterBy(attribute("data-attribute",
                "total-records")).first().getText();
        return Integer.parseInt(records.split(" ")[0]);
    }

    public void openSearch() {
        $(".context-selector__caret").click();
    }

    public SelenideElement findDairyByHerdcode(int herdCode) {
        $("#context-selector-input").setValue(Integer.toString(herdCode));
        return $("div.results__context-item");
    }

    public String getDairyName(SelenideElement selenideElement) {
        return selenideElement.find(".results__dairy--name").getText();
    }
}
