package examples.flowanalysis;
import java.util.Iterator;
import java.util.Collection;

public class InefficientCollectionRemoval
{
    
    /**
     * Example of incorrect map iteration
     * @param collection
     */
    public void inefficientRemoval(Collection collection)
    {
        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object element = iter.next();
            collection.remove(element);
        }
    }
    
    /**
     * Example of correct map iteration
     * @param collection
     */
    public void effectiveRemoval(Collection collection)
    {
        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            iter.remove();
        }
    }
}
