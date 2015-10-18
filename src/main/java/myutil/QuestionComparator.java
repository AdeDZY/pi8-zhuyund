package myutil;
import java.util.Comparator;

import type.Question;


public class QuestionComparator implements Comparator<Question> {

	@Override
	public int compare(Question o1, Question o2) {
		String qid1 = o1.getId();
		String qid2 = o2.getId();
		return qid1.compareTo(qid2);
	}

}
