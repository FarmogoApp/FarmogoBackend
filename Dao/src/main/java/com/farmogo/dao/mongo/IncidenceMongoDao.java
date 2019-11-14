package com.farmogo.dao.mongo;

import com.farmogo.dao.IncidenceDao;
import com.farmogo.model.incidences.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IncidenceMongoDao implements IncidenceDao {


    public static final String COLLECTION = "Incidences";

    @Inject
    MongoDatabase mongoDatabase;


    public MongoCollection<Document> getCollection() {
        return mongoDatabase.getCollection(COLLECTION);
    }

    @Override
    public void save(Incidence incidence) {
        if (incidence.getUuid() == null) {
            incidence.setUuid(new ObjectId().toString());
            getCollection().insertOne(BsonIncienceConverter.convert(incidence));
        } else {
            getCollection()
                    .replaceOne(Filters.eq("_id", new ObjectId(incidence.getUuid())), BsonIncienceConverter.convert(incidence));
        }

    }

    @Override
    public List<Incidence> getAll() {
        return StreamSupport.stream(getCollection().find().spliterator(), false)
                .map(BsonIncienceConverter::convert).collect(Collectors.toList());
    }


    static class BsonIncienceConverter implements IncidenceVisitor {

        Document document;
        Incidence incidence;

        private BsonIncienceConverter(Incidence incidence) {
            this.document = new Document();

            this.incidence = incidence;
        }

        public static Document convert(Incidence incidence) {
            return new BsonIncienceConverter(incidence).getDocument();
        }

        public static Incidence convert(Document document) {
            IncidenceType incidenceType = IncidenceType.valueOf(document.get("type", String.class));
            Incidence incidence = null;
            switch (incidenceType) {
                case GETOFF:
                    IncidenceGetoff incidenceGetoff = new IncidenceGetoff();
                    if (document.containsKey("getoffType"))
                        incidenceGetoff.setGetoffType(GetoffType.valueOf(document.get("getoffType", String.class)));

                    if (document.containsKey("healthRegister"))
                        incidenceGetoff.setHealthRegister(document.get("healthRegister", String.class));

                    incidence = incidenceGetoff;
                    break;
                case WEIGHT:
                    IncidenceWeight incidenceWeight = new IncidenceWeight();
                    if (document.containsKey("weight"))
                        incidenceWeight.setWeight(document.get("weight", Integer.class));

                    incidence = incidenceWeight;
                    break;
                case PREGNANCY:
                    IncidencePregnancy incidencePregnancy = new IncidencePregnancy();
                    if (document.containsKey("pregnancyType"))
                        incidencePregnancy.setPregnancyType(PregnancyType.valueOf(document.get("pregnancyType", String.class)));

                    incidence = incidencePregnancy;
                    break;
                case TREATMENT:
                    IncidenceTreatment incidenceTreatment = new IncidenceTreatment();
                    if (document.containsKey("treatmentType"))
                        incidenceTreatment.setTreatmentType(TreatmentType.valueOf(document.get("treatmentType", String.class)));

                    if (document.containsKey("medicine"))
                        incidenceTreatment.setMedicine(document.get("medicine", String.class));

                    if (document.containsKey("dose"))
                        incidenceTreatment.setDose(document.get("dose", String.class));

                    incidence = incidenceTreatment;
                    break;
            }
            incidence.setUuid(document.get("_id", ObjectId.class).toString());
            if (document.containsKey("observations"))
                incidence.setObservations(document.get("observations", String.class));

            if (document.containsKey("dueDate"))
                incidence.setDueDate(ConvertUtils.convert(document.getDate("dueDate")));

            incidence.setDone(document.get("done", Boolean.class));


            return incidence;
        }


        public Document getDocument() {
            commonData();
            incidence.accept(this);
            return document;
        }

        public void commonData() {
            document.append("_id", new ObjectId(incidence.getUuid()));
            document.append("type", incidence.getType().name());
            if (incidence.getObservations() != null)
                document.append("observations", incidence.getObservations());
            if (incidence.getDueDate() != null)
                document.append("dueDate", ConvertUtils.convert(incidence.getDueDate()));
            document.append("done", incidence.isDone());
        }

        @Override
        public void visit(IncidenceGetoff obj) {
            if (obj.getGetoffType() != null)
                document.append("getoffType", obj.getGetoffType().name());
            if (obj.getHealthRegister() != null)
                document.append("healthRegister", obj.getHealthRegister());
        }

        @Override
        public void visit(IncidencePregnancy obj) {
            if (obj.getPregnancyType() != null)
                document.append("pregnancyType", obj.getPregnancyType().name());
        }

        @Override
        public void visit(IncidenceTreatment obj) {
            if (obj.getTreatmentType() != null)
                document.append("treatmentType", obj.getTreatmentType().name());
            if (obj.getMedicine() != null)
                document.append("medicine", obj.getMedicine());
            if (obj.getDose() != null)
                document.append("dose", obj.getDose());
        }

        @Override
        public void visit(IncidenceWeight obj) {
            document.append("weight", obj.getWeight());
        }
    }

}
