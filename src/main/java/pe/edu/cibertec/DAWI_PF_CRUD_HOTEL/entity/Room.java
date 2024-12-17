package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer roomNumber;
    private String roomType;
    private Double pricePerNight;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
