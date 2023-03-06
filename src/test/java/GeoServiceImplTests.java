import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTests {
    GeoServiceImpl geoService;

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each");
        geoService = new GeoServiceImpl();
    }

    @AfterEach
    public void afterEach(){
        System.out.println("after each");
        geoService = null;
    }

    @ParameterizedTest
    @MethodSource("addSource")
    public void testByIp(String ip, Location location){
        System.out.println("Start byIp test");
        //act
        Location result = geoService.byIp(ip);

        //assert
        Assertions.assertEquals(location.getCity(), result.getCity());
    }

    @Test
    public void testByIpOther(){ //test with other IP
        System.out.println("test byIp with non equal 172. or 96.");
        String ip = "54.99.0.5";
        String expected = null;
        //act
        Executable executable = () -> geoService.byIp(ip);

        //assert
        Assertions.assertThrows(NullPointerException.class, executable);
    }

    public static Stream<Arguments> addSource(){
        String ip1 = "127.0.0.1";
        String ip2 = "172.0.32.11";
        String ip3 = "96.44.183.149";
        String ip4 = "172.0.32.113";
        String ip5 = "96.44.183.15";
        String ip6 = "54.99.0.5";

        Location location1 = new Location(null, null, null, 0);
        Location location2 = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location location3 = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location location4 = new Location("Moscow", Country.RUSSIA, null, 0);
        Location location5 = new Location("New York", Country.USA, null,  0);

        return Stream.of(
                Arguments.of(ip1, location1),
                Arguments.of(ip2, location2),
                Arguments.of(ip3, location3),
                Arguments.of(ip4, location4),
                Arguments.of(ip5, location5)
        );
    }
}
