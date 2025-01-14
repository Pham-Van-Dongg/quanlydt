package com.example.demo.service.Evaluations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Evaluations.Evaluations;
import com.example.demo.repository.Evaluations.EvaluationsRepository;

@Service
public class EvaluationsServiceImpl implements EvaluationsService {

	@Autowired
	private EvaluationsRepository evaluationsRepository;

	@Override
	public List<Evaluations> getEvaluations() {
		// TODO Auto-generated method stub
		return evaluationsRepository.findAll();
	}

	@Override
	public Evaluations addEvaluations(Evaluations ev) {
		// TODO Auto-generated method stub
		evaluationsRepository.save(ev);
		return null;
	}

	@Override
	public Evaluations getEvaluations(long evId) {
		// TODO Auto-generated method stub
		Evaluations ev = evaluationsRepository.findById(evId).get();
		return ev;
	}

	@Override
	public Evaluations updateEvaluations(long evId, Evaluations ev) {
		// TODO Auto-generated method stub
		Evaluations existingEvaluations = evaluationsRepository.findById(evId).orElse(null);

		if (existingEvaluations == null) {
			throw new RuntimeException("Evaluations not found with ID: " + evId); // Hoặc trả về null
		}
		
		existingEvaluations.setProject(ev.getProject());
		existingEvaluations.setEvaluation_date(ev.getEvaluation_date());
		existingEvaluations.setComments(ev.getComments());
		existingEvaluations.setScore(ev.getScore());
		existingEvaluations.setBoard_name(ev.getBoard_name());

		return evaluationsRepository.save(existingEvaluations);
	}

	@Override
	public Evaluations deleteEvaluations(long id) {
		// TODO Auto-generated method stub
		evaluationsRepository.delete(evaluationsRepository.findById(id).get());
		return null;
	}

}
