package macieserafin.pjwstk.edu.pl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class UnitsLoader {

    public static UnitsDb load() throws Exception {
        ObjectMapper om = new ObjectMapper();

        try (InputStream is = UnitsLoader.class.getResourceAsStream("/units.json")) {
            if (is == null) throw new IllegalStateException("Brak /units.json w resources");

            Map<String, UnitsDb.Category> raw =
                    om.readValue(is, new TypeReference<LinkedHashMap<String, UnitsDb.Category>>() {});

            UnitsDb db = new UnitsDb();
            db.categories.putAll(raw);
            return db;
        }
    }
}
