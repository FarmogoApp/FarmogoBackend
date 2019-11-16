import com.farmogo.dao.mongo.dto.IncidenceMongo;
import com.farmogo.dao.mongo.dto.IncidenceMongoGetoff;
import com.farmogo.model.incidences.GetoffType;
import com.farmogo.model.incidences.Incidence;
import com.farmogo.model.incidences.IncidenceGetoff;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;


public class TestIncidenceConverter {

    @Test
    public void testConverter() {
        IncidenceGetoff origen = new IncidenceGetoff();
        origen.setUuid(new ObjectId().toString());
        origen.setDone(true);
        origen.setDueDate(LocalDate.of(2019, 11, 16));
        origen.setObservations("observations");
        origen.setGetoffType(GetoffType.Sale);
        origen.setHealthRegister("register");

        Incidence generic = origen;


        IncidenceMongo map = IncidenceMongo.convert(origen);
        System.out.println();
        Assert.assertEquals(origen.getUuid(), map.getUuid().toString());
        Assert.assertEquals(origen.getObservations(), map.getObservations());
        IncidenceMongoGetoff mapWithType = (IncidenceMongoGetoff) map;
        Assert.assertEquals(origen.getGetoffType(), mapWithType.getGetoffType());

        Incidence original = IncidenceMongo.convert(map);

        Assert.assertEquals(origen.getUuid(), original.getUuid());
        Assert.assertEquals(origen.getObservations(), original.getObservations());
        IncidenceGetoff originalWithType = (IncidenceGetoff) original;
        Assert.assertEquals(origen.getGetoffType(), originalWithType.getGetoffType());
    }
}
