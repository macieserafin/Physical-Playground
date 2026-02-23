package macieserafin.pjwstk.edu.pl;

import java.util.*;

public class UnitsDb {
    public Map<String, Category> categories = new LinkedHashMap<>();

    public static class Category {
        public String base;
        public List<UnitDef> units = new ArrayList<>();
    }

    public static class UnitDef {
        public String name;
        public String symbol;
        public double to_base;
        public Double offset;
    }

//     budowa
//    "Length" -> Category(base="m", units=[mm, cm, m, km...])
//    "Mass" -> Category(base="kg", units=[g, kg, t...])

}
