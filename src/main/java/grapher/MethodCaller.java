package grapher;
import java.util.function.Consumer;

/**
 * MethodCaller
 */
public interface MethodCaller<T> {
    /**
     * Set the Method to be called.
     */
    public void setMethod(Consumer<T> method);
    /**
     * Call the method given to this class.
     */
    public void call();
}
