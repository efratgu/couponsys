package couponsys.pro2.couponsystem2.exception;

public enum ErrMsg {


    COMPANY_NAME_EXIST("company name already exist"),
    COMPANY_NAME_NOT_EXIST("cant update company name"),
    COMPANY_ID_NOT_EXIST("cant update company id"),
    COUPON_ID_NOT_EXIST("cant update coupon id"),
    COMPANY_EMAIL_EXIST("company email already exist"),
    COMPANY_TITLE_EXIST("company title already exist"),
    CUSTOMER_EMAIL_EXIST("customer email already exist"),
    WRONG_EMAIL_OR_PASSWORD("wrong email or password"),
    CUSTOMER_ID_NOT_EXIST("customer id not exist"),
    COUPON_AMOUNT_ZERO("cant buy coupon with zero amount"),
    CUSTOMER_ALREADY_HAVE_COUPON("cant buy coupon twice"),
    COUPON_EXPIRED("cant buy expired coupon");
    private String desc;

    ErrMsg(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
