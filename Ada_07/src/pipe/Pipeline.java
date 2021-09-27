package src.pipe;

import src.filters.Filter;

public class Pipeline{
    private Filter receiver;
    
    public Object send(Object input){
        return this.receiver.apply(input);
    }

    public void addFilter(Filter newFilter){
        if ( this.receiver == null ) this.receiver = newFilter;
        else this.receiver.connect(newFilter);
    }
}
 