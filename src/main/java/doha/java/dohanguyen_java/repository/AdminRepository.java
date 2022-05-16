package doha.java.dohanguyen_java.repository;

import doha.java.dohanguyen_java.Model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Integer> {

   AdminModel findByEmail(String email);
}
