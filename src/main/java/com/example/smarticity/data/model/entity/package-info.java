
@AnyMetaDef(name = "InstituteReviewsMetaDef", idType = "string", metaType = "string",
        metaValues = {
                @MetaValue(targetEntity = Hotel.class, value = "HOTEL"),
                @MetaValue(targetEntity = Hospital.class, value = "HOSPITAL"),
                @MetaValue(targetEntity = Business.class, value = "BUSINESS")
        })
package com.example.smarticity.data.model.entity;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;


