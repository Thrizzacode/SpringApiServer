package server.api.repository;

import org.springframework.data.repository.CrudRepository;
import server.api.model.Announcement;

public interface AnnouncementRepository extends CrudRepository<Announcement, String> {
    void deleteByAnnouncementid(int id);
}
