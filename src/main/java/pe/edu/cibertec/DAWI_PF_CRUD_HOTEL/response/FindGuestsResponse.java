package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.response;

import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.GuestDto;

public record FindGuestsResponse(String code,
                                 String error,
                                 Iterable<GuestDto> guests) {
}
