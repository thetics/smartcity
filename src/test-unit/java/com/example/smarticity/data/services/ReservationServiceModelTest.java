package com.example.smarticity.data.services;

import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.entity.enumer.Status;
import com.example.smarticity.data.service.models.ReservationServiceModel;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationServiceModelTest {

    private String description = "description";
    private Status status = Status.valueOf("CONFIRMED");
    private Date dateTo = new Date();
    private Date dateFrom = new Date();
    private InstituteType instituteType = InstituteType.valueOf("HOTEL");
    private String iBaseInstitution = "instituteId";


    @Test
    public void testGetterSetterAll() {
        ReservationServiceModel reservation = new ReservationServiceModel();

        reservation.setDescription(description);
        reservation.setStatus(status);
        reservation.setDateTo(dateTo);
        reservation.setDateFrom(dateFrom);
        reservation.setInstituteType(instituteType);
        reservation.setInstituteId(iBaseInstitution);


        assertEquals(reservation.getInstituteId(), iBaseInstitution);
        assertEquals(reservation.getDateFrom(), dateFrom);
        assertEquals(reservation.getDateTo(), dateTo);
        assertEquals(reservation.getDescription(), description);
        assertEquals(reservation.getInstituteType(), instituteType);
        assertEquals(reservation.getInstituteId(), iBaseInstitution);

    }

    @Test
    public void testConstructor() {


        ReservationServiceModel reservation = new ReservationServiceModel(description,status,dateTo,dateFrom, instituteType, iBaseInstitution);

        assertEquals(reservation.getInstituteId(), iBaseInstitution);
        assertEquals(reservation.getDescription(), description);
        assertEquals(reservation.getInstituteType(), instituteType);
        assertEquals(reservation.getStatus(),status);
        assertEquals(reservation.getDateTo(),dateTo);
        assertEquals(reservation.getDateFrom(),dateFrom);
    }
}
