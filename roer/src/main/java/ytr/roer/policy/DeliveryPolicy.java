package ytr.roer.policy;

import ytr.roer.Request;
import ytr.roer.Response;
import ytr.roer.RoerError;

/**
 * Created by tianrui on 17-4-29.
 */
public interface DeliveryPolicy {
    /**
     * Parses a response from the network or cache and delivers it.
     */
    public void postResponse(Request<?> request, Response<?> response);

    /**
     * Parses a response from the network or cache and delivers it. The provided
     * Runnable will be executed after delivery.
     */
    public void postResponse(Request<?> request, Response<?> response, Runnable runnable);

    /**
     * Posts an error for the given request.
     */
    public void postError(Request<?> request, RoerError error);
}
