package com.example.demo.service.Evaluations;

import java.util.List;

import com.example.demo.entity.Evaluations.Evaluations;

public interface EvaluationsService {

public List<Evaluations> getEvaluations();
	
	public Evaluations addEvaluations(Evaluations ev);
	
	public Evaluations getEvaluations(long evId);
	
	public Evaluations updateEvaluations( long evId,Evaluations ev);
	
	public Evaluations deleteEvaluations(long id);
}
