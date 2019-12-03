package com.farmogo.dao.mongo.dto;

import com.farmogo.model.incidences.IncidenceType;
import com.farmogo.model.incidences.PregnancyType;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@BsonDiscriminator(key = "type", value = "BIRTH")
public class IncidenceMongoBirth extends IncidenceMongo {

    private String officialId;
    private LocalDate birthDate;
    private ObjectId raceId;
    private String sex;
    private LocalDate enrollmentDate;

    public IncidenceMongoBirth() {
        super(IncidenceType.BIRTH);
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ObjectId getRaceId() {
        return raceId;
    }

    public void setRaceId(ObjectId raceId) {
        this.raceId = raceId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
