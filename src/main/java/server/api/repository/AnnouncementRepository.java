package server.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import server.api.model.Announcement;

public interface AnnouncementRepository extends PagingAndSortingRepository<Announcement, String> {
    void deleteByAnnouncementid(int id);
}
