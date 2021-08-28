package couponsys.pro2.couponsystem2.exception;

public class CouponSystemException extends Exception {

    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getDesc());
    }

}
