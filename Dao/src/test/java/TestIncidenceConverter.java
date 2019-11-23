import com.farmogo.dao.mongo.dto.IncidenceMongo;
import com.farmogo.dao.mongo.dto.IncidenceMongoDischarge;
import com.farmogo.model.incidences.DischargeType;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.IncidenceDischarge;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;


public class TestIncidenceConverter {

    @Test
    public void testConverter() {
        IncidenceDischarge origen = new IncidenceDischarge();
        origen.setUuid(new ObjectId().toString());
        origen.setDone(true);
        origen.setDueDate(LocalDate.of(2019, 11, 16));
        origen.setObservations("observations");
        origen.setDischargeType(DischargeType.Sale);
        origen.setHealthRegister("register");

        Incidence generic = origen;


        IncidenceMongo map = IncidenceMongo.convert(origen);
        System.out.println();
        Assert.assertEquals(origen.getUuid(), map.getUuid().toString());
        Assert.assertEquals(origen.getObservations(), map.getObservations());
        IncidenceMongoDischarge mapWithType = (IncidenceMongoDischarge) map;
        Assert.assertEquals(origen.getDischargeType(), mapWithType.getDischargeType());

        Incidence original = IncidenceMongo.convert(map);

        Assert.assertEquals(origen.getUuid(), original.getUuid());
        Assert.assertEquals(origen.getObservations(), original.getObservations());
        IncidenceDischarge originalWithType = (IncidenceDischarge) original;
        Assert.assertEquals(origen.getDischargeType(), originalWithType.getDischargeType());
    }
}
