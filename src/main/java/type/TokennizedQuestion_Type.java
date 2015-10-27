
/* First created by JCasGen Sun Oct 18 15:12:09 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Question with tokens
 * Updated by JCasGen Mon Oct 26 21:36:49 EDT 2015
 * @generated */
public class TokennizedQuestion_Type extends Question_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TokennizedQuestion_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TokennizedQuestion_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TokennizedQuestion(addr, TokennizedQuestion_Type.this);
  			   TokennizedQuestion_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TokennizedQuestion(addr, TokennizedQuestion_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TokennizedQuestion.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.TokennizedQuestion");
 
  /** @generated */
  final Feature casFeat_tokens;
  /** @generated */
  final int     casFeatCode_tokens;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTokens(int addr) {
        if (featOkTst && casFeat_tokens == null)
      jcas.throwFeatMissing("tokens", "type.TokennizedQuestion");
    return ll_cas.ll_getRefValue(addr, casFeatCode_tokens);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTokens(int addr, int v) {
        if (featOkTst && casFeat_tokens == null)
      jcas.throwFeatMissing("tokens", "type.TokennizedQuestion");
    ll_cas.ll_setRefValue(addr, casFeatCode_tokens, v);}
    
  
 
  /** @generated */
  final Feature casFeat_strTokens;
  /** @generated */
  final int     casFeatCode_strTokens;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getStrTokens(int addr) {
        if (featOkTst && casFeat_strTokens == null)
      jcas.throwFeatMissing("strTokens", "type.TokennizedQuestion");
    return ll_cas.ll_getStringValue(addr, casFeatCode_strTokens);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setStrTokens(int addr, String v) {
        if (featOkTst && casFeat_strTokens == null)
      jcas.throwFeatMissing("strTokens", "type.TokennizedQuestion");
    ll_cas.ll_setStringValue(addr, casFeatCode_strTokens, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public TokennizedQuestion_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_tokens = jcas.getRequiredFeatureDE(casType, "tokens", "uima.cas.StringList", featOkTst);
    casFeatCode_tokens  = (null == casFeat_tokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokens).getCode();

 
    casFeat_strTokens = jcas.getRequiredFeatureDE(casType, "strTokens", "uima.cas.String", featOkTst);
    casFeatCode_strTokens  = (null == casFeat_strTokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_strTokens).getCode();

  }
}



    