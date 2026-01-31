package com.smartquotes.randomquotesgenerator.controller;

import com.smartquotes.randomquotesgenerator.entity.Quote;
import com.smartquotes.randomquotesgenerator.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("/random")
    public ResponseEntity<List<Quote>> getRandomQuotes(
            @RequestParam(defaultValue = "1") int count) {
        return ResponseEntity.ok(quoteService.getRandomQuotes(count));
    }

    @GetMapping("/author")
    public ResponseEntity<List<Quote>> getQuotesByAuthor(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int count) {

        List<Quote> quotes = quoteService.getRandomQuotesByAuthor(name, count);

        if (quotes.isEmpty()) {
            return ResponseEntity.notFound().build(); // Returns 404 if author has no quotes
        }
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/theme")
    public ResponseEntity<List<Quote>> getQuotesByTag(
            @RequestParam String name,
            @RequestParam(defaultValue = "1") int count) {

        List<Quote> quotes = quoteService.getRandomQuotesByTag(name, count);

        if (quotes.isEmpty()) {
            return ResponseEntity.notFound().build(); // Returns 404 if tag has no quotes
        }
        return ResponseEntity.ok(quotes);
    }
}
