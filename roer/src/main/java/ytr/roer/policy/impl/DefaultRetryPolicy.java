package ytr.roer.policy.impl;

import ytr.roer.RoerError;
import ytr.roer.policy.RetryPolicy;

/**
 * Created by tianrui on 17-4-29.
 */
public class DefaultRetryPolicy implements RetryPolicy {
    @Override
    public int getCurrentTimeout() {
        return 0;
    }

    @Override
    public void retry(RoerError exception) throws RoerError {

    }
}
