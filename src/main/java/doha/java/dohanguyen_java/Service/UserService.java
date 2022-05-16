package doha.java.dohanguyen_java.Service;

import doha.java.dohanguyen_java.Model.UserModel;
import doha.java.dohanguyen_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> listall() {
        return (List<UserModel>) userRepository.findAll();
    }

    public void saveUser(UserModel userModel) {
        userRepository.save(userModel);
    }

    public UserModel Getid(Integer id) throws IdNotFound {
        Optional<UserModel> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IdNotFound("khong tim thay id " + id);
        }
    }

    public void DeleteuserId(Integer id) throws IdNotFound {
        Long a = userRepository.countById(id);
        if (a == null || a == 0) {
            throw new IdNotFound("khong tim thay id " + id);
        } else {
            userRepository.deleteById(id);
        }
    }
}
