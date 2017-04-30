package ytr.roer;

/**
 * Created by tianrui on 17-4-29.
 */
public interface Network {
    NetworkResponse performRequest(Request<?> request) throws RoerError;
}
