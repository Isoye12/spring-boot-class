package kr.hs.dgsw.rds;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppController {
  private final BoardRepository boardRepository;
  private final StringRedisTemplate stringRedisTemplate;

  @GetMapping("/redisHit")
  public String redisHitCount() {
    ValueOperations<String, String> vop
            = stringRedisTemplate.opsForValue();
    vop.increment("redisHitCount");
    String _count = vop.get("redisHitCount");
    return "redis Hit Count: " + _count;
  }

  @GetMapping("health")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok().body("Success Health Check");
  }

  @GetMapping("boards")
  public ResponseEntity<List<Board>> getBoards() {
    // 더미 데이터 생성을 위한 게시글 저장 로직
    Board board = new Board("게시글 제목", "게시글 내용");
    boardRepository.save(board);

    // 게시글 조회
    List<Board> boards = boardRepository.findAll();
    return ResponseEntity.ok().body(boards);
  }
}