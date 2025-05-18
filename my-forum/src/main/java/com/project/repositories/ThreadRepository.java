package com.project.repositories;

import com.project.entities.Game;
import com.project.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
    List<Thread> findByGame(Game game);

    @Query(value = """
        SELECT t.*
        FROM Thread t
        WHERE 
            (:searchTerm IS NULL OR 
            t.search_vector @@ websearch_to_tsquery('russian', :searchTerm) OR 
            t.search_vector @@ websearch_to_tsquery('english', :searchTerm))
        ORDER BY
            CASE WHEN :sortBy = 'DATE' THEN t.md_date_created END DESC,
            CASE WHEN :sortBy = 'LAST_POST' THEN t.last_post_time END DESC,
            CASE WHEN :sortBy = 'POSTS' THEN t.post_count END DESC
    """, nativeQuery = true)
    List<Thread> searchThreads(
            @Param("searchTerm") String searchTerm,
            @Param("sortBy") String sortBy
    );
}
