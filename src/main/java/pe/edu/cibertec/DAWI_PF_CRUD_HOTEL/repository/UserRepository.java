package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
