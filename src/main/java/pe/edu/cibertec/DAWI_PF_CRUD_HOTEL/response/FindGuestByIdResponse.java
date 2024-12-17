package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.response;

import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.GuestDetailDto;


public record FindGuestByIdResponse(String code,
                                    String error,
                                    GuestDetailDto guest) {
}
