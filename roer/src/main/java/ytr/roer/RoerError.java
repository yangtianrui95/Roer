package ytr.roer;

/**
 * Created by tianrui on 17-4-29.
 */
public class RoerError extends Exception {
    public RoerError(Exception e) {

    }

    public RoerError(Error e) {

    }

    public RoerError(NetworkResponse response) {

    }


    public void setNetworkTimeMs(long l) {

    }


//    public RoerError(Exception e) {
//        super(e);
//    }
//
    public RoerError() {
        super();
    }
}
