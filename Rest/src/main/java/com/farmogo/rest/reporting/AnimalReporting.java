package com.farmogo.rest.reporting;

import com.farmogo.model.Animal;
import com.farmogo.model.Farm;
import com.farmogo.services.AnimalService;
import com.farmogo.services.RaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.*;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Dependent
public class AnimalReporting {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    AnimalService animalService;
    @Inject
    RaceService raceService;



    public void generate(Farm farm, OutputStream out) throws IOException {
        AnimalReport  a = new AnimalReport();
        a.setFarmOfficialId(farm.getOfficialId());
        List<Animal> animals = animalService.getAnimalsByFarmId(farm.getUuid());
        a.setRegisters(animals.stream().map(this::from).collect(Collectors.toList()));


        StringBuilder sb = new StringBuilder();
        sb.append("<b>RAZA</b>: ");
        StringJoiner sj = new StringJoiner(", ");
        raceService.getAll().stream().forEach(r -> sj.add(String.format("%s: %s", r.getLetter()==null?"":r.getLetter(), r.getName())));
        sb.append(sj.toString());

        a.setObservations(sb.toString());

        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            objectMapper.writeValue( pw,a);
            System.out.println(sw.toString());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            objectMapper.writeValue( baos, a);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, bais);
            parameters.put(JRParameter.REPORT_LOCALE,new Locale("es","ES"));
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(AnimalReporting.class.getResourceAsStream("/reporting/AnimalRegister.jasper"), parameters);

            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

        } catch (JRException ex) {
            throw new ReportGeneratingError(ex);
        }
    }

    public  AnimalReportRow from(Animal animal){
        AnimalReportRow row = new AnimalReportRow();
        row.setOfficialId( animal.getOfficialId());
        row.setBirthDate(animal.getBirthDay());
        row.setRace(raceService.get(animal.getRaceId()).getLetter());
        row.setSex("Male".equals(animal.getSex())?"M":"H");
        row.setMotherOfficialId(animal.getMotherOfficialId());
        row.setEnrollmentDate(animal.getEnrrollementDate());
        row.setEnrollmentCause(animal.getEnrollmentCause());
        //row.setEnrollmentOrigin(???);
        row.setEnrollmentSanitaryRegister(animal.getEnrollmentSanitaryRegister());
        row.setDischargeDate(animal.getDischargeDate());
        row.setDischargeCause(animal.getDischargeCause());
        row.setDischargeDestination(animal.getDischargeDestination());
        row.setDischargeSanitaryRegister(animal.getDischargeSanitaryRegister());
        //row.setDateBonus1();
        //row.setDateBonus2();

        return row;
    }
}
