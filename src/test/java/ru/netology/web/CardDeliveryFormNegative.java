package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormNegative {
    int days = 4;
    MeetDate meetDate = new MeetDate();

    @Test
    public void invalidPhone() {
        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999/");
        $x( "//input[@placeholder='Город']" ).setValue( "Ярославль" );
        $x( "//input[@placeholder='Дата встречи']" ).doubleClick().sendKeys( Keys.BACK_SPACE );
        $x( "//input[@placeholder='Дата встречи']" ).setValue( meetDate.generateDate( days ) );
        $( "[data-test-id='name'] input" ).setValue( "Серова Ирина" );
        $( "[data-test-id='phone'] input" ).setValue( "89501005050" );
        $( "[data-test-id='agreement']" ).click();
        $x( "//*[text()='Забронировать']" ).click();
        $( "[data-test-id='phone'] span.input__sub" ).shouldHave( exactText( "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678." ) ).shouldBe( Condition.visible );

    }

    @Test
    public void invalidCity() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x( "//input[@placeholder='Город']" ).setValue( "Saint Petersburg" );
        $x( "//input[@placeholder='Дата встречи']" ).doubleClick().sendKeys( Keys.BACK_SPACE );
        $x( "//input[@placeholder='Дата встречи']" ).setValue( meetDate.generateDate( days ) );
        $( "[data-test-id='name'] input" ).setValue( "Серова Ирина" );
        $( "[data-test-id='phone'] input" ).setValue( "+79502200007" );
        $( "[data-test-id='agreement']" ).click();
        $x( "//*[text()='Забронировать']" ).click();
        $( "[data-test-id='city'] span.input__sub" ).shouldHave( exactText( "Доставка в выбранный город недоступна" ) ).shouldBe( Condition.visible );

    }

    @Test
    public void emptyDate() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x( "//input[@placeholder='Город']" ).setValue( "Ярославль" );
        $x( "//input[@placeholder='Дата встречи']" ).doubleClick().sendKeys( Keys.BACK_SPACE );
        $( "[data-test-id='name'] input" ).setValue( "Серова Ирина" );
        $( "[data-test-id='phone'] input" ).setValue( "+79502200007" );
        $( "[data-test-id='agreement']" ).click();
        $x( "//*[text()='Забронировать']" ).click();
        $( "[data-test-id='date'] span.input__sub" ).shouldHave( exactText( "Неверно введена дата" ) ).shouldBe( Condition.visible );

    }

    @Test
    public void emptyPhone() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x( "//input[@placeholder='Город']" ).setValue( "Ярославль");
        $x( "//input[@placeholder='Дата встречи']" ).doubleClick().sendKeys( Keys.BACK_SPACE );
        $x( "//input[@placeholder='Дата встречи']" ).setValue( meetDate.generateDate( days ) );
        $( "[data-test-id='name'] input" ).setValue( "Серова Ирина" );
        $( "[data-test-id='agreement']" ).click();
        $x( "//*[text()='Забронировать']" ).click();
        $( "[data-test-id='phone'] span.input__sub" ).shouldHave( exactText( "Поле обязательно для заполнения" ) ).shouldBe( Condition.visible );

    }
}
