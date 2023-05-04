
package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryFormPositive {

    int days = 4;
    MeetDate meetDate = new MeetDate();


    @Test
    void shouldSendForm() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-петербург");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(meetDate.generateDate(days));
        $("[data-test-id='name'] input").setValue("Серова Ирина");
        $("[data-test-id='phone'] input").setValue("+79502200007");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + meetDate.generateDate(days)), Duration.ofSeconds(15));

    }

    @Test
    public void shouldSendFormWithDashInName() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Ярославль");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(meetDate.generateDate(days));
        $("[data-test-id='name'] input").setValue("Иван-да-Марья Ирина");
        $("[data-test-id='phone'] input").setValue("+79502200007");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + meetDate.generateDate(days)), Duration.ofSeconds(15));

    }

}
