import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import myutil.PassageComparator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import type.Measurement;
import type.Passage;
import type.Question;
import type.TokennizedPassage;
import type.TokennizedQuestion;

/***
 * Evaluates the system in terms of several metrics p_at_1 p_at_5 rr ap map mrr
 * 
 * @author zhuyund
 *
 */
public class EvaluationAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    System.out.println(">> Evaluating system...");
    FSIndex passageIndex = aJCas.getAnnotationIndex(TokennizedPassage.type);
    FSIndex questionIndex = aJCas.getAnnotationIndex(TokennizedQuestion.type);

    // read passages and put into a hahsmap according to their question id.
    Iterator passageIter = passageIndex.iterator();
    HashMap<String, ArrayList<Passage>> questionPassages = new HashMap<String, ArrayList<Passage>>();
    while (passageIter.hasNext()) {
      Passage passage = (TokennizedPassage) passageIter.next();
      String qid = passage.getQuestionId();
      ArrayList<Passage> passages = questionPassages.get(qid);
      if (passages == null) {
        passages = new ArrayList<Passage>();
        questionPassages.put(qid, passages);
      }
      passages.add(passage);
    }

    // for each question, evaluate metrics
    // Also computes the MAP and MRR
    int nQuestion = 0;
    int cutOff = 5;
   
    Iterator questionIter = questionIndex.iterator();
    while (questionIter.hasNext()) {
      int tp = 0, fn = 0, fp = 0, nAns = 0;
      
      TokennizedQuestion question = (TokennizedQuestion) questionIter.next();
      String qid = question.getId();
      nQuestion += 1;

      // sort passages in term of score, descending order
      ArrayList<Passage> passages = questionPassages.get(qid);
      Collections.sort(passages, new PassageComparator());
      
      // count TPs, FPs, FNs
      
      for(int i = 0; i < passages.size() & i < cutOff; i++){
        
        if(passages.get(i).getLabel())
          ++tp;
        else{
          ++fp;
          }
      }
      for(int i = cutOff; i < passages.size(); i++){
        if(passages.get(i).getLabel()){
          ++fn;
          }
      }
      
      Measurement m = question.getMeasurement();
      m.setFp(fp);
      m.setFn(fn);
      m.setTp(tp);      
    }
  }
  
}
