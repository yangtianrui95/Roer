package hebust.graduation.beans;

public class BaseBean {

    protected int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isStatusOK() {
        return status == 0;
    }
}
