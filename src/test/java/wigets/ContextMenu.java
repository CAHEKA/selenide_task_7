package wigets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ContextMenu {

    private final SelenideElement hotSpot = $x("//div[@id='hot-spot']");

    public ContextMenu() {
        Selenide.open("https://the-internet.herokuapp.com/context_menu");
        hotSpot.should(visible, Duration.ofSeconds(30));
    }
    
    @Step("Click HotSpot")
    public ContextMenu clickHotSpot() {
        hotSpot.contextClick();
        return this;
    }

    @Step("Expected text: {expectedText}")
    public void checkContextMenuContainsText(String expectedText) {
        String actualText = switchTo().alert().getText();
        Assertions.assertEquals(expectedText, actualText,
                "Error: The text of the JS Alert does not match the expected text: " + expectedText);
        Selenide.closeWebDriver();
    }
}
