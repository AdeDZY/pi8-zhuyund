package rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import myutil.PassageComparator;
import type.Passage;
import type.Question;

public class CompositeRanker extends AbstractRanker implements IAggregator {

  /** Individual rankers */
  private List<IRanker> rankers;

  public CompositeRanker() {
    rankers = new ArrayList<IRanker>();
  }

  public void addRanker(IRanker ranker) {
    rankers.add(ranker);
  }

  /**
   * Sorts the given list of passages associated with the given question, and returns a ranked list
   * of passages. 
   * 
   * @param question
   * @param passages
   */
  @Override
  public List<Passage> rank(Question question, List<Passage> passages) {
    System.out.println(question.getId());
    for(Passage passage:passages){
      passage.setScore(this.score(question, passage));
    }
    List<Passage> rankedPassages = new ArrayList<Passage>(passages);
    Collections.sort(rankedPassages, new PassageComparator());
    return rankedPassages;
  }

  
  /**
   * Returns a score of the given passage associated with the given question.
   * 
   * @param question
   * @param passage
   * @return a score of the passage
   */
  @Override
  public Double score(Question question, Passage passage) {
    List<Double> scores = new ArrayList<Double>();
    for (IRanker r : rankers) {
      scores.add(r.score(question, passage));
    }
    return aggregateScores(scores);
  }

  /**
   * Aggregates the given scores, and returns a resulting score.
   * 
   * @param scores
   * @return an aggregated score: w1 * s1 + w2 * s2
   */
  @Override
  public Double aggregateScores(List<Double> scores) {
    double w1 = 0.5, w2 = 0.5;
    return w1 * scores.get(0) + w2 * scores.get(1);
    
  }
}
