package server.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import server.api.model.Announcement;
import server.api.service.AnnouncementService;

@RestController
@Tag(name = "Announcement", description = "公告相關服務")
public class AnnouncementController extends ApiController{

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/announcements")
    @Operation(
            summary = "取得所有公告",
            description = "取得所有公告",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功取得所有公告"
                    ),
            }
    )
    public Iterable<Announcement> getAllByPageable(Pageable pageable) {
        return announcementService.getAll(pageable);
    }

    @GetMapping("/announcements/count")
    @Operation(
            summary = "取得公告總條數",
            description = "取得公告總條數",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功取得公告總條數"
                    ),
            }
    )
    public long getAnnouncementCount() {
        return announcementService.getAnnouncementCount();
    }
    @PostMapping("/announcements/add")
    @Operation(
            summary = "新增公告",
            description = "新增公告",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功新增公告"
                    ),
            }
    )
    public Announcement addAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.addAnnouncement(announcement);
    }



    @PutMapping("/announcements/edit")
    @Operation(
            summary = "編輯公告",
            description = "編輯公告",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功編輯公告"
                    ),
            }
    )
    public Announcement editAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.editAnnouncement(announcement);
    }

    @DeleteMapping("/announcements/delete/{id}")
    @Operation(
            summary = "刪除公告",
            description = "刪除公告",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "成功刪除公告"
                    ),
            }
    )
    public void deleteAnnouncement(@PathVariable("id") int id) {
        announcementService.deleteAnnouncement(id);
    }
}
