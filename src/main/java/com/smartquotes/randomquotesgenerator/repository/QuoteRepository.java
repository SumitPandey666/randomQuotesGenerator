package com.smartquotes.randomquotesgenerator.repository;

import com.smartquotes.randomquotesgenerator.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    //for truely random quotes
    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Quote> findRandomQuotes(@Param("limit") int limit);

    //Get random quotes by a specific Author
    @Query(value = "SELECT * FROM quotes WHERE author_id = :authorId ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Quote> findRandomQuotesByAuthor(@Param("authorId") Long authorId, @Param("limit") int limit);

    //Get random quotes by a specific Tag
    // Requires a JOIN because tags are in a separate table
    @Query(value = """
        SELECT q.* FROM quotes q 
        JOIN quote_tags qt ON q.id = qt.quote_id 
        JOIN tags t ON qt.tag_id = t.id 
        WHERE t.name = :tagName 
        ORDER BY RAND() 
        LIMIT :limit
        """, nativeQuery = true)
    List<Quote> findRandomQuotesByTag(@Param("tagName") String tagName, @Param("limit") int limit);
}
