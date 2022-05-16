package doha.java.dohanguyen_java.repository;

import doha.java.dohanguyen_java.Model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Integer> {
    public Long countById(Integer id);
}
