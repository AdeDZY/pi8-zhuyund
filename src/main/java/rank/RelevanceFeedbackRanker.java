package rank;

import myutil.FSListFactory;
import myutil.PassageComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import type.Passage;
import type.Question;
import type.TokennizedPassage;

public class RelevanceFeedbackRanker extends AbstractRanker {

  int nTop;

  int nTerms;

  ArrayList<String> terms; // frequent terms find in pseudo relevance feedbacks


  /**
   * Create a ranker with nTop and nTerms
   * @param nTop # of documents to be used in pseudo relevance feedback
   * @param nTerms # of terms for question expansion
   */
  public RelevanceFeedbackRanker(int nTop, int nTerms){
    this.nTerms = nTerms;
    this.nTop = nTop;
    this.terms = new ArrayList<String>();
  }
  
  public RelevanceFeedbackRanker(){
    this.nTop = 5;
    this.nTerms = 7;
    this.terms = new ArrayList<String>();
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
    return this.evaluate(this.getPassageVec1Gram((TokennizedPassage)passage));
  }


  /**
   * Find the frequent terms in top-ranked passages as expansion terms.
   * The top nTerms terms in the top nTop passages will be used.
   * Ranking methods is a 2-grams overlapping ranker
   * @param question
   * @param passages
   */
  @Override
  protected void init(Question question, List<Passage> passages) {
    // clear previous results
    this.terms.clear();
   
    // sort passages by bigram score
    NgramRanker ngramRanker = new NgramRanker(2);
    List<Passage> ngramRankedPassages = new ArrayList<Passage>(passages);
    ngramRankedPassages = ngramRanker.rank(question, ngramRankedPassages);
    
    // ###### Find most frequent 1grams ######
    // count terms in top nTop documents
    HashMap<String, Integer> counter = new HashMap<String, Integer>();
    for (int i = 0; i < ngramRankedPassages.size() && i < this.nTop; i++) {
     
      TokennizedPassage passage = (TokennizedPassage) ngramRankedPassages.get(i);
            
      Collection<String> tokens = FSListFactory.createCollection(passage.getTokens(), null);
      String[] arrTokens = tokens.toArray(new String[0]);
      for (int j = 0; j < tokens.size(); j++) {
        if (counter.containsKey(arrTokens[j]))
          counter.put(arrTokens[j], counter.get(arrTokens[j]) + 1);
        else
          counter.put(arrTokens[j], 1);
      }
    }

    Set<Entry<String, Integer>> set = counter.entrySet();
    List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return (o2.getValue()).compareTo(o1.getValue());
      }
    });

    this.terms = new ArrayList<String>();
    for (int i = 0; i < this.nTerms && i < list.size(); i++) {
      Entry<String, Integer> e = list.get(i);
      this.terms.add(e.getKey());
      }
  }

  /**
   * Store a passage's tokens into a hashmap
   * @param passage
   * @return a hashmap with the paasage's tokens
   */
  protected HashMap<String, Integer> getPassageVec1Gram(TokennizedPassage passage) {
    HashMap<String, Integer> passageVector = new HashMap<String, Integer>();
    Collection<String> passageTokens = FSListFactory.createCollection(passage.getTokens(), null);
    String[] arrTokens = passageTokens.toArray(new String[0]);
    for (int i = 0; i < passageTokens.size(); i++) {
      passageVector.put(arrTokens[i], 1);
    }
    return passageVector;
  }

  /**
   * count the # of overlap tokens in the passage and the expanded question
   * @param pVec
   * @return the # of overlap tokens in the passage and the expanded question
   */
  protected Double evaluate(HashMap<String, Integer> pVec) {
    Double prod = 0.0;
    for (String gram : this.terms) {
      if (pVec.containsKey(gram))
        prod += 1;
    }
    return prod;
  }
}
