package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto;

import java.util.Date;

public record GuestDetailDto(Integer id,
                             String firstName,
                             String lastName,
                             String email,
                             String phoneNumber,
                             Date createdAt,
                             Date updatedAt) {

}
