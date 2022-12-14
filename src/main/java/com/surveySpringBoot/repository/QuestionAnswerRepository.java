package com.surveySpringBoot.repository;

import com.surveySpringBoot.model.Answer;
import com.surveySpringBoot.model.Question;
import com.surveySpringBoot.model.QuestionAnswer;
import com.surveySpringBoot.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {

    @Query(value = "select question_answer.id, id_question, id_answer" +
                " from survey_composition, question_answer where question_answer.id = survey_composition.id_question_answer " +
                "and id_survey = ?1 ; ",
        nativeQuery = true)
       List<QuestionAnswer> getQuestionAnswerByIdSurvey(Integer Id);

    @Query("select q from QuestionAnswer q where q.question = ?1 and q.answer = ?2")
    List<QuestionAnswer> findByQuestionAndAnswer(Question question, Answer answer);


}