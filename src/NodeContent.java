/**
 * The interface NodeContent is used to force any content a node can have, to be
 * able to return some sort of content, that can be used for other things.
 */
public interface NodeContent {

    /**
     * Get some content.
     */
    public String toString();
}
