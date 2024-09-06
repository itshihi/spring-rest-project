package org.example.springrestproject.service;

import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class InfluxService {
    private final WriteApi writeApi;

    public InfluxService(WriteApi writeApi) {
        this.writeApi = writeApi;
    }

    public void writeData(Point point){ // measurement 테이블명, field 시간 이외 컬럼, value 시간
        writeApi.writePoint(point);

    }

    public void flushData() {
        writeApi.flush();
    }
}

