package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.GuestDetailDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.GuestDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.UserDetailDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.UserDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.entity.Guest;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.entity.User;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.repository.GuestRepository;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.repository.UserRepository;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.service.ManageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GuestRepository guestRepository;

    //Servicio Web

    @Override
    @Cacheable("users")
    public List<UserDto> getAllUsers() throws Exception {

        List<UserDto> users = new ArrayList<UserDto>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(user -> users.add(new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail())));
        return users;

    }

    @Override
    @Cacheable("user")
    public UserDto getAllUsersById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail())).orElse(null);

    }

    @Override
    @Cacheable("userDetail")
    public UserDetailDto getUserById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> new UserDetailDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt())).orElse(null);

    }

    @Override
    @CacheEvict(value = {"users", "user", "userDetail"}, key = "#userDto.id()", allEntries = true)
    public boolean updateUser(UserDto userDto) throws Exception {

        Optional<User> optional = userRepository.findById(userDto.id());
        return optional.map(user -> {
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            user.setEmail(userDto.email());
            user.setUpdatedAt(new Date());
            userRepository.save(user);
            return true;
        }).orElse(false);

    }

    @Override
    @CacheEvict(value = {"users", "user", "userDetail"}, key = "#id", allEntries = true)
    public boolean deleteUserById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);

    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public boolean addUser(UserDetailDto userDetailDto, String password) throws Exception {

        Optional<User> optional = userRepository.findByUsername(userDetailDto.username());
        if (optional.isPresent()) {
            return false;
        } else {
            User user = new User();
            user.setUsername(userDetailDto.username());
            user.setEmail(userDetailDto.email());
            user.setFirstName(userDetailDto.firstName());
            user.setLastName(userDetailDto.lastName());
            user.setRole(userDetailDto.role());

            String hashedPassword = new BCryptPasswordEncoder().encode(password);
            user.setPassword(hashedPassword);

            user.setCreatedAt(new Date());
            userRepository.save(user);
            return true;
        }

    }

    //Servicios Apis

    @Override
    @Cacheable(value = "guests", key = "'allGuests'")
    public List<GuestDto> getAllGuests() throws Exception {

        List<GuestDto> guests = new ArrayList<GuestDto>();
        Iterable<Guest> iterable = guestRepository.findAll();
        iterable.forEach(guest -> guests.add(new GuestDto(guest.getId(),
                guest.getFirstName(),
                guest.getLastName(),
                guest.getEmail())));
        return guests;

    }

    @Override
    @Cacheable(value = "guest", key = "#id")
    public Optional<GuestDto> getAllGuestsById(int id) throws Exception {

        Optional<Guest> optional = guestRepository.findById(id);
        return optional.map(guest -> new GuestDto(guest.getId(),
                guest.getFirstName(),
                guest.getLastName(),
                guest.getEmail()));
    }


    @Override
    @Cacheable(value = "guestDetail", key = "#id")
    public Optional<GuestDetailDto> getGuestById(int id) throws Exception {

        Optional<Guest> optional = guestRepository.findById(id);
        return optional.map(guest -> new GuestDetailDto(guest.getId(),
                guest.getFirstName(),
                guest.getLastName(),
                guest.getEmail(),
                guest.getPhoneNumber(),
                guest.getCreatedAt(),
                guest.getUpdatedAt()));

    }

    @Override
    @CacheEvict(value = {"guests", "guest", "guestDetail"}, key = "#guestDto.id", allEntries = true)
    public boolean updateGuest(GuestDto guestDto) throws Exception {

        Optional<Guest> optional = guestRepository.findById(guestDto.id());
        return optional.map(guest -> {
            guest.setFirstName(guestDto.firstName());
            guest.setLastName(guestDto.lastName());
            guest.setEmail(guestDto.email());
            guest.setUpdatedAt(new Date());
            guestRepository.save(guest);
            return true;
        }).orElse(false);

    }

    @Override
    @CacheEvict(value = {"guests", "guest", "guestDetail"}, key = "#id", allEntries = true)
    public boolean deleteGuestById(int id) throws Exception {

        Optional<Guest> optional = guestRepository.findById(id);
        return optional.map(guest -> {
            guestRepository.delete(guest);
            return true;
        }).orElse(false);

    }

    @Override
    @CacheEvict(value = {"guests"}, allEntries = true)
    public boolean addGuest(GuestDetailDto guestDetailDto) throws Exception {

        Optional<Guest> optional = guestRepository.findById(guestDetailDto.id());
        if (optional.isPresent()) {
            return false;
        } else {
            Guest guest = new Guest();
            guest.setFirstName(guestDetailDto.firstName());
            guest.setLastName(guestDetailDto.lastName());
            guest.setEmail(guestDetailDto.email());
            guest.setPhoneNumber(guestDetailDto.phoneNumber());
            guest.setCreatedAt(new Date());
            guestRepository.save(guest);
            return true;
        }


    }

}
