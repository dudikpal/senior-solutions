package nav;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class NavService {

    private List<Type> types = Arrays.asList(
            new Type("001", "Adóbevallás"),
            new Type("002", "Befizetés")
    );

    public List<Type> getTypes() {
        return types;
    }

    public boolean hasValidType(String value) {
        return types.stream().anyMatch(type -> type.getCode().equals(value));
    }
}
