package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.response;

import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.UserDetailDto;

public record FindUserByIdResponse(String code,
                                   String error,
                                   UserDetailDto user) {
}
