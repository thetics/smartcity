package com.example.smarticity.data.model.entity;

import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.entity.enumer.Status;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationEntityTest {

    private String description = "description";
    private Status status = Status.valueOf("CONFIRMED");
    private Date dateTo = new Date();
    private Date dateFrom = new Date();
    private InstituteType instituteType = InstituteType.valueOf("HOTEL");
    //    private IBaseInstitution instituteId="das";
    private String getName = "name";

    private String getInformation = "info";

    private String getAddress = "adress";

    private String getCityId = "cityId";

    @Test
    public void testGetterSetterAll() {
        Reservation reservation = new Reservation();

        reservation.setDescription(description);
        reservation.setStatus(status);
        reservation.setDateFrom(dateFrom);
        reservation.setDateTo(dateTo);
        reservation.setInstituteType(instituteType);

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

        reservation.setInstituteId(iBaseInstitution);

        assertEquals(reservation.getInstituteId().getName(), "name");


        assertEquals(reservation.getDateFrom(), dateFrom);
        assertEquals(reservation.getDescription(), description);
        assertEquals(reservation.getInstituteType(), instituteType);
        assertEquals(reservation.getStatus(), status);
        assertEquals(reservation.getDateTo(), dateTo);

    }

    @Test
    public void testConstructor() {
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

        Reservation reservation = new Reservation(description,status, dateTo, dateFrom, instituteType, iBaseInstitution);

        reservation.setDescription(description);
        reservation.setStatus(status);
        reservation.setDateFrom(dateFrom);
        reservation.setDateTo(dateTo);
        reservation.setInstituteType(instituteType);


        assertEquals(reservation.getDateFrom(), dateFrom);
        assertEquals(reservation.getDescription(), description);
        assertEquals(reservation.getInstituteType(), instituteType);
        assertEquals(reservation.getStatus(), status);
        assertEquals(reservation.getDateTo(), dateTo);

    }
}
