package ytr.roer.policy;

import ytr.roer.RoerError;

/**
 * Created by tianrui on 17-4-29.
 */
public interface RetryPolicy {
    int getCurrentTimeout();

    void retry(RoerError exception) throws RoerError;
}
