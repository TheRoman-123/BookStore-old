package md.bookstore.exception;

import java.util.Objects;

public final class OffsetOrLimitException extends IllegalArgumentException {
    private final Integer offset;
    private final Integer limit;

    public OffsetOrLimitException() {
        offset = 0;
        limit = 0;
    }

    public OffsetOrLimitException(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public OffsetOrLimitException(String message, Throwable cause, Integer offset, Integer limit) {
        super(message, cause);
        this.offset = offset;
        this.limit = limit;
    }

    public OffsetOrLimitException(String message, Integer offset, Integer limit) {
        super(message);
        this.offset = offset;
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }
}
