package model.vo;

import java.util.Objects;

public class Number {
    private static final Integer MIN_VALUE = 0;
    private static final Integer MIX_VALUE = 24;
    private static final String NUMBER_REGEX = "^[0-9]*$";
    private final int value;

    private Number(final String value) throws IllegalArgumentException {
        validateNumber(value.trim());
        this.value = Integer.parseInt(value.trim());
    }

    public static Number generate(final String value) throws IllegalArgumentException {
        return new Number(value);
    }

    private static void validateNumber(final String value) throws IllegalArgumentException {
        if (!value.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("좌표 값에 숫자가 아닌 입력 혹은 음수 입력이 있습니다.");
        }
        int number = Integer.parseInt(value);
        if (number < MIN_VALUE || number > MIX_VALUE) {
            throw new IllegalArgumentException("점은 0~24 사이의 값만 가질 수 있습니다.");
        }
    }

    public Integer getDifference(final Number another) {
        return value - another.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return Objects.equals(value, number.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
