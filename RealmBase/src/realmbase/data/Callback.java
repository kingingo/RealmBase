package realmbase.data;

public interface Callback<T> {
	public void call(T obj,Throwable exception);
}
