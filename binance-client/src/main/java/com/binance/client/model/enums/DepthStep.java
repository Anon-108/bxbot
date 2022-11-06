package com.binance.client.model.enums;


/**
 * 深度步骤
 *  The aggregation depth type.
 *  聚合深度类型。
 */
public enum  DepthStep {

  /**
   * step0,step1,step2,step3,step4,step5
   * 步骤 0,步骤 1,步骤 2,步骤 3,步骤 4,步骤 5
   */
  STEP0("step0"),
  STEP1("step1"),
  STEP2("step2"),
  STEP3("step3"),
  STEP4("step4"),
  STEP5("step5"),
  ;

  private final String step;

  DepthStep(String step) {
    this.step = step;
  }

  public String getStep() {
    return step;
  }
}
