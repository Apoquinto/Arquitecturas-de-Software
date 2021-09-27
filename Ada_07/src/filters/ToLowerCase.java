package src.filters;

public class ToLowerCase extends Filter {

    @Override
    protected Object process(Object input) {
        return input.toString().toLowerCase();
    }
    
}
