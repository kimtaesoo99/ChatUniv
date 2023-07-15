package mju.chatuniv.comment.application.service;

import mju.chatuniv.board.domain.Board;
import mju.chatuniv.board.domain.BoardRepository;
import mju.chatuniv.board.exception.exceptions.BoardNotFoundException;
import mju.chatuniv.comment.application.dto.CommentAllResponse;
import mju.chatuniv.comment.application.dto.CommentRequest;
import mju.chatuniv.comment.application.dto.CommentResponse;
import mju.chatuniv.comment.application.dto.CommentPageInfo;
import mju.chatuniv.comment.domain.BoardComment;
import mju.chatuniv.comment.domain.Comment;
import mju.chatuniv.comment.domain.CommentRepository;
import mju.chatuniv.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class BoardCommentService implements EachCommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public BoardCommentService(final CommentRepository commentRepository, final BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public CommentResponse create(final Long boardId, final Member member, final CommentRequest commentRequest) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException(boardId));

        BoardComment boardComment = BoardComment.of(commentRequest.getContent(), member, board);
        commentRepository.save(boardComment);

        return CommentResponse.from(boardComment);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentAllResponse findComments(final Long boardId, final Pageable pageable) {
        Board board = boardRepository.findById(boardId).orElseThrow();

        Page<Comment> commentPageInfo = commentRepository.findAllByBoard(pageable, board, boardId);
        CommentPageInfo pageInfo = CommentPageInfo.from(commentPageInfo);

        List<CommentResponse> comments = commentPageInfo.stream()
                .map(CommentResponse::from)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));

        return CommentAllResponse.from(comments, pageInfo);
    }
}

