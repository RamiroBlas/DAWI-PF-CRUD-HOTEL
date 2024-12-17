package pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.GuestDetailDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.dto.GuestDto;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.response.*;
import pe.edu.cibertec.DAWI_PF_CRUD_HOTEL.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-guest")
public class ManageApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindGuestsResponse findGuests(@RequestParam(value = "id", defaultValue = "0") String id){
        try {
            if (Integer.parseInt(id) > 0) {
                Optional<GuestDto> optional = manageService.getAllGuestsById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    GuestDto guestDto = optional.get();
                    return new FindGuestsResponse("01", "", List.of(guestDto));
                } else {
                    return new FindGuestsResponse("02", "Guest not found", null);
                }

            } else {
                List<GuestDto> guests = manageService.getAllGuests();
                if (!guests.isEmpty())
                    return new FindGuestsResponse("01", "", guests);
                else
                    return new FindGuestsResponse("03", "Guest list not found", guests);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindGuestsResponse("99", "Service not found", null);
        }
    }

    @GetMapping("/detail")
    public FindGuestByIdResponse findGuestById(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<GuestDetailDto> optional = manageService.getGuestById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    GuestDetailDto guestDetailDto = optional.get();
                    return new FindGuestByIdResponse("01", "", guestDetailDto);
                } else {
                    return new FindGuestByIdResponse("02", "Guest not found", null);
                }

            } else
                return new FindGuestByIdResponse("03", "Parameter not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindGuestByIdResponse("99", "Service not found", null);

        }

    }

    @PutMapping("/update")
    public UpdateGuestResponse updateGuest(@RequestBody GuestDto guestDto) {

        try {
            if (manageService.updateGuest(guestDto)) {
                return new UpdateGuestResponse("01", "Guest update successfully");
            } else {
                return new UpdateGuestResponse("02", "Guest not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateGuestResponse("99", "Service not found");

        }

    }

    @DeleteMapping("/delete/{id}")
    public DeleteGuestByIdResponse deleteGuest(@PathVariable String id) {
        try {
            if (manageService.deleteGuestById(Integer.parseInt(id))) {
                return new DeleteGuestByIdResponse("01", "Guest deleted successfully");
            } else {
                return new DeleteGuestByIdResponse("02", "Guest not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteGuestByIdResponse("99", "Service error");
        }
    }

    @PostMapping("/add")
    public AddGuestResponse addGuest(@RequestBody GuestDetailDto guestDetailDto) {
        try {
            if (manageService.addGuest(guestDetailDto)) {
                return new AddGuestResponse("01", "Guest added successfully");
            } else {
                return new AddGuestResponse("02", "Guest already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AddGuestResponse("99", "Service error");
        }
    }

}
