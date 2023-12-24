package unitech.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExchangeRatesAPIService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ExchangeRatesAPIService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public double performCurrencyConversion(double amount, String baseCurrency, String targetCurrency) {
        String apiUrl = "https://api.exchangeratesapi.io/latest?base=" + baseCurrency + "&symbols=" + targetCurrency;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode ratesNode = root.path("rates").path(targetCurrency);

                if (!ratesNode.isMissingNode()) {
                    double rate = ratesNode.asDouble();
                    return amount * rate;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0.0;
    }
}