package com.horriblehades.toxictalks.repos;

import com.horriblehades.toxictalks.domain.ChatMessage;
import com.horriblehades.toxictalks.domain.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepo extends CrudRepository<Report, Long> {
}
