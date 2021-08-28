package couponsys.pro2.couponsystem2.services;

import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Customer;
import couponsys.pro2.couponsystem2.exception.CouponSystemException;

import java.util.List;

public interface AdminService {
    void addCompany(Company c) throws CouponSystemException;

    void updateCompany(int id, Company c) throws CouponSystemException;

    void deleteCompany(int id) throws CouponSystemException;

    List<Company> getAllCompanies();

    Company getOneCompany(int id) throws CouponSystemException;

    void addCCustomer(Customer c) throws CouponSystemException;

    void updateCustomer(int id, Customer c) throws CouponSystemException;

    void deleteCustomer(int id) throws CouponSystemException;

    List<Customer> getAllCustomers();

    Customer getOneCustomer(int id) throws CouponSystemException;

}
