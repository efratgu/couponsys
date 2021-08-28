package couponsys.pro2.couponsystem2.services;

import couponsys.pro2.couponsystem2.repos.CompanyRepository;
import couponsys.pro2.couponsystem2.repos.CouponRepository;
import couponsys.pro2.couponsystem2.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientServices {
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CompanyRepository companyRepository;

    public abstract boolean login(String email, String password);
}
