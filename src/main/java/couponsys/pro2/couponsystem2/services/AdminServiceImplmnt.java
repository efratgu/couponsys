package couponsys.pro2.couponsystem2.services;

import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Coupon;
import couponsys.pro2.couponsystem2.beans.Customer;
import couponsys.pro2.couponsystem2.exception.CouponSystemException;
import couponsys.pro2.couponsystem2.exception.ErrMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImplmnt extends ClientServices implements AdminService {

    @Override
    public void addCompany(Company c) throws CouponSystemException {

        if (companyRepository.existsByName(c.getName())) {

            throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXIST);
        }
        if (companyRepository.existsByEmail(c.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_EXIST);
        }
        companyRepository.save(c);
    }

    @Override
    public void updateCompany(int id, Company c) throws CouponSystemException {
        if (!companyRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        if (!companyRepository.existsByName(c.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_NOT_EXIST);
        }


        companyRepository.saveAndFlush(c);

    }

    @Override
    public void deleteCompany(int id) throws CouponSystemException {
        if (!companyRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }
        ///  List<Coupon> companucoupons=couponRepository.findByCompany(companyRepository.getById(id));
        //   customerRepository.deletePurchaseCoupon2(companucoupons);
        //   for (Coupon c:companucoupons) {
        customerRepository.deletePurchaseCoupon2(id);
//
        //    }
        companyRepository.deleteById(id);


    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(int id) throws CouponSystemException {
        return companyRepository.findById(id).orElseThrow(() -> new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST));
    }

    @Override
    public void addCCustomer(Customer c) throws CouponSystemException {

        if (customerRepository.existsByEmail(c.getEmail())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_EXIST);
        }
        customerRepository.save(c);
    }

    @Override
    public void updateCustomer(int id, Customer c) throws CouponSystemException {
        if (!customerRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        customerRepository.saveAndFlush(c);
    }

    @Override
    public void deleteCustomer(int id) throws CouponSystemException {
        if (!customerRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }
        customerRepository.deletePurchaseCoupon(id);
        customerRepository.deleteById(id);

    }

    @Override
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }


    @Override
    public Customer getOneCustomer(int id) throws CouponSystemException {
        return customerRepository.findById(id).orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST));
    }


    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

}
