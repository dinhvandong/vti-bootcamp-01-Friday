package com.vti.shopee_project.services;

import com.vti.shopee_project.entities.Product;
import com.vti.shopee_project.repositories.ProductRepository;
import com.vti.shopee_project.request.GeminiRequest;
import com.vti.shopee_project.response.GeminiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ProductMemoryService productMemoryService;
    @Autowired
    private ProductRepository productRepository;
    public String askGemini(String question) {
        // Get relevant products (or top 10 lightweight info)
        List<Product> products = productMemoryService.getAllProducts();
      //  ..findRelevantProducts(question, 10);
                //productRepository.findAll().stream()
               // .limit(10) // optimize for token use
              //  .collect(Collectors.toList());
        System.out.println("Size:"+ products.size());
        StringBuilder prompt = new StringBuilder();
        prompt.append("I have the following products:\n");
        for (Product p : products) {
            prompt.append(String.format("- %s: %s ($%.2f)\n", p.getTitle(), p.getDescription(), p.getPrice()));
        }
        prompt.append("\nQuestion: ").append(question);
        GeminiRequest request = new GeminiRequest(List.of(
                new GeminiRequest.Content("user", List.of(new GeminiRequest.Content.Part(prompt.toString())))
        ));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GeminiRequest> entity = new HttpEntity<>(request, headers);
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="+ apiKey;
        ResponseEntity<GeminiResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, GeminiResponse.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().candidates[0].content.parts[0].text;
        }
        return "Failed to get response from Gemini.";
    }


    public List<Product> findRelevantProducts(String query, int limit) {
        String lowerQuery = query.toLowerCase();

        return productRepository.findAll().stream()
                .filter(p -> {
                    String name = Optional.ofNullable(p.getTitle()).orElse("").toLowerCase();
                    String description = Optional.ofNullable(p.getDescription()).orElse("").toLowerCase();
                    String category = Optional.ofNullable(p.getCategory()).orElse("").toLowerCase();
                    return name.contains(lowerQuery) ||
                            description.contains(lowerQuery) ||
                            category.contains(lowerQuery);
                })
                .limit(limit)
                .collect(Collectors.toList());
    }


}

