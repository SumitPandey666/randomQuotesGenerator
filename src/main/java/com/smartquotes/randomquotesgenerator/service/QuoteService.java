package com.smartquotes.randomquotesgenerator.service;

import com.smartquotes.randomquotesgenerator.entity.Quote;
import com.smartquotes.randomquotesgenerator.repository.AuthorRepository;
import com.smartquotes.randomquotesgenerator.repository.QuoteRepository;
import com.smartquotes.randomquotesgenerator.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;

    //general random quotes
    public List<Quote> getRandomQuotes(int count){
        int validCount = validateCount(count);
        return quoteRepository.findRandomQuotes(validCount);
    }

    //random quote by author
    public List<Quote> getRandomQuotesByAuthor(String authorName, int count){
        int validCount = validateCount(count);

        //checking if the author exists
        return authorRepository.findByName(authorName)
                .map(author -> quoteRepository.findRandomQuotesByAuthor(author.getId(), validCount))
                .orElse(Collections.emptyList());
    }

    //random quotes by theme
    public List<Quote> getRandomQuotesByTag(String tagName, int count) {
        int validCount = validateCount(count);
        return quoteRepository.findRandomQuotesByTag(tagName, validCount);
    }

    // This ensures no one can spam DB asking for 1,000,000 quotes.
    private int validateCount(int count) {
        if (count < 1) return 1;
        if (count > 10) return 10;
        return count;
    }
}
