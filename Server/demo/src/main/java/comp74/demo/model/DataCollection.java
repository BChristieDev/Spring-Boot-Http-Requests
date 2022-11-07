package comp74.demo.model;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class DataCollection extends HashMap<Integer,Data> {

    public DataCollection() {

        super();

        for (int i = 0; i < 10; ++i) {

            Data data = new Data(0);

            data.setData("This is data item #" + data.id);

            post(data);

        }
    }

    public Data post(Data data) {

        return super.put(data.id, data);

    }

}
