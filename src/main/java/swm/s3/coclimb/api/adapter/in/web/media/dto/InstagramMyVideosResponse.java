package swm.s3.coclimb.api.adapter.in.web.media.dto;

import swm.s3.coclimb.api.adapter.out.instagram.dto.InstagramMediaResponseDto;

import java.util.List;

public class InstagramMyVideosResponse {
    private List<InstagramMediaResponseDto> medias;
    private int count;

    public InstagramMyVideosResponse(List<InstagramMediaResponseDto> medias, int count) {
        this.medias = medias;
        this.count = count;
    }

    public static InstagramMyVideosResponse of(List<InstagramMediaResponseDto> medias) {
        return new InstagramMyVideosResponse(medias, medias.size());
    }
}
