package com.java.demo.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MultiThreadingImpl implements Runnable{

    private Integer threadNumber;

    @Override
    public void run(){
/*        for(int i=0; i<=5; i++){

            if(threadNumber!=null && threadNumber==2){
                throw new RuntimeException();
            }

            log.info(
                    (threadNumber != null
                            ? i + " from thread-" + threadNumber
                            : i + "")
            );
        }*/

        callTestApi();

    }

    public void callTestApi(){
        try {
            ObjectMapper mapper = new ObjectMapper();

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://localhost:9090/parallel-processing/test")
                    .build(); // defaults to GET

            Response response = client.newCall(request).execute();

            String helloResponse = "";

            //helloResponse = mapper.readValue(response.body().byteStream(), String.class);

            System.out.println(response.body());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
