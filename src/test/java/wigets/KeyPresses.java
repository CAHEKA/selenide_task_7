package wigets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class KeyPresses {

    private final SelenideElement container = $(By.tagName("body"));
    private final By result = By.xpath(".//p[@id='result']");

    public KeyPresses() {
        Selenide.open("https://the-internet.herokuapp.com/key_presses");
        container.should(visible, Duration.ofSeconds(30));
    }
    
    @Step("Key presses: {key}")
    public KeyPresses keyPresses(String key) {
        container.sendKeys(Keys.ESCAPE);
        if (key.length() > 1)
            container.sendKeys(Keys.valueOf(key));
        else
            container.sendKeys(key);
        return this;
    }

    @Step("Check key: {key}")
    public KeyPresses checkKey(String key) {
        String expectedText = "You entered: " + key.toUpperCase(Locale.ROOT);
        Assertions.assertEquals(expectedText, container.find(result).text(), "The displayed text does not match the pressed key.");
        return this;
    }

}
