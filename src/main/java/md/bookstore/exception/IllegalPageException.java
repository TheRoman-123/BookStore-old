package md.bookstore.exception;

public final class IllegalPageException extends IllegalArgumentException {
    private final Integer pageNumber;
    private final Integer pageSize;

    public IllegalPageException() {
        pageNumber = 0;
        pageSize = 0;
    }

    public IllegalPageException(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public IllegalPageException(String message, Throwable cause, Integer pageNumber, Integer pageSize) {
        super(message, cause);
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public IllegalPageException(String message, Integer pageNumber, Integer pageSize) {
        super(message);
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
