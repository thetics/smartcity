package com.example.smarticity.service.implementations;

import com.example.smarticity.data.model.entity.Hotel;
import com.example.smarticity.data.model.entity.IBaseInstitution;
import com.example.smarticity.data.model.entity.Reservation;
import com.example.smarticity.data.model.entity.Review;
import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.entity.enumer.Status;
import com.example.smarticity.data.model.repository.HotelRepository;
import com.example.smarticity.data.model.repository.ReservationRepository;
import com.example.smarticity.data.service.models.ReservationServiceModel;
import com.example.smarticity.data.service.services.implementations.ReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    private String description = "description";
    private Status status = Status.valueOf("CONFIRMED");
    private Date dateTo = new Date();
    private Date dateFrom = new Date();
    private InstituteType instituteType = InstituteType.valueOf("HOTEL");
    private String iBaseInstitution1 = "instituteId";

    private String getName = "name";

    private String getInformation = "info";

    private String getAddress = "adress";

    private String getCityId = "cityId";

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    HotelRepository hotelRepository;
    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ReservationServiceImpl reservationService;


    @Test
    void createReservation() {
        IBaseInstitution iBaseInstitution = new IBaseInstitution() {
            @Override
            public String getName() {
                return getName;
            }

            @Override
            public String getInformation() {
                return getInformation;
            }

            @Override
            public String getAddress() {
                return getAddress;
            }

            @Override
            public String getCityId() {
                return getCityId;
            }
        };


        Reservation reservationEntity = new Reservation(description, status, dateTo, dateFrom,instituteType, iBaseInstitution);


        ReservationServiceModel model = new ReservationServiceModel(description, status, dateTo, dateFrom,instituteType,iBaseInstitution1);

        Hotel hotel = new Hotel("name", "information", "address", "cityId"); // todo insert data
        Mockito.when(hotelRepository.findById(Mockito.any())).thenReturn(Optional.of(hotel)); // other repos

        Mockito.when(reservationRepository.saveAndFlush(Mockito.any(Reservation.class))).thenReturn(reservationEntity);

        Mockito.when(modelMapper.map(Mockito.any(Reservation.class), Mockito.any())).thenReturn(model);


        ReservationServiceModel result =reservationService.createReservation(model);

        assertNotNull(result);
        assertEquals(model.getDescription(), result.getDescription());
        assertEquals(model.getStatus(), result.getStatus());
        assertEquals(model.getDateTo(), result.getDateTo());
        assertEquals(model.getDateFrom(), result.getDateFrom());
        assertEquals(model.getInstituteType(), result.getInstituteType());
        assertEquals(model.getInstituteId(), result.getInstituteId());
    }


    @Test
    void findAllReservations() {


        IBaseInstitution iBaseInstitution = new IBaseInstitution() {
            @Override
            public String getName() {
                return getName;
            }

            @Override
            public String getInformation() {
                return getInformation;
            }

            @Override
            public String getAddress() {
                return getAddress;
            }

            @Override
            public String getCityId() {
                return getCityId;
            }
        };

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(description,status, dateTo, dateFrom,instituteType, iBaseInstitution));
        reservations.add(new Reservation(description,status, dateTo, dateFrom,instituteType, iBaseInstitution));
        reservations.add(new Reservation(description,status, dateTo, dateFrom,instituteType, iBaseInstitution));
        reservations.add(new Reservation(description,status, dateTo, dateFrom,instituteType, iBaseInstitution));

        Mockito.when(reservationRepository.findAll()).thenReturn(reservations);
        List<Reservation> reservationServiceModels = reservationService.getAllReservations();

        assertEquals(reservationRepository.findAll().size(), reservations.size());
        for (int i = 0; i < reservations.size(); i++) {
            assertEquals(reservations.get(i).getStatus(), reservationServiceModels.get(i).getStatus());
            assertEquals(reservations.get(i).getDateTo(), reservationServiceModels.get(i).getDateTo());
            assertEquals(reservations.get(i).getDateFrom(),reservationServiceModels.get(i).getDateFrom());
            assertEquals(reservations.get(i).getInstituteType(),reservationServiceModels.get(i).getInstituteType());
        }
    }

}
