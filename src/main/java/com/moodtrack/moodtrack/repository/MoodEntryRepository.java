package com.moodtrack.moodtrack.repository;



import com.moodtrack.moodtrack.model.MoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {
}

