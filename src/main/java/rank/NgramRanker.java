package rank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import myutil.FSListFactory;
import myutil.PassageComparator;
import type.Passage;
import type.Question;
import type.TokennizedPassage;
import type.TokennizedQuestion;

public class NgramRanker extends AbstractRanker {

  private int n;
  
  /**
   * By default n = 2
   */
  public NgramRanker(){
    this.n = 2;
  }
  
  /**
   * Init the NgramRanker with n = n
   * @param n
   */
  public NgramRanker(int n){
    this.n = n;
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
    return this.evaluate(this.getQuestionVec((TokennizedQuestion)question, this.n), this.getPassageVec((TokennizedPassage)passage, this.n));
  }
  
  /**
   * Store the question's n-grams into a hashmap.
   * @param question
   * @param n
   * @return a hashmap with all n-grams in the question
   */
  protected HashMap<String, Integer> getQuestionVec(TokennizedQuestion question, int n) {
    HashMap<String, Integer> questionVector = new HashMap<String, Integer>();
    String[] arrTokens = question.getStrTokens().split(" ");
    for(int i = 0; i <= arrTokens.length - n; i++){
      String grams = "";
      for(int j = 0; j < n; j++)
        grams += arrTokens[i + j] + ' ';
      questionVector.put(grams.trim(), 1);
    }
    return questionVector;
  }

  /**
   * Store the passage's n-grams into a hashmap
   * @param passage
   * @param n
   * @return a hashmap with all n-grams in the passage
   */
  protected HashMap<String, Integer> getPassageVec(TokennizedPassage passage, int n) {
    HashMap<String, Integer> passageVector = new HashMap<String, Integer>();
    String[] arrTokens = passage.getStrTokens().split(" ");
    for(int i = 0; i <= arrTokens.length - n; i++){
      String grams = "";
      for(int j = 0; j < n; j++)
        grams += arrTokens[i + j] + ' ';     
      passageVector.put(grams.trim(), 1);
    }
    return passageVector;
  }

  /**
   * Return the # of overlap n-grams betweent the question and the passage
   * @param qVec
   * @param pVec
   * @return # of overlap n-grams
   */
  protected Double evaluate(HashMap<String, Integer> qVec, HashMap<String, Integer> pVec) {
    Double prod = 0.0;
    for(String gram: qVec.keySet()){
      if(pVec.containsKey(gram)){
        prod += 1; // overlap
      }
    }
    return prod;
  }
}
