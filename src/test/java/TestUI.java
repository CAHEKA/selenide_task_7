import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import wigets.ContextMenu;
import wigets.DragAndDrop;
import wigets.InfiniteScroll;
import wigets.KeyPresses;

import java.util.stream.Stream;


public class TestUI extends BaseTest {

    @Test()
    @DisplayName("Drag and drop")
    public void testDragAndDrop() {
        new DragAndDrop()
                .dragAndDrop("A", "B");
    }

    @Test()
    @DisplayName("Context menu")
    public void testContextMenu() {
        new ContextMenu()
                .clickHotSpot()
                .checkContextMenuContainsText("You selected a context menu");
    }

    @Test()
    @DisplayName("Infinite scroll")
    public void testInfiniteScroll() {
        new InfiniteScroll()
                .scrollTextAndSearch("Eius");
    }

    static Stream<String> keyPressesData() {
        return Stream.of("ENTER", "CONTROL", "ALT", "TAB","A","B","C","D","E","f","g","x","w","z");
    }

    @ParameterizedTest(name = "Key {0} presses")
    @MethodSource("keyPressesData")
    public void testKeyPresses(String keys) {
        new KeyPresses()
                .keyPresses(keys)
                .checkKey(keys);
    }


}
