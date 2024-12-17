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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
