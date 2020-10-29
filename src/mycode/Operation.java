package mycode;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {
        Operation result;
        try {
            if (i==0){
                throw new IllegalArgumentException();
            }
            result = values()[i];
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}

