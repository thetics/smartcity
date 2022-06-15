package com.example.smarticity.web.controller;

import com.example.smarticity.data.model.entity.IBaseInstitution;
import com.example.smarticity.data.model.entity.Reservation;
import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.entity.enumer.Status;
import com.example.smarticity.data.service.models.ReservationServiceModel;
import com.example.smarticity.data.service.services.implementations.ReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {
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
    ReservationServiceImpl reservationService;

    @Mock
    Errors errors;

    @InjectMocks
    ReservationController reservationController;


    @Test
    void viewAllReservations() {
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

        List<Reservation> models = new ArrayList<>();
        Reservation model = new Reservation(description, status, dateTo, dateFrom,instituteType,iBaseInstitution);
        Reservation model1 = new Reservation(description,status, dateTo, dateFrom,instituteType,iBaseInstitution);
        Reservation model2 = new Reservation(description, status, dateTo, dateFrom,instituteType,iBaseInstitution);

        models.add(model);
        models.add(model1);
        models.add(model2);



        Mockito.when(reservationService.getAllReservations()).thenReturn(models);

        ResponseEntity<List<Reservation>> result = reservationController.getAllReservations();

        assertEquals(result.getBody().size(),models.size());

    }

//    @Test
//    void createHospital_validationTest() {
//        HospitalServiceModel model = new HospitalServiceModel("test1", "test2", "test3", "test4");
//
//        List<ObjectError> errorList = new ArrayList<>();
//        errorList.add(new ObjectError("name", "invalid length."));
//
//        Mockito.when(errors.hasErrors()).thenReturn(true);
//        Mockito.when(errors.getAllErrors()).thenReturn(errorList);
//
//        ResponseEntity<?> result = hospitalController.createHospital(model, errors);
//        assertNotNull(result);
//        assertEquals(result.getBody(), errorList);
//    }
    @Test
    void createReservation() {

        ReservationServiceModel model = new ReservationServiceModel(description, status, dateTo, dateFrom,instituteType,iBaseInstitution1);

        Mockito.when(reservationService.createReservation(Mockito.any(ReservationServiceModel.class)))
                .thenReturn(model);

        ReservationServiceModel result = (ReservationServiceModel) reservationController.createReservation(model, errors).getBody();

        assertNotNull(result);
        assertEquals(model.getDescription(), result.getDescription());
        assertEquals(model.getStatus(), result.getStatus());
        assertEquals(model.getDateTo(), result.getDateTo());
        assertEquals(model.getDateFrom(), result.getDateFrom());
        assertEquals(model.getInstituteType(), result.getInstituteType());
        assertEquals(model.getInstituteId(), result.getInstituteId());
    }

}
