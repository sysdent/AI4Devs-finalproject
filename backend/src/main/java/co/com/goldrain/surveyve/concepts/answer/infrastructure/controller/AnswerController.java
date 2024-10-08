package co.com.goldrain.surveyve.concepts.answer.infrastructure.controller;

import co.com.goldrain.surveyve.concepts.answer.application.service.AnswerService;
import co.com.goldrain.surveyve.concepts.answer.domain.Answer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
@Tag(name = "Answer", description = "Answer management APIs")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/{id}/{emailId}/{nameId}")
    @Operation(summary = "Insert new answers")
    public ResponseEntity<Void> createAnswer(
            @PathVariable UUID id,
            @PathVariable UUID emailId,
            @PathVariable UUID nameId,
            @RequestBody Map<UUID, String> answers) {
        answerService.saveAnswers(id, emailId, nameId, answers);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing answer")
    public ResponseEntity<Answer> updateAnswer(@PathVariable UUID id, @RequestBody Answer answer) {
        answer.setId(id);
        return ResponseEntity.ok(answerService.updateAnswer(answer));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an answer")
    public ResponseEntity<Void> deleteAnswer(@PathVariable UUID id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an answer by ID")
    public ResponseEntity<Answer> getAnswerById(@PathVariable UUID id) {
        return ResponseEntity.ok(answerService.getAnswerById(id));
    }

    @GetMapping
    @Operation(summary = "Get all answers")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    @GetMapping("/count-by-option/{questionId}")
    @Operation(summary = "Count answers by option for a given question ID")
    public ResponseEntity<List<Map<String, Object>>> countAnswersByOption(@PathVariable UUID questionId) {
        return ResponseEntity.ok(answerService.countAnswersByOption(questionId));
    }

}