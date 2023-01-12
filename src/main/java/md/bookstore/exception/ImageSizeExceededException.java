package md.bookstore.exception;

import static md.bookstore.Constants.MAX_IMAGE_SIZE_MB;

public final class ImageSizeExceededException extends IllegalArgumentException {
    private final long actualSize;

    public ImageSizeExceededException(long actualSize) {
        super("The image is too big. Maximum allowed size is " + MAX_IMAGE_SIZE_MB + "MB");
        this.actualSize = actualSize;
    }
}
