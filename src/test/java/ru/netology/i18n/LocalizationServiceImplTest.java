package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    private LocalizationServiceImpl sut;

    @AfterEach
    public void tearDown() {
        sut = null;
    }

    @BeforeEach
    public void setUp() {
        sut = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("addSource")
    public void locale(Country country, String expected) {
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