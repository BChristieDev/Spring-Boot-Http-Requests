package comp74.demo.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comp74.demo.model.Data;
import comp74.demo.model.DataCollection;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {
    
    DataCollection dataCollection;

    @Autowired
    public ApiController(DataCollection dataCollection) {

        super();
        this.dataCollection = dataCollection;

    }

    Logger logger = LoggerFactory.getLogger(ApiController.class);
    
    @GetMapping("/data")
    public ResponseEntity<ArrayList<Data>> getData() {

        ResponseEntity<ArrayList<Data>> responseEntity;

        logger.info("GET /data");
        ArrayList<Data> dataList = new ArrayList<>(dataCollection.values());

        responseEntity = new ResponseEntity<>(dataList, HttpStatus.OK);

        return responseEntity;

    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable Integer id) {

        ResponseEntity<Data> responseEntity;
        Data data = dataCollection.get(id);

        if (data != null) {

            logger.info("GET /data/" + id);
            responseEntity = new ResponseEntity<>(data, HttpStatus.OK);

        }
        else {

            logger.error("ERROR 404: /data/" + id + " does not exit");
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }

        return responseEntity;

    }

    @PostMapping("/data")
    public ResponseEntity<Integer> postData(@RequestBody Data data) {

        ResponseEntity<Integer> responseEntity;

        data.setId();
        dataCollection.post(data);

        logger.info("POST /data/" + data.getId());

        responseEntity = new ResponseEntity<>(data.getId(), HttpStatus.CREATED);

        return responseEntity;

    }

    @PutMapping("/data/{id}")
    public ResponseEntity<Data> putData(@RequestBody Data newData, @PathVariable Integer id) {

        ResponseEntity<Data> responseEntity;
        Data oldData = dataCollection.get(id);

        if (oldData != null) {

            logger.info("PUT /data/" + newData.getId());
            newData.setId(oldData.getId());
            dataCollection.post(newData);
            responseEntity = new ResponseEntity<>(newData, HttpStatus.NO_CONTENT);

        }
        else {

            logger.error("ERROR 404: /data/" + id + " does not exit");
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }

        return responseEntity;

    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<Data> deleteData(@PathVariable Integer id) {

        ResponseEntity<Data> responseEntity;
        Data data = dataCollection.get(id);
        Data delData = null;

        if (data != null) {

            logger.info("DELETE /data/" + data.getId());
            delData = dataCollection.remove(data.getId());
            responseEntity = new ResponseEntity<>(delData, HttpStatus.NO_CONTENT);

        }
        else {
            logger.error("ERROR 404: /data/" + id + " does no exist");
            responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return responseEntity;

    }

}
