//package org.example.springrestproject.controller;
//
//import com.influxdb.client.write.Point;
//import org.example.springrestproject.service.InfluxReadService;
//import org.example.springrestproject.service.InfluxService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class InfluxController {
//    private final InfluxService influxService;
//    private final InfluxReadService influxReadService;
//
//
//    public InfluxController(InfluxService influxService, InfluxReadService influxReadService) {
//        this.influxService = influxService;
//        this.influxReadService = influxReadService;
//    }
//
//    @PostMapping("/write")
//    public void writeDate(@RequestParam Point point) {
//        influxService.writeData(point);
//    }
//
//    @GetMapping("/read")
//    public String readDate(@RequestParam String fluxQuery) {
//        return influxReadService.readData(fluxQuery).toString();
//    }
//}
