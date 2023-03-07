import ru.netology.entity.Location;
import ru.netology.geo.GeoService;

public class GeoServiceMok implements GeoService {
    private Location value;

    @Override
    public Location byIp(String ip) {
        return value;
    }

    public void setValue(Location value) {
        this.value = value;
    }

    @Override
    public Location byCoordinates(double latitude, double longitude) {
        throw new RuntimeException("Not implemented");
    }
}
