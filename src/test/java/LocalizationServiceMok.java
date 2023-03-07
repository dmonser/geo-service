import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;

public class LocalizationServiceMok implements LocalizationService {
    private String value;

    @Override
    public String locale(Country country) {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
