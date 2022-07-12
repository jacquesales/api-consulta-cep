package com.consultacep.apirest.service.impl;

import com.consultacep.apirest.entity.Contato;
import com.consultacep.apirest.entity.Endereco;
import com.consultacep.apirest.service.ConsultaCepService;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.consultacep.apirest.util.ConverteJsonEmString.converteJsonEmString;

@Service
public class ConsultaCepServiceImpl implements ConsultaCepService {

    static String api = "https://ws.apicep.com/cep/";
    static int sucessCode = 200;

    @Override
    public Endereco findByCep(String cep, Contato contato) throws Exception {

        String apiCallUrl = api + cep + ".json";

        try {
            URL url = new URL(apiCallUrl);
            HttpURLConnection conecttion = (HttpURLConnection) url.openConnection();

            if (conecttion.getResponseCode() != sucessCode)
                throw new RuntimeException("HTTP error code: " + conecttion.getResponseCode());

            BufferedReader response = new BufferedReader(new InputStreamReader(conecttion.getInputStream(), "UTF-8"));
            String jsonEmString = converteJsonEmString(response);

            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(jsonEmString, Endereco.class);
            endereco.setContato(contato);

            return (endereco);
        } catch (Exception e) {
            throw new Exception("Error: " + e);
        }
    }
}
