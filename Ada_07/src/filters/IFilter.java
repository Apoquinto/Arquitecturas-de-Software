package src.filters;

public interface IFilter {
    public Object apply(Object input);
    public void connect(Filter newFilter);
}
