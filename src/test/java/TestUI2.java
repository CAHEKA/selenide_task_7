import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import widgets.*;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.Arrays.asList;


public class TestUI2 extends BaseTest {

    @ParameterizedTest(name = "StatusCodes: {0}")
    @CsvSource({
            "200",
            "301",
            "404",
            "500",
    })
    public void testStatusCodes(String code) {
        new StatusCodes()
                .clickOnStatusCode(code)
                .checkContainsUrl(code);
    }
    
}
