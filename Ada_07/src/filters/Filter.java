package src.filters;

public abstract class Filter implements IFilter {
    protected Filter nextFilter;
    
    protected abstract Object process(Object input);

    public Object apply(Object input){
        Object output = process(input);
        if( this.nextFilter != null ) output = this.nextFilter.apply(output);
        return output;
    }

    public void connect(Filter newFilter){
        if( this.nextFilter == null ) this.nextFilter = newFilter;
        else this.nextFilter.connect(newFilter);
    }
}
