//package org.example.springrestproject.service;
//
//
//import com.influxdb.client.QueryApi;
//import com.influxdb.query.FluxTable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class InfluxReadService {
//    private final QueryApi queryApi;
//
//    public InfluxReadService(QueryApi queryApi) {
//        this.queryApi = queryApi;
//    }
//
//    public List<FluxTable> readData(String fluxQuery) {
//        return queryApi.query(fluxQuery);
//    }
//}
