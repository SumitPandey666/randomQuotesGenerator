package com.smartquotes.randomquotesgenerator.repository;

import com.smartquotes.randomquotesgenerator.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Finds author by name ("Thomas Sowell")
    Optional<Author> findByName(String name);

    // Finds author by URL slug ("thomas-sowell")
    Optional<Author> findBySlug(String slug);
}
