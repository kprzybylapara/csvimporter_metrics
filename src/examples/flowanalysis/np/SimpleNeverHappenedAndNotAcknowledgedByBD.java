package examples.flowanalysis.np;

public class SimpleNeverHappenedAndNotAcknowledgedByBD {
    
    private class Name { 
        String _name;
        
        Name (String name) { 
            _name = name;
        } 
        
        public String toString() { 
            return _name;
        } 
    } 
    
    Name _objectName; 
        
    public String getName () { 
        return _objectName.toString(); 
    } 
    
    void initialize (String name) { 
        _objectName = new Name (name); 
    } 

    /**
    public static void main(String[] args) { 
        SimpleNeverHappenedAndNotAcknowledgedByBD obj = 
        new SimpleNeverHappenedAndNotAcknowledgedByBD(); 
        System.out.println(obj.getName()); //NPE 
    } */

}