/**
 * The class TestContent is used to hold a simple string, and can be used as
 * node content.
 */
public class TestContent implements NodeContent {

    private String content;
    
    public TestContent() {
        this.content = "Hello, World!";
    }

    @Override
    public String toString() {
        return this.content;
    }
}
