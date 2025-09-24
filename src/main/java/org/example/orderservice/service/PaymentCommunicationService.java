package org.example.orderservice.service;
import org.example.orderservice.DTO.PaymentResponseDTO;
import org.example.orderservice.FeignClient.PaymentServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class PaymentCommunicationService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentCommunicationService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    // Method 1: Using RestTemplate
    public String getPaymentStatusUsingRestTemplate(Long orderId) {
        try {
            logger.debug("Fetching payment status using RestTemplate for order ID: {}", orderId);
            String url = paymentServiceUrl + "/payments/" + orderId;
            ResponseEntity<PaymentResponseDTO> response = restTemplate.getForEntity(url, PaymentResponseDTO.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().getStatus();
            }
            return "NOT_FOUND";
        } catch (RestClientException e) {
            logger.error("Error fetching payment status using RestTemplate: {}", e.getMessage());
            return "NOT_FOUND";
        }
    }

    // Method 2: Using WebClient
    public String getPaymentStatusUsingWebClient(Long orderId) {
        try {
            logger.debug("Fetching payment status using WebClient for order ID: {}", orderId);
            String url = paymentServiceUrl + "/payments/" + orderId;

            Mono<PaymentResponseDTO> response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                        return Mono.error(new RuntimeException("Payment not found"));
                    })
                    .bodyToMono(PaymentResponseDTO.class);

            PaymentResponseDTO paymentResponse = response.block();
            return paymentResponse != null ? paymentResponse.getStatus() : "NOT_FOUND";

        } catch (WebClientResponseException e) {
            // Handle WebClient specific exceptions (4xx, 5xx responses)
            logger.error("WebClient response error: Status={}, Message={}",
                    e.getStatusCode(), e.getMessage());
            return "NOT_FOUND";

        } catch (RuntimeException e) {
            // Handle other runtime exceptions (timeouts, connection errors, etc.)
            logger.error("Error fetching payment status using WebClient: {}", e.getMessage());
            return "NOT_FOUND";
        }
    }

    // Method 3: Using Feign Client
    public String getPaymentStatusUsingFeignClient(Long orderId) {
        try {
            logger.debug("Fetching payment status using Feign Client for order ID: {}", orderId);
            PaymentResponseDTO response = paymentServiceClient.getPaymentByOrderId(orderId);
            return response != null ? response.getStatus() : "NOT_FOUND";
        } catch (Exception e) {
            logger.error("Error fetching payment status using Feign Client: {}", e.getMessage());
            return "NOT_FOUND";
        }
    }
}
