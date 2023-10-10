package mju.chatuniv.board.infrasuructure.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import mju.chatuniv.comment.controller.dto.CommentAllResponse;

public class BoardSearchResponse {

    private Long boardId;
    private String title;
    private String content;
    private String email;
    private LocalDateTime createAt;
    private CommentAllResponse commentAllResponse;

    @QueryProjection
    public BoardSearchResponse(final Long boardId,
                               final String title,
                               final String content,
                               final String email,
                               final LocalDateTime createAt,
                               final CommentAllResponse commentAllResponse) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.email = email;
        this.createAt = createAt;
        this.commentAllResponse = commentAllResponse;
    }

    private BoardSearchResponse() {
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public CommentAllResponse getCommentAllResponse() {
        return commentAllResponse;
    }
}
