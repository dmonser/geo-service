import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImplTest {

    @ParameterizedTest
    @MethodSource("addSource")
    public void testSend(Location location, String ip, String expected) {
        System.out.println("Starting send test");
        GeoServiceMok geoServiceMok = new GeoServiceMok();
        geoServiceMok.setValue(location);

        LocalizationServiceMok localizationServiceMok = new LocalizationServiceMok();
        Country country = location.getCountry();
        localizationServiceMok.setValue(country.name());

        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceMok, localizationServiceMok);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        //act
        String result = messageSender.send(headers);
        //assert
        Assertions.assertEquals(expected, result);
    }

//    @ParameterizedTest
//    @MethodSource("addSource")
//    public void testSend(Location location, String ip, String expected) {
//        System.out.println("Starting send test");
//        GeoService geoService = Mockito.mock(GeoService.class);
//        Mockito.when(geoService.byIp(ip))
//                .thenReturn(location);
//
//        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
//        Mockito.when(localizationService.locale(location.getCountry()))
//                .thenReturn(expected);
//
//        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
//
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
//        //act
//        String result = messageSender.send(headers);
//        //assert
//        Assertions.assertEquals(expected, result);
//    }

    public static Stream<Arguments> addSource() {
        String ip1 = "127.0.0.1";
        String ip2 = "172.0.32.11";
        String ip3 = "96.44.183.149";
        String ip4 = "172.0.32.113";
        String ip5 = "96.44.183.15";
        String ip6 = "54.99.0.5";

        String dobro = "Добро пожаловать";
        String welcome = "Welcome";

//        Location location1 = new Location(null, null, null, 0);
        Location location2 = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location location3 = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location location4 = new Location("Moscow", Country.RUSSIA, null, 0);
        Location location5 = new Location("New York", Country.USA, null, 0);

        return Stream.of(
//                Arguments.of(location1, ip1, null),
                Arguments.of(location2, ip2, dobro),
                Arguments.of(location3, ip3, welcome),
                Arguments.of(location4, ip4, dobro),
                Arguments.of(location5, ip5, welcome)
        );
    }
}