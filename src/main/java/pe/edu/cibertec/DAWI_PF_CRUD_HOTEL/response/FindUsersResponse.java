package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.response;

import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.UserDto;

public record FindUsersResponse(String code,
                                String error,
                                Iterable<UserDto> users) {
}
