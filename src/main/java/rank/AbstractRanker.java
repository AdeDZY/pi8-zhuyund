package rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import myutil.PassageComparator;
import type.Passage;
import type.Question;

/**
 * This class provides a skeletal implementation of interface IRanker.
 */
public abstract class AbstractRanker implements IRanker {

  /**
   * Sorts the given list of passages associated with the given question, and returns a ranked list
   * of passages. Calls the init() function first for ranker initialization.
   * 
   * @param question
   * @param passages
   */
  public List<Passage> rank(Question question, List<Passage> passages){
    this.init(question, passages);
    for(Passage passage:passages){
      passage.setScore(this.score(question, passage));
    }
    
    // use a new list for sort so that the original list will remain constant
    List<Passage> rankedPassages = new ArrayList<Passage>(passages); 
    Collections.sort(rankedPassages, new PassageComparator());
    return rankedPassages;
  }

  /**
   * Returns a score of the given passage associated with the given question. 
   * A subclass needs to implement this method
   * 
   * @param question
   * @param passage
   * @return
   */
  public abstract Double score(Question question, Passage passage);
  
  protected void init(Question question, List<Passage> passages){};

}
