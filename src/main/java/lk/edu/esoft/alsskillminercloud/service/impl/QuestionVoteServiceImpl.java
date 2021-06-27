package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.QuestionVotesDTO;
import lk.edu.esoft.alsskillminercloud.entity.Question;
import lk.edu.esoft.alsskillminercloud.entity.QuestionVote;
import lk.edu.esoft.alsskillminercloud.entity.User;
import lk.edu.esoft.alsskillminercloud.repository.QuestionRepository;
import lk.edu.esoft.alsskillminercloud.repository.QuestionVoteRepository;
import lk.edu.esoft.alsskillminercloud.repository.UserRepository;
import lk.edu.esoft.alsskillminercloud.service.QuestionVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QuestionVoteServiceImpl implements QuestionVoteService {

    @Autowired
    private QuestionVoteRepository questionVoteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public boolean saveQuestionVote(QuestionVotesDTO questionVotesDTO) throws Exception {

        User user = userRepository.findById(questionVotesDTO.getUserName()).get();
        Question question = questionRepository.findById(questionVotesDTO.getQuestionID()).get();

        QuestionVote questionVote = new QuestionVote(user, question, questionVotesDTO.getDate(),
                questionVotesDTO.getStatus());
        questionVoteRepository.save(questionVote);

        return true;
    }

    @Override
    public QuestionVotesDTO getQuestionVote(long id) throws Exception {

        QuestionVote questionVote = questionVoteRepository.findById(id).get();

        QuestionVotesDTO questionVotesDTO = new QuestionVotesDTO(questionVote.getId(), questionVote.getUser().getName(),
                questionVote.getQuestion().getId(), questionVote.getDate(), questionVote.getStatus());

        return questionVotesDTO;

    }

    @Override
    public boolean deleteQuestionVote(long id) throws Exception {

        questionVoteRepository.deleteById(id);

        return true;

    }

    @Override
    public ArrayList<QuestionVotesDTO> getAllQuestionVotes() throws Exception {

        List<QuestionVote> questionVotes = questionVoteRepository.findAll();

        ArrayList<QuestionVotesDTO> questionVotesDTOS = new ArrayList<>();

        for (QuestionVote questionVote : questionVotes) {
            QuestionVotesDTO questionVotesDTO = new QuestionVotesDTO(questionVote.getId(), questionVote.getUser().getName(),
                    questionVote.getQuestion().getId(), questionVote.getDate(), questionVote.getStatus());

            questionVotesDTOS.add(questionVotesDTO);
        }

        return questionVotesDTOS;

    }

    @Override
    public long getUserQuestionVotesCount(String name, long questionID) throws Exception {
        return questionVoteRepository.getUserQuestionVotesCount(name, questionID);
    }
}
