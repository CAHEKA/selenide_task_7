package wigets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class InfiniteScroll {

    private final ElementsCollection elements = $$x("//div[@class='jscroll-added']");

    public InfiniteScroll() {
        Selenide.open("https://the-internet.herokuapp.com/infinite_scroll");
        elements.first().should(visible, Duration.ofSeconds(30));
    }

    @Step("Infinite scroll and search text: {text}")
    public InfiniteScroll scrollTextAndSearch(String text) {
        while (true) {
            int firstState = elements.size();
            for (int i = 0; i < 30; i++) {
                elements.last().scrollIntoView(true);
                if ($x("//*[contains(text(),'" + text + "')]").is(visible))
                    return this;
            }
            if (firstState == elements.size())
                Assertions.fail("Error: Text <" + text + "> not found");
        }
    }

}
