package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.api.response.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {
    @Value("${quote.api.key}")
    private String apikey;
    private static final String API="https://api.api-ninjas.com/v2/quotes?category=success&X-Api-Key=API_KEY";

    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponse getquote(){
        String finalAPI=API.replace("API_KEY",apikey);
        ResponseEntity<QuoteResponse[]> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, QuoteResponse[].class);
        QuoteResponse[] body = response.getBody();
        return (body != null && body.length > 0) ? body[0] : null;
    }
}
