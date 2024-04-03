package wigets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDrop {

    private final SelenideElement container = $x("//div[@class='example']");
    private final By element = By.xpath(".//header[text()]/..");

    public DragAndDrop() {
        Selenide.open("https://the-internet.herokuapp.com/drag_and_drop");
        container.should(visible, Duration.ofSeconds(30));
    }

    private SelenideElement getElementFromName(String name) {
        return container.find(By.xpath(".//header[text()='" + name + "']/.."));
    }

    private Point getPointFromName(String name) {
        return getElementFromName(name).getLocation();
    }

    private ElementsCollection getAllElements() {
        return container.findAll(element);
    }

    private Integer getIndexFromName (String name){
        ElementsCollection elements = getAllElements();
        for (int i = 0; i < elements.size(); i++) {
            WebElement header = elements.get(i).findElement(By.tagName("header"));
            if (header.getText().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    @Step("Drag аnd drop: {a} to {b}")
    public void dragAndDrop(String a ,String b) {
        Integer firstA = getIndexFromName(a);
        Integer firstB = getIndexFromName(b);

        actions()
                .moveToElement(getElementFromName(a))
                .clickAndHold()
                .moveByOffset((getPointFromName(b).x - getPointFromName(a).x) + 2, 
                        (getPointFromName(b).y - getPointFromName(a).y) + 2)
                .release()
                .perform();

        Integer lastA = getIndexFromName(a);
        Integer lastB = getIndexFromName(b);

        Assertions.assertEquals(lastB, firstA, 
                "Error: The element '" + a + "' is not in the place of the element '" + b);
        Assertions.assertEquals(lastA, firstB, 
                "Error: The element '" + b + "' is not in the place of the element '" + a);
    }
}
