package mycode;
import java.util.*;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (map.containsKey(currencyCode)) {
            return map.get(currencyCode);
        } else {
            map.put(currencyCode, new CurrencyManipulator(currencyCode));
            return getManipulatorByCurrencyCode(currencyCode);
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        Collection<CurrencyManipulator> list = new ArrayList<>();
        map.keySet().forEach(code->list.add(map.get(code)));
        return list;
    }
}
