

/* First created by JCasGen Sun Oct 18 15:12:09 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringList;


/** Passage with tokens
 * Updated by JCasGen Mon Oct 26 21:36:49 EDT 2015
 * XML source: /Users/zhuyund/Documents/workspace/pi8-zhuyund/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class TokennizedPassage extends Passage {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TokennizedPassage.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TokennizedPassage() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public TokennizedPassage(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public TokennizedPassage(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public TokennizedPassage(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: tokens

  /** getter for tokens - gets 
   * @generated
   * @return value of the feature 
   */
  public StringList getTokens() {
    if (TokennizedPassage_Type.featOkTst && ((TokennizedPassage_Type)jcasType).casFeat_tokens == null)
      jcasType.jcas.throwFeatMissing("tokens", "type.TokennizedPassage");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TokennizedPassage_Type)jcasType).casFeatCode_tokens)));}
    
  /** setter for tokens - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokens(StringList v) {
    if (TokennizedPassage_Type.featOkTst && ((TokennizedPassage_Type)jcasType).casFeat_tokens == null)
      jcasType.jcas.throwFeatMissing("tokens", "type.TokennizedPassage");
    jcasType.ll_cas.ll_setRefValue(addr, ((TokennizedPassage_Type)jcasType).casFeatCode_tokens, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: strTokens

  /** getter for strTokens - gets 
   * @generated
   * @return value of the feature 
   */
  public String getStrTokens() {
    if (TokennizedPassage_Type.featOkTst && ((TokennizedPassage_Type)jcasType).casFeat_strTokens == null)
      jcasType.jcas.throwFeatMissing("strTokens", "type.TokennizedPassage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokennizedPassage_Type)jcasType).casFeatCode_strTokens);}
    
  /** setter for strTokens - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setStrTokens(String v) {
    if (TokennizedPassage_Type.featOkTst && ((TokennizedPassage_Type)jcasType).casFeat_strTokens == null)
      jcasType.jcas.throwFeatMissing("strTokens", "type.TokennizedPassage");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokennizedPassage_Type)jcasType).casFeatCode_strTokens, v);}    
  }

    