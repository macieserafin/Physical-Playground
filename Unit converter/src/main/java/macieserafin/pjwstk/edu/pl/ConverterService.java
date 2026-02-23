package macieserafin.pjwstk.edu.pl;

import java.util.HashMap;
import java.util.Map;

public class ConverterService {
    private final UnitsDb db;
    private final Map<String, String> symbolToCategory = new HashMap<>();
    private final Map<String, UnitsDb.UnitDef> symbolToUnit = new HashMap<>();

    public ConverterService(UnitsDb db) {
        this.db = db;
        index();
    }

    private void index() {
        for (Map.Entry<String, UnitsDb.Category> entry : db.categories.entrySet()) {
            String categoryName = entry.getKey();
            for (UnitsDb.UnitDef u : entry.getValue().units) {
                symbolToCategory.put(u.symbol, categoryName);
                symbolToUnit.put(u.symbol, u);
            }
        }
    }

    public double convert(double value, String fromSymbol, String toSymbol) {
        UnitsDb.UnitDef from = symbolToUnit.get(fromSymbol);
        UnitsDb.UnitDef to = symbolToUnit.get(toSymbol);

        if (from == null) throw new IllegalArgumentException("Unknown entity: " + fromSymbol);
        if (to == null) throw new IllegalArgumentException("Unknown entity: " + toSymbol);

        String fromCat = symbolToCategory.get(fromSymbol);
        String toCat = symbolToCategory.get(toSymbol);

        if (!fromCat.equals(toCat)) {
            throw new IllegalArgumentException("Cannot convert between categories: " + fromCat + " -> " + toCat);
        }

        if ("Temperature".equals(fromCat)) {
            return convertTemperature(value, from, to);
        }

        return value * from.to_base / to.to_base;
    }

    private double convertTemperature(double value, UnitsDb.UnitDef from, UnitsDb.UnitDef to) {


        double fromOffset = from.offset == null ? 0.0 : from.offset;
        double toOffset = to.offset == null ? 0.0 : to.offset;

        double kelvin = (value * from.to_base) + fromOffset;
        return (kelvin - toOffset) / to.to_base;
    }
}
