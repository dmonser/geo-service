import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;


public class LocalizationServiceImplTest {
    LocalizationServiceImpl sut;

    @BeforeEach
    public void beforeEach(){
        sut = new LocalizationServiceImpl();
    }

    @AfterEach
    public void afterEach(){
        sut = null;
    }

    @ParameterizedTest
    @MethodSource("addSource")
    public void testLocale(Country country, String expected){
        System.out.println("Start locale test");
        //act
        String result = sut.locale(country);
        //assert
        Assertions.assertEquals(result, expected);
    }

    public static Stream<Arguments> addSource(){
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );
    }
}
