package mycode;
import mycode.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int count = 0;
        for (Integer key : denominations.keySet()) {
            count += key * denominations.get(key);
        }
        return count;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        HashMap<Integer, Integer> mapResult = new HashMap<>();
        TreeMap<Integer, Integer> tmpMap = new TreeMap<>(Collections.reverseOrder());
        tmpMap.putAll(denominations);
        int sum = 0;
        for (Integer keyNominal : tmpMap.keySet()) {
            int amountOfBanknotes = tmpMap.get(keyNominal);
            int valueForResult = (expectedAmount - sum) / keyNominal;
            if (valueForResult > 0) {
                if (valueForResult > amountOfBanknotes) {
                    valueForResult = amountOfBanknotes;
                }
                tmpMap.put(keyNominal, amountOfBanknotes - valueForResult);
                sum += valueForResult * keyNominal;
                mapResult.put(keyNominal, valueForResult);
            }
            if (sum == expectedAmount) {
                break;
            }
        }

        if (sum != expectedAmount) {
            sum = 0;
            mapResult = new HashMap<>();
            tmpMap = new TreeMap<>(Collections.reverseOrder());
            tmpMap.putAll(denominations);
            for (Integer keyNominal : tmpMap.keySet()) {
                int needBanknotes = expectedAmount / keyNominal;
                int amountOfBanknotes = tmpMap.get(keyNominal);
                if (expectedAmount % keyNominal == 0 && amountOfBanknotes >= needBanknotes) {
                    sum = expectedAmount;
                    tmpMap.put(keyNominal, amountOfBanknotes - needBanknotes);
                    mapResult.put(keyNominal, needBanknotes);
                    break;
                }
            }
        }

        if (sum != expectedAmount) {
            throw new NotEnoughMoneyException();
        }
        tmpMap.entrySet().removeIf(e -> e.getValue() == 0);
        denominations = new HashMap<>(tmpMap);
        return mapResult;
    }
}
