package server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import server.api.model.Announcement;
import server.api.model.UserIdentity;
import server.api.repository.AnnouncementRepository;

import javax.transaction.Transactional;

@Transactional
@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private UserIdentity userIdentity;

    //查詢所有公告
    public Iterable<Announcement> getAll(Pageable pageable) {
        return announcementRepository.findAll(pageable);
    }
    //新增公告
    public Announcement addAnnouncement(Announcement announcement) {
        announcement.setPublisher(userIdentity.getUsername());
        return announcementRepository.save(announcement);
    }
    //編輯公告
    public Announcement editAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }
    //刪除公告
    public void deleteAnnouncement(int id){
        announcementRepository.deleteByAnnouncementid(id);
    }


}
