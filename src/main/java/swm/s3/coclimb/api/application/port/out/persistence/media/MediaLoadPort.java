package swm.s3.coclimb.api.application.port.out.persistence.media;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import swm.s3.coclimb.domain.media.Media;

import java.util.List;
import java.util.Optional;

public interface MediaLoadPort {
    List<Media> findAll();
    List<Media> findAllVideos();

    List<Media> findMyMedias(Long userId);

    Optional<Media> findByInstagramMediaId(String instagramMediaId);

    Page<Media> findAllPaged(PageRequest pageRequest);

    Page<Media> findPagedByUserId(Long userId, PageRequest pageRequest);
}
