package unitech.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesApiResponse {

    private Map<String, Double> rates;

}
