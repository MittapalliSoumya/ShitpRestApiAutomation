public class ShiptExceptionHandling extends RuntimeException {

    /***
     * This methods is used to handle the exceptions caused in the code
     */

    public ShiptExceptionHandling() {
        super();
    }

    public ShiptExceptionHandling(String message) {
        super(message);
    }

    public ShiptExceptionHandling(String message, Throwable cause) {
        super(message, cause);
    }



}
