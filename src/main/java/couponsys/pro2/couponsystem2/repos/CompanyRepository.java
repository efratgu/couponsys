package couponsys.pro2.couponsystem2.repos;

import couponsys.pro2.couponsystem2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByName(String name);

    boolean existsByEmail(String email);

    boolean existsByPassword(String email);

    boolean existsById(int id);

    Company findFirstByEmailAndPassword(String email, String password);


}
