package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.service;

import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.GuestDetailDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.GuestDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.UserDetailDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    List<UserDto> getAllUsers() throws Exception;

    UserDto getAllUsersById(int id) throws Exception;

    UserDetailDto getUserById(int id) throws Exception;

    boolean updateUser(UserDto userDto) throws Exception;

    boolean deleteUserById(int id) throws Exception;

    boolean addUser(UserDetailDto userDetailDto, String password) throws Exception;

    List<GuestDto> getAllGuests() throws Exception;

    Optional<GuestDto> getAllGuestsById(int id) throws Exception;

    Optional<GuestDetailDto> getGuestById(int id) throws Exception;

    boolean updateGuest(GuestDto guestDto) throws Exception;

    boolean deleteGuestById(int id) throws Exception;

    boolean addGuest(GuestDetailDto guestDetailDto) throws Exception;

}
